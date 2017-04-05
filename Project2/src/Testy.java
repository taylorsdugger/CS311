import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by taylor on 3/31/17.
 */
public class Testy {

    public static void main(String args[]){

        /*WikiCrawler w = new WikiCrawler("/wiki/bicycle",100,"WikiComS.txt");
        w.crawl();

        String everything;
        StringBuilder sb  = new StringBuilder();

        try {
            Scanner in = new Scanner(new FileReader("/home/taylor/Programming/CS311/Project2/sample.txt"));

            while (in.hasNext()){
                sb.append(in.nextLine());
                sb.append("\n");
            }

        }catch (Exception e){}

        everything = sb.toString();

        ArrayList<String> aa = w.extractLinks(everything);
        for (String a: aa) {
            System.out.println(a);
        }*/


        GraphProcessor p = new GraphProcessor("/home/taylor/Programming/CS311/Project2/graphTest.txt");

    }
}
