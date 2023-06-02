package service

import dto.OrderDTO

interface OrderService {
    fun saveUpdateOrder(orderDTO: OrderDTO?): OrderDTO?
    val orders: List<OrderDTO?>?

    fun removeOrderDetailsById(id: Long?)
    fun getOrderDetailsById(id: Long?): OrderDTO?
}