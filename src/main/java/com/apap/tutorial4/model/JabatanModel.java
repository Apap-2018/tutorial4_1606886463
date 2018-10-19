package com.apap.tutorial4.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="jabatan")
public class JabatanModel implements Serializable, Comparable<JabatanModel>{
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public Double getGaji_pokok() {
		return gaji_pokok;
	}

	public void setGaji_pokok(Double gaji_pokok) {
		this.gaji_pokok = gaji_pokok;
	}
	
	@Override
	public int compareTo(JabatanModel jabatan) {
		int compare = Double.compare(jabatan.getGaji_pokok(), gaji_pokok);
		return compare;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;
	
	@NotNull
    @Size(max = 255)
    @Column(name = "deskripsi", nullable = false)
    private String deskripsi;
	
	@NotNull
    @Column(name = "gaji_pokok", nullable = false)
    private Double gaji_pokok;

}
