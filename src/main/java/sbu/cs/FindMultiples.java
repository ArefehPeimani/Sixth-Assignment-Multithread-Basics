package sbu.cs;

/*
    In this exercise, you must write a multithreaded program that finds all
    integers in the range [1, n] that are divisible by 3, 5, or 7. Return the
    sum of all unique integers as your answer.
    Note that an integer such as 15 (which is a multiple of 3 and 5) is only
    counted once.

    The Positive integer n > 0 is given to you as input. Create as many threads as
    you need to solve the problem. You can use a Thread Pool for bonus points.

    Example:
    Input: n = 10
    Output: sum = 40
    Explanation: Numbers in the range [1, 10] that are divisible by 3, 5, or 7 are:
    3, 5, 6, 7, 9, 10. The sum of these numbers is 40.

    Use the tests provided in the test folder to ensure your code works correctly.
 */

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FindMultiples
{

    public class Multiples implements Runnable {
        private int divisor;
        private int n;
        private ArrayList<Integer> multiples = new ArrayList<>();

        public Multiples(int divisor, int n) {
            this.divisor = divisor;
            this.n = n;
        }

        @Override
        public void run() {
            for (int i = 1; i <= n; i++) {
                if (i % divisor == 0) {
                    multiples.add(i);
                }
            }
        }

        public ArrayList<Integer> getMultiples() {
            return multiples;
        }
    }

    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */
    public int getSum(int n) {
        int sum = 0;

        //start tasks in a Thread Pool
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        Multiples multiplesOf3 = new Multiples(3, n);
        threadPool.execute(multiplesOf3);
        Multiples multiplesOf5 = new Multiples(5, n);
        threadPool.execute(multiplesOf5);
        Multiples multiplesOf7 = new Multiples(7, n);
        threadPool.execute(multiplesOf7);
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //put all multiples in an arraylist and get the sum of the unique ones
        ArrayList<Integer> allMultiples = new ArrayList<>();
        allMultiples.addAll(multiplesOf3.getMultiples());
        allMultiples.addAll(multiplesOf5.getMultiples());
        allMultiples.addAll(multiplesOf7.getMultiples());

        ArrayList<Integer> unique = new ArrayList<>();
        for (Integer multiple : allMultiples) {
            if (!unique.contains(multiple)) {
                unique.add(multiple);
                sum += multiple;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
    }
}
