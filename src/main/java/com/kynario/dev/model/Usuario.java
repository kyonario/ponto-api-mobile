package com.kynario.dev.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	private static final long serialVersionUID = 1L;
	private String nome;
	private String username; //campo responsavel para abrigar o email
	private String password;
	private String matricula;
	private Integer qnt_horas_jornada;
	private Double saldo_horas;
    @OneToMany(mappedBy="usr", cascade=CascadeType.ALL)
    private Set<Ponto> pontos;
}
