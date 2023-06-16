package com.serviciosYa.enums;

public enum Estrella {
    UNO(1),
    DOS(2),
    TRES(3),
    CUATRO(4),
    CINCO(5);

    final int cantidad;

    Estrella(int cantidad) {
        this.cantidad = cantidad;
    }
}
