package com.autobots.automanager.controle;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.dto.EmpresaDTO;
import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.assembler.EmpresaModelAssembler;
import com.autobots.automanager.services.ServicoEmpresa;;

@RestController
@RequestMapping("/empresas")
public class ControleEmpresa {

    @Autowired
    private ServicoEmpresa servico;

    @Autowired
    private EmpresaModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Empresa>> listarEmpresas() {

        List<EntityModel<Empresa>> empresas = servico
                .listarEmpresas()
                .stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(empresas,

            linkTo(methodOn(ControleEmpresa.class)
                    .listarEmpresas())
                    .withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Empresa> buscarEmpresa(
            @PathVariable Long id) {

        Empresa empresa = servico.buscarPorId(id);

        return assembler.toModel(empresa);
    }

    @PostMapping
    public EntityModel<Empresa> cadastrarEmpresa(
            @RequestBody EmpresaDTO dto) {

        Empresa empresa = servico.cadastrarEmpresa(dto);

        return assembler.toModel(empresa);
    }

    @PutMapping("/{id}")
    public EntityModel<Empresa> atualizarEmpresa(
            @PathVariable Long id,
            @RequestBody EmpresaDTO dto) {

        Empresa empresa = servico.atualizarEmpresa(id, dto);

        return assembler.toModel(empresa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEmpresa(
            @PathVariable Long id) {

        servico.deletarEmpresa(id);

        return ResponseEntity.noContent().build();
    }
}