package com.autobots.automanager.controle;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.assembler.VendaModelAssembler;
import com.autobots.automanager.dto.VendaDTO;
import com.autobots.automanager.entitades.Venda;
import com.autobots.automanager.services.ServicoVenda;

@RestController
@RequestMapping("/vendas")
public class ControleVenda {

    @Autowired
    private ServicoVenda servico;

    @Autowired
    private VendaModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Venda>> listar() {

        List<EntityModel<Venda>> vendas = servico
                .listar()
                .stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(vendas,

            linkTo(methodOn(ControleVenda.class)
                    .listar())
                    .withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Venda> buscarPorId(
            @PathVariable Long id) {

        Venda venda = servico.buscarPorId(id);

        return assembler.toModel(venda);
    }

    @PostMapping
    public EntityModel<Venda> cadastrar(
            @RequestBody VendaDTO dto) {

        Venda venda = servico.cadastrar(dto);

        return assembler.toModel(venda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(
            @PathVariable Long id) {

        servico.deletar(id);

        return ResponseEntity.noContent().build();
    }
}