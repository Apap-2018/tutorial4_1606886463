package com.apap.tutorial4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tutorial4.model.JabatanPegawaiModel;

@Repository
public interface JabatanPegawaiDB extends JpaRepository<JabatanPegawaiModel, Long>{
}
