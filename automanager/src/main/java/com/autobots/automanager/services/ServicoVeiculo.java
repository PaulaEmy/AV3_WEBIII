package com.autobots.automanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.VeiculoDTO;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.entitades.Veiculo;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import com.autobots.automanager.repositorios.RepositorioVeiculo;

@Service
public class ServicoVeiculo {

    @Autowired
    private RepositorioVeiculo repositorio;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public List<Veiculo> listar() {
        return repositorio.findAll();
    }

    public Veiculo buscarPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Veículo não encontrado"));
    }

    public Veiculo cadastrar(Long usuarioId, VeiculoDTO dto) {

        Usuario proprietario = repositorioUsuario.findById(usuarioId)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        Veiculo veiculo = new Veiculo();

        veiculo.setTipo(dto.getTipo());
        veiculo.setModelo(dto.getModelo());
        veiculo.setPlaca(dto.getPlaca());
        veiculo.setProprietario(proprietario);

        return repositorio.save(veiculo);
    }

    public Veiculo atualizar(Long id, VeiculoDTO dto) {

        Veiculo veiculo = buscarPorId(id);

        veiculo.setTipo(dto.getTipo());
        veiculo.setModelo(dto.getModelo());
        veiculo.setPlaca(dto.getPlaca());

        return repositorio.save(veiculo);
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }
}