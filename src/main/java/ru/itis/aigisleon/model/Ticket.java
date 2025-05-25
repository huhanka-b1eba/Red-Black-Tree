package ru.itis.aigisleon.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket implements Comparable<Ticket> {
    private final int seatId;
    private final String customerName;
    private final String bookingTime;

    public Ticket(int seatId, String customerName) {
        this.seatId = seatId;
        this.customerName = customerName;
        this.bookingTime = LocalDateTime.now().toString();
    }

    public Ticket(int seatId, String customerName, String bookingTime) {
        this.seatId = seatId;
        this.customerName = customerName;
        this.bookingTime = bookingTime;
    }

    public int getSeatId() {
        return seatId;
    }

    @Override
    public String toString() {
        return "" + seatId + " " + customerName + " " + bookingTime;
    }

    @Override
    public int compareTo(Ticket o) {
        return seatId - o.seatId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return seatId == ticket.seatId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(seatId);
    }
}
