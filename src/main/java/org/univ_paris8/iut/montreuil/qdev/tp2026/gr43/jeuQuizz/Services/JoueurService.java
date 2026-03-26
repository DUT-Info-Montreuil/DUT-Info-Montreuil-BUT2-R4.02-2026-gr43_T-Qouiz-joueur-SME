package org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.Services;

import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.Services.interfaces.IJoueurService;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.dtos.JoueurDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.enums.LangueEnum;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.BadRequestException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.ConflictException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.InternalException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JoueurService implements IJoueurService {

    // Simule la persistance en mémoire (à remplacer par un vrai repository)
    private final List<JoueurDTO> joueurs = new ArrayList<>();

    // =========================================================================
    // ajouterJoueur
    // =========================================================================

    @Override
    public JoueurDTO ajouterJoueur(JoueurDTO nouveauJoueur)
            throws BadRequestException, ConflictException, InternalException {

        // --- 1. Validation des données d'entrée ---
        validerJoueur(nouveauJoueur);

        // --- 2. Vérification unicité du pseudo ---
        boolean pseudoDejaExistant = joueurs.stream()
                .anyMatch(j -> j.getPseudo().equalsIgnoreCase(nouveauJoueur.getPseudo()));
        if (pseudoDejaExistant) {
            throw new ConflictException(
                    "Le pseudo \"" + nouveauJoueur.getPseudo() + "\" existe déjà.");
        }

        // --- 3. Persistance ---
        try {
            joueurs.add(nouveauJoueur);
        } catch (Exception e) {
            throw new InternalException(
                    "Erreur lors de l'enregistrement du joueur en base de données.", e);
        }

        return nouveauJoueur;
    }

    // =========================================================================
    // listerJoueurs
    // =========================================================================

    @Override
    public List<JoueurDTO> listerJoueurs()
            throws NotFoundException, InternalException {

        List<JoueurDTO> liste;

        // --- 1. Récupération ---
        try {
            liste = new ArrayList<>(joueurs);
        } catch (Exception e) {
            throw new InternalException(
                    "Erreur lors de la récupération des joueurs en base de données.", e);
        }

        // --- 2. Liste vide ---
        if (liste.isEmpty()) {
            throw new NotFoundException("Aucun joueur enregistré en base de données.");
        }

        // --- 3. Tri alphabétique par pseudo (insensible à la casse) ---
        liste.sort(Comparator.comparing(j -> j.getPseudo().toLowerCase()));

        return liste;
    }

    // =========================================================================
    // Méthode privée de validation
    // =========================================================================

    /**
     * Valide toutes les règles métier sur un JoueurDTO avant persistance.
     *
     * @param joueur le joueur à valider
     * @throws BadRequestException si une règle de validation est violée
     */
    private void validerJoueur(JoueurDTO joueur) throws BadRequestException {

        // Pseudo : ne doit pas être null/vide et ne doit pas commencer par un chiffre
        if (joueur.getPseudo() == null || joueur.getPseudo().isEmpty()) {
            throw new BadRequestException("Le pseudo est obligatoire.");
        }
        if (Character.isDigit(joueur.getPseudo().charAt(0))) {
            throw new BadRequestException(
                    "Le pseudo ne doit pas commencer par un chiffre : \"" + joueur.getPseudo() + "\".");
        }

        // Année de naissance : doit être un entier à exactement 4 chiffres (1000–9999)
        if (joueur.getAnneeNaissance() < 1000 || joueur.getAnneeNaissance() > 9999) {
            throw new BadRequestException(
                    "L'année de naissance doit comporter exactement 4 chiffres : " + joueur.getAnneeNaissance() + ".");
        }

        // Centres d'intérêts : soit vide, soit chaîne séparée par des virgules
        // ne commençant pas et ne finissant pas par une virgule
        if (joueur.getCentresInterets() != null) {
            String libelle = joueur.getCentresInterets().getLibelle();
            if (libelle != null && !libelle.isEmpty()) {
                if (libelle.startsWith(",")) {
                    throw new BadRequestException(
                            "Les centres d'intérêts ne doivent pas commencer par une virgule.");
                }
                if (libelle.endsWith(",")) {
                    throw new BadRequestException(
                            "Les centres d'intérêts ne doivent pas se terminer par une virgule.");
                }
            }
        }

        // Langue préférée : doit être non null et avoir un id compris entre 1 et 5
        if (joueur.getLanguePreferee() == null) {
            throw new BadRequestException("La langue préférée est obligatoire.");
        }
        int idLangue = joueur.getLanguePreferee().getId();
        if (idLangue < 1 || idLangue > 5) {
            throw new BadRequestException(
                    "L'id de langue doit être compris entre 1 et 5, reçu : " + idLangue + ".");
        }
    }
}