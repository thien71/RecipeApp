package com.example.recipe_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.model.MyRated;
import com.example.recipe_app.R;
import com.example.recipe_app.RecyclerViewItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyRatedAdapter extends RecyclerView.Adapter<MyRatedAdapter.MyRatedViewHolder>{
    private List<MyRated> myRatedList;

    private RecyclerViewItemClickListener itemClickListener;

    public MyRatedAdapter(List<MyRated> danhGiaList) {
        this.myRatedList = danhGiaList;
    }
    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
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
        String ten = myRated.getTen();
        String thoiGian = myRated.getNgayDanhGia();
        String hinh = myRated.getHinh();

        holder.txtTen.setText(ten);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        try {
//            Date ngayDanhGiaDate = sdf.parse(thoiGian);
//            Date gioHienTai = new Date();
//
//            long diffInMilliseconds = gioHienTai.getTime() - ngayDanhGiaDate.getTime();
//            long diffInHours = diffInMilliseconds / (60 * 60 * 1000);
//
//            holder.txtTime.setText(String.valueOf(diffInHours) + " giờ trước");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        Picasso.get().load(hinh).into(holder.imgHinh);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (itemClickListener != null) {
//                    itemClickListener.onItemClick(holder.getAdapterPosition());
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return myRatedList.size();
    }

    public class MyRatedViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinh;
//        CircleImageView ibtnLikeorDislike;
        TextView txtTen, txtTime;
        public MyRatedViewHolder(@NonNull View view) {
            super(view);

            imgHinh = (ImageView) view.findViewById(R.id.imgHinhRated);
//            ibtnLikeorDislike = (CircleImageView) view.findViewById(R.id.ibtnLikeorDislike);
            txtTen = (TextView) view.findViewById(R.id.txtTenBaiDang);
            txtTime = (TextView) view.findViewById(R.id.txtTime);
        }
    }
    public MyRated getItem(int position) {
        if (position >= 0 && position < myRatedList.size()) {
            return myRatedList.get(position);
        }
        return null;
    }
}
