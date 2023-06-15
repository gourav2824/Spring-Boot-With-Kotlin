package gourav.example.springbootwithkotlin.service

import gourav.example.springbootwithkotlin.model.Product
import gourav.example.springbootwithkotlin.repository.ReactiveProductRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

internal class ReactiveProductServiceTest {

    private val productRepository: ReactiveProductRepository = mock(ReactiveProductRepository::class.java)
    private val productService: ReactiveProductService = ReactiveProductService(productRepository)

    @Test
    internal fun `should fetch flux of all products`() {
        `when`(productRepository.findAll()).thenReturn(getFluxOfProducts())

        val productFlux = productService.getAllProducts()

        StepVerifier.create(productFlux)
            .expectNextMatches { product -> product.name == "Watch" }
            .expectNextMatches { product -> product.color == "Gold" }
            .expectNextCount(1)
            .verifyComplete()
    }

    private fun getFluxOfProducts(): Flux<Product> {
        return Flux.just(
            Product(1, "Watch", "A cool watch!", 1000.0, "Brown"),
            Product(2, "myPhone", "myPhone 15 Pro", 100000.0, "Gold"),
            Product(3, "Dell Laptop", "i5 Processor", 70000.0, "Black")
        ).log()
    }
}
