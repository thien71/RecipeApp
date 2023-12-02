package com.example.recipe_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.R;
import com.example.recipe_app.RecyclerViewItemClickListener;
import com.example.recipe_app.model.Cookbooks;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CookbooksAdapter extends RecyclerView.Adapter<CookbooksAdapter.DanhMucViewHolder>{
    private List<Cookbooks> danhMucList;
    private RecyclerViewItemClickListener itemClickListener;

    public CookbooksAdapter(List<Cookbooks> danhMucList) {
        this.danhMucList = danhMucList;
    }

    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public DanhMucViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cookbooks, parent, false);
        return new DanhMucViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhMucViewHolder holder, int position) {
        final Cookbooks danhMuc = danhMucList.get(position);

        String ten = danhMuc.getTen();
        String hinhAnh = danhMuc.getHinh();
        int soLuong = danhMuc.getSoLuong();

        holder.txtTenDanhMuc.setText(ten);
        holder.txtSoLuong.setText(soLuong + "");
        Picasso.get().load(hinhAnh).into(holder.imgHinh);

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
        return danhMucList.size();
    }


    public static class DanhMucViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenDanhMuc, txtSoLuong;
        ImageView imgHinh;

        public DanhMucViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenDanhMuc = itemView.findViewById(R.id.txtTen);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            imgHinh = itemView.findViewById(R.id.imgHinhAnh);
        }
    }
}
