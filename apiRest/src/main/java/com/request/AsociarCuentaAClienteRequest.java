package com.request;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsociarCuentaAClienteRequest {
    private String cliente;
    private long numeroCuenta;
    private String tipoCuenta;
    private String estado;
    private long saldo;
}
