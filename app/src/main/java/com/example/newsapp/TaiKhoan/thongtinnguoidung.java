package com.example.newsapp.TaiKhoan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.R;
import com.example.newsapp.TrangChu.taikhoan;
import com.example.newsapp.XoSo.xoso;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class thongtinnguoidung extends AppCompatActivity {
    Button savebutton;
    AlertDialog.Builder builder;
    ImageButton IMG_thongtinnguoidung_back;
    TextView txtl_ttnd_hovaten;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinnguoidung);
        savebutton = (Button) findViewById(R.id.btn_luuthongtin_thongtinngdung);
        builder = new AlertDialog.Builder(this);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Lưu thông tin người dùng thành công !" )
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // sau khi bấm ok thì quay lại trang tài khoản
                                finish();
                            }
                        });





                AlertDialog alert = builder.create();
                alert.setTitle("");
                alert.show();
            }
        });

        txtl_ttnd_hovaten = findViewById(R.id.txtl_ttnd_hovaten);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //.requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account!= null){
            String Name = account.getDisplayName();

            txtl_ttnd_hovaten.setText(Name);
        }
        IMG_thongtinnguoidung_back = findViewById(R.id.img_thongtinnguoidung_back);

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