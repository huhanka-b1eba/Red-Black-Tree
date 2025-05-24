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
            writer.println("numberOfData, addingTime, findingTime, deletingTime");
            fileName = "rbt_test.txt";
            try (Scanner sc = new Scanner(new File(fileName))) {
                TicketBookingSystem system = new TicketBookingSystem();
                int n = sc.nextInt();
                long[] addingTime = new long[n];
                long[] deletingTime = new long[n];
                long[] findingTime = new long[n];

                for (int i = 0; i < n; i++) {
                    int seat = sc.nextInt();
                    String name = sc.next();
                    String time = sc.next();
                    Ticket ticket = new Ticket(seat, name, time);

                    long addStart = System.nanoTime();
                    system.bookTicket(ticket);
                    long addFinish = System.nanoTime();
                    addingTime[i] = addFinish - addStart;

                    long findStart = System.nanoTime();
                    system.findBooking(seat);
                    long findFinish = System.nanoTime();
                    findingTime[i] = findFinish - findStart;

                    long deleteStart = System.nanoTime();
                    system.cancelBooking(seat);
                    long deleteFinish = System.nanoTime();

                    deletingTime[i] = deleteFinish - deleteStart;
                    system.bookTicket(ticket);
                }

                for (int i = 0; i < n; i++) {
                    writer.println((i+1) + ", " + addingTime[i] + ", " + findingTime[i] + ", " + deletingTime[i]);
                }

                writer.flush();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}