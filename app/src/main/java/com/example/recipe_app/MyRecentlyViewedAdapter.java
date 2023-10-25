package com.example.recipe_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecentlyViewedAdapter extends RecyclerView.Adapter<MyRecentlyViewedAdapter.MyRecentlyViewedViewHolder>{
    private List<MyRecentlyViewed> myRecentlyViewedList;

    public void setMyRecentlyViewedList(List<MyRecentlyViewed> myRecentlyViewedList) {
        this.myRecentlyViewedList = myRecentlyViewedList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyRecentlyViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_recently, parent, false);

        return new MyRecentlyViewedViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyRecentlyViewedViewHolder holder, int position) {
        MyRecentlyViewed myRecentlyViewed = myRecentlyViewedList.get(position);

        if(myRecentlyViewed == null) {
            return;
        }
        holder.imgHinh.setImageResource(myRecentlyViewed.getHinh());
        holder.txtTen.setText(myRecentlyViewed.getTen());

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
        if(myRecentlyViewedList != null) {
            return myRecentlyViewedList.size();
        }
        return 0;
    }

    public class MyRecentlyViewedViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinh;
        TextView txtTen;
        public MyRecentlyViewedViewHolder(@NonNull View view) {
            super(view);
            txtTen = (TextView) view.findViewById(R.id.txtTenRecently);
            imgHinh = (ImageView) view.findViewById(R.id.imgHinhRecently);
        }
    }

    private RecyclerViewItemClickListener itemClickListener; // Biến để lưu trữ người nghe

    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener; // Phương thức để thiết lập người nghe
    }
}
