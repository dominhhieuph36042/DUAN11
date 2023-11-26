package com.example.duan1.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.duan1.Adapter.ThongTinTkAdapter;
import com.example.duan1.DAO.DAOQuanTriVien;
import com.example.duan1.Model.QuanTriVien;
import com.example.duan1.R;

import java.util.ArrayList;


public class DoiMatKhauFragment extends Fragment {

    ListView lvQTV;
    ArrayList<QuanTriVien> list;

    Dialog dialog;
    EditText edUser,edHoTen,edPass,edRePass;

    EditText edUser1,edHoTen1,edPass1,edRePass1;
    Button btnSaveTT,btnCancleTT,btndoimatkhau;
    Button btnSaveTT1,btnCancleTT1;


    static DAOQuanTriVien dao;
    ThongTinTkAdapter adapter;
    QuanTriVien item;

    public DoiMatKhauFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_doi_mat_khau,container,false);
        lvQTV = v.findViewById(R.id.lvQTV);
//        fab = v.findViewById(R.id.fab);
        dao = new DAOQuanTriVien(getActivity());
        btndoimatkhau = v.findViewById(R.id.btn_doimatkhau);
        capNhatLv();



        btndoimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(),1);
            }
        });

        lvQTV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);// update
                return false;
            }
        });
        return v;
    }
    void capNhatLv() {
        list = (ArrayList<QuanTriVien>) dao.getAll();
        adapter = new ThongTinTkAdapter(getActivity(), this, list);
        lvQTV.setAdapter(adapter);
    }



    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_thongtintk);
        edUser = dialog.findViewById(R.id.edUser);
        edHoTen = dialog.findViewById(R.id.edHoTen);
        edPass = dialog.findViewById(R.id.edPass);
        edRePass = dialog.findViewById(R.id.edRePass);
        btnCancleTT = dialog.findViewById(R.id.btnCancelTT);
        btnSaveTT = dialog.findViewById(R.id.btnSaveTT);

        if (type != 0 && item != null){

            edUser.setText(item.getMaQTV());
            edHoTen.setText(item.getHoTen());
            edPass.setText(item.getMatKhau());
            edRePass.setText(item.getMatKhau());
        }

        btnCancleTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSaveTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new QuanTriVien();

                item.setMaQTV(edUser.getText().toString());
                item.setHoTen(edHoTen.getText().toString());
                item.setMatKhau(edPass.getText().toString());
                if(validate() > 0){
                    if (type == 0){
                        if (dao.insert(item) > 0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            edUser.setText("");
                            edHoTen.setText("");
                            edPass.setText("");
                            edRePass.setText("");
                        }else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.setMaQTV(edUser.getText().toString());
                        if (dao.update(item) > 0){
                            Toast.makeText(context, "Đổi thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Đổi thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }



    public int validate() {
        int check = 1;
        if (edUser.getText().length() == 0 || edHoTen.getText().length() == 0 || edPass.getText().length() == 0 || edRePass.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            String pass = edPass.getText().toString();
            String repass = edRePass.getText().toString();
            if (!pass.equals(repass)){
                Toast.makeText(getActivity(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }


}