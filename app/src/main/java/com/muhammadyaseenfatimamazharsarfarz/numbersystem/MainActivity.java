package com.muhammadyaseenfatimamazharsarfarz.numbersystem;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private EditText etvDecimal, etvBinary, etvOcta, etvHexa;
    private Button clear_text;
    private String value;
    private View.OnFocusChangeListener onFocusChangeListener;
    private int focusViewId;
    private TextWatcher textWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etvDecimal = findViewById(R.id.etvDecimal);
        etvBinary = findViewById(R.id.etvBinary);
        etvOcta = findViewById(R.id.etvOcta);
        etvHexa = findViewById(R.id.etvHexa);
        clear_text = findViewById(R.id.clear_text);
        clear_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearField();

            }
        });
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int end, int count) {
                value = ((EditText)findViewById(focusViewId)).getText().toString();
                if (value.length() > 0) {
                    convertNumber();
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        onFocusChangeListener=new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus){
                    //Toast.makeText(MainActivity.this, "Focus In", Toast.LENGTH_SHORT).show();
                    focusViewId = v.getId();
                    ((EditText) findViewById(focusViewId)).addTextChangedListener(textWatcher);
                    GradientDrawable gradientDrawable=new GradientDrawable(
                            GradientDrawable.Orientation.TR_BL,
                            new int[]{Color.parseColor("#cccc30"),Color.parseColor("#EC8282")}
                    );
                    gradientDrawable.setShape(GradientDrawable.RECTANGLE);
                    gradientDrawable.setCornerRadius(10);
                    if(focusViewId==R.id.etvDecimal){
                        gradientDrawable.setStroke(8, ContextCompat.getColor(getApplicationContext(),
                                android.R.color.holo_green_light));

                    }else {
                        gradientDrawable.setStroke(8, ContextCompat.getColor(getApplicationContext(),
                                android.R.color.holo_blue_light));

                    }

                    gradientDrawable.setStroke(8,Color.RED);
                    v.setBackground(gradientDrawable);
                }
                else {

                    ((EditText)findViewById(focusViewId)).removeTextChangedListener(textWatcher);
                    if(focusViewId==R.id.etvDecimal){
                        v.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.focus_view_design));
                    }
                    else {
                        v.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.focus_view_design));
                    }
                }
            }
        };

        etvDecimal.setOnFocusChangeListener(onFocusChangeListener);
        etvBinary.setOnFocusChangeListener(onFocusChangeListener);
        etvOcta.setOnFocusChangeListener(onFocusChangeListener);
        etvHexa.setOnFocusChangeListener(onFocusChangeListener);
    }

    private void clearField() {
        etvDecimal.setText("");
        etvBinary.setText("");
        etvOcta.setText("");
        etvHexa.setText("");

    }


    private void convertNumber() {
        try {
            long num = 0;
            switch (focusViewId){
                case R.id.etvDecimal:
                    num=Long.parseLong(value);
                    etvBinary.setText(String.valueOf(Long.toBinaryString(num)));

                    etvOcta.setText(String.valueOf(Long.toOctalString(num)));
                    etvHexa.setText(String.valueOf(Long.toHexString(num)));
                    break;
                case R.id.etvBinary:
                    num=Long.parseLong(value,2);
                    etvDecimal.setText(String.valueOf(num));
                    etvOcta.setText(String.valueOf(Long.toOctalString(num)));
                    etvHexa.setText(String.valueOf(Long.toHexString(num)));
                    break;
                case R.id.etvOcta:
                    num=Long.parseLong(value,8);
                    etvDecimal.setText(String.valueOf(num));
                    etvBinary.setText(String.valueOf(Long.toBinaryString(num)));
                    etvHexa.setText(String.valueOf(Long.toHexString(num)));
                    break;
                case R.id.etvHexa:
                    num=Long.parseLong(value,16);
                    etvDecimal.setText(String.valueOf(num));
                    etvBinary.setText(String.valueOf(Long.toBinaryString(num)));
                    etvOcta.setText(String.valueOf(Long.toOctalString(num)));

                    break;
            }


        } catch (NumberFormatException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}