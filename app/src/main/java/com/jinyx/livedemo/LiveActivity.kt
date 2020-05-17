package com.jinyx.livedemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_live.*

/**
 * @author Jin-Yx
 * @date 20-5-17
 */
class LiveActivity : AppCompatActivity() {

    private val mAdapter by lazy {
        LiveAdapter().apply {
            addOnItemClickListener(object : LiveAdapter.OnItemClickListener {
                override fun onItemClick(liveBean: LiveBean) {
                    val intent = Intent(this@LiveActivity, MainActivity::class.java)
                    intent.putExtra("playUrl", liveBean.url)
                    startActivity(intent)
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live)

        liveList?.layoutManager = LinearLayoutManager(this)
        liveList?.adapter = mAdapter

        loadContent()
    }

    fun loadContent() {
        val list = resources.getStringArray(R.array.tvUrl).map {
            val live = it.split(",")
            LiveBean(live[0], live[1])
        }
        mAdapter.setLiveList(list)
    }

}