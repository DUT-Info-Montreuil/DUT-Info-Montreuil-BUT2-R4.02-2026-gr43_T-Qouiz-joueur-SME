package org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.Services.interfaces;

import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.dtos.JoueurDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.BadRequestException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.ConflictException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.InternalException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.NotFoundException;
import java.util.List;

/**
 * Interface du service métier Joueur (SME).
 * Définit les opérations disponibles pour la gestion des joueurs.
 */
public interface IJoueurService {

    /**
     * Ajoute un nouveau joueur dans le système.
     *
     * @param nouveauJoueur DTO contenant les informations du joueur à créer
     *                      (prénom, pseudo, année de naissance, centres d'intérêts, langue)
     * @return JoueurDTO représentant le joueur nouvellement créé et validé
     * @throws BadRequestException si une saisie est invalide :
     *         - le pseudo commence par un chiffre
     *         - l'année de naissance ne fait pas 4 chiffres
     *         - le format des centres d'intérêts est incorrect (se termine par une virgule)
     *         - l'id de langue n'est pas compris entre 1 et 5
     * @throws ConflictException   si le pseudo existe déjà (le pseudo doit être unique)
     * @throws InternalException   en cas d'erreur interne lors de la persistance
     */
    public JoueurDTO ajouterJoueur(JoueurDTO nouveauJoueur)
            throws BadRequestException, ConflictException, InternalException;

    /**
     * Retourne la liste de tous les joueurs enregistrés, triés par ordre alphabétique de pseudo.
     *
     * @return List&lt;JoueurDTO&gt; la liste triée des joueurs
     * @throws NotFoundException si aucun joueur n'est enregistré
     * @throws InternalException en cas d'anomalie lors de la récupération des données
     */
    public List<JoueurDTO> listerJoueurs()
            throws NotFoundException, InternalException;
}
