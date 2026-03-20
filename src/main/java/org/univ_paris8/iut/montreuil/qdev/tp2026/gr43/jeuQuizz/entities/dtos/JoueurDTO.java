package org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.dtos;

import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.enums.LangueEnum;

/**
 * DTO représentant un joueur du jeu de Quizz.
 *
 * Contraintes métier :
 * - Le prénom est obligatoire.
 * - Le pseudo doit être unique et ne doit pas commencer par un chiffre.
 * - L'année de naissance doit comporter exactement 4 chiffres.
 * - Les centres d'intérêts sont soit vides, soit une chaîne séparée par des virgules
 *   ne finissant pas par une virgule.
 * - La langue préférée est obligatoire (id entre 1 et 5).
 */
public class JoueurDTO {

    private String prenom;
    private String pseudo;
    private int anneeNaissance;
    private CentresInteretsDTO centresInterets;
    private LangueEnum languePreferee;

    public JoueurDTO() {
    }

    public JoueurDTO(String prenom, String pseudo, int anneeNaissance,
                     CentresInteretsDTO centresInterets, LangueEnum languePreferee) {
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.anneeNaissance = anneeNaissance;
        this.centresInterets = centresInterets;
        this.languePreferee = languePreferee;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getAnneeNaissance() {
        return anneeNaissance;
    }

    public void setAnneeNaissance(int anneeNaissance) {
        this.anneeNaissance = anneeNaissance;
    }

    public CentresInteretsDTO getCentresInterets() {
        return centresInterets;
    }

    public void setCentresInterets(CentresInteretsDTO centresInterets) {
        this.centresInterets = centresInterets;
    }

    public LangueEnum getLanguePreferee() {
        return languePreferee;
    }

    public void setLanguePreferee(LangueEnum languePreferee) {
        this.languePreferee = languePreferee;
    }

    @Override
    public String toString() {
        return "JoueurDTO{" +
                "prenom='" + prenom + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", anneeNaissance=" + anneeNaissance +
                ", centresInterets=" + centresInterets +
                ", languePreferee=" + languePreferee +
                '}';
    }
}
