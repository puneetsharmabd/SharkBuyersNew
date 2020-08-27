package com.sharkbuyers.ui.authentication.loginActivity.presenter;


import com.sharkbuyers.ui.authentication.loginActivity.LoginActivity;
import com.sharkbuyers.ui.authentication.loginActivity.interfaces.ILogin;
import com.sharkbuyers.ui.authentication.loginActivity.interfaces.IMLogin;
import com.sharkbuyers.ui.authentication.loginActivity.interfaces.IPLogin;
import com.sharkbuyers.ui.authentication.loginActivity.responseModel.LoginResponseModel;
import com.sharkbuyers.ui.authentication.loginActivity.viewModel.MLogin;

public class PLogin  implements IPLogin {

    ILogin iLogin;
    IMLogin imLogin;


    public PLogin(LoginActivity loginActivity) {
        this.iLogin = loginActivity;
    }

    @Override
    public void login(String email, String password, String device_token) {
        imLogin=new MLogin(this);
        imLogin.loginRestCalls(email,password,device_token);
    }

    @Override
    public void loginSuccessFromModel(LoginResponseModel loginResponseModel) {
        iLogin.loginSuccessFromPresenter(loginResponseModel);
    }

    @Override
    public void loginFaileddFromModel(String message) {
        iLogin.loginFailedFromPresenter(message);

    }
}
