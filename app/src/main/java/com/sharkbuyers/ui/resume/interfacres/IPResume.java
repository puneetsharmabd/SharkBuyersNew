package com.sharkbuyers.ui.resume.interfacres;

import com.sharkbuyers.ui.resume.EditResumseResponseModel;
import com.sharkbuyers.ui.resume.ResumeResponseModel;

public interface IPResume {
    void resume(String access_token);
    void successResponseResumeFromModel(ResumeResponseModel resumeResponseModel);
    void  failedResponseResumeFromModel(String message);

    void editResumse(String access_token,String resume);
    void editSuccessResponseFromModel(EditResumseResponseModel editResumseResponseModel);
    void editFailedResponseFromModel(String message);
}
