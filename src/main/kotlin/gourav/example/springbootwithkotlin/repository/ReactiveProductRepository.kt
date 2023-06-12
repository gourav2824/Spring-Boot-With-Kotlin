package gourav.example.springbootwithkotlin.repository

import gourav.example.springbootwithkotlin.model.Product
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ReactiveProductRepository : ReactiveMongoRepository<Product, Int>
