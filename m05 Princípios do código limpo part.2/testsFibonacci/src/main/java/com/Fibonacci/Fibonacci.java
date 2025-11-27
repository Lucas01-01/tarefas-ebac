package com.Fibonacci;

public class Fibonacci {
    // Criamos um array para simplificar a computação
    private static final int MAX_ELEMENTOS = 100;
    private static final int[] elementosFib = new int[MAX_ELEMENTOS];

    public static int encontrarElementoPD(int n) {
        for (int i = 0; i < MAX_ELEMENTOS;
             i++) {
            elementosFib[i] = -1;
        }

        return encontrarElemento(n);
    }

    public static int encontrarElemento(int n) {
        if (elementosFib[n] == -1) {
            if (n <= 1) {
                elementosFib[n] = n;
            } else {

                elementosFib[n] = encontrarElemento(n - 1) + encontrarElemento(n - 2);
            }
        }

        return elementosFib[n];
    }

    public static void main(String[] args) {
        int n = 15;

        System.out.println("elemento " + n + ": " + encontrarElementoPD(n));
    }
}
