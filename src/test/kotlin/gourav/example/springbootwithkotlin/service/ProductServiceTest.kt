package gourav.example.springbootwithkotlin.service

import gourav.example.springbootwithkotlin.model.Product
import gourav.example.springbootwithkotlin.repository.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito

internal class ProductServiceTest {

    private val productRepository: ProductRepository = Mockito.mock(ProductRepository::class.java)
    private val productService: ProductService = ProductService(productRepository)

    @Test
    internal fun testAddProduct() {
        val product = Product(1, "Watch", "A cool watch!", 1000.0, "Brown")
        Mockito.`when`(productRepository.save(product)).thenReturn(product)

        val addedProduct = productService.addProduct(product)

        assertThat(addedProduct).isEqualTo(product)
    }
}
