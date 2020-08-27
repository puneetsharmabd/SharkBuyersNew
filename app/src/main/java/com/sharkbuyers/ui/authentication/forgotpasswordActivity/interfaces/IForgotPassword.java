package com.sharkbuyers.ui.authentication.forgotpasswordActivity.interfaces;

import com.sharkbuyers.ui.authentication.forgotpasswordActivity.ForgotPasswordResponseModel;

public interface IForgotPassword {
    void forgotpasswordSuccessFromPresenter(ForgotPasswordResponseModel forgotPasswordResponseModel);
    void forgotpasswordFailedFromPresenter(String message);
}
