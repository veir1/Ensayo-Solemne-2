package me.veir1.minimarket.storage.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public interface IProductFileReaderWriter { //
    void setInput(String inputFile);
    void setOutput(String outputFile);

    BufferedReader getFileReader();
    BufferedWriter getFileWriter();
    String getFileWriterPath();
}
