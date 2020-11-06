package com.AnonymousHippo.Palette;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PermissionActivity extends BaseActivity {

    private int PERMISSION_CHECK_INTERNET, PERMISSION_CHECK_RECORD;
    private final int PERMISSION_REQUEST_INTERNET = 1001;
    private final int PERMISSION_REQUEST_RECORD = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        Button permissionButton = findViewById(R.id.Permission_Button_re);
        PERMISSION_CHECK_INTERNET = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        PERMISSION_CHECK_RECORD = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);

        permissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PERMISSION_CHECK_INTERNET != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PermissionActivity.this, new String[]{Manifest.permission.INTERNET}, PERMISSION_REQUEST_INTERNET);
                }

                if (PERMISSION_CHECK_RECORD != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PermissionActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSION_REQUEST_RECORD);
                }

                PERMISSION_CHECK_INTERNET = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET);
                PERMISSION_CHECK_RECORD = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);
            }
        });

        new Thread() {
            public void run() {
                do {
                    PERMISSION_CHECK_INTERNET = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET);
                    PERMISSION_CHECK_RECORD = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);

                } while (PERMISSION_CHECK_RECORD != PackageManager.PERMISSION_GRANTED || PERMISSION_CHECK_INTERNET != PackageManager.PERMISSION_GRANTED);

                Intent intent = new Intent(getApplicationContext(), LoadingActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}