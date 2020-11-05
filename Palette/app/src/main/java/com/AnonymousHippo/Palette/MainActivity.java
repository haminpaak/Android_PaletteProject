package com.AnonymousHippo.Palette;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.mmin18.widget.RealtimeBlurView;

public class MainActivity extends BaseActivity {

    // FAB State Flag
    private boolean FAB_FLAG;

    // FAB 버튼
    private ImageButton mainFAB;
    private ImageButton myFAB;
    private TextView myFABTextView;
    private ImageButton likeFAB;
    private TextView likeFABTextView;
    private ImageButton settingFAB;
    private TextView settingFABTextView;

    // Blur View
    private RealtimeBlurView mainBlurView;

    // FAB 애니메이션
    private Animation fab_open, fab_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 인스턴스화
        final ScrollView mainScrollView = findViewById(R.id.Main_ScrollView_main);
        mainBlurView = findViewById(R.id.Main_blurView);

        // FAB 인스턴스화
        mainFAB = findViewById(R.id.Main_FAB_main);
        myFAB = findViewById(R.id.Main_FAB_my);
        myFABTextView = findViewById(R.id.Main_FABText_my);
        likeFAB = findViewById(R.id.Main_FAB_like);
        likeFABTextView = findViewById(R.id.Main_FABText_like);
        settingFAB = findViewById(R.id.Main_FAB_setting);
        settingFABTextView = findViewById(R.id.Main_FABText_setting);

        // 초기화
        FAB_FLAG = false;
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        // FAB 버튼 처리
        mainFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 열린 상태이면 닫기
                if(FAB_FLAG){
                    Message msg = FABCloseHandler.obtainMessage();
                    FABCloseHandler.sendMessage(msg);
                }

                // 닫힌 상태이면 열기
                else{
                    Message msg = FABOpenHandler.obtainMessage();
                    FABOpenHandler.sendMessage(msg);
                }
            }
        });

        myFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        likeFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        settingFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        // 스크롤뷰 위치 수정 (검색 EditText 숨김)
        mainScrollView.post(new Runnable() {
            @Override
            public void run() {
                mainScrollView.scrollTo(0, 170);
            }
        });
    }

    // 뒤로가기 버튼
    @Override
    public void onBackPressed() {
        finish();
    }

    /* Blur View 클릭 함수 */
    public void onBlurClicked(View view) {
        Message msg = FABCloseHandler.obtainMessage();
        FABCloseHandler.sendMessage(msg);
    }

    /* Title 클릭 */
    public void onTOPClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    // FAB 닫힘 Handler
    @SuppressLint("HandlerLeak")
    Handler FABCloseHandler = new Handler() {
        @Override
        @SuppressLint({"HandlerLeak", "SetTextI18n"})
        public void handleMessage(Message msg) {
            myFAB.startAnimation(fab_close);
            likeFAB.startAnimation(fab_close);
            settingFAB.startAnimation(fab_close);

            mainBlurView.setVisibility(View.INVISIBLE);
            myFAB.setVisibility(View.INVISIBLE);
            myFABTextView.setVisibility(View.INVISIBLE);
            likeFAB.setVisibility(View.INVISIBLE);
            likeFABTextView.setVisibility(View.INVISIBLE);
            settingFAB.setVisibility(View.INVISIBLE);
            settingFABTextView.setVisibility(View.INVISIBLE);

            FAB_FLAG = false;
        }
    };

    // FAB 열림 Handler
    @SuppressLint("HandlerLeak")
    Handler FABOpenHandler = new Handler() {
        @Override
        @SuppressLint({"HandlerLeak", "SetTextI18n"})
        public void handleMessage(Message msg) {
            myFAB.startAnimation(fab_open);
            likeFAB.startAnimation(fab_open);
            settingFAB.startAnimation(fab_open);

            mainBlurView.setVisibility(View.VISIBLE);
            myFAB.setVisibility(View.VISIBLE);
            myFABTextView.setVisibility(View.VISIBLE);
            likeFAB.setVisibility(View.VISIBLE);
            likeFABTextView.setVisibility(View.VISIBLE);
            settingFAB.setVisibility(View.VISIBLE);
            settingFABTextView.setVisibility(View.VISIBLE);

            FAB_FLAG = true;
        }
    };
}