package com.apap.tutorial4.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tutorial4.model.InstansiModel;
import com.apap.tutorial4.model.JabatanModel;
import com.apap.tutorial4.model.PegawaiModel;
import com.apap.tutorial4.model.ProvinsiModel;
import com.apap.tutorial4.service.InstansiService;
import com.apap.tutorial4.service.JabatanService;
import com.apap.tutorial4.service.PegawaiService;
import com.apap.tutorial4.service.ProvinsiService;

@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	@Autowired
	private JabatanService jabatanService;
	@Autowired
	public InstansiService instansiService;
	@Autowired
	public ProvinsiService provinsiService;

	@RequestMapping("/")
	private String home(Model model) {
    	List<JabatanModel> jabatanList = jabatanService.getAllJabatan();
		model.addAttribute("jabatan", jabatanList);
		model.addAttribute("instansi", instansiService.getAllInstansi());
		return "home";
	}
	
	@RequestMapping(value = "/pegawai", method = RequestMethod.GET)
	private String viewPegawai(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.pegawaiByNip(nip);
		if (pegawai==null) {
			model.addAttribute("error", "NIP "+nip+" tidak ditemukan");
			return "error";
		}
		List<JabatanModel> jabatan = pegawai.getJabatan();
		Collections.sort(jabatan);
		Double gaji = 
			(jabatan.get(0).getGaji_pokok() + 
			(jabatan.get(0).getGaji_pokok() * 
			(pegawai.getInstansi().getProvinsi().getPresentase_tunjangan()/100)));
		
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("jabatan", jabatan);
		model.addAttribute("gaji", gaji);
		return "viewPegawai";
	}
	 @GetMapping("/pegawai/add")
	    public String addPegawai(Model model) {
		 PegawaiModel pegawai = new PegawaiModel();
		 JabatanModel jabatan = new JabatanModel();
		 List<JabatanModel> list =  new ArrayList<JabatanModel>();
			list.add(jabatan);
			pegawai.setJabatan(list);
			System.out.println(pegawai.getJabatan().size());
	        model.addAttribute("pegawai", pegawai);
	        model.addAttribute("jabatanList", jabatanService.getAllJabatan());
	        model.addAttribute("instansi", instansiService.getAllInstansi());
	        model.addAttribute("provinsi", provinsiService.getAllProvinsi());
			


	        return "addPegawai";
	    }

	    @PostMapping(value = "/pegawai/add", params= {"addRow"})
	    public String addRowJabatan(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, Model model) {
	    	if (pegawai.getJabatan()== null) {
				pegawai.setJabatan(new ArrayList<JabatanModel>());
			}
	    	pegawai.getJabatan().add(new JabatanModel());
			 model.addAttribute("pegawai", pegawai);
			 System.out.println(pegawai.getJabatan().size());
		        model.addAttribute("jabatanList", jabatanService.getAllJabatan());
		     model.addAttribute("instansi", instansiService.getAllInstansi());
		        model.addAttribute("provinsi", provinsiService.getAllProvinsi());
			return "addPegawai";
	    }
	    
	    @PostMapping(value = "/pegawai/add", params= {"pegawaiSubmit"})
	    public String submitAddPegawai(@ModelAttribute PegawaiModel pegawai,Model model) {
			pegawaiService.addPegawai(pegawai);
			String message = "Pegawai bernama "+pegawai.getNama()+" dengan NIP: "+pegawai.getNip();
	        model.addAttribute("message", message);
	        return "add";
	    }
	    
	    @PostMapping(value="/pegawai/add",params= {"deleteRow"})
		private String deleteRowJabatan(@ModelAttribute PegawaiModel pegawai,BindingResult bindingResult, Model model, HttpServletRequest req) {
			final Integer rowId = Integer.valueOf(req.getParameter("deleteRow"));
		    pegawai.getJabatan().remove(rowId.intValue());
			 model.addAttribute("pegawai", pegawai);
			  model.addAttribute("jabatanList", jabatanService.getAllJabatan());
			     model.addAttribute("instansi", instansiService.getAllInstansi());
			        model.addAttribute("provinsi", provinsiService.getAllProvinsi());
			return "addPegawai";
		}
	    @RequestMapping("/provinsi/get")
		public @ResponseBody List<ProvinsiModel> getProvinsi(Model model) {
	    	List<ProvinsiModel> provinsi = provinsiService.getAllProvinsi();
			return provinsi;
		}
	    @RequestMapping(value= "/instansi/get", method = RequestMethod.GET)
		public @ResponseBody List<InstansiModel> getInstansi(@RequestParam("provinsi") long id, Model model) {
	    	List<InstansiModel> allInstansi = instansiService.getAllInstansi();
	    	List<InstansiModel> instansiProvinsi = new ArrayList<InstansiModel>();
	    	for (InstansiModel i: allInstansi) {
	    		if (i.getProvinsi().getId()==id) {
	    			instansiProvinsi.add(i);
	    			System.out.println(i.getNama());
	    		}
	    	}
	    	System.out.println("ada objek sejumlah: "+instansiProvinsi.size());
	    	
			return instansiProvinsi;
		}
	    
	    @RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
		private String ubahPegawaiView(@RequestParam("nip") String nip, Model model) {
			PegawaiModel pegawai = pegawaiService.pegawaiByNip(nip);
			System.out.println("ID OBJEK PEGAWAI: "+pegawai.getId());
			List<JabatanModel> jabatan = pegawai.getJabatan();
			Collections.sort(jabatan);
	        model.addAttribute("jabatanList", jabatanService.getAllJabatan());
		     model.addAttribute("instansi", instansiService.getAllInstansi());
		        model.addAttribute("provinsi", provinsiService.getAllProvinsi());
		        model.addAttribute("provinsiSelected", pegawai.getInstansi().getProvinsi());
		        model.addAttribute("instansiSelected", pegawai.getInstansi());
			model.addAttribute("pegawai", pegawai);
			return "ubahPegawai";
		}
	    
	    @PostMapping(value = "/pegawai/ubah", params= {"newRowUpdate"})
		private String newRowUpdate(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, Model model) {
			if (pegawai.getJabatan()== null) {
				pegawai.setJabatan(new ArrayList<JabatanModel>());
			}
	    	pegawai.getJabatan().add(new JabatanModel());
			 model.addAttribute("pegawai", pegawai);
	        model.addAttribute("jabatanList", jabatanService.getAllJabatan());
		     model.addAttribute("instansi", instansiService.getAllInstansi());
		        model.addAttribute("provinsi", provinsiService.getAllProvinsi());
		        model.addAttribute("provinsiSelected", pegawai.getInstansi().getProvinsi());
		        model.addAttribute("instansiSelected", pegawai.getInstansi());
			return "ubahPegawai";
		}
	    
	    @PostMapping(value="/pegawai/ubah",params= {"delRowUpdate"})
		private String delRowUpdate(@ModelAttribute PegawaiModel pegawai,BindingResult bindingResult, Model model, HttpServletRequest req) {
			final Integer rowId = Integer.valueOf(req.getParameter("delRowUpdate"));
		    pegawai.getJabatan().remove(rowId.intValue());
		    model.addAttribute("pegawai", pegawai);
	        model.addAttribute("jabatanList", jabatanService.getAllJabatan());
		     model.addAttribute("instansi", instansiService.getAllInstansi());
		        model.addAttribute("provinsi", provinsiService.getAllProvinsi());
		        model.addAttribute("provinsiSelected", pegawai.getInstansi().getProvinsi());
		        model.addAttribute("instansiSelected", pegawai.getInstansi());
			return "ubahPegawai";
		}
	   
	    @PostMapping(value="/pegawai/ubah",params= {"submitUpdate"})
	    public String submitUbahPegawai(@ModelAttribute PegawaiModel pegawai,Model model) {
			pegawaiService.updatePegawai(pegawai);
			pegawai=pegawaiService.pegawaiById(pegawai.getId());
			String message = "Pegawai bernama "+pegawai.getNama()+" dengan NIP: "+pegawai.getNip();
	        model.addAttribute("message", message);
	        return "add";
	    }
	    public boolean adaJabatan(String id,PegawaiModel singlePegawai) {
	    	for (JabatanModel jabatan: singlePegawai.getJabatan()) {
	    		if (jabatan.getId()==Integer.parseInt(id)) {
	    			return true;
	    		}
	    	}
	    	return false;
	    }
//	    fitur 4 insyallah fix sabi amin ya allah
	    @GetMapping(value="/pegawai/cari")
	    public String filterPegawai(@RequestParam("idProvinsi") Optional<String> idProvinsi, 
	    		@RequestParam("idInstansi") Optional<String> idInstansi, @RequestParam("idJabatan") Optional<String> idJabatan, Model model) {
	    	List<PegawaiModel> hasilFilter = new ArrayList<PegawaiModel>();
	    	if(idProvinsi.isPresent()&&idInstansi.isPresent()&&idJabatan.isPresent()) {
	    		for (PegawaiModel pegawai: pegawaiService.getAllPegawai()) {
	    			
		    		if (((!idProvinsi.get().equals("null"))? 
		    				(Integer.parseInt(idProvinsi.get())==pegawai.getInstansi().getProvinsi().getId()): true )
		    				
		    				& ( (!idInstansi.get().equals("null"))? 
		    				(Integer.parseInt(idInstansi.get())==pegawai.getInstansi().getId()) : true )
		    				
		    				& ((!idJabatan.get().equals("null"))? 
		    				(this.adaJabatan(idJabatan.get(), pegawai)): true ))
		    		
		    		{
		    			hasilFilter.add(pegawai);
		    		}
		    	}
		    	model.addAttribute("queries",hasilFilter);
	    	}
	    	else {
		    	model.addAttribute("queries",pegawaiService.getAllPegawai());
	    	}
	    	model.addAttribute("provinsi", provinsiService.getAllProvinsi());
	    	model.addAttribute("jabatan", jabatanService.getAllJabatan());
	    	return "filterPegawai";
	    }
	    
	    @RequestMapping(value = "/pegawai/termuda-tertua", method = RequestMethod.GET)
		private String getPilotTertuaTermuda(@RequestParam("idInstansi") long idInstansi, Model model) {
			PegawaiModel tertua = pegawaiService.getPegawaiTertua(idInstansi);
			PegawaiModel termuda = pegawaiService.getPegawaiTermuda(idInstansi);
			List<JabatanModel> jabatanTertua = tertua.getJabatan();
			Collections.sort(jabatanTertua);
			Double gajiTertua = 
				(jabatanTertua.get(0).getGaji_pokok() + 
				(jabatanTertua.get(0).getGaji_pokok() * 
				(tertua.getInstansi().getProvinsi().getPresentase_tunjangan()/100)));
			List<JabatanModel> jabatanTermuda= termuda.getJabatan();
			Collections.sort(jabatanTermuda);
			Double gajiTermuda = 
				(jabatanTermuda.get(0).getGaji_pokok() + 
				(jabatanTermuda.get(0).getGaji_pokok() * 
				(termuda.getInstansi().getProvinsi().getPresentase_tunjangan()/100)));
			
			model.addAttribute("tertua", tertua);
			model.addAttribute("gajiTertua", gajiTertua);
			model.addAttribute("termuda", termuda);
			model.addAttribute("gajiTermuda", gajiTermuda);
			return "tertuaTermuda";
		}
	    

	
}