package com.autobots.automanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.EmpresaDTO;
import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.repositorios.RepositorioEmpresa;
import com.autobots.automanager.repositorios.RepositorioUsuario;

@Service
public class ServicoEmpresa {

    @Autowired
    private RepositorioEmpresa repositorio;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public List<Empresa> listarEmpresas() {
        return repositorio.findAll();
    }

    public Empresa buscarPorId(Long id) {

        return repositorio.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Empresa não encontrada"));
    }

    public Empresa cadastrarEmpresa(EmpresaDTO dto) {

        Empresa empresa = new Empresa();

        empresa.setRazaoSocial(dto.getRazaoSocial());
        empresa.setNomeFantasia(dto.getNomeFantasia());
        empresa.setCadastro(dto.getCadastro());

        return repositorio.save(empresa);
    }

    public Empresa atualizarEmpresa(Long id, EmpresaDTO dto) {

        Empresa empresa = buscarPorId(id);

        empresa.setRazaoSocial(dto.getRazaoSocial());
        empresa.setNomeFantasia(dto.getNomeFantasia());

        return repositorio.save(empresa);
    }

    public void deletarEmpresa(Long id) {
        repositorio.deleteById(id);
    }

    public Empresa adicionarUsuario(
        Long empresaId,
        Long usuarioId) {

        Empresa empresa = repositorio
                .findById(empresaId)
                .orElseThrow(() ->
                        new RuntimeException("Empresa não encontrada"));

        Usuario usuario = repositorioUsuario
                .findById(usuarioId)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        empresa.getUsuarios().add(usuario);

        return repositorio.save(empresa);
    }
}