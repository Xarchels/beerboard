package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Biere;
import fr.almeri.beerboard.models.BiereId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BiereRepository extends CrudRepository<Biere, BiereId> {

    @Query("select distinct b.marque.nomMarque from Biere b order by b.marque.nomMarque asc")
    public ArrayList<String> getMarque();

    @Query("select count(b.version) from Biere b group by b.marque order by b.marque.nomMarque asc")
    public ArrayList<Integer> getNbVersionBiere();

    @Query("select distinct b.tauxAlcool from Biere b order by b.tauxAlcool asc")
    public ArrayList<String> getAlcool();

    @Query("select count(b.version) from Biere b group by b.tauxAlcool order by b.tauxAlcool asc")
    public ArrayList<Integer> getNbBiereAlcool();
}
