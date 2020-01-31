package Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
    
    @NamedQuery(name="AlunoResponsavel.consultarMae",
                query ="SELECT a FROM AlunoResponsavel a JOIN FETCH a.responsavel WHERE a.responsavel.parentesco = 'mae' and a.aluno.matricula = :mat"),
    
    @NamedQuery(name="AlunoResponsavel.consultarPai",
                query ="SELECT a FROM AlunoResponsavel a JOIN FETCH a.responsavel WHERE a.responsavel.parentesco = 'pai' and a.aluno.matricula = :mat"),
})
public class AlunoResponsavel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn
    private Aluno aluno;
    
    @ManyToOne
    @JoinColumn
    private Responsavel responsavel;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
