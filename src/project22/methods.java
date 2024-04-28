package project22;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class methods {    
    Stack postS = new Stack();
    Stack numbers = new Stack();
    Stack characters = new Stack();
    Stack expressions = new Stack();
    StringBuilder sb = new StringBuilder();
    String filePath = "file4.xml";

//push elemnts into postS stack as postfix
    //and save it in string called sb as prefix

    public void processElements(Element element) throws ParserConfigurationException, SAXException, IOException {
        String nodeName = element.getNodeName();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(filePath);

        Element rootElement = doc.getDocumentElement();

        // Extract values from the XML structure
        String binaryType = rootElement.getAttribute("type");
        NodeList atomNodeList = rootElement.getElementsByTagName("atom");
        String atomType = ((Element) atomNodeList.item(0)).getAttribute("value");
        if ("atom".equals(nodeName) || "operator".equals(nodeName)) {
            if (isInteger(atomType)) {
                String type = element.getAttribute("value");
                postS.push(type);
                sb.append(postS.top() + " ");
            } else {
                System.out.println(" File Invaild there's opertstor after the atom");
                System.exit(0);
            }
        }

        NodeList childNodes = element.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i) instanceof Element) {
                processElements((Element) childNodes.item(i));
            }
        }

    }

    //converst sb to infix expressions
    public String prefixToInfix(String prefix) {
        StringBuilder infix = new StringBuilder();
        String[] c = prefix.split(" ");
        // Scan the prefix expression from right to left

        for (int i = c.length - 1; i >= 0; i--) {

            if (!c[i].equals("+") && !c[i].equals("-") && !c[i].equals("/") && !c[i].equals("*") && !c[i].equals("^") && !c[i].equals(" ")) {
                expressions.push(c[i]);
            } else {
                String operand1 = expressions.pop();
                String operand2 = expressions.pop();
                String expression = "(" + operand1 + c[i] + operand2 + ")";
                expressions.push(expression);
            }
        }
        infix.append(expressions.pop());

        return infix.toString();

    }

    static int countN = 0;
    static int charN = 0;

    public void result() {
        if (postS.isEmpty()) {
            System.out.println("Stack is empty");
        } else {
            while (!postS.isEmpty()) {
                String c = postS.pop();

                if (isInteger(c)) {
                    numbers.push(c);

                } else {

                    while (!characters.isEmpty() && (precedense(c) <= precedense(characters.top()))) {
                        processOperator(numbers, characters);
                    }
                    characters.push(c);

                }
                while (!characters.isEmpty()) {
                    processOperator(numbers, characters);
                }
            }
        }

    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true; // Successfully parsed as an integer
        } catch (NumberFormatException e) {
            return false; // Cannot be parsed as an integer
        }
    }

    public boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    public double operResult(double v1, double v2, String oper) {
        double result = 0;
        switch ((int) oper.toCharArray()[0]) {
            case 43:
                result = v1 + v2;
                break;
            case 45:
                result = v1 - v2;
                break;
            case 42:
                result = v1 * v2;
                break;
            case 47:
                result = v1 / v2;
                break;
            case 94:
                result = Math.pow(v1, v2);
                break;
            default:
                System.out.println("It's not one of the recognized operators.");
                break;
        }
        return result;
    }

    public int precedense(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }

    public void processOperator(Stack numbers, Stack characters) {
        double v1 = Double.parseDouble(numbers.pop()); // Change to Double.parseDouble
        double v2 = Double.parseDouble(numbers.pop()); // Change to Double.parseDouble
        String operator = characters.pop();
        numbers.push(String.valueOf(operResult(v1, v2, operator)));
    }

}

