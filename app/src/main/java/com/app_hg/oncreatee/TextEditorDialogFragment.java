package com.app_hg.oncreatee;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.app_hg.oncreatee.librerys.PickerColor;

import static android.app.Activity.RESULT_OK;

public class TextEditorDialogFragment extends DialogFragment {

    public static final String TAG = TextEditorDialogFragment.class.getSimpleName();
    public static final String EXTRA_INPUT_TEXT = "extra_input_text";
    public static final String EXTRA_COLOR_CODE = "extra_color_code";
    public int ColorTEXTO = 3;
    public int FuenteTP = 1;
    private EditText mAddTextEditText;
    private ImageView mAddTextDoneTextView,style_fuente,text_col;
    private InputMethodManager mInputMethodManager;
    private int mColorCode;
    private TextEditor mTextEditor;
    Shader shader;
    public static Typeface typeface;
    public static Typeface typefaceN = Typeface.createFromAsset(Edit_main.assetsM
            ,"font/AmorEatPersia.ttf");

    public interface TextEditor {
        void onDone(String inputText, int colorCode,Typeface typeface);
    }

    //Show dialog with provide text and text color
    public static TextEditorDialogFragment
    show(@NonNull AppCompatActivity appCompatActivity,
         @NonNull String inputText, @ColorInt int colorCode,Typeface typeface) {
        Bundle args = new Bundle();
        args.putString(EXTRA_INPUT_TEXT, inputText);
        args.putInt(EXTRA_COLOR_CODE, colorCode);
        my_typeface(typeface);
        TextEditorDialogFragment fragment = new TextEditorDialogFragment();
        fragment.setArguments(args);
        fragment.show(appCompatActivity.getSupportFragmentManager(), TAG);
        return fragment;
    }

    public static Typeface my_typeface(Typeface typeface1){
        return  typeface=typeface1;
    }

    //Show dialog with default text input as empty and text color white
    public static TextEditorDialogFragment show(@NonNull AppCompatActivity appCompatActivity) {
        return show(appCompatActivity,
                " ", ContextCompat.getColor(appCompatActivity, R.color.BLANCO),typefaceN);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_text_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAddTextEditText = view.findViewById(R.id.add_text_edit_text);
        mInputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mAddTextDoneTextView = view.findViewById(R.id.done_tv);
        style_fuente = view.findViewById(R.id.estilofuente);
        text_col = view.findViewById(R.id.colorfuente);

        mAddTextEditText.requestFocus();
        mAddTextEditText.setText(getArguments().getString(EXTRA_INPUT_TEXT));
        mColorCode = getArguments().getInt(EXTRA_COLOR_CODE);
        mAddTextEditText.setTextColor(mColorCode);
        mAddTextEditText.setSelection( mAddTextEditText.getText().length());
        mInputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        mAddTextEditText.setTypeface(typeface);

        mAddTextDoneTextView.setOnClickListener(view1 -> {
            mInputMethodManager.hideSoftInputFromWindow(view1.getWindowToken(), 0);
            dismiss();
            String inputText = mAddTextEditText.getText().toString();
            if (!inputText.isEmpty() && mTextEditor != null) {
                mTextEditor.onDone(inputText, mColorCode,typeface);
            }
        });

        style_fuente.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), Founts.class);
            startActivityForResult(intent, FuenteTP);
            mInputMethodManager.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        });

        text_col.setOnClickListener(view1 -> {
            Intent inten = new Intent(getContext(), PickerColor.class);
            startActivityForResult(inten, ColorTEXTO);
            mInputMethodManager.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ColorTEXTO) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    /*if (data.getBooleanExtra("colorM",false)){
                    textView.paint.reset();

                        Toast.makeText(getContext(), "entra", Toast.LENGTH_SHORT).show();
                        // obtiene shader predeterminado antes de modificarlo para multicolor
                        shader = mAddTextEditText.getPaint().getShader();
                        mAddTextEditText.setHorizontalFadingEdgeEnabled(true);
                        Tvg.change(mAddTextEditText, new int[]{
                                Color.parseColor("#008744"),
                                Color.parseColor("#0057e7"),
                                Color.parseColor("#d62d20"),
                                Color.parseColor("#ffa700"),
                                Color.parseColor("#FFFFFF"),
                        });
                    }else {

                    }*/

                    mColorCode = data.getIntExtra("color", 0);
                    if (shader != null) mAddTextEditText.getPaint().setShader(shader);
                    mAddTextEditText.setTextColor(mColorCode);
                }
            }
        }
        if (requestCode == FuenteTP) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    typeface = Typeface.createFromAsset(Edit_main.assetsM, data.getStringExtra("rf"));
                    mAddTextEditText.setTypeface(typeface);
                }
            }
        }
    }

    //Callback to listener if user is done with text editing
    public void setOnTextEditorListener(TextEditor textEditor) {
        mTextEditor = textEditor;
    }
}
