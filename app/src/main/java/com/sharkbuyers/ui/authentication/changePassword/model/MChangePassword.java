package com.sharkbuyers.ui.authentication.changePassword.model;

import android.os.Handler;
import android.os.Message;

import com.sharkbuyers.services.APIInterface;
import com.sharkbuyers.services.RetrofitCalls;
import com.sharkbuyers.ui.authentication.changePassword.ChangePasswordResponseModel;
import com.sharkbuyers.ui.authentication.changePassword.interfaces.IMChangePassword;
import com.sharkbuyers.ui.authentication.changePassword.interfaces.IPChangePassword;
import com.sharkbuyers.ui.authentication.changePassword.presenter.PChangePassword;
import com.sharkbuyers.ui.authentication.otpActivity.responseModel.OtpResponse;

public class MChangePassword implements IMChangePassword {

    IPChangePassword ipChangePassword;

    public MChangePassword(PChangePassword pChangePassword) {
        this.ipChangePassword=pChangePassword;
    }

    @Override
    public void changepasswordRestCall(String access_token, String old_password, String new_password) {
        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.changepassword_Api(access_token,old_password,new_password,mHandler);
    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.CHANGE_PASSWORD_SUCCESS:
                    ChangePasswordResponseModel changePasswordResponseModel = ((ChangePasswordResponseModel) msg.obj);
                    ipChangePassword.successResponseFromModel(changePasswordResponseModel);
                    break;
                case APIInterface.CHANGE_PASSWORD_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipChangePassword.failedResponseFromModel(invalidRequest);
                    break;

            }
        }
    };
}
