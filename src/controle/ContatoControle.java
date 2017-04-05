/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ContatoBEAN;
import modelo.ContatoDAO;
import modelo.ContatoMySqlDAO;


public class ContatoControle {
    //private ContatoDAO cDAO = new ContatoDAO(); ---> ArrayList
    private ContatoMySqlDAO cDAO = new ContatoMySqlDAO();
   private int codigo = 0;
    
    public int atualizaCodigo(){
       codigo++;   
       return codigo;
    }
    
    public void cadastrar(ContatoBEAN c) {                
        c.setCod(this.atualizaCodigo());
        cDAO.cadastrar(c);
    }
    
    public ArrayList<ContatoBEAN> listarALL() {
        try {
            return cDAO.listarALL();
        } catch (SQLException ex) {
            Logger.getLogger(ContatoControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ContatoBEAN localizar(int codigo) {
        return cDAO.localizar(codigo);
    }    
        
    public boolean editar(ContatoBEAN c) {
        return cDAO.editar(c);        
    }
    
    public boolean remover(int codigo) {     
        return cDAO.remover(codigo);
    }

}
