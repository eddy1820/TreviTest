package com.tw4gamers.trevitest.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.tw4gamers.trevitest.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.view_item_btn.view.*


class ItemBtnView @kotlin.jvm.JvmOverloads constructor(context: Context,
                                                       attrs: AttributeSet? = null,
                                                       defStyleAttr: Int = 0)
  : RelativeLayout(context, attrs, defStyleAttr) {

  private val btnCallback = PublishSubject.create<View>()

  fun getBtnCallback(): Observable<View> = btnCallback

  init {
    LayoutInflater.from(context).inflate(R.layout.view_item_btn, this)
    btn.setOnClickListener {
      btnCallback.onNext(it)
    }
  }

  fun showLine(isShow: Boolean) {
    leftLineView.visibility = if (isShow) View.VISIBLE else View.GONE
    rightLineView.visibility = if (isShow) View.VISIBLE else View.GONE
    bottomLineView.visibility = if (isShow) View.VISIBLE else View.GONE
  }

  fun setBtnEnabled(isEnabled: Boolean) {
    btn.isEnabled = isEnabled
  }
}