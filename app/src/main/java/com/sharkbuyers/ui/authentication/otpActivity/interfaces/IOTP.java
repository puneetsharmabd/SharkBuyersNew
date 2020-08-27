package com.sharkbuyers.ui.authentication.otpActivity.interfaces;
import com.sharkbuyers.ui.authentication.otpActivity.responseModel.OtpResponse;

public interface IOTP {

    void successResponseFromPresenter(OtpResponse otpResponse);
    void errorResponseFromPresenter(String message);
}
