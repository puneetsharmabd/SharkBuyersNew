package com.sharkbuyers.ui.authentication.otpActivity;
import android.os.Handler;
import android.os.Message;
import com.sharkbuyers.services.APIInterface;
import com.sharkbuyers.services.RetrofitCalls;
import com.sharkbuyers.ui.authentication.otpActivity.interfaces.IMOTP;
import com.sharkbuyers.ui.authentication.otpActivity.interfaces.IPOTP;
import com.sharkbuyers.ui.authentication.otpActivity.responseModel.OtpResponse;

public class MOTP implements IMOTP {

    IPOTP ipotp;

    public MOTP(POTP potp) {
        this.ipotp=potp;
    }

    @Override
    public void otpRestCall(String otp, String email) {

        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.otp_Api(otp,email,mHandler);
    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.OTP_VERIFIY_SUCCESS:
                    OtpResponse otpResponse = ((OtpResponse) msg.obj);
                    ipotp.successResponseFromModel(otpResponse);
                    break;
                case APIInterface.OTP_VERIFIY_FAILD:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipotp.errorResponseFromModel(invalidRequest);
                    break;

            }
        }
    };
}
