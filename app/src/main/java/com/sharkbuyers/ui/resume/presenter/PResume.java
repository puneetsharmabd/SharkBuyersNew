package com.sharkbuyers.ui.resume.presenter;

import com.sharkbuyers.ui.resume.EditResumseResponseModel;
import com.sharkbuyers.ui.resume.ResumeActivity;
import com.sharkbuyers.ui.resume.ResumeResponseModel;
import com.sharkbuyers.ui.resume.interfacres.IMResume;
import com.sharkbuyers.ui.resume.interfacres.IPResume;
import com.sharkbuyers.ui.resume.interfacres.IResume;
import com.sharkbuyers.ui.resume.model.MResumse;

public class PResume implements IPResume {
    IResume ipResume;
    IMResume imResume;

    public PResume(ResumeActivity resumeActivity) {
        this.ipResume=resumeActivity;
    }

    @Override
    public void resume(String access_token) {
        imResume=new MResumse(this);
        imResume.resumeRestCall(access_token);
    }

    @Override
    public void successResponseResumeFromModel(ResumeResponseModel resumeResponseModel) {
        ipResume.successResponseResumeFromPresenter(resumeResponseModel);
    }

    @Override
    public void failedResponseResumeFromModel(String message) {
        ipResume.failedResponseResumeFromPresenter(message);
    }

    @Override
    public void editResumse(String access_token, String resume) {
        imResume=new MResumse(this);
        imResume.editResumseRestCall(access_token,resume);
    }

    @Override
    public void editSuccessResponseFromModel(EditResumseResponseModel editResumseResponseModel) {
        ipResume.editSuccessResponseFromPresenter(editResumseResponseModel);
    }

    @Override
    public void editFailedResponseFromModel(String message) {
        ipResume.editFailedResponseFromPresenter(message);
    }
}
