package com.apap.tutorial4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tutorial4.model.JabatanModel;
import com.apap.tutorial4.model.PegawaiModel;

@Repository
public interface JabatanDB extends JpaRepository<JabatanModel, Long>{
}

