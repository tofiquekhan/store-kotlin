package rest

import com.coffeeshop.dto.ShopDTO
import com.coffeeshop.service.ShopService
import com.storesystem.response.ResponseHandler.generateResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/shops")
class ShopController {
    @Autowired
    private val shopService: ShopService? = null
    @PostMapping
    fun saveUpdateShop(@RequestBody shopDTO: ShopDTO?): ResponseEntity<Any> {
        return generateResponse("success", HttpStatus.OK, shopService.saveUpdateShop(shopDTO))
    }

    @get:GetMapping
    val shops: ResponseEntity<Any>
        get() = generateResponse("success", HttpStatus.OK, shopService.getShops())

    @GetMapping("/{id}")
    fun getShop(@PathVariable id: Long?): ResponseEntity<Any> {
        return generateResponse("success", HttpStatus.OK, shopService.getShopDetailsById(id))
    }

    @PutMapping
    fun updateShop(@RequestBody shopDTO: ShopDTO?): ResponseEntity<Any> {
        val updateShop: ShopDTO = shopService.saveUpdateShop(shopDTO)
        return generateResponse("success", HttpStatus.OK, updateShop.getId())
    }

    @DeleteMapping("/{id}")
    fun deleteShop(@PathVariable id: Long?): ResponseEntity<Any> {
        shopService.getShopDetailsById(id)
        return generateResponse("Shop Deleted Successfully", HttpStatus.OK, null)
    }
}