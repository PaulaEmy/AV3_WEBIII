package com.autobots.automanager.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmpresaDTO {
    private String razaoSocial;
    private String nomeFantasia;
    private Date cadastro;
}