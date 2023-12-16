package com.example.recipe_app.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.R;
import com.example.recipe_app.model.BaiDangCongDong;
import com.example.recipe_app.model.BinhLuan;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BinhLuanAdapter extends RecyclerView.Adapter<BinhLuanAdapter.BinhLuanViewHolder>{
    public interface OnItemLongClickListener {
        void onItemLongClick(int position, int userId, int maBinhLuan);
    }
    private List<BinhLuan> binhLuanList;
    private OnItemLongClickListener itemLongClickListener;

    public BinhLuanAdapter(List<BinhLuan> binhLuanList) {
        this.binhLuanList = binhLuanList;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.itemLongClickListener = listener;
    }

    @NonNull
    @Override
    public BinhLuanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_binh_luan, parent, false);
        return new BinhLuanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BinhLuanViewHolder holder, int position) {
        BinhLuan binhLuan = binhLuanList.get(position);

        String avatar = binhLuan.getAvatar();
        String ten = binhLuan.getTenNguoiDung();
        String noiDung = binhLuan.getNoiDungBinhLuan();

        holder.txtTenNguoiDung.setText(ten);
        holder.txtNoiDungBinhLuan.setText(noiDung);
        Picasso.get().load(avatar).into(holder.cirAvatar);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemLongClickListener != null) {
                    itemLongClickListener.onItemLongClick(holder.getAdapterPosition(), binhLuan.getMaNguoiDung(), binhLuan.getMaBinhLuan());
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return binhLuanList.size();
    }

    public static class BinhLuanViewHolder extends RecyclerView.ViewHolder {
        CircleImageView cirAvatar;
        TextView txtTenNguoiDung, txtNoiDungBinhLuan;

        public BinhLuanViewHolder(@NonNull View itemView) {
            super(itemView);
            cirAvatar = itemView.findViewById(R.id.cirAvatarBinhLuan);
            txtTenNguoiDung = itemView.findViewById(R.id.txtTenNguoiDungBinhLuan);
            txtNoiDungBinhLuan = itemView.findViewById(R.id.txtNoiDungBinhLuan);
        }
    }
}
