package cs311.hw4;

import java.util.Random;

/**
 * Created by taylorsdugger on 10/5/16.
 */
public class SlowMatrixFactory implements IMeasureFactory {


    @Override
    public IMeasurable createRandom(int size) {
        Random r = new Random();
        int[][] data = new int[size][size];

        SlowMatrix m = new SlowMatrix(data);

        for (int i = 0; i < size; i++){
            for(int j = 0; j< size; j++){
                m.setElement(i,j,r.nextInt(1000));
            }
        }

        return m;
    }
}
