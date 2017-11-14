package com.example.service

import com.example.entity.*
import com.example.logger.Logger
import java.text.SimpleDateFormat

fun main(args: Array<String>)
{
    val customerList = prepareCustomerList()
    val productList = prepareProductList()


    val dateformat = SimpleDateFormat("dd/mm/yyyy hh:mm")
    val order1 = Order(dateformat.parse("12/08/2016 13:32"), customerList[1], mapOf(productList[0] to 12, productList[2] to 1), 45442)
    val order2 = Order(dateformat.parse("13/09/2017 11:12"), customerList[0], mapOf(productList[0] to 122), 332)
    val order3 = Order(dateformat.parse("12/02/2017 16:11"), customerList[2], mapOf(productList[2] to 10, productList[1] to 100), 452)

    Logger.log(order1)

    Logger.log(order2)

    Logger.log(order3)

    val array = arrayOf("--input",  "C:\\Programowanie\\KotlinTest", "-o",  "C:\\Programowanie\\KotlinTest\\output")
    io.gitlab.arturbosch.detekt.cli.main(array)

}

private fun prepareCustomerList() : List<Customer>
{
    val customer1 = Customer("John", "john@mail.com", 72665345)
    val customer2 = Customer("Terry", "terry@mail.com", 22341554)
    val customer3 = BusinessCustomer("Texy Inc.", "texy@texy.com", 12387654, 0.12)
    val customer4 = Customer("Ralph", "ralph@mail.com", 54555354)

    return listOf(customer1, customer2, customer3, customer4)
}

private fun prepareProductList(): List<Product>
{
    val producer1 = Producer("Apple Inc.", 232)
    val product1 = Product("Apple", 0.34, producer1)
    val product2 = Product("Table", 5.99, Producer("Ikea", 11111))
    val product3 = Product("iPod", 320.0, producer1)

    return listOf(product1, product2, product3)
}