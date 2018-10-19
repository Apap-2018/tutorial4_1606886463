package com.apap.tutorial4.service;

import java.util.List;

import com.apap.tutorial4.model.JabatanModel;
import com.apap.tutorial4.model.PegawaiModel;

public interface JabatanService {
	List<JabatanModel> getAllJabatan();
	JabatanModel jabatanById(long id);
	public void addJabatan(JabatanModel jabatan);
	public void updateJabatan(JabatanModel jabatan);
	void deleteJabatanById(long id);

}
