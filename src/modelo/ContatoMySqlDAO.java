/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContatoMySqlDAO {

    //armazena o obj de conexão com o BD mySql
    private Connection connection;
    //obj stmt que executa as consultas no BD
    private PreparedStatement stmt;

    public ContatoMySqlDAO() {
        //inicializa a conexão com o BD
        this.connection = ConnectionFactory.getConnection();
    }

    public void cadastrar(ContatoBEAN c) {
        String sql = "insert into contato (contnome, contendereco, conttelefone, contidade) values (?, ?, ?, ?);";

        try {
            //prepared statement para inserção
            stmt = connection.prepareStatement(sql);

            //seta os valores
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEndereco());
            stmt.setString(3, c.getTelefone());
            stmt.setInt(4, c.getIdade());

            //executa
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ContatoBEAN> listarALL() throws SQLException {
        String sql = "select * from contato;";
        ArrayList<ContatoBEAN> contatoAL = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(sql);

            //executa
            ResultSet rs = stmt.executeQuery();
            //joga resultado da consulta no arrayList
            while (rs.next()) {
                ContatoBEAN c = new ContatoBEAN();
                c.setCod(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setEndereco(rs.getString(3));
                c.setTelefone(rs.getString(4));
                c.setIdade(rs.getInt(5));
                //adiciona os dados no array
                contatoAL.add(c);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contatoAL;
    }

    public ContatoBEAN localizar(int codigo) {
         String sql = " select * from contato where contcod = ?";
        ContatoBEAN c = new ContatoBEAN();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, codigo);
            //executa
            ResultSet rs = stmt.executeQuery();
            //joga resultado da consulta no arrayList
            while (rs.next()) {
                c.setCod(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setEndereco(rs.getString(3));
                c.setTelefone(rs.getString(4));
                c.setIdade(rs.getInt(5));  
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    public boolean editar(ContatoBEAN c) {
        String sql = " update contato set contnome = ?, contendereco = ? , conttelefone = ?, contidade = ? where contcod = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEndereco());
            stmt.setString( 3, c.getTelefone());
            stmt.setInt(4, c.getIdade());
            stmt.setInt(5, c.getCod());
            int linhasAtualizadas = stmt.executeUpdate();
            stmt.close();
            if (linhasAtualizadas > 0) {
                System.out.println("Foram alterados " + linhasAtualizadas);
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean remover(int codigo) {
        String sql = " delete from contato where contcod = ?";

        try {
            //prepared statement para inserção
            stmt = connection.prepareStatement(sql);

            //seta os valores
            stmt.setInt(1, codigo);

            //executa
            stmt.execute();
            stmt.close();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
