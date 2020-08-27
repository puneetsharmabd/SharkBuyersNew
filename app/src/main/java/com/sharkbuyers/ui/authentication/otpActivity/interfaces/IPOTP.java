package com.sharkbuyers.ui.authentication.otpActivity.interfaces;

import com.sharkbuyers.ui.authentication.otpActivity.responseModel.OtpResponse;

public interface IPOTP {

    void setOtp(String otp, String email);
    void successResponseFromModel(OtpResponse otpResponse);
    void errorResponseFromModel(String message);
}
