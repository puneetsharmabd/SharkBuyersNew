package com.sharkbuyers.ui.authentication.loginActivity.interfaces;

import com.sharkbuyers.ui.authentication.loginActivity.responseModel.LoginResponseModel;

public interface IPLogin {

    void login(String email,String password,String device_token);
    void loginSuccessFromModel(LoginResponseModel loginResponseModel);
    void loginFaileddFromModel(String message);
}
