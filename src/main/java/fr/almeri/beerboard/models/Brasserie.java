package fr.almeri.beerboard.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "brasserie")
public class Brasserie {

    @Id
    @Column(name = "code_brasserie")
    private String codeBrasserie;

    @Column(name = "nom_brasserie")
    private String nomBrasserie;

    @Column(name = "ville")
    private String ville;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="region")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;

    public Brasserie() {

    }

    public String getCodeBrasserie() {
        return codeBrasserie;
    }

    public void setCodeBrasserie(String codeBrasserie) {
        this.codeBrasserie = codeBrasserie;
    }

    public String getNomBrasserie() {
        return nomBrasserie;
    }

    public void setNomBrasserie(String nomBrasserie) {
        this.nomBrasserie = nomBrasserie;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
