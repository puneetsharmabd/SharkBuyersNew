package com.sharkbuyers.ui.authentication.ProfileActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.sharkbuyers.R;
import com.sharkbuyers.baseClass.BaseClass;
import com.sharkbuyers.ui.authentication.editprofile.EditProfileActivity;
import com.sharkbuyers.ui.authentication.joinnowActivity.JoinNowActivity;
import com.sharkbuyers.ui.authentication.loginActivity.LoginActivity;
import com.sharkbuyers.utils.AppController;
import com.sharkbuyers.utils.CommonMethods;
import com.sharkbuyers.utils.Constant;
import com.sharkbuyers.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends BaseClass {

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.username)
    TextView username;

    @BindView(R.id.userphone)
    TextView userphone;

    @BindView(R.id.useremail)
    TextView useremail;

    @BindView(R.id.tvUserOccupations)
    TextView tvUserOccupations;

    @BindView(R.id.tvUserCountry)
    TextView tvUserCountry;

    @BindView(R.id.tvUserState)
    TextView tvUserState;

    @BindView(R.id.tvgender)
    TextView tvgender;

    @BindView(R.id.tvEditProfile)
    TextView tvEditProfile;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.imgeBrand)
    CircleImageView imgeBrand;

    private String intentFrom = "";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        context = ProfileActivity.this;
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        intentFrom = getIntent().getStringExtra("intentFrom");
        String firstname = AppController.getInstance(context).getString(AppController.Key.FIRSTNAME);
        String lastname = AppController.getInstance(context).getString(AppController.Key.LASTNAME);
        String userName = firstname + " " + lastname;
        username.setText(CommonMethods.upperCase(userName));
        userphone.setText(AppController.getInstance(context).getString(AppController.Key.PHONENUMBER));
        useremail.setText(AppController.getInstance(context).getString(AppController.Key.EMAIL));
        tvUserOccupations.setText(CommonMethods.upperCase(AppController.getInstance(context).getString(AppController.Key.OCCUPATION)));
        tvUserCountry.setText(CommonMethods.upperCase(AppController.getInstance(context).getString(AppController.Key.COUNTRY)));
        tvUserState.setText(CommonMethods.upperCase(AppController.getInstance(context).getString(AppController.Key.STATE)));
        tvgender.setText(CommonMethods.upperCase(AppController.getInstance(context).getString(AppController.Key.GENDER)));
        if (AppController.getInstance(context).getString(AppController.Key.BRANDIMAGE)!=null){
            Glide.with(context).load(Constant.IMAGE_URL + AppController.getInstance(context).getString(AppController.Key.BRANDIMAGE))
                    .placeholder(R.drawable.ic_e_dummy)
                    .into(imgeBrand);
        }else {
            Glide.with(context).load(Constant.IMAGE_URL + AppController.getInstance(context).getString(AppController.Key.BRANDIMAGE))
                    .placeholder(R.drawable.ic_e_dummy)
                    .into(imgeBrand);
        }

    }

    @OnClick({R.id.imgBack, R.id.tvEditProfile})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvEditProfile:
                Intent i = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(i);

                break;
        }
    }
}
