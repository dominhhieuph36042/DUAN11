package com.example.duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.DAO.DaoKhachHang;
import com.example.duan1.Model.HoaDon;
import com.example.duan1.Model.KhachHang;
import com.example.duan1.R;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {

    private RecyclerView.RecycledViewPool viewpool = new RecyclerView.RecycledViewPool();
    Context context;
    List<HoaDon> lstHoaDon;

    public DonHangAdapter(Context context, List<HoaDon> lstHoaDon) {
        this.context = context;
        this.lstHoaDon = lstHoaDon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdon_hang, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DaoKhachHang daoKH = new DaoKhachHang(context);
       HoaDon hoaDon = lstHoaDon.get(position);

        holder.txtdonHang.setText("Đơn hàng:" + hoaDon.getIdDonHang());

        KhachHang kh = daoKH.getID(hoaDon.getMaKH());
        holder.txtMakh.setText("Mã khách hàng:" + kh.getMaKH());
        holder.txtTongTien.setText("Tổng tiền:" + hoaDon.getTongTien() +" đ");

        holder.txtNgayDat.setText("Ngày đặt:" + hoaDon.getNgayDat());
    }

    @Override
    public int getItemCount() {
        return lstHoaDon != null ? lstHoaDon.size() : 0 ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView  txtdonHang, txtMakh, txtTongTien, txtNgayDat;
        RecyclerView reChiTiet;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdonHang = itemView.findViewById(R.id.idDonHang);
            txtMakh = itemView.findViewById(R.id.idKhachHang);
            txtTongTien = itemView.findViewById(R.id.tongTien);
            txtNgayDat = itemView.findViewById(R.id.ngayDat);
        }
    }
}
