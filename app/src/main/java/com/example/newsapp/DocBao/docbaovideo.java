package com.example.newsapp.DocBao;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.R;

public class docbaovideo extends AppCompatActivity {

    private VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docbaovideo);

        vv = (VideoView) findViewById(R.id.img_anhbao_docbaovideo);

        MediaController mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(vv);
        vv.setMediaController(mediacontroller);
        vv.setVideoURI(Uri.parse("http://www.demonuts.com/Demonuts/smallvideo.mp4"));
        //vv.start();
    }
}