package com.example.term3.model;

import java.util.Objects;

public class Sock {
    private final Color color;
    private final Size size;
    private final int cottonPersentage;

    public Sock(Color color, Size size, int cottonPersentage) {
        this.color = color;
        this.size = size;
        this.cottonPersentage = cottonPersentage;
    }

    public Color getColor() {
        return color;
    }

    public Size getSize() {
        return size;
    }

    public int getCottonPersentage() {
        return cottonPersentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sock sock = (Sock) o;
        return cottonPersentage == sock.cottonPersentage && color == sock.color && size == sock.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size, cottonPersentage);
    }
}
