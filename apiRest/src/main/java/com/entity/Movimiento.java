package com.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movimiento")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "fecha")
    private LocalDate fecha;
    
    @Column(name = "tipoMovimiento")
    private String tipoMovimiento;
    
    @Column(name = "valor")
    private long valor;
    
    @Column(name = "saldoInicial")
    private long saldoInicial;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;
 
}
