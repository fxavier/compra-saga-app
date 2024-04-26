package com.xavier;
import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreditoService {
    private double credito;

    private Map<Long, Double> pedido_valor = new HashMap<>();

    public CreditoService() {
       this.credito = 100.0;
    }

    public void adicionarPedido(Long pedido, Double valor) {
        if (credito < valor) {
            throw new IllegalStateException("Saldo insuficiente");
        }
        pedido_valor.put(pedido, valor);
        credito -= valor;
    }

    public void cancelPedido(Long pedido) {
        Double valor = pedido_valor.get(pedido);
        if (valor != null) {
            credito += valor;
            pedido_valor.remove(pedido);
        }
    }

    public double getCredito() {
        return credito;
    }


}
