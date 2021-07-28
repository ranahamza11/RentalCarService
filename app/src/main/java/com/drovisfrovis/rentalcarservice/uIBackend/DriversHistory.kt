package com.drovisfrovis.rentalcarservice.uIBackend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.drovisfrovis.rentalcarservice.R
import kotlinx.android.synthetic.main.activity_drivers_history.*
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader

class DriversHistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drivers_history)
        readDataFromFile()

        btn_go_back.setOnClickListener {
            setResult(RESULT_OK, Intent(this, RentCars::class.java))
            finish()
        }

    }

    private fun readDataFromFile(){
        val fileName = "driver.txt"
        try {
            val isr = InputStreamReader(openFileInput(fileName))
            val br = BufferedReader(isr)
            val sb = StringBuilder();
            var data: String? = br.readLine()

            while(data != null){
                sb.append(data).append("\n\n")
                data = br.readLine()
            }
            tv_display_prev_records.text = sb.toString()
        }catch (e: FileNotFoundException) {
            Log.e("login activity", "File not found: $e")
        } catch (e: IOException) {
            Log.e("login activity", "Can not read file: $e")
        }



    }

}