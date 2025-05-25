package ru.itis.aigisleon;

import ru.itis.aigisleon.model.Ticket;
import ru.itis.aigisleon.model.TicketBookingSystem;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Console {
    final String GREEN = "\u001B[32m";
    final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Console console = new Console();

        console.process();
    }

    private TicketBookingSystem sys;
    private Scanner sc = new Scanner(System.in);

    public void printMenu() {
        System.out.println(GREEN + "book - забронировать билет \n" +
                           "cancel - отменить бронь \n" +
                           "find - найти человека, сидящего на этом месте \n" +
                            "exit - выход" + RESET
        );
    }

    public void process() {
        System.out.println("Добро пожаловать в Айгизвиасейлс, сервис по бронированию дешёвых билетов");
        sys = new TicketBookingSystem();
        sys.setSpace(50);
        while (true) {
            printMenu();
            String command = sc.next();
            if (command.equals("book")) {
                printAvailable();
                book();
            } else if (command.equals("cancel")) {
                printOccupied();
                cancel();
            } else if (command.equals("find")) {
                printOccupied();
                find();
            } else if (command.equals("exit")) {
                return;
            }
        }
    }

    private void printAvailable() {
        List<Integer> occupied = sys.getAllBookings().stream().map(Ticket::getSeatId).toList();
        List<Integer> available = new ArrayList<>();
        for (int i = 1; i <= sys.getSpace(); i++) {
            available.add(i);
        }
        available.removeAll(occupied);
        System.out.println("Список свободных мест: " + available);
    }

    private void printOccupied() {
        List<Integer> occupied = sys.getAllBookings().stream().map(Ticket::getSeatId).toList();
        System.out.println("Список занятых мест: " + occupied);
    }

    private void book() {
        System.out.print("Введите номер места: ");
        int n = sc.nextInt();
        System.out.print("Введите ваше имя: ");
        String s = sc.next();
        boolean ok = sys.bookTicket(new Ticket(n, s));
        if (!ok)
            System.out.println("Не удалось забронировать, так как этого места нет в списке свободных мест");;
    }

    private void cancel() {
        System.out.print("Введите номер места: ");
        int n = sc.nextInt();
        boolean ok = sys.cancelBooking(n);
        if (!ok) System.out.println("Не удалось отменить бронь, так как это место не занято");
    }

    private void find() {
        System.out.print("Введите номер места: ");
        int n = sc.nextInt();
        Ticket found = sys.findBooking(n);
        if (found == null) {
            System.out.println("Это место свободно");
        } else {
            System.out.println(found);
        }
    }

}
