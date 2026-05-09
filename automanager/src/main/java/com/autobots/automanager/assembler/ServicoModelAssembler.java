package com.autobots.automanager.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controle.ControleServico;
import com.autobots.automanager.entitades.Servico;

@Component
public class ServicoModelAssembler
        implements RepresentationModelAssembler<Servico, EntityModel<Servico>> {

    @Override
    public EntityModel<Servico> toModel(Servico servico) {

        return EntityModel.of(servico,

            linkTo(methodOn(ControleServico.class)
                    .buscarPorId(servico.getId()))
                    .withSelfRel(),

            linkTo(methodOn(ControleServico.class)
                    .listar())
                    .withRel("servicos"),

            linkTo(methodOn(ControleServico.class)
                    .atualizar(servico.getId(), null))
                    .withRel("atualizar"),

            linkTo(methodOn(ControleServico.class)
                    .deletar(servico.getId()))
                    .withRel("deletar")
        );
    }
}