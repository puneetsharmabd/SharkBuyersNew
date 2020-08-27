package com.sharkbuyers.ui.authentication.country.Interfaces;

import com.sharkbuyers.ui.authentication.country.CountryResponseModel;

public interface ICountry {

    void successResponseModelCountry(CountryResponseModel countryResponseModel);
    void errorResponseModelCountry(String message);

}
