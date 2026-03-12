package org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.dtos;

import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.enums.LangueEnum;
/**
 * DTO représentant la langue préférée d'un joueur.
 */
public class LangueDTO {

    private int id;
    private String libelle;

    public LangueDTO() {
    }

    public LangueDTO(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    /**
     * Construit un LangueDTO depuis l'énumération correspondante.
     * @param langueEnum l'enum de langue
     * @return LangueDTO peuplé
     */
    public static LangueDTO fromEnum(LangueEnum langueEnum) {
        return new LangueDTO(langueEnum.getId(), langueEnum.getLibelle());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "LangueDTO{id=" + id + ", libelle='" + libelle + "'}";
    }
}
