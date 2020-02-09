package pl.marko.zegarki.repository.Marko;

import org.springframework.data.repository.CrudRepository;
import pl.marko.zegarki.entity.marko.MarkoBrand;

import java.util.List;

public interface MarkoBrandRepository extends CrudRepository<MarkoBrand, String> {

    List<MarkoBrand> findByBrandIn(List<String> brandIdList);

}
