package com.example.duan1.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.DAO.DaoHoaDon;
import com.example.duan1.DAO.DaoKhachHang;
import com.example.duan1.DAO.DaoSanPham;
import com.example.duan1.Model.HoaDon;
import com.example.duan1.Model.KhachHang;
import com.example.duan1.Model.SanPham;
import com.example.duan1.R;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {

    private RecyclerView.RecycledViewPool viewpool = new RecyclerView.RecycledViewPool();
    private final Context context;
    private  final List<HoaDon> lstHoaDon;

    DaoHoaDon daoHoaDon;

    public DonHangAdapter(Context context, List<HoaDon> lstHoaDon) {
        this.context = context;
        this.lstHoaDon = lstHoaDon;
        this.daoHoaDon = new DaoHoaDon(context);
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
        DaoSanPham daoSP = new DaoSanPham(context);

        HoaDon hoaDon = lstHoaDon.get(position);

        SanPham sanPham = daoSP.getID(String.valueOf(hoaDon.getMaSP()));

        holder.txtdonHang.setText("Đơn hàng:" + hoaDon.getIdDonHang());

        if (sanPham != null) {
            KhachHang kh = daoKH.getID(hoaDon.getMaKH());
            holder.txtMakh.setText("Mã khách hàng:" + kh.getMaKH());
            holder.txtTenSP.setText("Tên sản phẩm:" + sanPham.getTenSP());
            holder.txtTongTien.setText("Tổng tiền:" + String.valueOf(hoaDon.getTongTien()) + " đ");
            holder.txtNgayDat.setText("Ngày đặt:" + hoaDon.getNgayDat());
        } else {

//            holder.txtMakh.setText("Mã khách hàng: N/A");
//            holder.txtTenSP.setText("Tên sản phẩm: N/A");
//            holder.txtTongTien.setText("Tổng tiền: N/A");
//            holder.txtNgayDat.setText("Ngày đặt: N/A");
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder buider =new AlertDialog.Builder(context);
                buider.setTitle("Cảnh báo");
                buider.setIcon(R.drawable.warning);
                buider.setMessage("Bạn có chắc muốn xóa?");

                buider.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (daoHoaDon.delete(String.valueOf(hoaDon.getIdDonHang()))) {
                            lstHoaDon.clear();
                            lstHoaDon.addAll(daoHoaDon.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Delete succ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Delete false", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                buider.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Không xóa", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dia = buider.create();
                dia.show();
            }

        });
    }

    @Override
    public int getItemCount() {
         return lstHoaDon != null ? lstHoaDon.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView  txtdonHang, txtMakh, txtTongTien, txtNgayDat, txtTenSP;
        ImageButton delete;
        RecyclerView reChiTiet;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdonHang = itemView.findViewById(R.id.idDonHang);
            txtMakh = itemView.findViewById(R.id.idKhachHang);
            txtTongTien = itemView.findViewById(R.id.tongTien);
            txtNgayDat = itemView.findViewById(R.id.ngayDat);
            txtTenSP  = itemView.findViewById(R.id.idSanPham);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
