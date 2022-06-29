package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u where u.login = :login")
    public User findByLogin(@Param("login") String login);
}
