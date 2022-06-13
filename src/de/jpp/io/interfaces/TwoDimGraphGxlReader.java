package de.jpp.io.interfaces;

import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;
import de.jpp.model.interfaces.Graph;
import org.jdom2.Element;
import org.w3c.dom.Node;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TwoDimGraphGxlReader extends GxlReaderTemplate {

    public static void main(String[] args) throws ParseException {
        TwoDimGraphGxlReader twoDimGraphGxlReader = new TwoDimGraphGxlReader();
        TwoDimGraph twoDimGraph2 = null;
        TwoDimGraph twoDimGraph = new TwoDimGraph();
        try {
            twoDimGraph = (TwoDimGraph) twoDimGraphGxlReader.read("<gxl> <graph id=\"id1\"> <node id=\"id1\"> <attr name=\"description\"> <string>n1</string> </attr> <attr name=\"x\"> <int>0</int> </attr> <attr name=\"y\"> <int>0</int> </attr> </node> <node id=\"id2\"> <attr name=\"description\"> <string>n2</string> </attr> <attr name=\"x\"> <int>4</int> </attr> <attr name=\"y\"> <int>1</int> </attr> </node> <edge from=\"id1\" id=\"id4\" to=\"id2\"> <attr name=\"description\"> <string>e1</string> </attr> <attr name=\"cost\"> <float>1.2</float> </attr> </edge> </graph> </gxl>");
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        System.out.println("lol");
        try {
            twoDimGraph2 = (TwoDimGraph) twoDimGraphGxlReader.read("<gxl> <graph id=\"id1\"> <node id=\"id1\"> <attr name=\"description\">  </attr> <attr name=\"x\"> <int>0</int> </attr> <attr name=\"y\"> <int>0</int> </attr> </node> <node id=\"id2\"> <attr name=\"description\"> <string>n2</string> </attr> <attr name=\"k\"> <int>4</int> </attr> <attr name=\"y\"> <int>1</int> </attr> </node> <edge from=\"id1\" id=\"id4\" to=\"id2\"> <attr name=\"description\"> <string>e1</string> </attr> <attr name=\"cost\"> <float>1.2</float> </attr> </edge> </graph> </gxl>");
        } catch (ParseException e) {
                throw new ParseException();
        }




        ;
        twoDimGraph.equals(twoDimGraph2);


    }
    //getAttrValue("<edge from="id2" id="id19" to="id1">", "from") => id2

    public TwoDimGraphGxlReader(){

    }

    public String getAttrValue(Element element, String string){
        return element.getAttributeValue(string);
    }

    public void hello(){

    }

    public Graph read(String input) throws ParseException {
        return super.read(input);
    }

    @Override
    public Graph createGraph() {
        return new TwoDimGraph();
    }

    public String readNodeId(Node node, Element element){

        return element.getAttributeValue("id");
    }

    public XYNode readNode(Element element) throws ParseException {

        List children = element.getChildren();
        String label = null;
        float x = 0;
        float y = 0;
        boolean Xkamvor = false;
        boolean Ykamvor = false;
        boolean Labelkamvor = false;
        for (int i = 0; i < children.size(); i++) {
            Element e = (Element) children.get(i);
            if(e.getAttributeValue("name").equals("description")){
                List child = e.getChildren();
                if(child.size() == 0){
                    throw new ParseException();
                }
                label = e.getValue();
                Labelkamvor = true;
            }
            if(e.getAttributeValue("name").equals("x")){
                String tryx = e.getValue();
                if(tryx.contains(",")){
                    throw new ParseException();
                }
                x = Float.parseFloat(e.getValue());
                Xkamvor = true;
            }
            if(e.getAttributeValue("name").equals("y")){
                y= Float.parseFloat(e.getValue());
                Ykamvor = true;
            }
        }
        if(!Xkamvor || !Ykamvor || !Labelkamvor || Objects.isNull(label) || label.equals("  ")){
            throw new ParseException();
        }
        XYNode node1 = new XYNode(label, x, y);
        return node1;
    }

    public Optional readAnnotation(Element element){
        Optional optionalA = Optional.empty();
        List children = element.getChildren();
        for (int i = 0; i < children.size(); i++) {
            Element e = (Element) children.get(i);
            if(e.getAttributeValue("name").equals("description")){
                String tag = e.getValue().replace("\n", "");
                optionalA.of(tag);
            }
        }


        return optionalA;
    }
}
