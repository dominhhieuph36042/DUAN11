package com.example.duan1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Model.Item;
import com.example.duan1.R;

import java.util.List;

public class ChiTietHoaDonAdapter extends RecyclerView.Adapter<ChiTietHoaDonAdapter.ViewHolder> {
    List<Item> lstItem;

    public ChiTietHoaDonAdapter(List<Item> lstItem) {
        this.lstItem = lstItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemchi_tiet_don_hang, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = lstItem.get(position);
        holder.tvTenSP.setText(item.getTensp() + "");
    }

    @Override
    public int getItemCount() {
        return lstItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imagesChitiet;
        TextView tvTenSP, tvSoLuong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagesChitiet = itemView.findViewById(R.id.item_imgchiTiet);
            tvTenSP = itemView.findViewById(R.id.item_tenSpChiTiet);
            tvSoLuong  = itemView.findViewById(R.id.item_soLuongSpChiTiet);
        }
    }
}
