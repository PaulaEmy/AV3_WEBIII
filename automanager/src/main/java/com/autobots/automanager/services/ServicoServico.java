package com.autobots.automanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.ServicoDTO;
import com.autobots.automanager.entitades.Servico;
import com.autobots.automanager.repositorios.RepositorioServico;

@Service
public class ServicoServico {

    @Autowired
    private RepositorioServico repositorio;

    public List<Servico> listar() {
        return repositorio.findAll();
    }

    public Servico buscarPorId(Long id) {

        return repositorio.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Serviço não encontrado"));
    }

    public Servico cadastrar(ServicoDTO dto) {

        Servico servico = new Servico();

        servico.setNome(dto.getNome());
        servico.setValor(dto.getValor());
        servico.setDescricao(dto.getDescricao());

        return repositorio.save(servico);
    }

    public Servico atualizar(Long id, ServicoDTO dto) {

        Servico servico = buscarPorId(id);

        servico.setNome(dto.getNome());
        servico.setValor(dto.getValor());
        servico.setDescricao(dto.getDescricao());

        return repositorio.save(servico);
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }
}