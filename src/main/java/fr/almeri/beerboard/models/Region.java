package fr.almeri.beerboard.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="region")
public class Region {

    @Id
    @Column(name = "nom_region")
    private String nomRegion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nom_pays")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //Evite erreurs si attributs mal défini
    private Pays pays;

    public Region(){}

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Region{" +
                "nomRegion='" + nomRegion + '\'' +
                '}';
    }
}
