package entity
import jakarta.persistence.*

@Entity
@Table(name = "shops")
class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null
    private val name: String? = null
    private val phonenumber: Long? = null
    private val address: String? = null
}