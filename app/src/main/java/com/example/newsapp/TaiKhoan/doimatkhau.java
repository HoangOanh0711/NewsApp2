package com.example.newsapp.TaiKhoan;

import com.example.newsapp.TruyenDuLieu;
import com.example.newsapp.TaiKhoan.dangnhap;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.TrangChu.taikhoan;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class doimatkhau extends AppCompatActivity {

    ImageButton IMG_dmk_back;
    TextInputEditText mk_ht, mk_moi, mk_nhaplai;
    String pass_ht, pass_moi, pass_nhaplai,myphone;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://newsapp-a5dc3-default-rtdb.firebaseio.com/");
    Button btn_change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doimatkhau);

        khaibao();

        myphone = TruyenDuLieu.Tr_sdt;
        pass_ht = mk_ht.getText().toString().trim();
        pass_moi = mk_moi.getText().toString().trim();
        pass_nhaplai = mk_nhaplai.getText().toString().trim();

        //Nút cập nhật mật khẩu
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //đã hoàn thành
                databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(myphone)) {
                            final String getmk = snapshot.child(myphone).child("Mật khẩu").getValue(String.class);
                            Log.e("getmk:",getmk);
                            if (pass_ht.isEmpty()) {
                                Toast.makeText(doimatkhau.this,"Vui lòng nhập mật khẩu hiện tại",Toast.LENGTH_SHORT).show();
                            }
                            if (!getmk.equals(pass_ht)) {
                                Log.i("mkht",pass_ht);
                                Toast.makeText(doimatkhau.this,"Mật khẩu hiện tại không đúng",Toast.LENGTH_SHORT).show();
                            } else {
                                if (pass_moi.length() < 6) {
                                    Toast.makeText(doimatkhau.this,"Vui lòng nhập mật khẩu mới từ 6 kí tự trở lên",Toast.LENGTH_SHORT).show();
                                } else {
                                    if (!pass_moi.equals(pass_nhaplai)) {
                                        Toast.makeText(doimatkhau.this,"Nhập lại mật khẩu không giống",Toast.LENGTH_SHORT).show();
                                    } else {
                                        databaseReference.child("Users").child(myphone).child("Mật khẩu").setValue(pass_nhaplai);
                                        Toast.makeText(doimatkhau.this,"Mật khẩu đã được cập nhật",Toast.LENGTH_SHORT).show();
                                        mk_ht.setText("");
                                        mk_moi.setText("");
                                        mk_nhaplai.setText("");
                                    }
                                }

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(doimatkhau.this,"Lỗi",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        IMG_dmk_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDMK = new Intent(getApplicationContext(), taikhoan.class);
                startActivity(intentDMK);
            }
        });
    }

    private void khaibao(){
        mk_ht = (TextInputEditText) findViewById(R.id.txt_et_mk_ht);
        mk_moi = (TextInputEditText) findViewById(R.id.txt_et_mk_moi);
        mk_nhaplai = (TextInputEditText) findViewById(R.id.txt_et_mk_nhaplai);
        btn_change = (Button) findViewById(R.id.btn_capnhat_dmk);
        IMG_dmk_back = findViewById(R.id.img_dmk_back);
    }


    //Hàm quay về màn hình trước
//    public void backFromDMK(View view){
//
//         Intent intent = new Intent(getApplicationContext(), taikhoan.class);
//        Pair[] pairs = new Pair[1];
//        pairs[0] = new Pair<View, String>(findViewById(R.id.LayoutDMK),"transition_taikhoan");
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(doimatkhau.this,pairs);
//            startActivity(intent,options.toBundle());
//        }else {
//            startActivity(intent);
//        }
//    }
}