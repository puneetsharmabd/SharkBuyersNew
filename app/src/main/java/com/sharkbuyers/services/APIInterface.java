package com.sharkbuyers.services;

import com.sharkbuyers.ui.authentication.changePassword.ChangePasswordResponseModel;
import com.sharkbuyers.ui.authentication.editprofile.EditProfileResponseModel;
import com.sharkbuyers.ui.authentication.forgotpasswordActivity.ForgotPasswordResponseModel;
import com.sharkbuyers.ui.authentication.loginActivity.responseModel.LoginResponseModel;
import com.sharkbuyers.ui.authentication.otpActivity.responseModel.OtpResponse;
import com.sharkbuyers.ui.authentication.country.CountryResponseModel;
import com.sharkbuyers.ui.authentication.uploadresume.responseModel.RegisterResponseModel;
import com.sharkbuyers.ui.authentication.country.StatesResponseModel;
import com.sharkbuyers.ui.mainActivity.AllBuyersResponseModel;
import com.sharkbuyers.ui.others.OthersResponseModel;
import com.sharkbuyers.ui.resume.EditResumseResponseModel;
import com.sharkbuyers.ui.resume.ResumeResponseModel;
import com.sharkbuyers.ui.settings.LogoutResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIInterface {

    public static final int USER_REGISTER_SUCCESS = 1;
    public static final int USER_REGISTER_FAILED = 2;

    public static final int USER_LOGIN_SUCCESS = 3;
    public static final int USER_LOGIN_FAILD = 4;

    public static final int OTP_VERIFIY_SUCCESS = 5;
    public static final int OTP_VERIFIY_FAILD = 6;

    public static final int GET_COUNTRY_SUCCESS = 7;
    public static final int GET_COUNTRY_FAILED = 8;

    public static final int FORGOT_PASSWORD_SUCCESS = 9;
    public static final int FORGOT_PASSWORD_FAILED = 10;

    public static final int CHANGE_PASSWORD_SUCCESS = 11;
    public static final int CHANGE_PASSWORD_FAILED = 12;

    public static final int RESUME_SUCCESS = 13;
    public static final int RESUME_FAILED = 14;

    public static final int OTHER_SUCCESS = 15;
    public static final int OTHER_FAILED = 16;

    public static final int LOGOUT_SUCCESS = 17;
    public static final int LOGOUT_FAILED = 18;

    public static final int GET_STATE_SUCCESS = 19;
    public static final int GET_STATE_FAILED = 20;

    public static final int EDIT_RESUME_SUCCESS = 21;
    public static final int EDIT_RESUME_FAILED = 22;

    public static final int EDIT_PROFILE_SUCCESS = 23;
    public static final int EDIT_PROFILE_FAILED = 24;

    public static final int GET_ALL_BUYERS_SUCCESS = 25;
    public static final int GET_ALL_BUYERS_FAILED = 26;

    // Checked
    @Multipart
    @POST("signup")
    Call<RegisterResponseModel> register(
            @Part("first_name") RequestBody first_name,
            @Part("last_name") RequestBody last_name,
            @Part("email") RequestBody email,
            @Part("phone_num") RequestBody phone_num,
            @Part("gender") RequestBody gender,
            @Part("address") RequestBody address,
            @Part("occupation") RequestBody occupation,
            @Part("password") RequestBody password,
            @Part("resume") RequestBody resume,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("state") RequestBody state,
            @Part("country") RequestBody country,
            @Part("userType") RequestBody userType,
            @Part MultipartBody.Part brand_image);

    // Checked
    @Multipart
    @POST("signup_resume")
    Call<RegisterResponseModel> registerResume(
            @Part("first_name") RequestBody first_name,
            @Part("last_name") RequestBody last_name,
            @Part("email") RequestBody email,
            @Part("phone_num") RequestBody phone_num,
            @Part("gender") RequestBody gender,
            @Part("address") RequestBody address,
            @Part("occupation") RequestBody occupation,
            @Part("password") RequestBody password,
            @Part MultipartBody.Part resume,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("state") RequestBody state,
            @Part("country") RequestBody country,
            @Part("userType") RequestBody userType,
            @Part MultipartBody.Part brand_image);


    @Multipart
    @POST("edit_user")
    Call<EditProfileResponseModel> editProfile(
            @Header("Authorization") String access_token,
            @Part("first_name") RequestBody first_name,
            @Part("last_name") RequestBody last_name,
            @Part("email") RequestBody email,
            @Part("phone_num") RequestBody phone_num,
            @Part("gender") RequestBody gender,
            @Part("address") RequestBody address,
            @Part("occupation") RequestBody occupation,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("state") RequestBody state,
            @Part("country") RequestBody country,
            @Part("userType") RequestBody userType,
            @Part MultipartBody.Part brand_image);

    // Checked
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponseModel> user_Login(@Field("email") String email,
                                        @Field("password") String password,
                                        @Field("device_token") String device_token);

    // Checked
    @FormUrlEncoded
    @POST("verify_otp")
    Call<OtpResponse> verifiy_otp(@Field("otp") String otp,
                                  @Field("email") String email);

    // Checked
    @GET("get_countries")
    Call<CountryResponseModel> get_Country();

    // Checked
    @FormUrlEncoded
    @POST("get_states")
    Call<StatesResponseModel> get_States(@Field("country_id") String country_id);

    // Checked
    @FormUrlEncoded
    @POST("forgot_password")
    Call<ForgotPasswordResponseModel> forgotpassword(@Field("email") String email);

    // Checked
    @FormUrlEncoded
    @POST("change_password")
    Call<ChangePasswordResponseModel> changepassword(@Header("Authorization") String access_token,
                                                     @Field("old_password") String old_password
            , @Field("new_password") String new_password);

    // Checked
    @POST("user_resume")
    Call<ResumeResponseModel> resumse(@Header("Authorization") String access_token);

    // Checked
    @FormUrlEncoded
    @POST("edit_resume")
    Call<EditResumseResponseModel> editresumse(@Header("Authorization") String access_token,
                                               @Field("resume") String resume);

    // Checked
    @GET("logout")
    Call<LogoutResponseModel> logout(@Header("Authorization") String access_token);

    // Checked
    @GET("about_us")
    Call<OthersResponseModel> others();

    // Checked
    @FormUrlEncoded
    @POST("get_all_buyers")
    Call<AllBuyersResponseModel> get_all_buyers(@Header("Authorization") String access_token,
                                                @Field("country_id") String country_id,
                                                @Field("state_id") String state_id);


}
