package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Marque;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MarqueRepository extends CrudRepository<Marque, String> {

    @Query("select distinct (m.brasserie.nomBrasserie) from Marque m order by m.brasserie.nomBrasserie asc")
    public ArrayList<String> getBrasserie();

    @Query("select count(m.nomMarque) from Marque m group by m.brasserie.nomBrasserie order by m.brasserie.nomBrasserie asc")
    public ArrayList<Integer> getNbMarqueBrasserie();
}
