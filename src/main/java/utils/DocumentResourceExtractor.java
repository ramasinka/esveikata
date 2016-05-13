package utils;

import org.w3c.dom.*;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DocumentResourceExtractor {
    File file;

    public DocumentResourceExtractor(File file) {
        this.file = file;
    }

    public void extractResources() {
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            getNodeData(doc.getChildNodes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getNodeData(NodeList nodeList) {
        File f = null;
        XmlFile xmlFile = new XmlFile();
        String xmlEditor = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        String resourceType = null;
        String id = null;
        String versionId = null;
        String contentData = null;

        for (int count = 0; count < nodeList.getLength(); count++) {
            Node tempNode = nodeList.item(count);
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                if (tempNode.getNodeName() == "link") {
                    if (tempNode.hasAttributes()) {
                        NamedNodeMap nodeMap = tempNode.getAttributes();
                        Node node = nodeMap.getNamedItem("href");
                        String name = node.getNodeValue();
                        String[] result = name.split("/");
                        resourceType = result[0];
                        id = result[1];
                        versionId = result[3];
                        File file = new File("input/output/");
                        file.mkdir();
                        file = new File("input/output/" + resourceType);
                        file.mkdir();
                        file = new File("input/output/" + resourceType + "/" + id);
                        file.mkdir();
                        f = xmlFile.createXmlFile("input/output/", resourceType + "/", id + "/", versionId);
                        try {
                            xmlFile.writeToXmlFile(xmlEditor, f);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (tempNode.getNodeName() == "content") {
                    try {
                        for (int i = 0; i < tempNode.getChildNodes().getLength(); i++) {
                            contentData = nodeToString(tempNode.getChildNodes().item(i));
                            xmlFile.writeToXmlFile(contentData, f);
                        }
                        break;
                    } catch (TransformerConfigurationException e) {
                        e.printStackTrace();
                    } catch (TransformerException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (tempNode.hasChildNodes()) {
                getNodeData(tempNode.getChildNodes());
            }
        }
    }

    public static String nodeToString(Node node)
            throws TransformerException {
        StringWriter buf = new StringWriter();
        Transformer xform = TransformerFactory.newInstance().newTransformer();
        xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        xform.transform(new DOMSource(node), new StreamResult(buf));
        return (buf.toString());
    }
}

