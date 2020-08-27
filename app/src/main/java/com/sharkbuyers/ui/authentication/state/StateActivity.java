package com.sharkbuyers.ui.authentication.state;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sharkbuyers.R;
import com.sharkbuyers.baseClass.BaseClass;
import com.sharkbuyers.networks.NetworkUtils;
import com.sharkbuyers.ui.authentication.country.CountryResponseModel;
import com.sharkbuyers.ui.authentication.country.StatesResponseModel;
import com.sharkbuyers.ui.authentication.country.adapters.SimpleDividerItemDecoration;
import com.sharkbuyers.ui.authentication.country.adapters.StateAdapter;
import com.sharkbuyers.ui.authentication.state.interfaces.IPState;
import com.sharkbuyers.ui.authentication.state.interfaces.IState;
import com.sharkbuyers.ui.authentication.state.presenter.PState;
import com.sharkbuyers.utils.UtilsDialog;
import com.sharkbuyers.utils.UtilsFontFamily;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StateActivity extends BaseClass implements IState {

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.recyclerState)
    RecyclerView recyclerState;

    Context context;
    public StateAdapter stateAdapter;
    Dialog progressDialog;
    String state_name = "", country_id = "", state_id;
    IPState ipState;
    String intentFrom = "";
    public static TextView tvSelect;

    @BindView(R.id.searchView)
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        ButterKnife.bind(this);
        context = StateActivity.this;
        tvSelect = (TextView) findViewById(R.id.tvSelect);
        ipState = new PState(this);

        country_id = getIntent().getStringExtra("country_id");
        intentFrom = getIntent().getStringExtra("intentFrom");
        tvSelect.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));

        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

        if (NetworkUtils.isNetworkConnectionAvailable(context)) {
            progressDialog = UtilsDialog.ShowDialog(this);
            ipState.state(country_id);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                stateAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    @OnClick({R.id.imgBack, R.id.tvSelect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvSelect:
                Intent intent = new Intent();
                intent.putExtra("state_name", state_name);
                intent.putExtra("state_id", state_id);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    @Override
    public void successResponsePresenterState(StatesResponseModel statesResponseModel) {
        progressDialog.dismiss();
        if (statesResponseModel != null && statesResponseModel.getData().size() > 0) {
            stateAdapter = new StateAdapter(this, statesResponseModel.getData(), StateActivity.this, intentFrom);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerState.setLayoutManager(layoutManager);
            recyclerState.addItemDecoration(new SimpleDividerItemDecoration(this));
            recyclerState.setAdapter(stateAdapter);
        }
    }

    @Override
    public void errorResponsePresenteState(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Toast.makeText(context, "" + error_message, Toast.LENGTH_SHORT).show();
    }

    public void onItemClick(String statename, String stateid) {
        state_name = statename;
        state_id = stateid;
    }



}
