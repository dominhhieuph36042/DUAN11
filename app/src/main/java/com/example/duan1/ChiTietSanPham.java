package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1.DAO.DaoSanPham;
import com.example.duan1.Model.GioHang;
import com.example.duan1.Model.SanPham;
import com.squareup.picasso.Picasso;

public class ChiTietSanPham extends AppCompatActivity {

    int maSP;
//test
 //test2
    //test3
    TextView tvTenSP, tvHang, tvMoTa, tvGia;
    ImageView images;

    Button btnAddGioHang;

    SanPham sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        Intent i =  getIntent();
        maSP = i.getIntExtra("id", 0);
        DaoSanPham daoSp = new DaoSanPham(this);
        sp = daoSp.selectID(maSP);

        tvTenSP = findViewById(R.id.tvTenCTSP_kh);
        tvHang = findViewById(R.id.tvHangCTSP_kh);
        tvGia = findViewById(R.id.tvGiaCTSP_kh);
        tvMoTa = findViewById(R.id.tvMoTaCTSP_kh);
        images  = findViewById(R.id.imgCTSP);
        btnAddGioHang = findViewById(R.id.btnAddGioHang);

        tvTenSP.setText("Tên sản phẩm:" + sp.getTenSP().toString());
        tvHang.setText("Tên hãng:"+sp.getTenHang().toString());
        tvGia.setText("Giá:" + String.valueOf(sp.getGiaTien()));
        tvMoTa.setText("Mô tả:"+sp.getMoTa().toString());
//        images.setImageResource(Integer.parseInt(sp.getImages()));
        Picasso.get().load(sp.getImages()).into(images);


      initControl();
    }

    private void initControl() {
        btnAddGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theGioHang();
            }
        });
    }

    private void theGioHang() {
        if(untils.mangGioHang.size() > 0){
             boolean flag = false;
            int soLuong = 1;
            for (int i = 0; i< untils.mangGioHang.size(); i++){
                if(untils.mangGioHang.get(i).getMaSP() == sp.getMaSP()){
                    flag = true;
                }
            }
            if(flag == false){
                int gia = sp.getGiaTien() * soLuong;

                GioHang gioHang = new GioHang();
                gioHang.setGia(gia);
                gioHang.setSoluong(soLuong);
                gioHang.setMaSP(sp.getMaSP());
                gioHang.setTenSP(sp.getTenSP());
                gioHang.setHangSP(sp.getTenHang());

                untils.mangGioHang.add(gioHang);
            }

        } else {
            int soLuong = 1;
            int gia = sp.getGiaTien() * soLuong;

            GioHang gioHang = new GioHang();
            gioHang.setGia(gia);
            gioHang.setSoluong(soLuong);
            gioHang.setMaSP(sp.getMaSP());
            gioHang.setTenSP(sp.getTenSP());
            gioHang.setHangSP(sp.getTenHang());

            untils.mangGioHang.add(gioHang);
        }
    }


}