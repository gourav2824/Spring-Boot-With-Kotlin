package gourav.example.springbootwithkotlin.repository

import gourav.example.springbootwithkotlin.model.Product
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: MongoRepository<Product, Int>
