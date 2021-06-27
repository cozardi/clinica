package modelo.exceptions;

/**
 * Esta clase se lanza cuando el asociado no existe
 */
public class NoExisteAsociadoException extends Exception {

    public NoExisteAsociadoException(String message) {
        super(message);
    }
}
