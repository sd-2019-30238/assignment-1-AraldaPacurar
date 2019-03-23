package furnitureDeals.furnituredeals.dao;

import furnitureDeals.furnituredeals.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RoleDAO extends CrudRepository<Role, Integer> {
}
