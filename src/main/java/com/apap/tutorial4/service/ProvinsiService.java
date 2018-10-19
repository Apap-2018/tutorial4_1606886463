package com.apap.tutorial4.service;

import java.util.List;

import com.apap.tutorial4.model.ProvinsiModel;

public interface ProvinsiService {
	List<ProvinsiModel> getAllProvinsi();
	ProvinsiModel provinsiById(long id);
}

