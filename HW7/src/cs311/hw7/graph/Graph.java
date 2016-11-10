package cs311.hw7.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by taylorsdugger on 11/3/16.
 */

public class Graph<V,E> implements IGraph{

    private boolean isDirected;
    private int numVertices = 0;
    private int numEdges = 0;

    private List<Vertex> vertices = new LinkedList<>();//list of vertices
    private List<Edge> edges = new LinkedList<>();//list of edges

    @Override
    public void setDirectedGraph() {
        isDirected = true;
    }

    @Override
    public void setUndirectedGraph() {
        isDirected = false;
    }

    @Override
    public boolean isDirectedGraph() {
        return isDirected;
    }

    @Override
    public void addVertex(String vertexName) throws DuplicateVertexException {
        Vertex<V> v;

        //check to see if it contains it already
        if(!vertices.contains(new Vertex<>(vertexName, null))){
            v = new Vertex(vertexName, null);
            vertices.add(v);//if not add
            numVertices++;//++ this up
        }else{
            throw new DuplicateVertexException();//if already in, throw
        }

    }

    @Override
    public void addVertex(String vertexName, Object vertexData) throws DuplicateVertexException {
        Vertex v;

        //check to see if it contains it already
        if(!vertices.contains(new Vertex<>(vertexName, vertexData))){
            v = new Vertex(vertexName, vertexData);
            vertices.add(v);//if not add
            numVertices++;
        }else{
            throw new DuplicateVertexException();//if not throw
        }
    }

    @Override
    public void addEdge(String vertex1, String vertex2) throws DuplicateEdgeException, NoSuchVertexException {
        Edge e;
        //my checks to see if vertices exists
        boolean fail1 = true;
        boolean fail2 = true;

        //this goes through to see if the vertices are legit
        for(int i=0; i < numVertices; i++){
            if(vertices.get(i).getVertexName() == vertex1){
                fail1 = false;
            }else if(vertices.get(i).getVertexName() == vertex2){
                fail2 = false;
            }
        }

        //if they dont, then throw
        if(fail1 == true || fail2 == true) throw new NoSuchVertexException();

        //for directed
        if(isDirected) {
            //if the edge isnt already in then go ahead
            if (!edges.contains(new Edge(vertex1, vertex2, null))) {
                e = new Edge(vertex1, vertex2, null);
                edges.add(e);//add the new edge

                numEdges++;
            } else {
                throw new DuplicateEdgeException();//if it is, then throw
            }
        }else { //undirected

            //need to check if edge exists, since its undirected, check both ways
            if (!(edges.contains(new Edge(vertex1, vertex2, null)) || edges.contains(new Edge(vertex2, vertex1, null)))) {
                e = new Edge(vertex1, vertex2, null);
                edges.add(e);//add new edge

                numEdges++;
            } else {
                throw new DuplicateEdgeException();//then throw
            }
        }
     }

    @Override
    public void addEdge(String vertex1, String vertex2, Object edgeData) throws DuplicateEdgeException, NoSuchVertexException {
        Edge e;

        //my checks to see if vertices exists
        boolean fail1 = true;
        boolean fail2 = true;

        //this goes through to see if the vertices are legit
        for(int i=0; i < numVertices; i++){
            if(vertices.get(i).getVertexName() == vertex1){
                fail1 = false;
            }else if(vertices.get(i).getVertexName() == vertex2) {
                fail2 = false;
            }
        }

        //if they dont, then throw
        if(fail1 == true || fail2 == true) throw new NoSuchVertexException();


        if(isDirected) {
            //if the edge isnt already in then go ahead
            if (!edges.contains(new Edge(vertex1, vertex2, edgeData))) {
                e = new Edge(vertex1, vertex2, edgeData);
                edges.add(e);

                numEdges++;
            } else {
                throw new DuplicateEdgeException();
            }
        }else { //undirected
            //need to check if edge exists, since its undirected, check both ways
            if (!(edges.contains(new Edge(vertex1, vertex2, edgeData)) || edges.contains(new Edge(vertex2, vertex1, edgeData)))) {
                e = new Edge(vertex1, vertex2, edgeData);
                edges.add(e);

                numEdges++;
            } else {
                throw new DuplicateEdgeException();
            }
        }
    }

    @Override
    public Object getVertexData(String vertexName) throws NoSuchVertexException {
        Vertex v;

        //look for the vetex in my list
        for(int i=0; i < numVertices; i++){
            v = vertices.get(i);//get each one
            if(v.getVertexName().equals(vertexName)) return v.getVertexData();//then return it
        }

        throw new NoSuchVertexException();
    }

    @Override
    public void setVertexData(String vertexName, Object vertexData) throws NoSuchVertexException {
        Vertex v;

        //look for the vertex
        for(int i=0; i < numVertices; i++){
            v = vertices.get(i);
            if(v.getVertexName().equals(vertexName)) {
                //if bingo, then remove it and then make a new vertex and pop it in
                vertices.remove(i);
                vertices.add(new Vertex(vertexName, vertexData));
                return;
            }
        }

        throw new NoSuchVertexException();
    }

    @Override
    public Object getEdgeData(String vertex1, String vertex2) throws NoSuchVertexException, NoSuchEdgeException {
        Edge e;

        getVertex(vertex1);
        getVertex(vertex2);

        if(isDirected){
            //go through the edges looking for it
            for(int i = 0; i < numEdges; i++){
                e = edges.get(i);
                //since its directed, you need both to match
                if(e.getVertexName1().equals(vertex1) && e.getVertexName2().equals(vertex2))
                    return e.getEdgeData();
            }

        }else{ //nonDirected
            //go through edges
            for(int i = 0; i < numEdges; i++){
                e = edges.get(i);
                //since undirected, you only need one since a--b is same as b--a
                if(e.getVertexName1().equals(vertex1) || e.getVertexName1().equals(vertex2))
                    return e.getEdgeData();
            }

        }

        throw new NoSuchEdgeException();
    }

    @Override
    public void setEdgeData(String vertex1, String vertex2, Object edgeData) throws NoSuchVertexException, NoSuchEdgeException {

        Edge e;

        getVertex(vertex1);
        getVertex(vertex2);

        if(isDirected){
            //look for edge
            for(int i = 0; i < numEdges; i++){
                e = edges.get(i);
                if(e.getVertexName1().equals(vertex1) && e.getVertexName2().equals(vertex2))
                    //find edge and remove it
                    edges.remove(i);
                //then make new edge and add it back in
                Edge newEdge = new Edge(vertex1,vertex2,edgeData);
                edges.add(i,newEdge);
                return;
            }

        }else{ //nonDirected
            //look
            for(int i = 0; i < numEdges; i++){
                e = edges.get(i);
                if(e.getVertexName1().equals(vertex1) || e.getVertexName1().equals(vertex2))
                    //find edge and remove it
                    edges.remove(i);
                //then make new edge and add it back in
                Edge newEdge = new Edge(vertex1,vertex2,edgeData);
                edges.add(i,newEdge);
                return;
            }

        }

        throw new NoSuchEdgeException();
    }

    @Override
    public Vertex getVertex(String VertexName) {

        //go through vertices and find and return it
        for(int i=0; i < numVertices; i++){
                if(vertices.get(i).getVertexName() == VertexName) return vertices.get(i);
        }

        throw new NoSuchVertexException();
    }

    @Override
    public Edge getEdge(String vertexName1, String vertexName2) {

        if(getVertex(vertexName1) == null || getVertex(vertexName2) == null)
            throw new NoSuchVertexException();

        //if directed
        if(isDirected) {
            for (int i = 0; i < numEdges; i++) {
                //look for both to match up a->b
                if (edges.get(i).getVertexName1() == vertexName1 && edges.get(i).getVertexName2() == vertexName2) {
                    return edges.get(i);
                }
            }

        }else{ //undirected
            for (int i = 0; i < numEdges; i++) {
                //if undirected you need some more checks, since
                //a--b is same as b--a
                if ((edges.get(i).getVertexName1() == vertexName1)
                        || (edges.get(i).getVertexName1() == vertexName2)
                        && (edges.get(i).getVertexName2() == vertexName1)
                        || (edges.get(i).getVertexName2() == vertexName2)) {

                    return edges.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public List<Vertex> getVertices() {
        return vertices;
    }

    @Override
    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public List<Vertex> getNeighbors(String vertex) {
        List<Vertex> v = new LinkedList<Vertex>();

        if(isDirected) {
            for (int i = 0; i < numEdges; i++) {
                //look for all the vertices that are in one of the edges
                //then if they are return it cause it must be neighbor
                if (edges.get(i).getVertexName1() == vertex) {
                    v.add(getVertex(edges.get(i).getVertexName2()));
                }
            }
        }else{//undirected
            for (int i = 0; i < numEdges; i++) {
                //same here, but since undirected you need another if
                if (edges.get(i).getVertexName1() == vertex) {
                    v.add(getVertex(edges.get(i).getVertexName2()));
                    //this if checks both vertexNames since undirected
                    //you can have both ways
                } else if (edges.get(i).getVertexName2() == vertex) {
                    v.add(getVertex(edges.get(i).getVertexName1()));
                }
            }
        }

        return v;
    }

}
