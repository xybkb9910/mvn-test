package org.example;


import org.example.until.ExcelUntil;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        new ExcelUntil().getTestDate("Sheet1");
    }
}