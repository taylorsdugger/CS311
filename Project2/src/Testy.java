import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Taylor Dugger
 */
public class Testy {

    public static void main(String args[]){

       WikiCrawler w = new WikiCrawler("/wiki/Computer_Science",25,"WikiCS.txt");
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
        /*for (String a: aa) {
            System.out.println(a);
        }*/


        GraphProcessor p = new GraphProcessor("/home/taylor/Programming/CS311/Project2/WikiCS.txt");

        //System.out.println(p.sameComponent("5", "9"));

        //ArrayList<String> a = p.componentVertices("8");
        //System.out.println(a.toString());

        System.out.println((p.largestComponent()));

        System.out.println(p.bfsPath("/wiki/Computer_Science","/wiki/Logic").toString());
    }
}
