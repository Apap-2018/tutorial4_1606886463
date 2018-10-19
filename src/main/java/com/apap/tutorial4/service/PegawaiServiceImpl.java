package com.apap.tutorial4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial4.model.PegawaiModel;
import com.apap.tutorial4.repository.PegawaiDB;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
@Autowired
private PegawaiDB pegawaiDb;

@Override
public void addPegawai(PegawaiModel pegawai) {
	pegawai.setNip(this.nipGenerator(pegawai));
	System.out.println(pegawai.getNama());
	System.out.println(pegawai.getNip());
	System.out.println(pegawai.getTahun_masuk());
	System.out.println(pegawai.getTempat_lahir());
	System.out.println(pegawai.getTanggal_lahir().toString());
	System.out.println(pegawai.getInstansi().getNama());
	System.out.println(pegawai.getJabatan().get(0).getNama());
	pegawaiDb.save(pegawai);
}

public String nipGenerator (PegawaiModel pegawai) {
String generatedNip;
String kodeUrutanLahirMasuk;
String kodeTglLahir = pegawai.getTanggal_lahir().toString();
kodeTglLahir = kodeTglLahir.substring(8) + kodeTglLahir.substring(5, 7) + kodeTglLahir.substring(2, 4);	
	List<PegawaiModel> list = pegawaiDb.findByTahunMasukAndTanggalLahir(pegawai.getTahun_masuk(), pegawai.getTanggal_lahir());
	list.add(pegawai);
	kodeUrutanLahirMasuk = Integer.toString(list.size());
	if (Integer.parseInt(kodeUrutanLahirMasuk) < 10) {
		kodeUrutanLahirMasuk = "0" + kodeUrutanLahirMasuk;
	}
	generatedNip = Long.toString(pegawai.getInstansi().getId()) + kodeTglLahir + pegawai.getTahun_masuk() + kodeUrutanLahirMasuk;
	return generatedNip;
}

public List<PegawaiModel> getAllPegawai() {
	
	return pegawaiDb.findAll();
}



@Override
public void updatePegawai(PegawaiModel pegawaiModel) {
    PegawaiModel pegawai = pegawaiDb.getOne(pegawaiModel.getId());
    pegawai.setInstansi(pegawaiModel.getInstansi());
    pegawai.setNama(pegawaiModel.getNama());
    pegawai.setTahun_masuk(pegawaiModel.getTahun_masuk());
    pegawai.setTanggal_lahir(pegawaiModel.getTanggal_lahir());
    pegawai.setTempat_lahir(pegawaiModel.getTempat_lahir());
	pegawai.setNip(this.nipGenerator(pegawai));
	System.out.println("NIP BARU: "+this.nipGenerator(pegawai));
    pegawaiDb.save(pegawai);
}

@Override
public PegawaiModel pegawaiById(long id) {
	return pegawaiDb.getOne(id);
}

@Override
public PegawaiModel pegawaiByNip(String nip) {
	return pegawaiDb.findByNip(nip);
}

@Override
public void removePegawai(long id) {
	pegawaiDb.deleteById(id);
}


}
