package com.app_hg.oncreatee;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;

import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import com.google.android.material.snackbar.Snackbar;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static com.app_hg.oncreatee.Edit_main.MY_READ_EXT_STORAGE;
import static com.app_hg.oncreatee.Edit_main.bitmapM;
import static com.app_hg.oncreatee.Edit_main.bitmapSticker;

public class Image_galeria extends AppCompatActivity {

    ImageView imgBase;
    ImageView imgConf ;
    Button recortar ;
    Toolbar toolbar;
    RelativeLayout LimgGalery;
    FrameLayout  pasarImgCut ;

    private static final int SELECT_PICTURE = 5;
    private MediaPlayer mp;
    Bitmap newbitMap,bitmap;
    int new_width,new_height;
    Boolean si_img=false,rotado = false;
    public static Boolean sctiker = false;
    Context context=this;
    private Uri path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_galeria);

        /** identificadores**/
        imgBase = findViewById(R.id.imgBase);
        imgConf = findViewById(R.id.img_conf);
        recortar = findViewById(R.id.recortar);
        toolbar = findViewById(R.id.toolbar);
        LimgGalery = findViewById(R.id.LimgGalery);
        pasarImgCut = findViewById(R.id.pasar_img_cut);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        imgBase.setOnClickListener(onclikf);
        imgConf.setOnClickListener(onclikf);
        recortar.setOnClickListener(onclikf);
        returnGalery();
    }

    private View.OnClickListener onclikf = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.recortar:
                    if (si_img){
                        CropImage.activity(path)
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .start(Image_galeria.this);
                    }else {
                        mp = MediaPlayer.create(context, R.raw.sonido);mp.start();
                        Snackbar.make(LimgGalery,getResources()
                                .getString(R.string.si_img),Snackbar.LENGTH_LONG).show();
                    }
                    break;

                case R.id.img_conf:
                    returnGalery();
                    break;

                case R.id.imgBase:
                    returnGalery();
                    break;
            }
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_galery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.opcionok) {
            if (si_img){
                // crea una archivo temporal de la img selecionada y la pasa a la vista principal
               /* if (sctiker)ArchivoTemp("sticker.jpg");
                else ArchivoTemp("imagen.jpg");*/
                if (sctiker){
                    bitmapSticker = newbitMap;
                }else bitmapM = newbitMap;

                Intent inten = new Intent();
                setResult(RESULT_OK, inten);
                finish();

            }else {
                mp = MediaPlayer.create(context, R.raw.sonido);
                mp.start();
                Snackbar.make(LimgGalery,getResources()
                        .getString(R.string.si_img),Snackbar.LENGTH_LONG).show();
            }
        }
        if (id == android.R.id.home){
            finish();
        }
            return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case SELECT_PICTURE:
                // recibe la foto de la galeria
                if (resultCode== RESULT_OK){
                    imgBase.setVisibility(View.GONE);
                    path =data.getData();
                    try {
                        bitmap = getCorrectlyOrientedImage(context,path);
                        si_img=true;
                        int bwidth=bitmap.getWidth();
                        int bheight=bitmap.getHeight();
                        int swidth=imgConf.getWidth();
                        new_width=swidth;

                        new_height = (int) Math.floor((double) bheight
                                *( (double) new_width / (double) bwidth));

                        newbitMap = Bitmap.createScaledBitmap(bitmap,new_width,new_height, true);
                        imgConf.setImageDrawable(new BitmapDrawable(getResources(),newbitMap));

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(context,  getResources()
                                .getString(R.string.nocreada) , Toast.LENGTH_SHORT).show();
                    }
                }
            break;

            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                // recibe la foto despues de recortarla
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    InputStream is = null;
                    BitmapFactory.Options dbo = new BitmapFactory.Options();
                    dbo.inJustDecodeBounds = true;
                    BitmapFactory.decodeStream(is, null, dbo);

                    try {
                        is = context.getContentResolver().openInputStream(resultUri);
                        newbitMap = BitmapFactory.decodeStream(is);
                        try {
                            is.close();
                        } catch (IOException e) {e.printStackTrace();}

                        imgConf.setImageDrawable(new BitmapDrawable(getResources(),newbitMap));
                    } catch (FileNotFoundException e) {e.printStackTrace();}
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
                break;
        }
    }

   /* private void ArchivoTemp(String nombre){
        try {
            pasarImgCut.setDrawingCacheEnabled(true);
            Bitmap comc = Bitmap.createBitmap(pasarImgCut.getDrawingCache());
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            comc.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

          //  File file = File.createTempFile("imagen", null, this.getCacheDir());
            File file = new File(this.getCacheDir(), nombre);

            FileOutputStream fo = new FileOutputStream(file);
            fo.write(bytes.toByteArray());
        } catch (IOException e) {
            Toast.makeText(context,  getResources()
                    .getString(R.string.nocreada) , Toast.LENGTH_SHORT).show();
        }
    }*/

    private void returnGalery(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions();
            }else irGalery();
        }else irGalery();
    }

    private void irGalery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,getResources()
                .getString(R.string.seleccionar_galeria)), SELECT_PICTURE);
    }

    public static int getOrientation(Context context, Uri photoUri) {
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[] { MediaStore.Images.ImageColumns.ORIENTATION }, null,
                null, null);
        try {
            if (cursor.moveToFirst()) {return cursor.getInt(0);
            } else {return -1;}
        } finally {cursor.close();}
    }
    public Bitmap getCorrectlyOrientedImage(Context context, Uri photoUri) throws IOException {
        InputStream is = context.getContentResolver().openInputStream(photoUri);
        BitmapFactory.Options dbo = new BitmapFactory.Options();
        dbo.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, dbo);
        is.close();

        int rotatedWidth, rotatedHeight;
        int orientation = getOrientation(context, photoUri);
        if (orientation == 90 || orientation == 270) {
            rotatedWidth = dbo.outHeight;
            rotatedHeight = dbo.outWidth;
        } else {
            rotatedWidth = dbo.outWidth;
            rotatedHeight = dbo.outHeight;
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int MAX_IMAGE_WIDTH = dm.widthPixels;
        int MAX_IMAGE_HEIGHT = dm.heightPixels;
        Bitmap srcBitmap;
        is = context.getContentResolver().openInputStream(photoUri);

        if (rotatedWidth > MAX_IMAGE_WIDTH || rotatedHeight > MAX_IMAGE_HEIGHT) {
            float widthRatio = ((float) rotatedWidth) / ((float) MAX_IMAGE_WIDTH);
            float heightRatio = ((float) rotatedHeight) / ((float) MAX_IMAGE_HEIGHT);
            float maxRatio = Math.max(widthRatio, heightRatio);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = (int) maxRatio;
            srcBitmap = BitmapFactory.decodeStream(is, null, options);
        } else {
            srcBitmap = BitmapFactory.decodeStream(is);
        }
        is.close();

        if (orientation > 0) {
            Matrix matrix = new Matrix();matrix.postRotate(orientation);
            srcBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(),
                    srcBitmap.getHeight(), matrix, true);
        }
        return srcBitmap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_READ_EXT_STORAGE) {
            if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED))
                showSnackBar();
        }
    }
    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {showSnackBar();
            }else {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_READ_EXT_STORAGE);
            }
        }
    }
    private void showSnackBar() {
        Snackbar snackbar = Snackbar.make(LimgGalery,getResources()
                        .getString(R.string.permisoBloqueado),
                Snackbar.LENGTH_LONG);
        snackbar.setAction(getResources().getString(R.string.ajustes), v -> {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
            intent.setData(uri);context.startActivity(intent);
        });
        snackbar.show();
    }
}