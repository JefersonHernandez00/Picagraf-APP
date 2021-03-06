package com.app_hg.oncreatee.librerys.filters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.effect.Effect;
import android.media.effect.EffectContext;
import android.media.effect.EffectFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import ja.burhanrashid52.photoeditor.PhotoFilter;

import static android.media.effect.EffectFactory.EFFECT_AUTOFIX;
import static android.media.effect.EffectFactory.EFFECT_BLACKWHITE;
import static android.media.effect.EffectFactory.EFFECT_BRIGHTNESS;
import static android.media.effect.EffectFactory.EFFECT_CONTRAST;
import static android.media.effect.EffectFactory.EFFECT_CROSSPROCESS;
import static android.media.effect.EffectFactory.EFFECT_DOCUMENTARY;
import static android.media.effect.EffectFactory.EFFECT_DUOTONE;
import static android.media.effect.EffectFactory.EFFECT_FILLLIGHT;
import static android.media.effect.EffectFactory.EFFECT_FISHEYE;
import static android.media.effect.EffectFactory.EFFECT_FLIP;
import static android.media.effect.EffectFactory.EFFECT_GRAIN;
import static android.media.effect.EffectFactory.EFFECT_GRAYSCALE;
import static android.media.effect.EffectFactory.EFFECT_LOMOISH;
import static android.media.effect.EffectFactory.EFFECT_NEGATIVE;
import static android.media.effect.EffectFactory.EFFECT_POSTERIZE;
import static android.media.effect.EffectFactory.EFFECT_ROTATE;
import static android.media.effect.EffectFactory.EFFECT_SATURATE;
import static android.media.effect.EffectFactory.EFFECT_SEPIA;
import static android.media.effect.EffectFactory.EFFECT_SHARPEN;
import static android.media.effect.EffectFactory.EFFECT_TEMPERATURE;
import static android.media.effect.EffectFactory.EFFECT_TINT;
import static android.media.effect.EffectFactory.EFFECT_VIGNETTE;
import static com.app_hg.oncreatee.Edit_main.HeightGL;
import static com.app_hg.oncreatee.Edit_main.WidthGL;
import static ja.burhanrashid52.photoeditor.PhotoFilter.NONE;


public class ImageFilterViewX extends GLSurfaceView implements GLSurfaceView.Renderer {

    private static final String TAG = "ImageFilterViewX";
    private int[] mTextures = new int[2];
    private EffectContext mEffectContext;
    private Effect mEffect;
    private TextureRenderer mTexRenderer = new TextureRenderer();
    private int mImageWidth;
    private int mImageHeight;
    private boolean mInitialized = false;
    private PhotoFilter mCurrentEffect;
    private Bitmap mSourceBitmap;
    private OnSaveBitmap mOnSaveBitmap;
    private boolean isSaveImage = false;
    Bitmap mFilterBitmap;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.setMeasuredDimension((int) WidthGL, (int) HeightGL);
    }

    public ImageFilterViewX(Context context) {
        super(context);
        init();
    }

    public ImageFilterViewX(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        setEGLContextClientVersion(2);
        setRenderer(this);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setFilterEffect(NONE);
    }

    void setSourceBitmap(Bitmap sourceBitmap) {

        mSourceBitmap = sourceBitmap;
        mInitialized = false;
    }

    void setFilterEffect(PhotoFilter effect) {
        mCurrentEffect = effect;
      requestRender();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    }
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (mTexRenderer != null) {
            mTexRenderer.updateViewSize(width, height);
        }

    }
    @Override
    public void onDrawFrame(GL10 gl) {
        if (!mInitialized) {
            //Only need to do this once
            mEffectContext = EffectContext.createWithCurrentGlContext();
            mTexRenderer.init();
            loadTextures();
            mInitialized = true;
        }
        if (mCurrentEffect != NONE ) {
            //if an effect is chosen initialize it and apply it to the texture
            initEffect();
            applyEffect();
        }
        renderResult();
        if (isSaveImage) {
            mFilterBitmap = BitmapUtil.createBitmapFromGLSurface(this, gl);

            isSaveImage = false;
            if (mOnSaveBitmap != null) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    mOnSaveBitmap.onBitmapReady(mFilterBitmap);
                });
            }
        }
    }

    void saveBitmap(OnSaveBitmap onSaveBitmap) {

        mOnSaveBitmap = onSaveBitmap;
        isSaveImage = true;
        requestRender();
    }

    private void loadTextures() {

        // Generate textures
        GLES20.glGenTextures(2, mTextures, 0);
        // Load input bitmap
        if (mSourceBitmap != null) {
            mImageWidth = mSourceBitmap.getWidth();
            mImageHeight = mSourceBitmap.getHeight();
            mTexRenderer.updateTextureSize(mImageWidth, mImageHeight);
            // Upload to texture
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextures[0]);
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, mSourceBitmap, 0);
            // Set texture parameters
            GLToolbox.initTexParams();
        }

    }

    public void initEffect() {

        EffectFactory effectFactory = mEffectContext.getFactory();
        if (mEffect != null) mEffect.release();

        switch (mCurrentEffect) {
            case AUTO_FIX:
                mEffect = effectFactory.createEffect(EFFECT_AUTOFIX);
                mEffect.setParameter("scale", 0.5f);
                break;
            case BLACK_WHITE:
                mEffect = effectFactory.createEffect(EFFECT_BLACKWHITE);
                mEffect.setParameter("black", .1f);
                mEffect.setParameter("white", .7f);
                break;
            case BRIGHTNESS:
                mEffect = effectFactory.createEffect(EFFECT_BRIGHTNESS);
                mEffect.setParameter("brightness", 2.0f);
                break;
            case CONTRAST:
                mEffect = effectFactory.createEffect(EFFECT_CONTRAST);
                mEffect.setParameter("contrast", 1.4f);
                break;
            case CROSS_PROCESS:
                mEffect = effectFactory.createEffect(EFFECT_CROSSPROCESS);
                break;
            case DOCUMENTARY:
                mEffect = effectFactory.createEffect(EFFECT_DOCUMENTARY);
                break;
            case DUE_TONE:
                mEffect = effectFactory.createEffect(EFFECT_DUOTONE);
                mEffect.setParameter("first_color", Color.YELLOW);
                mEffect.setParameter("second_color", Color.DKGRAY);
                break;
            case FILL_LIGHT:
                mEffect = effectFactory.createEffect(EFFECT_FILLLIGHT);
                mEffect.setParameter("strength", .8f);
                break;
            case FISH_EYE:
                mEffect = effectFactory.createEffect(EFFECT_FISHEYE);
                mEffect.setParameter("scale", .5f);
                break;
            case FLIP_HORIZONTAL:
                mEffect = effectFactory.createEffect(EFFECT_FLIP);
                mEffect.setParameter("horizontal", true);
                break;
            case FLIP_VERTICAL:
                mEffect = effectFactory.createEffect(EFFECT_FLIP);
                mEffect.setParameter("vertical", true);
                break;
            case GRAIN:
                mEffect = effectFactory.createEffect(EFFECT_GRAIN);
                mEffect.setParameter("strength", 1.0f);
                break;
            case GRAY_SCALE:
                mEffect = effectFactory.createEffect(EFFECT_GRAYSCALE);
                break;
            case LOMISH:
                mEffect = effectFactory.createEffect(EFFECT_LOMOISH);
                break;
            case NEGATIVE:
                mEffect = effectFactory.createEffect(EFFECT_NEGATIVE);
                break;
            case NONE:
                break;
            case POSTERIZE:
                mEffect = effectFactory.createEffect(EFFECT_POSTERIZE);
                break;
            case ROTATE:
                mEffect = effectFactory.createEffect(EFFECT_ROTATE);
                mEffect.setParameter("angle", 180);
                break;
            case SATURATE:
                mEffect = effectFactory.createEffect(EFFECT_SATURATE);
                mEffect.setParameter("scale", .5f);
                break;
            case SEPIA:
                mEffect = effectFactory.createEffect(EFFECT_SEPIA);
                break;
            case SHARPEN:
                mEffect = effectFactory.createEffect(EFFECT_SHARPEN);
                break;
            case TEMPERATURE:
                mEffect = effectFactory.createEffect(EFFECT_TEMPERATURE);
                mEffect.setParameter("scale", .9f);
                break;
            case TINT:
                mEffect = effectFactory.createEffect(EFFECT_TINT);
                mEffect.setParameter("tint", Color.MAGENTA);
                break;
            case VIGNETTE:
                mEffect = effectFactory.createEffect(EFFECT_VIGNETTE);
                mEffect.setParameter("scale", .5f);
                break;
        }

    }

    public void applyEffect() {
        mEffect.apply(mTextures[0], mImageWidth, mImageHeight, mTextures[1]);
    }

    private void renderResult() {

        if (mCurrentEffect != NONE ) {
            // if no effect is chosen, just render the original bitmap
            mTexRenderer.renderTexture(mTextures[1]);
        } else {
            // render the result of applyEffect()
            mTexRenderer.renderTexture(mTextures[0]);
        }
    }
}
