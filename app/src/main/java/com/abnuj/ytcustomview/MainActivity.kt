package com.abnuj.ytcustomview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv = findViewById<TextView>(R.id.colorText)
        val mixer = findViewById<ColorMixer>(R.id.mixer)

        mixer.setOnColorChangeListner(object : ColorMixer.OnColorChangedListener {
            override fun onColorChange(argb: Int) {
                tv.setText(Integer.toHexString(argb))
            }
        })
    }
}