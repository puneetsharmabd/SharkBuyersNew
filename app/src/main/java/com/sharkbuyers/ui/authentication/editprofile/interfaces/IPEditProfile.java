package com.sharkbuyers.ui.authentication.editprofile.interfaces;

import com.sharkbuyers.ui.authentication.editprofile.EditProfileResponseModel;
import com.sharkbuyers.ui.authentication.uploadresume.responseModel.RegisterResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface IPEditProfile {

    void editProfile(String access_token,String first_name, String last_name, String email, String phone_number, String gender,
                  String address, String occupations, String latitude, String longitude, String state, String county, String  brand_image);
    void editProfilesuccessResponseFromModel(EditProfileResponseModel editProfileResponseModel);
    void editProfileerrorResponseFromModel(String message);
}
