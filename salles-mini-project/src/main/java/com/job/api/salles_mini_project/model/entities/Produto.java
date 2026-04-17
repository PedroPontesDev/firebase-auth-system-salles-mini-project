package com.job.api.salles_mini_project.model.entities;

import com.job.api.salles_mini_project.model.entities.enums.TipoUsuario;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer quantidade;
	
	private Double preco;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vendedor.id")
	Usuario usarioVendedor;
	
	TipoUsuario tipoUsuario;
	
}
