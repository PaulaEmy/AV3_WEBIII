package com.autobots.automanager.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.VendaDTO;
import com.autobots.automanager.entitades.*;
import com.autobots.automanager.repositorios.*;

@Service
public class ServicoVenda {

    @Autowired
    private RepositorioVenda repositorio;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioVeiculo repositorioVeiculo;

    @Autowired
    private RepositorioMercadoria repositorioMercadoria;

    @Autowired
    private RepositorioServico repositorioServico;

    public List<Venda> listar() {
        return repositorio.findAll();
    }

    public Venda buscarPorId(Long id) {

        return repositorio.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Venda não encontrada"));
    }

    public Venda cadastrar(VendaDTO dto) {

        Usuario cliente = repositorioUsuario.findById(dto.getClienteId())
                .orElseThrow(() ->
                        new RuntimeException("Cliente não encontrado"));

        Usuario funcionario = repositorioUsuario.findById(dto.getFuncionarioId())
                .orElseThrow(() ->
                        new RuntimeException("Funcionário não encontrado"));

        Veiculo veiculo = repositorioVeiculo.findById(dto.getVeiculoId())
                .orElseThrow(() ->
                        new RuntimeException("Veículo não encontrado"));

        Set<Mercadoria> mercadorias = new HashSet<>(
                repositorioMercadoria.findAllById(dto.getMercadoriasIds()));

        Set<Servico> servicos = new HashSet<>(
                repositorioServico.findAllById(dto.getServicosIds()));

        Venda venda = new Venda();

        venda.setCadastro(dto.getCadastro());
        venda.setIdentificacao(dto.getIdentificacao());

        venda.setCliente(cliente);
        venda.setFuncionario(funcionario);
        venda.setVeiculo(veiculo);

        venda.setMercadorias(mercadorias);
        venda.setServicos(servicos);

        return repositorio.save(venda);
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }
}