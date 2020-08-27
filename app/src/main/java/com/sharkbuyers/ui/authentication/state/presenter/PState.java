package com.sharkbuyers.ui.authentication.state.presenter;

import com.sharkbuyers.ui.authentication.country.StatesResponseModel;
import com.sharkbuyers.ui.authentication.state.StateActivity;
import com.sharkbuyers.ui.authentication.state.interfaces.IMState;
import com.sharkbuyers.ui.authentication.state.interfaces.IPState;
import com.sharkbuyers.ui.authentication.state.interfaces.IState;
import com.sharkbuyers.ui.authentication.state.model.MState;

public class PState implements IPState {

    IState iState;
    IMState imState;
    public PState(StateActivity stateActivity) {
        this.iState=stateActivity;
    }

    @Override
    public void state(String country_id) {
        imState=new MState(this);
        imState.stateRestCalls(country_id);
    }

    @Override
    public void successResponseModelState(StatesResponseModel statesResponseModel) {
        iState.successResponsePresenterState(statesResponseModel);
    }

    @Override
    public void errorResponseModelState(String message) {
        iState.errorResponsePresenteState(message);
    }
}
