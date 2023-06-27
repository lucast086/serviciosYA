package com.serviciosYa.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "proveedores")
@Entity
public class Proveedor extends Usuario {

    private String imagen;

    @ManyToMany(targetEntity = Oficio.class, fetch = FetchType.LAZY)
    private List<Oficio> oficios;

    @OneToMany(targetEntity = Resenia.class, fetch = FetchType.LAZY, mappedBy = "proveedor")
    private List<Resenia> resenias;

}
