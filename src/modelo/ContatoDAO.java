/*
*  CRUD DE CONTATO
 */
package modelo;

import java.util.ArrayList;


public class ContatoDAO {

    private ArrayList<ContatoBEAN> arrayContato = new ArrayList<ContatoBEAN>();

    public void cadastrar(ContatoBEAN c) {
        arrayContato.add(c);
    }
    
    public ArrayList<ContatoBEAN> listarALL() {
        return arrayContato;
    }

    public ContatoBEAN localizar(int codigo) {
        for (ContatoBEAN contatoBEAN : arrayContato) {
            if (contatoBEAN.getCod() == codigo) {
                return contatoBEAN;
            }
        }
        return null;
    }
    
    public int localizarIndex(int codigo) {
        int index = 0;
        for (ContatoBEAN contatoBEAN : arrayContato) {            
            if (contatoBEAN.getCod() == codigo) {         
                return index;
            }
            index++;
        }
        return -1;
    }
    
    public boolean editar(ContatoBEAN c) {
        boolean modificou = false;
        int index = this.localizarIndex(c.getCod());        
        if (index  != -1) {            
            arrayContato.set(index,c);         
            modificou = true;
        } 
         
        return modificou;
    }
    
    public boolean remover(int codigo) {
        boolean removeu = false;
        int index = this.localizarIndex(codigo);        
        if (index  != -1) {            
            arrayContato.remove(index);
            removeu = true;
        } 
        return removeu;
    }

}
