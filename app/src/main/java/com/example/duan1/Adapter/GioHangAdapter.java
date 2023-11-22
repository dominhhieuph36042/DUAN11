package com.example.duan1.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.duan1.Model.EventBus.TinhTongEvent;
import com.example.duan1.Model.GioHang;
import com.example.duan1.Model.IClickItemRCV;
import com.example.duan1.Model.IImageClichListener;
import com.example.duan1.Model.SanPham;
import com.example.duan1.R;
import com.example.duan1.untils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

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

       holder.setListenner(new IImageClichListener() {
           @Override
           public void onImageClick(View view, int pos, int giaTri) {
               if(giaTri ==1){
                 if(lstGioHang.get(pos).getSoluong() > 1){
                     int soLuongMoi = lstGioHang.get(pos).getSoluong()-1;
                     lstGioHang.get(pos).setSoluong(soLuongMoi);
                 }
               } else if(giaTri == 2){
                   if(lstGioHang.get(pos).getSoluong() < 100){
                       int soLuongMoi = lstGioHang.get(pos).getSoluong()+1;
                       lstGioHang.get(pos).setSoluong(soLuongMoi);
                   }
               }

               holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       AlertDialog.Builder buider = new AlertDialog.Builder(view.getRootView().getContext());
                       buider.setTitle("Thông báo");
                       buider.setMessage("Bạn có muốn xóa sản phẩm này khỏi giỏ hàng!");
                       buider.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               untils.mangGioHang.remove(pos);
                           }
                       });

                       buider.setNegativeButton("No", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               dialogInterface.dismiss();
                           }
                       });
                       buider.show();
                   }
               });
               holder.tvSoLuong.setText(lstGioHang.get(pos).getSoluong() + " ");
               EventBus.getDefault().postSticky(new TinhTongEvent());
           }
       });


    }

    @Override
    public int getItemCount() {
       return lstGioHang != null ? lstGioHang.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvmaSP, tvTenSP, tvHangSP, tvGia, tvSoLuong;
        ImageView btnDelete, images, imagesTru, imagesCong;

        IImageClichListener listenner;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvmaSP = itemView.findViewById(R.id.maSP_gioHang);
            tvTenSP = itemView.findViewById(R.id.tensanpham_gioHang);
            tvHangSP = itemView.findViewById(R.id.tenHang_gioHang);
            tvGia = itemView.findViewById(R.id.giatien_gioHang);
            tvSoLuong= itemView.findViewById(R.id.soLuong_gioHang);
            images = itemView.findViewById(R.id.images_gioHang);

            btnDelete = itemView.findViewById(R.id.btn_delete_gioHang);

            imagesTru = itemView.findViewById(R.id.item_gioHang_tru);
            imagesCong = itemView.findViewById(R.id.item_gioHang_cong);

            //event click
            imagesCong.setOnClickListener(this);
            imagesTru.setOnClickListener(this);
        }

        public void setListenner(IImageClichListener listenner) {
            this.listenner = listenner;
        }

        @Override
        public void onClick(View view) {
            if(view == imagesTru){
                listenner.onImageClick(view, getAdapterPosition(), 1);
                //1 tru
            }else if(view == imagesCong){
                listenner.onImageClick(view, getAdapterPosition(), 2);
                //2 cộng
            }
        }
    }
}
