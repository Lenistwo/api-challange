package pl.lenistwo.apichallange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lenistwo.apichallange.model.product.Product;
import pl.lenistwo.apichallange.model.request.ProductRequest;
import pl.lenistwo.apichallange.model.response.PageableResponse;
import pl.lenistwo.apichallange.model.response.PriceConvertResponse;
import pl.lenistwo.apichallange.model.response.ProductResponse;
import pl.lenistwo.apichallange.model.response.RatingResponse;
import pl.lenistwo.apichallange.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public PageableResponse<List<Product>> findAllProducts(@RequestParam(defaultValue = "0", required = false) int page,
                                                           @RequestParam(defaultValue = "5", required = false) int pageSize,
                                                           @RequestParam(defaultValue = "sek", required = false) String currencyCode) {
        return productService.getAllProducts(currencyCode, page, pageSize);
    }

    @GetMapping("/{productId}")
    public ProductResponse findProductById(@PathVariable long productId){
        return productService.getByProductId(productId);
    }

    @PutMapping
    public ProductResponse updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @GetMapping("/product-rating")
    public RatingResponse findProductRating(@RequestParam long productId){
        return productService.getProductRating(productId);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable long productId){
        return productService.deleteProductById(productId);
    }
}

