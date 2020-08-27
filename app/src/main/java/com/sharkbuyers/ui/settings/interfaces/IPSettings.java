package com.sharkbuyers.ui.settings.interfaces;

import com.sharkbuyers.ui.others.OthersResponseModel;
import com.sharkbuyers.ui.settings.LogoutResponseModel;

public interface IPSettings {
    void other();
    void successResponseOtherFromModel(OthersResponseModel othersResponseModel);
    void failedResponseOtherFromModel(String message);

    void logout(String access_token);
    void successResponseLogoutFromModel(LogoutResponseModel logoutResponseModel);
    void failedResponseLogoutFromModel(String message);

}
