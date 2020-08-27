package com.sharkbuyers.ui.howitworks;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.sharkbuyers.R;
import com.sharkbuyers.ui.buyers.buyersDetails.BuyersDetailsActivity;
import com.sharkbuyers.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HowItsWorksActivity extends AppCompatActivity {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvHowItsWork)
    TextView tvHowItsWork;
    @BindView(R.id.tvUnderstanding)
    TextView tvUnderstanding;
    @BindView(R.id.tvQ1)
    TextView tvQ1;
    @BindView(R.id.tvQ2)
    TextView tvQ2;
    @BindView(R.id.tvQ3)
    TextView tvQ3;
    @BindView(R.id.tvQ4)
    TextView tvQ4;
    @BindView(R.id.tvTittle)
    TextView tvTittle;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_its_works);
        ButterKnife.bind(this);
        context = HowItsWorksActivity.this;

        tvHowItsWork.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvUnderstanding.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));

        VideoView view = (VideoView)findViewById(R.id.videoView);
        //String path = "android.resource://" + getPackageName() + "/" + R.raw.sharkbuyerhowitworks;
        String path = "android.resource://" + getPackageName() + "/" + R.raw.shark;
        view.setVideoURI(Uri.parse(path));
        view.start();
    }

    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();
    }
}
