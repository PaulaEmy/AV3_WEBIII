package com.autobots.automanager.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controle.ControleMercadoria;
import com.autobots.automanager.entitades.Mercadoria;

@Component
public class MercadoriaModelAssembler
        implements RepresentationModelAssembler<Mercadoria, EntityModel<Mercadoria>> {

    @Override
    public EntityModel<Mercadoria> toModel(Mercadoria mercadoria) {

        return EntityModel.of(mercadoria,

            linkTo(methodOn(ControleMercadoria.class)
                    .buscarPorId(mercadoria.getId()))
                    .withSelfRel(),

            linkTo(methodOn(ControleMercadoria.class)
                    .listar())
                    .withRel("mercadorias"),

            linkTo(methodOn(ControleMercadoria.class)
                    .atualizar(mercadoria.getId(), null))
                    .withRel("atualizar"),

            linkTo(methodOn(ControleMercadoria.class)
                    .deletar(mercadoria.getId()))
                    .withRel("deletar")
        );
    }
}