package com.serviciosYa.enums;

public enum Estado {
    PENDIENTE("PENDIENTE"),
    PRESUPUESTADO("PRESUPUESTADO"),
    ACEPTADO("ACEPTADO"),
    CANCELADO("CANCELADO"),
    FINALIZADO("FINALIZADO");

    final String valor;

    Estado(String valor){this.valor = valor;}

}
