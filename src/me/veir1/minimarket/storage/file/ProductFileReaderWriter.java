package me.veir1.minimarket.storage.file;

import javax.swing.*;
import java.io.*;

// Me dio lata documentar este xd
public final class ProductFileReaderWriter implements IProductFileReaderWriter { 
    private BufferedReader fileReader;

    private String fileWriterPath;
    private BufferedWriter fileWriter;
    private final static String INTERNAL_ERROR_MESSAGE = "Ha ocurrido un error interno irrecuperable, por favor revise el registro para más información.";
    private final static String FILE_READER_NULL_ERROR = "El lector de archivos no puede ser nulo. Este error es irrecuperable en esta versión de Minimarket.";
    private final static String FILE_WRITER_NULL_ERROR = "El lector de archivos no puede ser nulo. Este error es irrecuperable en esta versión de Minimarket.";

    public ProductFileReaderWriter(final String inputFile, final String outputFile) {
        setInput(inputFile);
        setOutput(outputFile);
    }

    public void setInput(final String inputFile) {
        final String path = System.getProperty("user.dir") + System.getProperty("file.separator") + inputFile;
        try {
            fileReader = new BufferedReader(new FileReader(path));
        } catch (final IOException exception) {
            JOptionPane.showMessageDialog(null, INTERNAL_ERROR_MESSAGE, "Error", JOptionPane.ERROR_MESSAGE);
            exception.printStackTrace();
            System.exit(1);
        }
    }

    public void setOutput(final String outputFile) {
        final String path = System.getProperty("user.dir") + System.getProperty("file.separator") + outputFile;
        try {
            fileWriter = new BufferedWriter(new FileWriter(path, true));
            fileWriterPath = path;
        } catch (final IOException exception) {
            JOptionPane.showMessageDialog(null, INTERNAL_ERROR_MESSAGE, "Error", JOptionPane.ERROR_MESSAGE);
            exception.printStackTrace();
            System.exit(1);
        }
    }

    public BufferedReader getFileReader() {
        if (fileReader == null) {
            JOptionPane.showMessageDialog(null, FILE_READER_NULL_ERROR, "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return fileReader;
    }

    public BufferedWriter getFileWriter() {
        if (fileWriter == null) {
            JOptionPane.showMessageDialog(null, FILE_WRITER_NULL_ERROR, "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return fileWriter;
    }

    public String getFileWriterPath() {
        return fileWriterPath;
    }
}
