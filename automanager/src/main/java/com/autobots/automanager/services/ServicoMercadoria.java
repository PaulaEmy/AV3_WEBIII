package com.autobots.automanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.MercadoriaDTO;
import com.autobots.automanager.entitades.Mercadoria;
import com.autobots.automanager.repositorios.RepositorioMercadoria;

@Service
public class ServicoMercadoria {

    @Autowired
    private RepositorioMercadoria repositorio;

    public List<Mercadoria> listar() {
        return repositorio.findAll();
    }

    public Mercadoria buscarPorId(Long id) {

        return repositorio.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Mercadoria não encontrada"));
    }

    public Mercadoria cadastrar(MercadoriaDTO dto) {

        Mercadoria mercadoria = new Mercadoria();

        mercadoria.setValidade(dto.getValidade());
        mercadoria.setFabricacao(dto.getFabricacao());
        mercadoria.setCadastro(dto.getCadastro());

        mercadoria.setNome(dto.getNome());
        mercadoria.setQuantidade(dto.getQuantidade());
        mercadoria.setValor(dto.getValor());
        mercadoria.setDescricao(dto.getDescricao());

        return repositorio.save(mercadoria);
    }

    public Mercadoria atualizar(Long id, MercadoriaDTO dto) {

        Mercadoria mercadoria = buscarPorId(id);

        mercadoria.setValidade(dto.getValidade());
        mercadoria.setFabricacao(dto.getFabricacao());
        mercadoria.setCadastro(dto.getCadastro());

        mercadoria.setNome(dto.getNome());
        mercadoria.setQuantidade(dto.getQuantidade());
        mercadoria.setValor(dto.getValor());
        mercadoria.setDescricao(dto.getDescricao());

        return repositorio.save(mercadoria);
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }
}