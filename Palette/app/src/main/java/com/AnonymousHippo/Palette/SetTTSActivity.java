package com.AnonymousHippo.Palette;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.Locale;

public class SetTTSActivity extends BaseActivity {

    // TTS instance
    private TextToSpeech tts;
    private EditText sampleEditText;

    // TTS State
    private boolean TTS_FLAG;
    private float TTS_Pitch;
    private float TTS_Speed;

    private Button listenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_tts);

        // 인스턴스화
        ImageButton backButton = findViewById(R.id.SetTTS_ImageButton_back);
        Button okButton = findViewById(R.id.SetTTS_Button_done);
        listenButton = findViewById(R.id.SetTTS_Button_listen);
        sampleEditText = findViewById(R.id.SetTTS_EditText_sample);

        // 초기화
        TTS_FLAG = false;
        SharedPreferences preferences = getSharedPreferences("com.AnonymousHippo.Palette.sharePreference", MODE_PRIVATE);
        TTS_Pitch = preferences.getFloat("TTS_Pitch", 0.7f);
        TTS_Speed = preferences.getFloat("TTS_Speed", 1.0f);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int language = tts.setLanguage(Locale.KOREAN);  // 언어 설정
                    //언어 데이터가 없거나 혹은 언어가 지원하지 않으면...
                    if (language == TextToSpeech.LANG_MISSING_DATA || language == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(getApplicationContext(), "이 언어는 지원하지 않습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        listenButton.setEnabled(true);
                        listenButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.basic_button));
                    }
                }
                Log.i("status", String.valueOf(status));
            }
        });

        // 뒤로가기 버튼
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 확인 버튼
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 듣기 버튼
        listenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 재생
                if(!TTS_FLAG){
                    TTS_FLAG = true;
                    listenButton.setText("중지");
                    listenButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.basic_button_darkgray));
                    Speech();
                }
                // 중지
                else{
                    TTS_FLAG = false;
                    listenButton.setText("들어보기");
                    listenButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.basic_button));
                    tts.stop();
                    tts.shutdown();
                }

            }
        });
    }

    private void Speech() {
        String text = sampleEditText.getText().toString().trim();
        tts.setPitch(TTS_Pitch);
        tts.setSpeechRate(TTS_Speed);
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(tts != null){
            tts.stop();
            tts.shutdown();
        }
    }
}