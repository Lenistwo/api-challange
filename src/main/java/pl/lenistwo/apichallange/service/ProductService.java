package pl.lenistwo.apichallange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.lenistwo.apichallange.exception.ProductNotFoundException;
import pl.lenistwo.apichallange.model.pagination.Pagination;
import pl.lenistwo.apichallange.model.product.Product;
import pl.lenistwo.apichallange.model.request.ProductRequest;
import pl.lenistwo.apichallange.model.response.PageableResponse;
import pl.lenistwo.apichallange.model.response.ProductResponse;
import pl.lenistwo.apichallange.model.response.RatingResponse;
import pl.lenistwo.apichallange.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CurrencyService currencyService;
    private final ProductRepository productRepository;

    public PageableResponse<List<Product>> getAllProducts(String currencyCode, int page, int pageSize) {
        var pageResponse = productRepository.findAll(PageRequest.of(page, pageSize));
        var products = pageResponse.getContent().stream()
                                                            .peek(product -> product.setPrice(product.getPrice() * currencyService.getCourseByCurrencyCode(currencyCode)))
                                                            .collect(Collectors.toList());

        var pagination = new Pagination(page, pageSize, pageResponse.getTotalElements(), pageResponse.getTotalPages());
        return new PageableResponse<>(products, HttpStatus.OK.value(), pagination);
    }

    public RatingResponse getProductRating(long productId) {
        var ratings = productRepository.findRateByProductId(productId).orElseThrow(ProductNotFoundException::new);
        return new RatingResponse(HttpStatus.OK.value(), ratings);
    }

    public ProductResponse getByProductId(long productId) {
        var product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        return new ProductResponse(HttpStatus.OK.value(), product);
    }

    public ProductResponse updateProduct(Product product) {
        if (!productRepository.existsById(product.getId())) throw new ProductNotFoundException();
        productRepository.save(product);
        return new ProductResponse(HttpStatus.ACCEPTED.value(), product);
    }

    public ResponseEntity<Void> deleteProductById(long productId) {
        productRepository.deleteById(productId);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> createProduct(ProductRequest productRequest) {
        var product = new Product(productRequest.getName(), productRequest.getDescription(), productRequest.getPrice(), productRequest.getRating());
        productRepository.save(product);
        return ResponseEntity.accepted().build();
    }
}
