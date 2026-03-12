package org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions;

/**
 * Exception levée lorsqu'une ressource demandée est introuvable.
 *
 * Cas déclencheurs :
 * - La liste des joueurs récupérée est vide (aucun joueur enregistré).
 */
public class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
