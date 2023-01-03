package com.example.newsapp.TaiKhoan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.R;
import com.example.newsapp.TruyenDuLieu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class quenmatkhau3 extends AppCompatActivity {

    EditText ed_matkhau,ed_nhaplaimk;
    Button btn_capnhat;
    String Phone, st_matkhau, st_nhaplaimk;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://newsapp-a5dc3-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenmatkhau3);

        khaibao();

        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gangiatri();
                Log.e("st_matkhau",st_matkhau);
                Log.e("Phone",Phone);
                if (ktra()) {
                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(Phone)) {
                            databaseReference.child("Users").child(Phone).child("Mật khẩu").setValue(st_matkhau);
                            Toast.makeText(quenmatkhau3.this, "Mật khẩu đã được cập nhật", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), quenmatkhau4.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(quenmatkhau3.this, "Lỗi", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void gangiatri() {
        st_matkhau = ed_matkhau.getText().toString().trim();
        st_nhaplaimk = ed_nhaplaimk.getText().toString().trim();
        Phone = getIntent().getStringExtra("sdt-qmk1");;
    }

    private void khaibao() {
        ed_matkhau = findViewById(R.id.ed_matkhau_quenmk);
        ed_nhaplaimk = findViewById(R.id.ed_nhaplaimatkhau_quenmk);
        btn_capnhat = findViewById(R.id.btn_capnhat);
    }

    private boolean ktra() {
        if (st_matkhau.isEmpty()) {
            Toast.makeText(this, "Nhập mật khẩu", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (st_nhaplaimk.isEmpty()) {
            Toast.makeText(this, "Nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (st_matkhau.length()<6) {
            Toast.makeText(this, "Mật khẩu quá ngắn", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!st_matkhau.equals(st_nhaplaimk)) {
            Toast.makeText(this, "Mật khẩu khác nhau", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
}