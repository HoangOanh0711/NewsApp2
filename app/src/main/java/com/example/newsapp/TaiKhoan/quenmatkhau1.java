package com.example.newsapp.TaiKhoan;

import android.app.MediaRouteButton;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    String str_sdt;

    private quenmatkhau1 binding;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private MediaRouteButton progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenmatkhau1);

        khaibao();


        mAuth = FirebaseAuth.getInstance();
        //progressdialog = new ProgressDialog(quenmatkhau1.this);
        //progressdialog.setMessage("OTP đang được gửi, bạn đợi chút nha!");

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
                //str_sdt = "+" + countryCodePicker.getFullNumber();
                //TruyenDuLieu.Truyen_sdt_quenmk = str_sdt;
                if (sdt.getText().toString().trim().isEmpty()) {
                    Toast.makeText(quenmatkhau1.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                } else if (sdt.getText().toString().trim().length() != 10) {
                    Toast.makeText(quenmatkhau1.this, "Type valid Phone Number", Toast.LENGTH_SHORT).show();
                } else {
                    otpSend();
                }
                /*if (sdt.getText().toString().trim().isEmpty()) {
                    Toast.makeText(quenmatkhau1.this,"Nhập số điện thoại của bạn",Toast.LENGTH_SHORT).show();
                    return;
                }
                Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("Số điện thoại").equalTo(str_sdt);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
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
                                            //intent.putExtra("sdt",str_sdt);
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
                        } else {
                            Toast.makeText(quenmatkhau1.this,"Số điện thoại chưa được đăng ký",Toast.LENGTH_SHORT);
                        }*/
                    }

                    //@Override
                    /*public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(quenmatkhau1.this,"Lỗi kết nối mạng",Toast.LENGTH_SHORT);
                    }*/
                });

            }
        //});



    //}

    private void otpSend() {
        //binding.progressBar.setVisibility(View.VISIBLE);
        //binding.btn_guiotp.setVisibility(View.INVISIBLE);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                //binding.progressBar.setVisibility(View.GONE);
                btn_guiotp.setVisibility(View.VISIBLE);
                Toast.makeText(quenmatkhau1.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                //binding.progressBar.setVisibility(View.GONE);
                btn_guiotp.setVisibility(View.VISIBLE);
                Toast.makeText(quenmatkhau1.this, "OTP is successfully send.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(quenmatkhau1.this, quenmatkhau2.class);
                intent.putExtra("", sdt.getText().toString().trim());
                intent.putExtra("verificationId", verificationId);
                startActivity(intent);
            }
        };

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+84" + sdt.getText().toString().trim())
                        .setTimeout(120L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }





    private void khaibao() {
        btn_taiday = findViewById(R.id.btn_taiday_quenmk);
        btn_guiotp = findViewById(R.id.btn_guiotp);
        sdt = findViewById(R.id.ed_sdt_quenmk);
        img_check = findViewById(R.id.img_check_quenmk);
        countryCodePicker = findViewById(R.id.ccp_quenmk);
    }

    /*public void verifyPhoneNumber(View view) {
        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("Số điện thoại").equalTo(str_sdt);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Intent intent = new Intent(quenmatkhau1.this,quenmatkhau2.class);
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

    }*/
}