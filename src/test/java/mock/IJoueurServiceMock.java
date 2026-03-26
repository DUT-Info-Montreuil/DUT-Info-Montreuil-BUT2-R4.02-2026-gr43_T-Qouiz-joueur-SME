package mock;

import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.Services.interfaces.IJoueurService;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.dtos.CentresInteretsDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.dtos.JoueurDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.BadRequestException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.ConflictException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.InternalException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Mock manuel de IJoueurService pour les tests unitaires.
 * Simule la couche service sans dépendance externe.
 *
 * Logique de validation implémentée :
 * - Pseudo ne commence pas par un chiffre
 * - Année de naissance sur exactement 4 chiffres
 * - Centres d'intérêts : vide ou ne commençant/finissant pas par une virgule
 * - Langue non nulle
 * - Pseudo unique (sensible à la casse)
 */
public class IJoueurServiceMock implements IJoueurService {

    /** Stockage en mémoire des joueurs ajoutés. */
    private final List<JoueurDTO> joueurs = new ArrayList<>();

    // =========================================================
    // ajouterJoueur
    // =========================================================

    @Override
    public JoueurDTO ajouterJoueur(JoueurDTO nouveauJoueur)
            throws BadRequestException, ConflictException, InternalException {

        validerJoueur(nouveauJoueur);
        verifierPseudoUnique(nouveauJoueur.getPseudo());

        joueurs.add(nouveauJoueur);
        return nouveauJoueur;
    }

    // =========================================================
    // listerJoueurs
    // =========================================================

    @Override
    public List<JoueurDTO> listerJoueurs()
            throws NotFoundException, InternalException {

        if (joueurs.isEmpty()) {
            throw new NotFoundException("Aucun joueur enregistré.");
        }

        List<JoueurDTO> liste = new ArrayList<>(joueurs);
        liste.sort(Comparator.comparing(JoueurDTO::getPseudo));
        return liste;
    }

    // =========================================================
    // Méthodes privées de validation
    // =========================================================

    /**
     * Valide toutes les règles métier sur le joueur.
     * @throws BadRequestException si une règle est violée
     */
    private void validerJoueur(JoueurDTO joueur) throws BadRequestException {

        // Pseudo ne commence pas par un chiffre
        if (joueur.getPseudo() == null || joueur.getPseudo().isEmpty()) {
            throw new BadRequestException("Le pseudo est obligatoire.");
        }
        if (Character.isDigit(joueur.getPseudo().charAt(0))) {
            throw new BadRequestException(
                    "Le pseudo ne doit pas commencer par un chiffre : " + joueur.getPseudo());
        }

        // Année de naissance sur exactement 4 chiffres
        String anneeStr = String.valueOf(joueur.getAnneeNaissance());
        if (anneeStr.length() != 4) {
            throw new BadRequestException(
                    "L'année de naissance doit comporter exactement 4 chiffres : " + anneeStr);
        }

        // Centres d'intérêts : format valide
        CentresInteretsDTO centres = joueur.getCentresInterets();
        if (centres != null && !centres.isValide()) {
            throw new BadRequestException(
                    "Format des centres d'intérêts invalide : " + centres.getLibelle());
        }

        // Langue non nulle
        if (joueur.getLanguePreferee() == null) {
            throw new BadRequestException("La langue préférée est obligatoire.");
        }
    }

    /**
     * Vérifie que le pseudo n'existe pas déjà (sensible à la casse).
     * @throws ConflictException si le pseudo est déjà pris
     */
    private void verifierPseudoUnique(String pseudo) throws ConflictException {
        for (JoueurDTO j : joueurs) {
            if (j.getPseudo().equals(pseudo)) {
                throw new ConflictException(
                        "Le pseudo est déjà utilisé : " + pseudo);
            }
        }
    }
}
