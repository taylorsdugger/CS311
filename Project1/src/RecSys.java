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

            //Go through file
            while((scanner.hasNext())){
                //if its the first line, add the num of user and movies
                if (i==0){
                    numUsers = scanner.nextInt();
                    numMovies = scanner.nextInt();
                    i = 1;
                }

                //if not the first line, then we know the first is the userPoint
                ratings.add(scanner.nextFloat());

                //Add all the movie score for each user
                for(int j = 0; j < numMovies; j++) {
                    ratings.add((float) scanner.nextInt());
                }

                //put the user's Point, and movie scores contained in array ratings
                //into a hashMap
                //I used the Java HashMap for this, because I needed an Array for my
                //variable bit. I hope this was alright.
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

        //add each userPoint so we can compute nearestPoint
        for(int i = 1; i <= numUsers; i++){
            if(i != u)//dont want to add the user we're predicting
                myPoints.add(hm.get(i).get(0));
        }

        //Find nearestPoint
        NearestPoints n = new NearestPoints(myPoints);
        myPoints = n.npHashNearestPoints(userPoint);//lets reuse this array
        int sum = 0;

        //now we need to check if user j is in the NearestPoint return, since that just
        //returns the userPoint, not the user ID
        for(int j = 1; j <= myPoints.size();j++){
            if(myPoints.contains(hm.get(j).get(0))){ //if user j is in nearestPoints
                rating += hm.get(j).get(m); // add their movie score
                sum++;
            }
        }

        return rating/sum;
    }
}
