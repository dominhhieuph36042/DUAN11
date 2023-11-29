package com.example.duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.DAO.DaoSanPham;
import com.example.duan1.Model.IClickItemRCV;
import com.example.duan1.Model.SanPham;
import com.example.duan1.R;
import com.example.duan1.demo.Cart;

import java.util.List;

public class CTSanPhamAdapter extends RecyclerView.Adapter<CTSanPhamAdapter.ViewHolder> {

    private Context context;
    private List<SanPham> lstSP;


    DaoSanPham spDao;
    IClickItemRCV clickItemRCV;

    Cart addToCart;

    public CTSanPhamAdapter(Context context, List<SanPham> lstSP,IClickItemRCV itemRCV) {
        this.context = context;
        this.lstSP = lstSP;
        this.clickItemRCV = itemRCV;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ctsp, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham sp =lstSP.get(position);
        addToCart = new Cart();

        spDao = new DaoSanPham(context);


        holder.tvTenSanPham.setText("Tên sản phẩm:" + sp.getTenSP());
        holder.tvTenHang.setText("Tên hãng:" + sp.getTenHang());
        holder.tvGia.setText("Giá:" + String.valueOf(sp.getGiaTien()));
        holder.tvMoTa.setText("Mô tả:" + sp.getMoTa());


    }

    @Override
    public int getItemCount() {
         return lstSP != null ? lstSP.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenSanPham, tvTenHang, tvGia, tvMoTa;

        Button btnAddGioHang;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSanPham = itemView.findViewById(R.id.tvTenCTSP_kh);
            tvTenHang = itemView.findViewById(R.id.tvHangCTSP_kh);
            tvGia = itemView.findViewById(R.id.tvGiaCTSP_kh);
            tvMoTa = itemView.findViewById(R.id.tvMoTaCTSP_kh);
            btnAddGioHang = itemView.findViewById(R.id.btnAddGioHang);
        }
    }
}
