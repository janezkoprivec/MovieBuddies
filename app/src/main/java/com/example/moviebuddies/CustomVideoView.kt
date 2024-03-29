package com.example.moviebuddies

import android.R.attr.start
import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView


class CustomVideoView : VideoView {

    private var mListener: PlayPauseListener? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    fun setPlayPauseListener(listener: PlayPauseListener) {
        mListener = listener
    }

    override fun pause() {
        super.pause()
        if (mListener != null) {
            mListener!!.onPause()
        }
    }

    override fun start() {
        super.start()
        if (mListener != null) {
            mListener!!.onPlay()
        }
    }

    override fun seekTo(msec: Int) {
        super.seekTo(msec)
        if(mListener != null){
            mListener!!.onSeek()
        }
    }

    interface PlayPauseListener {
        fun onPlay()
        fun onPause()
        fun onSeek()
    }

}
