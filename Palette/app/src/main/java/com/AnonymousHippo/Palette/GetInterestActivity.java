package com.AnonymousHippo.Palette;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.igalata.bubblepicker.BubblePickerListener;
import com.igalata.bubblepicker.adapter.BubblePickerAdapter;
import com.igalata.bubblepicker.model.BubbleGradient;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;

import org.jetbrains.annotations.NotNull;

public class GetInterestActivity extends BaseActivity {

    BubblePicker bubblePicker;

    String[] titles = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    int[] colors = {Color.parseColor("#FFB5D8"), Color.parseColor("#A7E7FF"), Color.parseColor("#FFEEAC"), Color.parseColor("#CBFFD2"), Color.parseColor("#F8D5FF"),
            Color.parseColor("#FFB5D8"), Color.parseColor("#A7E7FF"), Color.parseColor("#FFEEAC"), Color.parseColor("#CBFFD2"), Color.parseColor("#F8D5FF")};
    int[] images = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_interest);

        Button okButton = findViewById(R.id.GetInterest_Button_done);

        bubblePicker = findViewById(R.id.GetInterest_BubblePicker_main);
        bubblePicker.setAdapter(new BubblePickerAdapter() {
            @Override
            public int getTotalCount() {
                return titles.length;
            }

            @NotNull
            @Override
            public PickerItem getItem(int i) {
                PickerItem pickerItem = new PickerItem();

                pickerItem.setTitle(titles[i]);
                pickerItem.setGradient(new BubbleGradient(colors[i], colors[i], BubbleGradient.HORIZONTAL));
                pickerItem.setTextColor(Color.BLACK);

                return pickerItem;
            }
        });

        bubblePicker.setListener(new BubblePickerListener() {
            @Override
            public void onBubbleSelected(@NotNull PickerItem pickerItem) {
                Log.i("selected", pickerItem.getTitle());
            }

            @Override
            public void onBubbleDeselected(@NotNull PickerItem pickerItem) {
                Log.i("selected", pickerItem.getTitle());
            }
        });

        bubblePicker.setBubbleSize(3);
        bubblePicker.setMaxSelectedCount(5);
        bubblePicker.setCenterImmediately(true);

        // 완료 버튼
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        bubblePicker.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        bubblePicker.onPause();
    }

    // 뒤로가기 버튼
    @Override
    public void onBackPressed() {
        //
    }
}