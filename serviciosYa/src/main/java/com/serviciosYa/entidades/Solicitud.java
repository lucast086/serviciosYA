package com.serviciosYa.entidades;

import com.serviciosYa.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Solicitud {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    private  Cliente cliente;

    @ManyToOne
    private  Proveedor proveedor;

    @ManyToOne
    private  Oficio oficio;

    private String descripcion;

    private Estado estado; //importe la clase enums.Estado

    private float costo;

    private String comentario;

    @Temporal(TemporalType.DATE)
    private Date fechaServicio;

}
