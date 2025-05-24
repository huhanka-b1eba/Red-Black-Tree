package ru.itis.aigisleon;

import ru.itis.aigisleon.model.Ticket;
import ru.itis.aigisleon.model.TicketBookingSystem;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.function.Function;

public class Console {
    public static void main(String[] args) {
        Console console = new Console();

        console.process();
    }

    private TicketBookingSystem sys;
    private Scanner sc = new Scanner(System.in);

    public void printMenu() {
        System.out.println("book - забронировать билет \n" +
                           "cancel - отменить бронь \n" +
                           "find - найти человека, сидящего на этом месте \n" +
                            "exit - выход"
        );
    }

    public void process() {
        System.out.println("Добро пожаловать в Айгизвиасейлс, сервис по бронированию дешёвых билетов");
        sys = new TicketBookingSystem();
        while (true) {
            printMenu();
            String command = sc.next();
            if (command.equals("book")) {
                book();
            } else if (command.equals("cancel")) {
                cancel();
            } else if (command.equals("find")) {
                find();
            } else if (command.equals("exit")) {
                return;
            }
        }
    }

    private void book() {
        System.out.print("Введите номер билета: ");
        int n = sc.nextInt();
        System.out.print("Введите ваше имя: ");
        String s = sc.next();
        sys.bookTicket(new Ticket(n, s));
    }

    private void cancel() {
        System.out.print("Введите номер билета: ");
        int n = sc.nextInt();
        sys.cancelBooking(n);
    }

    private void find() {
        System.out.println("Введите номер билета: ");
        int n = sc.nextInt();
        System.out.println(sys.findBooking(n));
    }

}
