package com.lenovo.manufacture.Item5;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.manufacture.MyOk;
import com.lenovo.manufacture.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class I5pop extends PopupWindow {
    private View view;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private ProgressBar pb;
    private Button bt;
    private Context con;
    private Boolean b;
    public I5pop(Context context){
        super(context);
        con=context;
        setWidth(GridView.LayoutParams.WRAP_CONTENT);
        setHeight(GridView.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOutsideTouchable(true);
        setTouchable(true);
        view = LayoutInflater.from(context).inflate(R.layout.item5_add,null);
         tv1=view.findViewById(R.id.tv1);
         tv2=view.findViewById(R.id.tv2);
         tv3=view.findViewById(R.id.tv3);
         pb=view.findViewById(R.id.pb);
         bt=view.findViewById(R.id.bt);
        setContentView(view);
    }

    private String[] steps = {
            "第一步", "第二步", "第三步", "第四步", "第五步",
            "第六步", "第七步", "第八步", "第九步", "第十步",
            "第十一步", "第十二步", "第十三步", "第十四步", "第十五步",
            "第十六步", "第十七步", "第十八步", "第十九步", "第二十步"};

    public void setview(int step, List<SM> list) {
        SM sm = list.get(step);
        tv1.setText(steps[step]+"");
        tv2.setText(sm.getPlStepName()+"");
        tv3.setText(sm.getPower()+"");
        pb.setProgress(sm.getPower());
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b=false;
                MyOk.post("dataInterface/UserPlStepInfo/updatePower", "id=" + sm.getId() + "&power=100", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {}
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            if (jsonObject.getString("status").equals("200")){
                                    b=true;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                if (b){
                    Toast.makeText(con, "修改成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
