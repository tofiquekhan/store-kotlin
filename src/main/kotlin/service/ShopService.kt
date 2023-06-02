package service

import dto.ShopDTO

interface ShopService {
    fun saveUpdateShop(shopDTO: ShopDTO?): ShopDTO?
    val shops: List<ShopDTO?>?

    fun removeShopDetailsById(id: Long?)
    fun getShopDetailsById(id: Long?): ShopDTO?
}