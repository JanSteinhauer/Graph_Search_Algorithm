package de.jpp.io.interfaces;

import de.jpp.model.XYNode;
import de.jpp.model.interfaces.Graph;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.*;

public abstract class GxlReaderTemplate<N, A, G extends Graph<N, A>, F> implements GraphReader<N, A, G , F>{ //todo später wieder abstrakt machen

    Map<String, XYNode> nodeMap = new HashMap<>();
    List<Element> nodeOrEdge = new ArrayList<>();
    List<String> letzeTags = new ArrayList<>();
    Map<String, String> label = new HashMap<>();


    public  GxlReaderTemplate(){ //todo was soll diese Methode sagen
        //todo ab creategraph abstrakt und die anderen noch mit dazu

    }

  /*  public static void main(String[] args) throws ParseException {
        GxlReaderTemplate gxlReaderTemplate = new GxlReaderTemplate();
        gxlReaderTemplate.read(null);
    }*/

    @Override
    public G read(F input) throws ParseException { //todo hier wird ein String übergeben
        nodeMap.clear();
        nodeOrEdge.clear();
        letzeTags.clear();
        String test = (String) input;
        if(Objects.isNull(test)){

            throw new ParseException();
        }
        if(test.contains("digraph")){ //todo wenn ein dotGraph übergeben wird, wird er hier abgefangen
            throw new ParseException();
        }


        SAXBuilder builder = new SAXBuilder(); //todo String statt f

        Document xml = null;
        try {
            Reader stringreader = new StringReader( (String) input);
            xml = builder.build(stringreader); //todo hier irgendwann den String statt File übergeben
            //xml = convertStringToXMLDocument( (String) input); //todo hier irgendwann den String statt File übergeben
        } catch (Exception e) {
            /*String hallo = ((String) input).replace("<gxl><graph id=\"id0\"><node id=\"id1\"><attr name=\"description\"><string>n1</string></attr><attr name=\"x\"><float>100.0</float></attr><attr name=\"y\"><float>100.0</float></attr></node><node id=\"id2\"><attr name=\"description\"><string>n2</string></attr><attr name=\"x\"><float>200.0</float></attr><attr name=\"y\"><float>150.0</float></attr></node><node id=\"id3\"><attr name=\"description\"><string>n3</string></attr><attr name=\"x\"><float>150.0</float></attr><attr name=\"y\"><float>250.0</float></attr></node><node id=\"id4\"><attr name=\"description\"><string>n4</string></attr><attr name=\"x\"><float>50.0</float></attr><attr name=\"y\"><float>200.0</float></attr></node><node id=\"id5\"><attr name=\"description\"><string>n5</string></attr><attr name=\"x\"><float>300.0</float></attr><attr name=\"y\"><float>150.0</float></attr></node><node id=\"id6\"><attr name=\"description\"><string>n6</string></attr><attr name=\"x\"><float>350.0</float></attr><attr name=\"y\"><float>250.0</float></attr></node><node id=\"id7\"><attr name=\"description\"><string>n7</string></attr><attr name=\"x\"><float>300.0</float></attr><attr name=\"y\"><float>350.0</float></attr></node><node id=\"id8\"><attr name=\"description\"><string>n8</string></attr><attr name=\"x\"><float>200.0</float></attr><attr name=\"y\"><float>350.0</float></attr></node><node id=\"id9\"><attr name=\"description\"><string>n11</string></attr><attr name=\"x\"><float>450.0</float></attr><attr name=\"y\"><float>250.0</float></attr></node><node id=\"id10\"><attr name=\"description\"><string>n12</string></attr><attr name=\"x\"><float>500.0</float></attr><attr name=\"y\"><float>350.0</float></attr></node><node id=\"id11\"><attr name=\"description\"><string>n13</string></attr><attr name=\"x\"><float>450.0</float></attr><attr name=\"y\"><float>450.0</float></attr></node><node id=\"id12\"><attr name=\"description\"><string>n14</string></attr><attr name=\"x\"><float>350.0</float></attr><attr name=\"y\"><float>450.0</float></attr></node><node id=\"id13\"><attr name=\"description\"><string>n9</string></attr><attr name=\"x\"><float>200.0</float></attr><attr name=\"y\"><float>100.0</float></attr></node><node id=\"id14\"><attr name=\"description\"><string>n10</string></attr><attr name=\"x\"><float>100.0</float></attr><attr name=\"y\"><float>300.0</float></attr></node><node id=\"id15\"><attr name=\"description\"><string>n15</string></attr><attr name=\"x\"><float>150.0</float></attr><attr name=\"y\"><float>450.0</float></attr></node><node id=\"id16\"><attr name=\"description\"><string>n16</string></attr><attr name=\"x\"><float>50.0</float></attr><attr name=\"y\"><float>450.0</float></attr></node><node id=\"id17\"><attr name=\"description\"><string>n17</string></attr><attr name=\"x\"><float>50.0</float></attr><attr name=\"y\"><float>550.0</float></attr></node><node id=\"id18\"><attr name=\"description\"><string>n18</string></attr><attr name=\"x\"><float>150.0</float></attr><attr name=\"y\"><float>550.0</float></attr></node><edge from=\"id1\" id=\"id1\" to=\"id2\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id1\" id=\"id1\" to=\"id4\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id1\" id=\"id1\" to=\"id13\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id4\" id=\"id1\" to=\"id3\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id4\" id=\"id1\" to=\"id1\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id4\" id=\"id1\" to=\"id14\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id12\" id=\"id1\" to=\"id11\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id12\" id=\"id1\" to=\"id7\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float","");
            hallo.replace("/attr></edge><edge from=\"id10\" id=\"id1\" to=\"id9\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id10\" id=\"id1\" to=\"id11\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge></graph></gxl><gxl><graph id=\"id0\"><node id=\"id19\"><attr name=\"description\"><string>n1</string></attr><attr name=\"x\"><float>100.0</float></attr><attr name=\"y\"><float>100.0</float></attr></node><node id=\"id20\"><attr name=\"description\"><string>n2</string></attr><attr name=\"x\"><float>200.0</float></attr><attr name=\"y\"><float>150.0</float></attr></node><node id=\"id21\"><attr name=\"description\"><string>n3</string></attr><attr name=\"x\"><float>150.0</float></attr><attr name=\"y\"><float>250.0</float></attr></node><node id=\"id22\"><attr name=\"description\"><string>n4</string></attr><attr name=\"x\"><float>50.0</float></attr><attr name=\"y\"><float>200.0</float></attr></node><node id=\"id23\"><attr name=\"description\"><string>n5</string></attr><attr name=\"x\"><float>300.0</float></attr><attr name=\"y\"><float>150.0</float></attr></node><node id=\"id24\"><attr name=\"description\"><string>n6</string></attr><attr name=\"x\"><float>350.0</float></attr><attr name=\"y\"><float>250.0</float></attr></node><node id=\"id25\"><attr name=\"description\"><string>n7</string></attr><attr name=\"x\"><float>300.0</float></attr><attr name=\"y\"><float>350.0</float></attr></node><node id=\"id26\"><attr name=\"description\"><string>n8</string></attr><attr name=\"x\"><float>200.0</float></attr><attr name=\"y\"><float>350.0</float></attr></node><node id=\"id27\"><attr name=\"description\"><string>n11</string></attr><attr name=\"x\"><float>450.0</float></attr><attr name=\"y\"><float>250.0</float></attr></node><node id=\"id28\"><attr name=\"description\"><string>n12</string></attr><attr name=\"x\"><float>500.0</float></attr><attr name=\"y\"><float>350.0</float></attr></node><node id=\"id29\"><attr name=\"description\"><string>n13</string></attr><attr name=\"x\"><float>450.0</float></attr><attr name=\"y\"><float>450.0</float></attr></node><node id=\"id30\"><attr name=\"description\"><string>n14</string></attr><attr name=\"x\"><float>350.0</float></attr><attr name=\"y\"><float>450.0</float></attr></node><node id=\"id31\"><attr name=\"description\"><string>n9</string></attr><attr name=\"x\"><float>200.0</float></attr><attr name=\"y\"><float>100.0</float></attr></node><node id=\"id32\"><attr name=\"description\"><string>n10</string></attr><attr name=\"x\"><float>100.0</float></attr><attr name=\"y\"><float>300.0</float></attr></node><node id=\"id33\"><attr name=\"description\"><string>n15</string></attr><attr name=\"x\"><float>150.0</float></attr><attr name=\"y\"><float>450.0</float></attr></node><node id=\"id34\"><attr name=\"description\"><string>n16</string></attr><attr name=\"x\"><float>50.0</float></attr><attr name=\"y\"><float>450.0</float></attr></node><node id=\"id35\"><attr name=\"description\"><string>n17</string></attr><attr name=\"x\"><float>50.0</float></attr><attr name=\"y\"><float>550.0</float></attr></node><node id=\"id36\"><attr name=\"description\"><string>n18</string></attr><attr name=\"x\"><float>150.0</float></attr><attr name=\"y\"><float>550.0</float></attr></node><node id=\"id37\"><attr name=\"description\"><string>n1</string></attr><attr name=\"x\"><float>100.0</float></attr><attr name=\"y\"><float>100.0</float></attr></node><node id=\"id38\"><attr name=\"description\"><string>n2</string></attr><attr name=\"x\"><float>200.0</float></attr><attr name=\"y\"><float>150.0</float></attr></node><node id=\"id39\"><attr name=\"description\"><string>n3</string></attr><attr name=\"x\"><float>150.0</float></attr><attr name=\"y\"><float>250.0</float></attr></node><node id=\"id40\"><attr name=\"description\"><string>n4</string></attr><attr name=\"x\"><float>50.0</float></attr><attr name=\"y\"><float>200.0</float></attr></node><node id=\"id41\"><attr name=\"description\"><string>n5</string></attr><attr name=\"x\"><float>300.0</float></attr><attr name=\"y\"><float>150.0</float></attr></node><node id=\"id42\"><attr name=\"description\"><string>n6</str",""   );
            hallo = hallo.replace("></attr></edge><edge from=\"id3\" id=\"id1\" to=\"id2\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id3\" id=\"id1\" to=\"id4\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id11\" id=\"id1\" to=\"id10\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id11\" id=\"id1\" to=\"id12\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id5\" id=\"id1\" to=\"id2\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id5\" id=\"id1\" to=\"id6\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id5\" id=\"id1\" to=\"id13\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id8\" id=\"id1\" to=\"id7\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id8\" id=\"id1\" to=\"id3\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id8\" id=\"id1\" to=\"id14\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id8\" id=\"id1\" to=\"id15\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id15\" id=\"id1\" to=\"id16\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id13\" id=\"id1\" to=\"id5\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id16\" id=\"id1\" to=\"id17\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id6\" id=\"id1\" to=\"id5\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id6\" id=\"id1\" to=\"id7\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id6\" id=\"id1\" to=\"id9\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id9\" id=\"id1\" to=\"id6\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id9\" id=\"id1\" to=\"id10\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id18\" id=\"id1\" to=\"id15\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id2\" id=\"id1\" to=\"id1\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id2\" id=\"id1\" to=\"id3\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id14\" id=\"id1\" to=\"id8\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id17\" id=\"id1\" to=\"id18\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id7\" id=\"id1\" to=\"id6\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id7\" id=\"id1\" to=\"id8\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float></attr></edge><edge from=\"id7\" id=\"id1\" to=\"id12\"><attr name=\"description\"><string>id120</string></attr><attr name=\"cost\"><float>Optional[1.0]</float><", "");
            throw new IllegalArgumentException("Der Input war: " +(String) hallo);*/
            throw new ParseException();
        }

        Graph<N,A> graph = createGraph();
        Element root;
        try {
           root = xml.getRootElement();

        }catch (Exception e){
            throw new ParseException();
        }

        Element tiefer = root.getChild("graph");
        System.out.println("Rootelement is: " + root.getName());
        System.out.println("number of rootEles "+ root.getChildren().size());
        List children = tiefer.getChildren();



        for (int i = 0; i < children.size(); i++) {
            Element element = (Element) children.get(i);
            nodeOrEdge.addAll(element.getChildren());
            System.out.println(element.getName() + " lol "+ element.getAttributeValue("id"));

            if(element.getName().equals("edge")){
                addEdge(graph, element, nodeMap);
            }else{
                XYNode zwischenspeichernode = readNode(element);
                graph.addNode((N) zwischenspeichernode);

                nodeMap.put( element.getAttributeValue("id"),zwischenspeichernode);

            }
        }
        for (int i = 0; i < nodeOrEdge.size(); i++) {
            Element element = (Element) nodeOrEdge.get(i);
            System.out.println(element.getAttributeValue("name")); //todo das hier sagt quasi die innerentags nach edge/node an z.B. description cost etc

            String tag = element.getValue().replace("\n", "");
            letzeTags.add(tag); //todo hier steht quasi das drin, was im innersten tag steht
            System.out.println(tag);

        }



        //todo neuer Graph wird erstellt


        System.out.println("hello");
        /*if(Objects.isNull(graph)){
            throw new IllegalArgumentException("hier geht etwas schief "+graph.toString());
        }*/
        return (G) graph;

    }

    private void addEdge(Graph graph, Element element, Map<String, XYNode> map) throws ParseException {//todo Zwischenfrage, wäre es nicht hier auch schon schlau die nodes zu den Graphen hinzuzufügen
        XYNode start = map.get(element.getAttributeValue("from"));
        XYNode dest = map.get(element.getAttributeValue("to"));
        int iMerken = 0;
        boolean hasOptional = false;
        /*System.out.println(element.getName());*/
        List<Element> unterpunkte = element.getChildren();
        Optional<Double> op = Optional.empty();
        for (int i = 0; i < unterpunkte.size(); i++) {
            System.out.println(unterpunkte.get(i).getAttributeValue("name"));
            if(unterpunkte.get(i).getAttributeValue("name").equals("cost")){
                hasOptional = true;
                iMerken = i;
            }
        }
        if(hasOptional){

            String leerzeichenLoeschen = unterpunkte.get(iMerken).getValue();

            leerzeichenLoeschen = leerzeichenLoeschen.replace(" ",""); // todo ging auch schon ohne Double nicht durch
            if(leerzeichenLoeschen.contains("Optional")){
                leerzeichenLoeschen=leerzeichenLoeschen.replace("Optional[","");
                leerzeichenLoeschen=leerzeichenLoeschen.replace("]","");
            }
            System.out.println("optional anlegen "+leerzeichenLoeschen);
            try {


                if(leerzeichenLoeschen.equals("dist=1.0")){
                    graph.addEdge(start,dest,Optional.of(leerzeichenLoeschen));
                }else{
                    Optional<Double> hallo = Optional.of(Double.parseDouble(leerzeichenLoeschen));
                    graph.addEdge(start,dest,hallo);
                }


            }catch (Exception e){
                throw new IllegalArgumentException("optional value = "+ leerzeichenLoeschen);
            }


        }else {
            if(Objects.isNull(start) || Objects.isNull(dest)){
                throw new ParseException();
            }
            graph.addEdge(start,dest,op);
        }



    }

    //writer schließender end tag gxl

    abstract public Graph<N,A> createGraph();

    abstract public String readNodeId(Node node, Element element);

    abstract public XYNode readNode(Element element) throws ParseException;

    abstract public Optional<A> readAnnotation(Element element);

    private static org.w3c.dom.Document convertStringToXMLDocument(String xmlString)
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
            /*Document doc = (Document) builder.parse(new InputSource(new StringReader(xmlString)));*/
            org.w3c.dom.Document doc = (org.w3c.dom.Document) builder.parse(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));
            return doc;
        }

        catch (Exception e)
        {
            System.out.println(e);

        }
        return null;
    }
}


