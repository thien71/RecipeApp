package com.example.recipe_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommunityAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Community> communityList;

    public CommunityAdapter(Context context, int layout, List<Community> communityList) {
        this.context = context;
        this.layout = layout;
        this.communityList = communityList;
    }

    @Override
    public int getCount() {
        return communityList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        ImageView imgHinh;
        CircleImageView civAvatar;
        ImageButton ibtnLuuLai, ibtnLike;
        TextView txtTenNguoi, txtTenMon, txtSoLike, txtLuuLai, txtSoBinhLuan, txtMoTa;
        LinearLayout linearSaveCommunity;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if(view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout, null);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imgHinhCommunity);
            holder.civAvatar = (CircleImageView) view.findViewById(R.id.civAvatarCommunity);
            holder.ibtnLike = (ImageButton) view.findViewById(R.id.ibtnLikeCommunity);
            holder.ibtnLuuLai = (ImageButton) view.findViewById(R.id.ibtnTymCommunity);
            holder.txtTenNguoi = (TextView) view.findViewById(R.id.txtTenNguoiCommunity);
            holder.txtTenMon = (TextView) view.findViewById(R.id.txtTenMonCommunity);
            holder.txtSoLike = (TextView) view.findViewById(R.id.txtSoLuongLikeCommunity);
            holder.txtSoBinhLuan = (TextView) view.findViewById(R.id.txtSoBinhLuanCommunity);
            holder.txtMoTa = (TextView) view.findViewById(R.id.txtMoTaCommunity);
            holder.txtLuuLai = (TextView) view.findViewById(R.id.txtTymCommunity);
            holder.linearSaveCommunity = (LinearLayout) view.findViewById(R.id.linearSaveCommunity);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Community community = communityList.get(position);
        holder.imgHinh.setImageResource(community.getHinh());
        holder.civAvatar.setImageResource(community.getAvatar());
        holder.ibtnLuuLai.setImageResource(community.getHinhIsSaved());
        holder.ibtnLike.setImageResource(community.getIsLiked());
        holder.txtTenNguoi.setText(community.getTenNguoi());
        holder.txtTenMon.setText(community.getTenMon());
        holder.txtSoLike.setText(community.getSoLike()+"");
        holder.txtLuuLai.setText(community.getTextIsSaved());
        holder.txtSoBinhLuan.setText(community.getSoBinhLuan()+"");
        holder.txtMoTa.setText(community.getMoTa());

        holder.linearSaveCommunity.setOnClickListener(new View.OnClickListener() {
            boolean isTym = false;
            @Override
            public void onClick(View view) {
                if(isTym) {
                    holder.txtLuuLai.setText("Lưu lại");
                    holder.ibtnLuuLai.setImageResource(R.drawable.baseline_favorite_border_12);
                } else {
                    holder.txtLuuLai.setText("Đã lưu");
                    holder.ibtnLuuLai.setImageResource(R.drawable.baseline_favorite_12);
                }
                isTym = !isTym;
            }
        });

        return view;
    }
}
