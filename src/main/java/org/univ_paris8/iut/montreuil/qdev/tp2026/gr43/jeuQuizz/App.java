package org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz;

import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.Services.JoueurService;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.Services.interfaces.IJoueurService;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.dtos.CentresInteretsDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.dtos.JoueurDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.enums.LangueEnum;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.BadRequestException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.ConflictException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.InternalException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.NotFoundException;

import java.util.List;

public class App {

    public static void main(String[] args) {

        IJoueurService joueurService = new JoueurService();

        // --- Ajout de joueurs valides ---
        ajouterJoueur(joueurService, "Minh",   "MinhatorDu93", 2002, "football,sport",   LangueEnum.FRANCAIS);
        ajouterJoueur(joueurService, "Sasha",  "SashaPopB",    2001, "musique,cinema",   LangueEnum.ENGLISH);
        ajouterJoueur(joueurService, "Yohann", "Yohann93",     2000, "",                 LangueEnum.FRANCAIS);

        // --- Tentative d'ajout avec pseudo déjà existant ---
        ajouterJoueur(joueurService, "Alice", "MinhatorDu93", 1999, "sport", LangueEnum.ESPANA);

        // --- Tentative d'ajout avec pseudo commençant par un chiffre ---
        ajouterJoueur(joueurService, "Bob", "1InvalidPseudo", 2003, "jeux", LangueEnum.ENGLISH);

        // --- Tentative d'ajout avec année invalide ---
        ajouterJoueur(joueurService, "Bob", "BobLeJoueur", 200, "jeux", LangueEnum.ENGLISH);

        // --- Tentative d'ajout avec centres d'intérêts mal formés ---
        ajouterJoueur(joueurService, "Bob", "BobLeJoueur", 2003, "football,sport,", LangueEnum.ENGLISH);

        // --- Lister tous les joueurs ---
        System.out.println("\n=== Liste des joueurs (ordre alphabétique) ===");
        try {
            List<JoueurDTO> liste = joueurService.listerJoueurs();
            for (JoueurDTO j : liste) {
                System.out.println("  - " + j.getPseudo()
                        + " | " + j.getPrenom()
                        + " | " + j.getAnneeNaissance()
                        + " | centres : " + j.getCentresInterets().getLibelle()
                        + " | langue : " + j.getLanguePreferee().getLibelle());
            }
        } catch (NotFoundException e) {
            System.out.println("[NOT FOUND] " + e.getMessage());
        } catch (InternalException e) {
            System.out.println("[ERREUR INTERNE] " + e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // Méthode utilitaire pour tester ajouterJoueur et afficher le résultat
    // -------------------------------------------------------------------------
    private static void ajouterJoueur(IJoueurService service, String prenom, String pseudo,
                                      int annee, String centres, LangueEnum langue) {
        JoueurDTO joueur = new JoueurDTO(
                prenom,
                pseudo,
                annee,
                new CentresInteretsDTO(centres),
                langue
        );
        try {
            JoueurDTO cree = service.ajouterJoueur(joueur);
            System.out.println("[OK] Joueur ajouté : " + cree.getPseudo());
        } catch (BadRequestException e) {
            System.out.println("[BAD REQUEST] " + e.getMessage());
        } catch (ConflictException e) {
            System.out.println("[CONFLIT] " + e.getMessage());
        } catch (InternalException e) {
            System.out.println("[ERREUR INTERNE] " + e.getMessage());
        }
    }
}