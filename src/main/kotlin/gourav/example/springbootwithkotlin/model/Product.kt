package gourav.example.springbootwithkotlin.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("products")
class Product(@Id val id: Int, val name: String, val description: String = "Product", val price: Double, val color: String)
