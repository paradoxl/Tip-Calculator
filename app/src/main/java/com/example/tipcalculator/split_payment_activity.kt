package com.example.tipcalculator

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


private  lateinit var theButton: Button
private lateinit var baseBill: EditText
private lateinit var tipText: TextView
private lateinit var totalTip: TextView
private lateinit var totalBill: TextView
//private lateinit var tipView : TextView
private lateinit var numberOfPeople: TextView

private const val TAG = "split_payment_activity"


class SplitPaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_split_payment)
        theButton = findViewById(R.id.multiple)
        baseBill = findViewById(R.id.enterBaseAmount)
        tipText = findViewById(R.id.editTip)
        totalTip = findViewById(R.id.totalTip)
        totalBill = findViewById(R.id.totalBill)
        numberOfPeople = findViewById(R.id.numberOfPeople)
        theButton.setBackgroundResource(R.color.purple_700)
        //switches activities
        theButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        tipText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(S: Editable?) {
                Log.i(TAG, "afterTextChanged $S")
//                computeTotals()
            }
        })




        baseBill.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(S: Editable?) {
                Log.i(TAG, "afterTextChanged $S")
//                computeTotals()
            }
        })


        numberOfPeople.addTextChangedListener(object : TextWatcher {
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


    private fun computeTotals() {
        if (numberOfPeople.text.isEmpty()) {
            totalTip.text = ""
            totalBill.text = ""
            return
        }

        if (baseBill.text.isEmpty()) {
            totalTip.text = ""
            totalBill.text = " "
            return
        }

        if (tipText.text.isEmpty()) {
            totalTip.text = ""
            totalBill.text = " "
            return
        }

        val base = baseBill.text.toString().toDouble()
        val tip = tipText.text.toString().toDouble()
        val people = numberOfPeople.text.toString().toDoubleOrNull()

        val tipAmount = base * tip / 100
        val totals = (base + tipAmount)
        println(people)
        if (people != null){
            val final = totals / people as Double //here
            totalTip.text = "%.2f".format(tipAmount)
            totalBill.text = "%.2f".format(final)
        }
        else{     
            totalBill.text = "Error determining number of people"
        }

        }

    }




