package com.abnuj.ytcustomview

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener

class ColorMixer(context: Context, attr: AttributeSet) : RelativeLayout(context, attr, 0) {

    private var swatch: View? = null
    private var red: SeekBar? = null
    private var blue: SeekBar? = null
    private var green: SeekBar? = null
    private var listener: OnColorChangedListener? = null

    val onMix: OnSeekBarChangeListener = object : OnSeekBarChangeListener {

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            val color = getColor()
            swatch!!.setBackgroundColor(color)
            if (listener != null) {
                listener!!.onColorChange(color)
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {

        }
    }

    init {
        (context as Activity).layoutInflater.inflate(R.layout.mixer, this, true)


        swatch = findViewById(R.id.swatch);

        red = findViewById(R.id.red);
        red?.max = 0xFF;
        red?.setOnSeekBarChangeListener(onMix);

        green = findViewById(R.id.green);
        green?.setMax(0xFF);
        green?.setOnSeekBarChangeListener(onMix);

        blue = findViewById(R.id.blue);
        blue?.setMax(0xFF);
        blue?.setOnSeekBarChangeListener(onMix);

        val a: TypedArray = context.obtainStyledAttributes(attr, R.styleable.ColorMixer, 0, 0)
        setColor(a.getInt(R.styleable.ColorMixer_initialColor, 0xFFA4C639.toInt()))
        a.recycle()

    }

    private fun setColor(color: Int) {
        swatch!!.setBackgroundColor(color)
        red!!.progress = Color.red(color)
        green!!.progress = Color.green(color)
        blue!!.progress = Color.blue(color)
    }


    fun getColor(): Int {
        return Color.argb(0xFF, red!!.progress, green!!.progress, blue!!.progress)
    }


    fun setOnColorChangeListner(listener: OnColorChangedListener){
        this.listener = listener
    }

    interface OnColorChangedListener {
        fun onColorChange(argb: Int)
    }


}