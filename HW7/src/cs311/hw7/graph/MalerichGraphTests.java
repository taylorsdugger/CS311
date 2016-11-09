package cs311.hw7.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import groovy.util.GroovyTestCase;
import org.junit.Test;

import cs311.hw7.graph.IGraph.DuplicateEdgeException;
import cs311.hw7.graph.IGraph.DuplicateVertexException;
import cs311.hw7.graph.IGraph.NoSuchEdgeException;
import cs311.hw7.graph.IGraph.NoSuchVertexException;

public class MalerichGraphTests extends GroovyTestCase{

    private final String KERMIT = "Kermit the Frog";
    private final String RALPH = "Ralph the Dog";

    @Test public void testAddVertices() {
        Graph<String, String> test = new Graph<String, String>();
        test.addVertex("A");
        test.addVertex("B");
        test.addVertex("C");

        ArrayList<String> arr = new ArrayList<String>();
        arr.add("A");
        arr.add("B");
        arr.add("C");

        List<IGraph.Vertex> vertices = test.getVertices();
        for (IGraph.Vertex<String> v : vertices) {
            if (!arr.remove(v.getVertexName())) {
                fail("Graph added an unwanted vertex.");
            }
        }

        if (arr.size() > 0) {
            fail("Graph is missing a vertex!");
        }
    }

    @Test public void testAddVerticesFailure() {
        Graph<String, String> test = new Graph<String, String>();
        test.addVertex("A");
        test.addVertex("B");
        test.addVertex("C");

        try {
            test.addVertex("C");
        } catch (DuplicateVertexException e) {
            // Test passed, return early.
            return;
        }

        // Catch statement missed, test failed.
        fail("Failed to throw DuplicateVertexException when adding duplicate vertex.");
    }

    @Test public void testNeighborsDirected() {
        Graph<String, String> test = new Graph<String, String>();
        test.setDirectedGraph();

        test.addVertex("A");
        test.addVertex("B");
        test.addVertex("C");

        test.addEdge("A", "B");
        test.addEdge("A", "C");

        // Make sure we actually have 2 edges.
        assertEquals(2, test.getEdges().size());

        // Get the neighbors of A, this should be both B and C.
        List<IGraph.Vertex> arr = test.getNeighbors("A");
        assertEquals("Expected 2 neighbors of A", 2, arr.size());

        // Vertices B and C should have no neighbors.
        arr = test.getNeighbors("B");
        assertEquals("Expected 0 neighbors of B", 0, arr.size());
        arr = test.getNeighbors("C");
        assertEquals("Expected 0 neighbors of C", 0, arr.size());
    }

    @Test public void testNeighborsUndirected() {
        Graph<String, String> test = new Graph<String, String>();
        test.setUndirectedGraph();

        test.addVertex("A");
        test.addVertex("B");
        test.addVertex("C");

        test.addEdge("A", "B");
        test.addEdge("A", "C");

        // Make sure we actually have 2 edges.
        assertEquals(2, test.getEdges().size());

        // Get the neighbors of A, this should be both B and C.
        List<IGraph.Vertex> arr = test.getNeighbors("A");
        assertEquals("Expected 2 neighbors of A", 2, arr.size());
        // B and C both have neighbors in A, but not each other.
        arr = test.getNeighbors("B");
        assertEquals("Expected 1 neighbors of B", 1, arr.size());
        arr = test.getNeighbors("C");
        assertEquals("Expected 1 neighbors of C", 1, arr.size());
    }

    @Test public void testDuplicateEdgesDirected() {
        Graph<String, String> test = new Graph<String, String>();
        test.setDirectedGraph();

        test.addVertex("A");
        test.addVertex("B");
        test.addVertex("C");

        test.addEdge("A", "B");
        test.addEdge("A", "C");

        // This is okay because the graph is directed.
        test.addEdge("B", "A");
        assertEquals(3, test.getEdges().size());

        try {
            // This is not okay.
            test.addEdge("A", "C");
        } catch (DuplicateEdgeException e) {
            // Test passed.
            return;
        }

        fail("Graph class did not throw DuplicateEdgeException where expected.");
    }

    @Test public void testDuplicateEdgesUndirected() {
        Graph<String, String> test = new Graph<String, String>();
        test.setUndirectedGraph();

        test.addVertex("A");
        test.addVertex("B");
        test.addVertex("C");

        test.addEdge("A", "B");
        test.addEdge("A", "C");

        try {
            // This is not okay for an undirected graph.
            test.addEdge("B", "A");
        } catch (DuplicateEdgeException e) {
            // Test passed.
            return;
        }

        fail("Graph class did not throw DuplicateEdgeException where expected.");
    }

    @Test public void testAddEdgeWithInvalidVertices() {
        Graph<String, String> test = new Graph<String, String>();
        test.addVertex("A");
        test.addVertex("B");

        try {
            test.addEdge("A", "C");
        } catch (NoSuchVertexException e) {
            // Test passed.
            return;
        }

        fail("Invalid edge added. Cannot add edge (A,C) with no such vertex C.");
    }

    @Test public void testVertexData() {
        Graph<String, String> test = new Graph<String, String>();
        test.addVertex("A", KERMIT);
        test.addVertex("B", RALPH);

        assertEquals(KERMIT, test.getVertexData("A"));
        assertEquals(RALPH, test.getVertexData("B"));
    }

    @Test public void testEdgeData() {
        Graph<String, String> test = new Graph<String, String>();
        test.setDirectedGraph();
        test.addVertex("A");
        test.addVertex("B");

        test.addEdge("A", "B", KERMIT);
        test.addEdge("B", "A", RALPH);

        try {
            assertEquals(KERMIT, test.getEdgeData("A", "B"));
            assertEquals(RALPH, test.getEdgeData("B", "A"));
        } catch (Exception e) {
            fail("This really shouldn't throw an exception.");
        }
    }

    @Test public void testEdgeDataUndirected() {
        Graph<String, String> test = new Graph<String, String>();
        test.setUndirectedGraph();
        test.addVertex("A");
        test.addVertex("B");
        test.addEdge("A", "B", KERMIT);

        try {
            assertEquals(KERMIT, test.getEdgeData("B", "A"));
        } catch (NoSuchEdgeException e) {
            fail("Make sure that edge BA counts as edge AB in an undirected graph.");
        } catch (Exception e) {
            fail();
        }
    }

    @Test public void testVertexDataFailure() {
        Graph<String, String> test = new Graph<String, String>();
        test.addVertex("A", KERMIT);
        test.addVertex("B", RALPH);

        try {
            test.getVertexData("C");
        } catch (NoSuchVertexException e) {
            // Test passed.
            return;
        }

        fail("Graph class failed to throw NoSuchVertexException.");
    }

    @Test public void testEdgeDataFailure() {
        Graph<String, String> test = new Graph<String, String>();
        test.setDirectedGraph();
        test.addVertex("A");
        test.addVertex("B");

        try {
            test.getEdgeData("A", "B");
        } catch (NoSuchEdgeException e) {
            // Test passed.
            return;
        }

        fail("Graph class failed to throw NoSuchEdgeException.");
    }
}