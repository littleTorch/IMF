package com.lenovo.manufacture.Item3;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.lenovo.manufacture.MyOk;
import com.lenovo.manufacture.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3_1 extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private RadioButton mCarId1;
    private RadioButton mCarId2;
    private RadioButton mCarId3;
    private RadioGroup mRg1;
    /**
     * xxxx/xx/xx
     */
    private Button mBu1;
    /**
     * 1
     */
    private EditText mEt2;
    /**
     * 1
     */
    private Spinner mSp1;
    /**
     * 自动
     */
    private RadioButton mRg21;
    /**
     * 手动
     */
    private RadioButton mRg22;
    private RadioGroup mRg2;
    /**
     * 烤漆
     */
    private RadioButton mRg31;
    /**
     * 电镀
     */
    private RadioButton mRg32;
    private RadioGroup mRg3;
    /**
     * 低配
     */
    private RadioButton mRg41;
    /**
     * 高配
     */
    private RadioButton mRg42;
    private RadioGroup mRg4;
    /**
     * 鼓式制动器
     */
    private RadioButton mRg51;
    /**
     * 盘式自动器
     */
    private RadioButton mRg52;
    private RadioGroup mRg5;
    /**
     * 1
     */
    private Spinner mSp2;
    /**
     * 下订单
     */
    private Button mOk;
    View view;
    private TimePickerView picker;
    private Handler handler;
    String carId = "1";
    String engine = "0";
    String num = "0";
    String time = (long)new Date().getTime()/1000+"";
    String speed = "0";
    String wheel = "0";
    String control = "0";
    String brake = "0";
    String hang = "0";
    String s2 = "userWorkId=1&userAppointmentName=订单10086&content=测试订单&type=0&gold=2000&color=1&";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment3_1, container, false);
        initView();
        gettime();
        sp1();
        sp2();
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                Toast.makeText(getActivity(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        return view;
    }

    private void sp2() {
        List sp2 = new ArrayList();
        sp2.add("独立悬挂系统");
        sp2.add("主动悬挂系统");
        sp2.add("横臂式悬挂系统");
        sp2.add("纵臂式悬挂系统");
        sp2.add("烛式悬挂系统");
        sp2.add("多连杆式悬挂系统");
        sp2.add("麦弗逊式悬挂系统");
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, sp2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSp2.setAdapter(adapter);
        mSp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hang = position + "";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void sp1() {
        List sp1 = new ArrayList();
        sp1.add("1.0");
        sp1.add("1.5");
        sp1.add("2.0");
        sp1.add("2.5");
        sp1.add("3.0");
        sp1.add("4.0");
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, sp1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSp1.setAdapter(adapter);
        mSp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                engine = position + "";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void gettime() {
        picker = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mBu1.setText(new SimpleDateFormat("yyyy/MM/dd").format(date));
                time = (long)date.getTime()/1000+ "";
            }
        }).isDialog(true)
                .setLabel("年", "月", "日", null, null, null)
                .setSubmitText("确定")
                .setCancelText("取消")
                .build();
    }

    private void initView() {
        mCarId1 = (RadioButton) view.findViewById(R.id.carId1);
        mCarId1.setOnClickListener(this);
        mCarId2 = (RadioButton) view.findViewById(R.id.carId2);
        mCarId2.setOnClickListener(this);
        mCarId3 = (RadioButton) view.findViewById(R.id.carId3);
        mCarId3.setOnClickListener(this);
        mRg1 = (RadioGroup) view.findViewById(R.id.rg1);
        mRg1.setOnCheckedChangeListener(this);
        mBu1 = (Button) view.findViewById(R.id.bu1);
        mBu1.setOnClickListener(this);
        mEt2 = (EditText) view.findViewById(R.id.et2);
        mEt2.setOnClickListener(this);
        mSp1 = (Spinner) view.findViewById(R.id.sp1);
        mRg21 = (RadioButton) view.findViewById(R.id.rg2_1);
        mRg21.setOnClickListener(this);
        mRg22 = (RadioButton) view.findViewById(R.id.rg2_2);
        mRg22.setOnClickListener(this);
        mRg2 = (RadioGroup) view.findViewById(R.id.rg2);
        mRg2.setOnCheckedChangeListener(this);
        mRg31 = (RadioButton) view.findViewById(R.id.rg3_1);
        mRg31.setOnClickListener(this);
        mRg32 = (RadioButton) view.findViewById(R.id.rg3_2);
        mRg32.setOnClickListener(this);
        mRg3 = (RadioGroup) view.findViewById(R.id.rg3);
        mRg3.setOnCheckedChangeListener(this);
        mRg41 = (RadioButton) view.findViewById(R.id.rg4_1);
        mRg41.setOnClickListener(this);
        mRg42 = (RadioButton) view.findViewById(R.id.rg4_2);
        mRg42.setOnClickListener(this);
        mRg4 = (RadioGroup) view.findViewById(R.id.rg4);
        mRg4.setOnCheckedChangeListener(this);
        mRg51 = (RadioButton) view.findViewById(R.id.rg5_1);
        mRg51.setOnClickListener(this);
        mRg52 = (RadioButton) view.findViewById(R.id.rg5_2);
        mRg52.setOnClickListener(this);
        mRg5 = (RadioGroup) view.findViewById(R.id.rg5);
        mRg5.setOnCheckedChangeListener(this);
        mSp2 = (Spinner) view.findViewById(R.id.sp2);
        mOk = (Button) view.findViewById(R.id.ok);
        mOk.setOnClickListener(this);
        mBu1.setText(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bu1:
                picker.show();
                break;
            case R.id.ok:
                String arg = s2 + "carId=" + carId + "&time=" + time + "&num=" + mEt2.getText() + "&engine=" + engine + "&speed=" + speed + "&wheel=" + wheel + "&control=" + control + "&brake=" + brake + "&hang=" + hang;
                Log.d("Fragment3_1", arg);
                MyOk.post("dataInterface/UserAppointment/create", arg, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            Log.d("Fragment3_1", jsonObject.toString());
                            if (jsonObject.getString("status").equals("200")) {
                                Message message = new Message();
                                message.what = 1;
                                message.obj = jsonObject.getString("message");
                                handler.sendMessage(message);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getId()) {
            default:
                break;
            case R.id.rg1:
                switch (checkedId) {
                    case R.id.carId1:
                        carId = 1 + "";
                        Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.carId2:
                        carId = 2 + "";
                        Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.carId3:
                        carId = 3 + "";
                        Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            case R.id.rg2:
                switch (checkedId) {
                    case R.id.rg2_1:
                        speed = 0 + "";
                        break;
                    case R.id.rg2_2:
                        speed = 1 + "";
                        break;
                }
                break;
            case R.id.rg3:
                switch (checkedId) {
                    case R.id.rg3_1:
                        wheel = 0 + "";
                        break;
                    case R.id.rg3_2:
                        wheel = 1 + "";
                        break;
                }
                break;
            case R.id.rg4:
                switch (checkedId) {
                    case R.id.rg4_1:
                        control = 0 + "";
                        break;
                    case R.id.rg4_2:
                        control = 1 + "";
                        break;
                }
                break;
            case R.id.rg5:
                switch (checkedId) {
                    case R.id.rg5_1:
                        brake = 0 + "";
                        break;
                    case R.id.rg5_2:
                        brake = 1 + "";
                        break;
                }
                break;
        }
    }
}
