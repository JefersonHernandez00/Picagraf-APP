package com.app_hg.oncreatee.librerys.stikert;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.app_hg.oncreatee.R;
import com.app_hg.oncreatee.TextEditorDialogFragment;

import static com.app_hg.oncreatee.Edit_main.viewtextlist;


public abstract class StickerView extends FrameLayout {

    public static final String TAG = "com.knef.stickerView";
    private Context contextp;
    private BorderView iv_border;
    private ImageView iv_scale;
    private ImageView iv_delete;
    public ImageView iv_flip;
    public ImageView iv_edit;
    // For scalling
    private float this_orgX = -1, this_orgY = -1;
    private float scale_orgX = -1, scale_orgY = -1;
    private double scale_orgWidth = -1, scale_orgHeight = -1;
    // For rotating
    private float rotate_orgX = -1, rotate_orgY = -1, rotate_newX = -1, rotate_newY = -1;
    // For moving
    private float move_orgX =-1, move_orgY = -1;
    private double centerX, centerY;
    private final static int BUTTON_SIZE_DP = 25;
    private final static int SELF_SIZE_DP = 100;
    LayoutParams this_params;
    int size;
    public static Boolean typetxto = false;
    public int pot;
    public static int valor_main_pot;
    private int cnt = 30;


    public StickerView(Context context) {
        super(context);
        init(context);
        contextp = context;
    }

    public StickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StickerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context){
        this.iv_border = new BorderView(context);
        this.iv_scale = new ImageView(context);
        this.iv_delete = new ImageView(context);
        this.iv_flip = new ImageView(context);
        this.iv_edit = new ImageView(context);
        this.pot =  valor_main_pot;

        this.iv_scale.setImageResource(R.drawable.zoominout);
        this.iv_delete.setImageResource(R.drawable.remove);
        this.iv_flip.setImageResource(R.drawable.flip);
        this.iv_edit.setImageResource(R.drawable.pencil);

        this.setTag("DraggableViewGroup");
        this.iv_border.setTag("iv_border");
        this.iv_scale.setTag("iv_scale");
        this.iv_delete.setTag("iv_delete");
        this.iv_flip.setTag("iv_flip");
        this.iv_edit.setTag("iv_pencil");

        int margin = convertDpToPixel(BUTTON_SIZE_DP, getContext())/2;
        size = convertDpToPixel(SELF_SIZE_DP, getContext());


        if (typetxto){
            this_params = new LayoutParams(
                    size*2,
                    size/2
            );
            this_params.gravity = Gravity.CENTER;
        }else {
            this_params = new LayoutParams(
                    size,
                    size
            );
            this_params.gravity = Gravity.CENTER;
        }


        LayoutParams iv_main_params = new LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                );
        iv_main_params.setMargins(margin,margin,margin,margin);

        LayoutParams iv_border_params =
                new LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                );
        iv_border_params.setMargins(margin,margin,margin,margin);

        LayoutParams iv_scale_params =
                new LayoutParams(
                        convertDpToPixel(BUTTON_SIZE_DP, getContext()),
                        convertDpToPixel(BUTTON_SIZE_DP, getContext())
                );
        iv_scale_params.gravity = Gravity.BOTTOM | Gravity.RIGHT;

        LayoutParams iv_delete_params =
                new LayoutParams(
                        convertDpToPixel(BUTTON_SIZE_DP, getContext()),
                        convertDpToPixel(BUTTON_SIZE_DP, getContext())
                );
        iv_delete_params.gravity = Gravity.TOP | Gravity.RIGHT;

        LayoutParams iv_flip_params =
                new LayoutParams(
                        convertDpToPixel(BUTTON_SIZE_DP, getContext()),
                        convertDpToPixel(BUTTON_SIZE_DP, getContext())
                );
        iv_flip_params.gravity = Gravity.TOP | Gravity.LEFT;

        LayoutParams iv_edit_params =
                new LayoutParams(
                        convertDpToPixel(BUTTON_SIZE_DP, getContext()),
                        convertDpToPixel(BUTTON_SIZE_DP, getContext())
                );
        iv_edit_params.gravity = Gravity.BOTTOM | Gravity.LEFT;

        this.setLayoutParams(this_params);
        this.addView(getMainView(0), iv_main_params);
        this.addView(getMainView(1), iv_main_params);
        this.addView(getMainView(2), iv_main_params);
        this.addView(iv_border, iv_border_params);
        this.addView(iv_scale, iv_scale_params);
        this.addView(iv_delete, iv_delete_params);
        this.addView(iv_flip, iv_flip_params);
        if (typetxto){
            this.addView(iv_edit, iv_edit_params);
        }



        this.setOnTouchListener(mTouchListener);
        this.iv_scale.setOnTouchListener(mTouchListener);

        this.iv_delete.setOnClickListener(view -> {
            if(StickerView.this.getParent()!=null){
                ViewGroup myCanvas = ((ViewGroup)StickerView.this.getParent());
                myCanvas.removeView(StickerView.this);
            }
        });
        this.iv_flip.setOnClickListener(view -> {
            View mainView;
            if(view.getTag().equals("emoji")){
                 mainView = getMainView(1);
            }else if (view.getTag().equals("texto")){
                mainView = getMainView(2);
            }else mainView = getMainView(0);

            Log.v(TAG, "flip the view");
            mainView.setRotationY(mainView.getRotationY() == -180f? 0f: -180f);
            mainView.invalidate();
            requestLayout();
        });

        this.iv_edit.setOnClickListener(view -> {
            setControlItemsHidden(true);
            String textr = viewtextlist.get(this.pot).getTextviewM().getText().toString();
            int colorcode = viewtextlist.get(this.pot).getTextviewM().getCurrentTextColor();
            Typeface typefacer = viewtextlist.get(this.pot).getTextviewM().getTypeface();

            TextEditorDialogFragment textEditorDialogFragment =
                    TextEditorDialogFragment.show((AppCompatActivity) contextp,textr,colorcode,typefacer);
            textEditorDialogFragment.setOnTextEditorListener((inputText, colorCode,typeface) -> {
                viewtextlist.get(this.pot).getTextviewM().setText(inputText);
                viewtextlist.get(this.pot).getTextviewM().setTextColor(colorCode);
                viewtextlist.get(this.pot).getTextviewM().setTypeface(typeface);
            });
        });


    }

    public boolean isFlip(){
        return getMainView(0).getRotationY() == -180f;
    }

    protected abstract View getMainView(int position);

    private OnTouchListener mTouchListener = new OnTouchListener() {

        @Override
        public boolean onTouch(View view, MotionEvent event) {

            if(view.getTag().equals("DraggableViewGroup")) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.v(TAG, "sticker view action down");
                        move_orgX = event.getRawX();
                        move_orgY = event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:

                            Log.v(TAG, "sticker view action move");
                        float offsetX = event.getRawX() - move_orgX;
                        float offsetY = event.getRawY() - move_orgY;
                        StickerView.this.setX(StickerView.this.getX()+offsetX);
                        StickerView.this.setY(StickerView.this.getY() + offsetY);
                        move_orgX = event.getRawX();
                        move_orgY = event.getRawY();
                        break;

                    case MotionEvent.ACTION_UP:
                        Log.v(TAG, "sticker view action up");
                        if (iv_border.getVisibility() == INVISIBLE){
                            setControlItemsHidden(false);
                        }else setControlItemsHidden(true);

                        break;
                }
            }else if(view.getTag().equals("iv_scale")){

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.v(TAG, "iv_scale action down");

                        this_orgX = StickerView.this.getX();
                        this_orgY = StickerView.this.getY();

                        scale_orgX = event.getRawX();
                        scale_orgY = event.getRawY();
                        scale_orgWidth = StickerView.this.getLayoutParams().width;
                        scale_orgHeight = StickerView.this.getLayoutParams().height;

                        rotate_orgX = event.getRawX();
                        rotate_orgY = event.getRawY();

                        centerX = StickerView.this.getX()+
                                ((View)StickerView.this.getParent()).getX()+
                                (float)StickerView.this.getWidth()/2;


                        //double statusBarHeight = Math.ceil(25 * getContext()
                        // .getResources().getDisplayMetrics().density);
                        int result = 0;
                        int resourceId = getResources()
                                .getIdentifier("status_bar_height", "dimen", "android");
                        if (resourceId > 0) {
                            result = getResources().getDimensionPixelSize(resourceId);
                        }
                        double statusBarHeight = result;
                        centerY = StickerView.this.getY()+
                                ((View)StickerView.this.getParent()).getY()+
                                statusBarHeight+
                                (float)StickerView.this.getHeight()/2;

                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.v(TAG, "iv_scale action move");

                        rotate_newX = event.getRawX();
                        rotate_newY = event.getRawY();

                        double angle_diff = Math.abs(
                                Math.atan2(event.getRawY() - scale_orgY , event.getRawX() - scale_orgX)
                                        - Math.atan2(scale_orgY - centerY, scale_orgX - centerX))*180/ Math.PI;

                        Log.v(TAG, "angle_diff: "+angle_diff);

                        double length1 = getLength(centerX, centerY, scale_orgX, scale_orgY);
                        double length2 = getLength(centerX, centerY, event.getRawX(), event.getRawY());

                        int size = convertDpToPixel(SELF_SIZE_DP, getContext());
                        if(length2 > length1
                                && (angle_diff < 25 || Math.abs(angle_diff-180)<25)
                        ) {
                            //scale up
                            double offsetX = Math.abs(event.getRawX() - scale_orgX);
                            double offsetY = Math.abs(event.getRawY() - scale_orgY);
                            double offset = Math.max(offsetX, offsetY);
                            offset = Math.round(offset);

                            StickerView.this.getLayoutParams().width += offset;
                            StickerView.this.getLayoutParams().height += offset;

                            //aumentar textsize
                            cnt++;
                            Log.v("size +",String.valueOf(cnt));
                            setSize(cnt);

                            onScaling(true);
                            //DraggableViewGroup.this.setX((float) (getX() - offset / 2));
                            //DraggableViewGroup.this.setY((float) (getY() - offset / 2));
                        }else if(length2 < length1
                                && (angle_diff < 25 || Math.abs(angle_diff-180)<25)
                                && StickerView.this.getLayoutParams().width > size/2
                                && StickerView.this.getLayoutParams().height > size/2)
                        {
                            //scale down
                            double offsetX = Math.abs(event.getRawX() - scale_orgX);
                            double offsetY = Math.abs(event.getRawY() - scale_orgY);
                            double offset = Math.max(offsetX, offsetY);
                            offset = Math.round(offset);
                            StickerView.this.getLayoutParams().width -= offset;
                            StickerView.this.getLayoutParams().height -= offset;

                            if (cnt >= 30) cnt--;
                            Log.v("size -",String.valueOf(cnt));
                            setSize(cnt);

                            onScaling(false);
                        }

                        //rotate

                        double angle = Math.atan2(
                                event.getRawY() - centerY, event.getRawX() - centerX) * 180 / Math.PI;
                        Log.v(TAG, "log angle: " + angle);

                        //setRotation((float) angle - 45);
                        setRotation((float) angle - 45);
                        Log.v(TAG, "getRotation(): " + getRotation());

                        onRotating();

                        rotate_orgX = rotate_newX;
                        rotate_orgY = rotate_newY;

                        scale_orgX = event.getRawX();
                        scale_orgY = event.getRawY();

                        postInvalidate();
                        requestLayout();

                        break;
                    case MotionEvent.ACTION_UP:
                        Log.v(TAG, "iv_scale action up");
                        break;
                }




            }
            return true;
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private double getLength(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(y2-y1, 2)+ Math.pow(x2-x1, 2));
    }

    private float[] getRelativePos(float absX, float absY){
        Log.v("ken", "getRelativePos getX:"+((View)this.getParent()).getX());
        Log.v("ken", "getRelativePos getY:"+((View)this.getParent()).getY());
        float [] pos = new float[]{
                absX-((View)this.getParent()).getX(),
                absY-((View)this.getParent()).getY()
        };
        Log.v(TAG, "getRelativePos absY:"+absY);
        Log.v(TAG, "getRelativePos relativeY:"+pos[1]);
        return pos;
    }

    public void setControlItemsHidden(boolean isHidden){
        if(isHidden) {
            iv_border.setVisibility(View.INVISIBLE);
            iv_scale.setVisibility(View.INVISIBLE);
            iv_delete.setVisibility(View.INVISIBLE);
            iv_flip.setVisibility(View.INVISIBLE);
            iv_edit.setVisibility(View.INVISIBLE);
        }else{
            iv_border.setVisibility(View.VISIBLE);
            iv_scale.setVisibility(View.VISIBLE);
            iv_delete.setVisibility(View.VISIBLE);
            iv_flip.setVisibility(View.VISIBLE);
            iv_edit.setVisibility(View.VISIBLE);
        }
    }

    protected View getImageViewFlip(){
        return iv_flip;
    }

    protected void onScaling(boolean scaleUp){}

    protected void onRotating(){}

    public void setSize(int size) { }


    private class BorderView extends View {

        public BorderView(Context context) {
            super(context);
        }

        public BorderView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public BorderView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // Draw sticker border

            LayoutParams params = (LayoutParams)this.getLayoutParams();
            Rect border = new Rect();
            border.left = this.getLeft() - params.leftMargin;
            border.top = this.getTop() - params.topMargin;
            border.right = this.getRight() - params.rightMargin;
            border.bottom = this.getBottom() - params.bottomMargin;
            Paint borderPaint = new Paint();
            borderPaint.setStrokeWidth(6);
            borderPaint.setColor(Color.WHITE);
            borderPaint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(border, borderPaint);

        }
    }
    private static int convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int)px;
    }
}
