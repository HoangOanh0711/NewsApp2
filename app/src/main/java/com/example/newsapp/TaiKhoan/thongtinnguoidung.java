package com.example.newsapp.TaiKhoan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.newsapp.TruyenDuLieu;
import com.example.newsapp.XoSo.xoso;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    int Idcheck;
    String myphone;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://newsapp-a5dc3-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinnguoidung);

        khaibao();
        myphone = TruyenDuLieu.Tr_sdt;
        showdata();

        //nhấn nút cập nhật
        btn_luuthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ganggiatri();
                databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(myphone)) {
                            if (!(hovaten.isEmpty())) {
                                databaseReference.child("Users").child(myphone).child("Họ và tên").setValue(hovaten);
                            }
                            if (!(ngaysinh.isEmpty())) {
                                databaseReference.child("Users").child(myphone).child("Ngày sinh").setValue(ngaysinh);
                            }
                            if (!(email.isEmpty())) {
                                databaseReference.child("Users").child(myphone).child("Email").setValue(email);
                            }
                            if (rbtn_nam.isChecked()) {
                                databaseReference.child("Users").child(myphone).child("Giới tính").setValue("Nam");
                            }
                            if (rbtn_nu.isChecked()) {
                                databaseReference.child("Users").child(myphone).child("Giới tính").setValue("Nữ");
                            }
                            Toast.makeText(thongtinnguoidung.this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(thongtinnguoidung.this, "Lỗi", Toast.LENGTH_SHORT).show();
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

    private void showdata() {
        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    final String hoten = snapshot.child(myphone).child("Họ và tên").getValue(String.class);
                    final String gioitinh = snapshot.child(myphone).child("Giới tính").getValue(String.class);
                    final String ngaysinh = snapshot.child(myphone).child("Ngày sinh").getValue(String.class);
                    final String email = snapshot.child(myphone).child("Email").getValue(String.class);
                    txt_et_hvt.setHint(hoten);
                    txt_et_email.setHint(email);
                    txt_et_ngaysinh.setHint(ngaysinh);
                    txt_et_sdt.setText(myphone);
                    txt_et_sdt.setFocusable(false);
                    txt_et_sdt.setClickable(true);
                    Log.e("thongtin",gioitinh);
                    if (gioitinh.equals("Nam")) {
                        rbtn_nam.isChecked();
                    } else if (gioitinh=="Nữ") {
                        rbtn_nu.isChecked();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(thongtinnguoidung.this, "Lỗi", Toast.LENGTH_SHORT).show();
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
        IMG_thongtinnguoidung_back = findViewById(R.id.img_thongtinnguoidung_back);
    }
    //Hàm quay về màn hình trước
    public void backFromTTND(View view){

        Intent intent = new Intent(getApplicationContext(), taikhoan.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.LayoutTTND),"transition_taikhoan");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(thongtinnguoidung.this,pairs);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);
        }
    }
    // muốn bôi đen thế này bạn dùng ctrl + / nha
}