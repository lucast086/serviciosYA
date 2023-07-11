package com.serviciosYa.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
@Entity
public class Cliente extends Usuario{

    private String direccion;

    @OneToMany(targetEntity = Solicitud.class, fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Solicitud> solicitudes;

}
