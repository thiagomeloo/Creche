package DAO;

import Entidades.AlunoResponsavel;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Persistence;


public class AlunoResponsavelDAO {
    
    //cria paradinha parecido com a conexao do banco
    public EntityManager getEM(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("Creche_1PU");
       return factory.createEntityManager();
    }
    
    public AlunoResponsavel salvar(AlunoResponsavel alunoResponsavel){
        
       EntityManager em = getEM();//Inicia Conexao
       
        try {
           em.getTransaction().begin(); //abre transação
           
           em.persist(alunoResponsavel);   //executa o insert
           
           em.getTransaction().commit();    //executa transação
           
        } catch (Exception e) {
            
           em.getTransaction().rollback();  //caso ocorra erro executa rollback
           
        } finally{
            
           em.close();  //fecha conexao
        }
        
       return alunoResponsavel;
    }
    
    
    public Entidades.AlunoResponsavel consultaMae(long matAl){
       EntityManager em = getEM();
       Entidades.AlunoResponsavel mae = null;
        try {
            Query consulta = em.createNamedQuery("AlunoResponsavel.consultarMae");
            consulta.setParameter("mat", matAl);
            mae = (AlunoResponsavel) consulta.getSingleResult();
            
        } catch (Exception e) {
            
            mae = null;
        }
       
       return mae;

    }
    
    public Entidades.AlunoResponsavel consultaPai(long matAl){
       EntityManager em = getEM();
       Entidades.AlunoResponsavel mae = null;
        try {
            Query consulta = em.createNamedQuery("AlunoResponsavel.consultarPai");
            consulta.setParameter("mat", matAl);
            mae = (AlunoResponsavel) consulta.getSingleResult();
            
        } catch (Exception e) {
            
            mae = null;
        }
       
       return mae;

    }

    
}
