package com.ProjetoIntegrador.feeling.model;

import java.util.List;

import javax.persistence.CascadeType;

/*
    Responsavel pela criação da tabela Theme, com os parametros determinados pelo comando "size",
    e também foram adicionados os getters e setters.
*/

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_theme")
public class Theme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(min = 1, max = 200)
	private String topic;
	
	@NotNull
	@Size(min=5, max=1000)
	private String description;

	@OneToMany(mappedBy = "theme", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("theme")
	private List <Post> post;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
