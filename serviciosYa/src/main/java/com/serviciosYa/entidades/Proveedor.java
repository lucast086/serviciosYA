package com.serviciosYa.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "proveedores")
@Entity
@NamedNativeQuery(
        name = "Proveedor.findByOficio",
        query = "SELECT * FROM proveedores p WHERE p.id IN ( SELECT proveedor_id FROM proveedores_oficios po WHERE po.oficios_id = :oficioId)",
        resultClass = Proveedor.class
)
public class Proveedor extends Usuario {

    @OneToOne(targetEntity = Imagen.class)
    private Imagen imagen;

    @Column(name = "calificacion")
    private Float calificacion;

    @ManyToMany(targetEntity = Oficio.class, fetch = FetchType.LAZY)
    private List<Oficio> oficios;

    @OneToMany(targetEntity = Resenia.class, fetch = FetchType.LAZY, mappedBy = "proveedor")
    private List<Resenia> resenias;

    @OneToMany(targetEntity = Solicitud.class, fetch = FetchType.LAZY, mappedBy = "proveedor")
    private List<Solicitud> Solicitudes;

}
