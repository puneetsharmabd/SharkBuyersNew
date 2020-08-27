package com.sharkbuyers.ui.resume.model;

import android.os.Handler;
import android.os.Message;

import com.sharkbuyers.services.APIInterface;
import com.sharkbuyers.services.RetrofitCalls;
import com.sharkbuyers.ui.resume.EditResumseResponseModel;
import com.sharkbuyers.ui.resume.ResumeResponseModel;
import com.sharkbuyers.ui.resume.interfacres.IMResume;
import com.sharkbuyers.ui.resume.interfacres.IPResume;
import com.sharkbuyers.ui.resume.presenter.PResume;

public class MResumse implements IMResume {

    IPResume ipResume;

    public MResumse(PResume pResume) {
        this.ipResume = pResume;
    }

    @Override
    public void resumeRestCall(String access_token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.resmue_Api(access_token, mHandler);
    }

    @Override
    public void editResumseRestCall(String access_token, String resume) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.editresmue_Api(access_token, resume, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.RESUME_SUCCESS:
                    ResumeResponseModel resumeResponseModel = ((ResumeResponseModel) msg.obj);
                    ipResume.successResponseResumeFromModel(resumeResponseModel);
                    break;
                case APIInterface.RESUME_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipResume.failedResponseResumeFromModel(invalidRequest);
                    break;
                case APIInterface.EDIT_RESUME_SUCCESS:
                    EditResumseResponseModel editResumseResponseModel = ((EditResumseResponseModel) msg.obj);
                    ipResume.editSuccessResponseFromModel(editResumseResponseModel);
                    break;
                case APIInterface.EDIT_RESUME_FAILED:
                    String invalidRequestedit = String.valueOf(msg.obj);
                    ipResume.editFailedResponseFromModel(invalidRequestedit);
                    break;

            }
        }
    };
}
