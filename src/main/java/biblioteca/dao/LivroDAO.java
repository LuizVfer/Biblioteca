package main.java.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.biblioteca.modelo.Livro;
import main.java.biblioteca.util.ConexaoBD;

public class LivroDAO {

    public void cadastrar(Livro livro) {
        String query = "INSERT INTO livro (titulo, autor, ISBN, anoPublicacao, disponibilidade) VALUES (?,?,?,?,?)";

        try {
            Connection conn = ConexaoBD.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getISBN());
            stmt.setInt(4, livro.getAnoPublicacao());
            stmt.setBoolean(5, livro.isDisponibilidade());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Livro consultarLivro(int id) {
        String query = "SELECT * FROM livro WHERE id = ?";
        Livro livro = null;

         try {
            Connection conn = ConexaoBD.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setISBN(rs.getString("ISBN"));
                livro.setAnoPublicacao(rs.getInt("anoPublicacao"));
                livro.setDisponibilidade(rs.getBoolean("disponibilade"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livro;
    }

    public void atualizarLivro(Livro livro) {
        String query = "UPDATE livro SET titulo = ?, autor = ?, ISBN = ?, anoPublicacao = ?, disponibilidade = ? WHERE id = ?";

        try {
            Connection conn = ConexaoBD.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getISBN());
            stmt.setInt(4, livro.getAnoPublicacao());
            stmt.setBoolean(5, livro.isDisponibilidade());
            stmt.setInt(6, livro.getId());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerLivro(int id) {
        String query = "DELETE FROM livro WHERE id = ?";

        try {
            Connection conn = ConexaoBD.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
    

