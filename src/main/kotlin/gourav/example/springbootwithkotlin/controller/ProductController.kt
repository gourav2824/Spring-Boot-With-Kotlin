package gourav.example.springbootwithkotlin.controller

import gourav.example.springbootwithkotlin.model.Product
import gourav.example.springbootwithkotlin.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

@RestController
@RequestMapping("/product")
class ProductController(private val productService: ProductService) {

    @PostMapping("/add")
    fun addProduct(@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity.ok(productService.addProduct(product))
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: Int): ResponseEntity<Product> {
        val product = productService.getProduct(id) ?: return ResponseEntity(HttpStatus.BAD_REQUEST)
        return ResponseEntity.ok(product)
    }

    @PutMapping("/update")
    fun updateProduct(@RequestBody product: Product): ResponseEntity<Product> {
        val updatedProduct = productService.updateProduct(product) ?: return ResponseEntity(HttpStatus.BAD_REQUEST)
        return ResponseEntity.ok(updatedProduct)
    }

    @DeleteMapping("/{id}")
    fun removeProduct(@PathVariable id: Int) {
        productService.removeProduct(id)
    }

    @GetMapping
    fun getAllProducts(): ResponseEntity<Array<Product>> {
        return ResponseEntity.ok(productService.getAllProducts())
    }

    @GetMapping(value = ["/flux"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun getAllProductsFluxStream(): Flux<Product> {
        val products = productService.getAllProducts()
        return Flux.just(*products)
            .delayElements(Duration.ofSeconds(1))
            .log()
    }
}
