package com.example.recipe_app.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.R;
import com.example.recipe_app.RecyclerViewItemClickListener;
import com.example.recipe_app.model.BaiDangCongDong;
import com.example.recipe_app.model.NguoiDung;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.BaiDangCongDongViewHolder> {
    private List<BaiDangCongDong> baiDangCongDongList;
    public interface OnLikeButtonClickListener {
        void onLikeButtonClick(int position);
    }
    private OnLikeButtonClickListener likeButtonClickListener;
    public void setOnLikeButtonClickListener(OnLikeButtonClickListener listener) {
        likeButtonClickListener = listener;
    }

    private RecyclerViewItemClickListener listener;
    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

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

            holder.ibtnLike.setImageResource(baiDang.isLiked() ? R.drawable.baseline_thumb_up_alt_24 : R.drawable.baseline_thumb_up_off_alt_12_black);

            Picasso.get().load(hinh).into(holder.imgHinh);
            Picasso.get().load(avatar).into(holder.cirAvatar);
        }

        holder.ibtnLike.setOnClickListener(v -> {
            boolean isLiked = baiDang.isLiked();
            baiDang.setLiked(!isLiked);

            int likeCount = baiDang.getSoLike() + (baiDang.isLiked() ? 1 : -1);
            baiDang.setSoLike(likeCount);

            notifyItemChanged(position, "like");
        });

        holder.itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public void onBindViewHolder(BaiDangCongDongViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            if (payloads.contains("like")) {
                BaiDangCongDong baiDang = baiDangCongDongList.get(position);

                if (baiDang.isLiked()) {
                    holder.ibtnLike.setImageResource(R.drawable.baseline_thumb_up_alt_24);
                } else {
                    holder.ibtnLike.setImageResource(R.drawable.baseline_thumb_up_off_alt_12_black);
                }
                holder.txtSoLike.setText(String.valueOf(baiDang.getSoLike()));
            }
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
        ImageButton ibtnLike;

        public BaiDangCongDongViewHolder(View itemView) {
            super(itemView);
            txtTenNguoiDung = itemView.findViewById(R.id.txtTenNguoiDungCommunity);
            txtTenTieuDe = itemView.findViewById(R.id.txtTenTieuDeCommunity);
            txtNoiDung = itemView.findViewById(R.id.txtNoiDungCommunity);
            txtSoLike = itemView.findViewById(R.id.txtSoLikeCommunity);
            txtSoBinhLuan = itemView.findViewById(R.id.txtSoBinhLuanCommunity);

            ibtnLike = itemView.findViewById(R.id.ibtnLikeCommunity);

            imgHinh = itemView.findViewById(R.id.imgHinhCommunity);
            cirAvatar = itemView.findViewById(R.id.civAvatarCommunity);
        }
    }
}
