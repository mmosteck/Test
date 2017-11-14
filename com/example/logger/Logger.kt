package com.example.logger

import com.example.entity.Order
import kotlin.Comparator;

object Logger
{
    fun log(order: Order)
    {
        println("Customer ${order.customer.name} placed order ${order.orderNumber} on ${order.date} ")
        println("Product list: ${order.productList}")
    }

    fun panic(panicMessage: String)
    {
        println("PANIC! $panicMessage")
    }
}