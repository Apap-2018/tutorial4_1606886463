package com.apap.tutorial4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial4.model.JabatanModel;
import com.apap.tutorial4.service.JabatanService;
import com.apap.tutorial4.service.PegawaiService;

@Controller
public class JabatanController {
	@Autowired
	private JabatanService jabatanService;
	
	@Autowired
	private PegawaiService pegawaiService;
	
	@RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
	private String viewJabatanDetail(@RequestParam("idJabatan") long id,Model model) {
    	JabatanModel jabatan = jabatanService.jabatanById(id);
		model.addAttribute("jabatan", jabatan);
		return "viewJabatan";
	}
	
	@RequestMapping(value = "/jabatan/viewall")
	private String viewJabatanDetail(Model model) {
		List<JabatanModel> jabatanList = jabatanService.getAllJabatan();
		model.addAttribute("jabatan", jabatanList);
		return "viewAllJabatan";
	}
	
	@GetMapping(value = "/jabatan/tambah")
	private String tambahJabatanGet(Model model) {
		 JabatanModel jabatan = new JabatanModel();
		 model.addAttribute("jabatan", jabatan);
		return "addJabatan";
	}

	@PostMapping(value = "/jabatan/tambah")
	private String tambahJabatanPost(@ModelAttribute JabatanModel jabatan,Model model) {
		jabatanService.addJabatan(jabatan);
		String message = "Jabatan  "+jabatan.getNama();
        model.addAttribute("message", message);
		return "add";
	}

//	@GetMapping(value = "/jabatan/ubah")
//	private String ubahJabatanGet(@RequestParam("idJabatan") long id,Model model) {
//		List<JabatanModel> jabatanList = jabatanService.getAllJabatan();
//		model.addAttribute("jabatan", jabatanList);
//		return "viewAllJabatan";
//	}

//	@PostMapping(value = "/jabatan/ubah")
//	private String ubahJabatanPost(Model model) {
//		List<JabatanModel> jabatanList = jabatanService.getAllJabatan();
//		model.addAttribute("jabatan", jabatanList);
//		return "viewAllJabatan";
//	}
	
	@GetMapping(value = "/jabatan/hapus")
	private String hapusJabatanGet(@RequestParam("idJabatan") long id, Model model) {
		String "add";
	}


}
