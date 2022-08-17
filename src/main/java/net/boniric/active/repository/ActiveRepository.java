package net.boniric.active.repository;

import net.boniric.active.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActiveRepository extends CrudRepository<User,Integer> {


}
