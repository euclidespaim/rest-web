package br.com.euclidespaim.rest;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
	
	/*@GET
	@Path("/get/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Nota buscarNotaPorId(@PathParam("id") int idNota){
		Nota nota = null;
		try {
			Nota = NotaDAO.buscarNotaPorId(idNota);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Nota;*/
	
}

