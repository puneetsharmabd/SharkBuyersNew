package com.sharkbuyers.ui.authentication.country.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sharkbuyers.R;
import com.sharkbuyers.ui.authentication.country.CountryResponseModel;
import com.sharkbuyers.ui.authentication.country.SelectCountryActivity;
import com.sharkbuyers.ui.authentication.state.StateActivity;
import com.sharkbuyers.ui.authentication.uploadresume.UploadResumeActivity;
import com.sharkbuyers.ui.authentication.country.StatesResponseModel;
import com.sharkbuyers.utils.UtilsFontFamily;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {

    Context context;
    List<StatesResponseModel.Datum> data;
    List<StatesResponseModel.Datum> mFilterList;
    StateActivity stateActivity;
    String intentFrom;

    public StateAdapter(StateActivity stateActivity, List<StatesResponseModel.Datum> data, Context context, String intentFrom) {
        this.context = context;
        this.stateActivity = stateActivity;
        this.data = data;

        this.intentFrom = intentFrom;
        this.mFilterList = data;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_states, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvStates.setText(mFilterList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (intentFrom.equalsIgnoreCase("register")) {
                    InputMethodManager imm = (InputMethodManager) v.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    stateActivity.tvSelect.setVisibility(View.VISIBLE);
                    stateActivity.tvSelect.setText(mFilterList.get(position).getName()+" ..click to proceed.");
                    ((StateActivity) stateActivity).onItemClick(mFilterList.get(position).getName(), String.valueOf(mFilterList.get(position).getId()));

                } else if (intentFrom.equalsIgnoreCase("main")) {
                    InputMethodManager imm = (InputMethodManager) v.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    stateActivity.tvSelect.setVisibility(View.VISIBLE);
                    stateActivity.tvSelect.setText(mFilterList.get(position).getName()+" ..click to proceed.");
                    ((StateActivity) stateActivity).onItemClick(mFilterList.get(position).getName(), String.valueOf(mFilterList.get(position).getId()));

                }else if (intentFrom.equalsIgnoreCase("editprofile")) {
                    InputMethodManager imm = (InputMethodManager) v.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    stateActivity.tvSelect.setVisibility(View.VISIBLE);
                    stateActivity.tvSelect.setText(mFilterList.get(position).getName()+" ..click to proceed.");
                    ((StateActivity) stateActivity).onItemClick(mFilterList.get(position).getName(), String.valueOf(mFilterList.get(position).getId()));

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

        @BindView(R.id.tvStates)
        TextView tvStates;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            tvStates.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
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

                    ArrayList<StatesResponseModel.Datum> filteredList = new ArrayList<>();

                    for (StatesResponseModel.Datum datum : data) {

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
                mFilterList = (ArrayList<StatesResponseModel.Datum>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
