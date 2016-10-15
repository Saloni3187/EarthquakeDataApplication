package com.earthquakeapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.earthquakeapp.model.EarthquakesList;
import com.earthquakeapp.views.R;

import java.util.ArrayList;
/**This class used to set data in recycler view which holds all the data while inflating the layout base on
 * different output value
 * Created by Saloni on 14/10/16.
 */
public class EarthquakeDetailsDataAdapter extends RecyclerView
        .Adapter<EarthquakeDetailsDataAdapter
        .DataObjectHolder> {
    private ArrayList<EarthquakesList> mDataset;

    //data holder to hold values for all the views
    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView tv_src;
        TextView tv_euid;
        TextView tv_lat;
        TextView tv_lng;
        TextView tv_magnitude;
        TextView tv_depth;
        TextView tv_time;
        public DataObjectHolder(View itemView) {
            super(itemView);
            tv_src = (TextView) itemView.findViewById(R.id.tv_src);
            tv_euid = (TextView) itemView.findViewById(R.id.tv_eqid);
            tv_lat = (TextView) itemView.findViewById(R.id.tv_lat);
            tv_lng = (TextView) itemView.findViewById(R.id.tv_lng);
            tv_magnitude = (TextView) itemView.findViewById(R.id.tv_magnitude);
            tv_depth = (TextView) itemView.findViewById(R.id.tv_depth);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }

    public EarthquakeDetailsDataAdapter(ArrayList<EarthquakesList> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.earthquake_details_data_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.tv_src.setText(mDataset.get(position).getSrc());
        holder.tv_euid.setText(mDataset.get(position).getEqid());
        holder.tv_lat.setText(mDataset.get(position).getLat());
        holder.tv_lng.setText(mDataset.get(position).getLon());
        holder.tv_magnitude.setText(mDataset.get(position).getMagnitude());
        holder.tv_depth.setText(mDataset.get(position).getDepth());
        holder.tv_time.setText(mDataset.get(position).getTimedate());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

