package tech.pdai.pdaitechspringboothellodemo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.pdai.pdaitechspringboothellodemo.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
