package com.serviciosYa.entidades;

import com.serviciosYa.enums.Estrella;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resenias")
public class Resenia {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "cant_estrellas")
    private Estrella estrellas;

    @ManyToOne(targetEntity = Proveedor.class)
    private Proveedor proveedor;

}
