package com.codingdojo.kenny.controladores;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.kenny.modelos.Book;
import com.codingdojo.kenny.modelos.LoginUser;
import com.codingdojo.kenny.modelos.User;
import com.codingdojo.kenny.servicios.AppService;

@Controller
public class UserController {
	
	@Autowired
	private AppService servicio;
	
	@GetMapping("/")
	public String index(@ModelAttribute("nuevoUsuario") User nuevoUsuario,
						@ModelAttribute("nuevoLogin") LoginUser nuevoLogin) {
		
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("nuevoUsuario") User nuevoUsuario,
						   BindingResult result, Model model, HttpSession session) {
	
		servicio.register(nuevoUsuario, result);
		if(result.hasErrors()) {
			System.out.println(result);
			/*
			 * SENDING A NEW INSTANCE OF LOGINUSER
			 */
			model.addAttribute("nuevoLogin", new LoginUser());
			return "index.jsp";
		}
		/*
		 * INICIAMOS SESSION
		 */
		session.setAttribute("user_session", nuevoUsuario);
		return "redirect:/dashboard";
		
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("nuevoLogin") LoginUser nuevoLogin,
						BindingResult result, Model model, HttpSession session) {
		/*
		 * INSTANCIAMOS USER Y VERIFICAMOS ERRORES
		 */
		User user = servicio.login(nuevoLogin, result);
		if(result.hasErrors()) {
			/*
			 * SENDING A INSTANCE OF USER
			 */
			model.addAttribute("nuevoUsuario", new User());
			return "index.jsp";
		}
		/*
		 * INICIAMOS SESSION
		 */
		session.setAttribute("user_session", user);
		return "redirect:/dashboard";
							
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user_session");
		return "redirect:/";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(@ModelAttribute("user") User user,
							HttpSession session,
							Model model) {
		/*REVISAMOS SESION*/
		User currentUser = (User)session.getAttribute("user_session");
		if(currentUser == null) {
			return "redirect:/";
		}
		/*
		 * OBTENEMOS TODOS LOS LIBROS
		 */
		List<Book> books = servicio.getAllBooks();
		/*
		 * MANDAMOS UN MODEL AL JSP con model PODRIA SER TAMBIEN CON SESSION
		 */
		model.addAttribute("user",currentUser);
		model.addAttribute("books", books);
		return "dashboard.jsp";
	}
}