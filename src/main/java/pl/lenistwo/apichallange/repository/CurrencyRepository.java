package pl.lenistwo.apichallange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.lenistwo.apichallange.model.product.Currency;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    @Query("select course from Currency where code=:code")
    Optional<Double> findByCode(@Param("code") String code);
}
