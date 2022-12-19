package com.example.newsapp.TrangChu;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.newsapp.GiaVang.giavang;
import com.example.newsapp.LichVanNien.lichvannien;
import com.example.newsapp.R;
import com.example.newsapp.TaiKhoan.dangnhap;
import com.example.newsapp.TaiKhoan.doimatkhau;
import com.example.newsapp.TaiKhoan.thongtinnguoidung;
import com.example.newsapp.Thoitiet.thoitiet;
import com.example.newsapp.TruyenDuLieu;
import com.example.newsapp.XoSo.xoso;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class taikhoan extends Fragment {
    private ImageView IMG_caidat_anhdaidien;
    private TextView TXT_caidat_tecmmguoidung;
    LinearLayout thongtinngdung,doimk,thoitiet,xoso,giavang,lichviet,dangxuat;
    private SaveState saveState;
    private Context context;
    String Phone;
    public static final int MY_REQUEST_CODE = 10;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://newsapp-a5dc3-default-rtdb.firebaseio.com/");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taikhoan, container, false);

        String myphone = TruyenDuLieu.Tr_sdt;
        //Ánh xạ
        khaibao(view);

        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(myphone)) {
                    final String username = snapshot.child(myphone).child("Tên người dùng").getValue(String.class);
                    TXT_caidat_tecmmguoidung.setText(username);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //đổi ảnh đại diện
        IMG_caidat_anhdaidien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // chuyển màn hình giá vàng
        giavang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentgiavang = new Intent(getActivity(), giavang.class);
                startActivity(intentgiavang);
            }
        });

        // chuyển qua màn hình xổ số
        xoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentxoso = new Intent(getActivity(), xoso.class);
                startActivity(intentxoso);
            }
        });

        // chuyển qua màn hình lịch việt
        lichviet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentlichviet = new Intent(getActivity(), lichvannien.class);
                startActivity(intentlichviet);
            }
        });

        // chuyển qua màn hình thông tin đăng nhập
        thongtinngdung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentttnd = new Intent(getActivity(), thongtinnguoidung.class);
                startActivity(intentttnd);
            }
        });

        //chuyển qua màn hình đổi mật khẩu
        doimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdmk = new Intent(getActivity(), doimatkhau.class);
                startActivity(intentdmk);
            }
        });

        //chuyển qua màn hình đổi mật khẩu
        thoitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdmk = new Intent(getActivity(), com.example.newsapp.Thoitiet.thoitiet.class);
                startActivity(intentdmk);
            }
        });

        //đăng xuất
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdmk = new Intent(getActivity(), dangnhap.class);
                startActivity(intentdmk);
            }
        });
        return view;
    }

    private void khaibao(View view) {
        thongtinngdung =view.findViewById(R.id.layout_caidat_ttnd);
        doimk =view.findViewById(R.id.layout_caidat_dmk);
        thoitiet =view.findViewById(R.id.layout_caidat_thoitiet);
        xoso =view.findViewById(R.id.layout_caidat_xoso);
        giavang =view.findViewById(R.id.layout_caidat_giavang);
        lichviet =view.findViewById(R.id.layout_caidat_lichviet);
        dangxuat = view.findViewById(R.id.layout_caidat_dangxuat);
        TXT_caidat_tecmmguoidung = view.findViewById(R.id.txt_caidat_tecmmguoidung);
        IMG_caidat_anhdaidien = view.findViewById(R.id.img_caidat_anhdaidien);
    }

    public static taikhoan newInstance() {
        taikhoan fragment = new taikhoan();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}