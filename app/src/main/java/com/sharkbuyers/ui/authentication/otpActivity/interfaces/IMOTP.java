package com.sharkbuyers.ui.authentication.otpActivity.interfaces;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface IMOTP {
    void otpRestCall(String otp,String email);
}
