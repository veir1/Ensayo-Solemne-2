package me.veir1.minimarket.storage;

import me.veir1.minimarket.product.Product;
import me.veir1.minimarket.storage.file.IProductFileReaderWriter;
import me.veir1.minimarket.storage.file.ProductFileReaderWriter;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ProductStorage implements IProductStorage {
    private final IProductFileReaderWriter productFileReaderWriter;
    private final List<Product> products;

    private final static String PRODUCT_READ_ERROR = "No se han podido leer los productos correctamente, asegúrese de que los productos cumplen con el formato [PRODUCTO:CANTIDAD:PRECIO] y que se tiene acceso al archivo de almacenamiento.";
    private final static String PRODUCT_WRITE_HEADER_ERROR = "No se ha podido escribir la cabecera en el archivo de salida. Recuerda que el programa debe tener acceso de lectura y escritura a la ruta especificada.";
    private final static String PRODUCT_WRITE_ERROR = "No se han podido escribir los productos en el archivo de salida. Recuerda que el programa debe tener acceso de lectura y escritura a la ruta especificada.";


    /**
     * Constructor de la clase ProductStorage. Creará una instancia de la clase
     * {@link ProductFileReaderWriter} declarada bajo la interfaz {@link IProductFileReaderWriter}
     * y generará los BufferedReaderWriter.
     *
     * Creará un ArrayList en el cual se almacerarán los productos leídos para luego ser
     * procesados y escritos en el output.
     */
    public ProductStorage(final String inputFile, final String outputFile) {
        productFileReaderWriter = new ProductFileReaderWriter(inputFile, outputFile);

        products = new ArrayList<>();

        readProducts();
        writeProducts();
    }

    /**
     * Leer los productos existentes en el archivo de texto solicitado. Obtendrá un
     * BufferedReader proveniente de la interfaz ProductFileReaderWriter, instanciada
     * en esta misma clase; ProductStorage.
     *
     * @see IProductFileReaderWriter - Interfaz de la clase {@link ProductFileReaderWriter}
     */
    @Override
    public void readProducts() {
        System.out.println("Reading...");
        try {
            final BufferedReader reader = productFileReaderWriter.getFileReader();
            while (reader.ready()) {
                final StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), "\n");
                final String[] productAttributes = tokenizer.nextToken().split(":");

                final String productName = productAttributes[0];
                final int productQuantity = Integer.parseInt(productAttributes[1]);
                final int productPrice = Integer.parseInt(productAttributes[2]);
                final Product product = new Product(productName, productQuantity, productPrice);

                products.add(product);
            }
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null, PRODUCT_READ_ERROR, "Error", JOptionPane.ERROR_MESSAGE);
            exception.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Escribir productos, obtendrá un BufferedWriter para escribir en el archivo
     * de destino. El BufferedWriter viene de la clase ProductFileReaderWriter, la cual
     * está bajo la interfaz IProductFileReaderWriter, instanciada en esta misma clase.
     *
     * @see IProductFileReaderWriter - Interfaz de la clase {@link ProductFileReaderWriter}
     */
    @Override
    public void writeProducts() {
        System.out.println("Writing..");
        final BufferedWriter writer = productFileReaderWriter.getFileWriter();

        try {
            writer.write("PRODUCTO | CANTIDAD | PRECIO TOTAL CON IVA | IVA | PRECIO NETO");
            writer.newLine();
            products.forEach(product -> {
                final String productName = product.getProductName();

                System.out.println("Guardando producto: " + productName + "...");

                final int productPrice = product.getPrice();
                final int productQuantity = product.getQuantity();

                final int ivaChile = (int)(productPrice * (20 / 100.0f)); // 19% de IVA en chile, maldito país
                final int totalPrice = (productPrice + ivaChile);
                try {
                    writer.write(product.getProductName() + " | " + productQuantity + " | " + totalPrice + " | " + ivaChile + " | " + productPrice);
                    writer.newLine();
                    writer.flush();

                    System.out.println("Producto guardado: " + productName + " [" + productQuantity + " | " + totalPrice + " | " + ivaChile + " | " + productPrice + "]");

                } catch (final IOException exception) {
                    JOptionPane.showMessageDialog(null, PRODUCT_WRITE_ERROR, "Error", JOptionPane.ERROR_MESSAGE);
                    exception.printStackTrace();
                    System.exit(1);
                }
            });
        } catch (final IOException exception) {
            JOptionPane.showMessageDialog(null, PRODUCT_WRITE_HEADER_ERROR, "Error", JOptionPane.ERROR_MESSAGE);
            exception.printStackTrace();
            System.exit(1);
        }

        JOptionPane.showMessageDialog(null, "Se han escrito " + products.size() + " productos en el archivo " + productFileReaderWriter.getFileWriterPath());
    }

}
