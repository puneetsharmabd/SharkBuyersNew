package com.sharkbuyers.ui.settings.presenter;

import com.sharkbuyers.ui.others.OthersResponseModel;
import com.sharkbuyers.ui.settings.LogoutResponseModel;
import com.sharkbuyers.ui.settings.SettingsActivity;
import com.sharkbuyers.ui.settings.interfaces.IMSettings;
import com.sharkbuyers.ui.settings.interfaces.IPSettings;
import com.sharkbuyers.ui.settings.interfaces.ISettings;
import com.sharkbuyers.ui.settings.model.MSettings;

public class PSettings implements IPSettings {
    ISettings iSettings;
    IMSettings imSettings;
    public PSettings(SettingsActivity settingsActivity) {
        this.iSettings=settingsActivity;
    }

    @Override
    public void other() {
        imSettings=new MSettings(this);
        imSettings.otherRestCall();
    }

    @Override
    public void successResponseOtherFromModel(OthersResponseModel othersResponseModel) {
        iSettings.successResponseOtherFromPresenter(othersResponseModel);
    }

    @Override
    public void failedResponseOtherFromModel(String message) {
        iSettings.failedResponseOtherFromPresenter(message);
    }

    @Override
    public void logout(String access_token) {
        imSettings=new MSettings(this);
        imSettings.logoutRestCall(access_token);
    }

    @Override
    public void successResponseLogoutFromModel(LogoutResponseModel logoutResponseModel) {
        iSettings.successResponseLogoutFromPresenter(logoutResponseModel);
    }

    @Override
    public void failedResponseLogoutFromModel(String message) {
        iSettings.failedResponseLogoutFromPresenter(message);
    }
}
