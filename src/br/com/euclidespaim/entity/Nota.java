package br.com.euclidespaim.entity;

public class Nota {
	
	private int id;
	
	private String nome;
	
	private String laudo;
	
	private String arquivo;
	
	private String anota;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLaudo() {
		return laudo;
	}

	public void setLaudo(String laudo) {
		this.laudo = laudo;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getAnota() {
		return anota;
	}

	public void setAnota(String anota) {
		this.anota = anota;
	}
	
	}
