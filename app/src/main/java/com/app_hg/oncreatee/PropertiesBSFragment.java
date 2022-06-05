package com.app_hg.oncreatee;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app_hg.oncreatee.librerys.PickerColor;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import static android.app.Activity.RESULT_OK;
import static com.app_hg.oncreatee.Edit_main.ColorBrocha;
import static com.app_hg.oncreatee.Edit_main.mPhotoEditor;

public class PropertiesBSFragment extends BottomSheetDialogFragment
        implements SeekBar.OnSeekBarChangeListener {

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ColorBrocha){
            if (resultCode == RESULT_OK) {
                if (mProperties != null) {
                    dismiss();
                    mProperties.onColorChanged(data.getIntExtra("color", 0));
                }
            }
        }
    }

    public PropertiesBSFragment() {
        // Required empty public constructor
    }

    private Properties mProperties;

    public interface Properties {
        void onColorChanged(int colorCode);

        void onOpacityChanged(int opacity);

        void onBrushSizeChanged(int brushSize);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_properties_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SeekBar sbOpacity = view.findViewById(R.id.sbOpacity);
        SeekBar sbBrushSize = view.findViewById(R.id.sbSize);
        Button colorbrocha = view.findViewById(R.id.buttoncolor);

        if (mProperties != null) {
            mProperties.onColorChanged(mPhotoEditor.getBrushColor());
        }
        sbOpacity.setOnSeekBarChangeListener(this);
        sbBrushSize.setOnSeekBarChangeListener(this);

        colorbrocha.setOnClickListener(view1 -> {
            Intent inten = new Intent(view.getContext(), PickerColor.class);
            startActivityForResult(inten, ColorBrocha);
        });

    }

    public void setPropertiesChangeListener(Properties properties) {
        mProperties = properties;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            case R.id.sbOpacity:
                if (mProperties != null) {
                    mProperties.onOpacityChanged(i);
                   // dismiss();
                }
                break;
            case R.id.sbSize:
                if (mProperties != null) {
                    mProperties.onBrushSizeChanged(i);
                  //  dismiss();
                }
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}