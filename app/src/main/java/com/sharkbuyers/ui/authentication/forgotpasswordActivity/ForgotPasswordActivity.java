package com.sharkbuyers.ui.authentication.forgotpasswordActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sharkbuyers.R;
import com.sharkbuyers.baseClass.BaseClass;
import com.sharkbuyers.networks.NetworkUtils;
import com.sharkbuyers.ui.authentication.forgotpasswordActivity.interfaces.IForgotPassword;
import com.sharkbuyers.ui.authentication.forgotpasswordActivity.interfaces.IPFrogotPassword;
import com.sharkbuyers.ui.authentication.forgotpasswordActivity.presenter.PForgotPassword;
import com.sharkbuyers.ui.authentication.loginActivity.LoginActivity;
import com.sharkbuyers.ui.mainActivity.MainActivity;
import com.sharkbuyers.utils.CommonMethods;
import com.sharkbuyers.utils.UtilsDialog;
import com.sharkbuyers.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends BaseClass implements IForgotPassword {

    @BindView(R.id.tvForgotText)
    TextView tvForgotText;
    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;
    @BindView(R.id.editEmail)
    EditText editEmail;

    @BindView(R.id.tvContinue)
    TextView tvContinue;

    Context context;

    Dialog progressDialog;
    IPFrogotPassword ipFrogotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        context = ForgotPasswordActivity.this;
        ipFrogotPassword=new PForgotPassword(this);
        tvForgotPassword.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvForgotText.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
    }

    @OnClick(R.id.tvContinue)
    public void onViewClicked() {
        validationOnEmail();
    }


    private void validationOnEmail() {

        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            if (editEmail.getText().toString().trim().isEmpty()) {
                editEmail.setError(getString(R.string.string_email));
            } else if (!CommonMethods.isValidEmail(editEmail.getText().toString())) {
                editEmail.setError(getString(R.string.string_validemail));
            } else {
                    progressDialog = UtilsDialog.ShowDialog(this);
                    ipFrogotPassword.forgotPassword(editEmail.getText().toString().trim());
            }
        }
    }

    @Override
    public void forgotpasswordSuccessFromPresenter(ForgotPasswordResponseModel forgotPasswordResponseModel) {
        progressDialog.dismiss();
        Intent intenthome = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
        intenthome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intenthome);
        Toast.makeText(context, "New password is sent to your email.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void forgotpasswordFailedFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }
}

