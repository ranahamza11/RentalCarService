package com.drovisfrovis.rentalcarservice.uIBackend

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.drovisfrovis.rentalcarservice.R
import com.drovisfrovis.rentalcarservice.classes.UserData
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.io.IOException
import java.io.OutputStreamWriter

class SignUp : AppCompatActivity() {

    private val fileName: String = "user.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        btn_submit.setOnClickListener {
            if(checkDataValidation()){
                val data = "${UserData.name}&${UserData.age}&${UserData.email}&${UserData.password}\n"
                writeDataToFile(data)
                Toast.makeText(this,"Sign UP successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        btn_cancel.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


    }

    private fun writeDataToFile(data: String) {
        try {
            val outputStreamWriter = OutputStreamWriter(openFileOutput(fileName, Context.MODE_APPEND))
            outputStreamWriter.append(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e("Exception", "File write failed: $e")
        }
    }

    private fun checkDataValidation(): Boolean{

        var flag = true
        if(et_name.text.toString().isEmpty()){
            et_name.error = "Name must be specified"
            flag = false
        }

        if(et_age.text.toString().isEmpty()){
            et_age.error = "Age cannot be empty"
            flag = false
        }

        if(et_email.text.toString().isEmpty()){
            et_email.error = "Email cannot be empty"
            flag = false
        }

        if(et_pass.text.toString().isEmpty()){
            et_pass.error = "Password cannot be empty"
            flag = false
        }

        if(et_conf_pass.text.toString().isEmpty()){
            et_conf_pass.error = "Password cannot be empty"
            flag = false
        }

        if(flag){
            if(et_pass.text.toString() == et_conf_pass.text.toString()){
                UserData.name = et_name.text.toString().trim()
                UserData.age = et_age.text.toString().trim().toInt()
                UserData.email = et_email.text.toString().trim()
                UserData.password = et_pass.text.toString().trim()

                return true
            }
            else{
                et_pass.text.clear()
                et_conf_pass.text.clear()
                Toast.makeText(this, "Password mismatch", Toast.LENGTH_LONG).show()
            }
        }
        return false
    }
}