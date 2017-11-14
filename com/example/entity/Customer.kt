package com.example.entity

open class Customer(val name: String, val email:String, val phone:Int)
{
    override fun toString(): String {
        return "Customer \n\tname: \t$name, \n\temail: \t$email, \n\tphone: \t$phone"
    }


}