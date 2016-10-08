package cs311.hw4;

import java.util.List;

/**
 * Created by taylorsdugger on 10/5/16.
 */
public class SlowMatrixTest {

    public static void main(String args[]){
        // 1.

        SlowMatrixFactory in = new SlowMatrixFactory();
        MeasureTimeComplexity timeComp = new MeasureTimeComplexity();

        int times1 = timeComp.normalize((in.createRandom(2)), 2);

        //2

        List<? extends IResult> times2 = timeComp.measure(in, times1, 2, 100, 1);

        //3

        System.out.print(times1);

        for(int i=0; i<times2.size(); i++){
            System.out.print(times2.get(i).getSize() + " " + times2.get(i).getTime());
        }

    }

}