package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * Implementa la persistencia de clases serializables mediante el método binario
 *
 */
public class PersistenciaBIN implements IPersistencia<Serializable> {

    private FileOutputStream fileoutput = null;
    private FileInputStream fileinput = null;
    private ObjectOutputStream objectoutput = null;
    private ObjectInputStream objectinput = null;

    @Override
    public void abrirInput(String nombre) throws IOException {
        fileinput = new FileInputStream(nombre);
        objectinput = new ObjectInputStream(fileinput);
    }

    @Override
    public void abrirOutput(String nombre) throws IOException {
        fileoutput = new FileOutputStream(nombre);
        objectoutput = new ObjectOutputStream(fileoutput);
    }

    @Override
    public void cerrarOutput() throws IOException {
        if (objectoutput != null)
            objectoutput.close();
    }

    @Override
    public void cerrarInput() throws IOException {
        if (objectinput != null)
            objectinput.close();
    }

    @Override
    public void guardar(Serializable serializable) throws IOException {
        if (objectoutput != null)
            objectoutput.writeObject(serializable);

    }

    @Override
    public Serializable cargar() throws IOException, ClassNotFoundException {
        Serializable serializable = null;
        if (objectinput != null)
            serializable = (Serializable) objectinput.readObject();
        return serializable;
    }


}
