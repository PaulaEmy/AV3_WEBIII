package com.autobots.automanager.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controle.ControleVeiculo;
import com.autobots.automanager.entitades.Veiculo;

@Component
public class VeiculoModelAssembler
        implements RepresentationModelAssembler<Veiculo, EntityModel<Veiculo>> {

    @Override
    public EntityModel<Veiculo> toModel(Veiculo veiculo) {

        return EntityModel.of(veiculo,

            linkTo(methodOn(ControleVeiculo.class)
                    .buscarPorId(veiculo.getId()))
                    .withSelfRel(),

            linkTo(methodOn(ControleVeiculo.class)
                    .listar())
                    .withRel("veiculos"),

            linkTo(methodOn(ControleVeiculo.class)
                    .atualizar(veiculo.getId(), null))
                    .withRel("atualizar"),

            linkTo(methodOn(ControleVeiculo.class)
                    .deletar(veiculo.getId()))
                    .withRel("deletar")
        );
    }
}