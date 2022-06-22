package fr.almeri.beerboard.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@IdClass(BiereId.class)
@Table(name = "biere")
public class Biere {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nom_marque")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Marque marque;

    @Id
    @Column(name = "version")
    private String version;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="no_type")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Type type;

    @Column(name = "couleur_biere")
    private String couleurBiere;

    @Column(name = "taux_alcool")
    private Double tauxAlcool;

    @Column(name = "caracteristiques")
    private String caracteristiques;

    public Biere(){

    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCouleurBiere() {
        return couleurBiere;
    }

    public void setCouleurBiere(String couleurBiere) {
        this.couleurBiere = couleurBiere;
    }

    public Double getTauxAlcool() {
        return tauxAlcool;
    }

    public void setTauxAlcool(Double tauxAlcool) {
        this.tauxAlcool = tauxAlcool;
    }

    public String getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }


}
