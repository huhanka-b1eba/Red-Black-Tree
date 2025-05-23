package ru.itis.aigisleon;

import ru.itis.aigisleon.model.Ticket;
import ru.itis.aigisleon.model.TicketBookingSystem;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName = "";

        File file = new File("rbt_result.csv");

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("iteration, numberOfData, addingTime, findingTime, deletingTime");
            for (int i = 0; i < 100; i++) {
                fileName = "ticket_rbt_tests/ticket_rbt_test_" + i + ".txt";
                try (Scanner sc = new Scanner(new File(fileName))) {
                    TicketBookingSystem system = new TicketBookingSystem();
                    int n = sc.nextInt();

                    int toFind = 0;
                    for (int j = 0; j < n - 1; j++) {
                        if (j == n / 2) {
                            toFind = sc.nextInt();
                            system.bookTicket(new Ticket(toFind, sc.next(), sc.next()));
                        } else {
                            system.bookTicket(new Ticket(sc.nextInt(), sc.next(), sc.next()));
                        }
                    }
                    long addStart = System.nanoTime();
                    system.bookTicket(new Ticket(sc.nextInt(), sc.next(), sc.next()));
                    long addFinish = System.nanoTime();

                    long findStart = System.nanoTime();
                    system.findBooking(toFind);
                    long findFinish = System.nanoTime();

                    long deleteStart = System.nanoTime();
                    system.cancelBooking(toFind);
                    long deleteFinish = System.nanoTime();


                    writer.println(i + ", " + n + ", " + (addFinish - addStart) + ", " + (findFinish - findStart) + ", " + (deleteFinish - deleteStart));
                    writer.flush();

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}