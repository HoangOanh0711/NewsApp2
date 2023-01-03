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
    String user, pass, Phone,newpass, st_matkhau, st_nhaplaimk ;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://newsapp-a5dc3-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenmatkhau3);

        khaibao();

        st_matkhau = ed_matkhau.getText().toString().trim();
        st_nhaplaimk = ed_nhaplaimk.getText().toString().trim();
        Phone = TruyenDuLieu.Truyen_sdt_quenmk;

        //user = getIntent().getStringExtra("Users");
        //pass = getIntent().getStringExtra("Mật khẩu");
        //Phone = getIntent().getStringExtra("");

        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ktra();
                Log.e("st_matkhau",st_matkhau);
                Log.e("st_nhaplaimk",st_nhaplaimk);
                databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //if (snapshot.hasChild(Phone)) {
                        //final String getmk = snapshot.child(Phone).child("Mật khẩu").getValue(String.class);
                        //if (getmk.equals(pass_ht)) {
                        newpass = ed_matkhau.getText().toString().trim();
                        databaseReference.child("Users").child(Phone).child("Mật khẩu").setValue(newpass);
                        Toast.makeText(quenmatkhau3.this, "Mật khẩu đã được cập nhật", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), quenmatkhau4.class));
                        //}
                        //else {
                        //Toast.makeText(quenmatkhau3.this,"Mật khẩu hiện tại không đúng",Toast.LENGTH_SHORT).show();
                        //}
                        //}
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(quenmatkhau3.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                /*Phone = TruyenDuLieu.Truyen_sdt_quenmk;
                newpass = ed_matkhau.getText().toString().trim();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                reference.child(Phone).child("Mật khẩu").setValue(newpass);
                startActivity(new Intent(getApplicationContext(), quenmatkhau4.class));
                finish();*/

                });
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