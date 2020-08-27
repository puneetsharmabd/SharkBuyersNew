package com.sharkbuyers.ui.authentication.country;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sharkbuyers.R;
import com.sharkbuyers.baseClass.BaseClass;
import com.sharkbuyers.networks.NetworkUtils;
import com.sharkbuyers.ui.authentication.country.Interfaces.ICountry;
import com.sharkbuyers.ui.authentication.country.Interfaces.IPCountry;
import com.sharkbuyers.ui.authentication.country.adapters.CountryAdapter;
import com.sharkbuyers.ui.authentication.country.adapters.SimpleDividerItemDecoration;
import com.sharkbuyers.ui.authentication.country.presenter.PCountry;
import com.sharkbuyers.utils.UtilsDialog;
import com.sharkbuyers.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectCountryActivity extends BaseClass implements ICountry {

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.recyclerCountry)
    RecyclerView recyclerCountry;


    public CountryAdapter countryAdapter;
    public static TextView tvSelect;
    String county_name = "", country_id = "";


    IPCountry ipCountry;
    Context context;
    Dialog progressDialog;
    String intentFrom = "";

    @BindView(R.id.searchView)
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);
        ButterKnife.bind(this);
        context = SelectCountryActivity.this;
        intentFrom = getIntent().getStringExtra("intentFrom");
        tvSelect = (TextView) findViewById(R.id.tvSelect);
        ipCountry = new PCountry(this);
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvSelect.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));

        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            progressDialog = UtilsDialog.ShowDialog(this);
            ipCountry.country();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                countryAdapter.getFilter().filter(newText);
                return true;
            }
        });

    }

    @Override
    public void successResponseModelCountry(CountryResponseModel countryResponseModel) {
        progressDialog.dismiss();
        if (countryResponseModel != null && countryResponseModel.getData().size() > 0) {
            countryAdapter = new CountryAdapter(this, countryResponseModel.getData(), SelectCountryActivity.this, intentFrom);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerCountry.setLayoutManager(layoutManager);
            recyclerCountry.addItemDecoration(new SimpleDividerItemDecoration(this));

            recyclerCountry.setAdapter(countryAdapter);
        }
    }

    @Override
    public void errorResponseModelCountry(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.imgBack, R.id.tvSelect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvSelect:
                Intent intent = new Intent();
                intent.putExtra("county_name", county_name);
                intent.putExtra("country_id", country_id);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    public void onItemClick(String countryid, String countryname) {
        county_name = countryname;
        country_id = countryid;
    }
}
