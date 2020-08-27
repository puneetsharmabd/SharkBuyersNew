package com.sharkbuyers.ui.authentication.otpActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.sharkbuyers.R;
import com.sharkbuyers.baseClass.BaseClass;
import com.sharkbuyers.networks.NetworkUtils;
import com.sharkbuyers.ui.authentication.loginActivity.LoginActivity;
import com.sharkbuyers.ui.authentication.otpActivity.interfaces.IOTP;
import com.sharkbuyers.ui.authentication.otpActivity.interfaces.IPOTP;
import com.sharkbuyers.ui.authentication.otpActivity.responseModel.OtpResponse;
import com.sharkbuyers.utils.UtilsDialog;
import com.sharkbuyers.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OTPActivity extends BaseClass implements IOTP {

    @BindView(R.id.otpView)
    PinView otpView;

    @BindView(R.id.tvResedCode)
    TextView tvResedCode;

    @BindView(R.id.tvVerifiy)
    TextView tvVerifiy;

    @BindView(R.id.tvCodeSend)
    TextView tvCodeSend;

    @BindView(R.id.tvDontHave)
    TextView tvDontHave;

    Context context;

    IPOTP ipotp;
    Dialog progressDialog;
    String email="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);
        ButterKnife.bind(this);
        context = OTPActivity.this;
        ipotp = new POTP(this);

        email=getIntent().getStringExtra("email");


        tvCodeSend.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvDontHave.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvResedCode.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
    }

    @OnClick({R.id.otpView, R.id.tvResedCode, R.id.tvVerifiy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.otpView:
                break;
            case R.id.tvResedCode:
                break;
            case R.id.tvVerifiy:
                validationOnOTP();
                break;
        }
    }

    private void validationOnOTP() {

        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            if (otpView.getText().toString().trim().isEmpty()) {
                otpView.setError(getString(R.string.enter_otp));

            } else {
                progressDialog = UtilsDialog.ShowDialog(this);
                ipotp.setOtp(otpView.getText().toString().trim(), email);
            }
        }
    }

    @Override
    public void successResponseFromPresenter(OtpResponse otpResponse) {
        progressDialog.dismiss();
        Intent intent = new Intent(OTPActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }
}
