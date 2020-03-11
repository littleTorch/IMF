package com.lenovo.manufacture.Item1;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Item1 extends AppCompatActivity implements View.OnClickListener {

    private Button mBeak;
    private TextView mHead;
    private LinearLayout mLin1;
    private LinearLayout mLine1;
    private LinearLayout mLine2;
    private LinearLayout mLine3;
    private LinearLayout mLine4;
    private LinearLayout[] mLines;
    Timer timer;
    List<Worker> workers;
    List<Line> lines;
    List<LineWorker> lineWorkers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item1);
        initView();
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getAllWorker();
            }
        },0,50000);
    }

    private void getAllWorker() {
        MyOk.post("dataInterface/People/getAll", "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject=new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("200")){
                        workers=new ArrayList<>();
                        JSONArray array=jsonObject.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j=array.getJSONObject(i);
                            Worker worker=new Worker();
                            worker=new Gson().fromJson(j.toString(),Worker.class);
                            workers.add(worker);
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
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("200")) {
                        lines=new ArrayList<>();
                        JSONArray array = jsonObject.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j = array.getJSONObject(i);
                            Line line = new Line();
                            line=new Gson().fromJson(j.toString(),Line.class);
                            lines.add(line);
                        }
                        getLineWorker();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getLineWorker() {
        MyOk.post("dataInterface/UserPeople/getAll", "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("200")) {
                        lineWorkers = new ArrayList<>();
                        JSONArray array = jsonObject.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j = array.getJSONObject(i);
                            LineWorker lineWorker=new LineWorker();
                            lineWorker=new Gson().fromJson(j.toString(),LineWorker.class);
                            lineWorkers.add(lineWorker);
                        }
                        runOnUiThread(new Runnable() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void run() {
                                addWorker();
                                addLineWorker();
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
    private void addLineWorker() {
        for (int i = 0; i < 4; i++) {
            mLines[i].removeAllViews();
            View view= LayoutInflater.from(Item1.this).inflate(R.layout.item1_add2,null);
            TextView pos=view.findViewById(R.id.pos);
            pos.setText(i+1+"");
            TextView name1=view.findViewById(R.id.name1);
            TextView name2=view.findViewById(R.id.name2);
            TextView name3=view.findViewById(R.id.name3);
            TextView name4=view.findViewById(R.id.name4);
            TextView pos1=view.findViewById(R.id.pos1);
            TextView pos2=view.findViewById(R.id.pos2);
            TextView pos3=view.findViewById(R.id.pos3);
            TextView pos4=view.findViewById(R.id.pos4);
            ProgressBar pb1=view.findViewById(R.id.pb1);
            ProgressBar pb2=view.findViewById(R.id.pb2);
            ProgressBar pb3=view.findViewById(R.id.pb3);
            ProgressBar pb4=view.findViewById(R.id.pb4);
            TextView[] names=new TextView[]{name1,name2,name3,name4};
            TextView[] poss=new TextView[]{pos1,pos2,pos3,pos4};
            ProgressBar[] pbs=new ProgressBar[]{pb1,pb2,pb3,pb4};
            List<LW> lws=new ArrayList<>();
            for (Line line : lines) {
                if (line.getPosition()==i){
                    for (LineWorker lineWorker : lineWorkers) {
                        if (line.getId()==lineWorker.getUserProductionLineId()) {
                            for (Worker worker : workers) {
                                if (worker.getId() == lineWorker.getPeopleId()) {
                                    LW lw = new LW(lineWorker.getPower(), worker.getPeopleName(), worker.getStatus());
                                    lws.add(lw);
                                    Log.d("Item1", "lw:" + lw);
                                }
                            }
                        }
                    }
                    lws.sort(((o1, o2) -> o2.getStatus().compareTo(o1.getStatus())));
                    for (int j = 0; j < 4; j++) {
                        LW lw = lws.get(j);
                        names[j].setText(lw.getPeopleName()+"");
                        poss[j].setText(lw.getPower()+"");
                        pbs[j].setProgress(lw.getPower());
                    }
                }
            }
            mLines[i].addView(view,LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        }
    }

    private void addWorker() {
        mLin1.removeAllViews();
        for (Worker worker : workers) {
            View view= LayoutInflater.from(Item1.this).inflate(R.layout.item1_add1,null);
            TextView tv1=view.findViewById(R.id.tv1);
            TextView tv2=view.findViewById(R.id.tv2);
            tv1.setText(worker.getPeopleName());
            tv2.setText(worker.getS());
            mLin1.addView(view);
        }
    }

    private void initView() {
        mBeak = (Button) findViewById(R.id.beak);
        mBeak.setOnClickListener(this);
        mHead = (TextView) findViewById(R.id.head);
        mLin1 = (LinearLayout) findViewById(R.id.lin1);
        mLine1 = (LinearLayout) findViewById(R.id.line1);
        mLine2 = (LinearLayout) findViewById(R.id.line2);
        mLine3 = (LinearLayout) findViewById(R.id.line3);
        mLine4 = (LinearLayout) findViewById(R.id.line4);
        mHead.setText("人事管理");
        mLines=new LinearLayout[]{mLine1,mLine2,mLine3,mLine4};
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
