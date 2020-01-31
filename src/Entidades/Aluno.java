package Entidades;
import java.sql.Date;
import javax.persistence.Column;
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
    
    @NamedQuery(name="Aluno.consultarTodosComEndereco",
                query ="SELECT a FROM Aluno a JOIN FETCH a.Endereco_idEndereco ORDER BY a.nome "),
    @NamedQuery(name="Aluno.consultarTodosComEnderecoEsemTurma",
                query ="SELECT a FROM Aluno a JOIN FETCH a.Endereco_idEndereco WHERE a.Turma_idTurma = NULL ORDER BY a.nome "),
    @NamedQuery(name="Aluno.consultarTodosComEnderecoPorLike",
                query ="SELECT a FROM Aluno a JOIN FETCH a.Endereco_idEndereco WHERE a.nome like :desc ORDER BY a.nome "),
    
    @NamedQuery(name="Aluno.consultarTodosAlunosDeUmaTurma",
                query ="SELECT a FROM Aluno a WHERE a.Turma_idTurma.idTurma = :id ORDER BY a.nome "),
})
public class Aluno {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long matricula; 
  private String statusMatricula;
  private String nome;
  @Column(nullable = false, unique = true)
  private String cpf;
  private String sexo;
  private Date dataNascimento;
  private String cor;
  private boolean necessidadesEspeciais;
  private boolean utilizaTransporte;
  private String especificacaoNecessidadesEspeciais;
  private String idSus;
  private String NIS;
  private String Naturalidade;
  private String Certidao_nasc;
  private String Livro_Nasc;
  private String Folhas_Nasc;
  private Date dataExpedicao_Certidao;
  private String Cartorio_Nasc;
  private String Cidade_Cartorio;
  private String UFnasc;
  

  @ManyToOne
  @JoinColumn
  private Turma Turma_idTurma;
  @ManyToOne
  @JoinColumn
  private Endereco Endereco_idEndereco;

    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }
    
    public String getStatusMatricula() {
        return statusMatricula;
    }

    public void setStatusMatricula(String statusMatricula) {
        this.statusMatricula = statusMatricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isNecessidadesEspeciais() {
        return necessidadesEspeciais;
    }

    public void setNecessidadesEspeciais(boolean necessidadesEspeciais) {
        this.necessidadesEspeciais = necessidadesEspeciais;
    }

    public boolean isUtilizaTransporte() {
        return utilizaTransporte;
    }

    public void setUtilizaTransporte(boolean utilizaTransporte) {
        this.utilizaTransporte = utilizaTransporte;
    }



    public String getEspecificacaoNecessidadesEspeciais() {
        return especificacaoNecessidadesEspeciais;
    }

    public void setEspecificacaoNecessidadesEspeciais(String especificacaoNecessidadesEspeciais) {
        this.especificacaoNecessidadesEspeciais = especificacaoNecessidadesEspeciais;
    }

    public String getIdSus() {
        return idSus;
    }

    public void setIdSus(String idSus) {
        this.idSus = idSus;
    }
    
    public String getNIS() {
        return NIS;
    }

    public void setNIS(String NIS) {
        this.NIS = NIS;
    }
    
    public Turma getTurma_idTurma() {
        return Turma_idTurma;
    }

    public void setTurma_idTurma(Turma Turma_idTurma) {
        this.Turma_idTurma = Turma_idTurma;
    }

    public Endereco getEndereco_idEndereco() {
        return Endereco_idEndereco;
    }

    public void setEndereco_idEndereco(Endereco Endereco_idEndereco) {
        this.Endereco_idEndereco = Endereco_idEndereco;
    }

    public String getNaturalidade() {
        return Naturalidade;
    }

    public void setNaturalidade(String Naturalidade) {
        this.Naturalidade = Naturalidade;
    }

    public String getCertidao_nasc() {
        return Certidao_nasc;
    }

    public void setCertidao_nasc(String Certidao_nasc) {
        this.Certidao_nasc = Certidao_nasc;
    }

    public String getLivro_Nasc() {
        return Livro_Nasc;
    }

    public void setLivro_Nasc(String Livro_Nasc) {
        this.Livro_Nasc = Livro_Nasc;
    }

    public String getFolhas_Nasc() {
        return Folhas_Nasc;
    }

    public void setFolhas_Nasc(String Folhas_Nasc) {
        this.Folhas_Nasc = Folhas_Nasc;
    }

    public Date getDataExpedicao_Certidao() {
        return dataExpedicao_Certidao;
    }

    public void setDataExpedicao_Certidao(Date dataExpedicao_Certidao) {
        this.dataExpedicao_Certidao = dataExpedicao_Certidao;
    }

    public String getCartorio_Nasc() {
        return Cartorio_Nasc;
    }

    public void setCartorio_Nasc(String Cartorio_Nasc) {
        this.Cartorio_Nasc = Cartorio_Nasc;
    }

    public String getCidade_Cartorio() {
        return Cidade_Cartorio;
    }

    public void setCidade_Cartorio(String Cidade_Cartorio) {
        this.Cidade_Cartorio = Cidade_Cartorio;
    }

    public String getUFnasc() {
        return UFnasc;
    }

    public void setUFnasc(String UFnasc) {
        this.UFnasc = UFnasc;
    }

    
}