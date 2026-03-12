package org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.enums;

/**
 * Enumération des langues disponibles pour un joueur.
 * Correspond aux choix proposés dans le menu de création de profil.
 */
public enum LangueEnum {

    FRANCAIS(1, "francais"),
    ENGLISH(2, "english"),
    DEUTCH(3, "deutch"),
    ESPANA(4, "espana"),
    ITALIA(5, "italia");

    private final int id;
    private final String libelle;

    LangueEnum(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    /**
     * Retourne l'enum correspondant à l'id fourni.
     * @param id identifiant (1 à 5)
     * @return LangueEnum correspondant
     * @throws IllegalArgumentException si l'id n'est pas compris entre 1 et 5
     */
    public static LangueEnum fromId(int id) {
        for (LangueEnum langue : values()) {
            if (langue.id == id) {
                return langue;
            }
        }
        throw new IllegalArgumentException("Id de langue invalide : " + id + ". Doit être compris entre 1 et 5.");
    }
}
