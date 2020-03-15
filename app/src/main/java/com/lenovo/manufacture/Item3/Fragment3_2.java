package com.lenovo.manufacture.Item3;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lenovo.manufacture.Item2.Shop;
import com.lenovo.manufacture.MyOk;
import com.lenovo.manufacture.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Fragment3_2 extends Fragment {
    private Handler handler;
    private List<CarOrder> carOrders;
    private TableLayout mTab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment3_2, container, false);
        mTab=view.findViewById(R.id.tab);
        getjson();
        handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                addview();
            }
        };
        return view;
    }

    private void getjson() {
        MyOk.post("dataInterface/UserAppointment/getAll", "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) { }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONObject jsonObject= null;
                try {
                    jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("200")) {
                        carOrders = new ArrayList<>();
                        JSONArray array = jsonObject.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject j = array.getJSONObject(i);
                            CarOrder carOrder = new CarOrder();
                            carOrder = new Gson().fromJson(j.toString(), CarOrder.class);
                            Log.d("Fragment3_2", carOrder.toString());
                            carOrders.add(carOrder);
                        }
                        Message message = new Message();
                        message.what=1;
                        handler.sendMessage(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void addview() {
        mTab.removeAllViews();
        for (CarOrder carOrder : carOrders) {
            View view=View.inflate(getActivity(),R.layout.item3_add,null);
            TextView tv1=view.findViewById(R.id.tv1);
            TextView tv2=view.findViewById(R.id.tv2);
            TextView tv3=view.findViewById(R.id.tv3);
            TextView tv4=view.findViewById(R.id.tv4);
            TextView tv5=view.findViewById(R.id.tv5);
            TextView tv6=view.findViewById(R.id.tv6);
            TextView tv7=view.findViewById(R.id.tv7);
            TextView tv8=view.findViewById(R.id.tv8);
            TextView tv9=view.findViewById(R.id.tv9);
            tv1.setText(""+carOrder.getCarId());
            tv2.setText(""+carOrder.getTime());
            tv3.setText(""+carOrder.getNum());
            tv4.setText(""+carOrder.getEngine());
            tv5.setText(""+carOrder.getSpeed());
            tv6.setText(""+carOrder.getWheel());
            tv7.setText(""+carOrder.getControl());
            tv8.setText(""+carOrder.getBrake());
            tv9.setText(""+carOrder.getHang());
            mTab.addView(view);
        }
    }
}

