package com.tw4gamers.trevitest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tw4gamers.trevitest.util.addTo
import com.tw4gamers.trevitest.widget.ItemBtnView
import com.tw4gamers.trevitest.widget.ItemView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_trevi.*
import java.util.*
import kotlin.concurrent.schedule

class TreviActivity : AppCompatActivity() {
  private var compositeDisposable = CompositeDisposable()

  companion object {
    private const val COLUMN = "COLUMN"
    private const val ROW = "ROW"
    fun start(activity: Activity, column: Int, row: Int) {
      val intent = Intent(activity, TreviActivity::class.java)
      intent.putExtra(COLUMN, column)
      intent.putExtra(ROW, row)
      activity.startActivity(intent)
    }

  }

  private val random = Random()
  private val indexList = mutableListOf<Int>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_trevi)
    val column = intent.getIntExtra(COLUMN, 0)
    val row = intent.getIntExtra(ROW, 0)
    initView(column, row)
    startGame(column, row)
  }

  private fun startGame(column: Int, row: Int) {
    val randomNum = random.nextInt(column * row)
    indexList.clear()
    indexList.addAll(findItemViewIndex(randomNum, column, row))
    showRandom(randomNum, column)
  }

  private fun initView(column: Int, row: Int) {
    gridLayout.columnCount = column
    gridLayout.rowCount = row
    val childCount = column * (row + 1)
    for (i in 1..childCount) {
      if (i > childCount - column) {
        val button = ItemBtnView(this)
        button.getBtnCallback().subscribe {
          hideViewLine()
          startGame(column, row)
        }.addTo(compositeDisposable)
        gridLayout.addView(button)
      } else {
        val itemView = ItemView(this)
        gridLayout.addView(itemView)
      }
    }
  }

  private fun hideViewLine() {
    indexList.forEach {
      val childView = gridLayout.getChildAt(it)
      when (childView) {
        is ItemView -> {
          childView.showAllLine(false)
          childView.showText(false)
        }
        is ItemBtnView -> {
          childView.setBtnEnabled(false)
          childView.showLine(false)
        }
      }
    }
  }

  private fun showRandom(randomNum: Int, column: Int) {
    val timer = Timer()
    timer.schedule(3000) {
      val itemView = gridLayout.getChildAt(randomNum) as ItemView
      runOnUiThread {
        itemView.showText(true)
        indexList.forEach {
          val childView = gridLayout.getChildAt(it)
          when (childView) {
            is ItemView -> {
              if (it <= column) {
                childView.showAllLine(true)
              } else {
                childView.showSideLine(true)
              }
            }
            is ItemBtnView -> {
              childView.setBtnEnabled(true)
              childView.showLine(true)
            }
          }
        }
      }
      timer.cancel()
    }
  }


  private fun findItemViewIndex(randomNum: Int, column: Int, row: Int): List<Int> {
    val indexList = mutableListOf<Int>()
    indexList.add(randomNum)
    var upIndex = randomNum
    var downIndex = randomNum
    while (upIndex < column * row) {
      upIndex += column
      indexList.add(upIndex)
    }
    while (downIndex > 0) {
      downIndex -= column
      if (downIndex >= 0) {
        indexList.add(downIndex)
      }
    }
    return indexList
  }

  override fun onDestroy() {
    super.onDestroy()
    compositeDisposable.dispose()
  }
}