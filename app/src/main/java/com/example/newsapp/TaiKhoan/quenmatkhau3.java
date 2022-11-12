package com.example.newsapp.TaiKhoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.newsapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class quenmatkhau3 extends AppCompatActivity {

    EditText ed_matkhau,ed_nhaplaimk;
    Button btn_capnhat;
    String user, pass, Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenmatkhau3);

        khaibao();
        user = getIntent().getStringExtra("Users");
        pass = getIntent().getStringExtra("Mật khẩu");
        Phone = getIntent().getStringExtra("");

        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phone = "0355736772";
                String _newpass = ed_matkhau.getText().toString().trim();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                reference.child(Phone).child("Mật khẩu").setValue(_newpass);
                startActivity(new Intent(getApplicationContext(), quenmatkhau4.class));
                finish();
            }
        });
    }

    private void khaibao() {
        ed_matkhau = findViewById(R.id.ed_matkhau_quenmk);
        ed_nhaplaimk = findViewById(R.id.ed_nhaplaimatkhau_quenmk);

        btn_capnhat = findViewById(R.id.btn_capnhat);
    }
}