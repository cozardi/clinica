package modelo.exceptions;

/**
 * Esta clase se lanza cuando el asociado a crear ya existe en el sistema
 */
public class YaExisteAsociadoException extends Exception {
    public YaExisteAsociadoException(String message) {
        super(message);
    }
}
