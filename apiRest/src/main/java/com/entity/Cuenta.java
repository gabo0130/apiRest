package com.entity;

import javax.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cuenta")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "numeroCuenta")
    private String numeroCuenta;

    @Column(name = "tipoCuenta")
    private String tipoCuenta;

    
    @Column(name = "saldoInicial")
    private int saldoInicial;

    
    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<Movimiento> movimientos;

    
}
