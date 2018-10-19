package com.apap.tutorial4.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tutorial4.model.PegawaiModel;

@Repository
public interface PegawaiDB extends JpaRepository<PegawaiModel, Long>{
PegawaiModel findByNip(String nip);

List<PegawaiModel> findByTahunMasukAndTanggalLahir(String tahunMasuk, Date tanggalLahir);

}
