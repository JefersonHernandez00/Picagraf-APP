package com.app_hg.oncreatee.librerys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app_hg.oncreatee.R;
import com.rarepebble.colorpicker.ColorPickerView;


public class PickerColor extends Activity {

    ColorPickerView colorPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picker_color);

        Button cancelar = findViewById(R.id.cancelar);
        Button aceptar = findViewById(R.id.aceptar);
        Button multicolor = findViewById(R.id.multicolor);
        colorPicker = findViewById(R.id.colorPicker);
        cancelar.setOnClickListener(oncliF);
        aceptar.setOnClickListener(oncliF);
        multicolor.setOnClickListener(oncliF);
        colorPicker.setColor(0xffff0000);
    }

    private View.OnClickListener oncliF = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cancelar:
                    finish();
                    break;

                case R.id.aceptar:
                    Intent databack = new Intent();
                    databack.putExtra("color", colorPicker.getColor());
                    setResult(RESULT_OK, databack);
                    finish();
                    break;

                case R.id.multicolor:
                    Intent multic = new Intent();
                    multic.putExtra("colorM",true);
                    setResult(RESULT_OK, multic);
                    finish();
                    break;
            }
        }
    };
}
