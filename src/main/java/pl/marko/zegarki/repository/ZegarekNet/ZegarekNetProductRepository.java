package pl.marko.zegarki.repository.ZegarekNet;

import org.springframework.data.repository.CrudRepository;
import pl.marko.zegarki.entity.ZegarekNet.ZegarekNetBrand;
import pl.marko.zegarki.entity.ZegarekNet.ZegarekNetProduct;

import java.util.List;

public interface ZegarekNetProductRepository extends CrudRepository<ZegarekNetProduct, String> {

    List<ZegarekNetProduct> findByProductBrand(ZegarekNetBrand brand);


}
