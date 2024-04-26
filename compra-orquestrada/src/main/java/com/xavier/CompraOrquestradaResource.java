package com.xavier;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/compra")
public class CompraOrquestradaResource {
    
    @Inject
    private CreditoService creditoService;

    @Inject
    private PedidoService pedidoService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response saga() {
        Long pedido = 1L;
        comprar(pedido++, 20.0);
        comprar(pedido++, 30.0);
        comprar(pedido++, 20.0);
        comprar(pedido++, 35.0);
        return Response.ok().build();
    }

    private void comprar(Long pedido, Double valor) {
        pedidoService.adicionarPedido(pedido);
       try {
         creditoService.adicionarPedido(pedido, valor);
         System.out.println("Pedido " + pedido + "  no valor de " + valor + " realizado com sucesso");
       } catch (IllegalStateException e) {
           pedidoService.cancelPedido(pedido);
           System.err.println("Pedido " + pedido + "  no valor de " + valor + " não foi realizado: " + e.getMessage());
       }
    }


}
