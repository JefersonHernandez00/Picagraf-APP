package com.app_hg.oncreatee;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.emoji.bundled.BundledEmojiCompatConfig;
import androidx.emoji.text.EmojiCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app_hg.oncreatee.librerys.filters.FilterViewAdapter;
import com.app_hg.oncreatee.librerys.BlurBuilder;
import com.app_hg.oncreatee.librerys.PickerColor;
import com.app_hg.oncreatee.librerys.stikert.StickerImageView;

import com.app_hg.oncreatee.librerys.stikert.StickerView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;


public class Edit_main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        EmojiBSFragment.EmojiListener, PropertiesBSFragment.Properties{


  /*  private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private float oldDist = 1f;
    private float currentScale;
    private float initialTouchRawX;
    private float initialTouchRawY;
    private int oldX,newX,oldY,newY;
    private float x_0 = 0;
    private float y_0 = 0;*/

    private static final int MY_WRITE_EXTERNAL_STORAGE = 1;
    private static final int MY_ACCESS_MEDIA = 2;
    private static final int MY_GPS = 3;
    public static final int MY_READ_EXT_STORAGE = 4;
    public final static int Img_Gallery = 2;
    public final static int ColorFONDO = 4;
    public final static int STICKER_Gallery = 5;
    public final static int ColorBrocha = 6;
    private static final int CAMERA_REQUEST = 52;

    public static PhotoEditor mPhotoEditor;
    private RecyclerView mRvFilters;
    public static PhotoEditorView mPhotoEditorView;
    private FilterViewAdapter mFilterViewAdapter = new FilterViewAdapter();
    private EmojiBSFragment mEmojiBSFragment;
    private PropertiesBSFragment mPropertiesBSFragment;
   // private BrushDrawingView brushDrawingView;
    public static List<StickerImageView> viewtextlist = new ArrayList<>();
    public static int pt_valor = 0;
    public static AssetManager assetsM;
    public static Resources iconCompat;
    NavigationView navigationView;
    private boolean writeavailable = false;
    private FirebaseAnalytics mFirebaseAnalytics;
    private AdView adView;
    private int var;

    Toolbar toolbar;
    public static FrameLayout container;
    RelativeLayout layoutMain,contADS;
    private MediaPlayer mp;
    public Context context = this;
    private Menu mMenu;
    public static Bitmap bitmapM,bitmapSticker;
    public static float WidthGL,HeightGL;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntjetDF();

        /** Configuracion de diseño **/
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        iconCompat = getResources();
        assetsM = getAssets();
        //oculta la opcion difuminado si no hay una imagen de galeria
        navigationView.getMenu().findItem(R.id.itemfdifuminado).setVisible(false);
        navigationView.getMenu().findItem(R.id.itemfiltros).setVisible(false);

        mPhotoEditor = new PhotoEditor.Builder(this, mPhotoEditorView)
                .setPinchTextScalable(false)
                .build();
        Bitmap fondo = BitmapFactory.decodeResource(context.getResources(),R.drawable.side_nav_bar);
        mPhotoEditorView.getSource().setImageDrawable(new BitmapDrawable(getResources(),fondo));
        container.setBackgroundColor(Color.BLACK);



        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT){
            //inicializa emojis paa android 4.4
            EmojiCompat.Config config = new BundledEmojiCompatConfig(context);
            EmojiCompat.init(config);
            
            //identifica tamaño de pantalla para el width de la vista principal
            Display display = getWindowManager().getDefaultDisplay();
            DisplayMetrics outMetrics = new DisplayMetrics();
            display.getMetrics(outMetrics);
            float widthPixels = outMetrics.widthPixels;
            float density = outMetrics.density;
            int vfWidth = (int) (widthPixels / density);

            Log.v("vista",String.valueOf(vfWidth));
            Log.v("vista",String.valueOf(convertDpToPixel(350,context)));

            if (vfWidth <= convertDpToPixel(350,context)){
                container.getLayoutParams().width = convertDpToPixel(300,context);
                container.getLayoutParams().height = convertDpToPixel(300,context);
                container.requestLayout();
            }
        }




        /**  text color gradiante  de las 2 formas posibles de usarlo**/
        /*Tvg.change(textoInd, Color.parseColor("#800CDD"),  Color.parseColor("#3BA3F2"));
        Tvg.change((TextView) findViewById(R.id.textoInd), new int[]{
                Color.parseColor("#F97C3C"),
                Color.parseColor("#FDB54E"),
                Color.parseColor("#64B678"),
                Color.parseColor("#478AEA"),
                Color.parseColor("#8446CC"),
        });*/
        /*Tvg.change(textoInd, new int[]{
                Color.parseColor("#008744"),
                Color.parseColor("#0057e7"),
                Color.parseColor("#d62d20"),
                Color.parseColor("#ffa700"),
                Color.parseColor("#FFFFFF"),
        });*/

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        ConteoShowRes();

      /*  // establecer dispositivo de prueba ADS google
        //    honor 10 lite - android 10  sm j5 - android 5.1
        List<String> testDeviceIds = Arrays.asList("1945182B60858573B05550E1254F43AF",
               "CCF94467116A8075DC128603DC372C28");
        RequestConfiguration configuration =
                new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build();
        MobileAds.setRequestConfiguration(configuration);*/

        MobileAds.initialize(this, initializationStatus -> {
            adView = new AdView(context);
            adView.setAdUnitId("ca-app-pub-6179577160528312/4569839985");
            contADS.addView(adView);
            loadBanner();
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(MY_GPS);
            }
        }

        // inicializamos las opciones de editor avanzado
        InicializaFiltros();
        //emojis
        mEmojiBSFragment = new EmojiBSFragment();
        mEmojiBSFragment.setEmojiListener(this);
        //dibujar
       // brushDrawingView = new BrushDrawingView(context);
        mPropertiesBSFragment = new PropertiesBSFragment();
        mPropertiesBSFragment.setPropertiesChangeListener(this);

        StickerView.typetxto = false;
        StickerImageView iv_sticker = new StickerImageView(Edit_main.this);
        iv_sticker.setImageResource(R.mipmap.ic_launcher_round);
        container.addView(iv_sticker);
    }

    private void InicializaFiltros(){
        LinearLayoutManager llmFilters = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRvFilters.setLayoutManager(llmFilters);
        mRvFilters.setAdapter(mFilterViewAdapter);
    }

    private void loadBanner() {
        AdRequest adRequest = new AdRequest.Builder().build();
        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);
        adView.loadAd(adRequest);
    }

    private AdSize getAdSize() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float hightpixels = outMetrics.heightPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        int adheight = (int) (hightpixels / density);
        return new AdSize(adWidth, adheight / 5);
    }

    private void ConteoShowRes() {
        //importante cambiar el nombre deest share para cada app
        var = getSharedPreferences("Sharepicagraf", MODE_PRIVATE).getInt("Var", 0);
        if (var <= 50) {
            var++;
            getSharedPreferences("Sharepicagraf", MODE_PRIVATE).edit().putInt("Var", var).apply();
            // Toast.makeText(context, String.valueOf(var), Toast.LENGTH_SHORT).show();
            if (var == 25) CalificarApp();
            if (var == 50) CalificarApp();
        }
    }

    private void CalificarApp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //debe ser para API > 21
            ReviewManager manager = ReviewManagerFactory.create(context);
            Task<ReviewInfo> request = manager.requestReviewFlow();
            request.addOnCompleteListener(task -> {
                try {
                    if (task.isSuccessful()) {
                        // We can get the ReviewInfo object
                        ReviewInfo reviewInfo = task.getResult();
                        Task<Void> flow = manager.launchReviewFlow(this, reviewInfo);
                        flow.addOnCompleteListener(task2 -> {
                        });
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    /** Identificadores **/
    private void IntjetDF(){
        layoutMain = findViewById(R.id.layoutMain);
        contADS = findViewById(R.id.contADS);
        toolbar = findViewById(R.id.toolbar);
        container = findViewById(R.id.containerX);
        mRvFilters = findViewById(R.id.rvFilterView);
        mPhotoEditorView = findViewById(R.id.photoEditorView);

    }

    //eventos para mover elementos
    /*   private  View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
          //  switch (event.getAction() & MotionEvent.ACTION_MASK) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    oldX = (int) event.getRawX(); //get touch position
                    oldY = (int) event.getRawY(); //get touch position
                    mode = DRAG;
                    x_0 = textoInd.getX();
                    y_0 = textoInd.getY();
                    initialTouchRawX = event.getRawX();
                    initialTouchRawY = event.getRawY();
                    currentScale = textoInd.getScaleX();
                    break;

                case MotionEvent.ACTION_POINTER_DOWN:
                    oldDist = spacing(event);
                    if (oldDist > 10f) mode = ZOOM;
                    break;

                case MotionEvent.ACTION_UP:
                    newX = (int) event.getRawX(); //get last touch position
                    newY = (int) event.getRawY(); //get last touch position
                    if  (!(Math.abs( newX-oldX )> 0 || Math.abs( newY-oldY )> 0)){
                        //editar texto
                        ViewItemM(true,0);
                        writeavailable = true;
                        textoInd.setVisibility(View.GONE);
                        editmain.setVisibility(View.VISIBLE);
                        editmain.setText(textoInd.getText());
                        editmain.setBackgroundColor(getResources().getColor(R.color.BLANCOT));
                        editmain.requestFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(editmain, InputMethodManager.SHOW_IMPLICIT);
                        editmain.setSelection(editmain.getText().length());
                    }

                    Rect scrollBounds2 = new Rect();
                    container.getHitRect(scrollBounds2);
                    if (!textoInd.getLocalVisibleRect(scrollBounds2))
                        view.animate().x(x_0).y(y_0).setDuration(100).start();
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                    mode = NONE;
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (mode == DRAG) {
                        Rect scrollBounds = new Rect();
                        container.getHitRect(scrollBounds);
                        if (textoInd.getLocalVisibleRect(scrollBounds)) {
                            float mShiftX = -(initialTouchRawX - x_0);
                            float mShiftY = -(initialTouchRawY - y_0);
                            textoInd.setX(event.getRawX() + mShiftX);
                            textoInd.setY(event.getRawY() + mShiftY);
                        } else view.animate().x(x_0).y(y_0).setDuration(100).start();

                    } else if (mode == ZOOM) {
                        float newDist2 = spacing(event);
                        if (newDist2 > 10f) {
                            float newScale = newDist2 / oldDist * currentScale;
                            textoInd.setScaleX(newScale);
                            textoInd.setScaleY(newScale);
                        }
                    }
                    break;
            }

            return true;
        }
    };*/
    /*private float spacing(MotionEvent event) {
        double x = event.getX(0) - event.getX(1);
        double y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ColorFONDO) {
            if (resultCode == RESULT_OK) {
               // mPhotoEditorView.getSource().setBackgroundColor(data.getIntExtra("color", 0));
                container.setBackgroundColor(data.getIntExtra("color", 0));
            }
        }
        if (requestCode == Img_Gallery) {
            if (resultCode == RESULT_OK) {
                /*File file = new File(this.getCacheDir(), "imagen.jpg");
                InputStream is = null;
                BitmapFactory.Options dbo = new BitmapFactory.Options();
                dbo.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(is, null, dbo);

                try {
                    is = context.getContentResolver().openInputStream(Uri.fromFile(file));
                    Bitmap srcBitmap = BitmapFactory.decodeStream(is);
                    try {
                        assert is != null;
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/

                Log.v("img original",String.valueOf(bitmapM.getWidth()));

                navigationView.getMenu().findItem(R.id.itemfdifuminado).setVisible(true);
                navigationView.getMenu().findItem(R.id.itemfiltros).setVisible(true);
                mPhotoEditorView.getSource().setImageDrawable(new BitmapDrawable(getResources(),bitmapM));

                WidthGL = getImageBounds(mPhotoEditorView.getSource()).width();
                HeightGL = getImageBounds(mPhotoEditorView.getSource()).height();

                Log.v("img width",String.valueOf( getImageBounds(mPhotoEditorView.getSource()).width()));
                Log.v("img height",String.valueOf( getImageBounds(mPhotoEditorView.getSource()).height()));


                /*   Bitmap blurredBitmap = BlurBuilder.blur(Edit_main.this, bitmapM);
                    container.setBackgroundDrawable(new BitmapDrawable(getResources(), blurredBitmap));*/
              /*  } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(context,  getResources()
                            .getString(R.string.nocreada) , Toast.LENGTH_SHORT).show();
                }*/
            }
        }
        if (requestCode == STICKER_Gallery) {
            if (resultCode == RESULT_OK) {
              /*  File file = new File(this.getCacheDir(), "sticker.jpg");
                InputStream is = null;
                BitmapFactory.Options dbo = new BitmapFactory.Options();
                dbo.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(is, null, dbo);
                try {
                    is = context.getContentResolver().openInputStream(Uri.fromFile(file));
                    Bitmap srcBitmap = BitmapFactory.decodeStream(is);
                    try {
                        assert is != null;
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    sticker(srcBitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/
                sticker(bitmapSticker);
            }
        }
        if (requestCode == CAMERA_REQUEST){
            if (resultCode == RESULT_OK) {

                bitmapM = (Bitmap) data.getExtras().get("data");
                Log.v("img original",String.valueOf(bitmapM.getWidth()));
                // mPhotoEditor.clearAllViews();

                navigationView.getMenu().findItem(R.id.itemfdifuminado).setVisible(true);
                navigationView.getMenu().findItem(R.id.itemfiltros).setVisible(true);
                mPhotoEditorView.getSource().setImageDrawable(new BitmapDrawable(getResources(),bitmapM));

                 Handler handler = new Handler();
                        handler.postDelayed(() -> {
                            WidthGL = getImageBounds(mPhotoEditorView.getSource()).width();
                            HeightGL = getImageBounds(mPhotoEditorView.getSource()).height();

                            Log.v("img width",String.valueOf( WidthGL));
                            Log.v("img height",String.valueOf( HeightGL));
                        }, 500); //  segundos de "delay"
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
          /*  final AlertDialog.Builder builder =
                    new AlertDialog.Builder(context)
                            .setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_action_warning, null))
                            .setTitle(getResources().getString(R.string.deseosalir))
                            .setMessage(getResources().getString(R.string.infSalir))
                            .setPositiveButton(getResources().getString(R.string.Salir), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).setNegativeButton(getResources().getString(R.string.cancelar), null);
            AlertDialog dialog = builder.create();
            dialog.show();*/

            Snackbar snackbar = Snackbar.make(layoutMain, getResources().getString(R.string.infSalir),
                    Snackbar.LENGTH_LONG);
            snackbar.setAction(getResources().getString(R.string.Salir), v -> {
                finish();
            });
            snackbar.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        mMenu = menu;
        ViewItemM(false,0);
        ViewItemM(false,1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.opcionVer_ocultar){
             vercultar();
        }
        if (id == R.id.undo){
            mPhotoEditor.undo();
        }
        if (id == R.id.redo){
            mPhotoEditor.redo();
        }
        if (id == R.id.eraser){
           mPhotoEditor.brushEraser();
        }
        if (id == R.id.pencil){
            mPropertiesBSFragment.show(getSupportFragmentManager(), mPropertiesBSFragment.getTag());
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itemcfondo) {
            Intent inten = new Intent(Edit_main.this, PickerColor.class);
            startActivityForResult(inten, ColorFONDO);

        }
        if (id == R.id.itemimgfondo) {
            Intent intent = new Intent(context, Image_galeria.class);
            startActivityForResult(intent, Img_Gallery);
            Image_galeria.sctiker = false;
        }
        if (id == R.id.itemcamara) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
        if (id == R.id.itemfiltros) {
            ViewItemM(true,0);
            mRvFilters.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.left_in);
            mRvFilters.startAnimation(animation);
        }
        if (id == R.id.itemfdifuminado) {
           /* File file = new File(this.getCacheDir(), "imagen.jpg");
            InputStream is = null;
            BitmapFactory.Options dbo = new BitmapFactory.Options();
            dbo.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, null, dbo);

            try {
                is = context.getContentResolver().openInputStream(Uri.fromFile(file));
                Bitmap srcBitmap = BitmapFactory.decodeStream(is);
                try {
                    assert is != null;
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bitmap blurredBitmap = BlurBuilder.blur(Edit_main.this, srcBitmap);
                mPhotoEditorView.getSource().setBackgroundDrawable(new BitmapDrawable(getResources(), blurredBitmap));
                container.setBackgroundDrawable(new BitmapDrawable(getResources(), blurredBitmap));


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(context,  getResources()
                        .getString(R.string.nocreada) , Toast.LENGTH_SHORT).show();
            }*/

            if (bitmapM != null){
                Bitmap blurredBitmap = BlurBuilder.blur(Edit_main.this, bitmapM);
              //  mPhotoEditorView.getSource().setBackgroundDrawable(new BitmapDrawable(getResources(), blurredBitmap));
                container.setBackgroundDrawable(new BitmapDrawable(getResources(), blurredBitmap));
            }
        }
        if (id == R.id.itemdibujar) {
            if (mPhotoEditor.getBrushDrawableMode()){
                mPhotoEditor.setBrushDrawingMode(false);
                ViewItemM(false,1);
            }else {
                mPhotoEditor.setBrushDrawingMode(true);
                ViewItemM(true,1);
            }
        }
        if (id == R.id.itemntexto) {
            TextEditorDialogFragment textEditorDialogFragment = TextEditorDialogFragment.show(this);
            textEditorDialogFragment.setOnTextEditorListener((inputText, colorCode,typeface) -> {
                TextoM(inputText);
                viewtextlist.get(pt_valor-1).getTextviewM().setTextColor(colorCode);
                viewtextlist.get(pt_valor-1).getTextviewM().setTypeface(typeface);
            });
        }
        if (id == R.id.itemsticker) {
            Intent intent = new Intent(context, Image_galeria.class);
            startActivityForResult(intent, STICKER_Gallery);
            Image_galeria.sctiker = true;
        }
        if (id == R.id.itemEmoji) {
            mEmojiBSFragment.show(getSupportFragmentManager(), mEmojiBSFragment.getTag());
        }
        if (id == R.id.inviteA) {
            if (ComprobarConex()) {
                String deepLink = "http://play.google.com/store/apps/details?id=com.app_hg.oncreatee";
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, getResources()
                        .getString(R.string.sharetext) + "\n\n" + deepLink);
                startActivity(Intent.createChooser(intent, getResources()
                        .getString(R.string.inviteAmigos)));
            }

        }
        if (id == R.id.opcioncompartir) {
            if (ComprobarConex()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(0);
                    } else ArchivoTemp();
                } else ArchivoTemp();
            }

        }
        if (id == R.id.opcionguardar) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {

                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(0);
                }else GuardaGalery();

            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                GuardaGalery();
            }else GuardaGalery();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void vercultar(){
        if (mRvFilters.getVisibility() == View.VISIBLE) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.rigth_out);
            mRvFilters.startAnimation(animation);
            mRvFilters.setVisibility(View.GONE);
            ViewItemM(false,0);
        }
    }

    /** parametros de los item del toolbar **/
    private void ViewItemM(boolean key, int position) {
        switch (position){
            case 0:
                if (mMenu != null) {
                    MenuItem item = mMenu.findItem(R.id.opcionVer_ocultar);
                    if (item != null) {
                        item.setVisible(key);
                    }
                }
                break;

            case 1:
                if (mMenu != null) {
                    MenuItem item = mMenu.findItem(R.id.pencil);
                    if (item != null) {
                        item.setVisible(key);
                    }
                }
                if (mMenu != null) {
                    MenuItem item = mMenu.findItem(R.id.eraser);
                    if (item != null) {
                        item.setVisible(key);
                    }
                }
                if (mMenu != null) {
                    MenuItem item = mMenu.findItem(R.id.undo);
                    if (item != null) {
                        item.setVisible(key);
                    }
                }
                if (mMenu != null) {
                    MenuItem item = mMenu.findItem(R.id.redo);
                    if (item != null) {
                        item.setVisible(key);
                    }
                }
                break;
        }

    }

    /** estabece sticker en imgview **/
    public void sticker(Bitmap bitmap) {
        StickerView.typetxto = false;
        StickerImageView iv_sticker = new StickerImageView(Edit_main.this);
        iv_sticker.setImageBitmap(bitmap);
        container.addView(iv_sticker);
    }

    public void Emoji(String unicode) {
        StickerView.typetxto = false;
        StickerImageView emoji = new StickerImageView(Edit_main.this);
        emoji.addEmoji(unicode);
        container.addView(emoji);
    }

    public void TextoM(String texto) {
        StickerView.typetxto = true;
        StickerView.valor_main_pot = pt_valor;
        StickerImageView textom = new StickerImageView(Edit_main.this);
        textom.addtextoM(texto);
        viewtextlist.add(pt_valor,textom);
        container.addView(textom);
        pt_valor++;
    }

    /** comprobar la conexion del dispositivo **/
    private boolean ComprobarConex() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            Snackbar.make(layoutMain, getResources().getString(R.string.notcont), Snackbar.LENGTH_LONG).show();
        }
        return false;
    }

    /** crear archivo temporal para compartir **/
    private void ArchivoTemp() {
        container.setDrawingCacheEnabled(true);
        Bitmap comc = Bitmap.createBitmap(container.getDrawingCache());
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        comc.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        //se crea un ruta predeterminada en el almacenmiento interno
        File directory = context.getFilesDir();
        File newFile = new File(directory, "imgci.jpg");

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
           /* Toast.makeText(this, "Error! No SDCARD Found!",
                    Toast.LENGTH_LONG).show();*/
        } else {
            // se crear archivo en SD
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            newFile = new File(storageDir, "imgc.jpg");
        }
        try {
            OutputStream os = new FileOutputStream(newFile);
            os.write(bytes.toByteArray());
            os.close();
        } catch (IOException e) {
            Toast.makeText(context, getResources()
                    .getString(R.string.nocreada), Toast.LENGTH_SHORT).show();
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            Uri imgURI = FileProvider.getUriForFile(this,
                    "com.app_hg.oncreatee.fileprovider", newFile);

            Intent Share = new Intent(Intent.ACTION_SEND);
            Share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION |
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Share.setType("image/*");
            Share.putExtra(Intent.EXTRA_STREAM, imgURI);
            startActivity(Intent.createChooser(Share, getResources().getString(R.string.compartir_por)));
        }else {
            Intent Share = new Intent(Intent.ACTION_SEND);
            Share.setType("image/*");
            Share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(newFile));
            startActivity(Intent.createChooser(Share, getResources().getString(R.string.compartir_por)));
        }
        container.setDrawingCacheEnabled(false);
    }

    /** verificar espacio de almacenamiento externo**/
    public static float megabytesAvailableSDCard(Context ctx) {
        //algunos dispositivos tienen 2 volumenes el emulad y el extraibleSD
        File[] externalStorageVolumes = ContextCompat.getExternalFilesDirs(ctx, null);
        Log.i("memoryV",String.valueOf(externalStorageVolumes.length));
        File primaryExternalStorage = externalStorageVolumes[externalStorageVolumes.length-1];
        StatFs stat = new StatFs(primaryExternalStorage.getPath());
        return ((long)stat.getBlockSize() * (long)stat.getAvailableBlocks()) / (1024.f * 1024.f);
    }

    /** verificar espacio de almacenamiento interno**/
    public static float megabytesAvailableInternalStorage(Context ctx) {
        StatFs stat = new StatFs(ctx.getFilesDir().getAbsolutePath());
        return ((long)stat.getBlockSize() * (long)stat.getAvailableBlocks()) / (1024.f * 1024.f);
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    /** guardar imagen en galeria**/
    private void GuardaGalery() {
        vercultar();
      //  Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();

        //para obtener bitmatcache
       /* container.setDrawingCacheEnabled(true);
        container.buildDrawingCache(true);*/

      /*  Bitmap bitmapOutput = Bitmap.createBitmap(container.getDrawingCache());
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmapOutput.compress(Bitmap.CompressFormat.PNG, 100, bytes);*/

        Bitmap bitmap = getBitmapFromView(container);

        File newFile = null;
        Uri myFavoriteSongUri = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            final String relativeLocation = Environment.DIRECTORY_PICTURES+"/PicaGraf/";
            ContentResolver resolver = getApplicationContext().getContentResolver();

            ContentValues contentValues = new ContentValues();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, timeStamp+"_picagraf"+".jpg");
            contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, relativeLocation);

            final Uri contentUri = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
            myFavoriteSongUri = resolver.insert(contentUri, contentValues);

            if (megabytesAvailableInternalStorage(context) >= 400f){
                try {
                   /* OutputStream os = resolver.openOutputStream(myFavoriteSongUri);
                    os.write(bytes.toByteArray());
                    os.close();*/

                    FileOutputStream ostream = (FileOutputStream) resolver.openOutputStream(myFavoriteSongUri);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 10, ostream);
                    ostream.close();

                    Snackbar.make(layoutMain, getResources()
                            .getString(R.string.imgguardada), Snackbar.LENGTH_LONG).show();
                    mp = MediaPlayer.create(Edit_main.this, R.raw.sonido);
                    mp.start();


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, getResources()
                            .getString(R.string.nocreada), Toast.LENGTH_SHORT).show();
                }
            }else Toast.makeText(context,  getResources().getString(R.string.No_espacio) ,
                    Toast.LENGTH_SHORT).show();



        }else {

            // codigo para guardar en SD funcion para nuevas versiones
          /*  File[] externalStorageVolumes = ContextCompat.getExternalFilesDirs(context, null);
            if (!externalStorageVolumes[externalStorageVolumes.length-1].getPath().isEmpty()) {

                Log.i("memorySD",String.valueOf(megabytesAvailableSDCard(context)));
                Log.i("memory",String.valueOf(megabytesAvailableInternalStorage(context)));
                //verifica si hay espacio disponible en SD
                if (megabytesAvailableSDCard(context) >= 400f){

                    Log.i("SD",String.valueOf(System.getenv ("SECONDARY_STORAGE")));
                    File storageDir = new File(System.getenv ("SECONDARY_STORAGE")+"/PicaGraf/");
                    storageDir.mkdirs();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    newFile = new File(storageDir, timeStamp+"_picagraf"+".jpg");
                    if (!newFile.exists()) {
                        newFile.mkdirs();

                    }
                    try {
                        newFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Crearimagen(bitmap,newFile);

                }else Save_memoriainterna(bitmap,newFile);
            }else Save_memoriainterna(bitmap,newFile);*/

            Save_memoriainterna(bitmap,newFile);
        }

      //  container.setDrawingCacheEnabled(false); // para cerrar bitmap cache
    }

    private void Save_memoriainterna(Bitmap bitmapOutput,File newFilex){
        if (megabytesAvailableInternalStorage(context) >= 400f){

            File storageDir = new File(Environment.getExternalStorageDirectory()+"/PicaGraf/");
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            newFilex = new File(storageDir, timeStamp+"_picagraf"+".jpg");
            storageDir.mkdir();
            Crearimagen(bitmapOutput,newFilex);

        }else Toast.makeText(context,  getResources().getString(R.string.No_espacio) ,
                Toast.LENGTH_SHORT).show();
    }

    private void Crearimagen(Bitmap bitmapOutput,File newFile){
        try {
                   /* OutputStream os = new FileOutputStream(newFile);
                    os.write(bytes.toByteArray());
                    os.close();*/
            //bitmapOutput.compress(Bitmap.CompressFormat.PNG, 100,new FileOutputStream(newFile));

          /*  String FILENAME="mytextfile.txt";
            File fileToCheck = new File(getExternalCacheDirectory(), FILENAME);
            if (fileToCheck.exists()) {
                FileOutputStream fos = openFileOutput(&quot;sdcard/mytextfile.txt&quot;, MODE_WORLD_WRITEABLE);
            }*/

            FileOutputStream ostream = new FileOutputStream(newFile);
            bitmapOutput.compress(Bitmap.CompressFormat.PNG, 10, ostream);
            ostream.close();

            // escanea el nuevo arcivo para mostrar en galeria
            MediaScannerConnection.scanFile(getApplicationContext(), new String[]{
                    newFile.getPath()}, new String[]{"image/jpg"}, null);
            Snackbar.make(layoutMain, getResources()
                    .getString(R.string.imgguardada), Snackbar.LENGTH_LONG).show();

            mp = MediaPlayer.create(Edit_main.this, R.raw.sonido);
            mp.start();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, getResources()
                    .getString(R.string.nocreada), Toast.LENGTH_SHORT).show();
        }
    }

    /** permisos del dispositivo **/
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //
            } else {
                showSnackBar();
            }
        } else if (requestCode == MY_GPS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               //
            } else {
                showSnackBar();
            }
        }else if (requestCode == MY_ACCESS_MEDIA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //
            } else {
                showSnackBar();
            }
        }
    }
    private void requestPermissions(int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            // permisos entre api 23 y 28
            switch (position) {
                case 0:
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        showSnackBar();
                    } else {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_WRITE_EXTERNAL_STORAGE);
                    }
                    break;

                case 1:
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        showSnackBar();
                    } else {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                MY_GPS);
                    }
                    break;
                default:

            }
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            //permisos para API >= 29
            switch (position) {
                case 0:
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_MEDIA_LOCATION)) {
                        showSnackBar();
                    } else {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_MEDIA_LOCATION},
                                MY_ACCESS_MEDIA);
                    }
                    break;

                case 1:
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        showSnackBar();
                    } else {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                MY_READ_EXT_STORAGE);
                    }
                    break;

                case 2:
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        showSnackBar();
                    } else {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                MY_GPS);
                    }
                    break;
                default:

            }
        }
    }
    private void showSnackBar() {
        Snackbar snackbar = Snackbar.make(layoutMain, getResources().getString(R.string.permisoBloqueado),
                Snackbar.LENGTH_LONG);
        snackbar.setAction(getResources().getString(R.string.ajustes), v -> {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
            intent.setData(uri);
            context.startActivity(intent);
        });
        snackbar.show();
    }

    @Override
    public void onEmojiClick(String emojiUnicode) {
        try {
            Emoji(emojiUnicode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onColorChanged(int colorCode) {
        mPhotoEditor.setBrushColor(colorCode);
    }
    @Override
    public void onOpacityChanged(int opacity) {
        mPhotoEditor.setOpacity(opacity);
    }
    @Override
    public void onBrushSizeChanged(int brushSize) {
        mPhotoEditor.setBrushSize(brushSize);
    }

    private static int convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int)px;
    }

    public static RectF getImageBounds(ImageView imageView) {
        RectF bounds = new RectF();
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            imageView.getImageMatrix().mapRect(bounds, new RectF(drawable.getBounds()));
        } return bounds;
    }

}


