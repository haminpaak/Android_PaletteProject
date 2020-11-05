package com.AnonymousHippo.Palette;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class AskActivity extends BaseActivity {

    // Elements
    private EditText emailEditText;
    private EditText titleEditText;
    private EditText contentEditText;
    private Button okButton;
    private TextView numTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        // 인스턴스화
        ImageButton backButton = findViewById(R.id.Ask_ImageButton_back);
        emailEditText = findViewById(R.id.Ask_EditText_email);
        titleEditText = findViewById(R.id.Ask_EditText_title);
        contentEditText = findViewById(R.id.Ask_EditText_content);
        okButton = findViewById(R.id.Ask_Button_done);
        numTextView = findViewById(R.id.Ask_TextView_textNum);

        // email EditText
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // title EditText
        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // content EditText
        contentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // ok Button
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 뒤로가기 버튼
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){

    }
}