package com.caihao.toast.toast.simple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.caihao.toast.Toast;

public class MainActivity extends AppCompatActivity {

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnShowToast).setOnClickListener(view -> {
            i++;
            Toast.makeText(MainActivity.this, i + " 自定义的Toast", Toast.LENGTH_LONG).show();
        });
    }
}