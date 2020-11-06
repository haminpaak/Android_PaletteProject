package com.AnonymousHippo.Palette;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class LoadingActivity extends BaseActivity {

    private int PERMISSION_CHECK_INTERNET, PERMISSION_CHECK_RECORD;
    private final int PERMISSION_REQUEST_INTERNET = 1001;
    private final int PERMISSION_REQUEST_RECORD = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        /* 로고 애니메이션 */
        Animation LogoAnimation = AnimationUtils.loadAnimation(LoadingActivity.this, R.anim.translate);
        final TextView logoTextView = findViewById(R.id.Loading_TextView_title);
        logoTextView.startAnimation(LogoAnimation);

        // 권한 체크
        PERMISSION_CHECK_INTERNET = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        PERMISSION_CHECK_RECORD = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);

        Intent intent;
        if(PERMISSION_CHECK_RECORD == PackageManager.PERMISSION_GRANTED && PERMISSION_CHECK_INTERNET == PackageManager.PERMISSION_GRANTED){
            intent = new Intent(getApplicationContext(), MainActivity.class);
        } else{
            intent = new Intent(getApplicationContext(), PermissionActivity.class);
        }
        startActivity(intent);
        finish();
    }
}