package com.sharkbuyers.ui.others;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sharkbuyers.R;
import com.sharkbuyers.baseClass.BaseClass;
import com.sharkbuyers.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherActivity extends BaseClass {

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.tvOthers)
    TextView tvOthers;

    private String intentFrom = "";
    Context context;
    String about_us = "", help_support = "", terms_condition = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        ButterKnife.bind(this);
        context = OtherActivity.this;
        intentFrom = getIntent().getStringExtra("intentFrom");
        about_us = getIntent().getStringExtra("about_us");
        help_support = getIntent().getStringExtra("help_support");
        terms_condition = getIntent().getStringExtra("terms_condition");
        if (intentFrom.equalsIgnoreCase("aboutus")) {

            tvTittle.setText("About us");
            //tvOthers.setText(about_us);
            tvOthers.setText("Our business is built on the idea that, in an ever-more-connected world, we’re not just responsible for what we do – we’re also accountable for what’s done on our behalf. And where there’s accountability, there’s a need for control…\n" +
                    "\n" +
                    "When we started out in the SharkBuyers industry, we saw that control was something buyers needed more of. So we designed our community model, our supporting services and the technology that powers them to address this need. We wanted to drive a new kind of procurement with less risk, less cost and less compliance issues. At the same time, wanted to bring suppliers into the fold – to help them meet buyers’ needs while reducing their own qualification burdens.\n" +
                    "\n" +
                    "Today, with all kinds of stakeholders holding businesses to ever-higher standards, it’s even more important for businesses to have a complete picture of their supply chains – not just in terms of their business performance, but their ethical and environmental standards too. Against this backdrop, our drive to develop and refine our offering has only increased.\n" +
                    "\n" +
                    "Now we’re working from offices all over the globe, serving a network of over 800 buyers and 175,000 suppliers in all kinds of industries. As such, we’re proud to be part of a truly worldwide community dedicated to raising standards and doing business in ways that benefit everyone.");
            tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));


        } else if (intentFrom.equalsIgnoreCase("helpsupport")) {
            tvTittle.setText("Help & Support");
            //tvOthers.setText(help_support);
            tvOthers.setText("Just send us your questions or concerns by starting a new case mlmbyrd31@gmail.com and we will give you the help you need.");
            tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));


        } else if (intentFrom.equalsIgnoreCase("termcondition")) {
            tvTittle.setText("Terms & Condition");
            //tvOthers.setText(terms_condition);
            tvOthers.setText("You will not violate any applicable law, contract, intellectual property or other third-party right or commit a tort, and you are solely responsible for your conduct while accessing or using our Services. You will not:\n" +
                    "\n" +
                    "*Engage in any harassing, threatening, intimidating, predatory or stalking conduct;\n" +
                    "*Use or attempt to use another user’s account without authorization from that user and SharkBuyers;\n" +
                    "*Use our Services in any manner that could interfere with, disrupt, negatively affect or inhibit other users from fully enjoying our Services or that could damage, disable, overburden or impair the functioning of our Services in any manner;\n" +
                    "*Reverse engineer any aspect of our Services or do anything that might discover source code or bypass or circumvent measures employed to prevent or limit access to any part of our Services;\n" +
                    "*Attempt to circumvent any content-filtering techniques we employ or attempt to access any feature or area of our Services that you are not authorized to access;\n" +
                    "*Develop or use any third-party applications that interact with our Services without our prior written consent, including any scripts designed to scrape or extract data from our Services;\n" +
                    "*Use our Services for any illegal or unauthorized purpose, or engage in, encourage or promote any activity that violates this Agreement.\n\n" +
                    "You may also only post or otherwise share User Content that is non-confidential and you have all necessary rights to disclose. You may not create, post, store or share any User Content that:\n" +
                    "\n\n" +
                    "*Is unlawful, libelous, defamatory, obscene, pornographic, indecent, lewd, suggestive, harassing, threatening, invasive of privacy or publicity rights, abusive, inflammatory or fraudulent;\n" +
                    "*Would constitute, encourage or provide instructions for a criminal offense, violate the rights of any party or otherwise create liability or violate any local, state, national or international law;\n" +
                    "*May infringe any patent, trademark, trade secret, copyright or other intellectual or proprietary right of any party;\n" +
                    "*Contains or depicts any statements, remarks or claims that do not reflect your honest views and experiences;\n" +
                    "*Impersonates, or misrepresents your affiliation with, any person or entity;\n" +
                    "*Contains any unsolicited promotions, political campaigning, advertising or solicitations;\n" +
                    "*Contains any private or personal information of a third party without such third party’s consent;\n" +
                    "*Contains any viruses, corrupted data or other harmful, disruptive or destructive files or content; or\n" +
                    "*Is, in our sole judgment, objectionable or that restricts or inhibits any other person from using or enjoying our Services, or that may expose SharkBuyers or others to any harm or liability of any type.\n\n" +
                    "In addition, although we have no obligation to screen, edit or monitor User Content, we may delete or remove User Content at any time and for any reason.");
            tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));

        } else {
            tvTittle.setText("Other");
            tvOthers.setText("Others");
            tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));

        }
    }

    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();
    }
}
