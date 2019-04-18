package furnitureDeals.furnituredeals.dao;

import furnitureDeals.furnituredeals.model.FurnitureType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface FurnitureTypeDAO extends CrudRepository<FurnitureType, Integer> {

    List<FurnitureType> findByName(String name);
}
