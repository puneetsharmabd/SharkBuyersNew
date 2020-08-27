package com.sharkbuyers.ui.authentication.editprofile.interfaces;

import com.sharkbuyers.ui.authentication.editprofile.EditProfileResponseModel;


public interface IEditProfile {
    void editProfilesuccessResponseFromPresenter(EditProfileResponseModel editProfileResponseModel);
    void editProfileerrorResponseFromPresenter(String message);


}
