package com.apap.tutorial4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial4.model.InstansiModel;
import com.apap.tutorial4.repository.InstansiDB;

@Service
@Transactional
public class InstansiServiceImpl implements InstansiService {
	@Autowired
	private InstansiDB instansiDb;

	@Override
	public List<InstansiModel> getAllInstansi() {
		return instansiDb.findAll();
	}
	@Override
	public InstansiModel instansiById(long id) {
		return instansiDb.getOne(id);
	}
}
