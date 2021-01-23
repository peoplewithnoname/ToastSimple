package com.caihao.toast.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.caihao.toast.R;

public class ToastSharedPreferencesUtils {

    final public static String TOAST_NORMAL_ID = "toast_normal_id";
    final public static String TOAST_WARM_ID = "toast_warm_id";
    final public static String TOAST_ERROR_ID = "toast_error_id";
    final public static String TOAST_SUCCESS_ID = "toast_success_id";

    private SharedPreferences shared;

    public ToastSharedPreferencesUtils(Context context) {
        shared = context.getSharedPreferences("toast", Context.MODE_PRIVATE);
    }

    public int getToastNormalId() {
        return shared.getInt(ToastSharedPreferencesUtils.TOAST_NORMAL_ID, R.drawable.img_toast_tip);
    }

    public int getToastWarmId() {
        return shared.getInt(ToastSharedPreferencesUtils.TOAST_WARM_ID, R.drawable.img_toast_warm);
    }

    public int getToastErrorId() {
        return shared.getInt(ToastSharedPreferencesUtils.TOAST_ERROR_ID, R.drawable.img_toast_error);
    }

    public int getToastSuccessId() {
        return shared.getInt(ToastSharedPreferencesUtils.TOAST_SUCCESS_ID, R.drawable.img_toast_success);
    }

    public void putToastNormalId(int value) {
        shared.edit().putInt(ToastSharedPreferencesUtils.TOAST_NORMAL_ID, value).commit();
    }

    public void putToastWarmId(int value) {
        shared.edit().putInt(ToastSharedPreferencesUtils.TOAST_WARM_ID, value).commit();
    }

    public void putToastErrorId(int value) {
        shared.edit().putInt(ToastSharedPreferencesUtils.TOAST_ERROR_ID, value).commit();
    }

    public void putToastSuccessId(int value) {
        shared.edit().putInt(ToastSharedPreferencesUtils.TOAST_SUCCESS_ID, value).commit();
    }


}
