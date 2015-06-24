package ru.koleslena.demo.util;


/**
 * Created by elenko on 24.06.15.
 */
public class GenerateSql {

    public static void main(String[] args) {

        for(int i = 0; i < 100; i++) {
            System.out.printf("insert into Item (content, name, published, id) values ('content %d', 'name %d', %s , '%d');%n", i, i, (i & 1) == 0 ? "TRUE" : "FALSE", i);
        }
    }
}
