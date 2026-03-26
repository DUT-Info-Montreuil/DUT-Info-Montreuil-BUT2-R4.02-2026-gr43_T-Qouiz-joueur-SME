package org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.dtos;

/**
 * DTO représentant les centres d'intérêts d'un joueur.
 * Le libellé est soit vide, soit une chaîne de caractères séparés par des virgules
 * et ne finissant pas par une virgule.
 * Exemple valide : "football, nature, sport, voiture"
 */
public class CentresInteretsDTO {

    /** Libellé des centres d'intérêts, séparés par des virgules. Peut-être vide. */
    private String libelle;

    public CentresInteretsDTO() {
        this.libelle = "";
    }

    public CentresInteretsDTO(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Vérifie que le libellé respecte le format métier :
     * soit vide, soit une chaîne ne commençant pas et ne finissant pas par une virgule.
     * @return true si valide, false sinon
     */
    public boolean isValide() {
        if (libelle == null || libelle.isEmpty()) return true;
        return !libelle.startsWith(",") && !libelle.endsWith(",");
    }

    @Override
    public String toString() {
        return "CentresInteretsDTO{libelle='" + libelle + "'}";
    }
}