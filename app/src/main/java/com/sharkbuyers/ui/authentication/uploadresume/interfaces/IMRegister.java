package com.sharkbuyers.ui.authentication.uploadresume.interfaces;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface IMRegister {
    void registerRestCall(String first_name, String last_name, String email, String phone_number, String gender,
                          String address, String occupations, String password, String resume,String latitude,String longitude,String state,String county,String brand_image);
}
