package com.jinyx.livedemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Jin-Yx
 * @date 20-5-17
 */
class LiveAdapter : RecyclerView.Adapter<LiveAdapter.LiveViewHolder>() {

    private var mList = ArrayList<LiveBean>()

    private var mListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_live, parent, false)
        return LiveViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: LiveViewHolder, position: Int) {
        val textView = holder.findView<TextView>(R.id.liveName)
        textView.text = mList[position].name
        textView.setOnClickListener {
            mListener?.onItemClick(mList[position])
        }
    }

    fun setLiveList(liveList: List<LiveBean>) {
        mList.clear()
        mList.addAll(liveList)
        notifyDataSetChanged()
    }

    fun addOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    class LiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun <V : View> findView(@IdRes id: Int): V {
            return itemView.findViewById(id)
        }

    }

    interface OnItemClickListener {

        fun onItemClick(liveBean: LiveBean)

    }

}