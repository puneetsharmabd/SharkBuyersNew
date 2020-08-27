package com.sharkbuyers.ui.authentication.uploadresume.interfaces;

import com.sharkbuyers.ui.authentication.uploadresume.responseModel.RegisterResponseModel;

public interface IRegister {
    void successResponseFromPresenter(RegisterResponseModel registerResponseModel);
    void errorResponseFromPresenter(String message);


}
