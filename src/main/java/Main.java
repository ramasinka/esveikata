import utils.UtilsXml;

import java.io.File;


/**
 * Created by Romcikas on 5/11/2016.
 */
public class Main {
    public static void main(String args[]) throws Exception {
        File file = new File("E://data/49.xml");
        String path = "E://data/submit/";
        UtilsXml readXmlFile = new UtilsXml(file,path);
        readXmlFile.parseXmlFile();

    }
}

