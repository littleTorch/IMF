package com.lenovo.manufacture.Item2;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.R;

import java.util.ArrayList;
import java.util.List;

public class Item21 extends AppCompatActivity implements View.OnClickListener {

    private Button mBeak;
    /**
     * 标题
     */
    private TextView mHead;
    private ImageView mIv1;
    private ImageView mIv2;
    private TableLayout mTab;
    private int a = 0, b = 0;
    List<Shop> shops;
    private int total;
    private TextView mTotal;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item21);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initView() {
        mBeak = (Button) findViewById(R.id.beak);
        mBeak.setOnClickListener(this);
        mHead = (TextView) findViewById(R.id.head);
        mIv1 = (ImageView) findViewById(R.id.iv1);
        mIv1.setOnClickListener(this);
        mIv2 = (ImageView) findViewById(R.id.iv2);
        mIv2.setOnClickListener(this);
        mTab = (TableLayout) findViewById(R.id.tab);
        mHead.setText("已购商品");
        getshop();
        mTotal = (TextView) findViewById(R.id.total);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getshop() {
        shops = new ArrayList<>();
        List<Shop> shopps = new ArrayList<>();
        shopps = (ArrayList<Shop>) getIntent().getSerializableExtra("shop");
        if (!shopps.isEmpty()) {
            shopps.sort((o1, o2) -> o2.getPrice() / o2.getNum() - o1.getPrice() / o1.getNum());
            total = 0;
            for (int i = 0; i < 4; i++) {
                Shop shop = shopps.get(i);
                total += shop.getPrice() * shop.getNum();
                shops.add(shop);
            }
        }
        mTotal.setText(total+"");
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
            case R.id.iv1:
                a = 1;
                setiv(mIv1, mIv2);
                break;
            case R.id.iv2:
                a = 2;
                setiv(mIv2, mIv1);
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
                View view = View.inflate(Item21.this, R.layout.item2_add, null);
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
}
