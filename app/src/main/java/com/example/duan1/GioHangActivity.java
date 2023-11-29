package com.example.duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.duan1.Adapter.GioHangAdapter;
import com.example.duan1.DAO.DaoGioHang;
import com.example.duan1.DAO.DaoSanPham;
import com.example.duan1.Fragment.HoaDonFragment;
import com.example.duan1.Fragment.Home_KH_Fragment;
import com.example.duan1.Fragment.ThongTin_KH_Fragment;
import com.example.duan1.Model.EventBus.TinhTongEvent;
import com.example.duan1.Model.GioHang;
import com.example.duan1.Model.IClickItemRCV;
import com.example.duan1.Model.SanPham;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class GioHangActivity extends AppCompatActivity {


    BottomNavigationView bottm ;
    RecyclerView rcvGioHang;
    ArrayList<GioHang> lstGioHang;
    static DaoGioHang daoGH;

    GioHangAdapter gioAdapter;

    TextView tvTongTien;

    static DaoSanPham daoSanPham;

    int maSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        tvTongTien = findViewById(R.id.tvTongTienGioHang);

        lstGioHang = new ArrayList<>();
        daoSanPham = new DaoSanPham(this);
        daoGH = new DaoGioHang(this);


//       untils.mangGioHang = daoGH.getAll();

      rcvGioHang = findViewById(R.id.rcv_gioHang);

      RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
      rcvGioHang.setLayoutManager(layoutManager);
      gioAdapter = new GioHangAdapter(getApplicationContext(), untils.mangGioHang, new IClickItemRCV() {
          @Override
          public void iclickItem(RecyclerView.ViewHolder viewHolder, int position, int type) {

          }
      });
      rcvGioHang.setAdapter(gioAdapter);
        if(untils.mangMuaHang != null){
            untils.mangMuaHang.clear();
        }
       tinhTongTien();


    }

    private void tinhTongTien() {
        long tongTienSP =0;

        for(int i =0; i<untils.mangMuaHang.size(); i++){
            tongTienSP = tongTienSP + (untils.mangMuaHang.get(i).getGia() * untils.mangMuaHang.get(i).getSoluong());
        }
        tvTongTien.setText(String.valueOf(tongTienSP) + " đ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe (sticky = true, threadMode = ThreadMode.MAIN)
    public void eventTinhTong(TinhTongEvent event){
        if(event != null){
            tinhTongTien();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }

    
}