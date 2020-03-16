package com.lenovo.manufacture.Item4;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.PopupWindowCompat;

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

public class Item4 extends AppCompatActivity implements View.OnClickListener {

    private Button mBeak;
    private TextView mHead;
    private ImageView[] mIms;
    private Timer timer;
    private int lineId;
    private int nowStepid;
    private List<Integer> allSteps;
    private List<StepMsg> stepMsgs;
    private TextView mTop;
    private I4popup pop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item4);
        initView();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getLine();
            }
        }, 0, 3000);
    }

    private void getLine() {
        MyOk.post("dataInterface/UserProductionLine/search", "position=2", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("200")) {
                        JSONArray array = jsonObject.getJSONArray("data");
                        JSONObject j = array.getJSONObject(0);
                        lineId = j.getInt("id");
                        nowStepid = j.getInt("stageId");
                        getAllStep();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getAllStep() {
        MyOk.post("dataInterface/UserPlStep/search", "userProductionLineId=" + lineId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("200")) {
                        JSONArray array = jsonObject.getJSONArray("data");
                        allSteps = new ArrayList<>();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j = array.getJSONObject(i);
                            int id = j.getInt("nextUserPlStepId");
                            if (j.getInt("nextUserPlStepId") != -1) {
                                allSteps.add(id - 1);
                            }
                        }
                        allSteps.sort(((o1, o2) -> o2 - o1));
                        allSteps.add(allSteps.get(18) + 1);
                        getStep();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getStep() {
        MyOk.post("dataInterface/Stage/getAll", "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("200")) {
                        stepMsgs = new ArrayList<>();
                        JSONArray array = jsonObject.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j = array.getJSONObject(i);
                            StepMsg stepMsg = new StepMsg();
                            stepMsg = new Gson().fromJson(j.toString(), StepMsg.class);
                            for (Integer integer : allSteps) {
                                if (integer == stepMsg.getPlStepId()) {
                                    stepMsgs.add(stepMsg);
                                }
                            }
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
    private void addview(){
        pop.dismiss();
        stepMsgs.sort((o1, o2) -> o1.getPlStepId() - o2.getPlStepId());
        for (int i = 0; i < stepMsgs.size(); i++) {
            StepMsg stepMsg = stepMsgs.get(i);
            mIms[i].setImageResource(R.drawable.yuan_w);
            if (stepMsg.getPlStepId() == nowStepid) {
                mIms[i].setImageResource(R.drawable.yuan_r);
                int j=i+1;
                mTop.setText("第"+j+"步-"+stepMsg.getContent());
                int xoff=0,yoff=0,g=Gravity.START;
                if (i==6){
                    xoff=-pop.getContentView().getMeasuredWidth();
                    yoff=-(pop.getContentView().getMeasuredHeight()+mIms[i].getHeight())/2;
                }else if (i==7){
                    xoff=-pop.getContentView().getMeasuredWidth();
                    yoff=-(pop.getContentView().getMeasuredHeight()+mIms[i].getHeight());
                }else if (i==12){
                    g=Gravity.END;
                }else if (i==13){
                    g=Gravity.END;
                    yoff=-(pop.getContentView().getMeasuredHeight()+mIms[i].getHeight())/2;
                }else if (i==14){
                    g=Gravity.END;
                    yoff=-(pop.getContentView().getMeasuredHeight()+mIms[i].getHeight());
                }else{
                    xoff=Math.abs(pop.getContentView().getMeasuredWidth()-mIms[i].getWidth())/2;
                    yoff=-(pop.getContentView().getMeasuredHeight()+mIms[i].getHeight());
                }
                PopupWindowCompat.showAsDropDown(pop,mIms[i],xoff,yoff,g);
            }
        }
    }

    private void initView() {
        mBeak = (Button) findViewById(R.id.beak);
        mBeak.setOnClickListener(this);
        mHead = (TextView) findViewById(R.id.head);
        pop=new I4popup(Item4.this);
        mHead.setText("生产线查看");
        mIms = new ImageView[20];
        for (int i = 0; i < 20; i++) {
            int j = i + 1;
            mIms[i] = findViewById(getResources().getIdentifier("Im" + j, "id", getPackageName()));
        }
        mTop = (TextView) findViewById(R.id.top);
        View contentView = pop.getContentView();
        contentView.measure(make(pop.getWidth()),make(pop.getHeight()));
    }

    @SuppressWarnings("ResourceType")
    private static int make(int mea){
        int mode;
        if (mea== ViewGroup.LayoutParams.WRAP_CONTENT){
            mode=View.MeasureSpec.UNSPECIFIED;
        }else{
            mode=View.MeasureSpec.EXACTLY;
        }
        return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(mea),mode);
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