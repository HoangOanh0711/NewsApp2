package com.example.newsapp.TaiKhoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.TruyenDuLieu;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class quenmatkhau3 extends AppCompatActivity {

    EditText ed_matkhau,ed_nhaplaimk;
    Button btn_capnhat;
    String user, pass, Phone,newpass, st_matkhau, st_nhaplaimk ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenmatkhau3);

        khaibao();

        st_matkhau = ed_matkhau.getText().toString().trim();
        st_nhaplaimk = ed_nhaplaimk.getText().toString().trim();
        Phone = TruyenDuLieu.Truyen_sdt_quenmk;

        /*user = getIntent().getStringExtra("Users");
        pass = getIntent().getStringExtra("Mật khẩu");
        Phone = getIntent().getStringExtra("");*/

        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ktra();
                /*Phone = TruyenDuLieu.Truyen_sdt_quenmk;
                newpass = ed_matkhau.getText().toString().trim();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                reference.child(Phone).child("Mật khẩu").setValue(newpass);
                startActivity(new Intent(getApplicationContext(), quenmatkhau4.class));
                finish();*/
            }
        });
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