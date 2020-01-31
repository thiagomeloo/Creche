package Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


//ENTIDADE NÃO ESTÁ SENDO UTILIZADA

@Entity
public class TelefoneResponsavel {
    
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long idTelefoneResponsavel;
   private String num_tel;
   @ManyToOne
   @JoinColumn
   private Responsavel  responsavelCpf;

    public long getIdTelefoneResponsavel() {
        return idTelefoneResponsavel;
    }

    public void setIdTelefoneResponsavel(long idTelefoneResponsavel) {
        this.idTelefoneResponsavel = idTelefoneResponsavel;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public Responsavel getResponsavelCpf() {
        return responsavelCpf;
    }

    public void setResponsavelCpf(Responsavel responsavelCpf) {
        this.responsavelCpf = responsavelCpf;
    }

}




