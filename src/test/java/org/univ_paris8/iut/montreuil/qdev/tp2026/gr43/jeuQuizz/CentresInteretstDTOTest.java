package org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz;

import org.junit.jupiter.api.Test;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.dtos.CentresInteretsDTO;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour CentresInteretsDTO.
 *
 * Règle métier : le libellé est soit vide, soit une chaîne de caractères
 * séparés par des virgules ne finissant pas par une virgule.
 *
 * Cas couverts : TU_CI_01 à TU_CI_04
 *
 * NOTE : la méthode isValide() est à implémenter dans CentresInteretsDTO.
 * Signature attendue : public boolean isValide()
 */
public class CentresInteretstDTOTest {

    // =========================================================
    // Nominaux
    // =========================================================

    /**
     * TU_CI_01 — Libellé vide (cas valide).
     * Entrée : libelle=""
     * Attendu : isValide() retourne true
     */
    @Test
    void centresInterets_libelleVide_estValide() {
        CentresInteretsDTO dto = new CentresInteretsDTO("");
        assertTrue(dto.isValide());
    }

    /**
     * TU_CI_02 — Plusieurs centres valides.
     * Entrée : libelle="football, nature, sport"
     * Attendu : isValide() retourne true
     */
    @Test
    void centresInterets_plusieursCentresValides_estValide() {
        CentresInteretsDTO dto = new CentresInteretsDTO("football,nature,sport");
        assertTrue(dto.isValide());
    }

    // =========================================================
    // Erreur de format
    // =========================================================

    /**
     * TU_CI_03 — Libellé se terminant par une virgule.
     * Entrée : libelle="football,"
     * Attendu : isValide() retourne false
     */
    @Test
    void centresInterets_libelleFinissantParVirgule_estInvalide() {
        CentresInteretsDTO dto = new CentresInteretsDTO("football,");
        assertFalse(dto.isValide());
    }

    /**
     * TU_CI_04 — Libellé commençant par une virgule.
     * Entrée : libelle=", football"
     * Attendu : isValide() retourne false
     */
    @Test
    void centresInterets_libelleCommencantParVirgule_estInvalide() {
        CentresInteretsDTO dto = new CentresInteretsDTO(",football");
        assertFalse(dto.isValide());
    }
}
