package com.example.recipe_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class CommunityFragment extends Fragment {
    ListView lvCommunity;
    ArrayList<Community> arrayCommunity;
    CommunityAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);

        lvCommunity = (ListView) view.findViewById(R.id.lvCommunity);

        arrayCommunity = new ArrayList<>();

        arrayCommunity.add(new Community(R.drawable.img_tom_nhoi_khoai_tay, R.drawable.img_avatar_panda_nam,
                "Minh Châu", "Tôm nhồi khoai tây", "Một bữa tối đặc biệt với tôm nhồi khoai tây. \uD83C\uDF64\uD83E\uDD54✨ \n" + "\n" + "#ẨmThựcSángTạo #TômKhoaiTâyNgonKhôngTưởng",
                1, 5, R.drawable.baseline_thumb_up_off_alt_12_black, "Lưu lại", R.drawable.baseline_favorite_border_12));
        arrayCommunity.add(new Community(R.drawable.img_mi_ong_pho_mai, R.drawable.img_avatar_3,
                "Sinh Ngô", "Mì ống", "Đêm nay, một phần mì ống phô mai thơm béo đã thổi bay mệt mỏi của ngày làm việc. \uD83E\uDDC0\uD83C\uDF5D✨\n" + "\n#MìỐngPhôMai #MónĂnGâyNghiện.",
                1, 2, R.drawable.baseline_thumb_up_off_alt_12_black, "Lưu lại", R.drawable.baseline_favorite_border_12));
        arrayCommunity.add(new Community(R.drawable.img_banh_hamburger, R.drawable.img_avatar_baohong,
                "Tuấn Nguyễn", "Bánh mì kẹp thịt", "Tối nay, một chiếc bánh hamburger ngon lành đã giúp xua tan mệt mỏi sau một ngày làm việc dài. \uD83C\uDF54\uD83C\uDF5F✨\n"+"\n#BánhHamburger #ThứcĂnBổDưỡng",
                4, 15, R.drawable.baseline_thumb_up_off_alt_12_black, "Lưu lại", R.drawable.baseline_favorite_border_12));
        arrayCommunity.add(new Community(R.drawable.img_sua_chuoi, R.drawable.img_avatar_soi,
                "Duy Vinh", "Sữa chuối", "Hôm nay, một ly sữa chuối mềm mịn đã làm dịu đi cảm xúc và mang lại sự thư giãn sau một ngày làm việc khá căng thẳng. \uD83C\uDF4C\uD83E\uDD5B✨\n" + "\n#SữaChuối #MónYêuCầu",
                2, 19, R.drawable.baseline_thumb_up_off_alt_12_black, "Lưu lại", R.drawable.baseline_favorite_border_12));

        adapter = new CommunityAdapter(getActivity(), R.layout.item_community, arrayCommunity);
        lvCommunity.setAdapter(adapter);

        lvCommunity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), arrayCommunity.get(position).getTenMon(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}