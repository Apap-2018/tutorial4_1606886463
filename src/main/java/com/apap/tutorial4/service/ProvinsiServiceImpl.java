package com.apap.tutorial4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial4.model.ProvinsiModel;
import com.apap.tutorial4.repository.ProvinsiDB;

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService {
	@Autowired
	private ProvinsiDB provinsiDb;

	@Override
	public List<ProvinsiModel> getAllProvinsi() {
		return provinsiDb.findAll();
	}
	@Override
	public ProvinsiModel provinsiById(long id) {
		return provinsiDb.getOne(id);
	}
}
