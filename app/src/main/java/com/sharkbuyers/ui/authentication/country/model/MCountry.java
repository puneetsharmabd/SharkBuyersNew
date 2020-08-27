package com.sharkbuyers.ui.authentication.country.model;

import android.os.Handler;
import android.os.Message;

import com.sharkbuyers.services.APIInterface;
import com.sharkbuyers.services.RetrofitCalls;
import com.sharkbuyers.ui.authentication.country.CountryResponseModel;
import com.sharkbuyers.ui.authentication.country.Interfaces.IMCountry;
import com.sharkbuyers.ui.authentication.country.Interfaces.IPCountry;
import com.sharkbuyers.ui.authentication.country.presenter.PCountry;
import com.sharkbuyers.ui.authentication.uploadresume.responseModel.RegisterResponseModel;

public class MCountry implements IMCountry {
    IPCountry ipCountry;

    public MCountry(PCountry pCountry) {
        this.ipCountry = pCountry;
    }

    @Override
    public void countryRestCalls() {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.country_Api(mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.GET_COUNTRY_SUCCESS:
                    CountryResponseModel countryResponseModel = ((CountryResponseModel) msg.obj);
                    ipCountry.successResponseModelCountry(countryResponseModel);
                    break;
                case APIInterface.GET_COUNTRY_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipCountry.errorResponseModelCountry(invalidRequest);
                    break;

            }
        }
    };
}
