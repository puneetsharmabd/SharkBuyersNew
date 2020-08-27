package com.sharkbuyers.ui.mainActivity.interfaces;

import com.sharkbuyers.ui.mainActivity.AllBuyersResponseModel;

public interface IAllBuyers {

    void buyersSuccessFromPresenter(AllBuyersResponseModel allBuyersResponseModel);
    void byyersFailFromPresenter(String message);

}
