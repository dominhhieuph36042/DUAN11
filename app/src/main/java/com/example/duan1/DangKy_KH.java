package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.DAO.DaoKhachHang;

public class DangKy_KH extends AppCompatActivity {

    DaoKhachHang daoKH;
    Button btndk, btnOut_dk;
    EditText edHoTen, edDiaChi, edSDT, edTaiKhoan, edMatKhau, edConf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_kh);

        btndk = findViewById(R.id.btnDk_dk);
        btnOut_dk = findViewById(R.id.btnOut_dk);
        edHoTen = findViewById(R.id.edHoTen_dk);
        edDiaChi = findViewById(R.id.edDiaChi_dk);
        edSDT = findViewById(R.id.edSDT_dk);
        edTaiKhoan = findViewById(R.id.edTK_dk);
        edMatKhau = findViewById(R.id.edMK_dk);
        edConf = findViewById(R.id.edConf_dk);
        daoKH = new DaoKhachHang(this);

        btnOut_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DangKy_KH.this, DangNhap_KH.class);
                startActivity(i);
            }
        });

        btndk.setOnClickListener(new View.OnClickListener() {
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
                       Toast.makeText(DangKy_KH.this, "Hãy điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                   }  else if(pass.length() < 8){
                       Toast.makeText(DangKy_KH.this, "Mật khẩu phải lớn hơn 8 ký tự", Toast.LENGTH_SHORT).show();
                   }else if(!pass.equals(conf)){
                       Toast.makeText(DangKy_KH.this, "Trường nhập lại mật khẩu không khớp với trường mật khẩu!", Toast.LENGTH_SHORT).show();
                   }
                   else {
                       Toast.makeText(DangKy_KH.this, "Đắng ký thành công!", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(DangKy_KH.this, DangNhap_KH.class));
                   }
            }
        });
    }
}