package com.tw4gamers.trevitest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    confirmBtn.setOnClickListener {
      val column = columnEdit.text.toString().toInt()
      val row = rowEdit.text.toString().toInt()
      if (column > 0 && row > 0) {
        TreviActivity.start(this, column, row)
      } else {
        Toast.makeText(this, "row or column must be greater than 0", Toast.LENGTH_SHORT).show()
      }
    }
  }
}
