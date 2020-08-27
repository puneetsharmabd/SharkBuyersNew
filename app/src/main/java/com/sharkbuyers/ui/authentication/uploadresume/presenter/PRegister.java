package com.sharkbuyers.ui.authentication.uploadresume.presenter;

import com.sharkbuyers.ui.authentication.uploadresume.UploadResumeActivity;
import com.sharkbuyers.ui.authentication.uploadresume.interfaces.IMRegister;
import com.sharkbuyers.ui.authentication.uploadresume.interfaces.IPRegister;
import com.sharkbuyers.ui.authentication.uploadresume.interfaces.IRegister;
import com.sharkbuyers.ui.authentication.uploadresume.responseModel.RegisterResponseModel;
import com.sharkbuyers.ui.authentication.uploadresume.viewModel.MRegister;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PRegister  implements IPRegister {

    IRegister iRegister;
    IMRegister imRegister;

    public PRegister(UploadResumeActivity uploadResumeActivity) {
            this.iRegister=uploadResumeActivity;
    }

    @Override
    public void register(String first_name, String last_name, String email, String phone_number,
                         String gender, String address, String occupations, String password,
                         String resume,String latitude,String longitude,String state,String county,String brand_image) {
        imRegister=new MRegister(this);
        imRegister.registerRestCall(first_name,last_name,email,phone_number,gender,address,occupations,password,resume,latitude,longitude,state,county, brand_image);
    }

    @Override
    public void successResponseFromModel(RegisterResponseModel registerResponseModel) {
        iRegister.successResponseFromPresenter(registerResponseModel);
    }

    @Override
    public void errorResponseFromModel(String message) {
        iRegister.errorResponseFromPresenter(message);
    }


}
