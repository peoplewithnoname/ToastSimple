package com.caihao.toast;

import android.content.Context;
import android.view.View;


/**
 * 用window替换toast
 */
public class Toast {

    public final static long LENGTH_LONG = 3000;

    public final static long LENGTH_SHORT = 1000;

    private static ToastWindowManager toastManager = null;

    public static ToastWindowManager makeText(Context context, String text, long duration) {
        return getToastManager().setContext(context).setText(text).setDuration(duration).setDefaultContentView();
    }

    public static ToastWindowManager makeText(Context context, View view, long duration) {
        return getToastManager().setContext(context).setText("").setDuration(duration).setNextContentView(view);
    }

    private static ToastWindowManager getToastManager() {
        if (toastManager == null) toastManager = new ToastWindowManager();
        return toastManager;
    }

}
