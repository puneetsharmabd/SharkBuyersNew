package com.sharkbuyers.ui.authentication.state.interfaces;
import com.sharkbuyers.ui.authentication.country.StatesResponseModel;

public interface IPState {
    void state(String country_id);
    void successResponseModelState(StatesResponseModel statesResponseModel);
    void errorResponseModelState(String message);

}
