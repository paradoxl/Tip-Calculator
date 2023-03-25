package com.example.tipcalculator

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var baseBill: EditText
    private lateinit var tipText: TextView
    private lateinit var totalTip: TextView
    private lateinit var totalBill: TextView
    private lateinit var tipView : TextView
    private lateinit var theButton: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        baseBill = findViewById(R.id.enterBaseAmount)
        tipText = findViewById(R.id.editTip)
        totalTip = findViewById(R.id.totalTip)
        totalBill = findViewById(R.id.totalBill)
        theButton = findViewById(R.id.single)


        theButton.setOnClickListener {
            val intent = Intent(this, SplitPaymentActivity::class.java)
            startActivity(intent)
        }

        tipText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(S: Editable?) {
                Log.i(TAG, "afterTextChanged $S")
                computeTotals()
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
        if(tipText.text.isEmpty()){
            totalTip.text = ""
            totalBill.text = " "
            return
        }
        val base = baseBill.text.toString().toDouble()
        val tip = tipText.text.toString().toDouble()


        val tipAmount = base * tip / 100
        val totals = base + tipAmount

        totalTip.text = "%.2f".format(tipAmount)
        totalBill.text = "%.2f".format(totals)
    }



}
