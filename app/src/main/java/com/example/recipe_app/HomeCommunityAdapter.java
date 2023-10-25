package com.example.recipe_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeCommunityAdapter extends RecyclerView.Adapter<HomeCommunityAdapter.HomeCommunityViewHolder> {
    private List<Community> homeCommunityList;

    public void setHomeCommunityList(List<Community> homeCommunityList) {
        this.homeCommunityList = homeCommunityList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeCommunityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_community, parent, false);

        return new HomeCommunityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCommunityViewHolder holder, int position) {
        Community homeCommunity = homeCommunityList.get(position);

        if(homeCommunity == null) {
            return;
        }
        holder.imgHinh.setImageResource(homeCommunity.getHinh());
        holder.txtTen.setText(homeCommunity.getTenMon());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(homeCommunityList != null) {
            return homeCommunityList.size();
        }
        return 0;
    }

    public class HomeCommunityViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinh;
        TextView txtTen;
        public HomeCommunityViewHolder(@NonNull View view) {
            super(view);
            txtTen = (TextView) view.findViewById(R.id.txtTenHomeCommunity);
            imgHinh = (ImageView) view.findViewById(R.id.imgHinhHomeCommunity);
        }
    }
    private RecyclerViewItemClickListener itemClickListener;

    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }
}
