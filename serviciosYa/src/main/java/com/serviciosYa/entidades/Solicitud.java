package com.serviciosYa.entidades;


import com.serviciosYa.enums.Estado;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
public class Solicitud {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    // private  Cliente cliente;
    // private  Proveedor proveedor;
    // private  Prestacion prestacion;
    private Estado estado; //importe la clase enums.Estado
    private String descripcion;
    private float costo;
    private String comentario;
    @Temporal(TemporalType.DATE)
    private Date fechaServicio;

}
