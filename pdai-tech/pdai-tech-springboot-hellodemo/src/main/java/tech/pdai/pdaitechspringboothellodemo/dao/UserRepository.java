package tech.pdai.pdaitechspringboothellodemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.pdai.pdaitechspringboothellodemo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
