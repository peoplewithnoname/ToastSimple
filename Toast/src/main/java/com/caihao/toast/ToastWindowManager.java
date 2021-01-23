package com.caihao.toast;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.caihao.toast.interfa.IToastWindowManager;
import com.caihao.toast.utils.ToastSharedPreferencesUtils;

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

    private int flag = 0;//0 普通  1 警告  2 错误  3 成功

    private String text;//显示的字符串

    private long duration;

    private TextView tvMsg;

    private ImageView ivTip;

    private long endTimeStamp = 0;// 0普通 1警告 2错误 3成功

    private Timer timer;

    private ToastSharedPreferencesUtils sharedPreferencesUtils;

    public ToastWindowManager(ToastSharedPreferencesUtils sharedPreferencesUtils) {
        this.sharedPreferencesUtils = sharedPreferencesUtils;
    }

    private void initData() {
        if (layoutParams == null)
            layoutParams = LayoutParamsManager.createLayoutParams(this.context);
        if (currentContentView == null)
            currentContentView = LayoutParamsManager.createDefaultContentView(context);
        if (tvMsg == null) tvMsg = currentContentView.findViewById(R.id.tvMsg);
        if (ivTip == null) ivTip = currentContentView.findViewById(R.id.ivTip);
    }

    public ToastWindowManager setContext(Context context) {
        this.context = context;
        initData();
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

    @Override
    public void addView() {
        if (hasContent) return;
        hasContent = true;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(currentContentView, layoutParams);
    }

    @Override
    public void removeView() {
        if (!hasContent) return;
        hasContent = false;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.removeView(currentContentView);
    }

    private void show() {
        LayoutParamsManager.setWindowType(context, layoutParams);
        if (hasContent) removeView();
        setContent();
        addView();
        runCallbackForRemove();
    }

    @Override
    public void showMessage() {
        flag = 0;
        show();
    }

    @Override
    public void showWarm() {
        flag = 1;
        show();
    }

    @Override
    public void showError() {
        flag = 2;
        show();
    }

    @Override
    public void showSuccess() {
        flag = 3;
        show();
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

    private void setContent() {
        if (flag == 0) ivTip.setImageResource(sharedPreferencesUtils.getToastNormalId());
        else if (flag == 1) ivTip.setImageResource(sharedPreferencesUtils.getToastWarmId());
        else if (flag == 2) ivTip.setImageResource(sharedPreferencesUtils.getToastErrorId());
        else if (flag == 3) ivTip.setImageResource(sharedPreferencesUtils.getToastSuccessId());
        if (tvMsg != null) tvMsg.setText(this.text);
    }

    public boolean hasContent() {
        return hasContent;
    }
}
