package cs311.hw4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taylorsdugger on 10/5/16.
 */
public class MeasureTimeComplexity implements IMeasureTimeComplexity {

    @Override
    public int normalize(IMeasurable m, long timeInMilliseconds) {
        long start = System.currentTimeMillis();

        int times= 0;

        while ((System.currentTimeMillis() - start) <= timeInMilliseconds){
            m.execute();
            times++;
        }

        return times;
    }

    @Override
    public List<? extends IResult> measure(IMeasureFactory factory, int nmeasures, int startsize, int endsize, int stepsize) {

        List<Result> list = new ArrayList<>();
        int pos =0;


        for(int i = startsize; i < endsize; i+=startsize){
            long start = System.currentTimeMillis();
            for(int j = 0; j < nmeasures; j++){
                factory.createRandom(i).execute();
            }
            long end = System.currentTimeMillis();

            Result r = new Result();
            r.setTime(end-start);
            r.setSize(i);
            list.add(pos, r);
            pos++;
        }
        return list;
    }
}
