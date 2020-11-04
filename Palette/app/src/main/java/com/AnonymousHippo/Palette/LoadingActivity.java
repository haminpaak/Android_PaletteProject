package com.AnonymousHippo.Palette;

import android.content.Intent;
import android.os.Bundle;

public class LoadingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
        overridePendingTransition(0, 0);
        startActivity(intent);
        finish();
    }
}