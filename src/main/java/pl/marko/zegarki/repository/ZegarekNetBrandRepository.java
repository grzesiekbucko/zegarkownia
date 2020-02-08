package pl.marko.zegarki.repository;

import org.springframework.data.repository.CrudRepository;
import pl.marko.zegarki.entity.ZegarekNetBrand;

import java.util.List;

public interface ZegarekNetBrandRepository extends CrudRepository<ZegarekNetBrand, String> {

    List<ZegarekNetBrand>findByBrandIn(List<String> brandIdList);

}
