package rest


import response.ResponseHandler.generateResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController {
    @Autowired
    private val orderService: OrderService? = null
    @PostMapping
    fun saveUpdateOrder(@RequestBody orderDTO: OrderDTO?): ResponseEntity<Any> {
        return generateResponse("success", HttpStatus.OK, orderService.saveUpdateOrder(orderDTO))
    }

    @get:GetMapping
    val orders: ResponseEntity<Any>
        get() = generateResponse("success", HttpStatus.OK, orderService.getOrders())

    @GetMapping("/{id}")
    fun getOrder(@PathVariable id: Long?): ResponseEntity<Any> {
        return generateResponse("success", HttpStatus.OK, orderService.getOrderDetailsById(id))
    }

    @PutMapping
    fun updateOrder(@RequestBody orderDTO: OrderDTO?): ResponseEntity<Any> {
        val updateOrder: OrderDTO = orderService.saveUpdateOrder(orderDTO)
        return generateResponse("success", HttpStatus.OK, updateOrder.getId())
    }

    @DeleteMapping("/{id}")
    fun deleteOrder(@PathVariable id: Long?): ResponseEntity<Any> {
        orderService.getOrderDetailsById(id)
        return generateResponse("Order Deleted Successfully", HttpStatus.OK, null)
    }
}