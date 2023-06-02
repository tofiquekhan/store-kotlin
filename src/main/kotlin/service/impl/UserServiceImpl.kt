package com.storesystem.service.impl

import com.storesystem.dto.UserDTO
import com.storesystem.entity.User
import com.storesystem.exception.UserNotFoundException
import com.storesystem.repository.UserRepository
import com.storesystem.service.UserService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.function.Function
import java.util.stream.Collectors

@Service
class UserServiceImpl : UserService {
    @Autowired
    private val userRepository: UserRepository? = null

    @Autowired
    private val modelMapper: ModelMapper? = null
    override fun saveUpdateUser(userDTO: UserDTO?): UserDTO? {
        val user: User = modelMapper!!.map(userDTO, User::class.java)
        val savedUser = userRepository!!.saveAndFlush(user)
        return modelMapper.map(savedUser, UserDTO::class.java)
    }

    override val users: MutableList<Any>?
        get() {
            val users =
                userRepository!!.findAll() as List<User?>
            return users.stream().map(Function<User?, Any> { user: User? ->
                modelMapper!!.map(
                    user,
                    UserDTO::class.java
                )
            }).collect(Collectors.toList<Any>())
        }

    override fun removeUserDetailsById(id: Long?) {
        getUserDetailsById(id)
        userRepository!!.deleteById(id)
    }

    override fun getUserDetailsById(id: Long?): UserDTO? {
        val userOptional = userRepository!!.findById(id)
        if (!userOptional.isPresent) {
            throw UserNotFoundException("user not found with id ::$id")
        }
        val user = userOptional.get()
        return modelMapper!!.map(user, UserDTO::class.java)
    }
}