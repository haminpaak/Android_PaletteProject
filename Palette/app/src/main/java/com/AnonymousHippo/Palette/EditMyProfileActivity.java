package com.AnonymousHippo.Palette;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

public class EditMyProfileActivity extends BaseActivity{

    private boolean insert1, insert2;

    // 데이터 전송용 배열
    private final String[] keys = new String[2];
    private final String[] data = new String[2];
    String result;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_profile);

        // 인스턴스화
        Button cancelButton = findViewById(R.id.EditProfile_Button_cancel);
        final Button okButton = findViewById(R.id.EditProfile_Button_done);

        final EditText nameEditText = findViewById(R.id.EditProfile_EditText_name);
        final EditText ageEditText = findViewById(R.id.EditProfile_EditText_age);

        // 초기화
        insert1 = insert2 = false;
        SharedPreferences preferences = getSharedPreferences("com.AnonymousHippo.Palette.sharePreference", MODE_PRIVATE);
        userEmail = preferences.getString("userEmail", "");

        // 저장 버튼
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        //TODO 서버로 데이터 전송하기

                    }
                }.start();
            }
        });

        // 이름
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                insert1 = !nameEditText.getText().toString().equals("");

                if(insert1 && insert2){
                    okButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.basic_button));
                    okButton.setClickable(true);
                }
                else{
                    okButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.basic_button_unclick));
                    okButton.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // 나이
        ageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                insert2 = !ageEditText.getText().toString().equals("");

                if(insert1 && insert2){
                    okButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.basic_button));
                    okButton.setClickable(true);
                }
                else{
                    okButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.basic_button_unclick));
                    okButton.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // 취소 버튼
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), MyProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    /* 프로필 사진 변경 */
    public void onProfileImageClicked(View view) {
        // TODO 갤러리 열어서 사진 가져와 저장, 이후 DB에 저장
    }
}