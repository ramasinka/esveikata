import utils.UtilsXml;

import java.io.File;



public class Main {
    public static void main(String args[]) throws Exception {
        File file = new File("E://data/49.xml");
        String path = "E://data/submit/";
        UtilsXml readXmlFile = new UtilsXml(file);
        readXmlFile.parseXmlFile();
    }
}

