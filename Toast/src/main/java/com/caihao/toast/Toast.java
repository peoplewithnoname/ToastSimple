package com.caihao.toast;

import android.content.Context;
import android.view.View;


/**
 * 用window替换toast
 */
public class Toast {

    public final static long LENGTH_LONG = 3000;

    public final static long LENGTH_SHORT = 1000;

    public static int toast_normal_id = R.drawable.img_toast_tip;
    public static int toast_warm_id = R.drawable.img_toast_warm;
    public static int toast_error_id = R.drawable.img_toast_error;
    public static int toast_success_id = R.drawable.img_toast_success;

    private static ToastWindowManager toastManager = null;

    public static ToastWindowManager makeText(Context context, String text, long duration) {
        return getToastManager().setContext(context).setText(text).setDuration(duration);
    }

    private static ToastWindowManager getToastManager() {
        if (toastManager == null) toastManager = new ToastWindowManager();
        return toastManager;
    }

    public static void init(int tipID, int warmID, int errorID, int successID) {
        if (tipID != 0) toast_normal_id = tipID;
        if (warmID != 0) toast_warm_id = warmID;
        if (errorID != 0) toast_error_id = errorID;
        if (successID != 0) toast_success_id = successID;
    }

}
