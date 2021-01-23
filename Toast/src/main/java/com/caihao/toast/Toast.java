package com.caihao.toast;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.Lifecycle;

import com.caihao.toast.utils.FullLifecycleObserverAdapter;
import com.caihao.toast.utils.ToastSharedPreferencesUtils;


/**
 * 用window替换toast
 */
public class Toast extends FullLifecycleObserverAdapter {

    public final static long LENGTH_LONG = 3000;

    public final static long LENGTH_SHORT = 1000;

    private Context context;

    private Lifecycle lifecycle;

    private ToastSharedPreferencesUtils sharedPreferencesUtils;

    private ToastWindowManager toastManager = null;

    public Toast(Context context, Lifecycle lifecycle) {
        this.context = context;
        this.lifecycle = lifecycle;
        sharedPreferencesUtils = new ToastSharedPreferencesUtils(this.context);
        toastManager = new ToastWindowManager(sharedPreferencesUtils);
        this.lifecycle.addObserver(this);
    }

    public void init(int tipID, int warmID, int errorID, int successID) {
        if (tipID != 0) sharedPreferencesUtils.putToastNormalId(tipID);
        if (warmID != 0) sharedPreferencesUtils.putToastWarmId(warmID);
        if (errorID != 0) sharedPreferencesUtils.putToastErrorId(errorID);
        if (successID != 0) sharedPreferencesUtils.putToastSuccessId(successID);
    }


    public ToastWindowManager makeText(String text, long duration) {
        return toastManager.setContext(context).setText(text).setDuration(duration);
    }

    @Override
    public void onStop() {
        super.onStop();
        toastManager.removeView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.lifecycle.removeObserver(this);
    }
}
