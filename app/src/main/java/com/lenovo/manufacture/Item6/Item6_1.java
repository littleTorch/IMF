package com.lenovo.manufacture.Item6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.R;

public class Item6_1 extends AppCompatActivity implements View.OnClickListener {

    private Button mBeak;
    /**
     * 标题
     */
    private TextView mHead;
    /**
     * XXXXXXXXXX
     */
    private TextView mTv1;
    /**
     * XXXXXXXXXX
     */
    private TextView mTv2;
    /**
     * XXXXXXXXXX
     */
    private TextView mTv3;
    private VideoView mVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item6_1);
        initView();
        getmsg();
        mVideo.setVideoPath("https://v-cdn.zjol.com.cn/276987.mp4");
    }

    private void getmsg() {
        Intent intent = getIntent();
        mTv1.setText(intent.getStringExtra("top"));
        mTv2.setText(intent.getStringExtra("time"));
        mTv3.setText(intent.getStringExtra("msg"));
    }

    private void initView() {
        mBeak = (Button) findViewById(R.id.beak);
        mBeak.setOnClickListener(this);
        mHead = (TextView) findViewById(R.id.head);
        mTv1 = (TextView) findViewById(R.id.tv1);
        mTv2 = (TextView) findViewById(R.id.tv2);
        mTv3 = (TextView) findViewById(R.id.tv3);
        mVideo = (VideoView) findViewById(R.id.video);
        mVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.beak:
                finish();
                break;
            case R.id.video:
                mVideo.start();
                break;
        }
    }
}
