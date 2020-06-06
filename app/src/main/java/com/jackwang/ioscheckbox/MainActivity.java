package com.jackwang.ioscheckbox;

import android.os.Bundle;
import android.widget.Toast;

import com.jackwang.ioscheck.IOSSwitchButton;
import com.jackwang.ioscheck.SwitchListener;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IOSSwitchButton iosSwitchButton = (IOSSwitchButton) findViewById(R.id.ios_switch);

        iosSwitchButton.setSwitchListener(new SwitchListener() {
            @Override
            public void changeCheck(boolean check) {
                Toast.makeText(MainActivity.this, check ? "开启" : "关闭", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
