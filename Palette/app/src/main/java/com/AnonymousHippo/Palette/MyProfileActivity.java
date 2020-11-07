package com.AnonymousHippo.Palette;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyProfileActivity extends BaseActivity {

    // 데이터 전송용 배열
    private final String[] keys = new String[2];
    private final String[] data = new String[2];
    String result;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        // 인스턴스화
        ImageButton backButton = findViewById(R.id.MyProfile_ImageButton_back);
        Button editButton = findViewById(R.id.MyProfile_Button_edit);

        final TextView nameTextView = findViewById(R.id.MyProfile_TextView_userName);
        final TextView emailTextView = findViewById(R.id.MyProfile_TextView_userEmail);
        final TextView ageTextView = findViewById(R.id.MyProfile_TextView_userAge);
        final TextView interestTextView = findViewById(R.id.MyProfile_TextView_userInterest);
        final TextView billingTextView = findViewById(R.id.MyProfile_TextView_userBilling);

        // 초기화
        SharedPreferences preferences = getSharedPreferences("com.AnonymousHippo.Palette.sharePreference", MODE_PRIVATE);
        userEmail = preferences.getString("userEmail", "");

        // 데이터 수신
        new Thread() {
            public void run() {
                //TODO 서버에서 user data, user interest 가져와서 TextView 보여주기

                /*
                * Message msg = mHandler.obtainMessage();
                * mHandler.sendMessage(msg);
                * */

            }
        }.start();

        // 수정 버튼
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditMyProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        // 뒤로가기 버튼
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

    }

    // TextView 정보 표시 Handler
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        @SuppressLint({"HandlerLeak", "SetTextI18n"})
        public void handleMessage(Message msg) {
            // TODO
        }
    };

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}