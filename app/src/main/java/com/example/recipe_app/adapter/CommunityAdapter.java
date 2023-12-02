package com.example.recipe_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.R;
import com.example.recipe_app.model.BaiDangCongDong;
import com.example.recipe_app.model.NguoiDung;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.BaiDangCongDongViewHolder> {
    private List<BaiDangCongDong> baiDangCongDongList;

    public CommunityAdapter(List<BaiDangCongDong> data) {
        this.baiDangCongDongList = data;
    }

    public BaiDangCongDongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_community, parent, false);
        return new BaiDangCongDongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaiDangCongDongViewHolder holder, int position) {
        BaiDangCongDong baiDang = baiDangCongDongList.get(position);
        NguoiDung nguoiDung = baiDang.getNguoiDung();

        if (nguoiDung != null) {
            String tenNguoiDung = nguoiDung.getTenNguoiDung();
            String avatar = nguoiDung.getAvatar();
            String tieuDe = baiDang.getTieuDe();
            String noiDung = baiDang.getNoiDung();
            String hinh = baiDang.getHinhAnh();
            int soLike = baiDang.getSoLike();
            int soBinhLuan = baiDang.getSoBinhLuan();

            holder.txtTenNguoiDung.setText(tenNguoiDung);
            holder.txtTenTieuDe.setText(tieuDe);
            holder.txtNoiDung.setText(noiDung);
            holder.txtSoLike.setText(String.valueOf(soLike));
            holder.txtSoBinhLuan.setText(String.valueOf(soBinhLuan));

            Picasso.get().load(hinh).into(holder.imgHinh);
            Picasso.get().load(avatar).into(holder.cirAvatar);
        }

    }
    @Override
    public int getItemCount() {
        return baiDangCongDongList.size();
    }

    public static class BaiDangCongDongViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenNguoiDung, txtTenTieuDe, txtNoiDung, txtSoLike, txtSoBinhLuan;
        CircleImageView cirAvatar;
        ImageView imgHinh;

        public BaiDangCongDongViewHolder(View itemView) {
            super(itemView);
            txtTenNguoiDung = itemView.findViewById(R.id.txtTenNguoiDungCommunity);
            txtTenTieuDe = itemView.findViewById(R.id.txtTenTieuDeCommunity);
            txtNoiDung = itemView.findViewById(R.id.txtNoiDungCommunity);
            txtSoLike = itemView.findViewById(R.id.txtSoLikeCommunity);
            txtSoBinhLuan = itemView.findViewById(R.id.txtSoBinhLuanCommunity);

            imgHinh = itemView.findViewById(R.id.imgHinhCommunity);
            cirAvatar = itemView.findViewById(R.id.civAvatarCommunity);
        }
    }
}
