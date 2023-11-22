package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.duan1.Adapter.GioHangAdapter;
import com.example.duan1.DAO.DaoGioHang;
import com.example.duan1.Model.IClickItemRCV;

import java.util.ArrayList;

public class GioHang extends AppCompatActivity {

    RecyclerView rcvGioHang;
    ArrayList<GioHang> lstGioHang;
    DaoGioHang daoGH;

    GioHangAdapter gioAdapter;

    int maSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

      rcvGioHang = findViewById(R.id.rcv_gioHang);
      rcvGioHang.setHasFixedSize(true);
      RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
      rcvGioHang.setLayoutManager(layoutManager);
      gioAdapter = new GioHangAdapter(getApplicationContext(), untils.mangGioHang, new IClickItemRCV() {
          @Override
          public void iclickItem(RecyclerView.ViewHolder viewHolder, int position, int type) {

          }
      });
      rcvGioHang.setAdapter(gioAdapter);
    }
}