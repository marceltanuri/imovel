package com.tanuri.imovel.dominio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Produto {

	@Id
	@GeneratedValue
	private Long id;

	private String nome;

	@Embedded
	private Localizacao localizacao = new Localizacao();

	@Enumerated(EnumType.STRING)
	private Estagio estagio;

	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	public Estagio getEstagio() {
		return estagio;
	}

	public Estagio[] getEstagios() {
		return Estagio.values();
	}

	public Tipo[] getTipos() {
		return Tipo.values();
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

	private Long metragem;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<ComponenteDoProduto> componentes;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Foto> fotos = new ArrayList<Foto>();

	private Integer fotoPrincipal = 0;

	public Integer getFotoPrincipal() {
		return fotoPrincipal;
	}

	public void setFotoPrincipal(Integer fotoPrincial) {
		this.fotoPrincipal = fotoPrincial;
	}

	@Transient
	private MultipartFile[] uploads;

	public MultipartFile[] getUploads() {
		return uploads;
	}

	public void setUploads(MultipartFile[] uploads) throws IOException {
		this.uploads = uploads;
		for (MultipartFile multipartFile : uploads) {
			if (multipartFile == null || multipartFile.isEmpty()) {
				continue;
			}
			this.fotos.add(new Foto(multipartFile));
		}
	}

	@Lob
	private String visaoGeral;

	private Double valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVisaoGeral() {
		return visaoGeral;
	}

	public void setVisaoGeral(String visaoGeral) {
		this.visaoGeral = visaoGeral;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Long getMetragem() {
		return metragem;
	}

	public void setMetragem(Long metragem) {
		this.metragem = metragem;
	}

	public List<ComponenteDoProduto> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<ComponenteDoProduto> componentes) {
		this.componentes = componentes;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public enum Estagio {

		NA_PLANTA("Na planta"), PRONTO("Pronto"), EM_OBRAS("Em obras"), LANCAMENTO("Lançamento"), PRE_LANCAMENTO(
				"Pré-lançamento"), FUTURO_LANCAMENTO("Futuro lançamento");

		private String descricao;

		public String getDescricao() {
			return descricao;
		}

		private Estagio(String descricao) {
			this.descricao = descricao;
		}

	}

	public enum Tipo {

		APARTAMENTO("Apartamentos"), CASA("Casas"), SALA_COMERCIAL("Salas comerciais"), FLAT("Flats"), LAJE_CORPORATIVA(
				"Lajes corporativas"), LOTEAMENTO("Loteamentos");

		private String descricao;

		public String getDescricao() {
			return descricao;
		}

		private Tipo(String descricao) {
			this.descricao = descricao;
		}
	}
}