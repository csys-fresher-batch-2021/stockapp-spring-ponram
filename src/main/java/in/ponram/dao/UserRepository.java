package in.ponram.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.ponram.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	Optional<User> findByUsername(@Param("userName") String userName);

}
