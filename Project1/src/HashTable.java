import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by taylorsdugger on 2/16/17.
 */
public class HashTable {

    /**
     * The hashfunction used
     */
    public HashFunction hf;

    /**
     * size p
     */
    private int p = 0;

    /**
     * The arrayList
     */
    private ArrayList<Tuple>[] arr;

/*    public static void main(String args[]){
        HashTable h = new HashTable(10);
    }*/
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
        arr = new ArrayList[p];

        //initializes it
        for(int i = 0; i < p; i++){
            arr[i] = new ArrayList<>();
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
            //look for a bigger list
            if(this.arr[i].size() > max)
                max = this.arr[i].size();
        }

        return max;
    }

    /**
     * Find average load of table
     *
     * @return the average load
     */
    public int averageLoad(){

        int sum = 0;
        int s;
        int nonNull = 0;

        for(int i = 0; i < p; i++){
            s = this.arr[i].size();
            //if it contains something then
           if(s > 0) {
               sum += s; //sum the total up
               nonNull++; //and add one to the nonNull Cell list
           }

        }

        return sum/nonNull;
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
    public double loadFactor(){

        double n = this.numElements();
        double s = this.size();
        return n/s;
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

        int hh = Math.abs(hf.hash(t.getKey()));

        this.arr[hh].add(t);

        if(loadFactor() > 0.7){




            //LinkedList<Tuple>[] newarr = new LinkedList[p*2];

            HashTable h = new HashTable(p*2);

            /*//initialize the new table
            for(int i = 0; i < p*2; i++){
                newarr[i] = new LinkedList<>();
            }

            //now copy over
            for(int j = 0; j < p; j++){
                newarr[j] = arr[j];
            }

            arr = newarr;
            p = p*2;*/
        }

    }

    /**
     * returns an array list of Tuples (in the hash table) whose key equals k. If no such
     * Tuples exist, returns an empty list. Note that the type of this method must be ArrayList<Tuple>
     *
     * @param k Key to look for
     * @return Array List of Tuples
     */
    public ArrayList<Tuple> search(int k){

        ArrayList<Tuple> list = new ArrayList<>();
        ArrayList<Tuple> temp;

        //get list from the array
        temp = this.arr[Math.abs(hf.hash(k))];

        //Get an ArrayList of the elements in my linked list at the key
        for(int i = 0; i < temp.size(); i++){
            list.add(temp.get(i));
        }

        return list;
    }

    /**
     * Removes Tuple from table
     *
     * @param t Tuple to remove
     */
    public void remove(Tuple t){

        ArrayList<Tuple> temp;
        int hashy = hf.hash(t.getKey());//gets rid of one calculation...

        //get list from the array
        temp = this.arr[hashy];

        //now look for the value and remove
        for(int i = 0; i < temp.size(); i++){
            if(temp.get(i).getValue() == (t.getValue())){
                temp.remove(i);
            }
        }

        //put the list back in
        this.arr[hashy] = temp;

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
