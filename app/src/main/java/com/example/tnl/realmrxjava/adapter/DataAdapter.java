package com.example.tnl.realmrxjava.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tnl.realmrxjava.R;
import com.example.tnl.realmrxjava.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tnl on 1/10/2018;
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private ArrayList<Model> modelArrayList;

    public DataAdapter(ArrayList<Model> modelArrayList) {
        this.modelArrayList = modelArrayList;
    }

    public void add(List<Model> dataModel) {
        modelArrayList.clear();
        modelArrayList.addAll(dataModel);
        notifyDataSetChanged();
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        holder.tvName.setText(modelArrayList.get(position).getName());
        holder.tvVersion.setText(modelArrayList.get(position).getVer());
        holder.tvApiLevel.setText(modelArrayList.get(position).getApi());

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvVersion, tvApiLevel;

        public DataViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvVersion = itemView.findViewById(R.id.tv_version);
            tvApiLevel = itemView.findViewById(R.id.tv_api_level);
        }
    }
}
