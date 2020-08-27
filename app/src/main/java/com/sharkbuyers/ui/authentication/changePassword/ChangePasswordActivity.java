package com.sharkbuyers.ui.authentication.changePassword;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sharkbuyers.R;
import com.sharkbuyers.baseClass.BaseClass;
import com.sharkbuyers.networks.NetworkUtils;
import com.sharkbuyers.ui.authentication.changePassword.interfaces.IChangePassword;
import com.sharkbuyers.ui.authentication.changePassword.interfaces.IPChangePassword;
import com.sharkbuyers.ui.authentication.changePassword.presenter.PChangePassword;
import com.sharkbuyers.ui.authentication.forgotpasswordActivity.interfaces.IPFrogotPassword;
import com.sharkbuyers.ui.authentication.loginActivity.LoginActivity;
import com.sharkbuyers.ui.authentication.otpActivity.OTPActivity;
import com.sharkbuyers.ui.mainActivity.MainActivity;
import com.sharkbuyers.utils.AppController;
import com.sharkbuyers.utils.CommonMethods;
import com.sharkbuyers.utils.UtilsDialog;
import com.sharkbuyers.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends BaseClass implements IChangePassword {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvOrder)
    TextView tvOrder;
    @BindView(R.id.editOldPassword)
    EditText editOldPassword;
    @BindView(R.id.editNewPassword)
    EditText editNewPassword;
    @BindView(R.id.editConfirmPassword)
    EditText editConfirmPassword;
    @BindView(R.id.tvChange)
    TextView tvChange;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    Context context;
    IPChangePassword ipFrogotPassword;
    Dialog progressDialog;
    String access_token="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        context=ChangePasswordActivity.this;
        access_token= AppController.getInstance(context).getString(AppController.Key.SAVE_ACCESS_TOKEN);
        ipFrogotPassword=new PChangePassword(this);
        tvOrder.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));

    }

    @OnClick({R.id.imgBack, R.id.tvChange})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvChange:
                validationOnChangePassword();
                break;
        }
    }

    private void validationOnChangePassword() {

        if (editOldPassword.getText().toString().trim().isEmpty()) {
            editOldPassword.setError(getString(R.string.old_password_enter));
        }else if (editNewPassword.getText().toString().trim().isEmpty()) {
            editNewPassword.setError(getString(R.string.new_password_enter));
        } else if (editNewPassword.length() < 6 || editNewPassword.length() > 16) {
            editNewPassword.setError(getString(R.string.password_length));
        } else if (!CommonMethods.isValidPassword(editNewPassword.getText().toString().trim())) {
            editNewPassword.setError(getString(R.string.password_pattern_hint));
        } else if (editConfirmPassword.getText().toString().trim().isEmpty()) {
            editConfirmPassword.setError(getString(R.string.confirm_password_enter));
        } else if (!editConfirmPassword.getText().toString().trim().matches(editNewPassword.getText().toString().trim())) {
            editConfirmPassword.setError(getString(R.string.r_password_not_match));
        }else {
            if (NetworkUtils.isNetworkConnectionAvailable(context)){
                progressDialog = UtilsDialog.ShowDialog(this);
                ipFrogotPassword.changepassword(access_token,editOldPassword.getText().toString().trim(),editConfirmPassword.getText().toString().trim());
            }
        }
    }

    @Override
    public void successResponseFromPresenter(ChangePasswordResponseModel changePasswordResponseModel) {
        progressDialog.dismiss();
        Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void failedResponseFromPresenter(String messages) {
        progressDialog.dismiss();
        String error_message = messages;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }
}
