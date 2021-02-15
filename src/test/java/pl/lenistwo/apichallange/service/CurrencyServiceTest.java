package pl.lenistwo.apichallange.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.lenistwo.apichallange.model.response.PriceConvertResponse;
import pl.lenistwo.apichallange.repository.CurrencyRepository;

import static org.mockito.Mockito.when;

class CurrencyServiceTest {

    private static final String NEK_CURRENCY = "nek";
    public static final Double NEK_COURSE = 2.0;
    public static final Double DEFAULT_PRICE = 1.0;
    public static final int SUCCESS_CODE = 200;

    @InjectMocks
    private CurrencyService currencyService;

    @Mock
    private CurrencyRepository currencyRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldConvertToGivenPrice() {
        when(currencyRepository.findByCode(NEK_CURRENCY)).thenReturn(NEK_COURSE);
        var convertedPrice = currencyService.convertPrice(DEFAULT_PRICE, NEK_CURRENCY);
        var priceConvertResponse = new PriceConvertResponse(SUCCESS_CODE, NEK_COURSE * DEFAULT_PRICE, NEK_CURRENCY);
        Assertions.assertEquals(priceConvertResponse, convertedPrice);
    }

    @Test
    void shouldConvertToDefaultPrice() {
        when(currencyRepository.findByCode(NEK_CURRENCY)).thenReturn(null);
        var convertedPrice = currencyService.convertPrice(DEFAULT_PRICE, NEK_CURRENCY);
        var priceConvertResponse = new PriceConvertResponse(SUCCESS_CODE, DEFAULT_PRICE, NEK_CURRENCY);
        Assertions.assertEquals(priceConvertResponse, convertedPrice);
    }
}
