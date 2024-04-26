package com.xavier;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Set;
import java.util.HashSet;

@ApplicationScoped
public class PedidoService {

    private Set<Long> pedidos = new HashSet<>();

    public void adicionarPedido(Long pedido) {
        pedidos.add(pedido);
    }

    public void cancelPedido(Long pedido) {
        pedidos.remove(pedido);
    }

}
