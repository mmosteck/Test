package com.example.entity

import java.util.*

data class Order(val date:Date, val customer: Customer, val productList:Map<Product, Int>, val orderNumber:Int)
{
    fun calculateTotalPrice():Double
    {
        tailrec fun calculate(sum: Double, iterator: Iterator<Map.Entry<Product, Int>> ) : Double = if (!iterator.hasNext()) sum else {
            val it = iterator.next()
            calculate(sum + it.key.price * it.value, iterator)
        }

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
