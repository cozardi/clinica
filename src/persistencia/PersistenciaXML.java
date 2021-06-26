package persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PersistenciaXML implements IPersistencia {
    private FileInputStream fileInput = null;
    private FileOutputStream fileOutput = null;
    private XMLEncoder encoder = null;
    private XMLDecoder decoder = null;

    @Override
    public void abrirInput(String nombre) throws IOException {
        fileInput = new FileInputStream(nombre);
        decoder = new XMLDecoder(fileInput);
    }

    @Override
    public void abrirOutput(String nombre) throws IOException {
        fileOutput = new FileOutputStream(nombre);
        encoder = new XMLEncoder(fileOutput);
    }

    @Override
    public void cerrarOutput() throws IOException {
        if (fileOutput != null)
            fileOutput.close();

    }

    @Override
    public void cerrarInput() throws IOException {
        if (fileInput != null)
            fileInput.close();
    }

    @Override
    public void guardar(Object objecto) throws IOException {
        if (encoder != null)
            encoder.writeObject(objecto);

    }

    @Override
    public Object cargar() throws IOException, ClassNotFoundException {
        Object aux = null;
        if (decoder != null)
            aux = decoder.readObject();
        return aux;
    }
}
