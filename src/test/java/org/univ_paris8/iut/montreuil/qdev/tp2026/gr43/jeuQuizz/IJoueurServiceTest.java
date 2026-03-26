package org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz;

import mock.IJoueurServiceMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.Services.interfaces.IJoueurService;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.dtos.CentresInteretsDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.dtos.JoueurDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.enums.LangueEnum;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.BadRequestException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.ConflictException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.InternalException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour IJoueurService.
 * Utilise IJoueurServiceMock — pas de Mockito.
 *
 * Cas couverts :
 *   ajouterJoueur : TU_AJ_01 à TU_AJ_07
 *   listerJoueurs : TU_LJ_01 à TU_LJ_03
 */
public class IJoueurServiceTest {

    private IJoueurService joueurService;

    @BeforeEach
    void setUp() {
        // Nouvelle instance avant chaque test pour repartir d'une base vide
        joueurService = new IJoueurServiceMock();
    }

    // =========================================================
    // Helper
    // =========================================================

    private JoueurDTO buildJoueurValide() {
        return new JoueurDTO(
                "Minh",
                "MinhatorDu93",
                2002,
                new CentresInteretsDTO("football,sport"),
                LangueEnum.FRANCAIS
        );
    }

    // =========================================================
    // ajouterJoueur — Nominaux
    // =========================================================

    /**
     * TU_AJ_01 — Joueur entièrement valide.
     * Entrée : prénom="Minh", pseudo="MinhatorDu93", année=2002,
     *          centres="football, sport", langue=FRANCAIS
     * Attendu : retourne JoueurDTO créé, aucune exception
     */
    @Test
    void ajouterJoueur_joueurValide_retourneJoueurDTO()
            throws BadRequestException, ConflictException, InternalException {
        JoueurDTO result = joueurService.ajouterJoueur(buildJoueurValide());
        assertNotNull(result);
        assertEquals("MinhatorDu93", result.getPseudo());
        assertEquals("Minh", result.getPrenom());
    }

    /**
     * TU_AJ_02 — Centres d'intérêts vides (cas valide).
     * Entrée : joueur valide avec centresInterets=""
     * Attendu : retourne JoueurDTO créé, aucune exception
     */
    @Test
    void ajouterJoueur_centresInteretsVides_retourneJoueurDTO()
            throws BadRequestException, ConflictException, InternalException {
        JoueurDTO joueur = buildJoueurValide();
        joueur.setCentresInterets(new CentresInteretsDTO(""));
        JoueurDTO result = joueurService.ajouterJoueur(joueur);
        assertNotNull(result);
    }

    // =========================================================
    // ajouterJoueur — BadRequestException
    // =========================================================

    /**
     * TU_AJ_03 — Pseudo commençant par un chiffre.
     * Entrée : pseudo="1Minh", reste valide
     * Attendu : lève BadRequestException
     */
    @Test
    void ajouterJoueur_pseudoCommenceParChiffre_leveBadRequestException() {
        JoueurDTO joueur = buildJoueurValide();
        joueur.setPseudo("1Minh");
        assertThrows(BadRequestException.class,
                () -> joueurService.ajouterJoueur(joueur));
    }

    /**
     * TU_AJ_04 — Année de naissance trop courte (3 chiffres).
     * Entrée : anneeNaissance=200
     * Attendu : lève BadRequestException
     */
    @Test
    void ajouterJoueur_anneeNaissanceTropCourte_leveBadRequestException() {
        JoueurDTO joueur = buildJoueurValide();
        joueur.setAnneeNaissance(200);
        assertThrows(BadRequestException.class,
                () -> joueurService.ajouterJoueur(joueur));
    }

    /**
     * TU_AJ_05 — Centres d'intérêts se terminant par une virgule.
     * Entrée : centresInterets="football,sport,"
     * Attendu : lève BadRequestException
     */
    @Test
    void ajouterJoueur_centresInteretsFinissantParVirgule_leveBadRequestException() {
        JoueurDTO joueur = buildJoueurValide();
        joueur.setCentresInterets(new CentresInteretsDTO("football,sport,"));
        assertThrows(BadRequestException.class,
                () -> joueurService.ajouterJoueur(joueur));
    }

    /**
     * TU_AJ_06 — Langue nulle (hors enum).
     * Entrée : languePreferee=null
     * Attendu : lève BadRequestException
     */
    @Test
    void ajouterJoueur_langueNulle_leveBadRequestException() {
        JoueurDTO joueur = buildJoueurValide();
        joueur.setLanguePreferee(null);
        assertThrows(BadRequestException.class,
                () -> joueurService.ajouterJoueur(joueur));
    }

    // =========================================================
    // ajouterJoueur — ConflictException
    // =========================================================

    /**
     * TU_AJ_07 — Pseudo déjà existant.
     * Contexte : "MinhatorDu93" existe déjà en base
     * Entrée : pseudo="MinhatorDu93"
     * Attendu : lève ConflictException
     */
    @Test
    void ajouterJoueur_pseudoDejaExistant_leveConflictException()
            throws BadRequestException, ConflictException, InternalException {
        joueurService.ajouterJoueur(buildJoueurValide());
        JoueurDTO doublon = buildJoueurValide();
        assertThrows(ConflictException.class,
                () -> joueurService.ajouterJoueur(doublon));
    }

    // =========================================================
    // listerJoueurs — Nominaux
    // =========================================================

    /**
     * TU_LJ_01 — Liste triée alphabétiquement par pseudo.
     * Contexte : 3 joueurs en base : pseudos "Zorro", "Alice", "Martin"
     * Attendu : retourne ["Alice", "Martin", "Zorro"]
     */
    @Test
    void listerJoueurs_listeTrieeAlphabetiquement_retourneListeDTO()
            throws BadRequestException, ConflictException, InternalException, NotFoundException {
        joueurService.ajouterJoueur(new JoueurDTO("Z", "Zorro",  2000, new CentresInteretsDTO(""), LangueEnum.FRANCAIS));
        joueurService.ajouterJoueur(new JoueurDTO("A", "Alice",  2001, new CentresInteretsDTO(""), LangueEnum.FRANCAIS));
        joueurService.ajouterJoueur(new JoueurDTO("M", "Martin", 2002, new CentresInteretsDTO(""), LangueEnum.FRANCAIS));

        List<JoueurDTO> result = joueurService.listerJoueurs();

        assertEquals(3, result.size());
        assertEquals("Alice",  result.get(0).getPseudo());
        assertEquals("Martin", result.get(1).getPseudo());
        assertEquals("Zorro",  result.get(2).getPseudo());
    }

    /**
     * TU_LJ_02 — Un seul joueur en base.
     * Contexte : 1 joueur en base
     * Attendu : liste de taille 1, aucune exception
     */
    @Test
    void listerJoueurs_unSeulJoueur_retourneListeDeTailleUn()
            throws BadRequestException, ConflictException, InternalException, NotFoundException {
        joueurService.ajouterJoueur(buildJoueurValide());
        List<JoueurDTO> result = joueurService.listerJoueurs();
        assertEquals(1, result.size());
    }

    // =========================================================
    // listerJoueurs — NotFoundException
    // =========================================================

    /**
     * TU_LJ_03 — Aucun joueur enregistré.
     * Contexte : base vide
     * Attendu : lève NotFoundException
     */
    @Test
    void listerJoueurs_aucunJoueur_leveNotFoundException() {
        assertThrows(NotFoundException.class,
                () -> joueurService.listerJoueurs());
    }
}