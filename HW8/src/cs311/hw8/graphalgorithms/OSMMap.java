package cs311.hw8.graphalgorithms;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import cs311.hw8.graph.Graph;
import cs311.hw8.graph.IGraph;
import cs311.hw8.graph.IGraph.Edge;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.*;

public class OSMMap<V,E> {

    public IGraph<Location, Way> map;
    public double TSPDistance;
    public List<Integer> TSPRoute;

    /**
     *Constructor
     */
    public OSMMap(){
        map = new Graph<Location, Way>();
        map.setDirectedGraph();
    }

    /**
     *Total Distance
     * @param args arguments
     */
    public void main(String args[]) {

        OSMMap<Location, Way> newMap = new OSMMap<>();

        //Ames Map
        newMap.LoadMap(args[0]);

        System.out.print(TotalDistance());

    }

    /**
     * Shortest Path
     * @param args arguments
     */
    public void main2(String args[]){

        OSMMap<Location, Way> newMap = new OSMMap<>();

        //Ames Map
        newMap.LoadMap(args[0]);

        //Route file
        File file = new File(args[1]);

        //Scanner
        Scanner in;
        try {
            in = new Scanner(file);
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return;
        }

        List<Location> locationsFromFile = new ArrayList<>();
        List<String> pathFile = new ArrayList<>();
        List<String> streetPath;

        Location loc;

        //Go through the file
        while (in.hasNextLine()) {
            Double lat = in.nextDouble();
            Double lon = in.nextDouble();
            loc = new Location(lat, lon);

            locationsFromFile.add(loc);
            pathFile.add(newMap.ClosestRoad(loc));
        }

        //Go through and get shortest route
        int i = 0;
        while(i < locationsFromFile.size()-1){
            pathFile = newMap.ShortestRoute(locationsFromFile.get(i), locationsFromFile.get(i + 1));
            streetPath = newMap.StreetRoute(pathFile);

            System.out.println("Start Route: " + i + "\nFirst Node: " + pathFile.get(0));

            //print out street names
            for (int j = 0; j < streetPath.size(); j++) {
                System.out.println("Road: " + streetPath.get(j));
            }

            System.out.println("Last Node: " + pathFile.get(pathFile.size() - 1) + "\n");
            i++;
        }

        in.close();
    }


    /**
     *
     * @param fileName MapFile to load
     */
    public void LoadMap(String fileName){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        File file = new File(fileName);
        Document mapFile = null;

        try {

            InputStream in = new FileInputStream(file);
            Reader reader = new InputStreamReader(in, "UTF-8");
            InputSource is = new InputSource(reader);

            builder = factory.newDocumentBuilder();
            mapFile = builder.parse(is);

        } catch (ParserConfigurationException|SAXException|IOException e) {
            e.printStackTrace();
        }

        mapFile.getDocumentElement().normalize();

        //Look for tags
        NodeList node = mapFile.getElementsByTagName("node");
        NodeList way = mapFile.getElementsByTagName("way");

        Double latitude;
        Double longitude;

        //Saves aaaaloooot of time
        int nodeLen = node.getLength();
        int wayLen= way.getLength();

        //First go through the nodes
        for (int i = 0; i < nodeLen; i++) {
            Node n = node.item(i);

            //Check if its an element
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element nodeElement = (Element) n;

                //Then get the lat, lon, and id tags
                latitude = Double.valueOf(nodeElement.getAttribute("lat"));
                longitude = Double.valueOf(nodeElement.getAttribute("lon"));
                String id = nodeElement.getAttribute("id");

                Location loc = new Location(latitude, longitude);

                //then add it to map
                map.addVertex(id, loc);
            }
        }

        //Now go through the ways
        for (int j = 0; j < wayLen; j++) {

            //Grab each way node
            Node wayNodes = way.item(j);

            //some variable
            String name =  "";
            int nameGiven = 0;
            int highwayHere = 0;
            int isOneWay = 0;

            //check if its an element
            if (wayNodes.getNodeType() == Node.ELEMENT_NODE) {
                //make wayElement
                Element wayElement = (Element) wayNodes;

                //then get a list of nodes from that element
                NodeList tags = wayElement.getElementsByTagName("tag");
                NodeList nds = wayElement.getElementsByTagName("nd");

                int tagLength = tags.getLength();
                int ndsLength = nds.getLength();

                //For all k tags in element loop through and grab highway, name, and oneway.
                for (int m = 0; m < tagLength; m++) {
                    Node nn = tags.item(m);

                    if (nn.getNodeType() == Node.ELEMENT_NODE) {

                        Element tagElement = (Element) nn;
                        String kTag = tagElement.getAttribute("k");
                        String vTag = tagElement.getAttribute("yes");


                        //look for highway tag
                        if (kTag.equals("highway")) {
                            highwayHere = 1;
                        }
                        //look for nameTag
                        if (kTag.equals("name")) {
                            name = tagElement.getAttribute("v");
                            nameGiven= 1;
                        }
                        //look for oneway tags
                        if (kTag.equals("oneway") && vTag.equals("yes")) {
                            isOneWay = 1;
                        }
                    }
                }

                //If a node has highway and name
                if(highwayHere + nameGiven == 2) {

                    String vertices[] = new String[ndsLength];
                    String vertex = null;

                    int u = 0;
                    while(u < ndsLength){
                        Node ref = nds.item(u);

                        if (ref.getNodeType() == Node.ELEMENT_NODE) {
                            Element refElement = (Element) ref;
                            vertex = refElement.getAttribute("ref");

                            vertices[u] = vertex;
                        }
                        u++;
                    }

                    //Now add to map and make edges
                    int v = 0;
                    while(v < vertices.length-1){
                        String v1 = vertices[v];
                        String v2 = vertices[++v];

                        Location loc1 = map.getVertexData(v1);
                        Location loc2 = map.getVertexData(v2);

                        //call the distance calculator
                        double vertexDist = getDistance(loc1.getLatitude(), loc1.getLongitude(), loc2.getLatitude(), loc2.getLongitude());

                        map.addEdge(v1, v2, new Way(name, vertexDist));

                        //not a oneway so add other way
                        if (isOneWay == 0)
                            map.addEdge(v2, v1, new Way(name, vertexDist));
                    }
                }
            }
        }
    }

    /**
     *
     * @param l Location from closest
     * @return the closest road
     */
    public String ClosestRoad(Location l){

        //variables
        String closest = "";
        List<IGraph.Vertex<Location>> list = map.getVertices();
        int size = list.size();

        double min = 10000;
        double curr = 0;

        double lat = l.getLatitude();
        double lon = l.getLongitude();

        //go through the list
        for(int i = 0; i < size; i++){
            //get the current distance
            curr = getDistance(lat, lon, list.get(i).getVertexData().getLatitude(), list.get(i).getVertexData().getLongitude());

            //check to see if its smaller
            if((curr < min) && (map.getNeighbors(list.get(i).getVertexName()).size()) > 0){
                //and set the min
                min = curr;
                closest = list.get(i).getVertexName();
            }
        }

        return closest;

    }

    /**
     *
     * @param fromLocation from this location
     * @param toLocation to this location
     * @return list of String types that is the sequence of vertex ID names that gives the path
     */
    public List<String> ShortestRoute(Location fromLocation, Location toLocation){
        String loc1 = ClosestRoad(fromLocation);
        String loc2 = ClosestRoad(toLocation);

        //Get the shortest Path
        List<Edge<Way>> list = GraphAlgorithms.ShortestPath(this.map, loc1, loc2);
        List<String> pathOfRoute = new ArrayList<>();
        int size = list.size();

        //for the first one get 1 and 2
        pathOfRoute.add(list.get(0).getVertexName1());
        pathOfRoute.add(list.get(0).getVertexName2());

        //then go through and the rest of the vertices
        for(int i = 1; i < size; i++){
            pathOfRoute.add(list.get(i).getVertexName2());
        }

        return pathOfRoute;
    }

    /**
     *
     * @param vIDS vertex ID names
     * @return a List of street names from the beginning location to the end location
     */
    public List<String> StreetRoute(List<String> vIDS){
        List<String> streetNames = new ArrayList<>();

        //Go through list of IDS
        for(int i = 0; i < vIDS.size() -1; i++){
            //get edge name
            String edge = map.getEdge(vIDS.get(i), vIDS.get(i+1)).getEdgeData().getName();

            //get rid of duplicates
            if(!streetNames.contains(edge)){
                streetNames.add(edge);
            }
        }

        return streetNames;
    }

    /**
     *
     * @return approximate miles of roadway in the map
     */
    public double TotalDistance(){
        double dist = 0;

        //get edges
        List<Edge<Way>> edges = map.getEdges();

        //sum up distance
        for(int i = 0; i < edges.size(); i++){
            dist += edges.get(i).getEdgeData().getWeight();
        }

        //approximate
        return dist/2;
    }


    /**
     *Location class to get latitude and longitude
     */
    public static class Location{
        private double latitude, longitude;

        public Location(double latitude, double longitude){
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude(){
            return this.latitude;

        }

        public double getLongitude(){
            return this.longitude;

        }
    }

    /**
     * Way class to get and set name of way and weight
     */
    public static class Way implements IWeight{//using iweight here
        private String name;
        private double dist;

        public Way(String name, double distance){
            this.name = name;
            this.dist = distance;
        }

        public String getName(){
            return this.name;
        }

        //weight ==distance
        @Override
        public double getWeight(){
            return this.dist;
        }
    }




    /**********************************************************************************************************************************/
    //https://dzone.com/articles/distance-calculation-using-3
    public static double getDistance(double lat1, double long1, double lat2, double long2){
        double theta = long1 - long2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}