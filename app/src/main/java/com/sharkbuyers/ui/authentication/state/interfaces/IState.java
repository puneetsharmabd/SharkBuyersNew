package com.sharkbuyers.ui.authentication.state.interfaces;

import com.sharkbuyers.ui.authentication.country.CountryResponseModel;
import com.sharkbuyers.ui.authentication.country.StatesResponseModel;

public interface IState {

    void successResponsePresenterState(StatesResponseModel statesResponseModel);
    void errorResponsePresenteState(String message);

}
