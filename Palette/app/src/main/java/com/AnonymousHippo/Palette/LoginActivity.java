package com.AnonymousHippo.Palette;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.AnonymousHippo.Palette.HttpPostData;

public class LoginActivity extends BaseActivity {

    private String[] keys = new String[1];
    private String[] datas = new String[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button okButton = findViewById(R.id.Login_Button_login);
        final EditText emailEditText = findViewById(R.id.Login_EditText_email);
        final EditText passwordEditText = findViewById(R.id.Login_EditText_password);

        //test
        okButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               String email = emailEditText.getText().toString();
               String password = passwordEditText.getText().toString();

               keys[0] = email;
               datas[0] = password;

               String result = HttpPostData.POST(keys, datas);
           }
        });
    }
}