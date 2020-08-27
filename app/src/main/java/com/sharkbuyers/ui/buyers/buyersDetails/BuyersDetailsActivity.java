package com.sharkbuyers.ui.buyers.buyersDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.sharkbuyers.R;
import com.sharkbuyers.baseClass.BaseClass;
import com.sharkbuyers.ui.authentication.otpActivity.OTPActivity;
import com.sharkbuyers.ui.buyers.buyersListingActitvity.BuyersListingActivity;
import com.sharkbuyers.ui.mainActivity.AllBuyersResponseModel;
import com.sharkbuyers.ui.resume.ResumeActivity;
import com.sharkbuyers.utils.AppController;
import com.sharkbuyers.utils.CommonMethods;
import com.sharkbuyers.utils.Constant;
import com.sharkbuyers.utils.UtilsFontFamily;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class BuyersDetailsActivity extends BaseClass {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.imgBuyerImage)
    ImageView imgBuyerImage;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvGender)
    TextView tvGender;
    @BindView(R.id.tvoccupation)
    TextView tvoccupation;
    @BindView(R.id.tvphonenumber)
    TextView tvphonenumber;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvAddress)
    TextView tvAddress;

    @BindView(R.id.tvViewResume)
    TextView tvViewResume;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.gender)
    TextView gender;

     @BindView(R.id.state)
    TextView state;

    @BindView(R.id.occupation)
    TextView occupation;

    @BindView(R.id.phonenumber)
    TextView phonenumber;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.address)
    TextView address;

    @BindView(R.id.tvState)
    TextView tvState;

    String intentFrom = "";
    Context context;

    int position;
    List<AllBuyersResponseModel.Datum> data;

    String resume = "";

    CircleImageView brandImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyers_details);
        brandImage = findViewById(R.id.brandImage);
        ButterKnife.bind(this);
        context = BuyersDetailsActivity.this;

        position = Integer.parseInt(getIntent().getStringExtra("position"));
        data = getIntent().getParcelableArrayListExtra("data");

        Log.d("+++++++",data.get(position).getCountryImage());
        Log.d("+++++++",data.get(position).getFirstName());

        name.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        gender.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        occupation.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        phonenumber.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        email.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        address.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvViewResume.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        state.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

        tvName.setText(CommonMethods.upperCase(data.get(position).getFirstName()) + " " + data.get(position).getLastName());
        tvGender.setText(CommonMethods.upperCase(data.get(position).getGender()));
        tvoccupation.setText(CommonMethods.upperCase(data.get(position).getOccupation()));
        tvphonenumber.setText(data.get(position).getPhoneNum());
        tvEmail.setText(data.get(position).getEmail());
        tvAddress.setText(CommonMethods.upperCase(data.get(position).getCountry().getName()));
        tvState.setText(CommonMethods.upperCase(data.get(position).getState().getName()));
        resume = data.get(position).getResume();

        Log.d("+++++++",Constant.IMAGE_URL + data.get(position).getCountryImage());
        Log.d("+++++++",Constant.IMAGE_URL + data.get(position).getBrandImage());

        if (data.get(position).getCountryImage() != null) {
            Glide.with(context).load(Constant.CIMAGE_URL + data.get(position).getCountryImage())
                    .placeholder(R.drawable.ic_e_dummy)
                    .dontAnimate()
                    .into(imgBuyerImage);
        } else {
            Glide.with(context).load(Constant.CIMAGE_URL + data.get(position).getCountryImage())
                    .placeholder(R.drawable.ic_e_dummy)
                    .dontAnimate()
                    .into(imgBuyerImage);
        }

        if (data.get(position).getBrandImage() != null) {
            Glide.with(context).load(Constant.IMAGE_URL + data.get(position).getBrandImage())
                    .placeholder(R.drawable.ic_e_dummy)
                    .dontAnimate()
                    .into(brandImage);
        } else {
            Glide.with(context).load(Constant.IMAGE_URL + data.get(position).getBrandImage())
                    .placeholder(R.drawable.ic_e_dummy)
                    .dontAnimate()
                    .into(brandImage);
        }

    }

    @OnClick({R.id.imgBack, R.id.tvViewResume})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvViewResume:
                intentFrom = "buyersdetails";
                Intent intent = new Intent(BuyersDetailsActivity.this, ResumeActivity.class);
                intent.putExtra("resume", resume);
                intent.putExtra("intentFrom", intentFrom);
                startActivity(intent);
                break;
        }
    }
}
