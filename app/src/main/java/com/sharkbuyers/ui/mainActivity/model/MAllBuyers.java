package com.sharkbuyers.ui.mainActivity.model;

import android.os.Handler;
import android.os.Message;

import com.sharkbuyers.services.APIInterface;
import com.sharkbuyers.services.RetrofitCalls;
import com.sharkbuyers.ui.mainActivity.AllBuyersResponseModel;
import com.sharkbuyers.ui.mainActivity.interfaces.IMAllBuyers;
import com.sharkbuyers.ui.mainActivity.interfaces.IPAllBuyers;
import com.sharkbuyers.ui.mainActivity.presenter.PAllBuyers;
import com.sharkbuyers.ui.resume.EditResumseResponseModel;
import com.sharkbuyers.ui.resume.ResumeResponseModel;

public class MAllBuyers implements IMAllBuyers {

    IPAllBuyers ipAllBuyers;
    public MAllBuyers(PAllBuyers pAllBuyers) {
        this.ipAllBuyers=pAllBuyers;
    }

    @Override
    public void allbuyersRestcall(String access_token,String country_id, String state_id) {
        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.get_all_buyers_Api(access_token,country_id,state_id,mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.GET_ALL_BUYERS_SUCCESS:
                    AllBuyersResponseModel allBuyersResponseModel = ((AllBuyersResponseModel) msg.obj);
                    ipAllBuyers.buyersSuccessFromModel(allBuyersResponseModel);
                    break;
                case APIInterface.GET_ALL_BUYERS_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipAllBuyers.byyersFailFromModel(invalidRequest);
                    break;


            }
        }
    };
}
