package com.lenovo.manufacture.Item3;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.lenovo.manufacture.R;

public class Item3 extends AppCompatActivity implements View.OnClickListener {

    private Button mBeak;

    private TextView mHead;

    private Button mBt1;

    private Button mBt2;
    Fragment frag1= new Fragment3_1();
    Fragment frag2= new Fragment3_2();
    Drawable drawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item3);
        initView();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fr,frag1);
        fragmentTransaction.commit();
    }

    private void initView() {
        mBeak = (Button) findViewById(R.id.beak);
        mBeak.setOnClickListener(this);
        mHead = (TextView) findViewById(R.id.head);
        mBt1 = (Button) findViewById(R.id.bt1);
        mBt1.setOnClickListener(this);
        mBt2 = (Button) findViewById(R.id.bt2);
        mBt2.setOnClickListener(this);
        drawable=mBt1.getBackground();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            default:
                break;
            case R.id.beak:
                finish();
                break;
            case R.id.bt1:
                fragmentTransaction.replace(R.id.fr,frag1);
                mBt1.setBackground(drawable);
                mBt2.setBackgroundColor(Color.WHITE);
                break;
            case R.id.bt2:
                fragmentTransaction.replace(R.id.fr,frag2);
                mBt2.setBackground(drawable);
                mBt1.setBackgroundColor(Color.WHITE);
                break;
        }
        fragmentTransaction.commit();
    }
}
