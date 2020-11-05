package com.AnonymousHippo.Palette;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        /* 로고 애니메이션 */
        Animation LogoAnimation = AnimationUtils.loadAnimation(LoadingActivity.this, R.anim.translate);
        final TextView logoTextView = findViewById(R.id.Loading_TextView_title);
        logoTextView.startAnimation(LogoAnimation);

        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}