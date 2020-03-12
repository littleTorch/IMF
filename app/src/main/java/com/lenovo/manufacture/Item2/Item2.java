package com.lenovo.manufacture.Item2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.lenovo.manufacture.MyOk;
import com.lenovo.manufacture.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Item2 extends AppCompatActivity implements View.OnClickListener {

    private Button mBeak;
    private TextView mHead;
    private ImageView mShop;
    private ImageView mIv1;
    private ImageView mIv2;
    private TableLayout mTab;
    private Timer timer;
    private int a = 0, b = 0;
    private List<Shop> shops;
    private Button mBut;
    private Intent intent=new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item2);
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
        MyOk.post("Interface/index/getMaterial", "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("200")) {
                        shops = new ArrayList<>();
                        JSONArray array = jsonObject.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j = array.getJSONObject(i);
                            Shop shop = new Shop();
                            shop = new Gson().fromJson(j.toString(), Shop.class);
                            Log.d("Item2", "shop:" + shop);
                            shops.add(shop);
                        }
                        runOnUiThread(new Runnable() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void run() {
                                addview();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addview() {
        mTab.removeAllViews();
        if (!shops.isEmpty()) {
            if (a == 1) {
                if (b == 1) {
                    shops.sort(((o1, o2) -> o2.getPrice() - o1.getPrice()));
                } else {
                    shops.sort(((o1, o2) -> o1.getPrice() - o2.getPrice()));
                }
            } else if (a == 2) {
                if (b == 1) {
                    shops.sort(((o1, o2) -> o2.getNum() - o1.getNum()));
                } else {
                    shops.sort(((o1, o2) -> o1.getNum() - o2.getNum()));
                }
            }
            for (int i = 0; i < shops.size(); i++) {
                Shop shop = shops.get(i);
                View view = View.inflate(Item2.this, R.layout.item2_add, null);
                TextView tv1 = view.findViewById(R.id.tv1);
                TextView tv2 = view.findViewById(R.id.tv2);
                TextView tv3 = view.findViewById(R.id.tv3);
                TextView tv4 = view.findViewById(R.id.tv4);
                TextView tv5 = view.findViewById(R.id.tv5);
                tv1.setText(shop.getId() + "");
                tv2.setText(shop.getMaterialName() + "");
                tv3.setText(shop.getContent() + "");
                tv4.setText(shop.getPrice() + "");
                tv5.setText(shop.getNum() + "");
                mTab.addView(view);
            }
        }
    }

    private void initView() {
        mBeak = (Button) findViewById(R.id.beak);
        mBeak.setOnClickListener(this);
        mHead = (TextView) findViewById(R.id.head);
        mShop = (ImageView) findViewById(R.id.shop);
        mShop.setOnClickListener(this);
        mIv1 = (ImageView) findViewById(R.id.iv1);
        mIv1.setOnClickListener(this);
        mIv2 = (ImageView) findViewById(R.id.iv2);
        mIv2.setOnClickListener(this);
        mTab = (TableLayout) findViewById(R.id.tab);
        mHead.setText("商品列表");
        mShop.setVisibility(View.VISIBLE);
        mBut = (Button) findViewById(R.id.but);
        mBut.setOnClickListener(this);
        intent.setClass(Item2.this, Item21.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.beak:
                finish();
                break;
            case R.id.shop:
                startActivity(intent);
                break;
            case R.id.iv1:
                a = 1;
                setiv(mIv1, mIv2);
                break;
            case R.id.iv2:
                a = 2;
                setiv(mIv2, mIv1);
                break;
            case R.id.but:
                Bundle bundle = new Bundle();
                bundle.putSerializable("shop", (Serializable) shops);
                intent.putExtras(bundle);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setiv(ImageView Iv1, ImageView Iv2) {
        Drawable.ConstantState v1 = Iv1.getDrawable().getConstantState();
        Drawable.ConstantState v2 = Iv2.getDrawable().getConstantState();
        Drawable.ConstantState t1 = getDrawable(R.drawable.triangle0001).getConstantState();
        Drawable.ConstantState t2 = getDrawable(R.drawable.triangle0002).getConstantState();
        Drawable.ConstantState t3 = getDrawable(R.drawable.triangle0003).getConstantState();
        Drawable.ConstantState t4 = getDrawable(R.drawable.triangle0004).getConstantState();
        if (v1.equals(t1) || v1.equals(t4)) {
            b = 1;
            Iv1.setImageResource(R.drawable.triangle0003);
        } else {
            b = 2;
            Iv1.setImageResource(R.drawable.triangle0001);
        }
        if (v2.equals(t1) || v2.equals(t4)) {
            Iv2.setImageResource(R.drawable.triangle0004);
        } else {
            Iv2.setImageResource(R.drawable.triangle0002);
        }
        addview();
    }
}
