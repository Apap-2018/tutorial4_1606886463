package com.apap.tutorial4.service;

import java.util.List;

import com.apap.tutorial4.model.InstansiModel;

public interface InstansiService {
	List<InstansiModel> getAllInstansi();
	InstansiModel instansiById(long id);
}
