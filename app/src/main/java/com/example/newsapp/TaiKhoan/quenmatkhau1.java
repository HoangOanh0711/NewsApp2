package com.example.newsapp.TaiKhoan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class quenmatkhau1 extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://newsapp-a5dc3-default-rtdb.firebaseio.com/");

    TextView btn_taiday;
    Button btn_guiotp;

    EditText sdt;

    CountryCodePicker countryCodePicker;
    ImageView img_check;

    FirebaseAuth mAuth;
    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenmatkhau1);

        btn_taiday = findViewById(R.id.btn_taiday_quenmk);
        btn_guiotp = findViewById(R.id.btn_guiotp);
        sdt = findViewById(R.id.ed_sdt_quenmk);
        img_check = findViewById(R.id.img_check_quenmk);
        countryCodePicker = findViewById(R.id.ccp_quenmk);

        mAuth = FirebaseAuth.getInstance();
        progressdialog = new ProgressDialog(quenmatkhau1.this);
        progressdialog.setMessage("OTP đang được gửi, bạn đợi chút nha!");

        countryCodePicker.registerCarrierNumberEditText(sdt);
        countryCodePicker.setPhoneNumberValidityChangeListener(new CountryCodePicker.PhoneNumberValidityChangeListener() {
            @Override
            public void onValidityChanged(boolean isValidNumber) {
                if (isValidNumber) {
                    img_check.setImageResource(R.drawable.ic_checkok);
                } else {
                    img_check.setImageResource(R.drawable.ic_checkx);
                }
            }
        });
        sdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString().trim();
                if (input.length() > 0) {
                    img_check.setVisibility(View.VISIBLE);
                } else {
                    img_check.setVisibility(View.GONE);
                }
            }
        });

        btn_taiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(quenmatkhau1.this, dangnhap.class);
                startActivity(intent);
                finish();
            }
        });

        btn_guiotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(quenmatkhau1.this, quenmatkhau2.class);
                startActivity(intent);
                finish();
                if (sdt.getText().toString().trim().isEmpty()) {
                    Toast.makeText(quenmatkhau1.this,"Nhập số điện thoại của bạn",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressdialog.show();

                PhoneAuthProvider.getInstance().verifyPhoneNumber("+" + countryCodePicker.getFullNumber(),
                        60, TimeUnit.SECONDS,quenmatkhau1.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressdialog.dismiss();
                                Toast.makeText(quenmatkhau1.this, e.getMessage(), Toast.LENGTH_SHORT);
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId,
                                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                                progressdialog.dismiss();
                                Intent intent = new Intent(getApplicationContext(),quenmatkhau2.class);
                                intent.putExtra("sdt","+" + countryCodePicker.getFullNumber());
                                intent.putExtra("otp",verificationId);
                                startActivity(intent);
                            }

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressdialog.dismiss();
                                final String code = phoneAuthCredential.getSmsCode();
                            }
                        }
                );
            }
        });


    }

    public void verifyPhoneNumber(View view) {
        final String sdt_hoanchinh = "+" + countryCodePicker.getFullNumber();

        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("Số điện thoại").equalTo(sdt_hoanchinh);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Intent intent = new Intent(quenmatkhau1.this,quenmatkhau2.class);
                    intent.putExtra("Số điện thoại",sdt_hoanchinh);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(quenmatkhau1.this,"Số điện thoại chưa được đăng ký",Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}