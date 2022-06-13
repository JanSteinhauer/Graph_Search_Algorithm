package de.jpp.io.interfaces;

import de.jpp.model.GraphImpl;
import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;
import de.jpp.model.interfaces.Edge;
import org.jdom2.Element;
import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TwoDimGraphGxlWriter extends GxlWriterTemplate {
    Map<Object,String> idMap = new HashMap<>();
    int maxId;

    public static void main(String[] args) throws ParseException {
        TwoDimGraph twoDimGraph = new TwoDimGraph();
        XYNode eins = new XYNode("hallo", 2,3);
        XYNode zwei = new XYNode("was geht", 4,5);
        twoDimGraph.addNodes(eins,zwei);
        twoDimGraph.addEdge(eins, zwei, Optional.of(2.0));
        TwoDimGraphGxlWriter gxlWriterTemplate = new TwoDimGraphGxlWriter();

        System.out.println(gxlWriterTemplate.write(twoDimGraph));
    }



    public TwoDimGraphGxlWriter(){

    }

    @Override
    public Element writeNode(XYNode node) {



        stringBuilder.append("<node id=\"id"+nodeid+"\">");
        stringBuilder.append("<attr name=\"description\">");
        stringBuilder.append("<string>");
        stringBuilder.append(node.getLabel());
        stringBuilder.append("</string>");
        stringBuilder.append("</attr>");
        stringBuilder.append("<attr name=\"x\">");
        stringBuilder.append("<float>");
        stringBuilder.append(String.valueOf(node.getX()));
        stringBuilder.append("</float>");
        stringBuilder.append("</attr>");
        stringBuilder.append("<attr name=\"y\">");
        stringBuilder.append("<float>");
        stringBuilder.append(String.valueOf(node.getY()));
        stringBuilder.append("</float>");
        stringBuilder.append("</attr>");
        stringBuilder.append("</node>");


        Element element = new Element(calculateId(node));
        Element desk = new Element("description");
        org.jdom2.Element x = new Element("x");
        org.jdom2.Element y = new Element("y");
        org.jdom2.Element xWert = new Element("float").setText(String.valueOf(node.getX()));
        org.jdom2.Element yWert = new Element("float").setText(String.valueOf(node.getY()));
        org.jdom2.Element deskString = new Element("string").setText(node.getLabel());

//        Element element = new Element(calculateId(node));
//        Element desk = new Element("attr name=\"description\"");
//        org.jdom2.Element x = new Element("attr name=\"x\"");
//        org.jdom2.Element y = new Element("attr name=\"y\"");
//        org.jdom2.Element xWert = new Element("float").setText(String.valueOf(node.getX()));
//        org.jdom2.Element yWert = new Element("float").setText(String.valueOf(node.getY()));
//        org.jdom2.Element deskString = new Element("string").setText(node.getLabel());

        x.addContent(xWert);
        y.addContent(yWert);
        desk.addContent(deskString);
        element.addContent(desk);
        element.addContent(x);
        element.addContent(y);



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
        stringBuilder.append("<attr name=\"description\">");
        stringBuilder.append("<string>id1"+number+"</string>");
        stringBuilder.append("</attr>");

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

    public String calculateId(Edge edge){
        return "<edge from=\"id"+nodeId.get(edge.getStart()) +"\" id=\"id"+edgeid+"\" to=\"id"+nodeId.get(edge.getDestination())+"\">";
    }

}

