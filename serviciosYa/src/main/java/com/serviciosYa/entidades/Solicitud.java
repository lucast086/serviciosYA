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
@Table(name = "solicitud")
public class Solicitud {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne(targetEntity = Cliente.class)
    private  Cliente cliente;

    @ManyToOne(targetEntity = Proveedor.class)
    private  Proveedor proveedor;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name = "costo")
    private float costo;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "Fecha_Servicio")
    @Temporal(TemporalType.DATE)
    private Date fechaServicio;

}
