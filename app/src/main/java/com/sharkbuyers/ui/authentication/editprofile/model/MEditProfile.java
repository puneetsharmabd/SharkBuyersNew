package com.sharkbuyers.ui.authentication.editprofile.model;

import android.os.Handler;
import android.os.Message;

import com.sharkbuyers.services.APIInterface;
import com.sharkbuyers.services.RetrofitCalls;
import com.sharkbuyers.ui.authentication.editprofile.EditProfileResponseModel;
import com.sharkbuyers.ui.authentication.editprofile.interfaces.IMEditProfile;
import com.sharkbuyers.ui.authentication.editprofile.interfaces.IPEditProfile;
import com.sharkbuyers.ui.authentication.editprofile.presenter.PEditProfile;
import com.sharkbuyers.ui.authentication.loginActivity.responseModel.LoginResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MEditProfile implements IMEditProfile {
    IPEditProfile ipEditProfile;
    public MEditProfile(PEditProfile pEditProfile) {
        this.ipEditProfile=pEditProfile;
    }

    @Override
    public void editProfileRestCall(String access_token,String first_name, String last_name, String email, String phone_number, String gender, String address, String occupations, String latitude, String longitude, String state, String county, String  brand_image) {
        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.editProfile_Api(access_token,first_name,last_name,email,phone_number,gender,address,occupations,latitude,longitude,state,county,brand_image,mHandler);
    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.EDIT_PROFILE_SUCCESS:
                    EditProfileResponseModel editProfileResponseModel = ((EditProfileResponseModel) msg.obj);
                    ipEditProfile.editProfilesuccessResponseFromModel(editProfileResponseModel);
                    break;
                case APIInterface.EDIT_PROFILE_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipEditProfile.editProfileerrorResponseFromModel(invalidRequest);
                    break;

            }
        }
    };
}
