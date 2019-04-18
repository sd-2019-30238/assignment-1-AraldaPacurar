package furnitureDeals.furnituredeals.dao;

import furnitureDeals.furnituredeals.model.Furniture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface FurnitureDAO extends CrudRepository<Furniture, Integer> {

    List<Furniture> findByName(String name);
    List<Furniture> findByPrice(int price);
    List<Furniture> findByFurnitureTypeId(int furnitureTypeId);
}
