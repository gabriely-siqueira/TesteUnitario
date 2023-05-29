package repository;

import java.util.ArrayList;
import java.util.List;

import enums.EnumSexo;
import models.Pessoa;

public class BancoDados {
	
	private List<Pessoa> dados = new ArrayList<Pessoa>();
	
	public Pessoa add (Pessoa p) {
		p.setId(dados.size()+1);
		dados.add(p);
		return p;
	}
	
	public Pessoa findById (final Integer id) {
		return dados.stream()
				.filter(p -> id.equals(p.getId()))
				.findAny()
				.orElse(null);
	}
	
	public Pessoa update (final Integer id, Pessoa p) {
		Pessoa cad = findById(id);
		if(cad != null) {
			dados.remove(cad);
			p.setId(cad.getId());
			dados.add(p);
			return p;
		}
		return null;
	}
	
	public void delete (final Integer id) {
		Pessoa cad = findById(id);
		if(cad != null) {
			dados.remove(cad);
		}
	}
	
	public List<Pessoa> listAll () {
		return dados;
	}
	
	public List<Pessoa> findByIdadeBetween (Integer idadeInicial, Integer idadeFinal) {
		return dados.stream()
				.filter(p -> p.getIdade() >= idadeInicial) 
				.filter(p -> p.getIdade() <= idadeFinal) 
				.toList();
	}
	
	public List<Pessoa> findBySexo (final EnumSexo sexo) {
		return dados.stream()
				.filter(p -> p.getSexo().equals(sexo)) 
				.toList();
	}
	
	public void clearData() {
		dados.clear();
	}}