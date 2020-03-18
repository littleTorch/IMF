package com.lenovo.manufacture.Item7;

import android.graphics.Color;
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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.gson.Gson;
import com.lenovo.manufacture.MyOk;
import com.lenovo.manufacture.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Item7 extends AppCompatActivity implements View.OnClickListener {

    private Button mBeak;
    private TextView mHead;
    private TextView mTotal;
    private TextView mCar;
    private TextView mMpv;
    private TextView mSuv;
    private ImageView mIv1;
    private ImageView mIv2;
    private ImageView mIv3;
    private TableLayout mTab;
    private List<PutCar> putCars;
    private int a = 0, b = 0;
    private int car, mpv, suv, total;
    private LineChart mLc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item7);
        initView();
        getjson();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addview() {
        if (a == 1) {
            if (b == 1) {
                putCars.sort((o1, o2) -> o2.getPrice() - o1.getPrice());
            } else {
                putCars.sort((o1, o2) -> o1.getPrice() - o2.getPrice());
            }
        } else if (a == 2) {
            if (b == 1) {
                putCars.sort((o1, o2) -> o2.getTime() - o1.getTime());
            } else {
                putCars.sort((o1, o2) -> o1.getTime() - o2.getTime());
            }
        } else if (a == 3) {
            if (b == 1) {
                putCars.sort((o1, o2) -> o2.getNum() - o1.getNum());
            } else {
                putCars.sort((o1, o2) -> o1.getNum() - o2.getNum());
            }
        }
        mTab.removeAllViews();
        for (PutCar putCar : putCars) {
            View view = View.inflate(Item7.this, R.layout.item7_add, null);
            TextView tv1 = view.findViewById(R.id.tv1);
            TextView tv2 = view.findViewById(R.id.tv2);
            TextView tv3 = view.findViewById(R.id.tv3);
            TextView tv4 = view.findViewById(R.id.tv4);
            tv1.setText(putCar.getCarTypeName() + "");
            tv1.setText(new DecimalFormat("###,###,###").format(putCar.getPrice()) + "");
            tv1.setText(new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(new Date(putCar.getTime() * 1000)));
            tv1.setText(putCar.getNum() + "");
            mTab.addView(view);
        }
    }

    private void getjson() {
        MyOk.post("Interface/index/userSellInfoTEditer", "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("200")) {
                        JSONArray array = jsonObject.getJSONArray("data");
                        putCars = new ArrayList<>();
                        car = 0;
                        mpv = 0;
                        suv = 0;
                        total = 0;
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j = array.getJSONObject(i);
                            PutCar putCar = new PutCar();
                            putCar = new Gson().fromJson(j.toString(), PutCar.class);
                            if (putCar.getCarTypeId() == 1) {
                                car += putCar.getPrice() * putCar.getNum();
                            } else if (putCar.getCarTypeId() == 2) {
                                mpv += putCar.getPrice() * putCar.getNum();
                            } else if (putCar.getCarTypeId() == 3) {
                                suv += putCar.getPrice() * putCar.getNum();
                            }
                            total += putCar.getPrice() * putCar.getNum();
                            putCars.add(putCar);
                        }
                        runOnUiThread(new Runnable() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void run() {
                                mTotal.setText(total + "");
                                mCar.setText(car + "");
                                mMpv.setText(mpv + "");
                                mSuv.setText(suv + "");
                                addview();
                                setLinechart();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setLinechart() {
        mLc.getDescription().setEnabled(false);
        mLc.setDragEnabled(false);
        List<Entry> list=new ArrayList<>();
        for (int i = 0; i < putCars.size(); i++){
            list.add(new Entry(i,putCars.get(i).getPrice()));
        }
        LineDataSet dataSet=new LineDataSet(list,"");
        dataSet.setColor(Color.RED);
        dataSet.setDrawCircles(false);
        LineData lineData=new LineData();
        lineData.addDataSet(dataSet);
        XAxis xAxis = mLc.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return new SimpleDateFormat("MM月dd日").format(new Date(putCars.get((int)value).getTime()))+"";
            }
        });
        mLc.getAxisRight().setEnabled(false);
        YAxis axisLeft = mLc.getAxisLeft();
        axisLeft.setDrawGridLines(false);
        mLc.setData(lineData);
        mLc.invalidate();
    }

    private void initView() {
        mBeak = (Button) findViewById(R.id.beak);
        mBeak.setOnClickListener(this);
        mHead = (TextView) findViewById(R.id.head);
        mTotal = (TextView) findViewById(R.id.total);
        mCar = (TextView) findViewById(R.id.car);
        mMpv = (TextView) findViewById(R.id.mpv);
        mSuv = (TextView) findViewById(R.id.suv);
        mIv1 = (ImageView) findViewById(R.id.iv1);
        mIv1.setOnClickListener(this);
        mIv2 = (ImageView) findViewById(R.id.iv2);
        mIv2.setOnClickListener(this);
        mIv3 = (ImageView) findViewById(R.id.iv3);
        mIv3.setOnClickListener(this);
        mTab = (TableLayout) findViewById(R.id.tab);
        mHead.setText("销售报表");
        mLc = (LineChart) findViewById(R.id.lc);
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
                setiv(mIv1, mIv2, mIv3);
                break;
            case R.id.iv2:
                a = 2;
                setiv(mIv2, mIv1, mIv3);
                break;
            case R.id.iv3:
                a = 3;
                setiv(mIv3, mIv2, mIv1);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setiv(ImageView Iv1, ImageView Iv2, ImageView Iv3) {
        Drawable.ConstantState v1 = Iv1.getDrawable().getConstantState();
        Drawable.ConstantState v2 = Iv2.getDrawable().getConstantState();
        Drawable.ConstantState v3 = Iv3.getDrawable().getConstantState();
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
        if (v3.equals(t1) || v3.equals(t4)) {
            Iv3.setImageResource(R.drawable.triangle0004);
        } else {
            Iv3.setImageResource(R.drawable.triangle0002);
        }
        addview();
    }
}
