package com.caihao.toast;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.caihao.toast.interfa.IToastWindowManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Toast 只用这一个实体类
 */
public class ToastWindowManager implements IToastWindowManager {

    private Context context;

    private WindowManager.LayoutParams layoutParams;

    private boolean hasContent = false;//当前toast是否处于显示状态

    private View currentContentView = null;//当前正在使用的View 可能是默认的View也可能是自定义的View

    private String text;

    private long duration;

    private TextView tvMsg;

    private long endTimeStamp = 0;

    private Timer timer;

    public ToastWindowManager() {

    }

    public ToastWindowManager setContext(Context context) {
        this.context = context;
        return this;
    }

    public ToastWindowManager setText(String text) {
        this.text = text;
        return this;
    }

    public ToastWindowManager setDuration(long duration) {
        this.duration = duration;
        if (this.duration <= 0) this.duration = 1000;
        return this;
    }

    private void initLayoutParams() {
        if (layoutParams == null)
            layoutParams = LayoutParamsManager.createLayoutParams(this.context);
        LayoutParamsManager.setWindowType(context, layoutParams);
        if (currentContentView == null) {
            currentContentView = LayoutParamsManager.createDefaultContentView(context);
            tvMsg = currentContentView.findViewById(R.id.tvMsg);
        }
    }

    @Override
    public void addView() {
        initLayoutParams();
        if (hasContent) removeView();
        hasContent = true;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(currentContentView, layoutParams);
        if (tvMsg != null) tvMsg.setText(this.text);
        runCallbackForRemove();
    }

    @Override
    public void removeView() {
        hasContent = false;
        if (currentContentView == null) return;
        if (tvMsg != null) tvMsg.setText("");
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.removeView(currentContentView);
    }

    @Override
    public void show() {
        if (tvMsg != null) tvMsg.setText(this.text);
        addView();
    }

    private void runCallbackForRemove() {
        endTimeStamp = System.currentTimeMillis() + duration;
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (System.currentTimeMillis() >= endTimeStamp) {
                        removeView();
                        timer.cancel();
                        timer.purge();
                        timer = null;
                    }
                }
            }, 0, 500);
        }
    }

    public boolean hasContent() {
        return hasContent;
    }
}
