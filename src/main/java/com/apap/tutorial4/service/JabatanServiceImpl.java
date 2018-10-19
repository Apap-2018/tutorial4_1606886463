package com.apap.tutorial4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial4.model.JabatanModel;
import com.apap.tutorial4.repository.JabatanDB;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService {
	@Autowired
	private JabatanDB jabatanDb;

	@Override
	public List<JabatanModel> getAllJabatan() {
		return jabatanDb.findAll();
	}
	@Override
	public JabatanModel jabatanById(long id) {
		return jabatanDb.getOne(id);
	}
	@Override
	public void addJabatan(JabatanModel jabatan) {
		jabatanDb.save(jabatan);
	}
	@Override
	public void updateJabatan(JabatanModel jabatan) {
		JabatanModel newJabatan = jabatanDb.getOne(jabatan.getId());
		newJabatan.setNama(jabatan.getNama());
		newJabatan.setDeskripsi(jabatan.getDeskripsi());
		newJabatan.setGaji_pokok(jabatan.getGaji_pokok());
		
        jabatanDb.save(jabatan);
		
	}
	@Override
	public void deleteJabatanById(long id) {
		jabatanDb.deleteById(id);	
	}
}
