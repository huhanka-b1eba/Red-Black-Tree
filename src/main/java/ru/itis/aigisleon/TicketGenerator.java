package ru.itis.aigisleon;

import ru.itis.aigisleon.model.Ticket;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class TicketGenerator {
    public static List<Ticket> generate (int count) {
        List<Ticket> tickets = new ArrayList<>();
        Random random = new Random();
        HashSet<Integer> usedIds = new HashSet<>();
        int i = 0;
        while (tickets.size() < count) {
            int seatId = 1 + random.nextInt(count);
            if (usedIds.add(seatId)) {
                String customer = "Клиент_" + i++;
                tickets.add(new Ticket(seatId, customer));
            }
        }
        return tickets;
    }

    public static void main(String[] args) {
        int n = 10000;
        String fileName = "rbt_test.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(n);
            generate(n).forEach(writer::println);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
