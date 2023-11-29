package com.example.recipe_app.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.R;
import com.example.recipe_app.model.NguyenLieu;

import java.util.List;

public class NguyenLieuAdapter extends RecyclerView.Adapter<NguyenLieuAdapter.NguyenLieuViewHolder>{
    private List<NguyenLieu> nguyenLieuList;

    public NguyenLieuAdapter(List<NguyenLieu> nguyenLieuList) {
        this.nguyenLieuList = nguyenLieuList;
    }

    @NonNull
    @Override
    public NguyenLieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nguyen_lieu, parent, false);
        return new NguyenLieuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NguyenLieuViewHolder holder, int position) {
        NguyenLieu nguyenLieu = nguyenLieuList.get(position);

        String tenNguyenLieu = nguyenLieu.getTenNguyenLieu();
        String soLuong  = nguyenLieu.getSoLuong()+ " "+ nguyenLieu.getDonVi();

        holder.txtTenNguyenLieu.setText(tenNguyenLieu);
        holder.txtSoLuong.setText(soLuong);
    }

    @Override
    public int getItemCount() {
        return nguyenLieuList.size();
    }

    public static class NguyenLieuViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenNguyenLieu;
        TextView txtSoLuong;

        public NguyenLieuViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenNguyenLieu = itemView.findViewById(R.id.txtTenNguyenLieu);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuongNguyenLieu);
        }
    }
}
