/**
 * Created by taylorsdugger on 11/3/16.
 */


package cs311.hw7.graphalgorithms;

import cs311.hw7.graph.IGraph;

import java.util.*;

//none of these are completed all the way....
//It all complies, but its not finished. I pretty much ran out of time on this
//and wasn't able to get any of these done.
//But i have some work for all of them in hopes of partial credit, I made
//sure to comment where i could putting down what I did and what was next.
//I struggled with these for a little while, I wasnt able to get a soluiton
//down that worked, and then the time ran out.
public class GraphAlgorithms {

    private double weight;

    //I got stuck doing this topSort.....Combined with running out of time and
    //running out of ideas, this is what I have...I think there is some correctness
    //to this but its just not together all the way. Some things are commented out
    //so it doesnt cause an error
    public static <V, E> List<IGraph.Vertex<V>> TopologicalSort(IGraph<V, E> g) {

        //the size of the amnt of vertices and stack
        int v = g.getVertices().size();
        Stack s = new Stack();

        //visted variable
        boolean visted[] = new boolean[v];

        //set all to false
        for(int i = 0; i < v; i++)
            visted[i] = false;

        //go through
        for(int j = 0; j< v; j++){
            //if false then
            if(visted[j] == false) {

                //set to true
                visted[v] = true;
                IGraph.Vertex m;

                //get the neighbors

                //Iterator<IGraph.Vertex> it = g.getNeighbors("").iterator();
                //while(it.hasNext()){
                //    m = it.next();
                //    if(!visted[m]){}
                }

                //then push on the current vertex

                s.push(("m"));//in quotes so it doesnt error
            }

        //the return array
        List ret = new ArrayList();
        int k = 0;

        //now pop everything off and return in top order
        while(!s.empty()){
            ret.add(k,s.pop());
            k++;
        }
        return ret;
    }

    //This one also isnt done. I have some work and comments here though
    //I just hit time constraint with all of this
    public static <V, E> List<List<IGraph.Vertex<V>>> AllTopologicalSort(IGraph<V, E> g )
    {
        //get vertex size
        int v = g.getEdges().size();

        //visted flag
        boolean visted[] = new boolean[v];

        //set all to false at first
        for(int i = 0; i < v; i++){
            visted[i] = false;
        }

        //another flag
        boolean flag = false;

        //now you go through each not visited
        for(int i =0; i < v; i++){
            if(!visted[i]){

            }
        }

        return null;
    }

    //I didn't have a lot done here. I got a start and got stuck. Again
    //because of time and other problems, this is all I have completed
    public static <V, E extends IWeight> IGraph<V, E> Kruscal(IGraph<V, E> g )
    {

        //get vertex size
        int v = g.getEdges().size();
        int i=0;

        //Make a list and graph
        List totEdges = g.getEdges();
        IGraph.Edge[] myEdge = new IGraph.Edge[v];

        //do a quick sort on edges in order of weight
        Arrays.sort(myEdge);

        //you need to go through edges
        while(i < v-1){
            //pick the smallest edge first
            IGraph.Edge nEdge;
            nEdge = myEdge[i++];

            //if its not in cycle then increment and go to next edge
            //other discard edge

            //return results

        }

        return null;
    }
}
