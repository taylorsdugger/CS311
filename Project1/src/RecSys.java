import jdk.management.resource.internal.inst.NetRMHooks;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by taylorsdugger on 2/23/17.
 */
public class RecSys {

    /**
     * num of Users
     */
    private int numUsers = 0;

    /**
     * Num of movies
     */
    private int numMovies = 0;

    /**
     * ArrayList of User's Movie rating
     */
    private HashMap<Integer, ArrayList<Float>> hm;

    /**
     * The string mrMatrix is contains the absolute path name of the file that
     * contains the mapped ratings matrix
     *
     * @param mrMatrix path of the file containing ratings matrix
     */
    public RecSys(String mrMatrix){

        try{
            Scanner scanner = new Scanner(new File(mrMatrix));
            hm = new HashMap<>();
            ArrayList<Float> ratings = new ArrayList<>();

            int i = 0;
            int user = 1;

            while((scanner.hasNext())){
                if (i==0){
                    numUsers = scanner.nextInt();
                    numMovies = scanner.nextInt();
                    i = 1;
                }

                ratings.add(scanner.nextFloat());

                for(int j = 0; j < numMovies; j++) {
                    ratings.add((float) scanner.nextInt());
                }

                hm.put(user, ratings);
                ratings = new ArrayList<>();
                user++;
            }

            scanner.close();

            System.out.println(hm.get(1).get(4));

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * If the user u has rated movie m, then it returns that rating; otherwise
     * it will predict the rating based on the approach described above,
     * and returns the predicted rating. The type of this method must be float.
     *
     * @param u user u
     * @param m movie m
     * @return rating given to movie, or a prediction of movie
     */
    public float ratingOf(int u, int m){

        //if already there return the rating
        float rating = hm.get(u).get(m);
        if(rating != 0)
            return rating;
        //else predict it
        float userPoint = hm.get(u).get(0);
        ArrayList<Float> myPoints = new ArrayList<>();

        for(int i = 1; i <= numUsers; i++){
            if(i != u)//dont want to add the user we're predicting
                myPoints.add(hm.get(i).get(0));
        }

        NearestPoints n = new NearestPoints(myPoints);
        myPoints = n.npHashNearestPoints(userPoint);//lets reuse this array

        int sum = 0;
        for(float point : myPoints){
            if(Math.abs(userPoint-point) <= 1){
                rating += hm.get(point).get(m); //add their movie score on
                sum++;
            }
        }


        return rating/sum;
    }
}
