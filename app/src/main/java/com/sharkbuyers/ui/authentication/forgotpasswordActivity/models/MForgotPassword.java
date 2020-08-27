package com.sharkbuyers.ui.authentication.forgotpasswordActivity.models;

import android.os.Handler;
import android.os.Message;

import com.sharkbuyers.services.APIInterface;
import com.sharkbuyers.services.RetrofitCalls;
import com.sharkbuyers.ui.authentication.forgotpasswordActivity.ForgotPasswordResponseModel;
import com.sharkbuyers.ui.authentication.forgotpasswordActivity.interfaces.IMForgotPassword;
import com.sharkbuyers.ui.authentication.forgotpasswordActivity.interfaces.IPFrogotPassword;
import com.sharkbuyers.ui.authentication.forgotpasswordActivity.presenter.PForgotPassword;
import com.sharkbuyers.ui.authentication.loginActivity.responseModel.LoginResponseModel;

public class MForgotPassword implements IMForgotPassword {

    IPFrogotPassword ipFrogotPassword;
    public MForgotPassword(PForgotPassword pForgotPassword) {
        this.ipFrogotPassword=pForgotPassword;
    }

    @Override
    public void forgotPasswordRestCalls(String email) {
        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.forgotpassword_Api(email,mHandler);
    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.FORGOT_PASSWORD_SUCCESS:
                    ForgotPasswordResponseModel forgotPasswordResponseModel = ((ForgotPasswordResponseModel) msg.obj);
                    ipFrogotPassword.forgotPasswordSuccessFromModel(forgotPasswordResponseModel);
                    break;
                case APIInterface.FORGOT_PASSWORD_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipFrogotPassword.forgotPasswordFailedFromModel(invalidRequest);
                    break;

            }
        }
    };
}
