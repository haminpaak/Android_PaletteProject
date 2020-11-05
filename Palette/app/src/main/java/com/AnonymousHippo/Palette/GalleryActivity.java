package com.AnonymousHippo.Palette;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import static java.lang.Math.abs;

public class GalleryActivity extends BaseActivity {

    /* 몰입모드 */
    private View decorView;
    private int uiOption;

    // 이미지 뷰
    private ImageView mainImageView;

    // 줌 인, 줌 아웃
    private double touch_interval_X = 0; // X 터치 간격
    private double touch_interval_Y = 0; // Y 터치 간격
    private int zoom_in_count = 0; // 줌 인 카운트
    private int zoom_out_count = 0; // 줌 아웃 카운트
    private int touch_zoom = 0; // 줌 크기

    // 저장한 전시회 Flag
    private boolean SAVED;

    // 화면 회전 Flag
    private boolean ROTATED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor("#000000"));

        /* 화면 꺼짐 방지 */
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_gallery);

        /* 몰입 모드 (하단 소프트바 숨기기) */
        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // 인스턴스화
        mainImageView = findViewById(R.id.Gallery_ImageView_main);

        ImageButton leftButton = findViewById(R.id.Gallery_ImageButton_left);
        ImageButton rightButton = findViewById(R.id.Gallery_ImageButton_right);
        ImageButton closeButton = findViewById(R.id.Gallery_ImageButton_close);
        ImageButton fullScreenButton = findViewById(R.id.Gallery_ImageButton_full);
        final ImageButton plusButton = findViewById(R.id.Gallery_ImageButton_plus);

        // 초기화
        SAVED = false;
        ROTATED = false;

        // Left Button
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Right Button
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Close Button
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        // Plus Button
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SAVED){
                    plusButton.setImageResource(R.drawable.plus);
                }
                else{
                    plusButton.setImageResource(R.drawable.checked);
                }
                SAVED = !SAVED;
            }
        });

        // Full Screen Button
        fullScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ROTATED){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                else{
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                decorView.setSystemUiVisibility(uiOption);
                ROTATED = !ROTATED;
            }
        });
    }

    @Override

    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    public void onBackPressed() {

    }

    public void onImageClicked(View view) {

    }

    // Background -> Foreground 올라왔을 때 몰입 모드 재적용
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            decorView.setSystemUiVisibility(uiOption);
        }
    }
}