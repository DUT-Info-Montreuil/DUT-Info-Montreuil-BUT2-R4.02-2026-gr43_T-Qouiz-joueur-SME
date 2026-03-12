package org.univ_paris8.iut.montreuil.qdev.tp2026.gr43.jeuQuizz.util.exceptions;

/**
 * Exception levée en cas d'erreur interne inattendue côté serveur.
 *
 * Cas déclencheurs :
 * - Échec lors de la persistance ou de la lecture des données (ex : fichier corrompu).
 * - Toute anomalie non gérée par les autres exceptions métier.
 */
public class InternalException extends Exception {

    public InternalException(String message) {
        super(message);
    }

    public InternalException(String message, Throwable cause) {
        super(message, cause);
    }
}
