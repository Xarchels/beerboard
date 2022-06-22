package fr.almeri.beerboard.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "type")
public class Type {

    @Id
    @Column(name= "no_type")
    private Integer noType;

    @Column(name = "nom_type")
    private String nomType;

    @Column(name = "description")
    private String description;

    @Column(name = "fermentation")
    private String fermentation;

    @Column(name = "commentaire")
    private String commentaire;

    public Type() {

    }

    public Integer getNoType() {
        return noType;
    }

    public void setNoType(Integer noType) {
        this.noType = noType;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFermentation() {
        return fermentation;
    }

    public void setFermentation(String fermentation) {
        this.fermentation = fermentation;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getNoTypeStr() {
        return noType.toString();
    }

    public void setNoTypeStr(String noTypeStr) {
        this.noType = Integer.parseInt(noTypeStr);
    }

    @Override
    public String toString() {
        return nomType;
    }
}
