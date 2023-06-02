package rest

import com.storesystem.dto.UserDTO
import response.ResponseHandler
import service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController {
    @Autowired
    private val userService: UserService? = null
    @PostMapping
    fun saveUpdateUser(@RequestBody userDTO: UserDTO?): ResponseEntity<Any> {
        return ResponseHandler.generateResponse("success", HttpStatus.OK, userService.saveUpdateUser(userDTO))
    }

    @get:GetMapping
    val users: ResponseEntity<Any>
        get() = ResponseHandler.generateResponse("success", HttpStatus.OK, userService.getUsers())

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long?): ResponseEntity<Any> {
        return ResponseHandler.generateResponse("success", HttpStatus.OK, userService.getUserDetailsById(id))
    }

    @PutMapping
    fun updateUser(@RequestBody userDTO: UserDTO?): ResponseEntity<Any> {
        val updateUser: UserDTO = userService.saveUpdateUser(userDTO)
        return ResponseHandler.generateResponse("success", HttpStatus.OK, updateUser.getId())
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long?): ResponseEntity<Any> {
        userService.getUserDetailsById(id)
        return ResponseHandler.generateResponse("User Deleted Successfully", HttpStatus.OK, null)
    }
}