package com.sharkbuyers.ui.mainActivity.interfaces;

import com.sharkbuyers.ui.mainActivity.AllBuyersResponseModel;

public interface IPAllBuyers {
    void getallBuyers(String access_token,String country_id,String state_id);
    void buyersSuccessFromModel(AllBuyersResponseModel allBuyersResponseModel);
    void byyersFailFromModel(String message);

}
