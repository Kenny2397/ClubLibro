package com.codingdojo.kenny.servicios;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.kenny.modelos.Book;
import com.codingdojo.kenny.modelos.LoginUser;
import com.codingdojo.kenny.modelos.User;
import com.codingdojo.kenny.repositorios.BookRepository;
import com.codingdojo.kenny.repositorios.UserRepository;

@Service
public class AppService {
	
	@Autowired
	private UserRepository repositorio_user;
	
	@Autowired
	private BookRepository repositorio_book;
	
	
	/*
	 * USER
	 */
	public User register(User nuevoUsuario, BindingResult result) {
		
		String nuevoEmail = nuevoUsuario.getEmail();
		
		//Revisamos si existe el correo electrónico en BD
		if(repositorio_user.findByEmail(nuevoEmail).isPresent()) {
			result.rejectValue("email", "Unique", "El correo fue ingresado previamente.");
		}
		
		if(! nuevoUsuario.getPassword().equals(nuevoUsuario.getConfirm()) ) {
			result.rejectValue("confirm", "Matches", "Las contraseñas no coiniciden");
		}
		
		if(result.hasErrors()) {
			return null;
		} else {
			//Encriptamos contraseña
			String contra_encr = BCrypt.hashpw(nuevoUsuario.getPassword(), BCrypt.gensalt());
			nuevoUsuario.setPassword(contra_encr);
			//Guardo usuario
			return repositorio_user.save(nuevoUsuario);
		}
		
	}

	public User login(LoginUser nuevoLogin, BindingResult result) {
		
		if(result.hasErrors()) {
			return null;
		}
		
		//Buscamos por correo
		Optional<User> posibleUsuario = repositorio_user.findByEmail(nuevoLogin.getEmail());
		if(!posibleUsuario.isPresent()) {
			result.rejectValue("email", "Unique", "Correo ingresado no existe");
			return null;
		}
		
		User user_login = posibleUsuario.get();
		
		//Comparamos contraseñas encriptadas
		if(! BCrypt.checkpw(nuevoLogin.getPassword(), user_login.getPassword()) ) {
			result.rejectValue("password", "Matches", "Contraseña inválida");
		}
		
		if(result.hasErrors()) {
			return null;
		} else {
			return user_login; 
		}
		
	}
	/*Guarda usuario en BD*/
	public User save_user(User updatedUser) {
		return repositorio_user.save(updatedUser);
	}
	
	/*Me regresa usuario en base a su id*/
	public User find_user(Long id) {
        Optional<User> optionalUser = repositorio_user.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }
	
	/*
	 * BOOK
	 */
	
	public List<Book> getAllBooks(){
		if(repositorio_book.findAll().isEmpty()) {
			return null;
		}
		return repositorio_book.findAll();
	}
	
	public Book createBook(Book book,BindingResult result) {
		if(result.hasErrors()) {
			return null;
		}else {
			return repositorio_book.save(book);
		}
		
	}
	public Book getBookById(Long id) {
		Optional<Book> libro = repositorio_book.findById(id);
		
		if(!libro.isPresent()) {
			return null;
		}
		
		Book book = libro.get();
		
		return book;
		
		
		
	}
	
}