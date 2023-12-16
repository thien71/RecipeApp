package com.example.recipe_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.adapter.HomeCommunityAdapter;
import com.example.recipe_app.adapter.HomeRecommendedAdapter;
import com.example.recipe_app.adapter.HomeTrendingAdapter;
import com.example.recipe_app.model.Community;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rcvHomeRecommended;
    private RecyclerView rcvHomeCommunity;
    private RecyclerView rcvHomeTreding;

    private HomeRecommendedAdapter homeRecommendedAdapter;
    private HomeCommunityAdapter homeCommunityAdapter;
    private HomeTrendingAdapter homeTrendingAdapter;

    private LinearLayout search_bar, home_chat;

    private RecyclerViewItemClickListener itemClickListener;
    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }
    public HomeFragment() {

    }

    public static HomeFragment newInstance(int maNguoiDung, int quyen) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt("maNguoiDung", maNguoiDung);
        args.putInt("quyen", quyen);
        fragment.setArguments(args);
        return fragment;
    }
    private int maNguoiDung;
    private int quyen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        if (getArguments() != null) {
            maNguoiDung = getArguments().getInt("maNguoiDung", 1);
            quyen = getArguments().getInt("quyen", 0);
        }

        rcvHomeRecommended = (RecyclerView) view.findViewById(R.id.rcvRecommended);
        rcvHomeCommunity = (RecyclerView) view.findViewById(R.id.rcvHomeCommunity);
        rcvHomeTreding = (RecyclerView) view.findViewById(R.id.rcvTrending);

        search_bar = (LinearLayout) view.findViewById(R.id.search_bar);
        home_chat = (LinearLayout) view.findViewById(R.id.home_chat);

        // Home Chat
        home_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChatboxActivity.class);
                startActivity(intent);
            }
        });

        //Home Search
        search_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        // Home Recommended
        homeRecommendedAdapter = new HomeRecommendedAdapter();
        rcvHomeRecommended.setLayoutManager(new GridLayoutManager (getActivity(), 4));
        rcvHomeRecommended.setFocusable(false);
        rcvHomeRecommended.setNestedScrollingEnabled(false);

        new LoadHomeRecommendedTask().execute();

//        homeRecommendedAdapter.setHomeRecommendedList(getHomeRecommendedList());
//        rcvHomeRecommended.setAdapter(homeRecommendedAdapter);
//        homeRecommendedAdapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                HomeRecommended selectHomeRecommended = homeRecommendedAdapter.getItem(position);
//                Intent intent = new Intent(getActivity(), DetailActivity.class);
//
//                intent.putExtra("maCongThuc", selectHomeRecommended.getMaCongThuc());
//
//                startActivity(intent);
//            }
//        });

        // Home Community
        homeCommunityAdapter = new HomeCommunityAdapter();
        LinearLayoutManager homeCommunityLinearLayoutManager = new LinearLayoutManager(getActivity());
        homeCommunityLinearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rcvHomeCommunity.setLayoutManager(homeCommunityLinearLayoutManager);
        rcvHomeCommunity.setFocusable(false);
        rcvHomeCommunity.setNestedScrollingEnabled(false);

        new LoadHomeCommunityTask().execute();


//        homeCommunityAdapter.setHomeCommunityList(getHomeCommunityList());
//        rcvHomeCommunity.setAdapter(homeCommunityAdapter);
//        homeCommunityAdapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Intent intent = new Intent(getActivity(), CommunityFragment.class);
//                startActivity(intent);
//            }
//        });

        // My Trending
        homeTrendingAdapter = new HomeTrendingAdapter();
        LinearLayoutManager homeTrendingLinearLayoutManager = new LinearLayoutManager(getActivity());
        homeTrendingLinearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rcvHomeTreding.setLayoutManager(homeTrendingLinearLayoutManager);
        rcvHomeTreding.setFocusable(false);
        rcvHomeTreding.setNestedScrollingEnabled(false);

//        new LoadHomeTrendingTask().execute();

//        homeTrendingAdapter.setHomeTrending(getHomeTrendingList(3));
//        rcvHomeTreding.setAdapter(homeTrendingAdapter);
//        homeTrendingAdapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Intent intent = new Intent(getActivity(), DetailActivity.class);
//                startActivity(intent);
//            }
//        });

        return view;
    }

    private class LoadHomeRecommendedTask extends AsyncTask<Void, Void, List<HomeRecommended>> {
        @Override
        protected List<HomeRecommended> doInBackground(Void... voids) {
            List<HomeRecommended> arrayHomeRecommendedList = new ArrayList<>();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("CongThuc");

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        int maCongThuc = snapshot.child("maCongThuc").getValue(Integer.class);
                        String hinh = snapshot.child("duongDanHinhAnh").getValue(String.class);
                        String tieuDe = snapshot.child("tieuDe").getValue(String.class);

                        HomeRecommended recommended = new HomeRecommended(maCongThuc, hinh, tieuDe);
                        arrayHomeRecommendedList.add(recommended);
                    }
                    homeRecommendedAdapter.setHomeRecommendedList(arrayHomeRecommendedList);
                    homeRecommendedAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

            return arrayHomeRecommendedList;
        }
        @Override
        protected void onPostExecute(List<HomeRecommended> homeRecommendedList) {
            super.onPostExecute(homeRecommendedList);

            homeRecommendedAdapter.setHomeRecommendedList(homeRecommendedList);
            rcvHomeRecommended.setAdapter(homeRecommendedAdapter);
            homeRecommendedAdapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    HomeRecommended selectHomeRecommended = homeRecommendedAdapter.getItem(position);
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("maCongThuc", selectHomeRecommended.getMaCongThuc());
                    startActivity(intent);
                }
            });
        }
    }

    private class LoadHomeCommunityTask extends AsyncTask<Void, Void, List<Community>> {
        @Override
        protected List<Community> doInBackground(Void... voids) {
            List<Community> arrayHomeCommunityList = new ArrayList<>();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("BaiDangCongDong");

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String hinh = snapshot.child("hinhAnh").getValue(String.class);
                        String tieuDe = snapshot.child("tieuDe").getValue(String.class);

                        Community community = new Community(hinh, tieuDe);
                        arrayHomeCommunityList.add(community);
                    }
                    homeCommunityAdapter.setHomeCommunityList(arrayHomeCommunityList);
                    homeCommunityAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

            return arrayHomeCommunityList;
        }
        @Override
        protected void onPostExecute(List<Community> homeCommunityList) {
            super.onPostExecute(homeCommunityList);
            homeCommunityAdapter.setHomeCommunityList(homeCommunityList);
            rcvHomeCommunity.setAdapter(homeCommunityAdapter);
            homeCommunityAdapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
                @Override
                public void onItemClick(int position) {
//                    CommunityFragment communityFragment = new CommunityFragment();
//                    FragmentManager fragmentManager = getSupportFragmentManager(); // sử dụng getSupportFragmentManager() nếu bạn đang sử dụng Support Library
//
//// Thay thế fragment hiện tại trong container layout (ví dụ, một FrameLayout có id là container_fragment)
//                    fragmentManager.beginTransaction()
//                            .replace(R.id.action_community, communityFragment)
//                            .addToBackStack(null) // Nếu bạn muốn thêm fragment vào back stack
//                            .commit();
                }
            });
        }
    }

//    private class LoadHomeTrendingTask extends AsyncTask<Void, Void, List<HomeRecommended>> {
//        @Override
//        protected List<HomeRecommended> doInBackground(Void... voids) {
//            List<HomeRecommended> arrayHomeTrendingList = new ArrayList<>();
//            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("CongThuc");
//
//            reference.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    List<HomeRecommended> fullList = new ArrayList<>();
//
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        String hinh = snapshot.child("duongDanHinhAnh").getValue(String.class);
//                        String tieuDe = snapshot.child("tieuDe").getValue(String.class);
//
//                        HomeRecommended recommended = new HomeRecommended(hinh, tieuDe);
//                        fullList.add(recommended);
//                    }
//                    int listSize = fullList.size();
//
//                    if (listSize > 0) {
//                        Random random = new Random();
//                        Set<Integer> selectedIndexes = new HashSet<>();
//
//                        while (selectedIndexes.size() < 3) {
//                            int randomIndex = random.nextInt(listSize);
//                            selectedIndexes.add(randomIndex);
//                        }
//
//                        for (Integer index : selectedIndexes) {
//                            arrayHomeTrendingList.add(fullList.get(index));
//                        }
//                    }
//                    homeTrendingAdapter.setHomeTrending(arrayHomeTrendingList);
//                    rcvHomeTreding.setAdapter(homeTrendingAdapter);
//                    homeTrendingAdapter.notifyDataSetChanged();
//                }
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                }
//            });
//            return arrayHomeTrendingList;
//        }
//        @Override
//        protected void onPostExecute(List<HomeRecommended> homeTrendingList) {
//            super.onPostExecute(homeTrendingList);
//            homeTrendingAdapter.setHomeTrending(homeTrendingList);
//            rcvHomeTreding.setAdapter(homeTrendingAdapter);
//            homeTrendingAdapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
//                @Override
//                public void onItemClick(int position) {
//                    HomeRecommended selectedItem = homeTrendingList.get(position);
//                    Intent intent = new Intent(getActivity(), DetailActivity.class);
//                    Log.d("MACONGTHUC1", position + "");
//                    Log.d("MACONGTHUC2", selectedItem.getMaCongThuc() + "");
//                    intent.putExtra("maCongThuc", selectedItem.getMaCongThuc());
//                    startActivity(intent);
//                }
//            });
//        }
//    }
//
//    private List<HomeRecommended> getHomeRecommendedList() {
//        List<HomeRecommended> arrayHomeRecommendedList = new ArrayList<>();
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference reference = database.getReference("CongThuc");
//
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    int maCongThuc = snapshot.child("maCongThuc").getValue(Integer.class);
//                    String hinh = snapshot.child("duongDanHinhAnh").getValue(String.class);
//                    String tieuDe = snapshot.child("tieuDe").getValue(String.class);
//
//                    HomeRecommended recommended = new HomeRecommended(maCongThuc, hinh, tieuDe);
//                    arrayHomeRecommendedList.add(recommended);
//                }
//                homeRecommendedAdapter.setHomeRecommendedList(arrayHomeRecommendedList);
//                homeRecommendedAdapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        return arrayHomeRecommendedList;
//    }
//    private List<Community> getHomeCommunityList() {
//        List<Community> arrayHomeCommunityList = new ArrayList<>();
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference reference = database.getReference("BaiDangCongDong");
//
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
////                    int maBaiDang = snapshot.child("maBaiDang").getValue(Integer.class);
//                    String hinh = snapshot.child("hinhAnh").getValue(String.class);
//                    String tieuDe = snapshot.child("tieuDe").getValue(String.class);
//
//                    Community community = new Community(hinh, tieuDe);
//                    arrayHomeCommunityList.add(community);
//                }
//                homeCommunityAdapter.setHomeCommunityList(arrayHomeCommunityList);
//                homeRecommendedAdapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        return arrayHomeCommunityList;
//    }
//    private List<HomeRecommended> getHomeTrendingList(int soMuc) {
//        List<HomeRecommended> arrayHomeTrendingList = new ArrayList<>();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("CongThuc");
//        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                List<HomeRecommended> fullList = new ArrayList<>();
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    String hinh = snapshot.child("duongDanHinhAnh").getValue(String.class);
//                    String tieuDe = snapshot.child("tieuDe").getValue(String.class);
//
//                    HomeRecommended recommended = new HomeRecommended(hinh, tieuDe);
//                    fullList.add(recommended);
//                }
//                int listSize = fullList.size();
//
//                if (listSize > 0) {
//                    Random random = new Random();
//                    Set<Integer> selectedIndexes = new HashSet<>();
//
//                    while (selectedIndexes.size() < soMuc) {
//                        int randomIndex = random.nextInt(listSize);
//                        selectedIndexes.add(randomIndex);
//                    }
//
//                    for (Integer index : selectedIndexes) {
//                        arrayHomeTrendingList.add(fullList.get(index));
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });
//        return arrayHomeTrendingList;
//    }
}