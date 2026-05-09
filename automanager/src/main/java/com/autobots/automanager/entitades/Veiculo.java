package com.autobots.automanager.entitades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.autobots.automanager.enumeracoes.TipoVeiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = { "proprietario", "vendas" })
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private TipoVeiculo tipo;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String placa;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario proprietario;

    @JsonIgnore
    @OneToMany(mappedBy = "veiculo",
	fetch = FetchType.EAGER,
	cascade = {
		CascadeType.PERSIST,
		CascadeType.MERGE,
		CascadeType.REFRESH
	})
    private Set<Venda> vendas = new HashSet<>();
}