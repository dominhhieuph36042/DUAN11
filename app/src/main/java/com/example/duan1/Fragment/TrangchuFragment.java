package com.example.duan1.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.duan1.R;


public class TrangchuFragment extends Fragment {



    public TrangchuFragment() {
        // Required empty public constructor
    }


Button btndonhang,btnsanpham,btnquanly,btnthongtin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);
        btndonhang = view.findViewById(R.id.btndonhang);
        btnsanpham = view.findViewById(R.id.btnsanpham);
        btnquanly = view.findViewById(R.id.btnquanlytk);
        btnthongtin = view.findViewById(R.id.btnthongtintk);


        return view;
    }




    public static TrangchuFragment newInstance(){
        TrangchuFragment fragment = new TrangchuFragment();
        return fragment;
}
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//       rcv_spKH.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//           @Override
//           public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//
//               FragmentManager manager = getActivity().getSupportFragmentManager();
//               manager.beginTransaction().replace(R.id.frame_container, CTSP_kh.newInstance()).commit();
//
//
//               return false;
//           }
//
//           @Override
//           public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//
//           }
//
//           @Override
//           public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//           }
//       });
//
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btndonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToolbarTitle("Quản lý Đơn hàng");
                FragmentManager manager = getActivity().getSupportFragmentManager();
               manager.beginTransaction().replace(R.id.frameLayout_home, HoaDonFragment.newInstance()).commit();

            }
        });

        btnsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToolbarTitle("Quản lý Sản phẩm");
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frameLayout_home, SanPhamFragment.newInstance()).commit();
            }
        });

        btnquanly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToolbarTitle("Quản lý tài khoản");
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frameLayout_home, QuanLyTKFragment.newInstance()).commit();
            }
        });

        btnthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToolbarTitle("Thông tin tài khoản");
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frameLayout_home, DoiMatKhauFragment.newInstance()).commit();
            }
        });
    }

    private void setToolbarTitle(String title) {
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle(title);
            }
        }
    }
}