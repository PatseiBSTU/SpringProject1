package by.patsei.springproject1.repository;

import by.patsei.springproject1.entity.UserCredentials;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserCredentialsRepository extends CrudRepository<UserCredentials, Long> {
	
	Optional<UserCredentials> findByLogin(String login);
	
}
