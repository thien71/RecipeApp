package com.example.recipe_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.adapter.NguyenLieuAdapter;
import com.example.recipe_app.model.CongThuc;
import com.example.recipe_app.model.NguyenLieu;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.UaThichViewHolder>{
    private List<CongThuc> uaThichList;
    private RecyclerViewItemClickListener itemClickListener;

    public SavedAdapter(List<CongThuc> uaThichList) {
        this.uaThichList = uaThichList;
    }

    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }
    @NonNull
    @Override
    public UaThichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saved, parent, false);
        return new UaThichViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UaThichViewHolder holder, int position) {
        final CongThuc uaThich = uaThichList.get(position);

        String tieuDe = uaThich.getTieuDe();
        String duongDanHinhAnh = uaThich.getDuongDanHinhAnh();

        holder.txtTieuDe.setText(tieuDe);
        Picasso.get().load(duongDanHinhAnh).into(holder.imgHinh);

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
        return uaThichList.size();
    }

    public static class UaThichViewHolder extends RecyclerView.ViewHolder {
        TextView txtTieuDe;
        ImageView imgHinh;

        public UaThichViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTieuDe = itemView.findViewById(R.id.txtTenUaThich);
            imgHinh = itemView.findViewById(R.id.imgHinhAnhUaThich);
        }
    }
}
