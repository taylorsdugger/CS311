package cs311.hw7.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by taylorsdugger on 11/3/16.
 */

public class Graph<V,E> implements IGraph{

    private boolean isDirected;
    private int numVertices = 0;
    private int numEdges = 0;

    private List<Vertex> vertices = new LinkedList<>();
    private List<Edge> edges = new LinkedList<>();

    private HashMap<Vertex, TreeSet<Vertex>> adjacency;

    public Vertex<V> ver;

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

        if(!vertices.contains(new Vertex<>(vertexName, null))){
            v = new Vertex(vertexName, null);
            vertices.add(v);
            //adjacency.put(v, new TreeSet<>());
            numVertices++;
        }else{
            throw new DuplicateVertexException();
        }

    }

    @Override
    public void addVertex(String vertexName, Object vertexData) throws DuplicateVertexException {
        Vertex v;

        if(!vertices.contains(new Vertex<>(vertexName, vertexData))){
            v = new Vertex(vertexName, vertexData);
            vertices.add(v);
            //adjacency.put(v, new TreeSet<>());
            numVertices++;
        }else{
            throw new DuplicateVertexException();
        }
    }

    @Override
    public void addEdge(String vertex1, String vertex2) throws DuplicateEdgeException, NoSuchVertexException {
        Edge e;
        boolean fail1 = true;
        boolean fail2 = true;

        for(int i=0; i < numVertices; i++){
            if(vertices.get(i).getVertexName() == vertex1){
                fail1 = false;
            }else if(vertices.get(i).getVertexName() == vertex2){
                fail2 = false;
            }
        }

        if(fail1 == true || fail2 == true) throw new NoSuchVertexException();

        if(isDirected) {
            if (!edges.contains(new Edge(vertex1, vertex2, null))) {
                e = new Edge(vertex1, vertex2, null);
                edges.add(e);

                numEdges++;
            } else {
                throw new DuplicateEdgeException();
            }
        }else { //undirected

            if (!(edges.contains(new Edge(vertex1, vertex2, null)) || edges.contains(new Edge(vertex2, vertex1, null)))) {
                e = new Edge(vertex1, vertex2, null);
                edges.add(e);

                numEdges++;
            } else {
                throw new DuplicateEdgeException();
            }
        }
     }

    @Override
    public void addEdge(String vertex1, String vertex2, Object edgeData) throws DuplicateEdgeException, NoSuchVertexException {
        Edge e;

        if(isDirected) {
            if (!edges.contains(new Edge(vertex1, vertex2, edgeData))) {
                e = new Edge(vertex1, vertex2, edgeData);
                edges.add(e);

                numEdges++;
            } else {
                throw new DuplicateEdgeException();
            }
        }else { //undirected
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

        for(int i=0; i < numVertices; i++){
            v = vertices.get(i);
            if(v.getVertexName().equals(vertexName)) return v.getVertexData();
        }

        throw new NoSuchVertexException();
    }

    @Override
    public void setVertexData(String vertexName, Object vertexData) throws NoSuchVertexException {
        Vertex v;

        for(int i=0; i < numVertices; i++){
            v = vertices.get(i);
            if(v.getVertexName().equals(vertexName)) {
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

            for(int i = 0; i < numEdges; i++){
                e = edges.get(i);
                if(e.getVertexName1().equals(vertex1) && e.getVertexName2().equals(vertex2))
                    return e.getEdgeData();
            }

        }else{ //nonDirected
            for(int i = 0; i < numEdges; i++){
                e = edges.get(i);
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

            for(int i = 0; i < numEdges; i++){
                e = edges.get(i);
                if(e.getVertexName1().equals(vertex1) && e.getVertexName2().equals(vertex2))
                    edges.remove(i);
                Edge newEdge = new Edge(vertex1,vertex2,edgeData);
                edges.add(i,newEdge);
                return;
            }

        }else{ //nonDirected
            for(int i = 0; i < numEdges; i++){
                e = edges.get(i);
                if(e.getVertexName1().equals(vertex1) || e.getVertexName1().equals(vertex2))
                    edges.remove(i);
                Edge newEdge = new Edge(vertex1,vertex2,edgeData);
                edges.add(i,newEdge);
                return;
            }

        }

        throw new NoSuchEdgeException();
    }

    @Override
    public Vertex getVertex(String VertexName) {

        for(int i=0; i < numVertices; i++){
                if(vertices.get(i).getVertexName() == VertexName) return vertices.get(i);
        }

        throw new NoSuchVertexException();
    }

    @Override
    public Edge getEdge(String vertexName1, String vertexName2) {
        if(getVertex(vertexName1) == null || getVertex(vertexName2) == null)
            throw new NoSuchVertexException();

        if(isDirected) {
            for (int i = 0; i < numEdges; i++) {
                if (edges.get(i).getVertexName1() == vertexName1 && edges.get(i).getVertexName2() == vertexName2) {
                    return edges.get(i);
                }
            }

        }else{ //undirected
            for (int i = 0; i < numEdges; i++) {
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
                if (edges.get(i).getVertexName1() == vertex) {
                    v.add(getVertex(edges.get(i).getVertexName2()));
                }
            }
        }else{//undirected
            for (int i = 0; i < numEdges; i++) {
                if (edges.get(i).getVertexName1() == vertex) {
                    v.add(getVertex(edges.get(i).getVertexName2()));
                } else if (edges.get(i).getVertexName2() == vertex) {
                    v.add(getVertex(edges.get(i).getVertexName1()));
                }
            }
        }

        return v;
    }

}
