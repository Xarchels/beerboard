package fr.almeri.beerboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "marque")
public class Marque {

    @Id
    @Column(name = "nom_marque")
    private String nomMarque;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="code_brasserie")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Brasserie brasserie;

    public Marque() {}

    public Marque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public String getNomMarque() {
        return nomMarque;
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public Brasserie getBrasserie() {
        return brasserie;
    }

    public void setBrasserie(Brasserie brasserie) {
        this.brasserie = brasserie;
    }
}
