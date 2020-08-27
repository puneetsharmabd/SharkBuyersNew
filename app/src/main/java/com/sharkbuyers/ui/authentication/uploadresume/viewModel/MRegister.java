package com.sharkbuyers.ui.authentication.uploadresume.viewModel;

import android.os.Handler;
import android.os.Message;

import com.sharkbuyers.services.APIInterface;
import com.sharkbuyers.services.RetrofitCalls;
import com.sharkbuyers.ui.authentication.uploadresume.interfaces.IMRegister;
import com.sharkbuyers.ui.authentication.uploadresume.interfaces.IPRegister;
import com.sharkbuyers.ui.authentication.uploadresume.presenter.PRegister;
import com.sharkbuyers.ui.authentication.uploadresume.responseModel.RegisterResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MRegister implements IMRegister {
    IPRegister ipRegister;

    public MRegister(PRegister pRegister) {
        this.ipRegister = pRegister;
    }

    @Override
    public void registerRestCall(String first_name, String last_name, String email, String phone_number, String gender, String address, String occupations, String password, String resume, String latitude, String longitude, String state, String county,String brand_image) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.regestrationApi(first_name, last_name, email, phone_number, gender, address, occupations, password, resume, latitude, longitude, state, county,brand_image, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.USER_REGISTER_SUCCESS:
                    RegisterResponseModel registerResponseModel = ((RegisterResponseModel) msg.obj);
                    ipRegister.successResponseFromModel(registerResponseModel);
                    break;
                case APIInterface.USER_REGISTER_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipRegister.errorResponseFromModel(invalidRequest);
                    break;

            }
        }
    };
}
