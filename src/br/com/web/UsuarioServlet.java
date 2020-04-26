package br.com.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dao.UsuarioDAO;
import br.com.model.Usuario;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
       this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	doGet(request, response);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch (action) {
		case "/novo":
			showNewForm(request, response);
			break;
			
		case "/inserir":
			try {
				insertUsuario(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case "/deletar":
			try {
				deleteUsuario(request, response);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case "/editar":
			try {
				showEditUsuario(request, response);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/alterar":
			try {
				updateUsuario(request, response);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			try {
				listUsuario(request, response);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("usuario-form.jsp");
		requestDispatcher.forward(request, response);
	}

	private void insertUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String nacionalidade = request.getParameter("nacionalidade");
		
		Usuario novoUsuario = new Usuario (nome, email, nacionalidade);
		usuarioDAO.inserirUsuario(novoUsuario);
		response.sendRedirect("list");
	}
	
	private void deleteUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		int id = Integer.parseInt(request.getParameter("id"));
		usuarioDAO.deletarUsuario(id);
		response.sendRedirect("list");
	}
	
	private void showEditUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		int id = Integer.parseInt(request.getParameter("id"));
		Usuario usuarioExistente = usuarioDAO.selectUsuarioByID(id);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("usuario-form.jsp");
		request.setAttribute("usuario", usuarioExistente);
		requestDispatcher.forward(request, response);
	}
	
	private void updateUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String nacionalidade = request.getParameter("nacionalidade");
		
		Usuario usuarioAlteracao = new Usuario(id, nome, email, nacionalidade);
		usuarioDAO.alterarUsuario(usuarioAlteracao);
		response.sendRedirect("list");
	}
	
	private void listUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		List<Usuario> listUsuarios = usuarioDAO.selectUsuarios();
		request.setAttribute("listUsuarios", listUsuarios);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("usuario-list.jsp");
		requestDispatcher.forward(request, response);
	}
	

}
