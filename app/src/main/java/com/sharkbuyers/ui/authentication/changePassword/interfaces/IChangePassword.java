package com.sharkbuyers.ui.authentication.changePassword.interfaces;

import com.sharkbuyers.ui.authentication.changePassword.ChangePasswordResponseModel;

public interface IChangePassword {
    void successResponseFromPresenter(ChangePasswordResponseModel changePasswordResponseModel);
    void failedResponseFromPresenter(String messages);
}
