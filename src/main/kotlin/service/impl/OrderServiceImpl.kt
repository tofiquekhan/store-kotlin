package service.impl

import entity.Order
import exception.OrderNotFoundException
import service.OrderService
import dto.OrderDTO

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import repository.OrderRepository
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors

@Service

class OrderServiceImpl : OrderService {
    @Autowired
    private val orderRepository: OrderRepository? = null

    @Autowired
    private val modelMapper: ModelMapper? = null
    override fun saveUpdateOrder(orderDTO: OrderDTO?): OrderDTO? {
        val order: Order = modelMapper!!.map(orderDTO, Order::class.java)
        val savedOrder: Order = orderRepository!!.saveAndFlush(order)
        val savedOrderDTO = modelMapper.map(savedOrder, OrderDTO::class.java)
        return savedOrderDTO
    }

    override val orders: List<OrderDTO>
        get() {

            val orders: List<Order?> = orderRepository!!.findAll() as List<Order?>
            val orderDTOS = orders.stream().map<Any>(Function<Order?, Any> { order: Order? ->
                modelMapper!!.map(
                    order,
                    OrderDTO::class.java
                )
            }).collect(Collectors.toList<Any>())

            return orderDTOS
        }

    override fun removeOrderDetailsById(id: Long?) {

        getOrderDetailsById(id)
        orderRepository!!.deleteById(id)

    }

    override fun getOrderDetailsById(id: Long?): OrderDTO? {

        val orderOptional: Optional<Order?> = orderRepository!!.findById(id)
        if (orderOptional.isEmpty()) {

            throw OrderNotFoundException("Order not found with id ::$id")
        }
        val order: Order = orderOptional.get()
        val orderDTO = modelMapper!!.map(order, OrderDTO::class.java)

        return orderDTO
    }
}