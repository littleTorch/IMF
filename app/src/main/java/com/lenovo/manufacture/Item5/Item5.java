package com.lenovo.manufacture.Item5;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
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

public class Item5 extends AppCompatActivity implements View.OnClickListener {

    private Button mBeak;
    private TextView mHead;
    private ImageView[] mIms;
    private ImageView[] mIas;
    private Timer timer;
    private I5pop pop;
    private int nowstepId,lineId;
    private List<Integer> stepId;
    List<SM> sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item5);
        initView();
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getLine();
            }
        },0,5000);
    }

    private void getLine() {
        MyOk.post("dataInterface/UserProductionLine/search", "position=3", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject=new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("200")){
                        JSONArray array=jsonObject.getJSONArray("data");
                            JSONObject j=array.getJSONObject(0);
                            lineId=j.getInt("id");
                            nowstepId=j.getInt("stageId");
                            getAllStep();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getAllStep() {
        MyOk.post("dataInterface/UserPlStep/search", "userProductionLineId="+lineId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("200")){
                        JSONArray array=jsonObject.getJSONArray("data");
                        stepId=new ArrayList<>();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j=array.getJSONObject(i);
                            Log.d("Item5", j.toString());
                            if (j.getInt("nextUserPlStepId")!=-1) {
                                stepId.add(j.getInt("nextUserPlStepId"));
                            }
                        }
                        stepId.sort(((o1, o2) -> o2-o1));
                        for (Integer integer : stepId) {
                            Log.d("Item5", "integer:" + integer);
                        }
                        stepId.add(stepId.get(18)-1);
                        getstepmsg();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getstepmsg() {
        MyOk.post("dataInterface/PLStep/getAll", "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("200")){
                        JSONArray array=jsonObject.getJSONArray("data");
                        sms=new ArrayList<>();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j=array.getJSONObject(i);
                            SM sm=new SM();
                            sm=new Gson().fromJson(j.toString(),SM.class);
                            for (Integer integer : stepId) {
                                if (integer==sm.getId()){
                                    sms.add(sm);
                                }
                            }
                        }
                        runOnUiThread(new Runnable() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void run() {
                                sms.sort((o1, o2) -> o1.getId()-o2.getId());
                                setia();
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

    private void setia() {
        for (ImageView mIm : mIas) {
            mIm.setVisibility(View.INVISIBLE);
        }
        for (int i = 0; i < sms.size(); i++) {
            SM sm = sms.get(i);
            if (sm.getPower()<sm.getConsume()){
                mIas[i].setVisibility(View.VISIBLE);
            }
        }
    }

    private void addview() {
        for (int i = 0; i < mIms.length; i++) {
            int finalI = i;
            mIms[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop.dismiss();
                    int xoff=0,yoff=0,g=Gravity.START;
                    if (finalI ==6){
                        xoff=-pop.getContentView().getMeasuredWidth();
                        yoff=-(pop.getContentView().getMeasuredHeight()+mIms[finalI].getHeight())/2;
                    }else if (finalI ==7){
                        xoff=-pop.getContentView().getMeasuredWidth();
                        yoff=-(pop.getContentView().getMeasuredHeight()+mIms[finalI].getHeight());
                    }else if (finalI ==13){
                        g=Gravity.END;
                        yoff=-(pop.getContentView().getMeasuredHeight()+mIms[finalI].getHeight())/2;
                    }else if (finalI ==14){
                        g=Gravity.END;
                        yoff=-(pop.getContentView().getMeasuredHeight()+mIms[finalI].getHeight());
                    }else{
                        xoff=Math.abs(pop.getContentView().getMeasuredWidth()-mIms[finalI].getWidth())/2;
                        yoff=-(pop.getContentView().getMeasuredHeight()+mIms[finalI].getHeight());
                    }
                    pop.setview(finalI,sms);
                    PopupWindowCompat.showAsDropDown(pop,mIms[finalI],xoff,yoff,g);
                }
            });
            mIas[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop.dismiss();
                    int xoff=0,yoff=0,g=Gravity.START;
                    if (finalI ==6){
                        xoff=-pop.getContentView().getMeasuredWidth();
                        yoff=-(pop.getContentView().getMeasuredHeight()+mIms[finalI].getHeight())/2;
                    }else if (finalI ==7){
                        xoff=-pop.getContentView().getMeasuredWidth();
                        yoff=-(pop.getContentView().getMeasuredHeight()+mIms[finalI].getHeight());
                    }else if (finalI ==12){
                        g=Gravity.END;
                    }else if (finalI ==13){
                        g=Gravity.END;
                        yoff=-(pop.getContentView().getMeasuredHeight()+mIms[finalI].getHeight())/2;
                    }else if (finalI ==14){
                        g=Gravity.END;
                        yoff=-(pop.getContentView().getMeasuredHeight()+mIms[finalI].getHeight());
                    }else{
                        xoff=Math.abs(pop.getContentView().getMeasuredWidth()-mIms[finalI].getWidth())/2;
                        yoff=-(pop.getContentView().getMeasuredHeight()+mIms[finalI].getHeight());
                    }
                    pop.setview(finalI,sms);
                    PopupWindowCompat.showAsDropDown(pop,mIms[finalI],xoff,yoff,g);
                }
            });
        }
    }

    private void initView() {
        mBeak = (Button) findViewById(R.id.beak);
        mBeak.setOnClickListener(this);
        mHead = (TextView) findViewById(R.id.head);
        mHead.setText("生产监控及修复");
        mIms = new ImageView[20];
        mIas = new ImageView[20];
        for (int i = 0; i < mIms.length; i++) {
            int j=i+1;
            mIms[i]=findViewById(getResources().getIdentifier("Im"+j,"id",getPackageName()));
            mIas[i]=findViewById(getResources().getIdentifier("Ia"+j,"id",getPackageName()));
            mIas[i].setVisibility(View.INVISIBLE);
        }
        pop=new I5pop(Item5.this);
        pop.getContentView().measure(make(pop.getWidth()),make(pop.getHeight()));
    }

    @SuppressWarnings("ResourceType")
    private static int make(int mea){
        int mode;
        if (mea== GridView.LayoutParams.WRAP_CONTENT){
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
