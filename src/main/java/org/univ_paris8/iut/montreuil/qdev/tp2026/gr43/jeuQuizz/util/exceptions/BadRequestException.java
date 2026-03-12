package org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions;

/**
 * Exception levée lorsqu'une saisie utilisateur est invalide.
 *
 * Cas déclencheurs :
 * - Le pseudo commence par un chiffre.
 * - L'année de naissance ne fait pas 4 chiffres.
 * - Le format des centres d'intérêts est incorrect (se termine par une virgule).
 * - L'id de langue n'est pas compris entre 1 et 5.
 */
public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
