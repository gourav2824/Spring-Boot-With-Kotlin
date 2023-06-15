package gourav.example.springbootwithkotlin.controller

import gourav.example.springbootwithkotlin.model.Product
import gourav.example.springbootwithkotlin.service.ReactiveProductService
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
import reactor.core.publisher.Mono
import java.time.Duration

@RestController
@RequestMapping("/reactive/product")
class ReactiveProductController(private val productService: ReactiveProductService) {

    @PostMapping("/add")
    fun addProduct(@RequestBody product: Product): ResponseEntity<Mono<Product>> {
        return ResponseEntity.ok(productService.addProduct(product))
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: Int): ResponseEntity<Mono<Product>> {
        return ResponseEntity.ok(productService.getProduct(id))
    }

    @PutMapping("/update")
    fun updateProduct(@RequestBody product: Product): ResponseEntity<Mono<Product>> {
        return ResponseEntity.ok(productService.updateProduct(product))
    }

    @DeleteMapping("/{id}")
    fun removeProduct(@PathVariable id: Int): ResponseEntity<Mono<Void>> {
        return ResponseEntity.ok(productService.removeProduct(id))
    }

    @GetMapping
    fun getAllProducts(): ResponseEntity<Flux<Product>> {
        return ResponseEntity.ok(productService.getAllProducts())
    }

    @GetMapping(value = ["/flux"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun getAllProductsFluxStream(): ResponseEntity<Flux<Product>> {
        val products = productService.getAllProducts()
        return ResponseEntity.ok(
            products
                .delayElements(Duration.ofSeconds(1))
                .log()
        )
    }
}
