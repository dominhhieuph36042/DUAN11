package com.example.duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.DAO.DaoSanPham;
import com.example.duan1.Model.IClickItemRCV;
import com.example.duan1.Model.SanPham;
import com.example.duan1.R;

import java.util.List;

public class SanPhamAdapter  extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder>{
    private Context context;
    private List<SanPham> lstSach;
   IClickItemRCV clickItemRCV;
    public SanPhamAdapter(Context context, List<SanPham> lstSach,IClickItemRCV itemRCV) {
        this.context = context;
        this.lstSach = lstSach;
        this.clickItemRCV = itemRCV;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DaoSanPham sanPham1 = new DaoSanPham(context);
        SanPham sanPham = lstSach.get(position);

        holder.sp_maSP.setText("Mã San Pham: " + sanPham.getMaSP());
        holder.sp_maHang.setText("Mã hang: " + sanPham.getMaHang());
        holder.sp_tensanpham.setText("Tên San pham: " + sanPham.getTenSP());
        holder.sp_phanloai.setText("Phan loai: " + sanPham.getPhanLoai());
        holder.sp_tinhtrang.setText("Tinh trang : " + sanPham.getTinhTrang());
        holder.sp_giatien.setText("Gia tien:"+ String.valueOf(sanPham.getGiaTien()));
        holder.sp_tinhtrang.setText("Tinh trang : " + sanPham.getTinhTrang());


        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItemRCV.iclickItem(holder, Integer.parseInt(sanPham.getMaSP()), 1);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItemRCV.iclickItem(holder, holder.getAdapterPosition(), 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstSach != null ? lstSach.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sp_maSP,sp_maHang,sp_tensanpham,sp_phanloai,sp_tinhtrang,sp_giatien,sp_trangthai;
        ImageButton btn_delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sp_maSP = itemView.findViewById(R.id.sp_maSP);
            sp_maHang = itemView.findViewById(R.id.sp_maHang);
            sp_tensanpham = itemView.findViewById(R.id.sp_tensanpham);
            sp_phanloai = itemView.findViewById(R.id.sp_phanloai);
            sp_tinhtrang = itemView.findViewById(R.id.sp_tinhtrang);
            sp_giatien = itemView.findViewById(R.id.sp_giatien);
            sp_trangthai = itemView.findViewById(R.id.sp_trangthai);
            btn_delete = itemView.findViewById(R.id.btn_delete);
        }
    }

}
