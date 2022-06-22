package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Pays;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

//Extends CrudRepositury<Nom de classe, type de l'ID>
@Repository //Indique que c'est là qu'on fait les requêtes
public interface PaysRepository  extends CrudRepository<Pays, String> {

    @Query("select p.nomPays from Pays p order by p.nomPays asc")
    public ArrayList<String> getListNomPays();

    @Query("select p.consommation from Pays p order by p.nomPays asc")
    public ArrayList<Integer> getListConsPays();

    @Query("select p.production from Pays p order by p.nomPays asc")
    public ArrayList<Integer> getListProdPays();
}
