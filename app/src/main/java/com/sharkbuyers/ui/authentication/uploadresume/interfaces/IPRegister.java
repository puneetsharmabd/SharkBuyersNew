package com.sharkbuyers.ui.authentication.uploadresume.interfaces;

import com.sharkbuyers.ui.authentication.uploadresume.responseModel.RegisterResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface IPRegister {

    void register(String first_name, String last_name, String email, String phone_number, String gender,
                  String address, String occupations, String password, String resume,String latitude,String longitude,String state,String county,String brand_image);
    void successResponseFromModel(RegisterResponseModel registerResponseModel);
    void errorResponseFromModel(String message);
}
