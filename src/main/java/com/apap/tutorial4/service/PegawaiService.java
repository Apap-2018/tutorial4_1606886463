package com.apap.tutorial4.service;

import java.util.List;

import com.apap.tutorial4.model.PegawaiModel;

public interface PegawaiService {
	void addPegawai(PegawaiModel pegawai);
	void updatePegawai(PegawaiModel pegawai);
	PegawaiModel pegawaiById(long id);
	PegawaiModel pegawaiByNip(String nip);
	void removePegawai(long id);
	List<PegawaiModel> getAllPegawai();
	public PegawaiModel getPegawaiTertua(long idInstansi);
	public PegawaiModel getPegawaiTermuda(long idInstansi);
}
