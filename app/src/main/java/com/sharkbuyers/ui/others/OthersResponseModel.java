package com.sharkbuyers.ui.others;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class OthersResponseModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("about_us")
        @Expose
        private String aboutUs;
        @SerializedName("help_support")
        @Expose
        private String helpSupport;
        @SerializedName("terms_condition")
        @Expose
        private String termsCondition;

        public String getAboutUs() {
            return aboutUs;
        }

        public void setAboutUs(String aboutUs) {
            this.aboutUs = aboutUs;
        }

        public String getHelpSupport() {
            return helpSupport;
        }

        public void setHelpSupport(String helpSupport) {
            this.helpSupport = helpSupport;
        }

        public String getTermsCondition() {
            return termsCondition;
        }

        public void setTermsCondition(String termsCondition) {
            this.termsCondition = termsCondition;
        }

    }
}
