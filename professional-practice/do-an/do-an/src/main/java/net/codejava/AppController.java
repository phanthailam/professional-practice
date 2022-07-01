package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/trasua/log0.png")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/home")
	public String viewHomePage2(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = null;
		if (principal instanceof UserDetails) {
			user = ((CustomUserDetails)principal).getUser();
		} else {
			user = null;
		}

		if (user != null) { // có đăng nhập
			model.addAttribute("isLogin", true);
			model.addAttribute("user", user);
		} else {
			model.addAttribute("isLogin", false);
		}

		return "index.html";
	}

	@GetMapping("/listProduct")
	public String viewMenu() {return "listProduct" ;}


	@GetMapping("/product")
	public String viewNhuongQuyen() {return "product" ;}

	@GetMapping("/contact")
	public String viewlienhe() {
		return "contact";
	}


	@GetMapping("/signup_form")
	public String dangnhap() {
		return "login";
	}

	@GetMapping("/register_success")
	public String dangki() {return "register";}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		
		return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
}
