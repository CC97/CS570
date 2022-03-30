package Homework2;

/*
    Homework 2
    Name: Cheng Chen
    CWID: 10473438
*/
import java.lang.Math;
public class Complexity{
    //O(n^2)
    public static void method1(int n) {
        int counter = 0;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                counter++; 
                System.out.println("Operation " + counter);
            }
        }
        System.out.println("Number of times: " + counter);
    }

    //O(n^3)
    public static void method2(int n) {
        int counter = 0;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                for(int z = 0; z < n; z++)
                {
                    counter++;
                    System.out.println("Operation " + counter);
                }                
            }
        }
        System.out.println("Number of times: " + counter);
    }

    //O(logn)
    public static void method3(int n) {
        int counter = 0;
        for(int i = 1; i < n; i *= 2)
        {
            counter++;
            System.out.println("Operation " + counter);
        }
        System.out.println("Number of times: " + counter);
    }

    //O(nlogn)
    public static void method4(int n) {
        int counter = 0;      
        for(int j = 0; j < n; j++)
        {
            for(int i = 1; i < n; i *= 2)
            {
                counter++;
                System.out.println("Operation " + counter);
            }
        } 
        System.out.println("Number of times: " + counter);
    }

    //O(loglogn)
    public static void method5(int n) {
        int counter = 0;
        for(double j = 1; j < (Math.log(n)/Math.log(2)); j *= 2)
        {
            counter++;
            System.out.println("Operation " + counter);
        }
        System.out.println("Number of times: " + counter);
    }

    //O(2^n)
    public static int num = 0;
    public static int method6(int n) {
        num++;
        System.out.println("Operation " + num);
        if(n == 1)
        {
            return num;
        }
        else
        {
            method6(n - 1);
            method6(n - 1);
            return num;
        }
    }

    public static void main(String[] args) {
        
        //method1(10);
        //method2(10);
        //method3(10000);
        //method4(10);
        method5(10000);

        //method6(4);
        //System.out.println("Number of times for method6: " + num);
        
    }
}
