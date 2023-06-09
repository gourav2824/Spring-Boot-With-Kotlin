package gourav.example.springbootwithkotlin.service

import gourav.example.springbootwithkotlin.model.Product
import gourav.example.springbootwithkotlin.repository.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.Optional

internal class ProductServiceTest {

    private val productRepository: ProductRepository = mock(ProductRepository::class.java)
    private val productService: ProductService = ProductService(productRepository)

    @Test
    internal fun `test add product`() {
        val product = Product(1, "Watch", "A cool watch!", 1000.0, "Brown")
        `when`(productRepository.save(product)).thenReturn(product)

        val addedProduct = productService.addProduct(product)

        assertThat(addedProduct).isEqualTo(product)
    }

    @Test
    internal fun `test get product by id`() {
        val productId = 1
        val product = Product(productId, "Watch", "A cool watch!", 1000.0, "Brown")
        `when`(productRepository.findById(productId)).thenReturn(Optional.of(product))

        val actualProduct = productService.getProduct(productId)

        assertThat(actualProduct).isEqualTo(product)
    }
}
