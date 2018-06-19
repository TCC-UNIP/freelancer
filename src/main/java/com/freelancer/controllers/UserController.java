package com.freelancer.controllers;
import java.util.List;
import java.util.Optional;
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
	public UserEntity save(@RequestBody UserEntity user) {
		user.setPassword(userServ.encpritografarBcripty(user.getPassword()));
		return userServ.save(user);
	}
	
	//ATUALIZA USUARIO
	@PutMapping(value="/protected/user/update")
	@ResponseBody
	public UserEntity Update(@RequestBody UserEntity user) {
		user.setPassword(userServ.encpritografarBcripty(user.getPassword()));
		return userServ.update(user);
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
	@GetMapping(value="protected/user/findname/{nome}/{page}/{nitens}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<UserEntity> findByNome(@PathVariable("nome") String nome, @PathVariable("page") int page, @PathVariable("nitens") int nitens){
		Page<UserEntity> userPage = userServ.findByNome(nome, PageRequest.of(page, nitens) );
		return userPage.getContent();
	}
	
	//encontrar os jobs em que um usuario esta se candidatando
	@RequestMapping(value="protected/user/candidatando{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<JobEntity> candidatando(@Param("id") Integer id) {	
		
			Optional<UserEntity> userOptioanl = userServ.findbyid(id);
			UserEntity user = userOptioanl.get();
			return user.getCandidatoAsVagas();
		
	}
}
