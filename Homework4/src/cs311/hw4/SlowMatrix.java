package cs311.hw4;


/**
 * Created by taylorsdugger on 10/4/16.
 */

public class SlowMatrix implements IMatrix, IMeasurable {

    private int[][] arr;

    public SlowMatrix(int[][] data){
        arr = data;

    }

    @Override
    public IMatrix multiply(IMatrix mat) throws IllegalArgumentException{

        SlowMatrix matrixA = new SlowMatrix(arr);
        SlowMatrix matrixB = (SlowMatrix) mat;
        int aRow = 0, aCol = 0, bRow = 0, bCol = 0;

        while(true){
            if(matrixA.getElement(1,aCol) == null ){
                break;
            }else{
                aCol++;
            }
        }
        while(true){
            if(matrixA.getElement(aRow,1) == null) {
                break;
            }else{
                aRow++;
            }
        }
        while(true){
            if(matrixB.getElement(1,bCol) == null){
                break;
            }else{
                bCol++;
            }
        }

        while(true){
            if(matrixB.getElement(bRow,1) == null){
                break;
            }else{
                bRow++;
            }
        }

        int[][] newMat = new int[aRow][bCol];

        if(aCol != bRow){
            throw new IllegalArgumentException();
        }

        for(int i =0; i < aRow; i++){
            for (int j = 0; j < bCol; j++) {
                for (int k = 0; k < aCol; k++){
                    newMat[i][j] += matrixA.getElement(i,k).intValue() * matrixB.getElement(k,j).intValue();
                }
            }
        }

        IMatrix newM = new SlowMatrix(newMat);

        return newM;
    }

    @Override
    public IMatrix subMatrix(int upperLeftRow, int upperLeftCol, int lowerRightRow, int lowerRightCol) throws IllegalArgumentException{

        return null;
    }

    @Override
    public IMatrix add(IMatrix mat) throws IllegalArgumentException {
        SlowMatrix matrixA = new SlowMatrix(arr);
        SlowMatrix matrixB = (SlowMatrix) mat;
        int aRow = 0, aCol = 0, bRow = 0, bCol = 0;

        while(true){
            if(matrixA.getElement(1,aCol) == null ){
                break;
            }else{
                aCol++;
            }
        }
        while(true){
            if(matrixA.getElement(aRow,1) == null) {
                break;
            }else{
                aRow++;
            }
        }
        while(true){
            if(matrixB.getElement(1,bCol) == null){
                break;
            }else{
                bCol++;
            }
        }

        while(true){
            if(matrixB.getElement(bRow,1) == null){
                break;
            }else{
                bRow++;
            }
        }

        int[][] newMat = new int[aRow][bCol];

        for(int i = 0; i< aRow; i++){
            for(int j = 0; j<aCol; j++){
                newMat[i][j] = matrixA.getElement(i,j).intValue() + matrixB.getElement(i,j).intValue();
            }
        }

        IMatrix newM = new SlowMatrix(newMat);

        return newM;
    }

    @Override
    public Number getElement(int row, int col) throws IllegalArgumentException {
        try {
            return arr[row][col];
        }catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    @Override
    public void setElement(int row, int col, Number val) throws IllegalArgumentException {
        try{
            arr[row][col] = (int) val;
        }catch (ArrayIndexOutOfBoundsException e){
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void execute() {
        this.multiply(this);
        //this.add(this); //So change this around for part 4
    }
}

