package pl.marko.zegarki.repository;

import org.springframework.data.repository.CrudRepository;
import pl.marko.zegarki.entity.ZegarekNetProduct;

public interface ZegarekNetProductRepository extends CrudRepository<ZegarekNetProduct, String> {
}
