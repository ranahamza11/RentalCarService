package com.drovisfrovis.rentalcarservice.uIBackend

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.drovisfrovis.rentalcarservice.R
import kotlinx.android.synthetic.main.activity_rent_cars.*

class RentCars : AppCompatActivity() {
    private val registerDriverReqCode = 1
    private val prevHistoryReqCode = 2

    private var phone: String? = "llo"
    private var address: String? = "hell"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rent_cars)
        tv_dispaly_userName.text =  getString(R.string.welcome_user, intent.getStringExtra("user"))
        btn_rent_cars.setOnClickListener {
            iv_phone.visibility = View.INVISIBLE
            iv_map.visibility = View.INVISIBLE
            tv_driver_name.visibility = View.INVISIBLE
            tv_info.visibility = View.INVISIBLE
            startActivityForResult(Intent(this, RegisterDriver::class.java), registerDriverReqCode)
        }

        btn_prev_history.setOnClickListener {
            startActivityForResult(Intent(this, DriversHistory::class.java), prevHistoryReqCode)
        }

        btn_logout.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        iv_phone.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")))
        }

        iv_map.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$address")))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == registerDriverReqCode){
            if(resultCode == RESULT_OK){
                iv_phone.visibility = View.VISIBLE
                iv_map.visibility = View.VISIBLE
                tv_driver_name.visibility = View.VISIBLE
                tv_info.visibility = View.VISIBLE

                tv_driver_name.text = getString(R.string.driver_name_is, data?.getStringExtra("driver"))
                phone = data?.getStringExtra("phone")
                address = data?.getStringExtra("address")

            }

            if(resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Driver registration is unsuccessful", Toast.LENGTH_SHORT).show()
            }
        }
    }
}