package com.sharkbuyers.ui.buyers.buyersListingActitvity.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sharkbuyers.R;
import com.sharkbuyers.ui.buyers.buyersDetails.BuyersDetailsActivity;
import com.sharkbuyers.ui.mainActivity.AllBuyersResponseModel;
import com.sharkbuyers.utils.CommonMethods;
import com.sharkbuyers.utils.Constant;
import com.sharkbuyers.utils.UtilsFontFamily;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class BuyersAdapter extends RecyclerView.Adapter<BuyersAdapter.ViewHolder> {

    Context context;
    List<AllBuyersResponseModel.Datum> data;
    List<AllBuyersResponseModel.Datum> mFilterList;
    int position;

    public BuyersAdapter(Context context, List<AllBuyersResponseModel.Datum> data) {
        this.context = context;
        this.data = data;
        this.mFilterList = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_buyers, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (mFilterList.get(position).getBrandImage() != null) {
            Glide.with(context).load(Constant.IMAGE_URL + mFilterList.get(position).getBrandImage())
                    .placeholder(R.drawable.ic_e_dummy)
                    .into(holder.imgBuyers);
        } else {
            Glide.with(context).load(Constant.IMAGE_URL + mFilterList.get(position).getBrandImage())
                    .placeholder(R.drawable.ic_e_dummy)
                    .dontAnimate()
                    .into(holder.imgBuyers);
        }
        String state_name = mFilterList.get(position).getState().getName();
        holder.tvBuyerName.setText(CommonMethods.upperCase(mFilterList.get(position).getFirstName()));
        holder.tvBuyerOccupation.setText(CommonMethods.upperCase(mFilterList.get(position).getOccupation()));
        holder.tvBuyerAddress.setText(CommonMethods.upperCase(mFilterList.get(position).getCountry().getName() + "," + state_name));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BuyersDetailsActivity.class);
                intent.putExtra("position", String.valueOf(position));
                intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) data);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFilterList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgBuyers)
        CircleImageView imgBuyers;

        @BindView(R.id.imgArrow)
        ImageView imgArrow;

        @BindView(R.id.tvBuyerName)
        TextView tvBuyerName;

        @BindView(R.id.tvBuyerOccupation)
        TextView tvBuyerOccupation;

        @BindView(R.id.tvBuyerAddress)
        TextView tvBuyerAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            tvBuyerName.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            tvBuyerAddress.setTypeface(UtilsFontFamily.typeFaceForRobotoLight(context));
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

                    ArrayList<AllBuyersResponseModel.Datum> filteredList = new ArrayList<>();

                    for (AllBuyersResponseModel.Datum datum : data) {

                        if (datum.getFirstName().toLowerCase().contains(charString) || datum.getLastName().toLowerCase().contains(charString)
                                || datum.getOccupation().toLowerCase().contains(charString)) {

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
                mFilterList = (ArrayList<AllBuyersResponseModel.Datum>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
