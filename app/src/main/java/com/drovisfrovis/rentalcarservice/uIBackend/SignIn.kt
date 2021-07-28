package com.drovisfrovis.rentalcarservice.uIBackend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.drovisfrovis.rentalcarservice.R
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader

class SignIn : AppCompatActivity() {

private lateinit var userName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btn_submit_sign_in.setOnClickListener {
            if(validateData()){
                Toast.makeText(this, "Successfully sign in", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, RentCars::class.java)
                intent.putExtra("user", userName)
                startActivity(intent)
                finish()
            }

            else
                Toast.makeText(this, "Invalid data", Toast.LENGTH_SHORT).show()
        }

        btn_cancel_sign_in.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun validateData(): Boolean{
        var flag = true
        if(et_email_sign_in.text.toString().isEmpty()){
            et_email_sign_in.error = "Email cannot be empty"
            flag = false
        }

        if(et_pass_sign_in.text.toString().isEmpty()){
            et_email_sign_in.error = "Password cannot be empty"
            flag = false
        }

        if(flag){
            if(validateEmailAndPass()){
                return flag
            }
            flag = false
        }
        return flag
    }

    private fun validateEmailAndPass(): Boolean{
        val fileName = "user.txt"
        try {
            val isr = InputStreamReader(openFileInput(fileName))
            val br = BufferedReader(isr)
            val sb = StringBuilder();
            var data: String? = br.readLine()
            var oneRecord: MutableList<String>
            while(data != null){
                oneRecord = data.split("&") as MutableList<String>
                if(oneRecord[2] == et_email_sign_in.text.toString() && oneRecord[3] == et_pass_sign_in.text.toString()){
                    userName = oneRecord[0]
                    return true
                }
                oneRecord.clear()
                data = br.readLine()
            }
        }catch (e: FileNotFoundException) {
            Log.e("login activity", "File not found: $e");
        } catch (e: IOException) {
            Log.e("login activity", "Can not read file: $e");
        }

        return false

    }
}