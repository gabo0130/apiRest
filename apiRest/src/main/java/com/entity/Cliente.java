package com.entity;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "clienteId")
public class Cliente extends Persona {
    
    @Column(name = "contrasena")
    private String contrasena;
    
    @Column(name = "estado")
    private String estado;
}
