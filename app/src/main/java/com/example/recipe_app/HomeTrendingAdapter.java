package com.example.recipe_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeTrendingAdapter extends RecyclerView.Adapter<HomeTrendingAdapter.HomeTrendingViewHolder>{
    private List<HomeRecommended> homeTrendingList;

    public void setHomeTrending(List<HomeRecommended> homeTrending) {
        this.homeTrendingList = homeTrending;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeTrendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_trending, parent, false);

        return new HomeTrendingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeTrendingViewHolder holder, int position) {
        HomeRecommended homeTrending = homeTrendingList.get(position);
        if(homeTrending == null) {
            return;
        }

        String duongDanHinhAnh = homeTrending.getHinh();
        Picasso.get().load(duongDanHinhAnh).into(holder.imgHinh);
        holder.txtTen.setText(homeTrending.getTen());

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
        if(homeTrendingList != null) {
            return homeTrendingList.size();
        }
        return 0;
    }

    public class HomeTrendingViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinh;
        TextView txtTen;
        public HomeTrendingViewHolder(@NonNull View view) {
            super(view);
            txtTen = (TextView) view.findViewById(R.id.txtTenTrending);
            imgHinh = (ImageView) view.findViewById(R.id.imgHinhTrending);
        }
    }
    private RecyclerViewItemClickListener itemClickListener;

    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }
}
