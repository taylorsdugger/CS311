package cs311.hw8.graphalgorithms;


import cs311.hw8.graph.IGraph.Edge;
import cs311.hw8.graph.IGraph;
import cs311.hw8.graph.IGraph.Vertex;

import java.util.*;

public class GraphAlgorithms {

    /**
     *
     * @param g Graph
     * @param vertexStart The starting vertex
     * @param vertexEnd The Ending vertex
     * @return The shortest Path of edges
     */
    public static <V, E extends IWeight> List<IGraph.Edge<E>> ShortestPath(IGraph<V,E> g, String vertexStart, String vertexEnd){

        List<Edge<E>> eList = g.getEdges();

        //check vertices if valid
        if(g.getVertex(vertexStart) == null || g.getVertex(vertexEnd) == null){
            throw new IllegalArgumentException("Vertex does not exist");
        }

        //No Null edges allowed
        for(int i = 0; i < eList.size(); i++){
            if(eList.get(i).getEdgeData() == null) {
                throw new IllegalArgumentException("Contains null edgeData");
            }
        }

        //Get list of vertices in graph and size and index
        List<Vertex<V>> vertexList = g.getVertices();
        int size = vertexList.size();
        int in = vertexList.indexOf(g.getVertex(vertexStart));
        int inStart = in; //this is for at end of this algo to determine if a path exists ( this way is cheaper than doing the above line again?)
        int inEnd = vertexList.indexOf(g.getVertex(vertexEnd));


        //List of vertexNames and visited indexes
        List<String> vertexNames = new ArrayList<>();
        List<Integer> visited = new ArrayList<>();
        visited.add(in);//visited the first one already

        //Weights
        Weight distance[]  = new Weight[size];
        //The starting vertex has weight 0 from itself
        distance[in] = new Weight(0);

        //
        int visitedNode[] = new int[size];
        boolean included[] = new boolean[size];

        //Go through VertexList
        for(int i = 0; i < size; i++){

            //First find the node with the smallest weight
            Weight min = new Weight(1000000000);//min value
            //go through array
            for (int j = 0; j < visited.size(); i++){
                //if its less and not already included
                if (distance[j].getWeight() < min.getWeight() && (!included[j])){
                    min = distance[j];
                    in = j; //get this one now
                }
            }

            included[in] = true;

            //Now we get the neighbors of that one
            List<Vertex<V>> listOfNeighbours = g.getNeighbors(vertexNames.get(in));
            int s = listOfNeighbours.size();//helps with time

            //But We found the end already, so break.
            if(vertexNames.get(in).equals(vertexEnd))
                break;

            //get its current weight
            double w = distance[in].getWeight();

            //Look through the neighbors
            for(int k = 0; k < s; k++){
                //the weights
                double edgeWeight = g.getEdge(vertexNames.get(in), listOfNeighbours.get(k).getVertexName()).getEdgeData().getWeight();
                double totalWeight = w + edgeWeight;

                int neighbourIn = vertexList.indexOf(listOfNeighbours.get(k));

                //if a neighbour is smaller
                if(totalWeight < distance[neighbourIn].getWeight()){
                    visited.add(neighbourIn);//visited

                    //add up some weights and make vistedNodes
                    distance[neighbourIn] = new Weight(totalWeight);
                    visitedNode[neighbourIn] = in;
                }
            }
        }

        List<Edge<E>> shortestPath = new ArrayList<>();
        Edge<E> edge;

        while(inEnd != inStart){
            if(visitedNode[inEnd] == 0){
                throw new IllegalArgumentException("No Path Exists");
            }

            //New edge to be added
            String vName1 = vertexList.get(visitedNode[inEnd]).getVertexName();
            String vName2 = vertexList.get(inEnd).getVertexName();
            edge = g.getEdge(vName1, vName2);

            //Add the edge
            shortestPath.add(edge);

            inEnd = visitedNode[inEnd];
        }

        return shortestPath;
    }
}
