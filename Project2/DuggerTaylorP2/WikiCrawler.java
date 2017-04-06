import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Taylor Dugger
 */
public class WikiCrawler {

    /**
     * Wikipedia
     */
    public static final String BASE_URL = "https://en.wikipedia.org";

    /**
     * The root url
     */
    private String seedUrl;

    /**
     * Max pages to search
     */
    private int max;

    /**
     * FileName to write to
     */
    private String fileName;

    /**
     * Keeps track of lookups to be polite
     */
    private int numRequestsToServer = 0;

    /**
     *
     * @param seedUrl relative address of the seed url (within Wiki domain).
     * @param max representing Maximum number pages to be crawled
     * @param fileName representing name of a file–The graph will be written to this file
     */
    public WikiCrawler(String seedUrl, int max, String fileName){
        this.seedUrl = seedUrl;
        this.max = max;
        this.fileName = fileName;

    }

    /**
     * This method gets a string (that represents contents of a .html
     * file) as parameter. This method should return an array list (of Strings) consisting of links from doc.
     * Type of this method is ArrayList<String>. You may assume that the html page is the source
     * (html) code of a wiki page. This method must
     • Extract only wiki links. I.e. only links that are of form /wiki/XXXXX.
     • Only extract links that appear after the first occurrence of the html tag <p> (or <P>).
     • Should not extract any wiki link that contain the characters “#” or “:”.
     • The order in which the links in the returned array list must be exactly the same order in
     * which they appear in doc.
     *
     * @param doc (that represents contents of a .html file
     * @return an array list consisting of links from doc.
     */
    public ArrayList<String> extractLinks(String doc){

        ArrayList<String> links = new ArrayList<>();

        //using a pattern here
        String p = "href=\"/wiki/(.[^#:]*?)\"";
        Pattern pattern = Pattern.compile(p);
        Matcher match = pattern.matcher(doc);

        while(match.find()){
            links.add("/wiki/" + match.group(1));
        }

        return links;
    }

    /**
     * This method should construct the web graph over following pages: Consider the first
     * max many pages that are visited when you do a BFS with seedUrl. Your program should construct
     * the web graph only over those pages. and writes the graph to the file fileName.
     */
    public void crawl() {

        try {
            //queue and list initialization
            //(2)
            Queue<String> Q = new LinkedList<>();
            List<String> visited = new LinkedList<>();
            List<String> edges = new LinkedList<>();

            //initialize the queue and visted list
            //(3)
            Q.add(seedUrl);
            visited.add(seedUrl);

            //URL stuffs
            URL url;
            InputStream is;

            //Output to file
            PrintWriter toFile = new PrintWriter(fileName);
            //Put the max first
            toFile.println(max);

            //checks the amount of http requests
            int numPages = 0;

            String currentPage;

            //Go through the queue
            //(4)
            while (!Q.isEmpty()) {

                //(a) Let currentPage be the first element of Q
                currentPage = Q.remove();
                numPages++;

                //My check for every 100 requests
                numRequestsToServer++;
                if (numRequestsToServer > 100) {
                    Thread.sleep(3000);
                    numRequestsToServer = 1;
                }

                //(b) Send a request to server at currentPage and download currentPage
                url = new URL(BASE_URL + currentPage);
                is = url.openStream();

                //(c) Extract all links from currentPage
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = "";
                String curPage = "";

                while(line != null){
                    line = br.readLine();
                    curPage = curPage + " " + line;
                }

                ArrayList<String> currentLinks = extractLinks(curPage);

                //(d) for every link wiki in currentPage
                for (String wiki: currentLinks) {
                    if(!currentPage.equals(wiki)) {//make sure its not this page
                        if (!visited.contains(wiki) && numPages <= max) {//if not visited yet and less than max
                            Q.add(wiki);//add to end of queue
                            visited.add(wiki);//add to visited
                            edges.add(currentPage + " " + wiki);//add this to the edges list
                            numPages++;//one more page
                        }
                        //if its visited already, make sure its not already in edges
                        if(visited.contains(wiki) && !edges.contains(currentPage + " " + wiki) && numPages > max){
                            edges.add(currentPage + " " + wiki);
                        }
                    }
                }

                br.close();
                is.close();
            }

            int i = 0;
            //go through list and add to file
            for (String a: edges) {
                i++;
                toFile.println(a);
            }

            toFile.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
