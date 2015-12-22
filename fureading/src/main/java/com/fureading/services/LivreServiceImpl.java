package com.fureading.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fureading.persistence.dao.LivreDao;
import com.fureading.persistence.model.ELivre;


@Transactional
public class LivreServiceImpl implements LivreService {

	
	@Autowired
	LivreDao livreDao;
	
	@Override
	public Long addLivre(ELivre livre) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ELivre getLivreById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ELivre> getLivreList() throws Exception {
		// TODO Auto-generated method stub
		return livreDao.getAll("id");
	}

	@Override
	public void deleteLivre(ELivre livre) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
