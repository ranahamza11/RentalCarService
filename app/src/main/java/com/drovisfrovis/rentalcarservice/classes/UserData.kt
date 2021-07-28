package com.drovisfrovis.rentalcarservice.classes

object UserData {
    lateinit var name: String
    var age: Int = -1
        set(value) {
            field = if(value < 1) -1 else value
        }

    lateinit var email: String
    lateinit var password: String

}