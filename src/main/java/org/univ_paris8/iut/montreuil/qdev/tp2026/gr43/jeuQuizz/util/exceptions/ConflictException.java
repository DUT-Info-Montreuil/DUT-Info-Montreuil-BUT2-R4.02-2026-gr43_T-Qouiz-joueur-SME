package org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions;

/**
 * Exception levée lorsqu'un conflit est détecté sur une ressource existante.
 *
 * Cas déclencheur :
 * - Le pseudo renseigné existe déjà (le pseudo doit être unique).
 */
public class ConflictException extends Exception {

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
