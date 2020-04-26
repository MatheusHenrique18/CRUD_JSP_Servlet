package br.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.RowFilter;

import br.com.model.Usuario;

public class UsuarioDAO {

	private String jdbcURL = "jdbc:postgresql://localhost:5432/crud_jsp";
	private String jdbcUsername = "postgres";
	private String jdbcPassword = "admin";
	
	private static final String INSERIR_USUARIO_SQL = "INSERT INTO public.usuario (nome, email, nacionalidade) VALUES (?, ?, ?);";
	
	private static final String SELECIONAR_TODOS_USUARIOS =	"SELECT * FROM public.usuario order by id asc;";
	
	private static final String SELECIONAR_USUARIO_POR_ID =	"SELECT * FROM public.usuario WHERE id = ?;";
	
	private static final String EXCLUIR_USUARIO = "DELETE FROM public.usuario WHERE id = ?;";
	
	private static final String ALTERAR_USUARIO = "UPDATE public.usuario SET nome=?, email=?, nacionalidade=? WHERE ID = ?;";
	
	protected Connection getConnection() {
		String driver = "org.postgresql.Driver";
		Connection connection = null;

		try {
			Class.forName(driver);
			connection = (Connection) DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			System.out.println("Conexão realizada com sucesso.");

		} catch (ClassNotFoundException ex) {
			System.err.print(ex.getMessage());
		} catch (SQLException e) {
			System.err.print(e.getMessage());
		}

		return connection;
	}
	
	//INSERINDO USUÁRIO
	public void inserirUsuario(Usuario usuario) throws SQLException{
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_USUARIO_SQL)){
			System.out.println(preparedStatement);
			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getNacionalidade());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//ALTERANDO USUÁRIO
	public boolean alterarUsuario(Usuario usuario) throws SQLException{
		boolean linhaEditada;
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ALTERAR_USUARIO)){
			System.out.println(preparedStatement);
			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getNacionalidade());
			preparedStatement.setInt(4, usuario.getId());
			
			linhaEditada = preparedStatement.executeUpdate() > 0;
		}//posso colocar o catch aqui posteriormente
		return linhaEditada;
	}
	
	//SELECIONAR USUÁRIO POR ID
	public Usuario selectUsuarioByID(int id) {
		Usuario usuario = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECIONAR_USUARIO_POR_ID);){
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String nacionalidade = rs.getString("nacionalidade");
				usuario = new Usuario(id, nome, email, nacionalidade);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	//SELECIONAR USUÁRIOS
	public List<Usuario> selectUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECIONAR_TODOS_USUARIOS);){
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String nacionalidade = rs.getString("nacionalidade");
				usuarios.add(new Usuario(id, nome, email, nacionalidade));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	//EXCLUIR USUÁRIO
	public boolean deletarUsuario(int id) throws SQLException{
		boolean linhaDeletada;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(EXCLUIR_USUARIO);){
			System.out.println(preparedStatement);
			preparedStatement.setInt(1, id);
			linhaDeletada = preparedStatement.executeUpdate() > 0;
		}//posso colocar o catch aqui posteriormente
		return linhaDeletada;
	}
	
}
