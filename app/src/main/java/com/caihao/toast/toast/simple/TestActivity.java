package com.caihao.toast.toast.simple;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.caihao.toast.Toast;

public class TestActivity extends AppCompatActivity {

    private int i = 0;

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        toast = new Toast(this, getLifecycle());

        toast.init(R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher);

        findViewById(R.id.btnShowToast).setOnClickListener(view -> {
            i++;
            toast.makeText(i + " 自定义的普通Toast", Toast.LENGTH_LONG).showMessage();
        });

        findViewById(R.id.btnShowWarmToast).setOnClickListener(view -> {
            i++;
            toast.makeText(i + " 自定义的警告Toast", Toast.LENGTH_LONG).showWarm();
        });

        findViewById(R.id.btnShowErrorToast).setOnClickListener(view -> {
            i++;
            toast.makeText(i + " 自定义的错误Toast", Toast.LENGTH_LONG).showError();
        });

        findViewById(R.id.btnShowSuccessToast).setOnClickListener(view -> {
            i++;
            toast.makeText(i + " 自定义的成功Toast", Toast.LENGTH_LONG).showSuccess();
        });
    }

}
