package pl.marko.zegarki.repository.Marko;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import pl.marko.zegarki.entity.ProductJoin;
import pl.marko.zegarki.entity.marko.MarkoProduct;

import java.util.List;

@Repository
public interface MarkoProductRepository extends CrudRepository<MarkoProduct, String> {

    @Async
    @Query(value = "SELECT kod FROM marko_product t WHERE t.shiping = :shiping",
            nativeQuery = true)
    List<MarkoProduct> findProductByShiping(@Param("shiping") String shiping);

 //   @Async
 //   @Query(value = "SELECT marko_product.kod, marko_product.new_price, marko_product.percent_sale, marko_product.shiping, zegarek_net_product.zegarek_net_new_price FROM marko_product INNER JOIN zegarek_net_product ON marko_product.kod=zegarek_net_product.kod WHERE marko_product.new_price != zegarek_net_product.zegarek_net_new_price",
//            nativeQuery = true)
//    List<MarkoProduct> findProductByKod();

    @Async
    @Query(value = "SELECT NEW pl.marko.zegarki.entity.ProductJoin(p.kod, p.new_price, p.new_price) FROM MarkoProduct p", nativeQuery = true)
    List<ProductJoin> join();

}
