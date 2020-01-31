package Entidades;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Matricula {
    
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long idMatricula ;
  private Date data_Matricula ;
  @ManyToOne
  @JoinColumn
  private Aluno Aluno_Matricula;

    public Date getData_Matricula() {
        return data_Matricula;
    }

    public void setData_Matricula(Date data_Matricula) {
        this.data_Matricula = data_Matricula;
    }

    public Aluno getAluno_Matricula() {
        return Aluno_Matricula;
    }

    public void setAluno_Matricula(Aluno Aluno_Matricula) {
        this.Aluno_Matricula = Aluno_Matricula;
    }

    public long getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(long idMatricula) {
        this.idMatricula = idMatricula;
    }
  
}
