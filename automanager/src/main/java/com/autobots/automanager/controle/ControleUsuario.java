package com.autobots.automanager.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.autobots.automanager.assembler.UsuarioModelAssembler;
import com.autobots.automanager.dto.UsuarioDTO;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.services.ServicoUsuario;

@RestController
@RequestMapping("/usuarios")
public class ControleUsuario {
    @Autowired
    private ServicoUsuario servico;

    @Autowired
    private UsuarioModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Usuario>> listarUsuarios() {

        List<EntityModel<Usuario>> usuarios = servico
                .listarUsuarios()
                .stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(usuarios,

            linkTo(methodOn(ControleUsuario.class)
                    .listarUsuarios())
                    .withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Usuario> buscarUsuario(
            @PathVariable Long id) {

        Usuario usuario = servico.buscarPorId(id);

        return assembler.toModel(usuario);
    }

    @PostMapping
    public EntityModel<Usuario> cadastrarUsuario(
            @RequestBody UsuarioDTO dto) {

        Usuario usuario = servico.cadastrarUsuario(dto);

        return assembler.toModel(usuario);
    }

    @PutMapping("/{id}")
    public EntityModel<Usuario> atualizarUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioDTO dto) {

        Usuario usuario = servico.atualizarUsuario(id, dto);

        return assembler.toModel(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(
            @PathVariable Long id) {

        servico.deletarUsuario(id);

        return ResponseEntity.noContent().build();
    }
}
