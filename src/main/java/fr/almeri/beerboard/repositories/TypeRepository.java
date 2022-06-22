package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends CrudRepository<Type, String> {
}
