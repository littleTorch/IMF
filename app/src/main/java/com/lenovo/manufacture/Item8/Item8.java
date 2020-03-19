package com.lenovo.manufacture.Item8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.lenovo.manufacture.MyOk;
import com.lenovo.manufacture.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Item8 extends AppCompatActivity implements View.OnClickListener {

    private Button mBeak;
    private TextView mHead;
    private LinearLayout mLin;
    private LinearLayout mLin1;
    private LinearLayout mLin2;
    private LinearLayout mLin3;
    private LinearLayout mLin4;
    private LinearLayout[] mLins;
    private List<Worker> workerList;
    private List<Line> lineList;
    private List<LineWorker> LWs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item8);
        initView();
        getworker();
    }

    private void getworker() {
        MyOk.post("dataInterface/People/getAll", "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject=new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("200")){
                        JSONArray array=jsonObject.getJSONArray("data");
                        workerList=new ArrayList<>();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j=array.getJSONObject(i);
                            Worker worker=new Worker();
                            worker=new Gson().fromJson(j.toString(),Worker.class);
                            workerList.add(worker);
                        }
                        getLine();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getLine() {
        MyOk.post("dataInterface/UserProductionLine/getAll", "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject=new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("200")){
                        JSONArray array=jsonObject.getJSONArray("data");
                        lineList=new ArrayList<>();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j=array.getJSONObject(i);
                            Line line=new Line();
                            line=new Gson().fromJson(j.toString(),Line.class);
                            lineList.add(line);
                        }
                        getLW();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getLW() {
        MyOk.post("dataInterface/UserPeople/getAll", "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject=new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("200")){
                        JSONArray array=jsonObject.getJSONArray("data");
                        LWs=new ArrayList<>();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j=array.getJSONObject(i);
                            LineWorker lineWorker=new LineWorker();
                            lineWorker=new Gson().fromJson(j.toString(),LineWorker.class);
                            LWs.add(lineWorker);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                addViewF();
                                addViewR();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void addViewR() {
        List<LW> lws=new ArrayList<>();
        for (Line line : lineList) {
            for (LineWorker lineWorker : LWs) {
                if (lineWorker.getUserProductionLineId()==line.getId()){
                    for (Worker worker : workerList) {
                        if (worker.getId()==lineWorker.getPeopleId()){
                            LW lw=new LW(worker.getPeopleName(),worker.getStatus(),worker.getHp(),line.getPosition());
                            lws.add(lw);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < mLins.length; i++) {
            mLins[i].removeAllViews();
            View view=LayoutInflater.from(Item8.this).inflate(R.layout.item8_add2,null);
            TextView top=view.findViewById(R.id.top);
            top.setText(i+1+"号生产线岗位安排");
            TextView[] tvs=new TextView[4];
            TextView[] pws=new TextView[4];
            ProgressBar[] pbs=new ProgressBar[4];
            for (int j = 0; j < 4; j++) {
                int k=j+1;
                tvs[j]=view.findViewById(getResources().getIdentifier("tv"+k,"id",getPackageName()));
                pws[j]=view.findViewById(getResources().getIdentifier("pw"+k,"id",getPackageName()));
                pbs[j]=view.findViewById(getResources().getIdentifier("pb"+k,"id",getPackageName()));
            }
            for (LW lw : lws) {
                if (lw.getPosition()==i){
                    tvs[lw.getStatus()].setText(lw.getPeopleName()+"");
                    pws[lw.getStatus()].setText(lw.getHp()+"");
                    pbs[lw.getStatus()].setProgress(lw.getHp());
                }
            }
            mLins[i].addView(view);
        }
    }

    private void addViewF() {
        mLin.removeAllViews();
        for (Worker worker : workerList) {
            View view= LayoutInflater.from(Item8.this).inflate(R.layout.item8_add1,null);
            TextView tv1=view.findViewById(R.id.tv1);
            TextView tv2=view.findViewById(R.id.tv2);
            tv1.setText(worker.getPeopleName()+"");
            tv2.setText(worker.getContent()+"");
            mLin.addView(view);
        }
    }

    private void initView() {
        mBeak = (Button) findViewById(R.id.beak);
        mBeak.setOnClickListener(this);
        mHead = (TextView) findViewById(R.id.head);
        mLin = (LinearLayout) findViewById(R.id.lin);
        mLin1 = (LinearLayout) findViewById(R.id.lin1);
        mLin2 = (LinearLayout) findViewById(R.id.lin2);
        mLin3 = (LinearLayout) findViewById(R.id.lin3);
        mLin4 = (LinearLayout) findViewById(R.id.lin4);
        mLins=new LinearLayout[]{mLin1,mLin2,mLin3,mLin4};
        mHead.setText("人事管理");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.beak:
                finish();
                break;
        }
    }
}
