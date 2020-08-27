package com.sharkbuyers.ui.authentication.state.model;

import android.os.Handler;
import android.os.Message;

import com.sharkbuyers.services.APIInterface;
import com.sharkbuyers.services.RetrofitCalls;

import com.sharkbuyers.ui.authentication.country.StatesResponseModel;
import com.sharkbuyers.ui.authentication.state.interfaces.IMState;
import com.sharkbuyers.ui.authentication.state.interfaces.IPState;
import com.sharkbuyers.ui.authentication.state.presenter.PState;

public class MState implements IMState {

    IPState ipState;

    public MState(PState pState) {
        this.ipState=pState;
    }

    @Override
    public void stateRestCalls(String country_id) {
        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.state_Api(country_id,mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.GET_STATE_SUCCESS:
                    StatesResponseModel statesResponseModel = ((StatesResponseModel) msg.obj);
                    ipState.successResponseModelState(statesResponseModel);
                    break;
                case APIInterface.GET_STATE_FAILED:
                    String invalidRequest = String.valueOf(msg.obj);
                    ipState.errorResponseModelState(invalidRequest);
                    break;

            }
        }
    };
}
