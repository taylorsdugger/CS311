import java.util.ArrayList;

/**
 * Created by Taylor Dugger
 */
public class Vertex {

    private String vertex;
    private ArrayList<String> edges;
    private boolean visited = false;

    public Vertex(String vertex){

        this.vertex = vertex;
        edges = new ArrayList<String>();
    }

    public String getVertex(){
        return this.vertex;
    }

    public void setEdge(String vertex2){
        edges.add(vertex2);
    }

    public ArrayList<String> getEdges(){
        return edges;
    }

    public boolean isVisited(){
        return visited;
    }

    public void setVisited(boolean visited){
        this.visited = visited;
    }

}
