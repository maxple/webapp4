package training;

import java.util.Iterator;

public class DataStructure {

    private final static int SIZE = 15;
    private int[] arrayOfInts = new int[SIZE];

    public DataStructure() {
        for (int i = 0; i < SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }

    public void printEven() {

        Iterator<Integer> iterator = this.new EvenIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    private class EvenIterator implements Iterator<Integer> {

        private int nextIndex = 0;

        public boolean hasNext() {

            return (nextIndex <= SIZE - 1);
        }

        public Integer next() {

            Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);

            nextIndex += 2;
            return retValue;
        }

        @Override
        public void remove() {

        }
    }

    public static void main(String s[]) {

        DataStructure ds = new DataStructure();
        ds.printEven();
    }
}