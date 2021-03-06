package com.kalu.safetools;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import lib.kalu.jnisafetools.SafeTools;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean checkSafe = lib.kalu.safetools.SafeTools.checkSafe();
        Toast.makeText(getApplicationContext(), String.valueOf(checkSafe), Toast.LENGTH_SHORT).show();

//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//
//                for (int i = 0; i < 100; i++) {
//                    String aesEncode = ToolUtil.aesEncode("123456789");
//                    String aesDecode = ToolUtil.aesDecode(aesEncode);
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//
//                for (int i = 0; i < 100; i++) {
//                    String aesEncode = ToolUtil.aesEncode("987654321");
//                    String aesDecode = ToolUtil.aesDecode(aesEncode);
//                }
//            }
//        }).start();

        // 加密
        findViewById(R.id.main_ens).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView input = findViewById(R.id.main_input);
                CharSequence text = input.getText();

                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(getApplicationContext(), "请输入加密信息", Toast.LENGTH_SHORT).show();
                } else {
                    String aesEncode = SafeTools.aesEncodeMult(String.valueOf(text), true, true, true, true);
                    TextView inputs = findViewById(R.id.main_inputs);
                    inputs.setText(aesEncode);
                }
            }
        });

        // 解密
        findViewById(R.id.main_des).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView input = findViewById(R.id.main_inputs);
                CharSequence text = input.getText();

                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(getApplicationContext(), "请输入加密信息", Toast.LENGTH_SHORT).show();
                } else {
                    String aesDecode = SafeTools.aesDecodeMult(String.valueOf(text), true, true, true, true);
                    TextView outputs = findViewById(R.id.main_outputs);
                    outputs.setText(aesDecode);
                }
            }
        });

        // 重置
        findViewById(R.id.main_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView input = findViewById(R.id.main_input);
                input.setText("");

                TextView inputs = findViewById(R.id.main_inputs);
                inputs.setText("");

                TextView outputs = findViewById(R.id.main_outputs);
                outputs.setText("");
            }
        });

        // 检测模拟器
        findViewById(R.id.main_emulator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean b = SafeTools.checkXposed();
                Toast.makeText(getApplicationContext(), b + "", Toast.LENGTH_SHORT).show();
            }
        });

        // 检测签名信息
        findViewById(R.id.main_signature1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SafeTools.checkSignature();
            }
        });

        // 检测签名信息
        findViewById(R.id.main_signature2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        SafeTools.checkSignature();

                    }
                }).start();
            }
        });
    }
}
