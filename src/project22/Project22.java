
package project22;

import java.io.IOException;
import javax.print.DocFlavor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Stack;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


public class Project22 {

   static methods m = new methods();

    public static void main(String[] args) throws ParserConfigurationException,IOException{
       
     try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(m.filePath);
            m.processElements(document.getDocumentElement());
        } catch (SAXException e) {
            System.out.println(" Invalid File Structure ");
            System.exit(0);
        }
        try{
        m.result();
            System.out.println("Expression = " + m.prefixToInfix(String.valueOf(m.sb)));
            System.out.println("Result = " +m.numbers.pop());
        }catch(Exception e){
            System.out.println("Invalid File Structure ");
            System.exit(0);
        }

    }
}


