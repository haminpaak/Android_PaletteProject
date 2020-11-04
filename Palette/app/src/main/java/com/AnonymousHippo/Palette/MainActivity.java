package com.AnonymousHippo.Palette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 뒤로가기 버튼
    @Override
    public void onBackPressed() {
        finish();
    }

    /* Blur View 클릭 함수 */
    public void onBlurClicked(View view) {

    }
}