package com.app_hg.oncreatee.librerys.filters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.app_hg.oncreatee.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import ja.burhanrashid52.photoeditor.PhotoFilter;

import static com.app_hg.oncreatee.Edit_main.bitmapM;
import static com.app_hg.oncreatee.Edit_main.iconCompat;
import static com.app_hg.oncreatee.Edit_main.mPhotoEditorView;



public class FilterViewAdapter extends RecyclerView.Adapter<FilterViewAdapter.ViewHolder> {

    private FilterImageView filterImageView;
    public static ImageFilterViewX imageFilterViewX;
    View view;

    public FilterViewAdapter() {
        setupFilters();
    }

    private List<Pair<String, PhotoFilter>> mPairList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_filter_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pair<String, PhotoFilter> filterPair = mPairList.get(position);
        Bitmap fromAsset = getBitmapFromAsset(holder.itemView.getContext(), filterPair.first);

        holder.mImageFilterView.setImageBitmap(fromAsset);
        holder.mTxtFilterName.setText(filterPair.second.name().replace("_", " "));

        filterImageView = new FilterImageView(holder.itemView.getContext());
        imageFilterViewX = new ImageFilterViewX(holder.itemView.getContext());

    }

    @Override
    public int getItemCount() {
        return mPairList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageFilterView;
        TextView mTxtFilterName;

        ViewHolder(View itemView) {
            super(itemView);
            mImageFilterView = itemView.findViewById(R.id.imgFilterView);
            mTxtFilterName = itemView.findViewById(R.id.txtFilterName);
            //Align brush to the size of image view
            RelativeLayout.LayoutParams imgFilterParam = new RelativeLayout.LayoutParams(
                    bitmapM.getWidth(), bitmapM.getHeight());
            imgFilterParam.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            imgFilterParam.addRule(RelativeLayout.ALIGN_LEFT,1);
            imgFilterParam.addRule(RelativeLayout.ALIGN_RIGHT,1);
            imgFilterParam.addRule(RelativeLayout.ALIGN_TOP, 1);
            imgFilterParam.addRule(RelativeLayout.ALIGN_BOTTOM, 1);

            itemView.setOnClickListener(v ->{
                mPhotoEditorView.removeView(imageFilterViewX);
                mPhotoEditorView.addView(imageFilterViewX,imgFilterParam);
                imageFilterViewX.setVisibility(View.VISIBLE);
                imageFilterViewX.setSourceBitmap(bitmapM);
                imageFilterViewX.setFilterEffect(mPairList.get(getLayoutPosition()).second);
                saveFilter(new OnSaveBitmap() {
                    @Override
                    public void onBitmapReady(Bitmap saveBitmap) {
                        mPhotoEditorView.getSource().setImageDrawable(new BitmapDrawable(iconCompat,saveBitmap));
                        /*Handler handler = new Handler();
                        handler.postDelayed(() -> {
                            mPhotoEditorView.getSource().setImageDrawable(new BitmapDrawable(iconCompat,bitmapM));
                        }, 4000); // 2 segundos de "delay"*/
                    }
                    @Override
                    public void onFailure(Exception e) { }
                });
            });
        }
    }

    private Bitmap getBitmapFromAsset(Context context, String strName) {
        AssetManager assetManager = context.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(strName);
            return BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    void saveFilter(@NonNull final OnSaveBitmap onSaveBitmap) {
        if (imageFilterViewX.getVisibility() == View.VISIBLE) {
            imageFilterViewX.saveBitmap(new OnSaveBitmap() {
                @Override
                public void onBitmapReady(final Bitmap saveBitmap) {
                    filterImageView.setImageBitmap(saveBitmap);
                    imageFilterViewX.setVisibility(View.GONE);
                    onSaveBitmap.onBitmapReady(saveBitmap);
                }
                @Override
                public void onFailure(Exception e) {
                    onSaveBitmap.onFailure(e);
                }
            });
        } else {
            onSaveBitmap.onBitmapReady(filterImageView.getBitmap());
        }
    }

    private void setupFilters() {
        mPairList.add(new Pair<>("filters/original.jpeg", PhotoFilter.NONE));
        mPairList.add(new Pair<>("filters/auto_fix.jpeg", PhotoFilter.AUTO_FIX));
        mPairList.add(new Pair<>("filters/brightness.jpeg", PhotoFilter.BRIGHTNESS));
        mPairList.add(new Pair<>("filters/contrast.jpeg", PhotoFilter.CONTRAST));
        mPairList.add(new Pair<>("filters/documentary.jpeg", PhotoFilter.DOCUMENTARY));
        mPairList.add(new Pair<>("filters/duo_tone.jpeg", PhotoFilter.DUE_TONE));
        mPairList.add(new Pair<>("filters/fill_light.jpeg", PhotoFilter.FILL_LIGHT));
        mPairList.add(new Pair<>("filters/fish_eye.jpeg", PhotoFilter.FISH_EYE));
        mPairList.add(new Pair<>("filters/grain.jpeg", PhotoFilter.GRAIN));
        mPairList.add(new Pair<>("filters/gray_scale.jpeg", PhotoFilter.GRAY_SCALE));
        mPairList.add(new Pair<>("filters/lomish.jpeg", PhotoFilter.LOMISH));
        mPairList.add(new Pair<>("filters/negative.jpeg", PhotoFilter.NEGATIVE));
        mPairList.add(new Pair<>("filters/posterize.jpeg", PhotoFilter.POSTERIZE));
        mPairList.add(new Pair<>("filters/sature.jpeg", PhotoFilter.SATURATE));
        mPairList.add(new Pair<>("filters/sepia.jpeg", PhotoFilter.SEPIA));
        mPairList.add(new Pair<>("filters/sharpen.jpeg", PhotoFilter.SHARPEN));
        mPairList.add(new Pair<>("filters/temperature.jpeg", PhotoFilter.TEMPERATURE));
        mPairList.add(new Pair<>("filters/tint.jpeg", PhotoFilter.TINT));
        mPairList.add(new Pair<>("filters/vignette.jpeg", PhotoFilter.VIGNETTE));
        mPairList.add(new Pair<>("filters/cross.jpeg", PhotoFilter.CROSS_PROCESS));
        mPairList.add(new Pair<>("filters/black_white.jpeg", PhotoFilter.BLACK_WHITE));
        mPairList.add(new Pair<>("filters/flip_h.jpeg", PhotoFilter.FLIP_HORIZONTAL));
        mPairList.add(new Pair<>("filters/flip_v.jpeg", PhotoFilter.FLIP_VERTICAL));
        mPairList.add(new Pair<>("filters/rotate.jpeg", PhotoFilter.ROTATE));
    }

}
