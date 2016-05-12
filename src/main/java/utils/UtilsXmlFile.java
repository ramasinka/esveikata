package utils;

import java.io.*;

/**
 * Created by Romcikas on 5/11/2016.
 */
public class UtilsXmlFile {
    public File createXmlFile(String path, String resourceType, String id, String versionId) {
        File file = new File(path + resourceType + "." + id + "." + versionId + ".xml");
        return file;
    }

    public void writeToXmlFile(String data, File file) throws IOException {
        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            output.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }
}

