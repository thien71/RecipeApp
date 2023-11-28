package com.example.recipe_app;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeRecommendedAdapter extends RecyclerView.Adapter<HomeRecommendedAdapter.HomeRecommendedViewHolder>{
    private List<HomeRecommended> homeRecommendedList;

    public void setHomeRecommendedList(List<HomeRecommended> homeRecommendedList) {
        this.homeRecommendedList = homeRecommendedList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeRecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_recommended, parent, false);

        return new HomeRecommendedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecommendedViewHolder holder, int position) {
        HomeRecommended homeRecommended = homeRecommendedList.get(position);

        if(homeRecommended == null) {
            return;
        }
        String duongDanHinhAnh = homeRecommended.getHinh();
        Picasso.get().load(duongDanHinhAnh).into(holder.imgHinh);

        holder.txtTen.setText(homeRecommended.getTen());

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
        if(homeRecommendedList != null) {
            return homeRecommendedList.size();
        }
        return 0;
    }

    public class HomeRecommendedViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinh;
        TextView txtTen;
        public HomeRecommendedViewHolder(@NonNull View view) {
            super(view);
            txtTen = (TextView) view.findViewById(R.id.txtTenRecommended);
            imgHinh = (ImageView) view.findViewById(R.id.imgHinhRecommended);
        }
    }

    public HomeRecommended getItem(int position) {
        if (position >= 0 && position < homeRecommendedList.size()) {
            return homeRecommendedList.get(position);
        }
        return null;
    }

    private RecyclerViewItemClickListener itemClickListener;

    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }
}
