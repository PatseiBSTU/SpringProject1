package by.patsei.springproject1.repository;


import by.patsei.springproject1.entity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {
	
	Optional<Token> findByToken(String token);
	
}
