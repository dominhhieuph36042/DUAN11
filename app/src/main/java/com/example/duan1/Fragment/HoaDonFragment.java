package com.example.duan1.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Adapter.DonHangAdapter;
import com.example.duan1.DAO.DaoHoaDon;
import com.example.duan1.Model.HoaDon;
import com.example.duan1.R;
import com.example.duan1.untils;

import java.util.ArrayList;
import java.util.List;


public class HoaDonFragment extends Fragment {

    RecyclerView rcvHoaDon;

    List<HoaDon> lstHoaDon;
    DaoHoaDon hoaDonDao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_hoa_don, container, false);

        lstHoaDon = new ArrayList<>();
        hoaDonDao = new DaoHoaDon(getContext());

        lstHoaDon = hoaDonDao.getAll();
        rcvHoaDon = v.findViewById(R.id.rcv_HoaDon);
        rcvHoaDon.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        DonHangAdapter adapter = new DonHangAdapter(getContext(), untils.hoadonAdmin);
        rcvHoaDon.setAdapter(adapter);
//        rcvHoaDon = v.findViewById(R.id.rcv_donHang);
//        rcvHoaDon.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//        DonHangAdapter adapter = new DonHangAdapter(getContext());
//        rcvHoaDon.setAdapter(adapter);
        return v;
    }

    public static HoaDonFragment newInstance(){
        HoaDonFragment fragment = new HoaDonFragment();
        return fragment;
    }

}