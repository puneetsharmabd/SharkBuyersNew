package com.sharkbuyers.ui.authentication.uploadresume;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.sharkbuyers.R;
import com.sharkbuyers.baseClass.BaseClass;
import com.sharkbuyers.networks.NetworkUtils;
import com.sharkbuyers.ui.authentication.country.SelectCountryActivity;
import com.sharkbuyers.ui.authentication.otpActivity.OTPActivity;
import com.sharkbuyers.ui.authentication.country.adapters.CountryAdapter;
import com.sharkbuyers.ui.authentication.country.adapters.StateAdapter;
import com.sharkbuyers.ui.authentication.state.StateActivity;
import com.sharkbuyers.ui.authentication.uploadresume.interfaces.IPRegister;
import com.sharkbuyers.ui.authentication.uploadresume.interfaces.IRegister;
import com.sharkbuyers.ui.authentication.uploadresume.presenter.PRegister;
import com.sharkbuyers.ui.authentication.uploadresume.responseModel.RegisterResponseModel;
import com.sharkbuyers.utils.UtilsDialog;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.sharkbuyers.utils.CommonMethods.getPath;


public class UploadResumeActivity extends BaseClass implements IPickResult, IRegister {

    @BindView(R.id.editOccupations)
    EditText editOccupations;
    @BindView(R.id.editAddress)
    EditText editAddress;

    @BindView(R.id.editCountry)
    EditText editCountry;

    @BindView(R.id.editCity)
    EditText editCity;

    @BindView(R.id.editBrand)
    EditText editBrand;
    @BindView(R.id.editResume)
    EditText editResume;
    @BindView(R.id.layoutUpLoad)
    RelativeLayout layoutUpLoad;

    @BindView(R.id.tvRegisterNow)
    TextView tvRegisterNow;
    Context context;
    private static final int AUTOCOMPLETE_REQUEST_CODE = 1;

    MultipartBody.Part imageMultipart;

    String firstName = "", lastName = "", email = "", phone = "", gender = "", confirm_password = "";
    String latitude = "", longitude = "";

    IPRegister ipRegister;
    Dialog progressDialog;
    String country_id = "", state_id = "";
    String country_name = "", state_name = "";

    String intentFrom = "";
    RequestBody imgrequest;
    String imgePath;
    private static final int COUNTRY_REQUEST_CODE = 11;
    private static final int STATE_REQUEST_CODE = 12;

    RelativeLayout layoutUpLoad1;
    EditText editBrand1;
    public static String pathForResume = "";

    public static File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_resume);
        layoutUpLoad1 = findViewById(R.id.layoutUpLoad1);
        editBrand1 = findViewById(R.id.editBrand1);
        ButterKnife.bind(this);
        context = UploadResumeActivity.this;
        ipRegister = new PRegister(this);
        Initialization();
        layoutUpLoad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                val intent = Intent()
//                        .setType("*/*")
//                        .setAction(Intent.ACTION_GET_CONTENT)
//
//                startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                try {
                    startActivityForResult(
                            Intent.createChooser(intent, "Select a File to Upload"),
                            111);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Potentially direct the user to the Market with a Dialog
                    Toast.makeText(context, "Please install a File Manager.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void Initialization() {
        editCountry = (EditText) findViewById(R.id.editCountry);

        firstName = getIntent().getStringExtra("firstName");
        lastName = getIntent().getStringExtra("lastName");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");
        gender = getIntent().getStringExtra("gender");
        confirm_password = getIntent().getStringExtra("password");

    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.layoutUpLoad, R.id.tvRegisterNow, R.id.editAddress, R.id.editCountry, R.id.editCity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layoutUpLoad:
                PickImageDialog.build(new PickSetup().setButtonOrientation(LinearLayoutCompat.HORIZONTAL)).show(this);
                break;
            case R.id.tvRegisterNow:
                valiationOnResume();

                break;

            case R.id.editAddress:

                break;
            case R.id.editCountry:
                intentFrom = "register";
                Intent intent = new Intent(UploadResumeActivity.this, SelectCountryActivity.class);
                intent.putExtra("intentFrom", intentFrom);
                startActivityForResult(intent, COUNTRY_REQUEST_CODE);
                break;
            case R.id.editCity:
                intentFrom = "register";
                if (country_id.equals("") && country_id == null) {
                    Toast.makeText(context, "Select country first ", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent_state = new Intent(UploadResumeActivity.this, StateActivity.class);
                    intent_state.putExtra("country_id", country_id);
                    intent_state.putExtra("intentFrom", intentFrom);
                    startActivityForResult(intent_state, STATE_REQUEST_CODE);
                }
                break;
        }
    }

    private void valiationOnResume() {
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            if (editOccupations.getText().toString().isEmpty()) {
                editOccupations.setError(getString(R.string.string_occupations));
            } else if (editCountry.getText().toString().isEmpty()) {
                editCountry.setError(getString(R.string.string_country));
            } else if (editCity.getText().toString().isEmpty()) {
                editCity.setError(getString(R.string.string_city));
            } else if (editBrand.getText().toString().isEmpty()) {
                editBrand.setError(getString(R.string.string_address));
            } else if (editBrand1.getText().toString().isEmpty()) {
                editBrand1.setError(getString(R.string.string_resume1));
            }
            else {
                progressDialog = UtilsDialog.ShowDialog(this);
                ipRegister.register(firstName, lastName, email, phone, gender, editAddress.getText().toString().trim(),
                        editOccupations.getText().toString().trim(), confirm_password, editResume.getText().toString().trim(),
                        "12.00", "12.00", state_id, country_id, imgePath);
            }
        }


    }

    @Override
    public void onPickResult(PickResult result) {
        if (result.getError() == null) {

            //If you want the Uri.
            //Mandatory to refresh image from Uri.
            //getImageView().setImageURI(null);

            //Setting the real returned image.
            //getImageView().setImageURI(r.getUri());


            //If you want the Bitmap.
            //SingleImages.setImageBitmap(result.getBitmap());

            imageMultipart = sendImageFileToserver(result.getBitmap(), "image", this);

            //Image path
            imgePath = result.getPath();

            Uri filePath = result.getUri();
            File file = getFile(context, filePath);
            String imageName = file.getName();
            editBrand.setText(imageName);

        } else {
            Toast.makeText(this, result.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static File getFile(Context context, Uri uri) {
        if (uri != null) {
            String path = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                path = getPath(context, uri);
            }
            if (path != null) {
                return new File(path);
            }
        }
        return null;
    }

    private MultipartBody.Part sendImageFileToserver(Bitmap imgBitmap, String image, Context context) {
        File filesDir = context.getFilesDir();
        File file = new File(filesDir, image + System.currentTimeMillis() + ".png");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        imgBitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
        byte[] bitmapdata = bos.toByteArray();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(bitmapdata);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData(image, file.getName(), reqFile);
        imgrequest = RequestBody.create(MediaType.parse("text/plain"), image);

        return fileToUpload;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COUNTRY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String county_name = data.getStringExtra("county_name");
                country_id = data.getStringExtra("country_id");
                editCountry.setText(county_name);

            }

        } else if (requestCode == STATE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String state_name = data.getStringExtra("state_name");
                state_id = data.getStringExtra("state_id");
                editCity.setText(state_name);

            }
        }
        else if (requestCode == 111)
        {
            if (resultCode == RESULT_OK) {
////                Uri uri = data.getData();
////                String FilePath = getRealPathFromURI(uri);
////                Log.d("++++++++","++filepath++"+FilePath);
//                // Get the Uri of the selected file
//                Uri uri = data.getData();
//                Log.d("TAG", "File Uri: " + uri.toString());
//                // Get the path
//                //String path = FileUtils.getPath(this, uri);
//                String path = uri.getPath();
//
//                Log.d("TAG", "File Path: " + path);
//                // Get the file instance
//                File file2 = new File(path);
//                // Initiate the upload
//                Log.d("++++++++","++filepath++"+file2.getName());
//                editBrand1.setText(file2.getName());
//                pathForResume = path;
//                Log.d("++++++++","++pathForResume++"+pathForResume);
//                String path1 = Environment.getExternalStorageDirectory() + "/" + "SharkBuyers/";
//// Create the parent path
//                File dir = new File(path1);
//                if (!dir.exists()) {
//                    dir.mkdirs();
//                }
//
//                String fullName = path1 + file2.getName();
//                file = new File(fullName);
                // Get the Uri of the selected file
                Uri uri = data.getData();
                Log.d("TAG", "File Uri: " + uri.toString());
                // Get the path
                String path = null;
                try {
                    path = getPathN(this, uri);
                    if (path==null)
                    {
                        Toast.makeText(context, "Choose a doc file to upload.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Log.d("TAG", "File Path: " + path);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                Log.d("TAG", "File Path: " + path);
                // Get the file instance
                file = new File(path);
                    editBrand1.setText(file.getName());
                // Initiate the upload
            }
        }


}

    public static String getPathN(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public String getRealPathFromURI(Uri contentUri) {
        String [] proj      = {MediaStore.Images.Media.DATA};
        Cursor cursor       = getContentResolver().query( contentUri, proj, null, null,null);
        if (cursor == null) return null;
        int column_index    = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    public void successResponseFromPresenter(RegisterResponseModel registerResponseModel) {
        progressDialog.dismiss();
        if (registerResponseModel != null && registerResponseModel.getData().size() > 0) {
            String email = registerResponseModel.getData().get(0).getEmail();
            Intent intent = new Intent(UploadResumeActivity.this, OTPActivity.class);
            intent.putExtra("email", email);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {

        }
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }


}
