package gourav.example.springbootwithkotlin.controller

import gourav.example.springbootwithkotlin.model.Product
import gourav.example.springbootwithkotlin.service.ProductService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductController(val productService: ProductService) {

    @PostMapping("/add")
    fun addProduct(@RequestBody product: Product): Product {
        return productService.addProduct(product)
    }
}
