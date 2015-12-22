package com.fureading.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.fureading.persistence.model.ELivre;

@Transactional
public interface LivreService {
	
	public Long addLivre(ELivre livre) throws Exception;
	public ELivre getLivreById(long id) throws Exception;
	public List<ELivre> getLivreList() throws Exception;
	public void deleteLivre(ELivre livre) throws Exception;

}
