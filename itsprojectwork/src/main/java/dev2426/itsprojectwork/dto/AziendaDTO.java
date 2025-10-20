package dev2426.itsprojectwork.dto;

import java.util.List;

public class AziendaDTO {
	private String nome;
	private List<AnnuncioDTO> annunci;
	
	public AziendaDTO() {}
	
	public AziendaDTO(String nome, List<AnnuncioDTO> annunci) {
		super();
		this.nome = nome;
		this.annunci = annunci;
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
