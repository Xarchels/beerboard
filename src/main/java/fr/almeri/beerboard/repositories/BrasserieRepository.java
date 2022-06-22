package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Brasserie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BrasserieRepository extends CrudRepository<Brasserie, String> {

    @Query("select distinct b.region.nomRegion from Brasserie b group by b.region.nomRegion order by count(b.codeBrasserie) desc")
    public ArrayList<String> getRegion();

    @Query("select count(b.codeBrasserie) from Brasserie b group by b.region.nomRegion order by count(b.codeBrasserie) desc")
    public ArrayList<Integer> getNbBrasserieRegion();
}
