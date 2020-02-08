package pl.marko.zegarki.repository;

import org.springframework.data.repository.CrudRepository;
import pl.marko.zegarki.entity.ZegarekNetBrand;
import pl.marko.zegarki.entity.ZegarekNetProduct;

import java.util.List;

public interface ZegarekNetProductRepository extends CrudRepository<ZegarekNetProduct, String> {

    List<ZegarekNetProduct> findByProductBrand(ZegarekNetBrand brand);


}
