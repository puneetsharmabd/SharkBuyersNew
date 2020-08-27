package com.sharkbuyers.ui.authentication.changePassword.interfaces;

import com.sharkbuyers.ui.authentication.changePassword.ChangePasswordResponseModel;

public interface IMChangePassword {
    void changepasswordRestCall(String access_token,String old_password,String new_password);

}
