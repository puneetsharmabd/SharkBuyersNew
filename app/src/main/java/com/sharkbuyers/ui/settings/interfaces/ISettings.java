package com.sharkbuyers.ui.settings.interfaces;

import com.sharkbuyers.ui.others.OthersResponseModel;
import com.sharkbuyers.ui.settings.LogoutResponseModel;

public interface ISettings {
    void successResponseOtherFromPresenter(OthersResponseModel othersResponseModel);
    void failedResponseOtherFromPresenter(String message);

    void successResponseLogoutFromPresenter(LogoutResponseModel logoutResponseModel);
    void failedResponseLogoutFromPresenter(String message);
}
