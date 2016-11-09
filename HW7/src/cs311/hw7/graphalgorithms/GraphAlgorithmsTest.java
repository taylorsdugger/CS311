package cs311.hw7.graphalgorithms;

import cs311.hw7.graph.Graph;
import cs311.hw7.graph.IGraph;
import org.junit.Before;
import org.junit.Test;

import static cs311.hw7.graphalgorithms.GraphAlgorithms.Kruscal;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

/**
 * Created by Keane on 11/7/2016.
 */
public class GraphAlgorithmsTest {
    Graph<String, Weighted> g;
    @Before
    public void setUp() {
        g = new Graph();
        g.setUndirectedGraph();
        for (char c = 'a'; c < 'k'; c++)
            g.addVertex(String.valueOf(c));
        g.addEdge("a", "b", new Weighted(10));
        g.addEdge("a", "d", new Weighted(87));
        g.addEdge("b", "d", new Weighted(79));
        g.addEdge("b", "e", new Weighted(60));
        g.addEdge("b", "f", new Weighted(16));
        g.addEdge("b", "c", new Weighted(71));
        g.addEdge("c", "f", new Weighted(76));
        g.addEdge("c", "g", new Weighted(85));
        g.addEdge("d", "e", new Weighted(33));
        g.addEdge("d", "h", new Weighted(66));
        g.addEdge("e", "h", new Weighted(11));
        g.addEdge("e", "f", new Weighted(16));
        g.addEdge("e", "i", new Weighted(75));
        g.addEdge("f", "i", new Weighted(7));
        g.addEdge("f", "g", new Weighted(36));
        g.addEdge("g", "j", new Weighted(34));
        g.addEdge("i", "j", new Weighted(66));
    }

    @Test
    public void topologicalSort() throws Exception {

    }

    @Test
    public void allTopologicalSort() throws Exception {

    }

    @Test
    public void kruscal() throws Exception {
        IGraph<String, Weighted> k = Kruscal(g);
        assertEquals(9, k.getEdges().size());
        assertNull(k.getEdge("d", "b"));
        assertNull(k.getEdge("a", "d"));
        assertNull(k.getEdge("d", "h"));
        assertNull(k.getEdge("e", "i"));
        assertNull(k.getEdge("e", "b"));
        assertNull(k.getEdge("i", "j"));
        assertNull(k.getEdge("f", "c"));
        assertNull(k.getEdge("c", "g"));
        assertNotNull(k.getEdge("a", "b"));
        assertNotNull(k.getEdge("d", "e"));
        assertNotNull(k.getEdge("e", "h"));
        assertNotNull(k.getEdge("b", "f"));
        assertNotNull(k.getEdge("f", "i"));
        assertNotNull(k.getEdge("f", "e"));
        assertNotNull(k.getEdge("g", "j"));
        assertNotNull(k.getEdge("c", "b"));
        assertNotNull(k.getEdge("f", "g"));
    }

    private static class Weighted implements IWeight {
        double weight;

        public Weighted(double weight) {
            this.weight = weight;
        }

        public double getWeight() {
            return weight;
        }

        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (this == obj)
                return true;
            if (getClass() != obj.getClass())
                return false;
            return weight == ((Weighted) obj).weight;
        }
    }
}