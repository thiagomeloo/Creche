package DAO;

import Entidades.Professor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

public class ProfessorDAO {
    //cria paradinha parecido com a conexao do banco
    public EntityManager getEM(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("Creche_1PU");
       return factory.createEntityManager();
    }
    
    public Professor salvar(Professor professor){
        
       EntityManager em = getEM();//Inicia Conexao
       
        try {
           em.getTransaction().begin(); //abre transação
           
           em.persist(professor);   //executa o insert
           
           em.getTransaction().commit();    //executa transação
           
        } catch (Exception e) {
            
           em.getTransaction().rollback();  //caso ocorra erro executa rollback
           
        } finally{
            
           em.close();  //fecha conexao
        }
        
       return professor;
    }

    public Professor atualizar(Professor professor){
        
       EntityManager em = getEM();//Inicia Conexao
       
        try {
           em.getTransaction().begin(); //abre transação
           
           em.merge(professor);   //executa o insert
           
           em.getTransaction().commit();    //executa transação
           
        } catch (Exception e) {
            
           em.getTransaction().rollback();  //caso ocorra erro executa rollback
           
        } finally{
            
           em.close();  //fecha conexao
        }
        
       return professor;
    }
    
    public List<Entidades.Professor> consultarTodosAtivo(){
       EntityManager em = getEM();
       List<Entidades.Professor> professores;
        try {
            Query q = em.createNamedQuery("Professor.consultarTodosAtivo");
            professores = q.getResultList();
        } catch (Exception e) {
            professores = new ArrayList();
        }finally{
            em.close();
        }      
       return professores;
       
    }
 
    public List<Entidades.Professor> consultarTodosInativo(){
       EntityManager em = getEM();
       List<Entidades.Professor> professores;
        try {
            Query q = em.createNamedQuery("Professor.consultarTodosInativo");
            professores = q.getResultList();
        } catch (Exception e) {
            professores = new ArrayList();
        }finally{
            em.close();
        }      
       return professores;
       
    }

    public List<Entidades.Professor> FiltrarTodosAtivosPeloNome(String str){
       EntityManager em = getEM();
       List<Entidades.Professor> professores;
        try {
            Query q = em.createNamedQuery("Professor.filtrarAtivoComLikePorNome");
            q.setParameter("desc", str+"%");
            professores = q.getResultList();
        } catch (Exception e) {
            professores = new ArrayList();
        }finally{
            em.close();
        }      
       return professores;
       
    }
    
    public List<Entidades.Professor> FiltrarTodosAtivosPeloRg(String str){
       EntityManager em = getEM();
       List<Entidades.Professor> professores;
        try {
            Query q = em.createNamedQuery("Professor.filtrarAtivoComLikePorRg");
            q.setParameter("desc", str+"%");
            professores = q.getResultList();
        } catch (Exception e) {
            professores = new ArrayList();
        }finally{
            em.close();
        }      
       return professores;
       
    }
    
    public List<Entidades.Professor> FiltrarTodosInativosPeloNome(String str){
       EntityManager em = getEM();
       List<Entidades.Professor> professores;
        try {
            Query q = em.createNamedQuery("Professor.filtrarInativoComLikePorNome");
            q.setParameter("desc", str+"%");
            professores = q.getResultList();
        } catch (Exception e) {
            professores = new ArrayList();
        }finally{
            em.close();
        }      
       return professores;
       
    }
    
    public List<Entidades.Professor> FiltrarTodosInativosPeloRg(String str){
       EntityManager em = getEM();
       List<Entidades.Professor> professores;
        try {
            Query q = em.createNamedQuery("Professor.filtrarInativoComLikePorRg");
            q.setParameter("desc", str+"%");
            professores = q.getResultList();
        } catch (Exception e) {
            professores = new ArrayList();
        }finally{
            em.close();
        }      
       return professores;
       
    }
    
    public Entidades.Professor RetornarProfessorPeloID(long id){
        EntityManager em = getEM();
        Entidades.Professor prof = new Entidades.Professor();
        try {
            Query q = em.createQuery("SELECT p FROM Professor p WHERE p.idProfessor = :id");
            q.setParameter("id", id);
            prof = (Professor) q.getSingleResult();
            
        } catch (Exception e) {
           
            
        }finally{
            em.close();
        }      
       return prof;    
        
    }

}
