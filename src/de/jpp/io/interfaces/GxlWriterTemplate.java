package de.jpp.io.interfaces;

import de.jpp.model.XYNode;
import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;
import org.jdom2.Document;
import org.jdom2.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;


public abstract class GxlWriterTemplate<N, A, G extends Graph<N, A>, F> implements GraphWriter<N, A, G , F>{ // classe noch abstract machen
    StringBuilder stringBuilder = new StringBuilder();
    List<N> nodes = new ArrayList<>();
    HashMap<N, List<Edge<N,A>>> edges = new HashMap<>();
    HashMap<Node, Integer> nodeId = new HashMap<>();


    int nodeid = 1;
    int edgeid = 1;


    public static void main(String[] args) throws ParseException {
//        GraphImpl<String,String> graph = new GraphImpl<String,String>();
//        graph.addNodes("node1", "node2","node3");
//        graph.addEdge("node1","node2", Optional.empty());
//        GxlWriterTemplate gxlWriterTemplate = new GxlWriterTemplate();
//        gxlWriterTemplate.write(graph);
    }

    @Override
    public  F write(G graph) throws ParseException {
        stringBuilder.setLength(0); // todo das ist quasi für den Stringbuilder clear
        nodes.clear();
        nodeId.clear();
        nodeid = 1;
        edgeid = 1;
        stringBuilder.append("<gxl>" +//todo gibt es einen unterschied zw graph und graph id="0"
                "<graph id=\"id0\">");//todo abstract ab writenode
        nodes.addAll(graph.getNodes());
        hello();
        for (int i = 0; i < nodes.size(); i++) {
            XYNode node = (XYNode)nodes.get(i);
            Element element = writeNode(node);
        }
        for(Edge<N,A> e : graph.getEdges()){
            writeEdge(e);
        }
        //neues Element

//        for(Map.Entry<N, List<Edge<N,A>>> entry : edges.entrySet()){
//            List<Edge<N,A>> edge = new ArrayList<>();
//            edge.addAll(entry.getValue());
//            for (int i = 0; i < edge.size(); i++) {
//                stringBuilder.append(writeEdge(edge.get(i)).toString());
//            }
//        }

        stringBuilder.append("</graph>" +
                "</gxl>");




        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        /*try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = (Document) builder.parse(new InputSource(new StringReader(stringBuilder.toString())));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/



        //Document doc = convertStringToXMLDocument(stringBuilder.toString());
        return (F) stringBuilder.toString();
    }

    public GxlWriterTemplate(){

    }

    public void hello(){
        System.out.println("Hello");
    }

    abstract public Element writeNode(XYNode node);


    abstract public Element writeEdge(Edge edge);


    abstract public String calculateId(XYNode node);

    abstract public String calculateId(Edge edge);

    private static Document convertStringToXMLDocument(String xmlString)
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = (Document) builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}



