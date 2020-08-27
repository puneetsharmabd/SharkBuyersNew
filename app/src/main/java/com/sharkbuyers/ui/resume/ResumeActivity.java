package com.sharkbuyers.ui.resume;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sharkbuyers.R;
import com.sharkbuyers.baseClass.BaseClass;
import com.sharkbuyers.networks.NetworkUtils;
import com.sharkbuyers.ui.resume.interfacres.IPResume;
import com.sharkbuyers.ui.resume.interfacres.IResume;
import com.sharkbuyers.ui.resume.presenter.PResume;
import com.sharkbuyers.ui.settings.SettingsActivity;
import com.sharkbuyers.utils.AppController;
import com.sharkbuyers.utils.UtilsDialog;
import com.sharkbuyers.utils.UtilsFontFamily;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResumeActivity extends BaseClass  implements IResume {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.imgEditResume)
    ImageView imgEditResume;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.tvUpdate)
    TextView tvUpdate;
    @BindView(R.id.editResume)
    EditText editResume;

    private boolean isClick = false;

    String intentFrom = "",resume="";
    Context context;
    IPResume ipResume;
    Dialog progressDialog;
    String accessToken="";
    WebView wb;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);
        ButterKnife.bind(this);
        context=ResumeActivity.this;
        ipResume=new PResume(this);
        resume=getIntent().getStringExtra("resume");

        wb = findViewById(R.id.webView);
        if (resume!=null && resume.length()>0)
        {
            Log.d("+++++++",resume);
//            wb = findViewById(R.id.webView);
//            Log.d("+++++++",resume);
//            wb.getSettings().setJavaScriptEnabled(true);
//            wb.getSettings().setLoadWithOverviewMode(true);
//            wb.getSettings().setUseWideViewPort(true);
//            wb.getSettings().setBuiltInZoomControls(true);
//            wb.getSettings().setPluginState(WebSettings.PluginState.ON);
//            String doc="<iframe src='http://docs.google.com/viewer?url=http://m8developers.com/sharkbuyers/public/resume/1596003191.docx&embedded=true' width='100%' height='100%' style='border: none;'></iframe>";
//            //wb.loadUrl("http://m8developers.com/sharkbuyers/public/resume/"+resume);
//            wb.loadUrl("http://docs.google.com/gview?embedded=true&url=" +"http://m8developers.com/sharkbuyers/public/resume/"+resume);
//            //finish();
//            if (resume.contains("pdf")) {
//                try {
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setDataAndType(Uri.parse("http://m8developers.com/sharkbuyers/public/resume/" + resume), "application/pdf");
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                    startActivity(intent);
//                    finish();
//                } catch (Exception e) {
//                    Log.d("+++++++", e.toString());
//                }
//            }
//            if (resume.contains("doc"))
//            {
//                wb.getSettings().setJavaScriptEnabled(true);
//                wb.getSettings().setLoadWithOverviewMode(true);
//                wb.getSettings().setUseWideViewPort(true);
//                wb.getSettings().setBuiltInZoomControls(true);
//                wb.getSettings().setPluginState(WebSettings.PluginState.ON);
//                //wb.setWebViewClient(new Callback());
//                //System.gc();
//                String doc="http://docs.google.com/gview?embedded=true&url=" +"http://m8developers.com/sharkbuyers/public/resume/"+resume;
//                //wb.loadUrl("http://m8developers.com/sharkbuyers/public/resume/"+resume);
//                wb.loadUrl(doc);
//                //wb.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + resume);
//                //wb.loadUrl("http://m8developers.com/sharkbuyers/public/resume/"+resume);
//                finish();
//            }
            wb.getSettings().setJavaScriptEnabled(true);
            wb.getSettings().setLoadWithOverviewMode(true);
            wb.getSettings().setUseWideViewPort(true);
            wb.getSettings().setBuiltInZoomControls(true);
            wb.getSettings().setPluginState(WebSettings.PluginState.ON);
            wb.getSettings().setDomStorageEnabled(true);
            wb.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
            //wb.setWebViewClient(new Callback());
            //System.gc();
            String doc="http://docs.google.com/gview?embedded=true&url=" +"http://m8developers.com/sharkbuyers/public/resume/"+resume;
            //wb.loadUrl("http://m8developers.com/sharkbuyers/public/resume/"+resume);
            wb.loadUrl(doc);
            wb.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageFinished(WebView view, String url) {
                    if (progressDialog!=null)
                    {
                        progressDialog.cancel();
                    }
                    checkPageFinished(doc);
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    progressDialog = UtilsDialog.ShowDialog(ResumeActivity.this);
                }
            });
        }

        accessToken= AppController.getInstance(context).getString(AppController.Key.SAVE_ACCESS_TOKEN);
        initViews();


    }

    private void initViews() {
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        intentFrom = getIntent().getStringExtra("intentFrom");
        if (intentFrom.equalsIgnoreCase("settings")) {
            if (NetworkUtils.isNetworkConnectionAvailable(context)){
                progressDialog = UtilsDialog.ShowDialog(this);
                ipResume.resume(accessToken);
            }
            editResume.setFocusable(false);
            editResume.setFocusableInTouchMode(false);
            editResume.setClickable(false);
            imgEditResume.setImageResource(R.drawable.ic_edit);
            imgEditResume.setVisibility(View.GONE);

        } else if (intentFrom.equalsIgnoreCase("buyersdetails")) {
            editResume.setFocusable(false);
            editResume.setFocusableInTouchMode(false);
            editResume.setClickable(false);
            imgEditResume.setImageResource(R.drawable.ic_edit);
            imgEditResume.setVisibility(View.GONE);
            editResume.setText(resume);
        } else {

        }
    }

    @OnClick({R.id.imgBack, R.id.imgEditResume,R.id.tvUpdate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;

            case R.id.imgEditResume:
                if (isClick == false) {

                    editResume.setFocusable(false);
                    editResume.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
                    editResume.setClickable(false);
                    imgEditResume.setImageResource(R.drawable.ic_edit);
                    tvUpdate.setVisibility(View.GONE);
                    isClick = true;


                } else {

                    editResume.setFocusable(true);
                    editResume.setFocusableInTouchMode(true);
                    editResume.setClickable(true);
                    editResume.setCursorVisible(true);
                    //imgEditResume.setImageResource(R.drawable.ic_done);
                    tvUpdate.setVisibility(View.VISIBLE);

                    isClick = false;


                }
                break;
            case R.id.tvUpdate:
                validationOnResumse();
                break;
        }
    }

    private void validationOnResumse() {
        if (editResume.getText().toString().trim().isEmpty()){
            editResume.setError("Write or paste your resume");
        }else {
            if (NetworkUtils.isNetworkConnectionAvailable(context)){
                progressDialog = UtilsDialog.ShowDialog(this);
                ipResume.editResumse(accessToken,editResume.getText().toString().trim());
            }
        }
    }

    @Override
    public void successResponseResumeFromPresenter(ResumeResponseModel resumeResponseModel) {
        progressDialog.dismiss();
        if (resumeResponseModel!=null&&resumeResponseModel.getData().size()>0){
            resume=resumeResponseModel.getData().get(0).getResume();
            editResume.setText(resume);
            Log.d("+++++++",resume);
//            resume = "1596804639.doc";
//            if (resume.contains("pdf")) {
//                try {
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setDataAndType(Uri.parse("http://m8developers.com/sharkbuyers/public/resume/" + resume), "application/pdf");
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                    startActivity(intent);
//                    finish();
//                } catch (Exception e) {
//                    Log.d("+++++++", e.toString());
//                }
//            }
//            if (resume.contains("doc"))
//            {
                wb.getSettings().setJavaScriptEnabled(true);
                wb.getSettings().setLoadWithOverviewMode(true);
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setBuiltInZoomControls(true);
                wb.getSettings().setPluginState(WebSettings.PluginState.ON);
                wb.getSettings().setDomStorageEnabled(true);
                wb.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
                //wb.setWebViewClient(new Callback());
                //System.gc();
                String doc="http://docs.google.com/gview?embedded=true&url=" +"http://m8developers.com/sharkbuyers/public/resume/"+resume;
                //wb.loadUrl("http://m8developers.com/sharkbuyers/public/resume/"+resume);
                wb.loadUrl(doc);
                wb.setWebViewClient(new WebViewClient(){
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        if (progressDialog!=null)
                        {
                            progressDialog.cancel();
                        }
                        checkPageFinished(doc);
                    }

                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);
                        progressDialog = UtilsDialog.ShowDialog(ResumeActivity.this);
                    }
                });

                //wb.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + resume);
                //wb.loadUrl("http://m8developers.com/sharkbuyers/public/resume/"+resume);

            //}
        }
    }

    @Override
    public void failedResponseResumeFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void editSuccessResponseFromPresenter(EditResumseResponseModel editResumseResponseModel) {
        progressDialog.dismiss();
        if (editResumseResponseModel!=null&&editResumseResponseModel.getData().size()>0){
            AppController.getInstance(context).put(AppController.Key.RESUME, editResumseResponseModel.getData().get(0).getResume().getResume());
            recreate();
        }
    }

    @Override
    public void editFailedResponseFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }

    public void checkPageFinished(String doc) {
        //If view is blank:
        if (wb.getContentHeight() == 0) {
            if (wb.getTitle().equals(""))
                wb.loadUrl(doc);
        }

    }
}
