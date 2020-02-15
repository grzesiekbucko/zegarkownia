package pl.marko.zegarki.repository.ZegarekNet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.marko.zegarki.entity.ZegarekNet.ZegarekNetBrand;
import pl.marko.zegarki.entity.ZegarekNet.ZegarekNetProduct;

import java.util.List;

@Repository
public interface ZegarekNetProductRepository extends JpaRepository<ZegarekNetProduct, String> {

    List<ZegarekNetProduct> findByProductBrand(ZegarekNetBrand brand);

}
