package pl.marko.zegarki.repository.Marko;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import pl.marko.zegarki.entity.marko.MarkoProduct;
import pl.marko.zegarki.entity.ProductJoinInterface;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MarkoProductRepository extends JpaRepository<MarkoProduct, String> {

    @Async
    @Query(value = "SELECT kod, product_brand FROM marko_product t WHERE t.shiping = :shiping",
            nativeQuery = true)
    List<MarkoProduct> findProductByShiping(@Param("shiping") String shiping);

    @Async
    @Query(value = "SELECT kod, product_brand FROM marko_product t WHERE t.product_brand = :productBrand",
            nativeQuery = true)
    List<MarkoProduct> findProductByBrand(@Param("productBrand") String brand);

    @Async
    @Query(value = "SELECT kod, product_brand FROM marko_product t WHERE t.shiping = :shiping and t.product_brand = :productBrand",
            nativeQuery = true)
    List<MarkoProduct> findProductByShipingAndProductBrand(@Param("shiping") String shiping, @Param("productBrand") String brand);

    @Query(value = "SELECT p.product_brand AS productBrand, p.kod AS productKod, p.new_price AS newPrice, p.percent_sale AS percentSale, p.shiping AS shiping, \n" +
            "c.zegarek_net_new_price AS zegarekNetNewPrice, \n" +
            "c.percent_sale AS zegarekNetPercentSale, \n" +
            "p.product_link AS markoLink, c.product_link AS zegarekNetLink \n" +
            "FROM marko_product p\n" +
            "JOIN zegarek_net_product c\n" +
            "ON p.kod = c.kod \n" +
            "WHERE p.new_price > c.zegarek_net_new_price", nativeQuery = true)
    List<ProductJoinInterface> findAllDtoBy();

    @Query(value = "SELECT p.product_brand AS productBrand, p.kod AS productKod, p.new_price AS newPrice, p.percent_sale AS percentSale, p.shiping AS shiping, \n" +
            "c.zegarek_net_new_price AS zegarekNetNewPrice, \n" +
            "c.percent_sale AS zegarekNetPercentSale, \n" +
            "p.product_link AS markoLink, c.product_link AS zegarekNetLink \n" +
            "FROM marko_product p\n" +
            "JOIN zegarek_net_product c\n" +
            "ON p.kod = c.kod \n" +
            "WHERE p.new_price > c.zegarek_net_new_price AND p.product_brand = :productBrand", nativeQuery = true)
    List<ProductJoinInterface> findAllComparedProductBy(@Param("productBrand") String brand);

    @Query(value = "SELECT p.product_brand AS productBrand, p.kod AS productKod, p.new_price AS newPrice, p.percent_sale AS percentSale, p.shiping AS shiping, \n" +
            "c.zegarek_net_new_price AS zegarekNetNewPrice, \n" +
            "c.percent_sale AS zegarekNetPercentSale, \n" +
            "p.product_link AS markoLink, c.product_link AS zegarekNetLink \n" +
            "FROM marko_product p\n" +
            "JOIN zegarek_net_product c\n" +
            "ON p.kod = c.kod \n" +
            "WHERE p.new_price > c.zegarek_net_new_price AND p.product_brand = :productBrand AND p.shiping = :shiping", nativeQuery = true)
    List<ProductJoinInterface> findAllComparedProductByShiping(@Param("productBrand") String brand, @Param("shiping") String shiping);

    @Query(value = "SELECT p.product_brand AS productBrand, p.kod AS productKod, p.new_price AS newPrice, p.percent_sale AS percentSale, p.shiping AS shiping, \n" +
            "c.zegarek_net_new_price AS zegarekNetNewPrice, \n" +
            "c.percent_sale AS zegarekNetPercentSale, \n" +
            "p.product_link AS markoLink, c.product_link AS zegarekNetLink \n" +
            "FROM marko_product p\n" +
            "JOIN zegarek_net_product c\n" +
            "ON p.kod = c.kod \n" +
            "WHERE p.new_price > c.zegarek_net_new_price AND c.percent_sale = :percent_sale", nativeQuery = true)
    List<ProductJoinInterface> findAllComparedProductBySale(@Param("percent_sale") BigDecimal percent_sale);
}
