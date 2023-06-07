package gourav.example.springbootwithkotlin.service

import gourav.example.springbootwithkotlin.model.Product
import gourav.example.springbootwithkotlin.repository.ProductRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class ProductService(val productRepository: ProductRepository) {
    fun addProduct(product: Product): Product {
        return productRepository.save(product)
    }

    fun getProduct(id: Int): Product? {
        return productRepository.findById(id).getOrElse { null }
    }

    fun getAllProducts(): MutableList<Product> {
        return productRepository.findAll()
    }
}
