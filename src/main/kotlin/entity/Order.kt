package entity
import jakarta.persistence.*

@Entity
@Table(name = "orders")
class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null
    private val name: String? = null
    private val phonenumber: Long? = null
    private val price: Double? = null
}