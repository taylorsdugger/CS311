import java.util.List;

/**
 * Created by taylorsdugger on 2/16/17.
 */
public class HashTable {

    /**
     * HashTable(int size) Finds the smallest prime integer p whose value is at least size. Creates
     * a hash table of size p where each cell initially is NULL. It will determine the hash function to be
     * used in the hash table by creating the object new HashFunction(p).
     *
     * @param size The value to look for
     */
    public HashTable(int size){


    }

    /**
     * Find max load of hash table
     *
     * @return maximum load
     */
    public int maxLoad(){

        return 0;
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

        return 0;
    }

    /**
     * Returns the number of Tuples that are currently stored in the hash table.
     *
     * @return number of tuples stored
     */
    public int numElements(){

        return 0;
    }

    /**
     * Returns the laod factor which is number of elements / size
     *
     * @return load factor
     */
    public int loadFactor(){

        return 0;
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
}
