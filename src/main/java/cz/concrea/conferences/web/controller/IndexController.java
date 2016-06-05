package cz.concrea.conferences.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.dao.repository.ConferenceRepository;

@Controller
@RequestMapping(value = "/confs/{conference}/")
@SessionAttributes("currentConf")
public class IndexController {
	
	@Autowired
	ConferenceRepository confRep;
	
	@RequestMapping("")
	public String index(@PathVariable(value="conference") String conferenceCode, Model model) {
		Conference currentConf = confRep.findByCodeName(conferenceCode);
		if(currentConf == null){
			return "notfound";
		}
		
		model.addAttribute("currentConf", currentConf);
		
		switch (currentConf.getConferenceType()) {
		
			case AIO: return "redirect:/confs/"+conferenceCode+"/aioform";
			case NORMAL: return "redirect:/confs/"+conferenceCode+"/login";
			
		}
		
		
		
		
		return "notfound";
	}
	
}
