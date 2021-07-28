package com.drovisfrovis.rentalcarservice.uIBackend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.drovisfrovis.rentalcarservice.R
import com.drovisfrovis.rentalcarservice.classes.DriverInfo
import kotlinx.android.synthetic.main.activity_register_driver.*
import java.io.IOException
import java.io.OutputStreamWriter
import java.util.*

class RegisterDriver : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_driver)
        btn_register.setOnClickListener {
            if(checkDataValidation()){
                Toast.makeText(this, "Driver is Registered successfully", Toast.LENGTH_SHORT).show()
                val data = "Name: ${DriverInfo.name} | CNIC: ${DriverInfo.cnicNumber} | Address: ${DriverInfo.address} | " +
                        "Phone: ${DriverInfo.phoneNumber} | Rented car: ${DriverInfo.carName} | Car model: ${DriverInfo.carModelNumber} " +
                        "At Time: ${Calendar.getInstance().time}\n"
                writeDataToFile(data)

                val intent = Intent(this, RentCars::class.java)

                intent.putExtra("driver", et_name_driver.text.toString())
                intent.putExtra("address", et_address.text.toString())
                intent.putExtra("phone", et_phone.text.toString())
                setResult(RESULT_OK, intent)
                finish()
            }

        }

        btn_cancel_driver.setOnClickListener {
            setResult(RESULT_CANCELED, Intent(this, RentCars::class.java))
            finish()
        }
    }

    private fun writeDataToFile(data: String) {
        val fileName = "driver.txt"
        try {
            val outputStreamWriter = OutputStreamWriter(openFileOutput(fileName, MODE_APPEND))
            outputStreamWriter.append(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e("Exception", "File write failed: $e")
        }
    }


    private fun checkDataValidation(): Boolean{

        var flag = true
        if(et_name_driver.text.toString().isEmpty()){
            et_name_driver.error = "Name must be specified"
            flag = false
        }

        if(et_cnic.text.toString().isEmpty()){
            et_cnic.error = "CNIC cannot be empty"
            flag = false
        }

        if(et_address.text.toString().isEmpty()){
            et_address.error = "Address cannot be empty"
            flag = false
        }

        if(et_phone.text.toString().isEmpty()){
            et_phone.error = "Phone must be specified"
            flag = false
        }



        if(et_rented_car.text.toString().isEmpty()){
            et_rented_car.error = "Car name cannot be empty"
            flag = false
        }

        if(et_car_model.text.toString().isEmpty()){
            et_car_model.error = "Car Model name cannot be empty"
            flag = false
        }

        if(flag){
            DriverInfo.name = et_name_driver.text.toString().trim()
            DriverInfo.cnicNumber = et_cnic.text.toString().trim().toLong()
            DriverInfo.address = et_address.text.toString().trim()
            DriverInfo.phoneNumber = et_phone.text.toString().trim().toLong()
            DriverInfo.carName = et_rented_car.text.toString().trim()
            DriverInfo.carModelNumber = et_car_model.text.toString().trim()
            return true
        }
        return false
    }
}