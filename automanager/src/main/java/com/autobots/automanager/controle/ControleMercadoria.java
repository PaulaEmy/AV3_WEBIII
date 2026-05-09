package com.autobots.automanager.controle;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.assembler.MercadoriaModelAssembler;
import com.autobots.automanager.dto.MercadoriaDTO;
import com.autobots.automanager.entitades.Mercadoria;
import com.autobots.automanager.services.ServicoMercadoria;

@RestController
@RequestMapping("/mercadorias")
public class ControleMercadoria {

    @Autowired
    private ServicoMercadoria servico;

    @Autowired
    private MercadoriaModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Mercadoria>> listar() {

        List<EntityModel<Mercadoria>> mercadorias = servico
                .listar()
                .stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(mercadorias,

            linkTo(methodOn(ControleMercadoria.class)
                    .listar())
                    .withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Mercadoria> buscarPorId(
            @PathVariable Long id) {

        Mercadoria mercadoria = servico.buscarPorId(id);

        return assembler.toModel(mercadoria);
    }

    @PostMapping
    public EntityModel<Mercadoria> cadastrar(
            @RequestBody MercadoriaDTO dto) {

        Mercadoria mercadoria = servico.cadastrar(dto);

        return assembler.toModel(mercadoria);
    }

    @PutMapping("/{id}")
    public EntityModel<Mercadoria> atualizar(
            @PathVariable Long id,
            @RequestBody MercadoriaDTO dto) {

        Mercadoria mercadoria = servico.atualizar(id, dto);

        return assembler.toModel(mercadoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(
            @PathVariable Long id) {

        servico.deletar(id);

        return ResponseEntity.noContent().build();
    }
}