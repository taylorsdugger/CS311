import java.util.Random;

/**
 * Created by taylorsdugger on 2/16/17.
 */
public class HashFunction {

    private int a, b, p;

    /**
     * HashFunction(int range). Picks the first (positive) prime integer whose value is at least range,
     * and sets the value of p to that prime integer. Then picks two random integers x and y from
     * {0, 1, · · · , p − 1} and sets a as x and b as y.
     *
     * @param range the prime value we want to be at least, and our random val range
     */
    public HashFunction(int range){

        int val = range;

        //Checks if range is a prime, if not then keep incrementing until you find a prime
        while (true){
            if(isPrime(val)) {
                break;
            }else{
                val++;
            }
        }

        //set p to our prime
        this.p = val;

        //Finds a random number
        Random random = new Random();
        int x = random.nextInt(range);
        int y = random.nextInt(range);

        //and sets it to a and b
        this.a = x;
        this.b = y;

    }

    /**
     * Returns the value of the hash function on x
     *
     * @param x value of hash on this
     * @return (ax + b)%p
     */
    public int hash(int x){

        return (this.a * x + this.b) % this.p;
    }

    /**
     *
     * @return a
     */
    public int getA(){

        return a;
    }

    /**
     *
     * @return b
     */
    public int getB(){

        return b;
    }

    /**
     *
     * @return p
     */
    public int getP(){

        return p;
    }

    /**
     *
     * @param x set A to x % p
     */
    public void setA(int x){

        this.a = x % this.p;
    }

    /**
     *
     * @param y set B to y % p
     */
    public void setB(int y){

        this.b = y % this.p;
    }

    /**
     * This method will pick the first (positive)
     * prime whose value is at least x and sets the value of p to that integer.
     *
     * @param x The prime we want to be at least this number
     */
    public void setP(int x){

        //Checks if range is a prime, if not then keep incrementing until you find a prime
        while (true){
            if(isPrime(x)) {
                break;
            }else{
                x++;
            }
        }

        //Sets p to prime
        this.p=x;
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
