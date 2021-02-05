package pl.lenistwo.apichallange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.lenistwo.apichallange.exception.CurrencyNotFoundException;
import pl.lenistwo.apichallange.model.pagination.Pagination;
import pl.lenistwo.apichallange.model.product.Currency;
import pl.lenistwo.apichallange.model.request.CurrencyRequest;
import pl.lenistwo.apichallange.model.response.CurrencyResponse;
import pl.lenistwo.apichallange.model.response.PageableResponse;
import pl.lenistwo.apichallange.model.response.PriceConvertResponse;
import pl.lenistwo.apichallange.repository.CurrencyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    public static final double DEFAULT_COURSE = 1.0;

    private final CurrencyRepository currencyRepository;

    public PageableResponse<List<Currency>> getAllCurrencies(int page, int pageSize) {
        var pageResponse = currencyRepository.findAll(PageRequest.of(page, pageSize));
        var pagination = new Pagination(page, pageSize, pageResponse.getTotalElements(), pageResponse.getTotalPages());
        return new PageableResponse<>(pageResponse.getContent(), HttpStatus.OK.value(), pagination);
    }

    public PriceConvertResponse convertPrice(double price, String toCurrency) {
        return new PriceConvertResponse(HttpStatus.OK.value(), price * getCourseByCurrencyCode(toCurrency), toCurrency);
    }

    public double getCourseByCurrencyCode(String code) {
        var course = currencyRepository.findByCode(code);
        return course != null ? course : DEFAULT_COURSE;
    }

    public CurrencyResponse getCurrencyById(long currencyId) {
        var currency = currencyRepository.findById(currencyId).orElseThrow(CurrencyNotFoundException::new);
        return new CurrencyResponse(HttpStatus.OK.value(), currency);
    }

    public ResponseEntity<Void> createCurrency(CurrencyRequest currencyRequest) {
        var currency = new Currency(currencyRequest.getCode(), currencyRequest.getCourse());
        currencyRepository.save(currency);
        return ResponseEntity.ok().build();
    }

    public CurrencyResponse updateCurrency(Currency currency) {
        if (!currencyRepository.existsById(currency.getId())) throw new CurrencyNotFoundException();
        currencyRepository.save(currency);
        return new CurrencyResponse(HttpStatus.OK.value(), currency);
    }

    public ResponseEntity<Void> deleteCurrency(long currencyId) {
        currencyRepository.deleteById(currencyId);
        return ResponseEntity.noContent().build();
    }
}
