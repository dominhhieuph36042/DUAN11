package com.example.duan1.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


        if (lstGioHang != null && !lstGioHang.isEmpty() && position < lstGioHang.size()) {
            GioHang gh = lstGioHang.get(position);
            spDao = new DaoSanPham(context);
            SanPham sp = spDao.getID(String.valueOf(gh.getMaSP()));
            if (sp != null) {
                // Thực hiện các thao tác với đối tượng SanPham
                holder.tvmaSP.setText("Mã sản phẩm:" + sp.getMaSP());
                holder.tvTenSP.setText("Tên sản phẩm:" + sp.getTenSP());
                holder.tvGia.setText("Giá:" + sp.getGiaTien());
                holder.tvSoLuong.setText("Số lượng:" + gh.getSoluong());
            } else {
                Log.e("GioHangAdapter", "Lỗi: getID trả về null");
                // Hoặc sử dụng Toast để hiển thị thông báo lỗi
                Toast.makeText(context, "Lỗi: getID trả về null", Toast.LENGTH_SHORT).show();
            }


        } else {
            Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
       return lstGioHang != null ? lstGioHang.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvmaSP, tvTenSP, tvHangSP, tvGia, tvSoLuong;
        Button btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvmaSP = itemView.findViewById(R.id.maSP_gioHang);
            tvTenSP = itemView.findViewById(R.id.tensanpham_gioHang);
            tvHangSP = itemView.findViewById(R.id.tenHang_gioHang);
            tvGia = itemView.findViewById(R.id.giatien_gioHang);
            tvSoLuong= itemView.findViewById(R.id.soLuong_gioHang);

            btnDelete = itemView.findViewById(R.id.btnThanhToan);

        }
    }
}
