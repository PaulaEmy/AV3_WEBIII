package com.autobots.automanager.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controle.ControleVenda;
import com.autobots.automanager.entitades.Venda;

@Component
public class VendaModelAssembler
        implements RepresentationModelAssembler<Venda, EntityModel<Venda>> {

    @Override
    public EntityModel<Venda> toModel(Venda venda) {

        return EntityModel.of(venda,

            linkTo(methodOn(ControleVenda.class)
                    .buscarPorId(venda.getId()))
                    .withSelfRel(),

            linkTo(methodOn(ControleVenda.class)
                    .listar())
                    .withRel("vendas"),

            linkTo(methodOn(ControleVenda.class)
                    .cadastrar(null))
                    .withRel("cadastrar"),

            linkTo(methodOn(ControleVenda.class)
                    .deletar(venda.getId()))
                    .withRel("deletar")
        );
    }
}