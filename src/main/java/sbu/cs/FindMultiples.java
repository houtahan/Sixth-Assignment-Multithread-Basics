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
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FindMultiples
{
    public class DivisionBy3 implements Runnable{
        private int n;
        private ArrayList<Integer> div3 = new ArrayList<>();
        public DivisionBy3(int n){
            this.n = n;
        }
        public void run() {
            for (int i = 1; i <= n; i++){
                if (i % 3 == 0){
                    div3.add(i);
                }
            }
        }
        public ArrayList<Integer> getDiv3() {
            return div3;
        }
    }

    public class DivisionBy5 implements Runnable{
        private int n;
        private ArrayList<Integer> div5 = new ArrayList<>();
        public DivisionBy5(int n){
            this.n = n;
        }
        public void run() {
            for (int i = 1; i <= n; i++){
                if (i % 5 == 0){
                    div5.add(i);
                }
            }
        }
        public ArrayList<Integer> getDiv5() {
            return div5;
        }
    }

    public class DivisionBy7 implements Runnable{
        private int n;
        private ArrayList<Integer> div7 = new ArrayList<>();
        public DivisionBy7(int n){
            this.n = n;
        }
        public void run() {
            for (int i = 1; i <= n; i++){
                if (i % 7 == 0){
                    div7.add(i);
                }
            }
        }
        public ArrayList<Integer> getDiv7() {
            return div7;
        }
    }

    // TODO create the required multithreading class/classes using your preferred method.


    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */
    public long getSum(int n) {
        ArrayList<Integer> div357 = new ArrayList<>();
        long sum = 0;
        DivisionBy3 divisionBy3 = new DivisionBy3(n);
        Thread thread3 = new Thread(divisionBy3);
        DivisionBy5 divisionBy5 = new DivisionBy5(n);
        Thread thread5 = new Thread(divisionBy5);
        DivisionBy7 divisionBy7 = new DivisionBy7(n);
        Thread thread7 = new Thread(divisionBy7);
        thread3.start();
        thread5.start();
        thread7.start();
        try {
            thread3.join();
            thread5.join();
            thread7.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        div357.addAll(divisionBy3.getDiv3());
        div357.addAll(divisionBy5.getDiv5());
        div357.addAll(divisionBy7.getDiv7());
        Set<Integer> set = new HashSet<>(div357);
        div357.clear();
        div357.addAll(set);
        for (int i = 0; i < div357.size(); i++){
            sum = div357.get(i) + sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
    }
}
