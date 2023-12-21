package com.example.recipe_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe_app.adapter.BinhLuanAdapter;
import com.example.recipe_app.model.BaiDangCongDong;
import com.example.recipe_app.model.BinhLuan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailCommunityActivity extends AppCompatActivity {
    TextView txtTenNguoiDung, txtTenTieuDe, txtNoiDung, txtSoLike, txtSoBinhLuan, txtTenMon;
    EditText edtBinhLuan;
    CircleImageView cirAvatar;
    ImageView imgHinh;
    ImageButton ibtnBack, ibtnGuiBinhLuan, ibtnLike;
    RecyclerView rcvBinhLuan;
    List<BinhLuan> binhLuanList;
    BinhLuanAdapter binhLuanAdapter;
    private int maBaiDang;
    private int maNguoiDung;
    private int maBinhLuanToRemove;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int selectedPosition = -1;
    private static final int MENU_EDIT = 0;
    private static final int MENU_DELETE = 1;

    private int soBinhLuan;
    boolean isLike = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_community);

        Mapping();
        init();

        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ibtnGuiBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noiDungBinhLuan = edtBinhLuan.getText().toString().trim();
                addNewComment(noiDungBinhLuan);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadComments();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        ibtnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLike) {
                    Intent intent = getIntent();
                    if (intent != null) {
                        BaiDangCongDong baiDangItem = (BaiDangCongDong) intent.getSerializableExtra("baiDangItem");
                        txtSoLike.setText(String.valueOf(baiDangItem.getSoLike()));
                    }
                    ibtnLike.setImageResource(R.drawable.baseline_thumb_up_off_alt_12_black);
                } else {
                    Intent intent = getIntent();
                    if (intent != null) {
                        BaiDangCongDong baiDangItem = (BaiDangCongDong) intent.getSerializableExtra("baiDangItem");
                        txtSoLike.setText(String.valueOf(baiDangItem.getSoLike() + 1));
                    }
                    ibtnLike.setImageResource(R.drawable.baseline_thumb_up_alt_24);
                }
                isLike = !isLike;
            }
        });
    }

    private void setupRecyclerView() {
        LinearLayoutManager communityLayoutManager = new LinearLayoutManager(this);
        rcvBinhLuan.setLayoutManager(communityLayoutManager);

        binhLuanAdapter = new BinhLuanAdapter(binhLuanList);
        rcvBinhLuan.setAdapter(binhLuanAdapter);

        binhLuanAdapter.setOnItemLongClickListener(new BinhLuanAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position, int userId, int maBinhLuan) {
                if (maNguoiDung == userId) {
                    openContextMenu(rcvBinhLuan);
                    selectedPosition = position;
                    maBinhLuanToRemove = maBinhLuan;
                }
            }
        });

        registerForContextMenu(rcvBinhLuan);

        rcvBinhLuan.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                getMenuInflater().inflate(R.menu.context_menu, menu);
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_edit) {
            String oldContent = binhLuanList.get(selectedPosition).getNoiDungBinhLuan();
            showEditCommentDialog(oldContent);
            return true;
        } else if (item.getItemId() == R.id.menu_delete) {
            xoaBinhLuan(selectedPosition);
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }
    private void showEditCommentDialog(String oldContent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sửa bình luận");

        final EditText editText = new EditText(this);
        editText.setText(oldContent);
        builder.setView(editText);

        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String noiDungMoi = editText.getText().toString();
                suaNoiDungBinhLuan(maBinhLuanToRemove, noiDungMoi);
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
    private void suaNoiDungBinhLuan(int maBinhLuan, String noiDungMoi) {
        DatabaseReference nguoiDungRef = FirebaseDatabase.getInstance().getReference("NguoiDung").child(String.valueOf(maNguoiDung)).child("BinhLuan");

        nguoiDungRef.orderByChild("maBinhLuan").equalTo(maBinhLuan).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot binhLuanSnapshot : dataSnapshot.getChildren()) {
                    binhLuanSnapshot.getRef().child("noiDungBinhLuan").setValue(noiDungMoi);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        loadComments();
//        binhLuanList.get(maBinhLuan).setNoiDungBinhLuan(noiDungMoi);
//        binhLuanAdapter.notifyItemChanged(maBinhLuan);
    }

    private void xoaBinhLuan(int position) {
        if (position != -1 && position < binhLuanList.size() && maBinhLuanToRemove != -1) {
            binhLuanList.remove(position);
            binhLuanAdapter.notifyItemRemoved(position);

            DatabaseReference nguoiDungRef = FirebaseDatabase.getInstance().getReference("NguoiDung").child(String.valueOf(maNguoiDung)).child("BinhLuan");
            nguoiDungRef.orderByChild("maBinhLuan").equalTo(maBinhLuanToRemove).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot binhLuanSnapshot : dataSnapshot.getChildren()) {
                        binhLuanSnapshot.getRef().removeValue();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

            int positionToRemove = findPositionByMaBinhLuan(maBinhLuanToRemove);
            if (positionToRemove != -1) {
                binhLuanList.remove(positionToRemove);
                binhLuanAdapter.notifyItemRemoved(positionToRemove);
            }

            DatabaseReference baiDangRef = FirebaseDatabase.getInstance().getReference("BaiDangCongDong").child(String.valueOf(maBaiDang));
            baiDangRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        long soBinhLuan = (long) dataSnapshot.child("soBinhLuan").getValue();

                        // Giảm số lượng bình luận đi 1 sau khi xóa
                        baiDangRef.child("soBinhLuan").setValue(soBinhLuan - 1);

                        //loadComments();

                        soBinhLuan--;
                        txtSoBinhLuan.setText(String.valueOf(soBinhLuan));
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });

            maBinhLuanToRemove = -1;
        }
    }

    private int findPositionByMaBinhLuan(int maBinhLuanToRemove) {
        for (int i = 0; i < binhLuanList.size(); i++) {
            BinhLuan binhLuan = binhLuanList.get(i);
            if (binhLuan.getMaBinhLuan() == maBinhLuanToRemove) {
                return i;
            }
        }
        return -1;
    }

    private void loadComments() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDung");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binhLuanList = new ArrayList<>();
                soBinhLuan = 0;
                for (DataSnapshot nguoiDungSnapshot : snapshot.getChildren()) {
                    DataSnapshot binhLuanSnapshot = nguoiDungSnapshot.child("BinhLuan");
                    for (DataSnapshot binhLuanItem : binhLuanSnapshot.getChildren()) {
                        int maBaiDangTrongNode = binhLuanItem.child("maBaiDang").getValue(Integer.class);
                        if (maBaiDangTrongNode == maBaiDang) {
                            int maNguoiDung = nguoiDungSnapshot.child("maNguoiDung").getValue(Integer.class);
                            String tenNguoiDung = nguoiDungSnapshot.child("tenNguoiDung").getValue(String.class);
                            String avatar = nguoiDungSnapshot.child("avatar").getValue(String.class);
                            String noiDungBinhLuan = binhLuanItem.child("noiDungBinhLuan").getValue(String.class);
                            int maBinhLuan = binhLuanItem.child("maBinhLuan").getValue(Integer.class);

                            BinhLuan binhLuan = new BinhLuan(maNguoiDung, maBinhLuan ,tenNguoiDung, avatar, noiDungBinhLuan);
                            binhLuanList.add(binhLuan);

                            soBinhLuan++;
                        }
                    }
                }
                txtSoBinhLuan.setText(String.valueOf(soBinhLuan));
                setupRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addNewComment(String noiDungBinhLuan) {
        if (!noiDungBinhLuan.isEmpty()) {
            DatabaseReference nguoiDungRef = FirebaseDatabase.getInstance().getReference("NguoiDung").child(String.valueOf(maNguoiDung));
            nguoiDungRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String tenNguoiDung = dataSnapshot.child("tenNguoiDung").getValue(String.class);
                        String avatar = dataSnapshot.child("avatar").getValue(String.class);

                        long soLuongBinhLuan = dataSnapshot.child("BinhLuan").getChildrenCount();
                        int maBinhLuan = (int) (soLuongBinhLuan + 1);

                        BinhLuan binhLuanMoi = new BinhLuan(maBaiDang, maBinhLuan, noiDungBinhLuan);

                        DatabaseReference binhLuanMoiRef = nguoiDungRef.child("BinhLuan").child(String.valueOf(maBinhLuan-1));
                        binhLuanMoiRef.setValue(binhLuanMoi);

                        DatabaseReference baiDangRef = FirebaseDatabase.getInstance().getReference("BaiDangCongDong").child(String.valueOf(maBaiDang));
                        baiDangRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    long soBinhLuan = (long) dataSnapshot.child("soBinhLuan").getValue();

                                    baiDangRef.child("soBinhLuan").setValue(soBinhLuan + 1);

                                    BinhLuan binhLuan = new BinhLuan(tenNguoiDung, avatar, noiDungBinhLuan);
                                    binhLuanList.add(binhLuan);
                                    binhLuanAdapter.notifyItemInserted(binhLuanList.size() - 1);

                                    loadComments();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        edtBinhLuan.setText("");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void init() {
        binhLuanList = new ArrayList<>();
        setupRecyclerView();

        Intent intent = getIntent();
        if (intent != null) {
            maBaiDang = intent.getIntExtra("maBaiDang", 1) + 1;
            maNguoiDung = intent.getIntExtra("maNguoiDung", 1);
            BaiDangCongDong baiDangItem = (BaiDangCongDong) intent.getSerializableExtra("baiDangItem");

            txtTenTieuDe.setText("Bài viết của " + baiDangItem.getNguoiDung().getTenNguoiDung());
            txtTenMon.setText(baiDangItem.getTieuDe());
            txtTenNguoiDung.setText(baiDangItem.getNguoiDung().getTenNguoiDung());
            txtNoiDung.setText(baiDangItem.getNoiDung());
            txtSoLike.setText(baiDangItem.getSoLike() + "");
            txtSoBinhLuan.setText(baiDangItem.getSoBinhLuan()+"");

            Picasso.get().load(baiDangItem.getHinhAnh()).into(imgHinh);
            Picasso.get().load(baiDangItem.getNguoiDung().getAvatar()).into(cirAvatar);
        }
        loadComments();
    }

    private void Mapping() {
        txtTenNguoiDung = findViewById(R.id.txtTenNguoiDungDetailCommunity);
        txtTenMon = findViewById(R.id.txtTenMonDetailCommunity);
        txtNoiDung = findViewById(R.id.txtNoiDungDetailCommunity);
        txtSoLike = findViewById(R.id.txtSoLikeDetailCommunity);
        ibtnLike = findViewById(R.id.ibtnLikeDetailCommunity);

        txtSoBinhLuan = findViewById(R.id.txtSoBinhLuanDetailCommunity);

        txtTenTieuDe = findViewById(R.id.txtTenTieuDeDetailCommunity);

        imgHinh = findViewById(R.id.imgHinhDetailCommunity);
        cirAvatar = findViewById(R.id.civAvatarDetailCommunity);

        ibtnBack = findViewById(R.id.ibtnBackDetailCommunity);

        rcvBinhLuan = findViewById(R.id.rcvBinhLuanCommunity);

        edtBinhLuan = findViewById(R.id.edtBinhLuan);
        ibtnGuiBinhLuan = findViewById(R.id.ibtnGuiBinhLuan);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshDetailCommunity);

    }
}
