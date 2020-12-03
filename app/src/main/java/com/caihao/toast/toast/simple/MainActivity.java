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

        findViewById(R.id.btnShowToast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                Toast.makeText(MainActivity.this, i + " 自定义的Toast", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.btnShowToast2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                View rootView = getLayoutInflater().inflate(R.layout.layout_cus_toast, null);
                TextView tvMsg = rootView.findViewById(R.id.tvShowMsg);
                tvMsg.setText(String.valueOf(i));
                Toast.makeText(MainActivity.this, rootView, Toast.LENGTH_LONG).show();
            }
        });

    }
}