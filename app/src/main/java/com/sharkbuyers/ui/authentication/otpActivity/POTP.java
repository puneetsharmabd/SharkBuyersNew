package com.sharkbuyers.ui.authentication.otpActivity;

import com.sharkbuyers.ui.authentication.otpActivity.interfaces.IMOTP;
import com.sharkbuyers.ui.authentication.otpActivity.interfaces.IOTP;
import com.sharkbuyers.ui.authentication.otpActivity.interfaces.IPOTP;
import com.sharkbuyers.ui.authentication.otpActivity.responseModel.OtpResponse;

public class POTP implements IPOTP {

    IOTP iotp;
    IMOTP imotp;

    public POTP(OTPActivity otpActivity) {
        this.iotp=otpActivity;
    }

    @Override
    public void setOtp(String otp, String email) {
        imotp=new MOTP(this);
        imotp.otpRestCall(otp,email);
    }

    @Override
    public void successResponseFromModel(OtpResponse otpResponse) {
        iotp.successResponseFromPresenter(otpResponse);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iotp.errorResponseFromPresenter(message);
    }
}
