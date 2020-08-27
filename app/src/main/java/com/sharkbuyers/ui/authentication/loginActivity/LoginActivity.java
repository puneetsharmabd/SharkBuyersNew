package com.sharkbuyers.ui.authentication.loginActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.sharkbuyers.R;
import com.sharkbuyers.baseClass.BaseClass;
import com.sharkbuyers.networks.NetworkUtils;
import com.sharkbuyers.ui.authentication.forgotpasswordActivity.ForgotPasswordActivity;
import com.sharkbuyers.ui.authentication.joinnowActivity.JoinNowActivity;
import com.sharkbuyers.ui.authentication.loginActivity.interfaces.ILogin;
import com.sharkbuyers.ui.authentication.loginActivity.interfaces.IPLogin;
import com.sharkbuyers.ui.authentication.loginActivity.presenter.PLogin;
import com.sharkbuyers.ui.authentication.loginActivity.responseModel.LoginResponseModel;
import com.sharkbuyers.ui.mainActivity.MainActivity;
import com.sharkbuyers.utils.AppController;
import com.sharkbuyers.utils.AppController2;
import com.sharkbuyers.utils.CommonMethods;
import com.sharkbuyers.utils.UtilsDialog;
import com.sharkbuyers.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseClass implements ILogin {

    @BindView(R.id.editEmail)
    TextView editEmail;

    @BindView(R.id.editPassword)
    TextView editPassword;

    @BindView(R.id.checkRemberMe)
    CheckBox checkRemberMe;

    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;

    @BindView(R.id.tvSignIn)
    TextView tvSignIn;

    @BindView(R.id.tvDontHave)
    TextView tvDontHave;

    @BindView(R.id.tvRegisterHere)
    TextView tvRegisterHere;

    @BindView(R.id.tvLogin)
    TextView tvLogin;

    Context context;

    private SparseIntArray mErrorString;
    private static final int REQUEST_PERMISSIONS = 20;

    IPLogin ipLogin;
    Dialog progressDialog;
    String userEmail = "", userPassword = "", device_token = "";
    String email;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        context = LoginActivity.this;
        ipLogin = new PLogin(this);
        runTimePermission();
        Initialization();
        // Testing for update code 
    }

    private void Initialization() {

         email = AppController2.getInstance(context).getString(AppController.Key.USER_LOGIN_EMAIL_STR);
         password = AppController2.getInstance(context).getString(AppController.Key.USER_LOGIN_PASSWORD_STR);
        device_token = AppController.getInstance(context).getString(AppController.Key.SAVE_DEVICE_TOKEN);

        if (email != null || password != null) {
            editEmail.setText(email);
            editPassword.setText(password);
            checkRemberMe.setChecked(true);
        } else {
            editEmail.setText("");
            editPassword.setText("");
            checkRemberMe.setChecked(false);
        }

        tvLogin.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvDontHave.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        checkRemberMe.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvRegisterHere.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvLogin.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvForgotPassword.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));


    }


    @OnClick({R.id.tvForgotPassword, R.id.tvSignIn, R.id.tvRegisterHere})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvForgotPassword:
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.tvSignIn:
                validationOnLogin();
                break;
            case R.id.tvRegisterHere:
                Intent i = new Intent(LoginActivity.this, JoinNowActivity.class);
                startActivity(i);
                break;
        }
    }


    private void validationOnLogin() {

        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            if (editEmail.getText().toString().trim().isEmpty()) {
                editEmail.setError(getString(R.string.string_email));
            } else if (!CommonMethods.isValidEmail(editEmail.getText().toString())) {
                editEmail.setError(getString(R.string.string_validemail));
            } else if (editPassword.getText().toString().trim().isEmpty()) {
                editPassword.setError(getString(R.string.r_password_enter));
            } else {

                if (checkRemberMe.isChecked()) {
                    rememberMe(editEmail.getText().toString().trim(), editPassword.getText().toString().trim());
                } else {
                    clear();
                }
                progressDialog = UtilsDialog.ShowDialog(this);
                ipLogin.login(editEmail.getText().toString().trim(), editPassword.getText().toString().trim(), device_token);

            }
        }
    }

    public void rememberMe(String email, String password) {

        AppController2.getInstance(context).put(AppController.Key.USER_LOGIN_EMAIL_STR, email);
        AppController2.getInstance(context).put(AppController.Key.USER_LOGIN_PASSWORD_STR, password);
    }

    public void clear() {
        AppController2.getInstance(context).clear();

    }


    private void runTimePermission() {

        mErrorString = new SparseIntArray();
        int currentapiVersion = Build.VERSION.SDK_INT;
        // if current version is M or greater than M
        if (currentapiVersion >= Build.VERSION_CODES.M) {
            String[] array = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
            };

            requestAppPermissions(array, R.string.permission, REQUEST_PERMISSIONS);
        } else {
            onPermissionsGranted(REQUEST_PERMISSIONS);
        }

    }

    // check requested permissions are on or off
    public void requestAppPermissions(final String[] requestedPermissions, final int stringId, final int requestCode) {
        mErrorString.put(requestCode, stringId);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        boolean shouldShowRequestPermissionRationale = false;
        for (String permission : requestedPermissions) {
            permissionCheck = permissionCheck + ContextCompat.checkSelfPermission(this, permission);
            shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale || ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale) {
                Snackbar snack = Snackbar.make(findViewById(android.R.id.content), stringId, Snackbar.LENGTH_INDEFINITE);
                View view = snack.getView();
                TextView tv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
                tv.setTextColor(Color.WHITE);
                snack.setAction("GRANT", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(LoginActivity.this, requestedPermissions, requestCode);
                    }
                }).show();
            } else {
                ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
            }
        } else {
            onPermissionsGranted(requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int permission : grantResults) {
            permissionCheck = permissionCheck + permission;
        }
        if ((grantResults.length > 0) && permissionCheck == PackageManager.PERMISSION_GRANTED) {
            onPermissionsGranted(requestCode);
        } else {
        }
    }


    // if permissions granted succesfully
    private void onPermissionsGranted(int requestcode) {

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        // startTimer();
    }

    @Override
    public void loginSuccessFromPresenter(LoginResponseModel loginResponseModel) {
        progressDialog.dismiss();
        if (loginResponseModel != null && loginResponseModel.getData().size() > 0) {
            AppController.getInstance(context).put(AppController.Key.SAVE_ACCESS_TOKEN, loginResponseModel.getAccesssToken());
            AppController.getInstance(context).put(AppController.Key.USERID, loginResponseModel.getData().get(0).getId());
            AppController.getInstance(context).put(AppController.Key.FIRSTNAME, loginResponseModel.getData().get(0).getFirstName());
            AppController.getInstance(context).put(AppController.Key.LASTNAME, loginResponseModel.getData().get(0).getLastName());
            AppController.getInstance(context).put(AppController.Key.EMAIL, loginResponseModel.getData().get(0).getEmail());
            AppController.getInstance(context).put(AppController.Key.PHONENUMBER, loginResponseModel.getData().get(0).getPhoneNum());
            AppController.getInstance(context).put(AppController.Key.GENDER, loginResponseModel.getData().get(0).getGender());
            AppController.getInstance(context).put(AppController.Key.OCCUPATION, loginResponseModel.getData().get(0).getOccupation());
            AppController.getInstance(context).put(AppController.Key.ADDRESS, loginResponseModel.getData().get(0).getAddress());
            AppController.getInstance(context).put(AppController.Key.BRANDIMAGE, loginResponseModel.getData().get(0).getBrandImage());
            AppController.getInstance(context).put(AppController.Key.RESUME, loginResponseModel.getData().get(0).getResume());
            AppController.getInstance(context).put(AppController.Key.LATITUDE, loginResponseModel.getData().get(0).getLatitude());
            AppController.getInstance(context).put(AppController.Key.LONGITDUE, loginResponseModel.getData().get(0).getLongitude());
            AppController.getInstance(context).put(AppController.Key.STATE, loginResponseModel.getData().get(0).getStateName());
            AppController.getInstance(context).put(AppController.Key.COUNTRY, loginResponseModel.getData().get(0).getCountryName());
            int  countryID=  loginResponseModel.getData().get(0).getCountry().getId();
            int  stateID= loginResponseModel.getData().get(0).getState().getId();
            Log.d("data+++++++","data+++++++"+countryID);
            Log.d("data+++++++","data+++++++"+stateID);
            AppController.getInstance(context).put(AppController.Key.COUNTRY_ID, countryID);
            AppController.getInstance(context).put(AppController.Key.STATE_ID, stateID);
            Intent intenthome = new Intent(LoginActivity.this, MainActivity.class);
            intenthome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intenthome);
        } else {

        }

    }

    @Override
    public void loginFailedFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }
}
