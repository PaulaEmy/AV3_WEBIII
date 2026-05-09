package com.autobots.automanager.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controle.ControleUsuario;
import com.autobots.automanager.entitades.Usuario;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler
        implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {

        return EntityModel.of(usuario,

            linkTo(methodOn(ControleUsuario.class)
                    .buscarUsuario(usuario.getId()))
                    .withSelfRel(),

            linkTo(methodOn(ControleUsuario.class)
                    .listarUsuarios())
                    .withRel("usuarios"),

            linkTo(methodOn(ControleUsuario.class)
                    .atualizarUsuario(usuario.getId(), null))
                    .withRel("atualizar"),

            linkTo(methodOn(ControleUsuario.class)
                    .deletarUsuario(usuario.getId()))
                    .withRel("deletar")
        );
    }
}