package com.autobots.automanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.UsuarioDTO;
import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.repositorios.RepositorioEmpresa;
import com.autobots.automanager.repositorios.RepositorioUsuario;

import java.util.List;

@Service
public class ServicoUsuario {

    @Autowired
    private RepositorioUsuario repositorio;

    @Autowired
    private RepositorioEmpresa repositorioEmpresa;

    public List<Usuario> listarUsuarios() {
        return repositorio.findAll();
    }

    public Usuario buscarPorId(Long id) {

        return repositorio.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Usuário não encontrado"));
    }

    public Usuario cadastrarUsuario(UsuarioDTO dto) {

        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());
        usuario.setNomeSocial(dto.getNomeSocial());
        usuario.setEndereco(dto.getEndereco());

        Empresa empresa = repositorioEmpresa
                .findById(dto.getEmpresaId())
                .orElseThrow(() ->
                        new RuntimeException("Empresa não encontrada"));

        usuario.setEmpresa(empresa);

        return repositorio.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, UsuarioDTO dto) {

        Usuario usuario = buscarPorId(id);

        usuario.setNomeSocial(dto.getNomeSocial());
        usuario.setEndereco(dto.getEndereco());
        
        Empresa empresa = repositorioEmpresa
        .findById(dto.getEmpresaId())
        .orElseThrow(() ->
                new RuntimeException("Empresa não encontrada"));

usuario.setEmpresa(empresa);

        return repositorio.save(usuario);
    }

    public void deletarUsuario(Long id) {
        repositorio.deleteById(id);
    }
}
