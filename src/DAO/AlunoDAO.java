package DAO;

import Entidades.Aluno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AlunoDAO {
    
    //cria paradinha parecido com a conexao do banco
    public EntityManager getEM(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("Creche_1PU");
       return factory.createEntityManager();
    }
    
    public Aluno salvar(Aluno aluno){
        
       EntityManager em = getEM();//Inicia Conexao
       
        try {
           em.getTransaction().begin(); //abre transação
           
           em.persist(aluno);   //executa o insert
           
           em.getTransaction().commit();    //executa transação
           
        } catch (Exception e) {
            
           em.getTransaction().rollback();  //caso ocorra erro executa rollback
           
        } finally{
            
           em.close();  //fecha conexao
        }
        
       return aluno;
    }
    
    public Aluno atualizar(Aluno aluno){
        
       EntityManager em = getEM();//Inicia Conexao
       
        try {
           em.getTransaction().begin(); //abre transação
           
           em.merge(aluno);   //executa o insert
           
           em.getTransaction().commit();    //executa transação
           
        } catch (Exception e) {
            
           em.getTransaction().rollback();  //caso ocorra erro executa rollback
           
        } finally{
            
           em.close();  //fecha conexao
        }
        
       return aluno;
    }
       
    public List<Entidades.Aluno> consultarTodosAlunosComEdereco(){
       EntityManager em = getEM();
       List<Entidades.Aluno> alunos;
        try {
            Query q = em.createNamedQuery("Aluno.consultarTodosComEndereco");
            alunos = q.getResultList();
        } catch (Exception e) {
            alunos = new ArrayList();
        }finally{
            em.close();
        }      
       return alunos;
       
    }
    
    public List<Entidades.Aluno> consultarTodosAlunosComEderecoSemTurma(){
       EntityManager em = getEM();
       List<Entidades.Aluno> alunos;
        try {
            Query q = em.createNamedQuery("Aluno.consultarTodosComEnderecoEsemTurma");
            alunos = q.getResultList();
        } catch (Exception e) {
            alunos = new ArrayList();
        }finally{
            em.close();
        }      
       return alunos;
       
    }
    
    public List<Entidades.Aluno> consultaAlunosPorNomeComLike(String str){
       EntityManager em = getEM();
       List<Entidades.Aluno> alunos;
        try {
            Query consulta = em.createNamedQuery("Aluno.consultarTodosComEnderecoPorLike"); 
            consulta.setParameter("desc", str+"%");
            alunos = consulta.getResultList();
        } catch (Exception e) {
            
            alunos = new ArrayList();
           
        }finally{
            em.close();
        }      
       return alunos;
       
    }
    
    public List<Entidades.Aluno> consultaAlunosPorMatComLike(String str){
       EntityManager em = getEM();
       List<Entidades.Aluno> alunos;
        try {
            
            Query consulta = em.createQuery("SELECT a FROM Aluno a WHERE a.matricula like '"+str+"%' ORDER BY a.nome"); 
            
            alunos = consulta.getResultList();
        } catch (Exception e) {
            System.out.println("ero");
            alunos = new ArrayList();
           
        }finally{
            em.close();
        }      
       return alunos;
       
    }
    
    public List<Entidades.Aluno> consultaAlunosDeUmaTurma(long id){
       EntityManager em = getEM();
       List<Entidades.Aluno> alunos;
        try {
            Query consulta = em.createNamedQuery("Aluno.consultarTodosAlunosDeUmaTurma");
            consulta.setParameter("id", id);
            alunos = consulta.getResultList();
        } catch (Exception e) {
            
            alunos = new ArrayList();
           
        }finally{
            em.close();
        }      
       return alunos;
       
    }
    
    public Long contarAlunosDeUmaTurma(long id){
       EntityManager em = getEM();
       Long qtd = null;
        try {
            Query consulta = em.createQuery("SELECT COUNT(a) FROM Aluno a WHERE a.Turma_idTurma.idTurma = :id ");
            consulta.setParameter("id", id);
            qtd = (Long) consulta.getSingleResult();
        } catch (Exception e) {

        }finally{
            em.close();
        }      
       return qtd;
       
    }
    
    public Entidades.Aluno consultaAlunoPorMatricula(long id){
       EntityManager em = getEM();
        Entidades.Aluno aluno = new Entidades.Aluno();
        try {
            Query consulta = em.createQuery("SELECT a FROM Aluno a WHERE a.matricula = :id "); 
            consulta.setParameter("id", id);
            aluno = (Aluno) consulta.getSingleResult();
        } catch (Exception e) {
 
        }finally{
            em.close();
        }      
       return aluno;
       
    }
    
    public Entidades.Aluno consultaAlunoPorCpf(String cpf){
       EntityManager em = getEM();
        Entidades.Aluno aluno = new Entidades.Aluno();
        try {
            Query consulta = em.createQuery("SELECT a FROM Aluno a WHERE a.cpf = :cpf "); 
            consulta.setParameter("cpf", cpf);
            aluno = (Aluno) consulta.getSingleResult();
        } catch (Exception e) {
 
        }finally{
            em.close();
        }      
       return aluno;
       
    }
    
    
}
