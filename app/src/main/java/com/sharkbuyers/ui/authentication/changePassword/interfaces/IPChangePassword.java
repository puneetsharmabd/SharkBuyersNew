package com.sharkbuyers.ui.authentication.changePassword.interfaces;

import com.sharkbuyers.ui.authentication.changePassword.ChangePasswordResponseModel;

public interface IPChangePassword {
    void changepassword(String access_token,String old_password,String new_password);
    void successResponseFromModel(ChangePasswordResponseModel changePasswordResponseModel);
    void failedResponseFromModel(String messages);
}
