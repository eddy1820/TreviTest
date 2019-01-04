package com.tw4gamers.trevitest.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.tw4gamers.trevitest.R
import kotlinx.android.synthetic.main.view_item.view.*


class ItemView @kotlin.jvm.JvmOverloads constructor(context: Context,
                                                    attrs: AttributeSet? = null,
                                                    defStyleAttr: Int = 0)
  : RelativeLayout(context, attrs, defStyleAttr) {
  init {
    LayoutInflater.from(context).inflate(R.layout.view_item, this)
  }

  fun showText(isShow: Boolean) {
    tv.visibility = if (isShow) View.VISIBLE else View.GONE
  }

  fun showAllLine(isShow: Boolean) {
    leftLineView.visibility = if (isShow) View.VISIBLE else View.GONE
    rightLineView.visibility = if (isShow) View.VISIBLE else View.GONE
    topLineView.visibility = if (isShow) View.VISIBLE else View.GONE
  }

  fun showSideLine(isShow: Boolean) {
    leftLineView.visibility = if (isShow) View.VISIBLE else View.GONE
    rightLineView.visibility = if (isShow) View.VISIBLE else View.GONE
  }

}