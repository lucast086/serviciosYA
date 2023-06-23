package com.serviciosYa.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
@Entity
public class Cliente extends Usuario{

    private String direccion;

}
