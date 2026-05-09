package com.autobots.automanager.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MercadoriaDTO {
    private Date validade;
	private Date fabricacao;
	private Date cadastro;
	private String nome;
	private long quantidade;
	private double valor;
	private String descricao;
}
