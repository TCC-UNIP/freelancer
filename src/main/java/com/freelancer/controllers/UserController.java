package com.freelancer.controllers;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.freelancer.model.JobEntity;
import com.freelancer.model.UserEntity;
import com.freelancer.services.UserServices;

@Controller
@RequestMapping()
public class UserController {
	
	@Autowired
	UserServices userServ;
	
	//PAGINA INDEX DE BOAS VINDAS
	@RequestMapping(value="/")
	@ResponseBody
	public String index() {
		return "<center><H1 style='color:green; padding-top: 7%;'>Bem vindo a API</H1><p>Serviços para multiplataformas</br>Gestão de vagas e contratos</p></center>";
	}
	
	//SAVAR USUARIO
	@PutMapping(value="/user")
	@ResponseBody
	public void save(@RequestBody UserEntity user, HttpServletResponse response) {
		user.setPassword(userServ.encpritografarBcripty(user.getPassword()));
		userServ.save(user);
		response.setStatus(201);
	}
	
	//ATUALIZA USUARIO obs nesse metodo não é possivel alterar nick name, necessario implementar alterações para que o usuario possa passar o nickname novo e antigo.
	@PutMapping(value="/protected/user/update")
	@ResponseBody
	public void Update(@RequestBody UserEntity user, HttpServletResponse response, Authentication auth, HttpServletResponse reponse) {
		if (auth.getName().equals(user.getNome())) {
			user.setPassword(userServ.encpritografarBcripty(user.getPassword()));
			userServ.update(user);
			response.setStatus(200);
		}else {
			response.setStatus(403);
		}

	}
	
	
	//LISTAR TODOS USUARIOS PAGINADOS
	@GetMapping(value="admin/user/{page}/{nitens}")
	@ResponseBody
	public List<UserEntity> listar(@PathVariable("page")int page, @PathVariable("nitens")int nitens){
		Page<UserEntity> pageRequest = userServ.listAllUsers(PageRequest.of(page, nitens));
		 List<UserEntity> list = pageRequest.getContent();
		return list;
		
	}
	
	//BUSACAR USUARIO POR ID
	@RequestMapping(value="admin/user/buscar{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public  Optional<UserEntity> findbyid(@Param("id") Integer id) {	
		
		return userServ.findbyid(id) ;
		
	}
	
	//DELETAR USUARIO POR ID
	@DeleteMapping(value="admin/user{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public void deleteById(@Param("id") Integer id) {	
		
		userServ.delete(id);
		
	}
	
	//ENCONTRAR TODOS USUARIOS POR NOME 
	@GetMapping(value="admin/user/findname/{nome}/{page}/{nitens}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<UserEntity> findByNome(@PathVariable("nome") String nome, @PathVariable("page") int page, @PathVariable("nitens") int nitens){
		Page<UserEntity> userPage = userServ.findByNome(nome, PageRequest.of(page, nitens) );
		return userPage.getContent();
	}
	
	//encontrar os jobs em que um usuario esta se candidatando
	@RequestMapping(value="protected/user/candidatando{nickname}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<JobEntity> candidatando(@Param("nickname") String nickname, Authentication auth, HttpServletResponse response) {	
		
			UserEntity user = userServ.findByNickName(nickname);
			
			if ( auth.getName().equals(user.getNickname())) {
				
				return user.getCandidatoAsVagas();
			}else{
				response.setStatus(403);
				return 	null;
			}	
	}
}
