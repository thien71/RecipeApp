package com.example.recipe_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.R;
import com.example.recipe_app.RecyclerViewItemClickListener;
import com.example.recipe_app.model.MyTips;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyTipsAdapter extends RecyclerView.Adapter<MyTipsAdapter.MyTipsViewHolder>{

    private List<MyTips> myTipsList;
    private RecyclerViewItemClickListener itemClickListener;

    public MyTipsAdapter(List<MyTips> binhLuanList) {
        this.myTipsList = binhLuanList;
    }
    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
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
        String tieuDe = myTips.getTen();
        String noiDung = myTips.getContent();
        String thoiGian = myTips.getTime();

        holder.txtNoiDung.setText(noiDung);
        holder.txtTen.setText(tieuDe);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            Date ngayDanhGiaDate = sdf.parse(thoiGian);
            Date gioHienTai = new Date();

            long diffInMilliseconds = gioHienTai.getTime() - ngayDanhGiaDate.getTime();
            long diffInHours = diffInMilliseconds / (60 * 60 * 1000);

            holder.txtTime.setText(String.valueOf(diffInHours) + " giờ trước");
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
        return myTipsList.size();
    }

    public class MyTipsViewHolder extends RecyclerView.ViewHolder {
//        ImageView imgHinh;
        TextView txtTen, txtTime, txtNoiDung;
        public MyTipsViewHolder(@NonNull View view) {
            super(view);

            txtNoiDung = (TextView) view.findViewById(R.id.txtContent);
            txtTen = (TextView) view.findViewById(R.id.txtTenTips);
            txtTime = (TextView) view.findViewById(R.id.txtTimeTips);
        }
    }

    public MyTips getItem(int position) {
        if (position >= 0 && position < myTipsList.size()) {
            return myTipsList.get(position);
        }
        return null;
    }
}
