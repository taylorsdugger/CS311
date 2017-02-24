
/**
 * Created by taylorsdugger on 2/16/17.
 */
public class Test {

    public static void main(String args[]){

        /*Tuple t = new Tuple(1,1);
        Tuple t2 = new Tuple(2,2);
        Tuple t3 = new Tuple(3,3);
        Tuple t4 = new Tuple(3,4);
        Tuple t5 = new Tuple(4,5);
        Tuple t6 = new Tuple(5,6);
        Tuple t7 = new Tuple(5,7);
        Tuple t8 = new Tuple(5,8);
        Tuple t9 = new Tuple(6,9);

        HashTable h = new HashTable(10);

        System.out.println("Start Size: " + h.size());

        h.add(t);
        h.add(t2);
        h.add(t3);
        System.out.println("Load factor after 3: " + h.loadFactor());
        h.add(t4);
        h.add(t5);
        h.add(t6);
        h.add(t7);
        h.add(t8);
        h.add(t9);

        System.out.println("Search for t8 value 8 " + h.search(5).get(2).getValue());
        h.remove(t8);
        System.out.println("Search for t8 value 8 " + h.search(5));

        System.out.println("Load factor: " + h.loadFactor());
        System.out.println("Num elements: " + h.numElements());
        System.out.println("Size: " + h.size());
        System.out.println("Average Load: " + h.averageLoad());
        System.out.println("Max Load: " + h.maxLoad());
        System.out.println("Search for key 6 value 9: " + h.search(6).get(0).getValue());
        System.out.println("Search for key 5 value 6,7: " + h.search(5).get(0).getValue() + h.search(5).get(1).getValue());
*/
        //NearestPoints n = new NearestPoints("/home/taylorsdugger/Programming/311/Project1/points.txt");
        //System.out.println(n.npHashNearestPoints((2820.1f)).toString());
        //n.allNearestPointsHash();
        //System.out.println(n.naiveNearestPoints(52295.2f).toString());
        //n.allNearestPointsNaive();

        RecSys r = new RecSys("/home/taylorsdugger/Programming/311/Project1/recPoints.txt");
        System.out.println(r.ratingOf(2,1));

    }
}
