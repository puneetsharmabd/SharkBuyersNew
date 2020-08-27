package com.sharkbuyers.ui.authentication.editprofile;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.bumptech.glide.Glide;
import com.sharkbuyers.R;
import com.sharkbuyers.baseClass.BaseClass;
import com.sharkbuyers.networks.NetworkUtils;
import com.sharkbuyers.ui.authentication.ProfileActivity.ProfileActivity;
import com.sharkbuyers.ui.authentication.country.SelectCountryActivity;
import com.sharkbuyers.ui.authentication.editprofile.interfaces.IEditProfile;
import com.sharkbuyers.ui.authentication.editprofile.interfaces.IPEditProfile;
import com.sharkbuyers.ui.authentication.editprofile.presenter.PEditProfile;
import com.sharkbuyers.ui.authentication.loginActivity.LoginActivity;
import com.sharkbuyers.ui.authentication.state.StateActivity;
import com.sharkbuyers.ui.mainActivity.MainActivity;
import com.sharkbuyers.utils.AppController;
import com.sharkbuyers.utils.CommonMethods;
import com.sharkbuyers.utils.Constant;
import com.sharkbuyers.utils.UtilsDialog;
import com.sharkbuyers.utils.UtilsFontFamily;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;



public class EditProfileActivity extends BaseClass implements IPickResult, IEditProfile {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.editFirstName)
    EditText editFirstName;
    @BindView(R.id.editLastName)
    EditText editLastName;
    @BindView(R.id.editEmail)
    EditText editEmail;
    @BindView(R.id.editPhone)
    EditText editPhone;
    @BindView(R.id.editGender)
    EditText editGender;

    @BindView(R.id.editOccupations)
    EditText editOccupations;

    @BindView(R.id.tvSave)
    TextView tvSave;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.imgeBrand)
    CircleImageView imgeBrand;
    @BindView(R.id.layoutCamera)
    RelativeLayout layoutCamera;
    Context context;
    IPEditProfile ipEditProfile;
    Dialog progressDialog;
    MultipartBody.Part imageMultipart;

    Dialog dialog;
    String access_token="";

    private static final int COUNTRY_REQUEST_CODE = 012;
    private static final int STATE_REQUEST_CODE = 013;

    public static  EditText editECountry;;
    public static  EditText editEState;;
    String country_id = "", state_id = "";
    String intentFrom;
    Bitmap bitmap;

    String imgePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        context = EditProfileActivity.this;
        access_token= AppController.getInstance(context).getString(AppController.Key.SAVE_ACCESS_TOKEN);
        country_id= String.valueOf(AppController.getInstance(context).getInt(AppController.Key.COUNTRY_ID,0));
        state_id= String.valueOf(AppController.getInstance(context).getInt(AppController.Key.STATE_ID,0));
        imgePath= Constant.IMAGE_URL + AppController.getInstance(context).getString(AppController.Key.BRANDIMAGE);
        ipEditProfile = new PEditProfile(this);

        editECountry=(EditText)findViewById(R.id.editECountry);
        editEState=(EditText)findViewById(R.id.editEState);


        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        editFirstName.setText(CommonMethods.upperCase(AppController.getInstance(context).getString(AppController.Key.FIRSTNAME)));
        editLastName.setText(CommonMethods.upperCase(AppController.getInstance(context).getString(AppController.Key.LASTNAME)));
        editPhone.setText(AppController.getInstance(context).getString(AppController.Key.PHONENUMBER));
        editEmail.setText(AppController.getInstance(context).getString(AppController.Key.EMAIL));
        editOccupations.setText(CommonMethods.upperCase(AppController.getInstance(context).getString(AppController.Key.OCCUPATION)));
        editECountry.setText(CommonMethods.upperCase(AppController.getInstance(context).getString(AppController.Key.COUNTRY)));
        editEState.setText(CommonMethods.upperCase(AppController.getInstance(context).getString(AppController.Key.STATE)));
        editGender.setText(CommonMethods.upperCase(AppController.getInstance(context).getString(AppController.Key.GENDER)));

        if (AppController.getInstance(context).getString(AppController.Key.BRANDIMAGE)!=null){
            Glide.with(context).load(Constant.IMAGE_URL + AppController.getInstance(context).getString(AppController.Key.BRANDIMAGE))
                    .placeholder(R.drawable.ic_e_dummy)
                    .into(imgeBrand);
            new getImagefromURL(imgeBrand).execute(Constant.IMAGE_URL + AppController.getInstance(context).getString(AppController.Key.BRANDIMAGE));

        }


        editECountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentFrom = "editprofile";
                Intent intent = new Intent(EditProfileActivity.this, SelectCountryActivity.class);
                intent.putExtra("intentFrom", intentFrom);
                startActivityForResult(intent, COUNTRY_REQUEST_CODE);
            }
        });
        editEState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentFrom = "editprofile";
                if (country_id.equals("") && country_id == null) {
                    Toast.makeText(context, "Select country first ", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent_state = new Intent(EditProfileActivity.this, StateActivity.class);
                    intent_state.putExtra("country_id", country_id);
                    intent_state.putExtra("intentFrom", intentFrom);
                    startActivityForResult(intent_state, STATE_REQUEST_CODE);
                }
            }
        });
    }

    @OnClick({R.id.imgBack, R.id.tvSave, R.id.layoutCamera, R.id.editGender})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvSave:
                validationOnEditProfile();
                break;
            case R.id.layoutCamera:
                PickImageDialog.build(new PickSetup().setButtonOrientation(LinearLayout.HORIZONTAL)).show(this);

                break;

            case R.id.editGender:
                genderDialog();
                break;
        }
    }

    private void genderDialog() {

        dialog = new Dialog(context, R.style.DialogThemes);
        dialog.setContentView(R.layout.dialog_gender);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView tv_cancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tv_male = (TextView) dialog.findViewById(R.id.tv_male);
        TextView tv_female = (TextView) dialog.findViewById(R.id.tv_female);

        tv_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editGender.setText("Male");
                dialog.dismiss();
            }
        });

        tv_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editGender.setText("Female");
                dialog.dismiss();
            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private void validationOnEditProfile() {
        if (editFirstName.getText().toString().trim().isEmpty()) {
            editFirstName.setError(getString(R.string.string_first_name));
        } else if (editFirstName.length() < 3 || editFirstName.length() > 15) {
            editFirstName.setError(getString(R.string.username_length));
        } else if (editLastName.getText().toString().trim().isEmpty()) {
            editLastName.setError(getString(R.string.string_last_name));
        } else if (editLastName.length() < 3 || editLastName.length() > 15) {
            editLastName.setError(getString(R.string.lastname_length));
        } else if (editEmail.getText().toString().trim().isEmpty()) {
            editEmail.setError(getString(R.string.string_email));
        } else if (!CommonMethods.isValidEmail(editEmail.getText().toString())) {
            editEmail.setError(getString(R.string.string_validemail));
        } else if (editPhone.getText().toString().trim().isEmpty()) {
            editPhone.setError(getString(R.string.string_phone_number));
        } else if (!CommonMethods.isValidMobile(editPhone.getText().toString())) {
            editPhone.setError(getString(R.string.enter_valid_phone_number));
        } else if (editGender.getText().toString().isEmpty()) {
            editGender.setError(getString(R.string.string_gender));
        } else if (editECountry.getText().toString().isEmpty()) {
            editECountry.setError(getString(R.string.string_occupations));
        } else if (editEState.getText().toString().isEmpty()) {
            editEState.setError(getString(R.string.string_country));
        } else if (editOccupations.getText().toString().isEmpty()) {
            editOccupations.setError(getString(R.string.string_city));
        } else {
            if (NetworkUtils.isNetworkConnectionAvailable(context)){
                progressDialog = UtilsDialog.ShowDialog(this);
                ipEditProfile.editProfile(access_token,editFirstName.getText().toString().trim(),
                        editLastName.getText().toString().trim(), editEmail.getText().toString().trim(),
                        editPhone.getText().toString().trim(), editGender.getText().toString().trim(), "Dummy Address",
                        editOccupations.getText().toString().trim(), "0.0", "0.0", state_id, country_id, imgePath);

            }

        }
    }

    @Override
    public void editProfilesuccessResponseFromPresenter(EditProfileResponseModel editProfileResponseModel) {
        progressDialog.dismiss();
        if (editProfileResponseModel != null && editProfileResponseModel.getData().size() > 0) {
            AppController.getInstance(context).put(AppController.Key.FIRSTNAME, editProfileResponseModel.getData().get(0).getFirstName());
            AppController.getInstance(context).put(AppController.Key.LASTNAME, editProfileResponseModel.getData().get(0).getLastName());
            AppController.getInstance(context).put(AppController.Key.EMAIL, editProfileResponseModel.getData().get(0).getEmail());
            AppController.getInstance(context).put(AppController.Key.PHONENUMBER, editProfileResponseModel.getData().get(0).getPhoneNum());
            AppController.getInstance(context).put(AppController.Key.GENDER, editProfileResponseModel.getData().get(0).getGender());
            AppController.getInstance(context).put(AppController.Key.OCCUPATION, editProfileResponseModel.getData().get(0).getOccupation());
            AppController.getInstance(context).put(AppController.Key.ADDRESS, editProfileResponseModel.getData().get(0).getAddress());
            AppController.getInstance(context).put(AppController.Key.BRANDIMAGE, editProfileResponseModel.getData().get(0).getBrandImage());
            AppController.getInstance(context).put(AppController.Key.STATE, editProfileResponseModel.getData().get(0).getStateName());
            AppController.getInstance(context).put(AppController.Key.COUNTRY, editProfileResponseModel.getData().get(0).getCountryName());

            Intent edit = new Intent(EditProfileActivity.this, ProfileActivity.class);
            edit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(edit);
        } else {

        }
    }

    @Override
    public void editProfileerrorResponseFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPickResult(PickResult result) {
        if (result.getError() == null) {

            //If you want the Uri.
            //Mandatory to refresh image from Uri.
            //getImageView().setImageURI(null);

            //Setting the real returned image.
            //getImageView().setImageURI(r.getUri());
            //Image path
            imgePath = result.getPath();

            //If you want the Bitmap.
            imgeBrand.setImageBitmap(result.getBitmap());
            imageMultipart = sendImageFileToserver(result.getBitmap(),"image",EditProfileActivity.this);


        } else {
            Toast.makeText(this, result.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private MultipartBody.Part sendImageFileToserver(Bitmap imgBitmap, String image, Context context) {
        File filesDir = context.getFilesDir();
        File file = new File(filesDir, image + System.currentTimeMillis() + ".png");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
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
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), reqFile);
        RequestBody  img_ReqBody = RequestBody.create(MediaType.parse("text/plain"), image);

        return fileToUpload;

    }

    //Get Image from Url
    public class getImagefromURL extends AsyncTask<String, Void, Bitmap> {

        CircleImageView imgeBrand;

        public getImagefromURL(CircleImageView imgeBrand) {
            this.imgeBrand = imgeBrand;
        }

        @Override
        protected Bitmap doInBackground(String... url) {

            String urlimage = url[0];

            bitmap = null;


            try {
                InputStream stream = new URL(urlimage).openStream();
                bitmap = BitmapFactory.decodeStream(stream);
                imageMultipart = sendImageFileToserver(bitmap, "image", EditProfileActivity.this);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgeBrand.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COUNTRY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String county_name = data.getStringExtra("county_name");
                country_id = data.getStringExtra("country_id");
                editECountry.setText(county_name);

            }

        } else if (requestCode == STATE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String state_name = data.getStringExtra("state_name");
                state_id = data.getStringExtra("state_id");
                editEState.setText(state_name);

            }
        }
    }
}
