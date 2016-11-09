package cs311.hw7.graph;

import groovy.util.GroovyTestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Keane on 11/7/2016.
 */
public class GraphTest {
    Graph g;

    @Before
    public void setUp() throws Exception {
        g = new Graph<String, String>();
        g.addVertex("d1");
        g.addVertex("d2");
    }

    @Test
    public void setDirectedGraph() throws Exception {
        g.setDirectedGraph();
        assertTrue(g.isDirectedGraph());
    }

    @Test
    public void setUndirectedGraph() throws Exception {
        g.setUndirectedGraph();
        assertFalse(g.isDirectedGraph());
    }

    @Test
    public void isDirectedGraph() throws Exception {
        assertFalse(g.isDirectedGraph());
    }

    @Test
    public void addVertex() throws Exception {
        IGraph.Vertex v = new IGraph.Vertex("testVertex", null);
        g.addVertex("testVertex");
        assertEquals(v, g.getVertex("testVertex"));
    }

    @Test(expected = IGraph.DuplicateVertexException.class)
    public void addVertexDup() throws Exception {
        g.addVertex("d1");
    }

    @Test
    public void addVertex1() throws Exception {
        IGraph.Vertex v = new IGraph.Vertex("testVertex", "testData");
        g.addVertex("testVertex", "testData");
        assertEquals(v, g.getVertex("testVertex"));
    }

    @Test(expected = IGraph.DuplicateVertexException.class)
    public void addVertexDup1() throws Exception {
        g.addVertex("d1", "testDatablah");
    }

    @Test
    public void addEdge() throws Exception {
        IGraph.Edge e = new IGraph.Edge("d1", "d2", null);
        g.addEdge("d1", "d2");
        assertEquals(e, g.getEdge("d1", "d2"));
    }

    @Test(expected = IGraph.DuplicateEdgeException.class)
    public void addEdgeDup() throws Exception {
        g.addEdge("d1", "d2");
        g.addEdge("d1", "d2");
    }

    @Test(expected = IGraph.DuplicateEdgeException.class)
    public void addEdgeDup2() throws Exception {
        g.addEdge("d1", "d2");
        g.addEdge("d2", "d1");
    }

    @Test
    public void addEdgeDup2Directed() throws Exception {
        g.setDirectedGraph();
        g.addEdge("d1", "d2");
        g.addEdge("d2", "d1");
    }

    @Test(expected = IGraph.DuplicateEdgeException.class)
    public void addEdgeDup1() throws Exception {
        g.addEdge("d1", "d2", "data");
        g.addEdge("d1", "d2", "datar");
    }

    @Test(expected = IGraph.NoSuchVertexException.class)
    public void addEdgeBad() throws Exception {
        g.addEdge("d1", "da2");
    }

    @Test
    public void addEdge1() throws Exception {
        IGraph.Edge e = new IGraph.Edge("d1", "d2", "data");
        g.addEdge("d1", "d2", "data");
        assertEquals(e, g.getEdge("d1", "d2"));
    }

    @Test
    public void getVertexData() throws Exception {
        IGraph.Vertex v = new IGraph.Vertex("testV", "testData");
        g.addVertex("testV", "testData");
        assertEquals("testData", g.getVertexData("testV"));
    }

    @Test
    public void setVertexData() throws Exception {
        g.addVertex("testV", "testData");
        g.setVertexData("testV", "testDataNew");
        assertEquals("testDataNew", g.getVertexData("testV"));
    }

    @Test
    public void getEdgeData() throws Exception {
        IGraph.Edge e = new IGraph.Edge("d1", "d2", "data");
        g.addEdge("d1", "d2", "data");
        assertEquals("data", g.getEdgeData("d1", "d2"));
        assertEquals("data", g.getEdgeData("d2", "d1"));
    }

    @Test
    public void setEdgeData() throws Exception {
        g.addEdge("d1", "d2", "testData");
        g.setEdgeData("d1", "d2", "testDataNew");
        assertEquals("testDataNew", g.getEdgeData("d2", "d1"));
    }

    @Test
    public void getVertex() throws Exception {
        IGraph.Vertex v = new IGraph.Vertex("testV", "testData");
        g.addVertex("testV", "testData");
        assertEquals(v, g.getVertex("testV"));
    }

    @Test
    public void getEdge() throws Exception {
        IGraph.Edge e = new IGraph.Edge("d1", "d2", "testData");
        g.addEdge("d1", "d2", "testData");
        assertEquals(e, g.getEdge("d1", "d2"));
        assertEquals(e, g.getEdge("d2", "d1"));
    }

    @Test
    public void getVertices() throws Exception {
        IGraph.Vertex v0 = new IGraph.Vertex("testV0", "testData");
        IGraph.Vertex v1 = new IGraph.Vertex("testV1", "testData");
        IGraph.Vertex v2 = new IGraph.Vertex("testV2", "testData");
        IGraph.Vertex v3 = new IGraph.Vertex("testV3", "testData");
        g.addVertex("testV0");
        g.addVertex("testV1");
        g.addVertex("testV2");
        g.addVertex("testV3");
        List<IGraph.Vertex> l = g.getVertices();
        assertTrue(l.contains(v0));
        assertTrue(l.contains(v1));
        assertTrue(l.contains(v2));
        assertTrue(l.contains(v3));
    }

    @Test
    public void getEdges() throws Exception {
        g.addVertex("testV0");
        g.addVertex("testV1");
        g.addVertex("testV2");
        g.addVertex("testV3");
        g.addVertex("v0");
        g.addVertex("v1");
        g.addVertex("v2");
        g.addVertex("v3");
        IGraph.Edge e0 = new IGraph.Edge("testV0", "v0", null);
        IGraph.Edge e1 = new IGraph.Edge("testV1", "v1", null);
        IGraph.Edge e2 = new IGraph.Edge("testV2", "v2", null);
        IGraph.Edge e3 = new IGraph.Edge("testV3", "v3", null);
        g.addEdge("testV0", "v0");
        g.addEdge("testV1", "v1");
        g.addEdge("testV2", "v2");
        g.addEdge("testV3", "v3");
        List<IGraph.Edge> l = g.getEdges();
        assertTrue(l.contains(e0));
        assertTrue(l.contains(e1));
        assertTrue(l.contains(e2));
        assertTrue(l.contains(e3));
    }

    @Test
    public void getNeighborsUndirected() throws Exception {
        g.addVertex("testV0");
        g.addVertex("testV1");
        g.addVertex("testV2");
        g.addVertex("testV3");
        g.addVertex("v0");
        g.addVertex("v1");
        g.addVertex("v2");
        g.addVertex("v3");
        IGraph.Vertex v0 = new IGraph.Vertex("v0", "testData");
        IGraph.Vertex v1 = new IGraph.Vertex("v1", "testData");
        IGraph.Vertex v2 = new IGraph.Vertex("testV2", "testData");
        g.addEdge("testV0", "v0");
        g.addEdge("testV0", "v1");
        g.addEdge("testV2", "testV0");
        g.addEdge("testV3", "v3");
        List<IGraph.Vertex> l = g.getNeighbors("testV0");
        assertTrue(l.contains(v0));
        assertTrue(l.contains(v1));
        assertTrue(l.contains(v2));
        assertEquals(3, l.size());
    }

    @Test
    public void getNeighborsDirected() throws Exception {
        g.setDirectedGraph();
        g.addVertex("testV0");
        g.addVertex("testV1");
        g.addVertex("testV2");
        g.addVertex("testV3");
        g.addVertex("v0");
        g.addVertex("v1");
        g.addVertex("v2");
        g.addVertex("v3");
        IGraph.Vertex v0 = new IGraph.Vertex("v0", "testData");
        IGraph.Vertex v1 = new IGraph.Vertex("v1", "testData");
        g.addEdge("testV0", "v0");
        g.addEdge("testV0", "v1");
        g.addEdge("testV2", "testV0");
        g.addEdge("testV1", "testV0");
        g.addEdge("testV3", "v3");
        List<IGraph.Vertex> l = g.getNeighbors("testV0");
        assertTrue(l.contains(v0));
        assertTrue(l.contains(v1));
        assertEquals(2, l.size());
    }

}