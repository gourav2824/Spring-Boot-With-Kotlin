package gourav.example.springbootwithkotlin.service

import gourav.example.springbootwithkotlin.model.Product
import gourav.example.springbootwithkotlin.repository.ProductRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class ProductService(private val productRepository: ProductRepository) {
    fun addProduct(product: Product): Product {
        return productRepository.save(product)
    }

    fun getProduct(id: Int): Product? {
        return productRepository.findById(id).getOrElse { null }
    }

    fun removeProduct(id: Int) {
        productRepository.deleteById(id)
    }

    fun getAllProducts(): MutableList<Product> {
        return productRepository.findAll()
    }
}
