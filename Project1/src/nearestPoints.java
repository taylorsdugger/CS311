import java.io.*;
import java.util.ArrayList;

/**
 * Created by taylorsdugger on 2/22/17.
 */
public class nearestPoints {

    /**
     * ArrayList of points provided
     */
    private ArrayList<Float> myPoints;

    /**
     * HashTable used
     */
    private HashTable hashTable;

    /**
     * The variable dataFile holds the absolute path of the file
     * that contains the set of points S.
     *
     * @param dataFile The absolute path of the file of points S
     */
    public nearestPoints(String dataFile){

        myPoints = new ArrayList<>();

        try{
            FileReader fileReader = new FileReader(dataFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while((line = bufferedReader.readLine()) != null){
                myPoints.add(Float.parseFloat(line));
            }

            fileReader.close();
            bufferedReader.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * The array list pointSet contains the set of points S.
     *
     * @param pointSet Array list that contains set of points S
     */
    public nearestPoints(ArrayList<Float> pointSet){

        this.myPoints = pointSet;
    }

    /**
     * Returns an array list of points (from the set S) that are close
     * to p. This method must implement the naive approach.
     * Note that the type of this method must be ArrayList<float>
     *
     * @param p Array List of points close to
     */
    public ArrayList naiveNearestPoints(float p){

        ArrayList closePoints = new ArrayList();

        for(int i = 0; i < myPoints.size(); i++){
            float q = myPoints.get(i);
            if(Math.abs(p-q) <= 1){
                closePoints.add(q);
            }
        }

        return closePoints;
    }

    /**
     * Builds the data structure that enables to quickly answer nearest point
     * queries. Your data structure must use the notion of neighbor preserving
     * hashing and along with the class HashTable. Otherwise, you will receive zero credit
     */
    public void buildDataStructure(){

        //size m = 1.5n round up to int
        hashTable = new HashTable((int)Math.ceil(myPoints.size()*1.5));

        for(int i = 0; i < myPoints.size(); i++){
            float p = myPoints.get(i);
            //g(p) function
            int g = (int)Math.floor(p);
            //make tuple
            Tuple t = new Tuple(g,p);
            //add tuple
            hashTable.add(t);
        }

    }

    /**
     * Returns an array list of points (from the S) that are close top.
     * This method must use the data structure that was built.
     * The expected run time of this method must be O(N(p));
     * otherwise you will receive zero credit.
     *
     * @param p point p to find points close to
     * @return array list of points that are close to p
     */
    public ArrayList npHashNearestPoints(float p){

        buildDataStructure();

        

        return null;
    }

    /**
     * For every point p ∈ S, compute the list of all points from S that
     * are close to p by calling the method NaiveNearestPoints(p).
     * Write the results to a file named NaiveSolution.txt
     */
    public void allNearestPointsNaive(){

        try {
            PrintWriter writer = new PrintWriter("NaiveSolution.txt", "UTF-8");
            String out;

            for (int i = 0; i < myPoints.size(); i++) {
                float q = myPoints.get(i);
                out = naiveNearestPoints(q).toString().replace(",","").replace("[","")
                        .replace("]","").trim();

                writer.println(q + " " + out);
                out = "";
            }

            writer.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * For every point p ∈ S, compute the list of all points from S that
     * are close to p by calling the method NPHashNearestPoints(p).
     * Write the results to a file named
     * HashSolution.txt. The expected time of this method must be O(n+Ep∈S N(p));
     * otherwise you will receive zero credit.
     */
    public void allNearestPointsHash(){

    }


}
