package com.kynario.dev.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Ponto implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	private static final long serialVersionUID = 1L;
	private String hora_entrada;
	private String hora_saida;
	private String Data;
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usr;
	

}
