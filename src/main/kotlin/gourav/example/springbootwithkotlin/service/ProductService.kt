package gourav.example.springbootwithkotlin.service

import gourav.example.springbootwithkotlin.model.Product
import gourav.example.springbootwithkotlin.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(val productRepository: ProductRepository) {
    fun addProduct(product: Product): Product {
        return productRepository.save(product)
    }
}
