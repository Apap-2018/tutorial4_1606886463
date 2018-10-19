package com.apap.tutorial4.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tutorial4.model.JabatanModel;
import com.apap.tutorial4.model.PegawaiModel;
import com.apap.tutorial4.service.JabatanService;
import com.apap.tutorial4.service.PegawaiService;

@Controller
public class JabatanController {
	@Autowired
	private JabatanService jabatanService;
	
	@Autowired
	private PegawaiService pegawaiService;
	
	@RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
	private String viewJabatanDetail(@RequestParam("id_jabatan") long id,Model model) {
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

	@GetMapping(value = "/jabatan/ubah")
	private String ubahJabatanGet(@RequestParam("id_jabatan") long idJabatan,Model model) {
		 JabatanModel jabatan = jabatanService.jabatanById(idJabatan);
		 model.addAttribute("jabatan",jabatan);
		return "ubahJabatan";
	}

	@PostMapping(value = "/jabatan/ubah")
	private String ubahJabatanPost(@ModelAttribute JabatanModel jabatan,Model model) {
		jabatanService.updateJabatan(jabatan);
		String message = "Update Jabatan  "+jabatan.getNama();
        model.addAttribute("message", message);
		return "add";
	}

	
	@PostMapping(value = "/jabatan/hapus")
	private String hapusJabatanGet(@ModelAttribute JabatanModel jabatan, Model model) {
		 String message = "Penghapusan Jabatan  "+jabatan.getNama();
		jabatanService.deleteJabatanById(jabatan.getId());
	     model.addAttribute("message", message);
		return "add";
	}
	
	@GetMapping(value = "/jabatan/hapus/check")
	public @ResponseBody Map<String, String> checkJabatan(@RequestParam("id_jabatan") long id) {
	    HashMap<String, String> map = new HashMap<>();
	    String canDelete = "true";
	    for (PegawaiModel pegawai : pegawaiService.getAllPegawai()) {
	    	for (JabatanModel jabatan:pegawai.getJabatan()) {
	    		if (jabatan.getId()== id) {
	    			canDelete="false";
	    		}
	    	}
	    }
	    map.put("canDelete", canDelete);
	    return map;
	}
	
	


}
