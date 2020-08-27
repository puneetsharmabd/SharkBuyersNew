package com.sharkbuyers.ui.resume.interfacres;

import com.sharkbuyers.ui.resume.EditResumseResponseModel;
import com.sharkbuyers.ui.resume.ResumeResponseModel;

public interface IResume {

    void successResponseResumeFromPresenter(ResumeResponseModel resumeResponseModel);
    void  failedResponseResumeFromPresenter(String message);

    void editSuccessResponseFromPresenter(EditResumseResponseModel editResumseResponseModel);
    void editFailedResponseFromPresenter(String message);
}
