package DAO;

import Entidades.Responsavel;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ResponsavelDAO {
    //cria paradinha parecido com a conexao do banco
    public EntityManager getEM(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("Creche_1PU");
       return factory.createEntityManager();
    }
    
    public Responsavel salvar(Responsavel responsavel){
        
       EntityManager em = getEM();//Inicia Conexao
       
        try {
           em.getTransaction().begin(); //abre transação
           
           em.merge(responsavel);   //executa o insert
           
           em.getTransaction().commit();    //executa transação
           
        } catch (Exception e) {
            
           em.getTransaction().rollback();  //caso ocorra erro executa rollback
           
        } finally{
            
           em.close();  //fecha conexao
        }
        
       return responsavel;
    }
    
    public Responsavel PegarIdResponsavelComRG(String rg){
       EntityManager em = getEM();
       Responsavel res = new Responsavel();
        try {
            Query consulta = em.createNamedQuery("Responsavel.consultarResponsavelPeloRg");
            consulta.setParameter("rg", rg);
            res = (Responsavel) consulta.getSingleResult();
        } catch (Exception e) {

        }finally{
            em.close();
        }      
       return res;
       
    }
    
}
