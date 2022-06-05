package com.app_hg.oncreatee;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class Founts extends Activity {
    String ruta0, ruta3, ruta4, ruta5, ruta6, ruta7, ruta8, ruta9, ruta10, ruta11, ruta12,
            ruta13, ruta14, ruta15, ruta16, ruta18, ruta19, ruta20, ruta21, ruta22, ruta23, ruta24,
            ruta25, ruta26, ruta27, ruta28, ruta29, ruta30, ruta31, ruta32, ruta33, ruta35, ruta36,
            ruta37, ruta40, ruta41, ruta42, ruta43, ruta44, ruta47, ruta48,
            ruta49, ruta50, ruta51, ruta52, ruta53, ruta54, ruta55, ruta56, ruta57, ruta58, ruta59, ruta60,
            ruta61, ruta62, ruta63, ruta64, ruta65, ruta66, ruta67, ruta68, ruta69, ruta70, ruta71, ruta72,
            ruta73, ruta74, ruta75, ruta76, ruta77, ruta78, ruta79, ruta80, ruta81, ruta82, ruta83,
            ruta91, ruta92, ruta93, rutafuente;


    RadioButton fuente0,fuente3, fuente4 ,fuente5,fuente6 , fuente7,fuente8 ,fuente9 , fuente10
            ,fuente11 ,fuente12 ,fuente13 , fuente14 ,fuente15 ,fuente16 ,fuente18 ,fuente19
            , fuente20 ,fuente21 ,fuente22 ,fuente23 , fuente24 ,fuente25 , fuente26
            ,fuente27 , fuente28 ,fuente29 , fuente30 , fuente31 , fuente32
            ,fuente33 , fuente35 , fuente36 , fuente37 , fuente40 , fuente41
            , fuente42, fuente43, fuente44, fuente47, fuente48, fuente49
            , fuente50 , fuente51,fuente52 , fuente53 , fuente54 , fuente55 , fuente56
            , fuente57 ,fuente58 , fuente59 , fuente60 , fuente61 , fuente62 , fuente63
            , fuente64 , fuente65 ,fuente66 ,fuente67 , fuente68 , fuente69 , fuente70
            , fuente71 , fuente72 , fuente73 , fuente74 , fuente75 , fuente76
            ,fuente77 , fuente78 , fuente79 , fuente80 , fuente81 , fuente82
            , fuente83 , fuente91, fuente92 ,fuente93;

    RadioGroup fuentesGroup ;
    Button cancelar ;
    Button send ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuente);

        fuente0 = findViewById(R.id.fuente0);
      fuente3 = findViewById(R.id.fuente3);
         fuente4 = findViewById(R.id.fuente4);
         fuente5 = findViewById(R.id.fuente5);
         fuente6 = findViewById(R.id.fuente6);
         fuente7 = findViewById(R.id.fuente7);
         fuente8 = findViewById(R.id.fuente8);
         fuente9 = findViewById(R.id.fuente9);
       fuente10 = findViewById(R.id.fuente10);
        fuente11 = findViewById(R.id.fuente11);
         fuente12 = findViewById(R.id.fuente12);
         fuente13 = findViewById(R.id.fuente13);
      fuente14 = findViewById(R.id.fuente14);
         fuente15 = findViewById(R.id.fuente15);
         fuente16 = findViewById(R.id.fuente16);
        fuente18 = findViewById(R.id.fuente18);
         fuente19 = findViewById(R.id.fuente19);
       fuente20 = findViewById(R.id.fuente20);
        fuente21 = findViewById(R.id.fuente21);
         fuente22 = findViewById(R.id.fuente22);
         fuente23 = findViewById(R.id.fuente23);
         fuente24 = findViewById(R.id.fuente24);
        fuente25 = findViewById(R.id.fuente25);
        fuente26 = findViewById(R.id.fuente26);
       fuente27 = findViewById(R.id.fuente27);
         fuente28 = findViewById(R.id.fuente28);
         fuente29 = findViewById(R.id.fuente29);
        fuente30 = findViewById(R.id.fuente30);
         fuente31 = findViewById(R.id.fuente31);
         fuente32 = findViewById(R.id.fuente32);
        fuente33 = findViewById(R.id.fuente33);
         fuente35 = findViewById(R.id.fuente35);
         fuente36 = findViewById(R.id.fuente36);
        fuente37 = findViewById(R.id.fuente37);
       fuente40 = findViewById(R.id.fuente40);
         fuente41 = findViewById(R.id.fuente41);
         fuente42 = findViewById(R.id.fuente42);
         fuente43 = findViewById(R.id.fuente43);
         fuente44 = findViewById(R.id.fuente44);
         fuente47 = findViewById(R.id.fuente47);
         fuente48 = findViewById(R.id.fuente48);
         fuente49 = findViewById(R.id.fuente49);
         fuente50 = findViewById(R.id.fuente50);
        fuente51 = findViewById(R.id.fuente51);
        fuente52 = findViewById(R.id.fuente52);
         fuente53 = findViewById(R.id.fuente53);
         fuente54 = findViewById(R.id.fuente54);
         fuente55 = findViewById(R.id.fuente55);
         fuente56 = findViewById(R.id.fuente56);
         fuente57 = findViewById(R.id.fuente57);
         fuente58 = findViewById(R.id.fuente58);
        fuente59 = findViewById(R.id.fuente59);
         fuente60 = findViewById(R.id.fuente60);
         fuente61 = findViewById(R.id.fuente61);
         fuente62 = findViewById(R.id.fuente62);
         fuente63 = findViewById(R.id.fuente63);
         fuente64 = findViewById(R.id.fuente64);
        fuente65 = findViewById(R.id.fuente65);
         fuente66 = findViewById(R.id.fuente66);
         fuente67 = findViewById(R.id.fuente67);
         fuente68 = findViewById(R.id.fuente68);
         fuente69 = findViewById(R.id.fuente69);
         fuente70 = findViewById(R.id.fuente70);
         fuente71 = findViewById(R.id.fuente71);
         fuente72 = findViewById(R.id.fuente72);
         fuente73 = findViewById(R.id.fuente73);
         fuente74 = findViewById(R.id.fuente74);
         fuente75 = findViewById(R.id.fuente75);
       fuente76 = findViewById(R.id.fuente76);
        fuente77 = findViewById(R.id.fuente77);
         fuente78 = findViewById(R.id.fuente78);
        fuente79 = findViewById(R.id.fuente79);
      fuente80 = findViewById(R.id.fuente80);
        fuente81 = findViewById(R.id.fuente81);
       fuente82 = findViewById(R.id.fuente82);
         fuente83 = findViewById(R.id.fuente83);
       fuente91 = findViewById(R.id.fuente91);
         fuente92 = findViewById(R.id.fuente92);
         fuente93 = findViewById(R.id.fuente93);
        fuentesGroup = findViewById(R.id.fuentes_group);
       cancelar = findViewById(R.id.cancelar);
       send = findViewById(R.id.send_button);

        if (android.os.Build.VERSION.SDK_INT != Build.VERSION_CODES.O)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        EstablecerFuente();
        OnclikF();
    }


    private void EstablecerFuente(){
        ruta0 = "font/AmorEatPersia.ttf";
        Typeface TF0 = Typeface.createFromAsset(getAssets(), ruta0);
        fuente0.setTypeface(TF0);

        ruta3 = "font/A Charming Font Expanded.ttf";
        Typeface TF3 = Typeface.createFromAsset(getAssets(), ruta3);
        fuente3.setTypeface(TF3);

        ruta4 = "font/A Yummy Apology.ttf";
        Typeface TF4 = Typeface.createFromAsset(getAssets(), ruta4);
        fuente4.setTypeface(TF4);

        ruta5 = "font/serif.ttf";
        Typeface TF5 = Typeface.createFromAsset(getAssets(), ruta5);
        fuente5.setTypeface(TF5);

        ruta6 = "font/Acadian.ttf";
        Typeface TF6 = Typeface.createFromAsset(getAssets(), ruta6);
        fuente6.setTypeface(TF6);

        ruta7 = "font/acmesa.TTF";
        Typeface TF7 = Typeface.createFromAsset(getAssets(), ruta7);
        fuente7.setTypeface(TF7);

        ruta8 = "font/Walt Disney Script.ttf";
        Typeface TF8 = Typeface.createFromAsset(getAssets(), ruta8);
        fuente8.setTypeface(TF8);

        ruta9 = "font/actionj.ttf";
        Typeface TF9 = Typeface.createFromAsset(getAssets(), ruta9);
        fuente9.setTypeface(TF9);

        ruta10 = "font/Advert-Italic.ttf";
        Typeface TF10 = Typeface.createFromAsset(getAssets(), ruta10);
        fuente10.setTypeface(TF10);

        ruta11 = "font/Albemarle Demo.ttf";
        Typeface TF11 = Typeface.createFromAsset(getAssets(), ruta11);
        fuente11.setTypeface(TF11);

        ruta12 = "font/Allegro.ttf";
        Typeface TF12 = Typeface.createFromAsset(getAssets(), ruta12);
        fuente12.setTypeface(TF12);

        ruta13 = "font/Almonte Snow.ttf";
        Typeface TF13 = Typeface.createFromAsset(getAssets(), ruta13);
        fuente13.setTypeface(TF13);

        ruta14 = "font/AlphaMack AOE.ttf";
        Typeface TF14 = Typeface.createFromAsset(getAssets(), ruta14);
        fuente14.setTypeface(TF14);

        ruta15 = "font/AlphaRev.ttf";
        Typeface TF15 = Typeface.createFromAsset(getAssets(), ruta15);
        fuente15.setTypeface(TF15);

        ruta16 = "font/Alpine Regular.ttf";
        Typeface TF16 = Typeface.createFromAsset(getAssets(), ruta16);
        fuente16.setTypeface(TF16);

        ruta18 = "font/Alte Caps.ttf";
        Typeface TF18 = Typeface.createFromAsset(getAssets(), ruta18);
        fuente18.setTypeface(TF18);

        ruta19 = "font/Amethyst Zucchini.ttf";
        Typeface TF19 = Typeface.createFromAsset(getAssets(), ruta19);
        fuente19.setTypeface(TF19);

        ruta20 = "font/Aarco-.ttf";
        Typeface TF20 = Typeface.createFromAsset(getAssets(), ruta20);
        fuente20.setTypeface(TF20);

        ruta21 = "font/angelina.TTF";
        Typeface TF21 = Typeface.createFromAsset(getAssets(), ruta21);
        fuente21.setTypeface(TF21);

        ruta22 = "font/AngloSaxonItalic.ttf";
        Typeface TF22 = Typeface.createFromAsset(getAssets(), ruta22);
        fuente22.setTypeface(TF22);

        ruta23 = "font/AnthroPosoph-Bold.ttf";
        Typeface TF23 = Typeface.createFromAsset(getAssets(), ruta23);
        fuente23.setTypeface(TF23);

        ruta24 = "font/Ariendesse.ttf";
        Typeface TF24 = Typeface.createFromAsset(getAssets(), ruta24);
        fuente24.setTypeface(TF24);

        ruta25 = "font/Ariston Wd.ttf";
        Typeface TF25 = Typeface.createFromAsset(getAssets(), ruta25);
        fuente25.setTypeface(TF25);

        ruta26 = "font/Artesania Display.ttf";
        Typeface TF26 = Typeface.createFromAsset(getAssets(), ruta26);
        fuente26.setTypeface(TF26);

        ruta27 = "font/ArtificeSSK.ttf";
        Typeface TF27 = Typeface.createFromAsset(getAssets(), ruta27);
        fuente27.setTypeface(TF27);

        ruta28 = "font/Asenine Super Thin.ttf";
        Typeface TF28 = Typeface.createFromAsset(getAssets(), ruta28);
        fuente28.setTypeface(TF28);

        ruta29 = "font/Ashby Black Italic.ttf";
        Typeface TF29 = Typeface.createFromAsset(getAssets(), ruta29);
        fuente29.setTypeface(TF29);

        ruta30 = "font/Asimov.ttf";
        Typeface TF30 = Typeface.createFromAsset(getAssets(), ruta30);
        fuente30.setTypeface(TF30);

        ruta31 = "font/Asphodel.ttf";
        Typeface TF31 = Typeface.createFromAsset(getAssets(), ruta31);
        fuente31.setTypeface(TF31);

        ruta32 = "font/Ataxia Outline BRK.ttf";
        Typeface TF32 = Typeface.createFromAsset(getAssets(), ruta32);
        fuente32.setTypeface(TF32);

        ruta33 = "font/Augusta Shadow.ttf";
        Typeface TF33 = Typeface.createFromAsset(getAssets(), ruta33);
        fuente33.setTypeface(TF33);

        ruta35 = "font/Axaxax.ttf";
        Typeface TF35 = Typeface.createFromAsset(getAssets(), ruta35);
        fuente35.setTypeface(TF35);

        ruta36 = "font/Back to the Futurex.ttf";
        Typeface TF36 = Typeface.createFromAsset(getAssets(), ruta36);
        fuente36.setTypeface(TF36);

        ruta37 = "font/Backlash BRK.ttf";
        Typeface TF37 = Typeface.createFromAsset(getAssets(), ruta37);
        fuente37.setTypeface(TF37);

        ruta40 = "font/Bajoran Regular.ttf";
        Typeface TF40 = Typeface.createFromAsset(getAssets(), ruta40);
        fuente40.setTypeface(TF40);

        ruta41 = "font/Baldur Regular.ttf";
        Typeface TF41 = Typeface.createFromAsset(getAssets(), ruta41);
        fuente41.setTypeface(TF41);

        ruta42 = "font/Ballade Contour.ttf";
        Typeface TF42 = Typeface.createFromAsset(getAssets(), ruta42);
        fuente42.setTypeface(TF42);

        ruta43 = "font/Baloney.ttf";
        Typeface TF43 = Typeface.createFromAsset(getAssets(), ruta43);
        fuente43.setTypeface(TF43);

        ruta44 = "font/Baltar.ttf";
        Typeface TF44 = Typeface.createFromAsset(getAssets(), ruta44);
        fuente44.setTypeface(TF44);

        ruta47 = "font/Baraquiel.ttf";
        Typeface TF47 = Typeface.createFromAsset(getAssets(), ruta47);
        fuente47.setTypeface(TF47);

        ruta48 = "font/Barbatrick.ttf";
        Typeface TF48 = Typeface.createFromAsset(getAssets(), ruta48);
        fuente48.setTypeface(TF48);

        ruta49 = "font/Basca.ttf";
        Typeface TF49 = Typeface.createFromAsset(getAssets(), ruta49);
        fuente49.setTypeface(TF49);

        ruta50 = "font/BatFont.ttf";
        Typeface TF50 = Typeface.createFromAsset(getAssets(), ruta50);
        fuente50.setTypeface(TF50);

        ruta51 = "font/Baubau.ttf";
        Typeface TF51 = Typeface.createFromAsset(getAssets(), ruta51);
        fuente51.setTypeface(TF51);

        ruta52 = "font/Bauer.ttf";
        Typeface TF52 = Typeface.createFromAsset(getAssets(), ruta52);
        fuente52.setTypeface(TF52);

        ruta53 = "font/MiasScribblings~.ttf";
        Typeface TF53 = Typeface.createFromAsset(getAssets(), ruta53);
        fuente53.setTypeface(TF53);

        ruta54 = "font/Baveuse.ttf";
        Typeface TF54 = Typeface.createFromAsset(getAssets(), ruta54);
        fuente54.setTypeface(TF54);

        ruta55 = "font/christmas_cookies.ttf";
        Typeface TF55 = Typeface.createFromAsset(getAssets(), ruta55);
        fuente55.setTypeface(TF55);

        ruta56 = "font/ciudad capital.ttf";
        Typeface TF56 = Typeface.createFromAsset(getAssets(), ruta56);
        fuente56.setTypeface(TF56);

        ruta57 = "font/crewniverse_font.otf";
        Typeface TF57 = Typeface.createFromAsset(getAssets(), ruta57);
        fuente57.setTypeface(TF57);

        ruta58 = "font/Screaming Red.ttf";
        Typeface TF58 = Typeface.createFromAsset(getAssets(), ruta58);
        fuente58.setTypeface(TF58);

        ruta59 = "font/docallismeonstreet.otf";
        Typeface TF59 = Typeface.createFromAsset(getAssets(), ruta59);
        fuente59.setTypeface(TF59);

        ruta60 = "font/galacticstorm.ttf";
        Typeface TF60 = Typeface.createFromAsset(getAssets(), ruta60);
        fuente60.setTypeface(TF60);

        ruta61 = "font/galacticstorm3d.ttf";
        Typeface TF61 = Typeface.createFromAsset(getAssets(), ruta61);
        fuente61.setTypeface(TF61);

        ruta62 = "font/galacticstormlaser.ttf";
        Typeface TF62 = Typeface.createFromAsset(getAssets(), ruta62);
        fuente62.setTypeface(TF62);

        ruta63 = "font/Game Robot.ttf";
        Typeface TF63 = Typeface.createFromAsset(getAssets(), ruta63);
        fuente63.setTypeface(TF63);

        ruta64 = "font/HabboFont.ttf";
        Typeface TF64 = Typeface.createFromAsset(getAssets(), ruta64);
        fuente64.setTypeface(TF64);

        ruta65 = "font/HappyFox-Condensed.otf";
        Typeface TF65 = Typeface.createFromAsset(getAssets(), ruta65);
        fuente65.setTypeface(TF65);

        ruta66 = "font/JumboRings.ttf";
        Typeface TF66 = Typeface.createFromAsset(getAssets(), ruta66);
        fuente66.setTypeface(TF66);

        ruta67 = "font/King Will Be King.ttf";
        Typeface TF67 = Typeface.createFromAsset(getAssets(), ruta67);
        fuente67.setTypeface(TF67);

        ruta68 = "font/Kingdom of Heart.ttf";
        Typeface TF68 = Typeface.createFromAsset(getAssets(), ruta68);
        fuente68.setTypeface(TF68);

        ruta69 = "font/Minecraft.ttf";
        Typeface TF69 = Typeface.createFromAsset(getAssets(), ruta69);
        fuente69.setTypeface(TF69);

        ruta70 = "font/missionpossible.ttf";
        Typeface TF70 = Typeface.createFromAsset(getAssets(), ruta70);
        fuente70.setTypeface(TF70);

        ruta71 = "font/Mostwasted.ttf";
        Typeface TF71 = Typeface.createFromAsset(getAssets(), ruta71);
        fuente71.setTypeface(TF71);

        ruta72 = "font/Mucho_Power.otf";
        Typeface TF72 = Typeface.createFromAsset(getAssets(), ruta72);
        fuente72.setTypeface(TF72);

        ruta73 = "font/SecondAvenue.ttf";
        Typeface TF73 = Typeface.createFromAsset(getAssets(), ruta73);
        fuente73.setTypeface(TF73);

        ruta74 = "font/one percent font.ttf";
        Typeface TF74 = Typeface.createFromAsset(getAssets(), ruta74);
        fuente74.setTypeface(TF74);

        ruta75 = "font/Paris in Love.ttf";
        Typeface TF75 = Typeface.createFromAsset(getAssets(), ruta75);
        fuente75.setTypeface(TF75);

        ruta76 = "font/Parrot Leaf.ttf";
        Typeface TF76 = Typeface.createFromAsset(getAssets(), ruta76);
        fuente76.setTypeface(TF76);

        ruta77 = "font/Peinture Fraiche.ttf";
        Typeface TF77 = Typeface.createFromAsset(getAssets(), ruta77);
        fuente77.setTypeface(TF77);

        ruta78 = "font/Pixeltype.ttf";
        Typeface TF78 = Typeface.createFromAsset(getAssets(), ruta78);
        fuente78.setTypeface(TF78);

        ruta79 = "font/RaspoutineClassic_TB.otf";
        Typeface TF79 = Typeface.createFromAsset(getAssets(), ruta79);
        fuente79.setTypeface(TF79);

        ruta80 = "font/REDPOWER.ttf";
        Typeface TF80 = Typeface.createFromAsset(getAssets(), ruta80);
        fuente80.setTypeface(TF80);

        ruta81 = "font/SantasSleighFull Bold.ttf";
        Typeface TF81 = Typeface.createFromAsset(getAssets(), ruta81);
        fuente81.setTypeface(TF81);

        ruta82 = "font/SantasSleighFull Deluxe.ttf";
        Typeface TF82 = Typeface.createFromAsset(getAssets(), ruta82);
        fuente82.setTypeface(TF82);

        ruta83 = "font/Schreibii.ttf";
        Typeface TF83 = Typeface.createFromAsset(getAssets(), ruta83);
        fuente83.setTypeface(TF83);

        ruta91 = "font/TAKE ON ME.ttf";
        Typeface TF91 = Typeface.createFromAsset(getAssets(), ruta91);
        fuente91.setTypeface(TF91);

        ruta92 = "font/Thumbs UP.ttf";
        Typeface TF92 = Typeface.createFromAsset(getAssets(), ruta92);
        fuente92.setTypeface(TF92);

        ruta93 = "font/TRIBAL__.ttf";
        Typeface TF93 = Typeface.createFromAsset(getAssets(), ruta93);
        fuente93.setTypeface(TF93);
    }

    private void OnclikF(){
        cancelar.setOnClickListener(view -> {
            finish();
        });

        send.setOnClickListener(view -> {
            RadioButton currentRadio = findViewById(fuentesGroup.getCheckedRadioButtonId());
            if (currentRadio == fuente0) {rutafuente = ruta0;}
            if (currentRadio == fuente3) {rutafuente = ruta3;}
            if (currentRadio == fuente4) {rutafuente = ruta4;}
            if (currentRadio == fuente5) {rutafuente = ruta5;}
            if (currentRadio == fuente6) {rutafuente = ruta6;}
            if (currentRadio == fuente7) {rutafuente = ruta7;}
            if (currentRadio == fuente8) {rutafuente = ruta8;}
            if (currentRadio == fuente9) {rutafuente = ruta9;}
            if (currentRadio == fuente10) {rutafuente = ruta10;}
            if (currentRadio == fuente11) {rutafuente = ruta11;}
            if (currentRadio == fuente12) {rutafuente = ruta12;}
            if (currentRadio == fuente13) {rutafuente = ruta13;}
            if (currentRadio == fuente14) {rutafuente = ruta14;}
            if (currentRadio == fuente15) {rutafuente = ruta15;}
            if (currentRadio == fuente16) {rutafuente = ruta16;}
            if (currentRadio == fuente18) {rutafuente = ruta18;}
            if (currentRadio == fuente19) {rutafuente = ruta19;}
            if (currentRadio == fuente20) {rutafuente = ruta20;}
            if (currentRadio == fuente21) {rutafuente = ruta21;}
            if (currentRadio == fuente22) {rutafuente = ruta22;}
            if (currentRadio == fuente23) {rutafuente = ruta23;}
            if (currentRadio == fuente24) {rutafuente = ruta24;}
            if (currentRadio == fuente25) {rutafuente = ruta25;}
            if (currentRadio == fuente26) {rutafuente = ruta26;}
            if (currentRadio == fuente27) {rutafuente = ruta27;}
            if (currentRadio == fuente28) {rutafuente = ruta28;}
            if (currentRadio == fuente29) {rutafuente = ruta29;}
            if (currentRadio == fuente30) {rutafuente = ruta30;}
            if (currentRadio == fuente31) {rutafuente = ruta31;}
            if (currentRadio == fuente32) {rutafuente = ruta32;}
            if (currentRadio == fuente33) {rutafuente = ruta33;}
            if (currentRadio == fuente35) {rutafuente = ruta35;}
            if (currentRadio == fuente36) {rutafuente = ruta36;}
            if (currentRadio == fuente37) {rutafuente = ruta37;}
            if (currentRadio == fuente40) {rutafuente = ruta40;}
            if (currentRadio == fuente41) {rutafuente = ruta41;}
            if (currentRadio == fuente42) {rutafuente = ruta42;}
            if (currentRadio == fuente43) {rutafuente = ruta43;}
            if (currentRadio == fuente44) {rutafuente = ruta44;}
            if (currentRadio == fuente47) {rutafuente = ruta47;}
            if (currentRadio == fuente48) {rutafuente = ruta48;}
            if (currentRadio == fuente49) {rutafuente = ruta49;}
            if (currentRadio == fuente50) {rutafuente = ruta50;}
            if (currentRadio == fuente51) {rutafuente = ruta51;}
            if (currentRadio == fuente52) {rutafuente = ruta52;}
            if (currentRadio == fuente53) {rutafuente = ruta53;}
            if (currentRadio == fuente54) {rutafuente = ruta54;}
            if (currentRadio == fuente55) {rutafuente = ruta55;}
            if (currentRadio == fuente56) {rutafuente = ruta56;}
            if (currentRadio == fuente57) {rutafuente = ruta57;}
            if (currentRadio == fuente58) {rutafuente = ruta58;}
            if (currentRadio == fuente59) {rutafuente = ruta59;}
            if (currentRadio == fuente60) {rutafuente = ruta60;}
            if (currentRadio == fuente61) {rutafuente = ruta61;}
            if (currentRadio == fuente62) {rutafuente = ruta62;}
            if (currentRadio == fuente63) {rutafuente = ruta63;}
            if (currentRadio == fuente64) {rutafuente = ruta64;}
            if (currentRadio == fuente65) {rutafuente = ruta65;}
            if (currentRadio == fuente66) {rutafuente = ruta66;}
            if (currentRadio == fuente67) {rutafuente = ruta67;}
            if (currentRadio == fuente68) {rutafuente = ruta68;}
            if (currentRadio == fuente69) {rutafuente = ruta69;}
            if (currentRadio == fuente70) {rutafuente = ruta70;}
            if (currentRadio == fuente71) {rutafuente = ruta71;}
            if (currentRadio == fuente72) {rutafuente = ruta72;}
            if (currentRadio == fuente73) {rutafuente = ruta73;}
            if (currentRadio == fuente74) {rutafuente = ruta74;}
            if (currentRadio == fuente75) {rutafuente = ruta75;}
            if (currentRadio == fuente76) {rutafuente = ruta76;}
            if (currentRadio == fuente77) {rutafuente = ruta77;}
            if (currentRadio == fuente78) {rutafuente = ruta78;}
            if (currentRadio == fuente79) {rutafuente = ruta79;}
            if (currentRadio == fuente80) {rutafuente = ruta80;}
            if (currentRadio == fuente81) {rutafuente = ruta81;}
            if (currentRadio == fuente82) {rutafuente = ruta82;}
            if (currentRadio == fuente83) {rutafuente = ruta83;}
            if (currentRadio == fuente91) {rutafuente = ruta91;}
            if (currentRadio == fuente91) {rutafuente = ruta91;}
            if (currentRadio == fuente92) {rutafuente = ruta92;}
            if (currentRadio == fuente93) {rutafuente = ruta93;}

            Intent databack = new Intent();
            databack.putExtra("rf", rutafuente);
            setResult(RESULT_OK, databack);
            finish();
        });
    }
}
