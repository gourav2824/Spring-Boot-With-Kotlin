package gourav.example.springbootwithkotlin.service

import gourav.example.springbootwithkotlin.model.Product
import gourav.example.springbootwithkotlin.repository.ReactiveProductRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ReactiveProductService(private val productRepository: ReactiveProductRepository) {

    fun addProduct(product: Product): Mono<Product> {
        return productRepository.save(product)
    }

    fun getProduct(id: Int): Mono<Product> {
        return productRepository.findById(id)
    }

    fun updateProduct(product: Product): Mono<Product> {
        return productRepository.existsById(product.id)
            .filter { exists -> exists }
            .then(productRepository.save(product))
    }

    fun removeProduct(id: Int): Mono<Void> {
        return productRepository.deleteById(id)
    }

    fun getAllProducts(): Flux<Product> {
        return productRepository.findAll()
    }
}
