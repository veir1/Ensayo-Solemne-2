package me.veir1.minimarket;

import me.veir1.minimarket.storage.IProductStorage;
import me.veir1.minimarket.storage.ProductStorage;

import javax.swing.*;

/**
 * Concepto de Minimarket para ensayo solemne #2 de la asignatura
 * Programación Orientada a Objetos.
 * Docente: Myrle Suárez
 *
 * @author veir1
 * @version 1.0.0
 */
public final class Minimarket {
    private final IProductStorage productStorage; // Dejo declarado el Storage por si lo llego a necesitar en algún punto

    public static void main(String[] args) {
        new Minimarket();
    }

    public Minimarket() {
        final String fileReaderPath = JOptionPane.showInputDialog(null, "Por favor escribe el nombre del archivo de almacenamiento. Debe ser un archivo existente en la raíz de este programa.\nEjemplo: productos.txt", "Información", JOptionPane.INFORMATION_MESSAGE);
        final String fileWriterPath = JOptionPane.showInputDialog(null, "Por favor escribe el nombre del archivo de salida. Puede ser un archivo inexistente y debe terminar en .txt\nEjemplo: productos_salida.txt", "Información", JOptionPane.INFORMATION_MESSAGE);

        productStorage = new ProductStorage(fileReaderPath, fileWriterPath);
    }
}
