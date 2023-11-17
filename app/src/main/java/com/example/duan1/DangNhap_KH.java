package com.example.duan1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.DAO.DaoKhachHang;

public class DangNhap_KH extends AppCompatActivity {

    Button btnDN, btnDK, btnOut;
    EditText edTK, edMK;
    CheckBox chkKH;
    DaoKhachHang daoKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap_kh);

       btnOut = findViewById(R.id.btn_out_kh);
       btnDN = findViewById(R.id.btn_login_kh);
       btnDK = findViewById(R.id.btn_dk_kh);

       edTK = findViewById(R.id.txt_username_kh);
       edMK = findViewById(R.id.txt_password_kh);
       chkKH = findViewById(R.id.chk_rememberAccount_kh);

       daoKH = new DaoKhachHang(this);

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pas = pref.getString("PASSWORD", "");
        Boolean rem = pref.getBoolean("REMEMBER", false);

        edTK.setText(user);
        edMK.setText(pas);
        chkKH.setChecked(rem);

       btnDN.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String user = edTK.getText().toString();
               String pass= edMK.getText().toString();
               boolean kt = daoKH.dangNhap(user, pass);

               if(kt){
                   Toast.makeText(DangNhap_KH.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                   rememberLogin(user, pass, chkKH.isChecked());
                   startActivity(new Intent(DangNhap_KH.this, TrangChuKH.class));
               } else {
                   Toast.makeText(DangNhap_KH.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
               }
           }
       });

       btnOut.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(DangNhap_KH.this, luaChonND.class);
               startActivity(i);
           }
       });

       btnDK.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(DangNhap_KH.this, DangKy_KH.class);
               startActivity(i);
           }
       });
    }

    public void rememberLogin(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(!status){
            // xoa trang thai luu truoc do
            edit.clear();
        } else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        //lưu lại
        edit.commit();
    }
}