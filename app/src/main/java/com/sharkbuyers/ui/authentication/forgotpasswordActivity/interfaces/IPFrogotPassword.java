package com.sharkbuyers.ui.authentication.forgotpasswordActivity.interfaces;

import com.sharkbuyers.ui.authentication.forgotpasswordActivity.ForgotPasswordResponseModel;

public interface IPFrogotPassword {
    void forgotPassword(String email);
    void forgotPasswordSuccessFromModel(ForgotPasswordResponseModel forgotPasswordResponseModel);
    void forgotPasswordFailedFromModel(String message);
}
