package com.lenovo.manufacture.Item9;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.lenovo.manufacture.Item8.Line;
import com.lenovo.manufacture.MyOk;
import com.lenovo.manufacture.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Item9 extends AppCompatActivity implements View.OnClickListener {

    private Button mBeak;

    private TextView mHead;
    private View mV1;

    private Button mCar1;
    private View mV2;

    private Button mCar2;
    private View mV3;

    private Button mCar3;
    private View mPv1;

    private Button mPos1;
    private View mPv2;

    private Button mPos2;
    private View mPv3;

    private Button mPos3;
    private View mPv4;

    private Button mPos4;
    private View mOkv;

    private Button mOk;
    private Button[] mPoss;
    Timer timer;
    List<Integer> linelist;
    private View[] mVs;
    private View[] mPvs;
    int pos = 0, lineId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item9);
        initView();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getjson();
            }
        }, 0, 50000);
    }

    private void getjson() {
        MyOk.post("dataInterface/UserProductionLine/getAll", "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("200")) {
                        JSONArray array = jsonObject.getJSONArray("data");
                        linelist = new ArrayList<>();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j = array.getJSONObject(i);
                            linelist.add(j.getInt("position"));
                        }
                        setbt();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setbt() {
        Drawable background = mV1.getBackground();
        for (int i = 0; i < mPoss.length; i++) {
            mPoss[i].setBackgroundColor(Color.parseColor("#ff33b5e5"));
            for (Integer integer : linelist) {
                if (integer == i) {
                    mPoss[i].setBackgroundColor(Color.parseColor("#aaa"));
                    mPoss[i].setEnabled(false);
                }
            }
        }
    }

    private void initView() {
        mBeak = (Button) findViewById(R.id.beak);
        mBeak.setOnClickListener(this);
        mHead = (TextView) findViewById(R.id.head);
        mV1 = (View) findViewById(R.id.v1);
        mCar1 = (Button) findViewById(R.id.car1);
        mCar1.setOnClickListener(this);
        mV2 = (View) findViewById(R.id.v2);
        mCar2 = (Button) findViewById(R.id.car2);
        mCar2.setOnClickListener(this);
        mV3 = (View) findViewById(R.id.v3);
        mCar3 = (Button) findViewById(R.id.car3);
        mCar3.setOnClickListener(this);
        mPv1 = (View) findViewById(R.id.pv1);
        mPos1 = (Button) findViewById(R.id.pos1);
        mPos1.setOnClickListener(this);
        mPv2 = (View) findViewById(R.id.pv2);
        mPos2 = (Button) findViewById(R.id.pos2);
        mPos2.setOnClickListener(this);
        mPv3 = (View) findViewById(R.id.pv3);
        mPos3 = (Button) findViewById(R.id.pos3);
        mPos3.setOnClickListener(this);
        mPv4 = (View) findViewById(R.id.pv4);
        mPos4 = (Button) findViewById(R.id.pos4);
        mPos4.setOnClickListener(this);
        mOkv = (View) findViewById(R.id.okv);
        mOk = (Button) findViewById(R.id.ok);
        mOk.setOnClickListener(this);
        mPoss = new Button[]{mPos1, mPos2, mPos3, mPos4};
        mVs = new View[]{mV1, mV2, mV3};
        mPvs = new View[]{mPv1, mPv2, mPv3, mPv4};
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.beak:
                finish();
                break;
            case R.id.car1:
                lineId = 1;
                setv();
                mV1.setVisibility(View.INVISIBLE);
                break;
            case R.id.car2:
                lineId = 2;
                setv();
                mV2.setVisibility(View.INVISIBLE);
                break;
            case R.id.car3:
                lineId = 3;
                setv();
                mV3.setVisibility(View.INVISIBLE);
                break;
            case R.id.pos1:
                pos = 0;
                setpv();
                mPv1.setVisibility(View.INVISIBLE);
                break;
            case R.id.pos2:
                pos = 1;
                setpv();
                mPv2.setVisibility(View.INVISIBLE);
                break;
            case R.id.pos3:
                pos = 2;
                setpv();
                mPv3.setVisibility(View.INVISIBLE);
                break;
            case R.id.pos4:
                pos = 3;
                setpv();
                mPv4.setVisibility(View.INVISIBLE);
                break;
            case R.id.ok:
                mOkv.setVisibility(View.INVISIBLE);
                createLine();
                Timer timer1 = new Timer();
                timer1.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mOkv.setVisibility(View.VISIBLE);
                                timer1.cancel();
                            }
                        });
                    }
                }, 1000, 1000);
                break;
        }
    }

    private void createLine() {
        MyOk.post("Interface/index/createStudentLine", "lineId=" + lineId + "&pos=" + pos, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                        String s = jsonObject.getString("message");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Item9.this, s, Toast.LENGTH_SHORT).show();
                            }
                        });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
        private void setpv () {
            for (View mV : mPvs) {
                mV.setVisibility(View.VISIBLE);
            }
        }

        private void setv () {
            for (View mV : mVs) {
                mV.setVisibility(View.VISIBLE);
            }
        }
    }
