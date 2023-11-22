package com.example.duan1.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.DAO.DaoSanPham;
import com.example.duan1.Model.CTSanPham;
import com.example.duan1.Model.GioHang;
import com.example.duan1.Model.IClickItemRCV;
import com.example.duan1.Model.SanPham;
import com.example.duan1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {

    private Context context;
    private List<GioHang> lstGioHang;
    DaoSanPham spDao;
    IClickItemRCV clickItemRCV;

    public GioHangAdapter(Context context, List<GioHang> lstGioHang, IClickItemRCV clickItemRCV) {
        this.context = context;
        this.lstGioHang = lstGioHang;
        this.clickItemRCV = clickItemRCV;
    }

    @NonNull
    @Override
    public GioHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.ViewHolder holder, int position) {
       GioHang gh = lstGioHang.get(position);
       holder.tvmaSP.setText(gh.getTenSP());
       holder.tvTenSP.setText(gh.getTenSP());
       holder.tvHangSP.setText(gh.getHangSP());
       holder.tvGia.setText("Giá:" + gh.getGia() + "đ" );
       holder.tvSoLuong.setText(gh.getSoluong() + " ");

    }

    @Override
    public int getItemCount() {
       return lstGioHang != null ? lstGioHang.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvmaSP, tvTenSP, tvHangSP, tvGia, tvSoLuong;
        ImageView btnDelete, images;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvmaSP = itemView.findViewById(R.id.maSP_gioHang);
            tvTenSP = itemView.findViewById(R.id.tensanpham_gioHang);
            tvHangSP = itemView.findViewById(R.id.tenHang_gioHang);
            tvGia = itemView.findViewById(R.id.giatien_gioHang);
            tvSoLuong= itemView.findViewById(R.id.soLuong_gioHang);
            images = itemView.findViewById(R.id.images_gioHang);

            btnDelete = itemView.findViewById(R.id.btn_delete_gioHang);

        }
    }
}
