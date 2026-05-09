package com.autobots.automanager.controle;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.assembler.VeiculoModelAssembler;
import com.autobots.automanager.dto.VeiculoDTO;
import com.autobots.automanager.entitades.Veiculo;
import com.autobots.automanager.services.ServicoVeiculo;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/veiculos")
public class ControleVeiculo {

    @Autowired
    private ServicoVeiculo servico;

    @Autowired
    private VeiculoModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Veiculo>> listar() {

        List<EntityModel<Veiculo>> veiculos = servico
                .listar()
                .stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(veiculos,

            linkTo(methodOn(ControleVeiculo.class)
                    .listar())
                    .withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Veiculo> buscarPorId(
            @PathVariable Long id) {

        Veiculo veiculo = servico.buscarPorId(id);

        return assembler.toModel(veiculo);
    }

    @PostMapping("/usuario/{usuarioId}")
    public EntityModel<Veiculo> cadastrar(
            @PathVariable Long usuarioId,
            @RequestBody VeiculoDTO dto) {

        Veiculo veiculo = servico.cadastrar(usuarioId, dto);

        return assembler.toModel(veiculo);
    }

    @PutMapping("/{id}")
    public EntityModel<Veiculo> atualizar(
            @PathVariable Long id,
            @RequestBody VeiculoDTO dto) {

        Veiculo veiculo = servico.atualizar(id, dto);

        return assembler.toModel(veiculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        servico.deletar(id);

        return ResponseEntity.noContent().build();
    }
}