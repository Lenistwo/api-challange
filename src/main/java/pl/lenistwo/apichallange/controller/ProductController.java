package pl.lenistwo.apichallange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lenistwo.apichallange.model.product.Product;
import pl.lenistwo.apichallange.model.response.PageableResponse;
import pl.lenistwo.apichallange.model.response.PriceConvertResponse;
import pl.lenistwo.apichallange.model.response.RatingResponse;
import pl.lenistwo.apichallange.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public PageableResponse<List<Product>> getAllProducts(@RequestParam(defaultValue = "5", required = false) int page,
                                                          @RequestParam(defaultValue = "5", required = false) int pageSize,
                                                          @RequestParam(defaultValue = "sek", required = false) String currencyCode) {
        return productService.getAllProducts(currencyCode, page, pageSize);
    }

    @GetMapping("/price")
    public PriceConvertResponse toPrice(@RequestParam double price, @RequestParam String toCurrency){
        return productService.convertPrice(price, toCurrency);
    }

    @GetMapping("/product-rating")
    public RatingResponse getProductRating(@RequestParam long productId){
        return productService.getProductRating(productId);
    }
}

