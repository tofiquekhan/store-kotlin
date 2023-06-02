package service.impl

import exception.ShopNotFoundException
import dto.ShopDTO
import entity.Shop
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import repository.ShopRepository
import service.ShopService
import java.util.stream.Collectors

@Service

class ShopServiceImpl : ShopService {
    @Autowired
    private val shopRepository: ShopRepository? = null

    @Autowired
    private val modelMapper: ModelMapper? = null
    override fun saveUpdateShop(shopDTO: ShopDTO?): ShopDTO? {

        val shop = modelMapper!!.map(shopDTO, Shop::class.java)
        val savedShop = shopRepository!!.saveAndFlush(shop)
        val savedShopDTO = modelMapper.map(savedShop, ShopDTO::class.java)

        return savedShopDTO
    }

    override val shops: List<ShopDTO>
        get() {

            val shops = shopRepository!!.findAll() as List<Shop?>
            val shopDTOS = shops.stream().map { shop: Shop? ->
                modelMapper!!.map(
                    shop,
                    ShopDTO::class.java
                )
            }.collect(Collectors.toList())

            return shopDTOS
        }

    override fun removeShopDetailsById(id: Long?) {

        getShopDetailsById(id)
        shopRepository!!.deleteById(id)

    }

    override fun getShopDetailsById(id: Long?): ShopDTO? {

        val shopOptional = shopRepository!!.findById(id)
        if (shopOptional.isEmpty) {

            throw ShopNotFoundException("Shop not found with id ::$id")
        }
        val shop = shopOptional.get()
        val shopDTO = modelMapper!!.map(shop, ShopDTO::class.java)
        return shopDTO
    }
}