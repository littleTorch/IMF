package com.lenovo.manufacture.Item6;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class Item6 extends AppCompatActivity implements View.OnClickListener {

    private Button mBeak;
    private TextView mHead;
    private TextView mTop1;
    private View mV1;
    private TextView mTop2;
    private View mV2;
    private TextView mTop3;
    private View mV3;
    private TextView mTop4;
    private View mV4;
    private ListView mLv1;
    private List<Mes> mess;
    private ColorStateList textColors;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item6);
        initView();
        handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                List<Mes> list=new ArrayList<>();
                for (Mes mes : mess) {
                    if (mes.getStatus()==msg.what){
                        list.add(mes);
                    }
                }
                addList(list);
            }
        };
    }

    private void addList(List<Mes> list) {
        ListAdapter adapter=new ListAdapter(Item6.this,R.layout.item6_item,list);
        mLv1.setAdapter(adapter);
    }

    private void initView() {
        mBeak = (Button) findViewById(R.id.beak);
        mBeak.setOnClickListener(this);
        mHead = (TextView) findViewById(R.id.head);
        mTop1 = (TextView) findViewById(R.id.top1);
        mV1 = (View) findViewById(R.id.v1);
        mTop2 = (TextView) findViewById(R.id.top2);
        mV2 = (View) findViewById(R.id.v2);
        mTop3 = (TextView) findViewById(R.id.top3);
        mV3 = (View) findViewById(R.id.v3);
        mTop4 = (TextView) findViewById(R.id.top4);
        mV4 = (View) findViewById(R.id.v4);
        mLv1 = (ListView) findViewById(R.id.Lv1);
        mHead.setText("汽车资讯");
        textColors = mTop1.getTextColors();
        mTop1.setOnClickListener(this);
        mTop2.setOnClickListener(this);
        mTop3.setOnClickListener(this);
        mTop4.setOnClickListener(this);
        getmsg();
        mLv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv1=view.findViewById(R.id.tv1);
                TextView tv2=view.findViewById(R.id.tv2);
                TextView tv3=view.findViewById(R.id.tv3);
                Intent intent=new Intent();
                intent.setClass(Item6.this,Item6_1.class);
                intent.putExtra("top",tv1.getText());
                intent.putExtra("time",tv3.getText());
                intent.putExtra("msg",tv2.getText());
                startActivity(intent);
            }
        });
    }

    private void getmsg() {
        MyOk.post("dataInterface/Information/getAll", "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject=new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("200")){
                        JSONArray array=jsonObject.getJSONArray("data");
                        mess=new ArrayList<>();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j=array.getJSONObject(i);
                            Mes mes=new Mes();
                            mes=new Gson().fromJson(j.toString(),Mes.class);
                            mess.add(mes);
                        }
                        Item6.this.handler.sendMessage(MyOk.getMessage(0,""));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class ListAdapter extends ArrayAdapter<Mes>{
        private int res;
        public ListAdapter(@NonNull Context context, int resource, @NonNull List<Mes> objects) {
            super(context, resource, objects);
            res=resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Mes mes=getItem(position);
            View view= LayoutInflater.from(getContext()).inflate(res,parent,false);
            TextView tv1=view.findViewById(R.id.tv1);
            TextView tv2=view.findViewById(R.id.tv2);
            TextView tv3=view.findViewById(R.id.tv3);
            tv1.setText(mes.getInformationName());
            tv2.setText(mes.getWords());
            tv3.setText(mes.getTime());
            return view;
        }
    }

    @Override
    public void onClick(View v) {
        reView();
        switch (v.getId()) {
            default:
                break;
            case R.id.beak:
                finish();
                break;
            case R.id.top1:
                mTop1.setTextColor(textColors);
                mV1.setVisibility(View.VISIBLE);
                Item6.this.handler.sendMessage(MyOk.getMessage(0,""));
                break;
            case R.id.top2:
                mTop2.setTextColor(textColors);
                mV2.setVisibility(View.VISIBLE);
                Item6.this.handler.sendMessage(MyOk.getMessage(1,""));
                break;
            case R.id.top3:
                mTop3.setTextColor(textColors);
                mV3.setVisibility(View.VISIBLE);
                Item6.this.handler.sendMessage(MyOk.getMessage(2,""));
                break;
            case R.id.top4:
                mTop4.setTextColor(textColors);
                mV4.setVisibility(View.VISIBLE);
                Item6.this.handler.sendMessage(MyOk.getMessage(3,""));
                break;
        }
    }

    public void reView() {
        mTop1.setTextColor(Color.BLACK);
        mTop2.setTextColor(Color.BLACK);
        mTop3.setTextColor(Color.BLACK);
        mTop4.setTextColor(Color.BLACK);
        mV1.setVisibility(View.INVISIBLE);
        mV2.setVisibility(View.INVISIBLE);
        mV3.setVisibility(View.INVISIBLE);
        mV4.setVisibility(View.INVISIBLE);
    }
}
