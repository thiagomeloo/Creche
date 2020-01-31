package Entidades;

import javax.persistence.*;


@Entity
@NamedQueries({
    @NamedQuery(name="Professor.consultarTodosAtivo",
                query ="SELECT p FROM Professor p WHERE p.estadoAtual=true ORDER BY p.nome"),
    
    @NamedQuery(name="Professor.consultarTodosInativo",
                query ="SELECT p FROM Professor p WHERE p.estadoAtual=false ORDER BY p.nome"),
    
    @NamedQuery(name="Professor.filtrarAtivoComLikePorNome",
                query ="SELECT p FROM Professor p WHERE p.estadoAtual=true and p.nome like :desc ORDER BY p.nome"),
    
    @NamedQuery(name="Professor.filtrarAtivoComLikePorRg",
                query ="SELECT p FROM Professor p WHERE p.estadoAtual=true and p.rg like :desc ORDER BY p.nome"),
    
     @NamedQuery(name="Professor.filtrarInativoComLikePorNome",
                query ="SELECT p FROM Professor p WHERE p.estadoAtual=false and p.nome like :desc ORDER BY p.nome"),
    
    @NamedQuery(name="Professor.filtrarInativoComLikePorRg",
                query ="SELECT p FROM Professor p WHERE p.estadoAtual=false and p.rg like :desc ORDER BY p.nome"),
    
}) 

public class Professor {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long idProfessor;
  private String nome;
  @Column(nullable = false, unique = true)
  private String rg; 
  private String sexo; 
  private String tel1; 
  private String tel2;
  private boolean estadoAtual;

    public long getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(long idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public boolean isEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(boolean estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    @Override
    public String toString() {
        return nome;
    }
       

}
