package DAO;

import Entidades.Matricula;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MatriculaDAO {
  //cria paradinha parecido com a conexao do banco
    public EntityManager getEM(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("Creche_1PU");
       return factory.createEntityManager();
    }
    
    public Matricula salvar(Matricula matricula){
        
       EntityManager em = getEM();//Inicia Conexao
       
        try {
           em.getTransaction().begin(); //abre transação
           
           em.persist(matricula);   //executa o insert
           
           em.getTransaction().commit();    //executa transação
           
        } catch (Exception e) {
            
           em.getTransaction().rollback();  //caso ocorra erro executa rollback
           
        } finally{
            
           em.close();  //fecha conexao
        }
        
       return matricula;
    }  
}
