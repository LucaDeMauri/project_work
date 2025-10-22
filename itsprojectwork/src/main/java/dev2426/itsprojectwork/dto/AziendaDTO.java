package dev2426.itsprojectwork.dto;

import java.util.List;

public class AziendaDTO {
	private Long id;
	private String nome;
	private List<AnnuncioDTO> annunci;
	
	public AziendaDTO() {}
	public AziendaDTO(Long id, String nome, List<AnnuncioDTO> annunci) {
		this.id = id;
		this.nome = nome;
		this.annunci = annunci;
	}
	
	public AziendaDTO(String nome, List<AnnuncioDTO> annunci) {
		super();
		this.nome = nome;
		this.annunci = annunci;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<AnnuncioDTO> getAnnunci() {
		return annunci;
	}
	public void setAnnunci(List<AnnuncioDTO> annunci) {
		this.annunci = annunci;
	}
	
	
}
