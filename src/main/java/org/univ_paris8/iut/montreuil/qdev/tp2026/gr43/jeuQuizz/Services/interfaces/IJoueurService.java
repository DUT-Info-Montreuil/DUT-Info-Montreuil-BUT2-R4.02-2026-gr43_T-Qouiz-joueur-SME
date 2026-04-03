package org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.Services.interfaces;

import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.dtos.JoueurDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.BadRequestException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.ConflictException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.InternalException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.NotFoundException;

import java.util.List;

public interface IJoueurService {

    /**
     * Ajoute un nouveau joueur après validation de ses données.
     *
     * @param nouveauJoueur le joueur à créer (prénom, pseudo, année de naissance,
     *                      centres d'intérêts, langue)
     * @return le JoueurDTO nouvellement créé et validé
     * @throws BadRequestException si une erreur de saisie est détectée :
     *         - pseudo commençant par un chiffre
     *         - année de naissance ne faisant pas 4 caractères
     *         - mauvais format pour les centres d'intérêts
     *         - id de langue non compris entre 1 et 5
     * @throws ConflictException   si le pseudo existe déjà en base
     * @throws InternalException   en cas de problème côté serveur ou d'échec de la base de données
     */
    public JoueurDTO ajouterJoueur(JoueurDTO nouveauJoueur)
            throws BadRequestException, ConflictException, InternalException;

    /**
     * Retourne la liste de tous les joueurs triée par ordre alphabétique de pseudo.
     *
     * @return la liste des JoueurDTO triée alphabétiquement par pseudo
     * @throws NotFoundException si la liste récupérée en base de données est vide
     * @throws InternalException en cas de problème côté serveur ou d'anomalie de la base de données
     */
    public List<JoueurDTO> listerJoueurs()
            throws NotFoundException, InternalException;
}