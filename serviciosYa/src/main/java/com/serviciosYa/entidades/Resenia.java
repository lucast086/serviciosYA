package com.serviciosYa.entidades;

import com.serviciosYa.enums.Estrella;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    @Enumerated(EnumType.ORDINAL)
    private Estrella estrellas;

    @ManyToOne(targetEntity = Proveedor.class)
    private Proveedor proveedor;

}
