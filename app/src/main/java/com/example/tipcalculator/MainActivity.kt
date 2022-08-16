package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var baseBill: EditText
    private lateinit var tipPercent: SeekBar
    private lateinit var totalTip: TextView
    private lateinit var totalBill: TextView
    private lateinit var tipView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        baseBill = findViewById(R.id.enterBaseAmount)
        tipPercent = findViewById(R.id.EnterTipPercent)
        totalTip = findViewById(R.id.totalTip)
        totalBill = findViewById(R.id.totalBill)
        tipView = findViewById(R.id.TipView)


        tipPercent.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i(TAG, "OnProgressChanged $progress")
                tipView.text = "$progress%"
                computeTotals()

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        baseBill.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(S: Editable?) {
                Log.i(TAG, "afterTextChanged $S")
                computeTotals()
            }
        })
    }


    private fun computeTotals(){
        if(baseBill.text.isEmpty()){
            totalTip.text = ""
            totalBill.text = " "
            return
        }
        val base = baseBill.text.toString().toDouble()
        val tip = tipPercent.progress


        val tipAmount = base * tip / 100
        val totals = base + tipAmount

        totalTip.text = "%.2f".format(tipAmount)
        totalBill.text = "%.2f".format(totals)
    }

}
