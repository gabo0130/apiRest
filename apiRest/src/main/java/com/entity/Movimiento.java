package com.entity;

import javax.persistence.*;
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
    private int id;
    
    @Column(name = "fecha")
    private LocalDate fecha;
    
    @Column(name = "tipoMovimiento")
    private String tipoMovimiento;
    
    @Column(name = "valor")
    private int valor;
    
    @Column(name = "saldo")
    private int saldo;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;
 
}
