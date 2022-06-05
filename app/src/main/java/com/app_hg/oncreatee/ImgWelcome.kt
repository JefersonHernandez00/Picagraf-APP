package com.app_hg.oncreatee

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import com.sarnava.textwriter.TextWriter


class ImgWelcome : Activity() {
    private val imgI: Long = 1400

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_img_welcome)

        val display = windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)
        val widthPixels = outMetrics.widthPixels.toFloat()
        val density = outMetrics.density
        val adWidth = (widthPixels / density)

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT){
            val textWriter: TextWriter = findViewById(R.id.textWriter)
            textWriter.setWidth(adWidth/30)
                    .setDelay(20)
                    .setColor(resources.getColor(R.color.colorPrimaryx))
                    .setConfig(TextWriter.Configuration.INTERMEDIATE)
                    .setSizeFactor(adWidth/15)
                    .setLetterSpacing(adWidth/20)
                    .setText("PICAGRAF")
                    .setListener {
                        val NuevoLayaout = Intent(this@ImgWelcome, Edit_main::class.java)
                        startActivity(NuevoLayaout)
                        finish()
                    }.startAnimation()
        }else {
            val textWriter: TextWriter = findViewById(R.id.textWriter)
            textWriter.setWidth(10f)
                    .setDelay(20)
                    .setColor(resources.getColor(R.color.colorPrimaryx))
                    .setConfig(TextWriter.Configuration.INTERMEDIATE)
                    .setSizeFactor(37f)
                    .setLetterSpacing(20f)
                    .setText("PICAGRAF")
                    .setListener {
                        val NuevoLayaout = Intent(this@ImgWelcome, Edit_main::class.java)
                        startActivity(NuevoLayaout)
                        finish()
                    }.startAnimation()
        }


        /*val task = object : TimerTask() {
            override fun run() {
                val NuevoLayaout: Intent
                NuevoLayaout = Intent(this@ImgWelcome, Edit::class.java)
                startActivity(NuevoLayaout)
                finish()
            }
        }
        val timer = Timer()
        timer.schedule(task, imgI)*/


    }
}
