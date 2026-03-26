package org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz;

import org.junit.jupiter.api.Test;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.entities.enums.LangueEnum;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour LangueEnum.fromId().
 *
 * Cas couverts : TU_LE_01 à TU_LE_04
 */
public class LangueEnumTest {

    // =========================================================
    // fromId — Nominaux
    // =========================================================

    /**
     * TU_LE_01 — fromId(1) retourne FRANCAIS.
     * Entrée : fromId(1)
     * Attendu : retourne FRANCAIS, aucune exception
     */
    @Test
    void fromId_idUnValide_retourneFrancais() {
        LangueEnum result = LangueEnum.fromId(1);
        assertEquals(LangueEnum.FRANCAIS, result);
    }

    /**
     * TU_LE_02 — fromId(5) retourne ITALIA.
     * Entrée : fromId(5)
     * Attendu : retourne ITALIA, aucune exception
     */
    @Test
    void fromId_idCinqValide_retourneItalia() {
        LangueEnum result = LangueEnum.fromId(5);
        assertEquals(LangueEnum.ITALIA, result);
    }

    // =========================================================
    // fromId — IllegalArgumentException
    // =========================================================

    /**
     * TU_LE_03 — fromId(0) id invalide (trop petit).
     * Entrée : fromId(0)
     * Attendu : lève IllegalArgumentException
     */
    @Test
    void fromId_idZeroInvalide_leveIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> LangueEnum.fromId(0));
    }

    /**
     * TU_LE_04 — fromId(6) id invalide (trop grand).
     * Entrée : fromId(6)
     * Attendu : lève IllegalArgumentException
     */
    @Test
    void fromId_idSixInvalide_leveIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> LangueEnum.fromId(6));
    }
}
