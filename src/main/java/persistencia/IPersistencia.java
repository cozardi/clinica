package persistencia;

import java.io.IOException;

/**
 * 
 * Interfaz para serialización de clases
 *
 * @param <E>
 */
public interface IPersistencia<E> {
    void abrirInput(String nombre) throws IOException;

    void abrirOutput(String nombre) throws IOException;

    void cerrarOutput() throws IOException;

    void cerrarInput() throws IOException;

    void guardar(E objecto) throws IOException;

    E cargar() throws IOException, ClassNotFoundException;
}