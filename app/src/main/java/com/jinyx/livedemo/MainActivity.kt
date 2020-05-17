package com.jinyx.livedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import tv.danmaku.ijk.media.player.IMediaPlayer
import tv.danmaku.ijk.media.player.IjkMediaPlayer

class MainActivity : AppCompatActivity(),
    IMediaPlayer.OnPreparedListener, IMediaPlayer.OnInfoListener,
    IMediaPlayer.OnCompletionListener, IMediaPlayer.OnErrorListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }

        val url = intent.getStringExtra("playUrl")!!

        player?.setOnPreparedListener(this)
        player?.setOnInfoListener(this)
        player?.setOnCompletionListener(this)
        player?.setOnErrorListener(this)
        player?.setVideoPath(url)
        player?.start()
    }

    override fun onPrepared(mp: IMediaPlayer?) {
        progressBar.visibility = View.GONE
    }

    override fun onInfo(mp: IMediaPlayer?, what: Int, extra: Int): Boolean {
        return true
    }

    override fun onCompletion(mp: IMediaPlayer?) {

    }

    override fun onError(mp: IMediaPlayer?, what: Int, extra: Int): Boolean {
        Toast.makeText(this, "播放失败", Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onDestroy() {
        player?.stopPlayback()
        player?.release(true)
        player?.stopBackgroundPlay()
        IjkMediaPlayer.native_profileEnd()
        super.onDestroy()
    }

}
