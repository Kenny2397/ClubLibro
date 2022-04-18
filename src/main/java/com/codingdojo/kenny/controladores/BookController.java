package com.codingdojo.kenny.controladores;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.kenny.modelos.Book;
import com.codingdojo.kenny.modelos.User;
import com.codingdojo.kenny.servicios.AppService;

@Controller
@RequestMapping("/books")
public class BookController {
	@Autowired
	private AppService servicio;
	
	@GetMapping("/new")
	public String nuevo(@ModelAttribute(value="newBook") Book book, HttpSession session){
		/*REVISAMOS SESION*/
		User currentUser = (User)session.getAttribute("user_session");
		if(currentUser == null) {
			return "redirect:/";
		}
		return "new.jsp";
	}
	
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute(value="newBook") Book book,
							BindingResult result,Model model,HttpSession session) {
		/*REVISAMOS SESION*/
		User currentUser = (User)session.getAttribute("user_session");
		if(currentUser == null) {
			return "redirect:/";
		}
		/*
		 * verificamos errores
		 */
		servicio.createBook(book, result);
		if(result.hasErrors()) {
			System.out.println(result);
			/*
			 * SENDING A NEW INSTANCE OF LOGINUSER
			 */
			
			/*+++++++++++++++++++++++++++++
			//LA SIGUIENTE LINEA DE CODIGO NO ES NECESARIA YA QUE SI SE PONE CREA UNA NUEVA
			//INSTANCIA Y NO MUESTRA LOS ERRORES
			
			model.addAttribute("newBook", new Book());
			
			+++++++++++++++++++++++*/
			return "new.jsp";
		}
		
		
		return "redirect:/dashboard";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Long id,Model model, HttpSession session) {
		/*REVISAMOS SESION*/
		User currentUser = (User)session.getAttribute("user_session");
		if(currentUser == null) {
			return "redirect:/";
		}
		/*
		 * OBTENEMOS BOOK BY ID 
		 */
		Book book = servicio.getBookById(id);
		model.addAttribute("book", book);
		
		return "show.jsp";
		
	}
}
