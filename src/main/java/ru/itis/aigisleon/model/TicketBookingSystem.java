package ru.itis.aigisleon.model;

import ru.itis.aigisleon.tree.RedBlackTree;

public class TicketBookingSystem {
    private final RedBlackTree<Ticket> bookings = new RedBlackTree<>();

    // Бронирование места
    public boolean bookTicket(Ticket ticket) {
        if (bookings.search(ticket) != null) {
            return false;  // Место уже занято
        }
        bookings.insert(ticket);
        return true;
    }

    // Отмена брони
    public boolean cancelBooking(int seatId) {
        Ticket dummyTicket = new Ticket(seatId, "");
        Ticket found = bookings.search(dummyTicket).values;
        if (found == null) {
            return false;  // Место не найдено
        }
        bookings.delete(found);
        return true;
    }

    // Поиск брони по ID места
    public Ticket findBooking(int seatId) {
        return bookings.search(new Ticket(seatId, "")).values;
    }

    /*
    // Вывод всех бронирований (в отсортированном порядке)
    public void printAllBookings() {
        bookings.inOrderTraversal();  // Нужно реализовать в RBT
    }
     */
}