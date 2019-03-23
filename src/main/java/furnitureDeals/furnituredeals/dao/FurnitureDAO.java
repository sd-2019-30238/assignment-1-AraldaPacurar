package furnitureDeals.furnituredeals.dao;

import furnitureDeals.furnituredeals.model.Furniture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FurnitureDAO extends CrudRepository<Furniture, Integer> {
}
