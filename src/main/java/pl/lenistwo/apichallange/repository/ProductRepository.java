package pl.lenistwo.apichallange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.lenistwo.apichallange.model.product.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select rate from Rating where product.id=:id")
    Optional<List<Integer>> findRateByProductId(@Param("id") long id);
}
