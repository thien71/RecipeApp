package com.example.recipe_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyTipsAdapter extends RecyclerView.Adapter<MyTipsAdapter.MyTipsViewHolder>{

    private List<MyTips> myTipsList;

    public void setMyTipsList(List<MyTips> myTipsList) {
        this.myTipsList = myTipsList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyTipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_tips, parent, false);

        return new MyTipsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTipsViewHolder holder, int position) {
        MyTips myTips = myTipsList.get(position);

        if(myTips == null) {
            return;
        }

        holder.txtContent.setText(myTips.getContent());
        holder.txtLikeNumber.setText(myTips.getLikeNumber()+"");
        holder.txtTen.setText(myTips.getTen());
        holder.txtTime.setText(myTips.getTime());
        holder.imgHinh.setImageResource(myTips.getHinh());

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
        if(myTipsList != null) {
            return myTipsList.size();
        }
        return 0;
    }

    public class MyTipsViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinh;
        TextView txtTen, txtTime, txtLikeNumber, txtContent;
        public MyTipsViewHolder(@NonNull View view) {
            super(view);

            txtContent = (TextView) view.findViewById(R.id.txtContent);
            txtLikeNumber = (TextView) view.findViewById(R.id.txtLikeNumber);
            txtTen = (TextView) view.findViewById(R.id.txtTenTips);
            txtTime = (TextView) view.findViewById(R.id.txtTimeTips);
            imgHinh = (ImageView) view.findViewById(R.id.imgHinhAnhTips);
        }
    }
    private RecyclerViewItemClickListener itemClickListener;

    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public MyTips getItem(int position) {
        if (position >= 0 && position < myTipsList.size()) {
            return myTipsList.get(position);
        }
        return null;
    }
}
