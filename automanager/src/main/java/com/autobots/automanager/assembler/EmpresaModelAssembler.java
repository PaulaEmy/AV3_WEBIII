package com.autobots.automanager.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controle.ControleEmpresa;
import com.autobots.automanager.entitades.Empresa;

@Component
public class EmpresaModelAssembler implements RepresentationModelAssembler<Empresa, EntityModel<Empresa>> {

    @Override
    public EntityModel<Empresa> toModel(Empresa empresa) {

        return EntityModel.of(empresa,

            linkTo(methodOn(ControleEmpresa.class)
                    .buscarEmpresa(empresa.getId()))
                    .withSelfRel(),

            linkTo(methodOn(ControleEmpresa.class)
                    .listarEmpresas())
                    .withRel("empresas"),

            linkTo(methodOn(ControleEmpresa.class)
                    .atualizarEmpresa(empresa.getId(), null))
                    .withRel("atualizar"),

            linkTo(methodOn(ControleEmpresa.class)
                    .deletarEmpresa(empresa.getId()))
                    .withRel("deletar")
        );
    }
}