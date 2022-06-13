package de.jpp.io.interfaces;

import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;
import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;
import org.w3c.dom.Node;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwoDimGraphDotIO<N, A, G extends Graph<N, A>, F> implements GraphReader<N, A, G, F>,GraphWriter<N, A, G, F>{ //todo towodimgraph habe ich NA weggemachet
    Map<Integer, XYNode> map = new HashMap<>();
    List<String> nodeline = new ArrayList<>();
    List<String> edgeline = new ArrayList<>();
    int nodeId = 1;

    Map<XYNode, Integer> writeHashmap = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoDimGraphDotIO<?, ?, ?, ?> that = (TwoDimGraphDotIO<?, ?, ?, ?>) o;
        //return nodeId == that.nodeId;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId);
    }

    public static void main(String[] args) throws ParseException, IOException {
        TwoDimGraphDotIO twoDimGraphDotIO = new TwoDimGraphDotIO();
        TwoDimGraphDotIO twoDimGraphDotIO2 = new TwoDimGraphDotIO();
        TwoDimGraphDotIO twoDimGraphDotIO3 = new TwoDimGraphDotIO();

        TwoDimGraph twoDimGraph3 = (TwoDimGraph) twoDimGraphDotIO3.read("digraph{1[label=n1x=100.0y=100.0]2[label=n2x=200.0y=150.0]3[label=n3x=150.0y=250.0]4[label=n4x=50.0y=200.0]5[label=n5x=300.0y=150.0]6[label=n6x=350.0y=250.0]7[label=n7x=300.0y=350.0]8[label=n8x=200.0y=350.0]9[label=n11x=450.0y=250.0]10[label=n12x=500.0y=350.0]11[label=n13x=450.0y=450.0]12[label=n14x=350.0y=450.0]13[label=n9x=200.0y=100.0]14[label=n10x=100.0y=300.0]15[label=n15x=150.0y=450.0]16[label=n16x=50.0y=450.0]17[label=n17x=50.0y=550.0]18[label=n18x=150.0y=550.0]1->2[1.0]1->4[1.0]1->13[1.0]4->3[1.0]4->1[1.0]4->14[1.0]12->11[1.0]12->7[1.0]3->2[1.0]3->4[1.0]11->10[1.0]11->12[1.0]5->2[1.0]5->6[1.0]5->13[1.0]8->7[1.0]8->3[1.0]8->14[1.0]8->15[1.0]15->16[1.0]13->5[1.0]16->17[1.0]6->5[1.0]6->7[1.0]6->9[1.0]9->6[1.0]9->10[1.0]18->15[1.0]2->1[1.0]2->3[1.0]14->8[1.0]17->18[1.0]7->6[1.0]7->8[1.0]7->12[1.0]10->9[1.0]10->11[1.0]}");



        TwoDimGraph twoDimGraph2 = (TwoDimGraph) twoDimGraphDotIO.read("digraph{\n" +
                "\t1    [label=n16 x=50.0 y=450.0]\n" +
                "\t2 [label=n7 x=300.0 y=350.0  ]\n" +
                "\t3 [label=n1 x=100.0 y=100.0]\n" +
                "\t5 [label=n4 x=50.0 y=200.0]\n" +
                "\t4 [label=n11 x=450.0 y=250.0]\n" +
                "\t6 [label=n8 x=200.0 y=350.0]\n" +
                "\t7 [label=n3 x=150.0 y=250.0]\n" +
                "\t8 [label=n10 x=100.0 y=300.0]\n" +
                "\t9 [label=n15 x=150.0 y=450.0]\n" +
                "\t10 [label=n18 x=150.0 y=550.0]\n" +
                "\t11 [label=n6 x=350.0 y=250.0]\n" +
                "\t12  [label=n13      x=450.0 y=450.0]\n" +
                "\t13 [x=350.0 y=450.0     label=n14]\n" +
                "\t14 [label=n12 x=500.0 y=350.0]\n" +
                "\t15 [label=n9 x=200.0 y=100.0]\n" +
                "\t16 [label=n17 y=550.0 x=50.0]\n" +
                "\t17 [label=n2 x=200.0 y=150.0]\n" +
                "\t18 [label=n5 x=300.0 y=150.0]\n" +
                "\n" +
                "\t1 -> 16 [ dist=1.0]\n" +
                "\t5 -> 7 [dist=1.0]\n" +
                "\t2 -> 11 [dist=1.0 ]\n" +
                "\t2 -> 6 [dist=1.0]\n" +
                "\t2 -> 13 [dist=1.0 ]\n" +
                "\t3 -> 17 [dist=1.0]\n" +
                "\t3 -> 5 [dist=1.0]\n" +
                "\t3 -> 15 [dist=1.0]\n" +
                "\t4 -> 11 [dist=1.0]\n" +
                "\t4 -> 14 [dist=1.0]\n" +

                "\t5 -> 3 [dist=1.0]\n" +
                "\t5 -> 8 [dist=1.0]\n" +
                "\t6 -> 2 [dist=1.0]\n" +
                "\t6 -> 7 [dist=1.0]\n" +
                "\t6 -> 8 [dist=1.0]\n" +
                "\t6 -> 9 [dist=1.0]\n" +
                "\t7 -> 17 [dist=1.0]\n" +
                "\t7 -> 5 [dist=1.0]\n" +
                "\t8 -> 6 [dist=1.0]\n" +
                "\t14 -> 4 [dist=1.0]\t\n" +
                "\t9 -> 1 [dist=1.0]\n" +
                "\t10 -> 9 [dist=1.0]\n" +
                "\t11 -> 18 [dist=1.0]\n" +
                "\t11 -> 2 [dist=1.0]\n" +
                "\t18 -> 15 [dist=1.0]\n" +
                "\t11 -> 4 [dist=1.0]\n" +
                "\t12 -> 14 [dist=1.0]\n" +
                "\t17 -> 3 [dist=1.0]\n" +
                "\t12 -> 13 [dist=1.0]\n" +
                "\t13 -> 12 [dist=1.0]\n" +
                "\t13 -> 2 [dist=1.0]\n" +

                "\t14 -> 12 [dist=1.0]\n" +
                "\t15 -> 18 [dist=1.0]\n" +
                "\t16 -> 10 [dist=1.0]\n" +

                "\t17 -> 7 [dist=1.0]\n" +
                "\t18 -> 17 [dist=1.0]\n" +
                "\t18 -> 11 [dist=1.0]\n" +
                "}");

        TwoDimGraph twoDimGraph23 = (TwoDimGraph) twoDimGraphDotIO2.read("digraph{\n" +
                "\t1    [label=n16 x=50.0 y=450.0]\n" +
                "\t2 [label=n7 x=300.0 y=350.0  ]\n" +
                "\t3 [label=n1 x=100.0 y=100.0]\n" +
                "\t5 [label=n4 x=50.0 y=200.0]\n" +
                "\t7 [label=n3 x=150.0 y=250.0]\n" +
                "\t8 [label=n10 x=100.0 y=300.0]\n" +
                "\t4 [label=n11 x=450.0 y=250.0]\n" +
                "\t6 [label=n8 x=200.0 y=350.0]\n" +

                "\t9 [label=n15 x=150.0 y=450.0]\n" +
                "\t10 [label=n18 x=150.0 y=550.0]\n" +
                "\t11 [label=n6 x=350.0 y=250.0]\n" +
                "\t13 [x=350.0 y=450.0     label=n14]\n" +
                "\t12  [label=n13      x=450.0 y=450.0]\n" +
                "\t15 [label=n9 x=200.0 y=100.0]\n" +

                "\t14 [label=n12 x=500.0 y=350.0]\n" +

                "\t16 [label=n17 y=550.0 x=50.0]\n" +
                "\t17 [label=n2 x=200.0 y=150.0]\n" +
                "\t18 [label=n5 x=300.0 y=150.0]\n" +
                "\n" +
                "\t1 -> 16 [ dist=1.0]\n" +
                "\t5 -> 7 [dist=1.0]\n" +
                "\t2 -> 13 [dist=1.0 ]\n" +
                "\t3 -> 17 [dist=1.0]\n" +
                "\t2 -> 11 [dist=1.0 ]\n" +
                "\t2 -> 6 [dist=1.0]\n" +

                "\t3 -> 5 [dist=1.0]\n" +
                "\t3 -> 15 [dist=1.0]\n" +
                "\t4 -> 11 [dist=1.0]\n" +
                "\t4 -> 14 [dist=1.0]\n" +

                "\t5 -> 3 [dist=1.0]\n" +
                "\t5 -> 8 [dist=1.0]\n" +
                "\t7 -> 17 [dist=1.0]\n" +
                "\t7 -> 5 [dist=1.0]\n" +
                "\t6 -> 2 [dist=1.0]\n" +
                "\t6 -> 7 [dist=1.0]\n" +
                "\t6 -> 8 [dist=1.0]\n" +
                "\t6 -> 9 [dist=1.0]\n" +

                "\t8 -> 6 [dist=1.0]\n" +
                "\t14 -> 4 [dist=1.0]\t\n" +
                "\t9 -> 1 [dist=1.0]\n" +
                "\t10 -> 9 [dist=1.0]\n" +
                "\t13 -> 12 [dist=1.0]\n" +
                "\t13 -> 2 [dist=1.0]\n" +
                "\t11 -> 18 [dist=1.0]\n" +
                "\t18 -> 15 [dist=1.0]\n" +
                "\t11 -> 2 [dist=1.0]\n" +

                "\t11 -> 4 [dist=1.0]\n" +
                "\t12 -> 14 [dist=1.0]\n" +
                "\t17 -> 3 [dist=1.0]\n" +
                "\t12 -> 13 [dist=1.0]\n" +


                "\t14 -> 12 [dist=1.0]\n" +
                "\t15 -> 18 [dist=1.0]\n" +
                "\t16 -> 10 [dist=1.0]\n" +

                "\t17 -> 7 [dist=1.0]\n" +
                "\t18 -> 17 [dist=1.0]\n" +
                "\t18 -> 11 [dist=1.0]\n" +
                "}");
        System.out.println(twoDimGraph2.equals(twoDimGraph23));
        System.out.println(twoDimGraph23.equals(twoDimGraph2));

//        twoDimGraphDotIO.read(null);
        TwoDimGraph twoDimGraph = new TwoDimGraph();
        XYNode eins = new XYNode("hallo", 2,3);
        XYNode zwei = new XYNode("was geht", 4,5);
        twoDimGraph.addNodes(eins,zwei);
        twoDimGraph.addEdge(eins, zwei, Optional.of(2.0));
        twoDimGraphDotIO.write(twoDimGraph);
    }

    public TwoDimGraphDotIO(){

    }

    public void parseLine(TwoDimGraph twoDimGraph, Map<Integer, XYNode> map, String s) throws ParseException {
        if(s.length() >= 18){ //todo hier habe ich 19 auf 18 heruntergestzt sonst geht der complex chaning nicht durch
            nodeline.add(s);
            parseNode(twoDimGraph, map,s);
        }else{
            edgeline.add(s);
            parseEdge(twoDimGraph,map,s);
        }
    }

    public void parseNode(TwoDimGraph twoDimGraph, Map<Integer, XYNode> map, String s) throws ParseException {
        String mapIndex = reg(s,"\\d+\\[");
        mapIndex = mapIndex.replace("[","");
        String x = reg(s,"x=\\d+.\\d");
        x = x.replace("x=", "");
        String y = reg(s,"y=\\d+.\\d");
        y = y.replace("y=", "");
        try {
            String label = reg(s,"n\\d+");
            XYNode xyNode = new XYNode(label, Double.parseDouble(x),Double.parseDouble(y));
            twoDimGraph.addNodes(xyNode);
            map.put(Integer.parseInt(mapIndex), xyNode);
        }catch (Exception e){
            XYNode xyNode = new XYNode("LabelNichtDa", Double.parseDouble(x),Double.parseDouble(y));
            twoDimGraph.addNodes(xyNode);
            map.put(Integer.parseInt(mapIndex), xyNode);
        }


    }
    public void parseEdge(TwoDimGraph twoDimGraph, Map<Integer, XYNode> map, String s) throws ParseException {
        String start = reg(s,"\\d+-");
        start = start.replace("-", "");
        String dest = reg(s,">\\d+");
        dest = dest.replace(">", "");
        String annotation = null;
        if(s.contains("dist")){
            annotation = reg(s,"dist=\\d+.\\d");
            annotation = annotation.replace("dist=", "");
        }else {
            annotation = reg(s,"\\[\\d+.\\d");
            annotation = annotation.replace("[", "");
        }


        XYNode startXY = map.get(Integer.parseInt(start));
        XYNode destXY = map.get(Integer.parseInt(dest));
        twoDimGraph.addEdge(startXY, destXY, Optional.of(Double.parseDouble(annotation)));
    }

    public Map<String,String> parseAttributes(String s) throws ParseException {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> stringStringMap = new HashMap<>();

        stringBuilder.append(s);
        String string = reg("lol" , "\n");
        return stringStringMap;
    }


    @Override
    public G read(F input) throws ParseException {
        TwoDimGraph twoDimGraph = new TwoDimGraph();
        //String content = Files.readString(Path.of("Val.dot"), StandardCharsets.US_ASCII);
        //todo diese Zeile hier wieder aktiv machen
        String content = (String) input;
        String orginalSpeicherung = content;
        content = content.replace("\n","");
        content = content.replace("\t","");

        content = content.replace("digraph{","");

        if(content.contains("\"")){

            parseAnnoying(twoDimGraph, map, content);
            return (G) twoDimGraph;
        }
        content = content.replace("}","");
        content = content.replace(" ","");
        String[] line = content.split("]");

        try {

            for (int i = 0; i < line.length; i++) {
                parseLine(twoDimGraph,map,line[i]);
            }
        }catch (Exception e){
            throw new ParseException(e);
            //throw new IllegalArgumentException("Inhalt ="+orginalSpeicherung);
        }


        return (G) twoDimGraph;
    }

    public void parseAnnoying(TwoDimGraph twoDimGraph, Map<Integer, XYNode> map, String s) throws ParseException {
        String mapIndex = reg(s, "^\\d");
        String label = reg(s, "\\\".*\\\"");
        label = label.replace("\"", "");
        //label = label.replace("}","[}]");
        String x = reg(s,"x=\\d+");
        x = x.replace("x=", "");
        String y = reg(s,"y=\\d+");
        y = y.replace("y=", "");
        XYNode xyNode = new XYNode(label, Double.parseDouble(x),Double.parseDouble(y));
        twoDimGraph.addNodes(xyNode);
        map.put(Integer.parseInt(mapIndex), xyNode);

    }

    @Override
    public F write(G graph) throws ParseException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("digraph{");
        List nodeList = new ArrayList<>();
        if(Objects.isNull(graph.getNodes())){

            throw new ParseException();
        }else{
            nodeList.addAll(graph.getNodes());
        }


        try {
            for (int i = 0; i < nodeList.size(); i++) {
                stringBuilder.append(writeNode((XYNode) nodeList.get(i)));
            }
            for(Edge<N,A> e: graph.getEdges()){
                stringBuilder.append(writeEdge(e));
            }

        }catch (Exception e){
            throw new ParseException();
        }

        stringBuilder.append("}");

        return (F) stringBuilder.toString();
    }

    public String writeNode(XYNode node){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(nodeId);
        writeHashmap.put(node, nodeId);
        nodeId +=1;
        stringBuilder.append("[label=");
        stringBuilder.append(node.getLabel());
        stringBuilder.append("x=");
        stringBuilder.append(node.getX());
        stringBuilder.append("y=");
        stringBuilder.append(node.getY());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public String writeEdge(Edge edge){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(writeHashmap.get(edge.getStart()));
        stringBuilder.append("->");
        stringBuilder.append(writeHashmap.get(edge.getDestination()));
        if(edge.getAnnotation().isPresent()){
            stringBuilder.append("[");
            stringBuilder.append(edge.getAnnotation().get());
            stringBuilder.append("]");
        }
        return stringBuilder.toString();
    }


    public String reg(String s, String pattern) throws ParseException {

        Pattern patt = Pattern.compile(pattern);
        Matcher matcher = patt.matcher(s);

        if (matcher.find()) {
            return matcher.group(); // you can get it from desired index as well
        } else {
            try {
                matcher.group();
            }catch (Exception e){
                throw new ParseException(e);
            }
            return null;//bis hier hin wird er eh nicht kommen

        }
    }
}

