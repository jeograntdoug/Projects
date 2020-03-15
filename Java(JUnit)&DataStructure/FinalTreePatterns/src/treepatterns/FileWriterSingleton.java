/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treepatterns;

////////////////////////////////////

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// FileManagerSingleton start
////////////////////////////////////
class FileWriterSingleton {
    private static BufferedWriter fileOutput;
    private static final String FILE_NAME = "events.txt";

    public static BufferedWriter getFileReaderSingleTon() throws IOException {
        if(fileOutput == null){
            fileOutput = new BufferedWriter(new FileWriter(new File(FILE_NAME),true));
        }
        return fileOutput;
    }

    public static void closeFileReaderSingleTon() throws IOException{
        fileOutput.close();
    }
}
////////////////////////////////////
// FileManagerSingleton end
////////////////////////////////////