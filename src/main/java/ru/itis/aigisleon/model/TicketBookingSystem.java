package ru.itis.aigisleon.model;

import ru.itis.aigisleon.tree.RedBlackTree;

import java.util.List;

public class TicketBookingSystem {
    private final RedBlackTree<Ticket> bookings = new RedBlackTree<>();
    private int space = 10001;

    // Бронирование места
    public boolean bookTicket(Ticket ticket) {
        if (bookings.search(ticket) != bookings.getNil() || ticket.getSeatId() > space) {
            return false;
        }
        bookings.insert(ticket);
        return true;
    }

    // Отмена брони
    public boolean cancelBooking(int seatId) {
        Ticket dummyTicket = new Ticket(seatId, "");
        Ticket found = bookings.search(dummyTicket).values;
        if (found == bookings.getNil().values) {
            return false;  // Место не найдено
        }
        bookings.delete(found);
        return true;
    }

    // Поиск брони по ID места
    public Ticket findBooking(int seatId) {
        return bookings.search(new Ticket(seatId, "")).values;
    }

    public List<Ticket> getAllBookings() {
        return bookings.inOrderTraversal();
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public int getSpace() {
        return space;
    }
}