package com.storesystem

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
object StoreManagementApplication {
    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(StoreManagementApplication::class.java, *args)
    }
}
