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
            int seatId = 1 + random.nextInt(count * 5);
            if (usedIds.add(seatId)) {
                String customer = "Клиент_" + i++;
                tickets.add(new Ticket(seatId, customer));
            }
        }
        return tickets;
    }

    public static void main(String[] args) {
        int n = 0;
        String dir = "ticket_rbt_tests";
        new File(dir).mkdirs();
        for (int i = 0; i < 100; i++) {
            n += 100;
            String fileName = dir + "/ticket_rbt_test_" + i + ".txt";
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
                writer.println(n);
                generate(n).forEach(writer::println);
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
