package com.sharkbuyers.ui.authentication.loginActivity.viewModel;

import android.os.Handler;
import android.os.Message;

import com.sharkbuyers.services.APIInterface;
import com.sharkbuyers.services.RetrofitCalls;
import com.sharkbuyers.ui.authentication.loginActivity.interfaces.IMLogin;
import com.sharkbuyers.ui.authentication.loginActivity.interfaces.IPLogin;
import com.sharkbuyers.ui.authentication.loginActivity.presenter.PLogin;
import com.sharkbuyers.ui.authentication.loginActivity.responseModel.LoginResponseModel;


public class MLogin implements IMLogin {
    IPLogin ipLogin;

    public MLogin(PLogin pLogin) {
        this.ipLogin=pLogin;
    }

    @Override
    public void loginRestCalls(String email, String password, String device_token) {
        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.login_Api(email,password,device_token,mHandler);
    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.USER_LOGIN_SUCCESS:
                    LoginResponseModel loginResponseModel = ((LoginResponseModel) msg.obj);
                    ipLogin.loginSuccessFromModel(loginResponseModel);
                    break;
                case APIInterface.USER_LOGIN_FAILD:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipLogin.loginFaileddFromModel(invalidRequest);
                    break;

            }
        }
    };
}
