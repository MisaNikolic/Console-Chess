package com.misa.chess;

public class Test {

    public static void main(String[] args) {

        int x = 5;
        int i = 10;
        while(i >0) {
            if(i % 2 == 0) {
                System.out.println("i = " + i + "-> Even");
                i--;
                continue;
            }
            if(i == 5) {
                System.out.println("i = " + i + "-> We are half way there.");
                i--;
                continue;
            }

            if(i == 1) {
                System.out.println("i = " + i + "-> Exiting the loop");
                break;
            }

            System.out.println(i);
            i--;
        }
    }
}
