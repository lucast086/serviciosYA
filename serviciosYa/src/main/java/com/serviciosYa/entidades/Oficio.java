package com.serviciosYa.entidades;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oficios")
public class Oficio {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;

    @Column(name = "nombre_oficio")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "icono")
    private String codigoIcono;

    @Column(name = "color")
    private String colorTarjeta;

}
