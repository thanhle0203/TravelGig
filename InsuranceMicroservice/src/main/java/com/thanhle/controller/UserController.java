package com.thanhle.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.thanhle.domain.User;
import com.thanhle.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/login")
	public String login(@RequestParam(required = false) String logout,
			@RequestParam(required = false) String error, HttpServletRequest request, Model model) {
		if (error != null) {
			model.addAttribute("error", "Invalid credentials");
		}
		if (logout != null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, null, auth);
			}
			return "redirect:/login?logout";
		}
		return "login";
	}

	@GetMapping(value = "/accessDeniedPage")
	public String accessDenied(Principal principal, Model model) {
		String message = principal.getName() + ", Unauthorized access";
		model.addAttribute("message", message);
		return "accessDenied";
	}

	@GetMapping(value = "/signup")
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@PostMapping(value = "/signup")
	public RedirectView saveSignup(@ModelAttribute User user) {
		try {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(user.getUserPassword());
			user.setUserPassword(hashedPassword);
			userService.save(user);
			return new RedirectView("/login", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new RedirectView("/signup", true);
		}
	}

	@PostMapping(value = "/user/{username}")
	public String getUserByUsername(@PathVariable String username, Model model) {
		User user = userService.findByUserName(username);
		if (user != null) {
			model.addAttribute("email", user.getEmail());
		} else {
			model.addAttribute("email", "User not found");
		}
		return "userDetails";
	}
}
