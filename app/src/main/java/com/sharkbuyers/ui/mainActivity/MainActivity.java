package com.sharkbuyers.ui.mainActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;

import com.sharkbuyers.R;
import com.sharkbuyers.baseClass.BaseClass;
import com.sharkbuyers.ui.authentication.ProfileActivity.ProfileActivity;
import com.sharkbuyers.ui.authentication.country.SelectCountryActivity;
import com.sharkbuyers.ui.authentication.state.StateActivity;
import com.sharkbuyers.ui.authentication.uploadresume.UploadResumeActivity;
import com.sharkbuyers.ui.buyers.buyersListingActitvity.BuyersListingActivity;
import com.sharkbuyers.ui.howitworks.HowItsWorksActivity;
import com.sharkbuyers.ui.mainActivity.interfaces.IAllBuyers;
import com.sharkbuyers.ui.mainActivity.interfaces.IPAllBuyers;
import com.sharkbuyers.ui.mainActivity.presenter.PAllBuyers;
import com.sharkbuyers.ui.settings.SettingsActivity;
import com.sharkbuyers.utils.AppController;
import com.sharkbuyers.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseClass {

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.tvSubmit)
    TextView tvSubmit;

    @BindView(R.id.searchView)
    SearchView searchView;

    boolean doubleBackToExitPressedOnce = false;

    @BindView(R.id.tvHowItsWork)
    TextView tvHowItsWork;

    @BindView(R.id.tvProfile)
    TextView tvProfile;

    @BindView(R.id.tvSettings)
    TextView tvSettings;

    Context context;

    @BindView(R.id.layoutHowItsWork)
    LinearLayout layoutHowItsWork;

    @BindView(R.id.layoutProfile)
    LinearLayout layoutProfile;
    @BindView(R.id.layoutSetting)
    LinearLayout layoutSetting;


    private static final int COUNTRY_REQUEST_CODE = 04;
    private static final int STATE_REQUEST_CODE = 05;

    public static TextView tvSelectCountry;
    public static TextView tvSelectState;
    String intentFrom;
    String country_id = "", state_id = "";
    String country_name ;
    String state_name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = MainActivity.this;

        tvSelectCountry = (TextView) findViewById(R.id.tvSelectCountry);
        tvSelectState = (TextView) findViewById(R.id.tvSelectState);

        tvHowItsWork.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvProfile.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvSettings.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));

        if (country_id!=null && country_name !=null){
            country_id=AppController.getInstance(context).getString(AppController.Key.SAVE_COUNTRY_ID);
            country_name=AppController.getInstance(context).getString(AppController.Key.SAVE_COUNTRY);
            tvSelectCountry.setText(country_name);
        }
        if (state_id!=null && country_name !=null){
            state_id=AppController.getInstance(context).getString(AppController.Key.SAVE_STATE_ID);
            state_name=AppController.getInstance(context).getString(AppController.Key.SAVE_STATE);
            tvSelectState.setText(country_name);

        }

        tvSelectCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentFrom = "main";
                Intent intent = new Intent(MainActivity.this, SelectCountryActivity.class);
                intent.putExtra("intentFrom", intentFrom);
                startActivityForResult(intent, COUNTRY_REQUEST_CODE);
            }
        });
        tvSelectState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentFrom = "main";
                if (country_id.equals("") && country_id == null) {
                    Toast.makeText(context, "Select country first ", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent_state = new Intent(MainActivity.this, StateActivity.class);
                    intent_state.putExtra("country_id", country_id);
                    intent_state.putExtra("intentFrom", intentFrom);
                    startActivityForResult(intent_state, STATE_REQUEST_CODE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.doubleBackToExitPressedOnce = false;
    }

    @OnClick({R.id.layoutHowItsWork, R.id.layoutProfile, R.id.layoutSetting, R.id.tvSubmit})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.layoutHowItsWork:
                intent = new Intent(MainActivity.this, HowItsWorksActivity.class);
                startActivity(intent);

                //Toast.makeText(context, "Coming soon!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.layoutProfile:
                intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;

            case R.id.layoutSetting:
                intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.tvSubmit:

                validationonHome();

                break;
        }
    }

    private void validationonHome() {

        if (tvSelectCountry.getText().toString().equalsIgnoreCase("Select Country")) {
            Toast.makeText(context, "Please select country", Toast.LENGTH_SHORT).show();
        } else if (tvSelectState.getText().toString().equalsIgnoreCase("Select State")) {
            Toast.makeText(context, "Please select state", Toast.LENGTH_SHORT).show();
        } else {

            Intent intent = new Intent(MainActivity.this, BuyersListingActivity.class);
            intent.putExtra("country_id", country_id);
            intent.putExtra("state_id", state_id);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COUNTRY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                country_name = data.getStringExtra("county_name");
                country_id = data.getStringExtra("country_id");
                AppController.getInstance(context).put(AppController.Key.SAVE_COUNTRY,country_name);
                AppController.getInstance(context).put(AppController.Key.SAVE_COUNTRY_ID,country_id);

                tvSelectCountry.setText(country_name);


            }

        } else if (requestCode == STATE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                state_name = data.getStringExtra("state_name");
                state_id = data.getStringExtra("state_id");
                AppController.getInstance(context).put(AppController.Key.SAVE_STATE,state_name);
                AppController.getInstance(context).put(AppController.Key.SAVE_STATE_ID,state_id);
                tvSelectState.setText(state_name);


            }
        }
    }
}
