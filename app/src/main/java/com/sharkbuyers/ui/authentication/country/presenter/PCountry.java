package com.sharkbuyers.ui.authentication.country.presenter;

import com.sharkbuyers.ui.authentication.country.CountryResponseModel;
import com.sharkbuyers.ui.authentication.country.Interfaces.ICountry;
import com.sharkbuyers.ui.authentication.country.Interfaces.IMCountry;
import com.sharkbuyers.ui.authentication.country.Interfaces.IPCountry;
import com.sharkbuyers.ui.authentication.country.SelectCountryActivity;
import com.sharkbuyers.ui.authentication.country.model.MCountry;

public class PCountry implements IPCountry {

    ICountry iCountry;
    IMCountry imCountry;
    public PCountry(SelectCountryActivity selectCountryActivity) {
        this.iCountry=selectCountryActivity;
    }

    @Override
    public void country() {
        imCountry=new MCountry(this);
        imCountry.countryRestCalls();
    }

    @Override
    public void successResponseModelCountry(CountryResponseModel countryResponseModel) {
        iCountry.successResponseModelCountry(countryResponseModel);
    }

    @Override
    public void errorResponseModelCountry(String message) {
        iCountry.errorResponseModelCountry(message);
    }
}
