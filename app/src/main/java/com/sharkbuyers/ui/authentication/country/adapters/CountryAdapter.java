package com.sharkbuyers.ui.authentication.country.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sharkbuyers.R;
import com.sharkbuyers.ui.authentication.country.SelectCountryActivity;
import com.sharkbuyers.ui.authentication.uploadresume.UploadResumeActivity;
import com.sharkbuyers.ui.authentication.country.CountryResponseModel;
import com.sharkbuyers.ui.mainActivity.AllBuyersResponseModel;
import com.sharkbuyers.utils.UtilsFontFamily;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    Context context;
    List<CountryResponseModel.Datum> data;
    List<CountryResponseModel.Datum> mFilterList;

    SelectCountryActivity selectCountryActivity;
    String intentFrom;

    public CountryAdapter(Context context, List<CountryResponseModel.Datum> data, SelectCountryActivity selectCountryActivity, String intentFrom) {
        this.context = context;
        this.data = data;
        this.selectCountryActivity = selectCountryActivity;
        this.intentFrom = intentFrom;
        this.mFilterList = data;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = mFilterList.get(position).getName();
        holder.tvCountry.setText(name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intentFrom.equalsIgnoreCase("register")) {
                    InputMethodManager imm = (InputMethodManager) v.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    selectCountryActivity.tvSelect.setVisibility(View.VISIBLE);
                    selectCountryActivity.tvSelect.setText(name+" ..click to proceed.");
                    ((SelectCountryActivity) selectCountryActivity).onItemClick(String.valueOf(mFilterList.get(position).getId()), mFilterList.get(position).getName());

                } else if (intentFrom.equalsIgnoreCase("main")) {
                    InputMethodManager imm = (InputMethodManager) v.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    selectCountryActivity.tvSelect.setVisibility(View.VISIBLE);
                    selectCountryActivity.tvSelect.setText(name+" ..click to proceed.");
                    ((SelectCountryActivity) selectCountryActivity).onItemClick(String.valueOf(mFilterList.get(position).getId()), mFilterList.get(position).getName());
                } else if (intentFrom.equalsIgnoreCase("editprofile")) {
                    InputMethodManager imm = (InputMethodManager) v.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    selectCountryActivity.tvSelect.setVisibility(View.VISIBLE);
                    selectCountryActivity.tvSelect.setText(name+" ..click to proceed.");
                    ((SelectCountryActivity) selectCountryActivity).onItemClick(String.valueOf(mFilterList.get(position).getId()), mFilterList.get(position).getName());
                } else {

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mFilterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCountry)
        TextView tvCountry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            tvCountry.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {

                    mFilterList = data;
                }else {

                    ArrayList<CountryResponseModel.Datum> filteredList = new ArrayList<>();

                    for (CountryResponseModel.Datum datum : data) {

                        if (datum.getName().toLowerCase().contains(charString)) {

                            filteredList.add(datum);
                        }
                    }
                    mFilterList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilterList = (ArrayList<CountryResponseModel.Datum>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
