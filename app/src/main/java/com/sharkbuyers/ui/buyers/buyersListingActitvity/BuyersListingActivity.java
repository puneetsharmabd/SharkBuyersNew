package com.sharkbuyers.ui.buyers.buyersListingActitvity;

import android.app.Dialog;
import android.content.Context;
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
import com.sharkbuyers.ui.buyers.buyersListingActitvity.adapters.BuyersAdapter;
import com.sharkbuyers.ui.mainActivity.AllBuyersResponseModel;
import com.sharkbuyers.ui.mainActivity.interfaces.IAllBuyers;
import com.sharkbuyers.ui.mainActivity.interfaces.IPAllBuyers;
import com.sharkbuyers.ui.mainActivity.presenter.PAllBuyers;
import com.sharkbuyers.utils.AppController;
import com.sharkbuyers.utils.UtilsDialog;
import com.sharkbuyers.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuyersListingActivity extends BaseClass implements IAllBuyers {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.imgNoData)
    ImageView imgNoData;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.recyclerBuyers)
    RecyclerView recyclerBuyers;

    BuyersAdapter buyersAdapter;
    Context context;
    String country_id = "", state_id = "";
    IPAllBuyers ipAllBuyers;
    String access_token = "";
    Dialog progressDialog;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyers_listing);
        ButterKnife.bind(this);
        context = BuyersListingActivity.this;
        access_token = AppController.getInstance(context).getString(AppController.Key.SAVE_ACCESS_TOKEN);
        country_id = getIntent().getStringExtra("country_id");
        state_id = getIntent().getStringExtra("state_id");
        ipAllBuyers = new PAllBuyers(this);
        viewInitialization();
    }

    private void viewInitialization() {
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            progressDialog = UtilsDialog.ShowDialog(this);
            ipAllBuyers.getallBuyers(access_token, country_id, state_id);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                buyersAdapter.getFilter().filter(newText);
                return true;
            }
        });

    }

    @Override
    public void buyersSuccessFromPresenter(AllBuyersResponseModel allBuyersResponseModel) {
        progressDialog.dismiss();
        if (allBuyersResponseModel != null && allBuyersResponseModel.getData().size() > 0) {
            imgNoData.setVisibility(View.GONE);
            searchView.setVisibility(View.VISIBLE);
            buyersAdapter = new BuyersAdapter(this, allBuyersResponseModel.getData());
            recyclerBuyers.setLayoutManager(new LinearLayoutManager(this));
            recyclerBuyers.setAdapter(buyersAdapter);
        }
    }

    @Override
    public void byyersFailFromPresenter(String message) {
        progressDialog.dismiss();
        imgNoData.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.GONE);
        String error_message = message;
        //Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();
    }
}