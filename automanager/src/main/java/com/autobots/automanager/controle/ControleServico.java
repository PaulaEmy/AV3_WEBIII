package com.autobots.automanager.controle;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.assembler.ServicoModelAssembler;
import com.autobots.automanager.dto.ServicoDTO;
import com.autobots.automanager.entitades.Servico;
import com.autobots.automanager.services.ServicoServico;

@RestController
@RequestMapping("/servicos")
public class ControleServico {

    @Autowired
    private ServicoServico servico;

    @Autowired
    private ServicoModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Servico>> listar() {

        List<EntityModel<Servico>> servicos = servico
                .listar()
                .stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(servicos,

            linkTo(methodOn(ControleServico.class)
                    .listar())
                    .withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Servico> buscarPorId(
            @PathVariable Long id) {

        Servico servicoEncontrado = servico.buscarPorId(id);

        return assembler.toModel(servicoEncontrado);
    }

    @PostMapping
    public EntityModel<Servico> cadastrar(
            @RequestBody ServicoDTO dto) {

        Servico servicoCadastrado = servico.cadastrar(dto);

        return assembler.toModel(servicoCadastrado);
    }

    @PutMapping("/{id}")
    public EntityModel<Servico> atualizar(
            @PathVariable Long id,
            @RequestBody ServicoDTO dto) {

        Servico servicoAtualizado = servico.atualizar(id, dto);

        return assembler.toModel(servicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(
            @PathVariable Long id) {

        servico.deletar(id);

        return ResponseEntity.noContent().build();
    }
}