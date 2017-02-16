
/**
 * Created by taylorsdugger on 2/15/17.
 */
public class Tuple {

    int key;
    double value;

    private Tuple tuple;

    /**
     * Creates Tuple Object with keyP and valueP
     * @param keyP the key
     * @param valueP the value
     */
    public Tuple(int keyP, float valueP){

        tuple = new Tuple(keyP, valueP);
    }

    /**
     *
     * @return key
     */
    public int getKey(){

        return this.key;
    }

    /**
     *
     * @return value
     */
    public double getValue(){

        return this.value;
    }

    /**
     * Returns true if this tuple equals t; otherwise false
     *
     * @param t the tuple to check
     * @return true if equal
     */
    public boolean equals(Tuple t){

        if(this.getKey() == t.getKey() && this.getValue() == t.getValue())
            return true;

        return false;
    }
}
