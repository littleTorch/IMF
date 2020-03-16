package com.lenovo.manufacture.Item4;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.lenovo.manufacture.R;

public class I4popup extends PopupWindow {
    public I4popup(Context context) {
        super(context);
        setWidth(GridView.LayoutParams.WRAP_CONTENT);
        setHeight(GridView.LayoutParams.WRAP_CONTENT);
        setOutsideTouchable(false);
        setFocusable(false);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view= LayoutInflater.from(context).inflate(R.layout.item4_add, null);
        setContentView(view);
    }
}
