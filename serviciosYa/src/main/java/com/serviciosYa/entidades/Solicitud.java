package com.serviciosYa.entidades;
import com.serviciosYa.enums.Estado;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "solicitud")
@NamedNativeQuery(
        name = "Solicitud.findByClienteId",
        query = "SELECT * FROM solicitud where cliente_id = :userId",
        resultClass = Solicitud.class
)
@NamedNativeQuery(
        name = "Solicitud.findByProveedorId",
        query = "SELECT * FROM solicitud where proveedor_id = :userId",
        resultClass = Solicitud.class
)
public class Solicitud {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne(targetEntity = Cliente.class)
    private Cliente cliente;

    @ManyToOne(targetEntity = Proveedor.class)
    private Proveedor proveedor;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name = "costo")
    private Float costo;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "Fecha_Servicio")
    @Temporal(TemporalType.DATE)
    private Date fechaServicio;

}
