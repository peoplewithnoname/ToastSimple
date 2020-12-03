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

    private boolean hasContent = false;
    private boolean contentViewIsDefault = false;

    private View contentView = null;

    private View nextContentView = null;//下一个View

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

    public ToastWindowManager setNextContentView(View contentView) {
        this.nextContentView = contentView;
        tvMsg = null;
        contentViewIsDefault = false;
        return this;
    }

    public ToastWindowManager setDefaultContentView() {
        if (contentViewIsDefault) return this;
        nextContentView = LayoutParamsManager.createDefaultContentView(context);
        tvMsg = nextContentView.findViewById(R.id.tvMsg);
        contentViewIsDefault = true;
        return this;
    }

    @Override
    public void addView() {
        if (layoutParams == null)
            layoutParams = LayoutParamsManager.createLayoutParams(this.context);
        LayoutParamsManager.setWindowType(context, layoutParams);
        if (nextContentView != null) {
            if (hasContent) removeView();
            contentView = nextContentView;
            nextContentView = null;
        }
        if (hasContent) {
            hasContent = true;
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.updateViewLayout(contentView, layoutParams);
        } else {
            hasContent = true;
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.addView(contentView, layoutParams);
        }
        if (tvMsg != null) tvMsg.setText(this.text);
        runCallbackForRemove();
    }

    @Override
    public void removeView() {
        hasContent = false;
        if (tvMsg != null) tvMsg.setText("");
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.removeView(contentView);
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
