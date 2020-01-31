package DAO;

import Entidades.Turma;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TurmaDAO {
    //cria paradinha parecido com a conexao do banco
    public EntityManager getEM(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("Creche_1PU");
       return factory.createEntityManager();
    }
    
    public Turma salvar(Turma turma){
        
       EntityManager em = getEM();//Inicia Conexao
       
        try {
           em.getTransaction().begin(); //abre transação
           
           em.merge(turma);   //executa o insert
           
           em.getTransaction().commit();    //executa transação
           
        } catch (Exception e) {
            
           em.getTransaction().rollback();  //caso ocorra erro executa rollback
           
        } finally{
            
           em.close();  //fecha conexao
        }
        
       return turma;
    }
    
    public void Deletar(Turma turma){
        EntityManager em = getEM();
        em.getTransaction().begin();
        Turma current = null;
        if (!em.contains(turma)) {
          current = em.merge(turma);
        }
        em.remove(current);
        em.getTransaction().commit();
        em.close();

    }
    

    
    public List<Entidades.Turma> consultarTodsAsTurmas(){
       EntityManager em = getEM();
       List<Entidades.Turma> turma;
        try {
            Query q = em.createNamedQuery("Turma.consultarTodasAsTurmas");
            turma = q.getResultList();
        } catch (Exception e) {
            turma = new ArrayList();
        }finally{
            em.close();
        }      
       return turma;
       
    }
}
