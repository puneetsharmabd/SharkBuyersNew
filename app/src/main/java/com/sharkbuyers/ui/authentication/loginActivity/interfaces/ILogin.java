package com.sharkbuyers.ui.authentication.loginActivity.interfaces;

import com.sharkbuyers.ui.authentication.loginActivity.responseModel.LoginResponseModel;

public interface ILogin {

    void loginSuccessFromPresenter(LoginResponseModel loginResponseModel);
    void loginFailedFromPresenter(String message);
}
