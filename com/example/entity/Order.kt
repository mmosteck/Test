package com.example.entity

import java.util.*
import kotlin.Byte

data class Order(val date:Date, val customer: Customer, val productList:Map<Product, Int>, val orderNumber:Int)
{
    fun calculateTotalPrice():Double
    {
        tailrec fun calculate(sum: Double, iterator: Iterator<Map.Entry<Product, Int>> ) : Double = if (!iterator.hasNext()) sum else {
            val it = iterator.next()
            calculate(sum + it.key.price * it.value, iterator)
        }

        val i = 5

        if (i == 5)
        {}

        return calculate(0.0, productList.iterator())
    }



    fun cal():Double
    {
        var sum = 0.0
        productList.forEach {product, quantity -> sum +=  product.price * quantity }
        if(customer is BusinessCustomer)
            sum *= (1 - customer.discount)
        return sum
    }


}
