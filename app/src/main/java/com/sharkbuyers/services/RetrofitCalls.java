package com.sharkbuyers.services;

import android.os.Handler;
import android.os.Message;

import com.sharkbuyers.ui.authentication.changePassword.ChangePasswordResponseModel;
import com.sharkbuyers.ui.authentication.country.StatesResponseModel;
import com.sharkbuyers.ui.authentication.editprofile.EditProfileResponseModel;
import com.sharkbuyers.ui.authentication.forgotpasswordActivity.ForgotPasswordResponseModel;
import com.sharkbuyers.ui.authentication.loginActivity.responseModel.LoginResponseModel;
import com.sharkbuyers.ui.authentication.otpActivity.responseModel.OtpResponse;
import com.sharkbuyers.ui.authentication.country.CountryResponseModel;
import com.sharkbuyers.ui.authentication.uploadresume.UploadResumeActivity;
import com.sharkbuyers.ui.authentication.uploadresume.responseModel.RegisterResponseModel;
import com.sharkbuyers.ui.mainActivity.AllBuyersResponseModel;
import com.sharkbuyers.ui.others.OthersResponseModel;
import com.sharkbuyers.ui.resume.EditResumseResponseModel;
import com.sharkbuyers.ui.resume.ResumeResponseModel;
import com.sharkbuyers.ui.settings.LogoutResponseModel;
import com.sharkbuyers.utils.Constant;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCalls {

    public APIInterface apiInterface;

    public RetrofitCalls() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }


    public void regestrationApi(String first_name, String last_name, String email, String phone_number, String gender,
                                String address, String occupations, String password, String resume, String latitude, String longitude, String state, String county, String brand_image, final Handler mHandler) {

        File imageFile = null;
        MultipartBody.Part imageFileBody = null;
        MultipartBody.Part imageFileBody1 = null;

        if (brand_image != null && !brand_image.equals("")) {
            imageFile = new File(brand_image);
        }

        if (imageFile != null && imageFile.exists()) {
            MediaType mediaType = null;
            if (brand_image.endsWith("png")) {
                mediaType = MediaType.parse("image/png");
            } else {
                mediaType = MediaType.parse("image/jpeg");
            }
            RequestBody requestBody = RequestBody.create(mediaType, imageFile);
            RequestBody requestBody1 = RequestBody.create(MediaType.parse("application/doc"), UploadResumeActivity.file);
            imageFileBody = MultipartBody.Part.createFormData("brand_image", imageFile.getName(), requestBody);
            imageFileBody1 = MultipartBody.Part.createFormData("resume", UploadResumeActivity.file.getName(), requestBody1);
        }
        RequestBody userTypeBody = RequestBody.create(MediaType.parse("text/plain"), brand_image );
        RequestBody user_first_name = RequestBody.create(MediaType.parse("text/plain"), first_name);
        RequestBody user_last_name = RequestBody.create(MediaType.parse("text/plain"), last_name);
        RequestBody user_email = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody user_phone_number = RequestBody.create(MediaType.parse("text/plain"), phone_number);
        RequestBody user_gender = RequestBody.create(MediaType.parse("text/plain"), gender);
        RequestBody user_adddress = RequestBody.create(MediaType.parse("text/plain"), address);
        RequestBody user_occupations = RequestBody.create(MediaType.parse("text/plain"), occupations);
        RequestBody user_password = RequestBody.create(MediaType.parse("text/plain"), password);
        //RequestBody user_resume = RequestBody.create(MediaType.parse("text/plain"), resume);
        RequestBody user_latitude = RequestBody.create(MediaType.parse("text/plain"), latitude);
        RequestBody user_longitude = RequestBody.create(MediaType.parse("text/plain"), longitude);
        RequestBody user_state = RequestBody.create(MediaType.parse("text/plain"), state);
        RequestBody user_country = RequestBody.create(MediaType.parse("text/plain"), county);
        final Message message = new Message();
//        Call<RegisterResponseModel> call = apiInterface.register(user_first_name, user_last_name, user_email, user_phone_number, user_gender, user_adddress
//                , user_occupations, user_password, user_resume, user_latitude, user_longitude, user_state, user_country, userTypeBody, imageFileBody);
        Call<RegisterResponseModel> call = apiInterface.registerResume(user_first_name, user_last_name, user_email, user_phone_number, user_gender, user_adddress
                , user_occupations, user_password, imageFileBody1, user_latitude, user_longitude, user_state, user_country, userTypeBody, imageFileBody);
        call.enqueue(new Callback<RegisterResponseModel>() {
            @Override
            public void onResponse(Call<RegisterResponseModel> call,
                                   Response<RegisterResponseModel> response) {

                if (response.body() != null) {

                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.USER_REGISTER_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.USER_REGISTER_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }

            }

            @Override
            public void onFailure(Call<RegisterResponseModel> call, Throwable t) {
                message.what = apiInterface.USER_REGISTER_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }


    public void editProfile_Api(String access_token, String first_name, String last_name, String email, String phone_number, String gender,
                                String address, String occupations, String latitude, String longitude, String state, String county, String  brand_image, final Handler mHandler) {
        File imageFile = null;
        MultipartBody.Part imageFileBody = null;

        if (brand_image != null && !brand_image.equals("")) {
            imageFile = new File(brand_image);
        }

        if (imageFile != null && imageFile.exists()) {
            MediaType mediaType = null;
            if (brand_image.endsWith("png")) {
                mediaType = MediaType.parse("image/png");
            } else {
                mediaType = MediaType.parse("image/jpeg");
            }
            RequestBody requestBody = RequestBody.create(mediaType, imageFile);
            imageFileBody = MultipartBody.Part.createFormData("brand_image", imageFile.getName(), requestBody);
        }
        RequestBody userTypeBody = RequestBody.create(MediaType.parse("text/plain"), brand_image );
        RequestBody user_first_name = RequestBody.create(MediaType.parse("text/plain"), first_name);
        RequestBody user_last_name = RequestBody.create(MediaType.parse("text/plain"), last_name);
        RequestBody user_email = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody user_phone_number = RequestBody.create(MediaType.parse("text/plain"), phone_number);
        RequestBody user_gender = RequestBody.create(MediaType.parse("text/plain"), gender);
        RequestBody user_adddress = RequestBody.create(MediaType.parse("text/plain"), address);
        RequestBody user_occupations = RequestBody.create(MediaType.parse("text/plain"), occupations);
        RequestBody user_latitude = RequestBody.create(MediaType.parse("text/plain"), latitude);
        RequestBody user_longitude = RequestBody.create(MediaType.parse("text/plain"), longitude);
        RequestBody user_state = RequestBody.create(MediaType.parse("text/plain"), state);
        RequestBody user_country = RequestBody.create(MediaType.parse("text/plain"), county);
        final Message message = new Message();
        Call<EditProfileResponseModel> call = apiInterface.editProfile(Constant.BEARER + access_token, user_first_name, user_last_name, user_email,
                user_phone_number, user_gender, user_adddress
                , user_occupations, user_latitude, user_longitude, user_state, user_country, userTypeBody, imageFileBody);
        call.enqueue(new Callback<EditProfileResponseModel>() {
            @Override
            public void onResponse(Call<EditProfileResponseModel> call,
                                   Response<EditProfileResponseModel> response) {

                if (response.body() != null) {

                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.EDIT_PROFILE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.EDIT_PROFILE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<EditProfileResponseModel> call, Throwable t) {
                message.what = apiInterface.EDIT_PROFILE_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    public void login_Api(String email, String password, String device_token, final Handler mHandler) {

        final Message message = new Message();
        Call<LoginResponseModel> call = apiInterface.user_Login(email, password, device_token);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call,
                                   Response<LoginResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.USER_LOGIN_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.USER_LOGIN_FAILD;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                message.what = apiInterface.USER_LOGIN_FAILD;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    public void otp_Api(String otp, String email, final Handler mHandler) {

        final Message message = new Message();
        Call<OtpResponse> call = apiInterface.verifiy_otp(otp, email);
        call.enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call,
                                   Response<OtpResponse> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.OTP_VERIFIY_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.OTP_VERIFIY_FAILD;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {
                message.what = apiInterface.OTP_VERIFIY_FAILD;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    public void forgotpassword_Api(String email, final Handler mHandler) {

        final Message message = new Message();
        Call<ForgotPasswordResponseModel> call = apiInterface.forgotpassword(email);
        call.enqueue(new Callback<ForgotPasswordResponseModel>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponseModel> call,
                                   Response<ForgotPasswordResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.FORGOT_PASSWORD_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.FORGOT_PASSWORD_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponseModel> call, Throwable t) {
                message.what = apiInterface.FORGOT_PASSWORD_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    public void changepassword_Api(String accesss_token, String old_password, String new_password, final Handler mHandler) {

        final Message message = new Message();
        Call<ChangePasswordResponseModel> call = apiInterface.changepassword(Constant.BEARER + accesss_token, old_password, new_password);
        call.enqueue(new Callback<ChangePasswordResponseModel>() {
            @Override
            public void onResponse(Call<ChangePasswordResponseModel> call,
                                   Response<ChangePasswordResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.CHANGE_PASSWORD_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.CHANGE_PASSWORD_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponseModel> call, Throwable t) {
                message.what = apiInterface.CHANGE_PASSWORD_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    public void resmue_Api(String accesss_token, final Handler mHandler) {

        final Message message = new Message();
        Call<ResumeResponseModel> call = apiInterface.resumse(Constant.BEARER + accesss_token);
        call.enqueue(new Callback<ResumeResponseModel>() {
            @Override
            public void onResponse(Call<ResumeResponseModel> call,
                                   Response<ResumeResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.RESUME_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.RESUME_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResumeResponseModel> call, Throwable t) {
                message.what = apiInterface.RESUME_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    public void editresmue_Api(String accesss_token, String resume, final Handler mHandler) {

        final Message message = new Message();
        Call<EditResumseResponseModel> call = apiInterface.editresumse(Constant.BEARER + accesss_token, resume);
        call.enqueue(new Callback<EditResumseResponseModel>() {
            @Override
            public void onResponse(Call<EditResumseResponseModel> call,
                                   Response<EditResumseResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.EDIT_RESUME_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.EDIT_RESUME_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<EditResumseResponseModel> call, Throwable t) {
                message.what = apiInterface.EDIT_RESUME_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    public void logout_Api(String accesss_token, final Handler mHandler) {

        final Message message = new Message();
        Call<LogoutResponseModel> call = apiInterface.logout(Constant.BEARER + accesss_token);
        call.enqueue(new Callback<LogoutResponseModel>() {
            @Override
            public void onResponse(Call<LogoutResponseModel> call,
                                   Response<LogoutResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.LOGOUT_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.LOGOUT_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<LogoutResponseModel> call, Throwable t) {
                message.what = apiInterface.LOGOUT_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    public void country_Api(final Handler mHandler) {

        final Message message = new Message();
        Call<CountryResponseModel> call = apiInterface.get_Country();
        call.enqueue(new Callback<CountryResponseModel>() {
            @Override
            public void onResponse(Call<CountryResponseModel> call,
                                   Response<CountryResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.GET_COUNTRY_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GET_COUNTRY_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<CountryResponseModel> call, Throwable t) {
                message.what = apiInterface.GET_COUNTRY_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    public void state_Api(String country_id, final Handler mHandler) {

        final Message message = new Message();
        Call<StatesResponseModel> call = apiInterface.get_States(country_id);
        call.enqueue(new Callback<StatesResponseModel>() {
            @Override
            public void onResponse(Call<StatesResponseModel> call,
                                   Response<StatesResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.GET_STATE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GET_STATE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<StatesResponseModel> call, Throwable t) {
                message.what = apiInterface.GET_STATE_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    public void other_Api(final Handler mHandler) {

        final Message message = new Message();
        Call<OthersResponseModel> call = apiInterface.others();
        call.enqueue(new Callback<OthersResponseModel>() {
            @Override
            public void onResponse(Call<OthersResponseModel> call,
                                   Response<OthersResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.OTHER_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.OTHER_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<OthersResponseModel> call, Throwable t) {
                message.what = apiInterface.OTHER_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });

    }

    public void get_all_buyers_Api(String access_token, String country_id, String state_id, final Handler mHandler) {

        final Message message = new Message();
        Call<AllBuyersResponseModel> call = apiInterface.get_all_buyers(Constant.BEARER + access_token, country_id, state_id);
        call.enqueue(new Callback<AllBuyersResponseModel>() {
            @Override
            public void onResponse(Call<AllBuyersResponseModel> call,
                                   Response<AllBuyersResponseModel> response) {

                if (response.body() != null) {

                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.GET_ALL_BUYERS_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GET_ALL_BUYERS_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<AllBuyersResponseModel> call, Throwable t) {
                message.what = apiInterface.GET_ALL_BUYERS_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }


}
