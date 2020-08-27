package com.sharkbuyers.ui.settings;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.sharkbuyers.R;
import com.sharkbuyers.baseClass.BaseClass;
import com.sharkbuyers.networks.NetworkUtils;
import com.sharkbuyers.ui.authentication.ProfileActivity.ProfileActivity;
import com.sharkbuyers.ui.authentication.changePassword.ChangePasswordActivity;
import com.sharkbuyers.ui.authentication.loginActivity.LoginActivity;
import com.sharkbuyers.ui.mainActivity.MainActivity;
import com.sharkbuyers.ui.others.OtherActivity;
import com.sharkbuyers.ui.others.OthersResponseModel;
import com.sharkbuyers.ui.resume.ResumeActivity;
import com.sharkbuyers.ui.settings.interfaces.IPSettings;
import com.sharkbuyers.ui.settings.interfaces.ISettings;
import com.sharkbuyers.ui.settings.presenter.PSettings;
import com.sharkbuyers.utils.AppController;
import com.sharkbuyers.utils.UtilsDialog;
import com.sharkbuyers.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends BaseClass implements ISettings {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvChnagePassword)
    TextView tvChnagePassword;
    @BindView(R.id.tvProfile)
    TextView tvProfile;
    @BindView(R.id.tvResume)
    TextView tvResume;
    @BindView(R.id.tvAboutus)
    TextView tvAboutus;
    @BindView(R.id.tvHelpSupport)
    TextView tvHelpSupport;
    @BindView(R.id.tvTermsCondition)
    TextView tvTermsCondition;
    @BindView(R.id.tvLogout)
    TextView tvLogout;

    @BindView(R.id.tvAccount)
    TextView tvAccount;

    @BindView(R.id.tvOthers)
    TextView tvOthers;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    String intentFrom = "";
    Context context;

    IPSettings ipSettings;

    String about_us="",help_support ="",terms_condition="";
    Dialog progressDialog;
    String access_token="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        context = SettingsActivity.this;
        ipSettings=new PSettings(this);
        access_token= AppController.getInstance(context).getString(AppController.Key.SAVE_ACCESS_TOKEN);
        tvAccount.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvOthers.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));

        if (NetworkUtils.isNetworkConnectionAvailable(context)){
            progressDialog = UtilsDialog.ShowDialog(this);
            ipSettings.other();
        }

    }

    @OnClick({R.id.imgBack, R.id.tvChnagePassword, R.id.tvProfile, R.id.tvResume, R.id.tvAboutus, R.id.tvHelpSupport, R.id.tvTermsCondition, R.id.tvLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvChnagePassword:
                Intent intent_changepassword = new Intent(SettingsActivity.this, ChangePasswordActivity.class);
                startActivity(intent_changepassword);
                break;
            case R.id.tvProfile:
                Intent intent_profile = new Intent(SettingsActivity.this, ProfileActivity.class);
                startActivity(intent_profile);
                break;
            case R.id.tvResume:
                intentFrom = "settings";
                Intent settings = new Intent(SettingsActivity.this, ResumeActivity.class);
                settings.putExtra("intentFrom", intentFrom);
                startActivity(settings);
                break;
            case R.id.tvAboutus:
                intentFrom = "aboutus";
                setIntent(intentFrom);
                break;
            case R.id.tvHelpSupport:
                intentFrom = "helpsupport";
                setIntent(intentFrom);
                break;
            case R.id.tvTermsCondition:
                intentFrom = "termcondition";
                setIntent(intentFrom);
                break;
            case R.id.tvLogout:
                logoutAccount();
                break;
        }
    }

    private void setIntent(String intentFrom) {
        Intent intent = new Intent(SettingsActivity.this, OtherActivity.class);
        intent.putExtra("intentFrom", intentFrom);
        intent.putExtra("about_us",about_us);
        intent.putExtra("help_support",help_support);
        intent.putExtra("terms_condition",terms_condition);
        startActivity(intent);
    }

    private void logoutAccount() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogeTheme);
        builder.setCancelable(true);
        builder.setMessage("Are you sure you want to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (NetworkUtils.isNetworkConnectionAvailable(context)){
                            progressDialog = UtilsDialog.ShowDialog(context);
                            ipSettings.logout(access_token);
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void successResponseOtherFromPresenter(OthersResponseModel othersResponseModel) {
        progressDialog.dismiss();
        if (othersResponseModel!=null||othersResponseModel.getData().size()>0){
            about_us=othersResponseModel.getData().get(0).getAboutUs();
            help_support=othersResponseModel.getData().get(0).getHelpSupport();
            terms_condition=othersResponseModel.getData().get(0).getHelpSupport();
        }
    }

    @Override
    public void failedResponseOtherFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successResponseLogoutFromPresenter(LogoutResponseModel logoutResponseModel) {
        progressDialog.dismiss();
        AppController.getInstance(context).clear();
        Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // if the activity running has it's own context
    }

    @Override
    public void failedResponseLogoutFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }
}
