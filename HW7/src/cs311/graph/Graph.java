package cs311.graph;

import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by taylorsdugger on 11/6/16.
 */

public class Graph implements IGraph{

    private boolean isDirected;
    private int numVertices = 0;
    private int numEdges = 0;

    private List<Vertex> vertices;
    private List<Edge> edges;

    private HashMap<Vertex, TreeSet<Vertex>> adjacency;


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
        Vertex v;

        if(!vertices.contains(new Vertex<>(vertexName, null))){
            v = new Vertex(vertexName, null);
            vertices.add(v);
            adjacency.put(v, new TreeSet<>());
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
            adjacency.put(v, new TreeSet<>());
            numVertices++;
        }else{
            throw new DuplicateVertexException();
        }
    }

    @Override
    public void addEdge(String vertex1, String vertex2) throws DuplicateEdgeException, NoSuchVertexException {
        Edge e;

        if(isDirected) {
            if (!edges.contains(new Edge(vertex1, vertex2, null))) {
                e = new Edge(vertex1, vertex2, null);
                edges.add(e);

                numEdges++;
            } else {
                throw new DuplicateEdgeException();
            }
        }else { //undirected
            if (!edges.contains(new Edge(vertex1, vertex2, null)) || !edges.contains(new Edge(vertex2, vertex1, null))) {
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
            if (!edges.contains(new Edge(vertex1, vertex2, edgeData)) || !edges.contains(new Edge(vertex2, vertex1, edgeData))) {
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
            if(v.getVertexName().equals(vertexName)) return v;
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

    }

    @Override
    public Vertex getVertex(String VertexName) {
        return null;
    }

    @Override
    public Edge getEdge(String vertexName1, String vertexName2) {
        if(getVertex(vertexName1) == null || getVertex(vertexName2) == null)
            throw new NoSuchVertexException();

        //return adjacency.get(vertices.get(vertexName1)).contains(vertices.get(vertexName2));

        return null;
    }

    @Override
    public List<Vertex> getVertices() {
        return null;
    }

    @Override
    public List<Edge> getEdges() {
        return null;
    }

    @Override
    public List<Vertex> getNeighbors(String vertex) {
        return null;
    }


}
