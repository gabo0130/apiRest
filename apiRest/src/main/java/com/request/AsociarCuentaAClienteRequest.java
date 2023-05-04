package com.request;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsociarCuentaAClienteRequest {
    private String cliente;
    private int numeroCuenta;
    private String tipoCuenta;
    private String estado;
    private int saldo;
}
