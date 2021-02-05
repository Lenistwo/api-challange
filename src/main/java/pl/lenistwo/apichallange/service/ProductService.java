package pl.lenistwo.apichallange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.lenistwo.apichallange.model.pagination.Pagination;
import pl.lenistwo.apichallange.model.product.Product;
import pl.lenistwo.apichallange.model.response.PageableResponse;
import pl.lenistwo.apichallange.model.response.PriceConvertResponse;
import pl.lenistwo.apichallange.model.response.RatingResponse;
import pl.lenistwo.apichallange.repository.CurrencyRepository;
import pl.lenistwo.apichallange.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    public static final double DEFAULT_COURSE = 1.0;

    private final ProductRepository productRepository;
    private final CurrencyRepository currencyRepository;

    public PageableResponse<List<Product>> getAllProducts(String currencyCode, int page, int pageSize) {
        var pageResponse = productRepository.findAll(PageRequest.of(page, pageSize));

        var products = pageResponse.getContent().stream()
                                                            .peek(product -> product.setPrice(product.getPrice() * getCourseByCurrencyCode(currencyCode)))
                                                            .collect(Collectors.toList());

        var pagination = new Pagination(page, pageSize, pageResponse.getTotalElements(), pageResponse.getTotalPages());
        return new PageableResponse<>(products, HttpStatus.OK.value(), pagination);
    }

    public PriceConvertResponse convertPrice(double price, String toCurrency) {
        return new PriceConvertResponse(HttpStatus.OK.value(),price * getCourseByCurrencyCode(toCurrency), toCurrency);
    }


    public RatingResponse getProductRating(long productId) {
        var ratings = productRepository.findRateByProductId(productId);
        return new RatingResponse(HttpStatus.OK.value(), ratings);
    }

    private double getCourseByCurrencyCode(String code) {
        return currencyRepository.findByCode(code).orElse(DEFAULT_COURSE);
    }
}
