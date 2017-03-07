package br.com.euclidespaim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.euclidespaim.config.BDConfig;
import br.com.euclidespaim.entity.Nota;

public class NotaDAO {
	
	public List<Nota> listarNota() throws Exception {
		List<Nota> lista = new ArrayList<>();
		
		Connection conexao = BDConfig.getConnection();
		
		String sql = "SELECT * FROM TB_DCM";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		
		while (rs.next() ) {
			Nota nota = new Nota();
			nota.setId(rs.getInt("ID"));
			nota.setNome(rs.getString("NOME"));
			nota.setLaudo(rs.getString("LAUDO"));
			nota.setArquivo(rs.getString("ARQUIVO"));
			nota.setAnota(rs.getString("ANOTA"));
			
			
			lista.add(nota);
		}
		
		return lista;
	}
	
	public static Nota buscarNotaPorId(int idPacs) throws Exception {
		Nota nota = null;
		
		Connection conexao = BDConfig.getConnection();
		
		String sql = "SELECT * FROM TB_DCM WHERE ID = ?";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, idPacs);
		ResultSet rs = statement.executeQuery();
		
		if (rs.next()) {
			nota = new Nota();
			nota.setId(rs.getInt("ID"));
			nota.setNome(rs.getString("NOME"));
			nota.setLaudo(rs.getString("LAUDO"));
			nota.setArquivo(rs.getString("ARQUIVO"));
			nota.setAnota(rs.getString("NOTA"));
		}
		
		return nota;		
		
	}
	
	public void addNota(Nota nota) throws Exception {
		Connection conexao = BDConfig.getConnection();
		
		String sql = "INSERT INTO TB_DCM(NOME, LAUDO, ARQUIVO, ANOTA) VALUES(?, ?, ?, ?)";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, nota.getNome());
		statement.setString(2, nota.getLaudo());
		statement.setString(3, nota.getArquivo());
		statement.setString(4, nota.getAnota());
		statement.execute();
	}
	
	public void updateNota(Nota nota) throws Exception {
		Connection conexao = BDConfig.getConnection();
		
		String sql = "UPDATE TB_DCM SET NOME = ?, LAUDO = ? WHERE ID = ?";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, nota.getNome());
		statement.setString(2, nota.getLaudo());
		statement.setInt(3, nota.getId());
		statement.execute();
	}
	
	
	public void removerNota(int idNota) throws Exception {
		Connection conexao = BDConfig.getConnection();
		
		String sql = "DELETE FROM TB_DCM WHERE ID = ?";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, idNota);
		statement.execute();
	}
}
