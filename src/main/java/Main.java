import utils.DocumentResourceExtractor;

import java.io.File;



public class Main {
    public static void main(String args[]) throws Exception {
        File file = new File(args[0]);
        DocumentResourceExtractor documentResourceExtractor = new DocumentResourceExtractor(file);
        documentResourceExtractor.extractResources();
    }
}

