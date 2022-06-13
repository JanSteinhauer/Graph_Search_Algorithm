package de.jpp.io.interfaces;

import de.jpp.model.LabelMapGraph;
import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;
import de.jpp.model.interfaces.Graph;
import org.jdom2.Element;
import org.w3c.dom.Node;

import java.util.List;
import java.util.Optional;

public class LabelMapGraphReader  extends GxlREaderTemplateLabel  {
    public static void main(String[] args) throws ParseException {
        LabelMapGraphReader labelMapGraphReader = new LabelMapGraphReader();
        LabelMapGraph twoDimGraph = (LabelMapGraph) labelMapGraphReader.read("<gxl> <graph id=\"id1\"> <node id=\"id1\"> <attr name=\"description\"> <string>n1</string> </attr> </node> <node id=\"id2\"> <attr name=\"description\"> <string>n2</string> </attr> </node>  <edge from=\"id1\" id=\"id3\" to=\"id2\"> <attr name=\"description\"> <string>edge</string> </attr> <attr name=\"x\"> <int>0.00</int> </attr> <attr name=\"y\"> <int>0</int> </attr> <attr name=\"foo\"> <String>bar</String> </attr> <attr name = \"bar\"> <float>3.0</float>  </attr>  </edge> </graph> </gxl>");
        System.out.println("lol");
    }
    @Override
    public Graph createGraph() {
        return new LabelMapGraph();
    }



    @Override
    public String readNodeId(Node node, Element element) {
        return element.getAttributeValue("id");
    }

    @Override
    public Graph read(Object input) throws ParseException {

        Graph g = super.read(input);

        return g;
    }

    @Override
    public String readNode(Element element) throws ParseException {
        List children = element.getChildren();
        String label = "";
        float x = 0;
        float y = 0;
        boolean Labelkamvor = false;
        for (int i = 0; i < children.size(); i++) {
            Element e = (Element) children.get(i);
            if(e.getAttributeValue("name").equals("description")){ //todo hier einfach mit string arbeiten
                //und die verschiedenen exceptions abfangen wie keine
                List child = e.getChildren();
                if(child.size() == 0){
                    throw new ParseException();
                }
                Labelkamvor = true;
                label = e.getValue();
            }
            if(e.getAttributeValue("name").equals("x")){
                x = Float.parseFloat(e.getValue());
            }
            if(e.getAttributeValue("name").equals("y")){
                y= Float.parseFloat(e.getValue());
            }
        }
        if(!Labelkamvor){
            throw new ParseException();
        }

        label = label.replace(" ","");
        return label;
    }

    @Override
    public Optional readAnnotation(Element element) {
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
