package com.example.entity

class BusinessCustomer (name: String, email: String, phone:Int, val discount:Double): Customer(name, email, phone)
{
    override fun toString(): String {
        return "${super.toString()}\n\tdiscount: $discount"
    }
}
