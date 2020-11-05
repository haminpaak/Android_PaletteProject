package com.AnonymousHippo.Palette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // 인스턴스화
        ImageButton backButton = findViewById(R.id.Setting_ImageButton_back);
        Button myProfileButton = findViewById(R.id.Setting_Button_MyProfile);
        Button developerButton = findViewById(R.id.Setting_Button_Developer);
        Button askButton = findViewById(R.id.Setting_Button_Ask);
        Button changePasswordButton = findViewById(R.id.Setting_Button_ChangePassword);
        Button logoutButton = findViewById(R.id.Setting_Button_LogOut);
        Button quitButton = findViewById(R.id.Setting_Button_Quit);

        // 뒤로가기 버튼
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        // 내 프로필
        myProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        // 개발팀
        developerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DeveloperActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        // 문의사항
        askButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AskActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        // 비밀번호 변경
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        // 로그아웃
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        // 회원 탈퇴
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuitActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}