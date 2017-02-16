import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by taylorsdugger on 2/16/17.
 */
public class HashTable {

    private HashFunction hf;
    private int p = 0;
    private LinkedList<Tuple>[] arr;

    public static void main(String args[]){
        HashTable h = new HashTable(10);
    }
    /**
     * HashTable(int size) Finds the smallest prime integer p whose value is at least size. Creates
     * a hash table of size p where each cell initially is NULL. It will determine the hash function to be
     * used in the hash table by creating the object new HashFunction(p).
     *
     * @param size The value to look for
     */
    public HashTable(int size){

        while (true){
            if(isPrime(size)) {
                break;
            }else{
                size++;
            }
        }

        //Sets p to prime
        p = size;

        //Creates our array of linked lists
        arr = new LinkedList[p];

        //initializes it
        for(int i = 0; i < p; i++){
            arr[i] = new LinkedList<>();
        }

        //new hashFunction
        hf = new HashFunction(p);
    }

    /**
     * Find max load of hash table
     *
     * @return maximum load
     */
    public int maxLoad(){

        int max = 0;

        for(int i = 0; i < p; i++){
            max += this.arr[i].size();
        }

        return max;
    }

    /**
     * Find average load of table
     *
     * @return the average load
     */
    public int averageLoad(){

        return 0;
    }

    /**
     * The size of the hash table
     *
     * @return size of table
     */
    public int size(){

        return p;
    }

    /**
     * Returns the number of Tuples that are currently stored in the hash table.
     *
     * @return num number of tuples stored
     */
    public int numElements(){

        int num = 0;

        for(int i = 0; i < p; i ++){
            if(this.arr[i] != null){
                num+= arr[i].size();
            }
        }

        return num;
    }

    /**
     * Returns the laod factor which is number of elements / size
     *
     * @return load factor
     */
    public int loadFactor(){

        return numElements()/size();
    }

    /**
     * add(Tuple t) Adds the tuple t to the hash table; places t in the list pointed by the cell h(t.getKey())
     * where h is the hash function that is being used. When the load factors becomes bigger than 0.7,
     * then it (approximately) doubles the size of the hash table and rehashes all the elements (tuples) to
     * the new hash table. The size of the new hash table must be: Smallest prime integer whose value is
     * at least twice the current size
     *
     * @param t Tuple to add
     */
    public void add(Tuple t){

        this.arr[hf.hash(t.getKey())].add(t);

        if(loadFactor() > 0.7){

            LinkedList<Tuple>[] newarr = new LinkedList[p];

            //initialize the new table
            for(int i = 0; i < p*2; i++){
                newarr[i] = new LinkedList<>();
            }

            //now copy over
            for(int j = 0; j < p; j++){
                newarr[j] = arr[j];
            }

            arr = newarr;
            p = p*2;
            hf = new HashFunction(p);
        }

    }

    /**
     * returns an array list of Tuples (in the hash table) whose key equals k. If no such
     * Tuples exist, returns an empty list. Note that the type of this method must be ArrayList<Tuple>
     *
     * @param k Key to look for
     * @return Array List of Tuples
     */
    public List<Tuple> search(int k){

        return null;
    }

    /**
     * Removes Tuple from table
     *
     * @param t Tuple to remove
     */
    public void remove(Tuple t){


    }

    /**
     * Helper to see if its a prime
     *
     * @param x the num to check
     * @return true if its a prime
     */
    private boolean isPrime(int x){
        if (x % 2 ==0) return false;

        for(int i = 3; i*i <= x; i+=2) {
            if(x % i == 0)
                return false;
        }
        return true;
    }
}
