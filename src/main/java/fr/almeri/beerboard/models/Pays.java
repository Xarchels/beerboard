package fr.almeri.beerboard.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// Indiquer table correspondant à l'objet Pays
//Relie table dans BDD à la classe.
@Entity
@Table(name="pays")
public class Pays {


    @Id
    @Column(name="nom_pays")
    private String nomPays;

    @Column(name = "consommation")
    private Double consommation;

    @Column(name = "production")
    private Double production;

    public Pays(){

    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public Double getConsommation() {
        return consommation;
    }

    public void setConsommation(Double consommation) {
        this.consommation = consommation;
    }

    public Double getProduction() {
        return production;
    }

    public void setProduction(Double production) {
        this.production = production;
    }

    @Override
    public String toString(){
        return nomPays;
    }
}
