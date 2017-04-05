import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by taylor on 4/3/17.
 */
public class GraphProcessor {

    /**
     * The hashMap of graph
     */
    private HashMap<Integer, Vertex> graph;

    /**
     * The hashMap of the reverse graph
     */
    private HashMap<Integer,Vertex> graphR;

    /**
     * The output from SCC Algo
     */
    private HashMap<Integer, String> S;

    /**
     *
     */
    private ArrayList<ArrayList<String>> allSCCs;

    /**
     *
     */
    private int components = 0;

    /**
     *
     */
    private int largest = 0;

    /**
     * my finishTime 'counter'. FinishDFS said to set FinishTime[
     */
    private Stack<Vertex> finishTime;

    private int numVertices = 0;


    /**
     * GraphProcessor(String graphData) graphData hold the absolute path of a file that stores a
     * directed graph. This file will be of the following format: First line indicates number of vertices.
     * Each subsequent line lists a directed edge of the graph. The vertices of this graph are represented
     * as strings.
     * @param graphData hold the absolute path of a file that stores a
     * directed graph.
     */
    public GraphProcessor(String graphData){

        try{

            //First build the graph
            FileReader data = new FileReader(new File(graphData));

            Scanner s = new Scanner(data);

            numVertices = s.nextInt();

            graph = new HashMap<>();

            while(s.hasNext()){
                String first = s.next();
                String second = s.next();
                //System.out.println(first + "---->" + second);
                //if a new vertex is coming in at the first spot, put it in first
                if(!graph.containsKey(first.hashCode())){
                    Vertex g = new Vertex(first);
                    graph.put(first.hashCode(), g);
                }

                //add the edge connection to the second vertex
                graph.get(first.hashCode()).setEdge(second);

                //check the second vertex and make sure its in the graph
                if(!graph.containsKey(second.hashCode())){
                    Vertex g = new Vertex(second);
                    graph.put(second.hashCode(), g);
                }
            }

            s.close();

            //Now find the SCC's
            //using the private methods below derived from the lecture notes.
            SCC();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     *
     * @param v Vertex v
     * @return out degree of v
     */
    public int outDegree(String v){

        return 0;
    }

    /**
     *
     * @param u Vertex u
     * @param v Vertex v
     * @return true if u and v belond to same SCC
     */
    public boolean sameComponent(String u, String v){

        return false;
    }

    /**
     *
     * @param v Vertex v
     * @return Return all the vertices that belong to the same Strongly Connected
     * Component of v (including v).
     */
    public ArrayList<String> componentVertices(String v){

        return null;
    }

    /**
     *
     * @return size of largest component
     */
    public int largestComponent(){

        return largest;
    }

    /**
     *
     * @return num of strongly connected components
     */
    public int numComponents(){

        return components;
    }

    /**
     * This method returns an array
     * list of strings that represents the BFS path from u to v. Note that this method must return an
     * array list of Strings. First vertex in the path must be u and the last vertex must be v. If there is no
     * path from u to v, then this method returns an empty list.
     * @param u start point
     * @param v end point
     * @return Returns the BFS path from u to v.
     */
    public ArrayList<String> bfsPath(String u, String v){

        return null;
    }

    /**********************************************************************************************************/
    //Private methods start here. For all of these I used the template in the lecture notes DFS on Directed Graphs
    //I tried as best as I could to keep the naming convention similar to the notes and to comment each part as
    //the notes said like: 1), 2), 3).

    /**
     *
     */
    private void SCC(){
        //1) Input graph G

        //2) call computeOrder(g)
        //3)Let v1 be ordering of vertices that FinishTime(vi) > FinishTime(vi+1)
        //4)Unmark every vertex of V
        computeOrder();


        /*for (Vertex v: graph.values()) {
            System.out.println(v.getVertex() + "--->" + v.getEdges().toString());
        }
        System.out.println("\n");
        for (Vertex v: graphR.values()) {
            System.out.println(v.getVertex() + "--->" + v.getEdges().toString());
        }
        System.out.println("\n");
        //2. Let v1, v2, ... be ordering vertices of v such that FinishTime(vi) > FinishTime(vi+1)
        while(!finishTime.isEmpty()) {
            Vertex a = finishTime.pop();
            System.out.println(a.getVertex() + "--->" + a.getEdges().toString());
        }*/


        //5)for i in range [1...n]
        while (!finishTime.isEmpty()){
            Vertex curr = finishTime.pop();
            //if vi is unmarked
            if(!graphR.get(curr.getVertex().hashCode()).isVisited()){

                //Set S = {}
                S = new HashMap<>();

                //SccDFS()
                SccDFS(curr.getVertex(), S);

                //Output S
                for (String s: S.values()) {

                }

                //These help keep track for the public methods
                components++;

                if(S.size()>largest)
                    largest = S.size();

            }
        }

    }

    /**
     * The DFS of the graph
     * @param curr Vertex v
     * @param S ResultSet S
     */
    private void SccDFS(String curr, HashMap<Integer, String> S){

        //1) Mark v
        graphR.get(curr.hashCode()).setVisited(true);

        //2)S = S U {v}
        S.put(curr.hashCode(),curr);

        //3)For every u in <v,u> e E
        ArrayList<String> edge = graphR.get(curr.hashCode()).getEdges();

        for (String u: edge) {
            //if u is unmarked DFS(G,u)
            if(!graphR.get(u.hashCode()).isVisited()) {
                SccDFS(u, S);
            }
        }

    }

    /**
     * This computes the Order of the graph and gets it ready so we can find the SCC's
     */
    private void computeOrder(){
        //1) Input G
        //2) Compute G^r, reverse graph as graphR
        getGraphReverse();

        //3) Unmark every vertex in G
        for (Vertex v: graph.values()) {
            //System.out.println(v.getVertex() + "-->" + v.getEdges().toString());
            v.setVisited(false);
        }

        //unmark every vertex in G^r
        for (Vertex v: graphR.values()) {
            v.setVisited(false);
        }

        //4) counter = 0. I decided to use a stack instead of a counter because when going through
        //the vertices in order of finishTime in SCC Algo I found it easier to just push the smallest counter
        //to the stack instead of assigning it a counter value, so the counter is technically the order of the stack
        finishTime = new Stack<>();

        //if v is unmarked call finishDFS
        for(Vertex v: graph.values()){
            if(!v.isVisited()){
                finishDFS(v);
            }
        }

    }

    /**
     * This will reverse my graph, graph into a new graph, graphR, where the edges will be backwards
     * @return The reverse of the edges in the graph
     */
    private void getGraphReverse(){
        graphR = new HashMap<>();

        for (Vertex v: graph.values()) {

            ArrayList<String> vEdges = v.getEdges();

            for (String e: vEdges) {
                if(!graphR.containsKey(e.hashCode())){
                    Vertex temp = new Vertex(e);
                    graphR.put(e.hashCode(),temp);
                }
                //key1 v.getVertex().hashCode();
                graphR.get(e.hashCode()).setEdge(v.getVertex());

                if(!graphR.containsKey(v.getVertex().hashCode())){
                    Vertex vv = new Vertex(v.getVertex());
                    graphR.put(v.getVertex().hashCode(),vv);
                }
            }
        }

    }

    /**
     * This assigns a finishTime to each vertex in the form of a stack. The order of the stack is the order
     * of the finishTimes
     * @param v Vertex v
     */
    private void finishDFS(Vertex v){

        //1)Mark v
        graph.get(v.getVertex().hashCode()).setVisited(true);

        //2)for every u such that <v, u> in Edges
        ArrayList<String> edge = graph.get(v.getVertex().hashCode()).getEdges();

        for (String u: edge) {
            //2(a) if u is unmarked, DFS(G,u)
            if(!graph.get(u.hashCode()).isVisited()){
                finishDFS(graph.get(u.hashCode()));
            }
        }

        //3 and 4) counter++, FinishTime[v] = counter
        finishTime.push(v);
    }


}
