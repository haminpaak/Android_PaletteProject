package com.AnonymousHippo.Palette;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.GestureDetector.OnGestureListener;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mmin18.widget.RealtimeBlurView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

public class GalleryActivity extends BaseActivity implements OnGestureListener {

    // 제스처
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private GestureDetector gestureScanner;

    /* 몰입모드 */
    private View decorView;
    private int uiOption;

    // 저장한 전시회 Flag
    private boolean SAVED;

    // 화면 회전 FLAG
    private boolean SCREEN_ROTATION;

    // 정보 표시 FLAG
    private boolean INFO_FLAG;

    //
    private RealtimeBlurView blurView;
    private LinearLayout infoView;

    // 애니메이션
    private Animation slideUpAnimation;

    // 이미지 List
    private ArrayList<Integer> IMAGES;

    // 이미지 index
    private int INDEX, MAX_INDEX;

    // Main Image View
    private ImageView mainImageView;

    // 건너뛰기 버튼
    private Handler leftHandler, rightHandler;

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
        gestureScanner = new GestureDetector(this);

        mainImageView = findViewById(R.id.Gallery_ImageView_main);

        ImageButton leftButton = findViewById(R.id.Gallery_ImageButton_left);
        ImageButton rightButton = findViewById(R.id.Gallery_ImageButton_right);
        ImageButton closeButton = findViewById(R.id.Gallery_ImageButton_close);
        ImageButton infoButton = findViewById(R.id.Gallery_ImageButton_info);
        final ImageButton plusButton = findViewById(R.id.Gallery_ImageButton_plus);

        blurView = findViewById(R.id.Gallery_blurView);
        infoView = findViewById(R.id.Gallery_LinearLayout_info);

        // 초기화
        SAVED = false;
        INFO_FLAG = false;

        // 사진 테스트
        IMAGES = new ArrayList<Integer>();
        IMAGES.add(R.drawable.art1);
        IMAGES.add(R.drawable.art2);
        IMAGES.add(R.drawable.art3);
        IMAGES.add(R.drawable.art4);
        IMAGES.add(R.drawable.art5);
        IMAGES.add(R.drawable.art6);

        // 인덱스 세팅
        INDEX = 0;
        MAX_INDEX = IMAGES.size();

        // 애니메이션
        slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);

        // 빠른 전환 딜레이 (Milli Sec)
        final int FAST_CHANGE_DELAY = 200;

        // Left Button
        leftButton.setOnTouchListener(new View.OnTouchListener() {

            private Handler leftHandler;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (leftHandler != null)
                            return true;
                        leftHandler = new Handler();
                        leftHandler.postDelayed(leftAction, FAST_CHANGE_DELAY);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (leftHandler == null) return true;
                        leftHandler.removeCallbacks(leftAction);
                        leftHandler = null;
                        break;
                }
                return false;
            }

            Runnable leftAction = new Runnable() {
                @Override public void run() {

                    if(INDEX > 0){
                        INDEX--;
                        Message msg = mHandler.obtainMessage();
                        mHandler.sendMessage(msg);
                        leftHandler.postDelayed(this, FAST_CHANGE_DELAY);
                    }
                }
            };
        });

        // Right Button
        rightButton.setOnTouchListener(new View.OnTouchListener() {

            private Handler rightHandler;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (rightHandler != null)
                            return true;
                        rightHandler = new Handler();
                        rightHandler.postDelayed(rightAction, FAST_CHANGE_DELAY);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (rightHandler == null) return true;
                        rightHandler.removeCallbacks(rightAction);
                        rightHandler = null;
                        break;
                }
                return false;
            }

            Runnable rightAction = new Runnable() {
                @Override public void run() {

                    if(INDEX < MAX_INDEX - 1){
                        INDEX++;
                        Message msg = mHandler.obtainMessage();
                        mHandler.sendMessage(msg);
                        rightHandler.postDelayed(this, FAST_CHANGE_DELAY);
                    }
                }
            };
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
                if (SAVED) {
                    plusButton.setImageResource(R.drawable.plus);
                    SAVED = false;
                } else {
                    plusButton.setImageResource(R.drawable.checked);
                    SAVED = true;
                }
            }
        });

        // 정보 button
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 정보 표시
                if (!INFO_FLAG) {
                    infoView.setAlpha(1.0f);
                    infoView.startAnimation(slideUpAnimation);

                    infoView.setVisibility(View.VISIBLE);
                    blurView.setVisibility(View.VISIBLE);
                } else {
                    infoView.setAlpha(0f);
                    infoView.setVisibility(View.INVISIBLE);
                    blurView.setVisibility(View.INVISIBLE);
                }
                INFO_FLAG = !INFO_FLAG;
            }
        });
    }

    @Override
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("onConfigurationChanged", "onConfigurationChanged");

        // 세로 전환 시
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d("onConfigurationChanged", "Configuration.ORIENTATION_PORTRAIT");
            SCREEN_ROTATION = false;
        }

        // 가로 전환 시
        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("onConfigurationChanged", "Configuration.ORIENTATION_LANDSCAPE");
            decorView.setSystemUiVisibility(uiOption);
            SCREEN_ROTATION = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent me) {
        return gestureScanner.onTouchEvent(me);
    }

    @Override
    public void onBackPressed() {

    }

    public void onBlurClicked(View view) {
        INFO_FLAG = false;
        infoView.setAlpha(0f);
        infoView.setVisibility(View.INVISIBLE);
        blurView.setVisibility(View.INVISIBLE);
    }

    // Background -> Foreground 올라왔을 때 몰입 모드 재적용
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            decorView.setSystemUiVisibility(uiOption);
        }
    }

    // 사진 변경 핸들러
    @SuppressLint("HandlerLeak")
    android.os.Handler mHandler = new Handler() {
        @Override
        @SuppressLint({"HandlerLeak", "SetTextI18n"})
        public void handleMessage(Message msg) {
            int nowImage = IMAGES.get(INDEX);
            mainImageView.setImageResource(nowImage);
        }
    };

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Toast.makeText(getApplicationContext(), "Long Press", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            // right to left swipe
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                // Toast.makeText(getApplicationContext(), "Left Swipe", Toast.LENGTH_SHORT).show();

                if(INDEX == MAX_INDEX - 1){
                    Toast.makeText(getApplicationContext(), "마지막 작품입니다", Toast.LENGTH_SHORT).show();
                }
                if(INDEX < MAX_INDEX - 1){
                    INDEX++;
                }

                Message msg = mHandler.obtainMessage();
                mHandler.sendMessage(msg);
            }
            // left to right swipe
            if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                //Toast.makeText(getApplicationContext(), "Right Swipe", Toast.LENGTH_SHORT).show();

                if(INDEX == 0){
                    Toast.makeText(getApplicationContext(), "첫 번째 작품입니다", Toast.LENGTH_SHORT).show();
                }
                if(INDEX > 0){
                    INDEX--;
                }

                Message msg = mHandler.obtainMessage();
                mHandler.sendMessage(msg);
            }
            // down to up swipe
            if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                //Toast.makeText(getApplicationContext(), "Swipe up", Toast.LENGTH_SHORT).show();

                infoView.setAlpha(1.0f);
                infoView.startAnimation(slideUpAnimation);
                infoView.setVisibility(View.VISIBLE);
                blurView.setVisibility(View.VISIBLE);

                INFO_FLAG = true;
            }
            // up to down swipe
            if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                //Toast.makeText(getApplicationContext(), "Swipe down", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ignored) {

        }
        return true;
    }
}