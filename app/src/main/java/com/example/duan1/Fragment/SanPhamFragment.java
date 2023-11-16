package com.example.duan1.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Adapter.HangSpinnerAdapter;
import com.example.duan1.Adapter.SanPhamAdapter;
import com.example.duan1.DAO.DaoHang;
import com.example.duan1.DAO.DaoSanPham;
import com.example.duan1.Model.Hang;
import com.example.duan1.Model.SanPham;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class SanPhamFragment extends Fragment {

    RecyclerView rcv_sanpham;
    ArrayList<SanPham> lstSanPham;

    ArrayList<SanPham> Templs;
    ArrayList<Hang> lstHang;

    FloatingActionButton btn_add;
    static DaoSanPham sanPhamDao;
    SanPhamAdapter adapter;
    SanPham sanPham;
    DaoHang HangDAO;

    Hang hang;
    HangSpinnerAdapter spinnerAdapter;

    Dialog dialog;
    Spinner spinner_loaiSach;
    EditText edt_maSach,edt_tenSach,edt_giaThue,search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_san_pham, container, false);
    }
}