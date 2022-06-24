package com.example.hotel.controller;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hotel.entity.Reservation;

import com.example.hotel.entity.Employee;
import com.example.hotel.repository.EmployeeRep;
import com.example.hotel.repository.ReservationRep;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HotelReservationController {
	private EmployeeRep employeeRepository;
	private ReservationRep reservationRepository;

	@GetMapping(path="/index")

	public String employee(Model model,
							@RequestParam(name="page" , defaultValue="0") int page, 
							@RequestParam(name="size" , defaultValue="5")int size,
							@RequestParam(name="keyword",defaultValue="") String keyword)
{
		Page<Employee> employee=employeeRepository.findByUsernameContains(keyword,PageRequest.of(page, size));
		model.addAttribute("employee",employee.getContent());
		model.addAttribute("pages",new int[employee.getTotalPages()]);
		model.addAttribute("currentpage",page);
		model.addAttribute("keyword",keyword);
		return "employee";
	}
	@GetMapping("/your-reservations")
	public String reservationsList(Model model,String username,
			@RequestParam(name="page" , defaultValue="0") int page, 
			@RequestParam(name="size" , defaultValue="5")int size) {
		
		List<Reservation> reservation=reservationRepository.findAll();

		model.addAttribute("resList", reservation);

		return "your-reservations";
	}
	@GetMapping("/delete")
	
	public String delete (Long id,String keyword,int page) {
		employeeRepository.deleteById(id);
		return "redirect:/index?page="+page+"&keyword="+keyword;
	}
@GetMapping("/deleteR")
	
	public String deleteReservation (Long id) {
		reservationRepository.deleteById(id);
		return "redirect:/your-reservations";
	}

@GetMapping(value="/login")
public String login() {
	return "login";
}
@GetMapping(value="/HomePage")
public String HomePage() {
	return "HomePage";
}

@GetMapping("/")
public String home() {
	
	return "redirect:/HomePage";
}


   @GetMapping("/formEmployee")
   public String formEmployee( Model model) {
	   model.addAttribute("employee",new Employee());
	   return "formEmployee";
   }
   @PostMapping(path="/save")
   public String save(Model model ,@Valid Employee employee ,BindingResult bindingResult,
		   @RequestParam(defaultValue="0")int page,
		   @RequestParam(defaultValue="")String keyword) {
	   if(bindingResult.hasErrors()) return "formEmployee";
	   employeeRepository.save(employee);
	   return "redirect:/index?page="+page+"&keyword="+keyword;
   }
   
   @GetMapping(value="/editEmployee")
   public String editEmployee( Model model,Long id,String keyword,int page) {
	   Employee employee=employeeRepository.findById(id).orElse(null);
	   if(employee==null) throw new RuntimeException("Employee introuvable");
	   model.addAttribute("employee",employee);
		model.addAttribute("currentpage",page);
		model.addAttribute("keyword",keyword);
	   return "editEmployee";
   }
   
   @PostMapping(path="/saveReservation")
   public String saveReservation(Model model ,Reservation  reservation ,BindingResult bindingResult,
		   @RequestParam(defaultValue="0")int page,
		   @RequestParam(defaultValue="")String keyword) {
	   if(bindingResult.hasErrors()) return "Reservation";
	   reservationRepository.save(reservation);
	   return "HomePage";
   }
   @GetMapping("/Reservation")
   public String formReservation( Model model) {
	   model.addAttribute("reservation",new Reservation());
	   return "Reservation";
   }
  
   
   @RequestMapping(value="/logout", method = RequestMethod.GET)
   public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       if (auth != null){    
           new SecurityContextLogoutHandler().logout(request, response, auth);
       }
       return "redirect:/login";  }
  

}
