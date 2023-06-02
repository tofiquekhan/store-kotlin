package service

import com.storesystem.dto.UserDTO

interface UserService {
    fun saveUpdateUser(userDTO: UserDTO?): UserDTO?
    val users: MutableList<Any>?

    fun removeUserDetailsById(id: Long?)
    fun getUserDetailsById(id: Long?): UserDTO?
}