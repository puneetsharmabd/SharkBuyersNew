package com.sharkbuyers.ui.authentication.changePassword.presenter;

import com.sharkbuyers.ui.authentication.changePassword.ChangePasswordActivity;
import com.sharkbuyers.ui.authentication.changePassword.ChangePasswordResponseModel;
import com.sharkbuyers.ui.authentication.changePassword.interfaces.IChangePassword;
import com.sharkbuyers.ui.authentication.changePassword.interfaces.IMChangePassword;
import com.sharkbuyers.ui.authentication.changePassword.interfaces.IPChangePassword;
import com.sharkbuyers.ui.authentication.changePassword.model.MChangePassword;


public class PChangePassword implements IPChangePassword {
    IChangePassword iChangePassword;
    IMChangePassword imChangePassword;

    public PChangePassword(ChangePasswordActivity changePasswordActivity) {
        this.iChangePassword=changePasswordActivity;
    }

    @Override
    public void changepassword(String access_token, String old_password, String new_password) {
        imChangePassword=new MChangePassword(this);
        imChangePassword.changepasswordRestCall(access_token,old_password,new_password);
    }

    @Override
    public void successResponseFromModel(ChangePasswordResponseModel changePasswordResponseModel) {
        iChangePassword.successResponseFromPresenter(changePasswordResponseModel);
    }

    @Override
    public void failedResponseFromModel(String messages) {
        iChangePassword.failedResponseFromPresenter(messages);
    }
}
