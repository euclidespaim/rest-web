package br.com.euclidespaim.rest;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.euclidespaim.dao.NotaDAO;
import br.com.euclidespaim.entity.Nota;

@Path("/notas") 
public class NotasService {
	
	private NotaDAO notaDAO;
	
	@PostConstruct
	private void init() {
		notaDAO = new NotaDAO();
	}
	
	@GET
	@Path("/list")
	@Produces (MediaType.APPLICATION_JSON)
	public List<Nota> listarNota() {
		List<Nota> lista = null;
		try {
			lista = notaDAO.listarNota();
	    } catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@POST
	@Path ("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addNota(Nota nota){
		String msg = "";
		
		System.out.println(nota.getNome());
		
		try {
			notaDAO.addNota(nota);
			
			msg = "Entrada inserida com sucesso!";
		}catch (Exception e){
			msg = "Atenção: Erro ao inserir dados!";
			e.printStackTrace();
		}
		
		return msg;
		}

	@GET
	@Path("/get/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Nota buscarNotaPorId(@PathParam("id") int idAnota){
		Nota nota = null;
		try {
			nota = NotaDAO.buscarNotaPorId(idAnota);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nota;
	}
}

