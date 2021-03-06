package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends CrudRepository<Region, String> {
}
