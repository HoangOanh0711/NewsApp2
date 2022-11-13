package com.example.newsapp.TaiKhoan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.TrangChu.taikhoan;
import com.example.newsapp.XoSo.xoso;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class thongtinnguoidung extends AppCompatActivity {

    //sửa đây thành Image button luôn
    ImageButton IMG_thongtinnguoidung_back;
    TextInputEditText txt_et_hvt, txt_et_ngaysinh, txt_et_email, txt_et_sdt;
    String hovaten, ngaysinh, email, sdt;
    RadioButton rbtn_nam, rbtn_nu;
    RadioGroup rGroup_gioitinh;
    Button btn_luuthongtin;
    String Phone;
    int Idcheck;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://newsapp-a5dc3-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinnguoidung);
        khaibao();
        IMG_thongtinnguoidung_back = findViewById(R.id.img_thongtinnguoidung_back);
        Phone = "0397370612";

        //nhấn nút cập nhật
        btn_luuthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ganggiatri();
                databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(Phone)) {
                            databaseReference.child("Users").child(Phone).child("Họ và tên").setValue(hovaten);
                            databaseReference.child("Users").child(Phone).child("Ngày sinh").setValue(ngaysinh);
                            databaseReference.child("Users").child(Phone).child("Email").setValue(email);
                            if (rbtn_nam.isChecked()) {
                                databaseReference.child("Users").child(Phone).child("Giới tính").setValue("Nam");
                            }
                            if (rbtn_nu.isChecked()){
                                databaseReference.child("Users").child(Phone).child("Giới tính").setValue("Nữ");
                            }
                            Toast.makeText(thongtinnguoidung.this,"Cập nhật thông tin thành công",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(thongtinnguoidung.this,"Lỗi",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // gọi click này
        IMG_thongtinnguoidung_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // gọi intent cho nó chuyển về á
                Intent intentTTND = new Intent(getApplicationContext(), taikhoan.class);
                startActivity(intentTTND);
             }
        });
    }

    //phần này để làm cho
    private void ganggiatri() {
        hovaten = txt_et_hvt.getText().toString();
        ngaysinh = txt_et_ngaysinh.getText().toString().trim();
        email = txt_et_email.getText().toString().trim();
        sdt = txt_et_sdt.getText().toString().trim();
        Idcheck = rGroup_gioitinh.getCheckedRadioButtonId();
    }

    private void khaibao(){
        txt_et_hvt = (TextInputEditText) findViewById(R.id.txtl_ttnd_hovaten);
        txt_et_ngaysinh = (TextInputEditText) findViewById(R.id.txtl_ttnd_ngaysinh);
        txt_et_email = (TextInputEditText) findViewById(R.id.txtl_ttnd_email);
        txt_et_sdt = (TextInputEditText) findViewById(R.id.txtl_ttnd_sodienthoai);
        rbtn_nam = (RadioButton)findViewById(R.id.rd_signup_Nam) ;
        rbtn_nu = (RadioButton)findViewById(R.id.rd_signup_Nu) ;
        rGroup_gioitinh = (RadioGroup)findViewById(R.id.rg_signup_GioiTinh) ;
        btn_luuthongtin = (Button) findViewById(R.id.btn_luuthongtin_thongtinngdung);
    }
    // vậy là xong thôi
//    //Hàm quay về màn hình trước
//    public void backFromTTND(View view){
//
//        Intent intent = new Intent(getApplicationContext(), taikhoan.class);
//        Pair[] pairs = new Pair[1];
//        pairs[0] = new Pair<View, String>(findViewById(R.id.LayoutTTND),"transition_taikhoan");
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(thongtinnguoidung.this,pairs);
//            startActivity(intent,options.toBundle());
//        }else {
//            startActivity(intent);
//        }
//    }
    // muốn bôi đen thế này bạn dùng ctrl + / nha
}