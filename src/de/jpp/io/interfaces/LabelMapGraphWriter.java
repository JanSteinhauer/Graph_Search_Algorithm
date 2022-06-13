package de.jpp.io.interfaces;

import de.jpp.model.LabelMapGraph;
import de.jpp.model.XYNode;
import de.jpp.model.interfaces.Edge;
import org.jdom2.Element;

import java.util.HashMap;
import java.util.Map;

public class LabelMapGraphWriter extends GxlWriterTemplateLabel{
    public static void main(String[] args) throws ParseException {
        LabelMapGraphReader labelMapGraphReader3 = new LabelMapGraphReader();
        LabelMapGraph labelMapGraph = (LabelMapGraph) labelMapGraphReader3.read("<gxl> <graph id=\"id1\"> <node id=\"id1\"> <attr name=\"description\"> <string>n1</string> </attr> </node> <node id=\"id2\"> <attr name=\"description\"> <string>n2</string> </attr> </node>  <edge from=\"id1\" id=\"id3\" to=\"id2\"> <attr name=\"description\"> <string>edge</string> </attr> <attr name=\"x\"> <int>0.00</int> </attr> <attr name=\"y\"> <int>0</int> </attr> <attr name=\"foo\"> <String>bar</String> </attr> <attr name = \"bar\"> <float>3.0</float>  </attr>  </edge> </graph> </gxl>");
/*        labelMapGraph.addNode("hallo");
        labelMapGraph.addEdge("hallo", "eins");*/

        LabelMapGraphWriter labelMapGraphWriter = new LabelMapGraphWriter();
        String res = (String) labelMapGraphWriter.write(labelMapGraph);
        LabelMapGraphReader labelMapGraphReader = new LabelMapGraphReader();
        LabelMapGraph labelMapGraph1 = (LabelMapGraph) labelMapGraphReader.read(res);
        LabelMapGraphWriter labelMapGraphWriter1 = new LabelMapGraphWriter();
        String res2 = (String) labelMapGraphWriter1.write(labelMapGraph1);
        LabelMapGraphReader labelMapGraphReader1 = new LabelMapGraphReader();
        LabelMapGraph labelMapGraph2 = (LabelMapGraph) labelMapGraphReader1.read(res2);
        System.out.println(labelMapGraph2.equals(labelMapGraph));
        System.out.println("lol");
    }
    Map<Object,String> idMap = new HashMap<>();
    int maxId;
//sachen einfach rausparsen
    @Override
    public Element writeNode(String node) {



        stringBuilder.append("<node id=\"id"+node+"\">");
        stringBuilder.append("<attr name=\"description\">");
        stringBuilder.append("<string>");
        stringBuilder.append(node);
        stringBuilder.append("</string>");
        stringBuilder.append("</attr>");
        stringBuilder.append("</node>");

        Element element = new Element(node);
        Element desk = new Element("description");

        org.jdom2.Element deskString = new Element("string").setText(node);

//        Element element = new Element(calculateId(node));
//        Element desk = new Element("attr name=\"description\"");
//        org.jdom2.Element x = new Element("attr name=\"x\"");
//        org.jdom2.Element y = new Element("attr name=\"y\"");
//        org.jdom2.Element xWert = new Element("float").setText(String.valueOf(node.getX()));
//        org.jdom2.Element yWert = new Element("float").setText(String.valueOf(node.getY()));
//        org.jdom2.Element deskString = new Element("string").setText(node.getLabel());


        desk.addContent(deskString);
        element.addContent(desk);




        return element;
    }

    public  String assignId(Object o){
        return "assignId";
    }

    public org.jdom2.Element createAttribute(String string1, String string2, String string3){
        StringBuilder sb = new StringBuilder();
        org.jdom2.Element element = new org.jdom2.Element(string1);
        org.jdom2.Element inner = new org.jdom2.Element(string2).setText(string3);
        element.addContent(inner);
        return element;
    }

//    public org.jdom2.Element writeNode(XYNode node){
//        org.jdom2.Element element = new org.jdom2.Element(calculateId(node));
//        org.jdom2.Element desk = new org.jdom2.Element("attr name=\"description\"");
//        org.jdom2.Element x = new org.jdom2.Element("attr name=\"x\"");
//        org.jdom2.Element y = new org.jdom2.Element("attr name=\"y\"");
//        org.jdom2.Element xWert = new org.jdom2.Element("float").setText("node.getX()");
//        org.jdom2.Element yWert = new org.jdom2.Element("float").setText("node.getY()");
//        org.jdom2.Element deskString = new org.jdom2.Element("string").setText(node.getLabel());
//
//        x.addContent(xWert);
//        y.addContent(yWert);
//        desk.addContent(deskString);
//        element.addContent(desk);
//        element.addContent(x);
//        element.addContent(y);
//        return element;
//
//
//    }


    public org.jdom2.Element writeEdge(Edge edge){
        int number = edgeid+nodeid;
        stringBuilder.append(calculateId(edge));
        /*stringBuilder.append("<attr name=\"description\">"); //todo hier habe ich ausgeklammert
        stringBuilder.append("<string>id1"+number+"</string>");
        stringBuilder.append("</attr>");
*/
        String anfang = "id"+ edgeid;
        org.jdom2.Element element = new org.jdom2.Element(anfang);
        //org.jdom2.Element desk = new org.jdom2.Element("attr name=\"description\"");
        org.jdom2.Element desk = new org.jdom2.Element("description");

        org.jdom2.Element deskString = new org.jdom2.Element("string").setText("id"+number);
        desk.addContent(deskString);
        element.addContent(desk);

        if(edge.getAnnotation().isPresent()){//todo Frage ist die cost die Annotation
            stringBuilder.append("<attr name=\"cost\">");
            stringBuilder.append("<float>"+edge.getAnnotation().toString()+"</float>");
            stringBuilder.append("</attr>");

            //org.jdom2.Element cost = new org.jdom2.Element("attr name=\"cost\"");
            org.jdom2.Element cost = new org.jdom2.Element("cost");
            org.jdom2.Element costStr = new org.jdom2.Element("float").setText(edge.getAnnotation().toString());
            cost.addContent(costStr);
            element.addContent(cost);
        }
        stringBuilder.append("</edge>");


        return element;
    }

    public String calculateId(XYNode node){
        String result = "node id=\"id"+nodeid+"\"";
        nodeId.put(node, nodeid);
        String result2 = "id"+nodeid;
        nodeid += 1;

        return result2;
    }

    public String calculateId(Edge edge){ //todo hier darf nicht null raukommen
        return "<edge from=\"id"+edge.getStart()+"\" id=\"id"+edgeid+"\" to=\"id"+edge.getDestination()+"\">";
    }
}
