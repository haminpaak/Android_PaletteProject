package com.AnonymousHippo.Palette;

import android.content.Intent;
import android.os.Bundle;

public class LoadingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Intent intent = new Intent(getApplicationContext(), GetInterestActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}