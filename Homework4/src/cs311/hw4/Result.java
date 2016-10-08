package cs311.hw4;

/**
 * Created by taylorsdugger on 10/7/16.
 */
public class Result implements IResult {

    int size = 0;
    long time = 0;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public long getTime() {
        return time;
    }

    public void setSize(int s){
        size = s;
    }

    public void setTime(long t){
        time = t;
    }
}
