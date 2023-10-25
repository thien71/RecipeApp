package com.example.recipe_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyRatedAdapter extends RecyclerView.Adapter<MyRatedAdapter.MyRatedViewHolder>{
    private List<MyRated> myRatedList;

    public void setMyRatedList(List<MyRated> myRatedList) {
        this.myRatedList = myRatedList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyRatedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_rated, parent, false);

        return new MyRatedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRatedViewHolder holder, int position) {
        MyRated myRated = myRatedList.get(position);
        if(myRated == null) {
            return;
        }
        holder.imgHinh.setImageResource(myRated.getHinh());
        holder.ibtnLikeorDislike.setImageResource(myRated.getLikeOrDislike());
        holder.txtTen.setText(myRated.getTen());
        holder.txtTime.setText(myRated.getTime());

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
        if(myRatedList != null) {
            return myRatedList.size();
        }
        return 0;
    }

    public class MyRatedViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinh;
        CircleImageView ibtnLikeorDislike;
        TextView txtTen, txtTime;
        public MyRatedViewHolder(@NonNull View view) {
            super(view);

            imgHinh = (ImageView) view.findViewById(R.id.imgHinhRated);
            ibtnLikeorDislike = (CircleImageView) view.findViewById(R.id.ibtnLikeorDislike);
            txtTen = (TextView) view.findViewById(R.id.txtTen);
            txtTime = (TextView) view.findViewById(R.id.txtTime);
        }
    }

    private RecyclerViewItemClickListener itemClickListener;

    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public MyRated getItem(int position) {
        if (position >= 0 && position < myRatedList.size()) {
            return myRatedList.get(position);
        }
        return null;
    }
}
