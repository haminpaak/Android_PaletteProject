package com.AnonymousHippo.Palette;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class SignUpActivity extends AppCompatActivity {

    // 데이터 전송용 배열
    private final String[] keys = new String[4];
    private final String[] data = new String[4];
    String result;

    // 경고 TextView
    TextView emailAlertTextView;
    TextView password1AlertTextView;
    TextView password2AlertTextView;
    TextView alertTextView;

    // EditText 입력 감지 Flag
    Boolean insert1, insert2, insert3, insert4, insert5;

    // 회원가입 단계
    int step = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // 인스턴스화
        final Button okButton = findViewById(R.id.SignUp_Button_done);
        final EditText emailEditText = findViewById(R.id.SignUp_EditText_email);
        final EditText password1EditText = findViewById(R.id.SignUp_EditText_password1);
        final EditText password2EditText = findViewById(R.id.SignUp_EditText_password2);
        final EditText nameEditText = findViewById(R.id.SignUp_EditText_name);
        final EditText ageEditText = findViewById(R.id.SignUp_EditText_age);
        ImageButton backButton = findViewById(R.id.SignUp_ImageButton_back);

        final TextView emailTextView = findViewById(R.id.SignUp_TextView_email);
        final TextView password1TextView = findViewById(R.id.SignUp_TextView_password1);
        final TextView password2TextView = findViewById(R.id.SignUp_TextView_password2);
        final TextView nameTextView = findViewById(R.id.SignUp_TextView_name);
        final TextView ageTextView = findViewById(R.id.SignUp_TextView_age);

        emailAlertTextView = findViewById(R.id.SignUp_TextView_alert1);
        password1AlertTextView = findViewById(R.id.SignUp_TextView_alert2);
        password2AlertTextView = findViewById(R.id.SignUp_TextView_alert3);
        alertTextView = findViewById(R.id.SignUp_TextView_alert4);

        // 초기화
        insert1 = insert2 = insert3 = insert4 = insert5 = false;

        // 회원가입 Button
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (step == 0) {
                    String email = emailEditText.getText().toString();
                    String password;

                    try {
                        password = AES256Chiper.AES_Encode(password1EditText.getText().toString());
                    } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
                        e.printStackTrace();
                        password = password1EditText.getText().toString();
                    }

                    String name = nameEditText.getText().toString();
                    String age = ageEditText.getText().toString();

                    keys[0] = "email"; keys[1] = "password"; keys[2] = "name"; keys[3] = "age";
                    data[0] = email; data[1] = password; data[2] = name; data[3] = age;

                    Log.i("test", email);

                    // 확인 코드 모드로 변환
                    okButton.setClickable(false);
                    okButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.basic_button_unclick));

                    emailTextView.setText("확인 코드");
                    emailEditText.setInputType(2);
                    emailEditText.setText("");

                    password1TextView.setVisibility(View.INVISIBLE);
                    password1EditText.setVisibility(View.INVISIBLE);
                    password2TextView.setVisibility(View.INVISIBLE);
                    password2EditText.setVisibility(View.INVISIBLE);
                    nameTextView.setVisibility(View.INVISIBLE);
                    nameEditText.setVisibility(View.INVISIBLE);
                    ageTextView.setVisibility(View.INVISIBLE);
                    ageEditText.setVisibility(View.INVISIBLE);

                    step = 1;
                }
                else if(step == 1){
                    new Thread() {
                        public void run() {
                            result = HttpPostData.POST("account/signup/", keys, data);

                            Log.i("result : ", result);

                            switch (result) {
                                case "1":{
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(0, 0);
                                    finish();
                                    break;
                                }

                                case "-1":
                                case "SEND_FAIL":
                                case "NO_DATA_RECEIVED": {
                                    Message msg = mHandler.obtainMessage();
                                    mHandler.sendMessage(msg);
                                    break;
                                }
                            }
                        }
                    }.start();
                }
            }
        });

        // 이메일 EditText
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (step == 0){

                }
                else if(step == 1){

                }
                insert1 = !emailEditText.getText().toString().equals("") && android.util.Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // 비밀번호1 EditText
        password1EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                insert2 = !password1EditText.getText().toString().equals("");
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        // 비밀번호2 EditText
        password2EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                insert3 = !password1EditText.getText().toString().equals("");
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        // 이름 EditText
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                insert4 = !nameEditText.getText().toString().equals("");
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        // 나이 EditText
        ageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                insert2 = !ageEditText.getText().toString().equals("");
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        // 뒤로가기 버튼
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });


    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        @SuppressLint({"HandlerLeak", "SetTextI18n"})
        public void handleMessage(Message msg) {
            alertTextView.setVisibility(View.VISIBLE);
        }
    };

    // 뒤로가기 버튼
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}