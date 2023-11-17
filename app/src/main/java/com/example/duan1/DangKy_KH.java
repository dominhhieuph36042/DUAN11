package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1.DAO.DaoKhachHang;

public class DangKy_KH extends AppCompatActivity {


    EditText edHoTen, edDiaChi, edSDT, edTaiKhoan, edMatKhau, edConf;
    Button btnout, btnDK;

    DaoKhachHang daoKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_kh);

        edHoTen = findViewById(R.id.edHoTen_dk);
        edDiaChi = findViewById(R.id.edDiaChi_dk);
        edSDT = findViewById(R.id.edSDT_dk);
        edTaiKhoan = findViewById(R.id.edTaiKhoan_dk);
        edMatKhau = findViewById(R.id.edMatKhau_dk);
        edConf = findViewById(R.id.edConf_dk);
        btnDK = findViewById(R.id.btnDk_dk);
        btnout = findViewById(R.id.btnOut_dk);
        daoKH = new DaoKhachHang(this);

        btnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangKy_KH.this, DangNhap_KH.class));
            }
        });

        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String user = edTaiKhoan.getText().toString();
                String pass = edMatKhau.getText().toString();
                String conf = edConf.getText().toString();
                String hoTen = edHoTen.getText().toString();
                String diaChi = edDiaChi.getText().toString();
                String sdt = edSDT.getText().toString();

                boolean kt = daoKH.dangKy(user, pass, hoTen, diaChi, sdt);

                if(user.isEmpty() || pass.isEmpty() || conf.isEmpty() || hoTen.isEmpty() || diaChi.isEmpty() || sdt.isEmpty()){
                    Toast.makeText(DangKy_KH.this, "Hãy nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else if(pass.length() < 8){
                    Toast.makeText(DangKy_KH.this, "Mật khẩu phải lớn hơn 8 ký tự", Toast.LENGTH_SHORT).show();
                } else if(!pass.equals(conf)){
                    Toast.makeText(DangKy_KH.this, "Trường xác nhận lại mật khẩu và trường mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                } else if(kt) {
                    Toast.makeText(DangKy_KH.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DangKy_KH.this, DangNhap_KH.class));
                }

            }
        });

    }
}