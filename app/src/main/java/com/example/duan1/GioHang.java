package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.duan1.Adapter.GioHangAdapter;
import com.example.duan1.DAO.DaoGioHang;
import com.example.duan1.Model.EventBus.TinhTongEvent;
import com.example.duan1.Model.IClickItemRCV;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class GioHang extends AppCompatActivity {

    RecyclerView rcvGioHang;
    ArrayList<GioHang> lstGioHang;
    DaoGioHang daoGH;

    GioHangAdapter gioAdapter;

    TextView tvTongTien;
    GioHang gioHang;

    int maSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        tvTongTien = findViewById(R.id.tvTongTienGioHang);

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

        tinhTongTien();
    }

    private void tinhTongTien() {
        long tongTienSP =0;

        for(int i =0; i<untils.mangGioHang.size(); i++){
            tongTienSP = tongTienSP + (untils.mangGioHang.get(i).getGia() * untils.mangGioHang.get(i).getSoluong());
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

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void eventTinhTong(TinhTongEvent event){
        if(event != null){
            tinhTongTien();
        }
    }
}