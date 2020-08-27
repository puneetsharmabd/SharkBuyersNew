package com.sharkbuyers.ui.authentication.editprofile.presenter;

import com.sharkbuyers.ui.authentication.editprofile.EditProfileActivity;
import com.sharkbuyers.ui.authentication.editprofile.EditProfileResponseModel;
import com.sharkbuyers.ui.authentication.editprofile.interfaces.IEditProfile;
import com.sharkbuyers.ui.authentication.editprofile.interfaces.IMEditProfile;
import com.sharkbuyers.ui.authentication.editprofile.interfaces.IPEditProfile;
import com.sharkbuyers.ui.authentication.editprofile.model.MEditProfile;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PEditProfile implements IPEditProfile {

    IEditProfile iEditProfile;
    IMEditProfile imEditProfile;

    public PEditProfile(EditProfileActivity editProfileActivity) {
        this.iEditProfile=editProfileActivity;
    }

    @Override
    public void editProfile(String access_token,String first_name, String last_name, String email, String phone_number, String gender, String address, String occupations,  String latitude, String longitude, String state, String county, String brand_image) {
        imEditProfile=new MEditProfile(this);
        imEditProfile.editProfileRestCall(access_token,first_name,last_name,email,phone_number,gender,address,occupations,latitude,longitude,state,county,brand_image);
    }

    @Override
    public void editProfilesuccessResponseFromModel(EditProfileResponseModel editProfileResponseModel) {
        iEditProfile.editProfilesuccessResponseFromPresenter(editProfileResponseModel);
    }

    @Override
    public void editProfileerrorResponseFromModel(String message) {
        iEditProfile.editProfileerrorResponseFromPresenter(message);
    }
}
