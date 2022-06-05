package com.app_hg.oncreatee.librerys.stikert;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.Transliterator;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;
import androidx.emoji.bundled.BundledEmojiCompatConfig;
import androidx.emoji.text.EmojiCompat;

import com.app_hg.oncreatee.Edit_main;
import com.app_hg.oncreatee.R;
import com.app_hg.oncreatee.librerys.PickerColor;


public class StickerImageView extends StickerView {

    private ImageView iv_main;
    private  TextView textoemoji;
    public TextView textoM;

    public StickerImageView(Context context) {
        super(context);
    }

    public StickerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StickerImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

  /*  public void setOwnerId(String owner_id){
        this.owner_id = owner_id;
    }

    public String getOwnerId(){
        return this.owner_id;
    }*/

    @Override
    public View getMainView(int position) {
        switch (position) {
            case 0:
                if (this.iv_main == null) {
                    this.iv_main = new ImageView(getContext());
                    this.iv_main.setScaleType(ImageView.ScaleType.FIT_XY);
                }
                return iv_main;

            case 1:
                if (this.textoemoji == null) {
                    this.textoemoji = new TextView(getContext());
                    this.textoemoji.setTextColor(getResources().getColor(R.color.BLANCO));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        this.textoemoji.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
                    }


                }
                return textoemoji;

            case 2:
                if (this.textoM == null) {
                    this.textoM = new TextView(getContext());
                    this.textoM.setTextColor(getResources().getColor(R.color.BLANCO));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        this.textoM.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
                    }

                   /* TextViewCompat.setAutoSizeTextTypeWithDefaults(this.textoM,
                           TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);*/

                }
                return textoM;

            default:
                break;
        }

        return null;
    }
    public void setImageBitmap(Bitmap bmp){
        this.iv_main.setImageBitmap(bmp);
    }

    public void setImageResource(int res_id){
        this.iv_main.setImageResource(res_id);
    }

 /*   public void setImageDrawable(Drawable drawable){ this.iv_main.setImageDrawable(drawable); }

    public Bitmap getImageBitmap(){ return ((BitmapDrawable)this.iv_main.getDrawable()).getBitmap() ; }*/


    public void addEmoji(String emojiName) {
        this.textoemoji.setGravity(Gravity.CENTER);
        this.textoemoji.setTextSize(30);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT){
            if (EmojiCompat.get().getLoadState() == EmojiCompat.LOAD_STATE_SUCCEEDED) {
                CharSequence emoji_processed = EmojiCompat.get().process(emojiName);
                this.textoemoji.setText(emoji_processed);
            }
        }else this.textoemoji.setText(emojiName);

        this.iv_flip.setTag("emoji");
    }
    public void addtextoM(String texto) {
        this.textoM.setGravity(Gravity.CENTER);
        this.textoM.setTextSize(30);
        this.textoM.setText(texto);
        this.iv_flip.setTag("texto");
    }

    public TextView getTextviewM(){
        return this.textoM;
    }

    @Override
    public void setSize(int size) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            this.textoM.setTextSize(size);
            this.textoM.setGravity(Gravity.CENTER);

            this.textoemoji.setTextSize(size);
            this.textoemoji.setGravity(Gravity.CENTER);
        }
    }
}
