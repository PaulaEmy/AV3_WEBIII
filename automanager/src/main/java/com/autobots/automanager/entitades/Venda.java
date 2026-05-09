package com.autobots.automanager.entitades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@EqualsAndHashCode(exclude = {"cliente", "funcionario", "veiculo"})
@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date cadastro;

    @Column(nullable = false, unique = true)
    private String identificacao;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario cliente;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario funcionario;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Mercadoria> mercadorias = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Servico> servicos = new HashSet<>();

	@JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Veiculo veiculo;
}