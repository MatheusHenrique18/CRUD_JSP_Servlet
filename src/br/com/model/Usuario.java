package br.com.model;

public class Usuario {
	private Integer id;
	private String nome;
	private String email;
	private String nacionalidade;
	
	public Usuario(Integer id, String nome, String email, String nacionalidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.nacionalidade = nacionalidade;
	}
	
	public Usuario(String nome, String email, String nacionalidade) {
		super();
		this.nome = nome;
		this.email = email;
		this.nacionalidade = nacionalidade;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
}
