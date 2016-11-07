package cs311.graph;

import java.util.List;

/**
 * Created by taylorsdugger on 11/6/16.
 */
public class Graph implements IGraph{

    @Override
    public void setDirectedGraph() {

    }

    @Override
    public void setUndirectedGraph() {

    }

    @Override
    public boolean isDirectedGraph() {
        return false;
    }

    @Override
    public void addVertex(String vertexName) throws DuplicateVertexException {

    }

    @Override
    public void addVertex(String vertexName, Object vertexData) throws DuplicateVertexException {

    }

    @Override
    public void addEdge(String vertex1, String vertex2) throws DuplicateEdgeException, NoSuchVertexException {

    }

    @Override
    public void addEdge(String vertex1, String vertex2, Object edgeData) throws DuplicateEdgeException, NoSuchVertexException {

    }

    @Override
    public Object getVertexData(String vertexName) throws NoSuchVertexException {
        return null;
    }

    @Override
    public void setVertexData(String vertexName, Object vertexData) throws NoSuchVertexException {

    }

    @Override
    public Object getEdgeData(String vertex1, String vertex2) throws NoSuchVertexException, NoSuchEdgeException {
        return null;
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
