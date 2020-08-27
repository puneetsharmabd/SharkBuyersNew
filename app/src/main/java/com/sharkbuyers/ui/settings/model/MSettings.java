package com.sharkbuyers.ui.settings.model;

import android.os.Handler;
import android.os.Message;

import com.sharkbuyers.services.APIInterface;
import com.sharkbuyers.services.RetrofitCalls;
import com.sharkbuyers.ui.authentication.loginActivity.responseModel.LoginResponseModel;
import com.sharkbuyers.ui.others.OthersResponseModel;
import com.sharkbuyers.ui.settings.LogoutResponseModel;
import com.sharkbuyers.ui.settings.interfaces.IMSettings;
import com.sharkbuyers.ui.settings.interfaces.IPSettings;
import com.sharkbuyers.ui.settings.presenter.PSettings;

public class MSettings implements IMSettings {
    IPSettings ipSettings;

    public MSettings(PSettings pSettings) {
        this.ipSettings = pSettings;
    }

    @Override
    public void otherRestCall() {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.other_Api(mHandler);
    }

    @Override
    public void logoutRestCall(String access_token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.logout_Api(access_token, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.OTHER_SUCCESS:
                    OthersResponseModel othersResponseModel = ((OthersResponseModel) msg.obj);
                    ipSettings.successResponseOtherFromModel(othersResponseModel);
                    break;
                case APIInterface.OTHER_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipSettings.failedResponseOtherFromModel(invalidRequest);
                    break;
                case APIInterface.LOGOUT_SUCCESS:
                    LogoutResponseModel logoutResponseModel = ((LogoutResponseModel) msg.obj);
                    ipSettings.successResponseLogoutFromModel(logoutResponseModel);
                    break;
                case APIInterface.LOGOUT_FAILED:
                    String logout= String.valueOf(msg.obj);
                    ipSettings.failedResponseLogoutFromModel(logout);
                    break;

            }
        }
    };
}
