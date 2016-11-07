package com.company;

public class Main {

    public static void main(String[] args) {

    }

    public void problem1(){
        int k,n;

        int[] list = new int[k];
        int[] result = new int[n];
        heap[k];

        for(int i = 0; i< k; i++){
            heap[i] = heap.getMin(list[i]);
        }

        buildMinHeap(heap);

        for(int j=0;j<n;j++){
            array[j] = heap.getMin();
            nextMin = heap.GetMin(list[0]);

            for(int l= 1; l<k;l++){
                if(heap.getMin(list[j] < nextMin))
                    nextMin = heap.GetMin(list[l]);
            }
            MinHeap.insert(heap,nextMin);
        }
    }

    public void problem2(){

        for(int i = 0; i<k; i++){
            heap.insert(0,k);
        }

        while(!heap.empty()){
            i,k = heap.deleteMin();
            result[c++] = lists[k][i];
            i++;
            if(i<lists[k].length)
                heap.insert(i,k);
        }
    }
}