//package com.example.duan1.Fragment;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.duan1.Adapter.GioHangAdapter;
//import com.example.duan1.DAO.DaoGioHang;
//import com.example.duan1.DAO.DaoSanPham;
//import com.example.duan1.Model.CTSanPham;
//import com.example.duan1.Model.GioHang;
//import com.example.duan1.Model.IClickItemRCV;
//import com.example.duan1.Model.SanPham;
//import com.example.duan1.R;
//import com.example.duan1.demo.Cart;
//
//import java.util.ArrayList;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link GioHang_KH_Fragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class GioHang_KH_Fragment extends Fragment {
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public GioHang_KH_Fragment() {
//
//    }
//
//
//    public static GioHang_KH_Fragment newInstance(String param1, String param2) {
//        GioHang_KH_Fragment fragment = new GioHang_KH_Fragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    ArrayList<GioHang> lstGio;
//
//    DaoGioHang daoGioHang;
//
//    GioHangAdapter adapter;
//
//        RecyclerView rcvGioHang;
//        GioHang gioHang;
//     Cart cart;
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View v= inflater.inflate(R.layout.fragment_gio_hang__k_h, container, false);
//
//         rcvGioHang = v.findViewById(R.id.rcvGioHang);
//         lstGio = new ArrayList<>();
//
//         LinearLayoutManager manager = new LinearLayoutManager(requireContext());
//
//         rcvGioHang.setLayoutManager(manager);
//         adapter = new GioHangAdapter(requireContext(), lstGio, new IClickItemRCV() {
//             @Override
//             public void iclickItem(RecyclerView.ViewHolder viewHolder, int position, int type) {
//
//             }
//         });
//         rcvGioHang.setAdapter(adapter);
//         cart = new Cart();
//
//         Bundle args = getArguments();
//         if(args != null){
//
//             String tenSP = args.getString("tenSP");
//             String tenHang = args.getString("tenHang");
//             int giaTien = args.getInt("giaTien");
//
//             gioHang = new GioHang(01,01, tenSP, tenHang, giaTien, 1);
//
//             lstGio.add(gioHang);
//
//             cart.addToCart(gioHang);
//             updateCartUI();
//         }
//
//        return v;
//    }
//
//    private void updateCartUI() {
//      if(lstGio != null && !lstGio.isEmpty()){
//          adapter.notifyDataSetChanged();
//      }
//    }
//}