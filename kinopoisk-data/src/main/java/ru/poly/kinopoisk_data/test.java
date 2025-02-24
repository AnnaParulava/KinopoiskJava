package ru.poly.kinopoisk_data;

public class test {
    public static void main(String[] args) {
        print(null);
        print("String");
        print(0.3d);
        print(true);
        print('B');
        print(2);
    }

    static void print(int i) {
        System.out.println("i." + i);
    }

    static void print(String s) {
        System.out.println("s." + s);
    }

    static void print(boolean b) {
        System.out.println("b." + b);
    }

    static void print(Object o) {
        System.out.println("o." + o);
    }
}
