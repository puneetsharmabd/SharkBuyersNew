package com.sharkbuyers.ui.authentication.forgotpasswordActivity.presenter;

import com.sharkbuyers.ui.authentication.forgotpasswordActivity.ForgotPasswordActivity;
import com.sharkbuyers.ui.authentication.forgotpasswordActivity.ForgotPasswordResponseModel;
import com.sharkbuyers.ui.authentication.forgotpasswordActivity.interfaces.IForgotPassword;
import com.sharkbuyers.ui.authentication.forgotpasswordActivity.interfaces.IMForgotPassword;
import com.sharkbuyers.ui.authentication.forgotpasswordActivity.interfaces.IPFrogotPassword;
import com.sharkbuyers.ui.authentication.forgotpasswordActivity.models.MForgotPassword;

public class PForgotPassword implements IPFrogotPassword {
    IForgotPassword iForgotPassword;
    IMForgotPassword imForgotPassword;
    public PForgotPassword(ForgotPasswordActivity forgotPasswordActivity) {
        this.iForgotPassword=forgotPasswordActivity;
    }

    @Override
    public void forgotPassword(String email) {
        imForgotPassword=new MForgotPassword(this);
        imForgotPassword.forgotPasswordRestCalls(email);
    }

    @Override
    public void forgotPasswordSuccessFromModel(ForgotPasswordResponseModel forgotPasswordResponseModel) {
        iForgotPassword.forgotpasswordSuccessFromPresenter(forgotPasswordResponseModel);
    }

    @Override
    public void forgotPasswordFailedFromModel(String message) {
        iForgotPassword.forgotpasswordFailedFromPresenter(message);
    }
}
