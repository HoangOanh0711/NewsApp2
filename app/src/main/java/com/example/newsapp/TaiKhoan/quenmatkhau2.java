package com.example.newsapp.TaiKhoan;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class quenmatkhau2 extends AppCompatActivity {

    Button btn_xacnhan;
    TextView txt_sdt, btn_taiday_quenmk;
    EditText ed_otp1, ed_otp2, ed_otp3, ed_otp4, ed_otp5, ed_otp6;

    //private static final String TAG = "PhoneAuthActivity";

    private FirebaseAuth mAuth;
    //private FirebaseAuth.AuthStateListener mAuthListener;
    //private PhoneAuthProvider.ForceResendingToken forceResendingToken;

    private String mVerificationId;
    //private PhoneAuthProvider.ForceResendingToken mResendToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenmatkhau2);
        mAuth = FirebaseAuth.getInstance();


        khaibao();
        setupOTPInput();

        //btn_taiday_quenmk = findViewById(R.id.btn_taiday_quenmk);
        btn_taiday_quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(quenmatkhau2.this, "OTP Send Successfully.", Toast.LENGTH_SHORT).show();
            }
        });

        //Log.e("qua đc",TruyenDuLieu.Truyen_sdt_quenmk);
        //txt_sdt.setText("Hãy điền OTP gồm 6 số vừa được gửi đến số điện thoại "+ TruyenDuLieu.Truyen_sdt_quenmk);

        mVerificationId = getIntent().getStringExtra("OTP");
        Log.e("qmk2",mVerificationId);

        //btn_xacnhan = findViewById(R.id.btn_xacnhan);
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //btn_xacnhan.setVisibility(View.INVISIBLE);
                if (!ed_otp1.getText().toString().trim().isEmpty() && !ed_otp2.getText().toString().trim().isEmpty()
                        && !ed_otp3.getText().toString().trim().isEmpty()
                        && !ed_otp4.getText().toString().trim().isEmpty()
                        && !ed_otp5.getText().toString().trim().isEmpty()
                        && !ed_otp6.getText().toString().trim().isEmpty()) {

                    // marging user's input in a string
                    String getuserotp = ed_otp1.getText().toString() +
                            ed_otp2.getText().toString() +
                            ed_otp3.getText().toString() +
                            ed_otp4.getText().toString() +
                            ed_otp5.getText().toString() +
                            ed_otp6.getText().toString();

                    if (mVerificationId != null) {

                        //progressBar.setVisibility(View.VISIBLE);
                        //btn_xacnhan.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(mVerificationId, getuserotp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        //progressBar.setVisibility(View.GONE);
                                        btn_xacnhan.setVisibility(View.VISIBLE);

                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(), quenmatkhau3.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(quenmatkhau2.this, "Enter corrent OTP", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                    } /*else {
                        Toast.makeText(quenmatkhau2.this, "Please check internet", Toast.LENGTH_SHORT).show();
                    }*/

                    //Toast.makeText(MainActivity2.this, "OTP Verify", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(quenmatkhau2.this, "Please fill all number", Toast.LENGTH_SHORT).show();
                }
                /*
                if (ed_otp1.getText().toString().isEmpty() ||
                        ed_otp2.getText().toString().isEmpty() ||
                        ed_otp3.getText().toString().isEmpty() ||
                        ed_otp4.getText().toString().isEmpty() ||
                        ed_otp5.getText().toString().isEmpty() ||
                        ed_otp6.getText().toString().isEmpty()) {
                    Toast.makeText(quenmatkhau2.this,"Nhập OTP đã được gửi qua số điện thoại",Toast.LENGTH_SHORT).show();
                    //return;
                } else {
                    if (mVerificationId != null) {
                        String code = ed_otp1.getText().toString().trim() +
                                ed_otp2.getText().toString().trim() +
                                ed_otp3.getText().toString().trim() +
                                ed_otp4.getText().toString().trim() +
                                ed_otp5.getText().toString().trim() +
                                ed_otp6.getText().toString().trim();

                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
                        FirebaseAuth
                                .getInstance()
                                .signInWithCredential(credential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            btn_xacnhan.setVisibility(View.INVISIBLE);
                                            Toast.makeText(quenmatkhau2.this, "Welcome...", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(quenmatkhau2.this, quenmatkhau3.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                        } else {
                                            btn_xacnhan.setVisibility(View.VISIBLE);
                                            Toast.makeText(quenmatkhau2.this, "OTP is not Valid!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }*/
                /*String otp = ed_otp1.getText().toString() +
                        ed_otp2.getText().toString() +
                        ed_otp3.getText().toString() +
                        ed_otp4.getText().toString() +
                        ed_otp5.getText().toString() +
                        ed_otp6.getText().toString();
                if (mVerificationId!=null) {
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(mVerificationId, otp);
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), quenmatkhau3.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(quenmatkhau2.this,"OTP sai, vui lòng nhập lại",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });
                }*/
            }
        });

        /*
        ed_otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //if (ed_otp1.getText().toString().length()==1) {
                    ed_otp2.requestFocus();
                //}
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ed_otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //if (ed_otp2.getText().toString().length()==1) {
                    ed_otp3.requestFocus();
                //}
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ed_otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //if (ed_otp3.getText().toString().length()==1) {
                    ed_otp4.requestFocus();
                //}
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ed_otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //if (ed_otp4.getText().toString().length()==1) {
                    ed_otp5.requestFocus();
                //}
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ed_otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //if (ed_otp5.getText().toString().length()==1) {
                    ed_otp6.requestFocus();
                //}
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/
    }


    /*@Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }*/

    private void setupOTPInput() {
        ed_otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /*if (ed_otp1.getText().toString().length()==1) {
                    ed_otp2.requestFocus();
                }*/
                ed_otp2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ed_otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //if (ed_otp2.getText().toString().length()==1) {
                    ed_otp3.requestFocus();
                //}
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ed_otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //if (ed_otp3.getText().toString().length()==1) {
                    ed_otp4.requestFocus();
                //}
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ed_otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //if (ed_otp4.getText().toString().length()==1) {
                    ed_otp5.requestFocus();
                //}
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ed_otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //if (ed_otp5.getText().toString().length()==1) {
                    ed_otp6.requestFocus();
                //}
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void khaibao() {

        btn_taiday_quenmk = findViewById(R.id.btn_taiday_quenmk);
        btn_xacnhan = findViewById(R.id.btn_xacnhan);
        ed_otp1 = findViewById(R.id.ed_otp1);
        ed_otp2 = findViewById(R.id.ed_otp2);
        ed_otp3 = findViewById(R.id.ed_otp3);
        ed_otp4 = findViewById(R.id.ed_otp4);
        ed_otp5 = findViewById(R.id.ed_otp5);
        ed_otp6 = findViewById(R.id.ed_otp6);
    }
}