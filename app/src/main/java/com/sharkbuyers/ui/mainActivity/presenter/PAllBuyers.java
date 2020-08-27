package com.sharkbuyers.ui.mainActivity.presenter;

import com.sharkbuyers.ui.buyers.buyersListingActitvity.BuyersListingActivity;
import com.sharkbuyers.ui.mainActivity.AllBuyersResponseModel;
import com.sharkbuyers.ui.mainActivity.MainActivity;
import com.sharkbuyers.ui.mainActivity.interfaces.IAllBuyers;
import com.sharkbuyers.ui.mainActivity.interfaces.IMAllBuyers;
import com.sharkbuyers.ui.mainActivity.interfaces.IPAllBuyers;
import com.sharkbuyers.ui.mainActivity.model.MAllBuyers;

public class PAllBuyers implements IPAllBuyers {

    IAllBuyers iAllBuyers;
    IMAllBuyers imAllBuyers;

    public PAllBuyers(BuyersListingActivity buyersListingActivity) {
        this.iAllBuyers=buyersListingActivity;
    }


    @Override
    public void getallBuyers(String access_token,String country_id, String state_id) {
        imAllBuyers=new MAllBuyers(this);
        imAllBuyers.allbuyersRestcall(access_token,country_id,state_id);
    }

    @Override
    public void buyersSuccessFromModel(AllBuyersResponseModel allBuyersResponseModel) {
        iAllBuyers.buyersSuccessFromPresenter(allBuyersResponseModel);
    }

    @Override
    public void byyersFailFromModel(String message) {
        iAllBuyers.byyersFailFromPresenter(message);
    }
}
