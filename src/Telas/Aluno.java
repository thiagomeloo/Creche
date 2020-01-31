package Telas;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;



public class Aluno extends javax.swing.JInternalFrame {

    public void Inserir_Aluno_Com_Mae_Pai_e_Endereco(){
        
        //CRIANDO OBJETO ENDEREÇO
        Entidades.Endereco endereco = new Entidades.Endereco();
        DAO.EnderecoDAO daoEndereco = new DAO.EnderecoDAO();
        endereco.setRua(txtRua.getText());
        endereco.setBairro(txtBairro.getText());
        endereco.setNumero(Integer.parseInt(txtNumeroCasa.getText()));
        endereco.setCidade(txtCidade.getText());
        endereco.setEstado(txtEstado.getText());
        daoEndereco.salvar(endereco);
        endereco.setIdEndereco(endereco.getIdEndereco());
        
        //CRIANDO OBJETO ALUNO
        Entidades.Aluno aluno = new Entidades.Aluno();
        DAO.AlunoDAO daoAluno = new DAO.AlunoDAO();
        aluno.setNome(txtNomeAluno.getText());
        aluno.setSexo((String) ComboSexoAluno.getSelectedItem());
        aluno.setCor((String) ComboCorAluno.getSelectedItem());
        aluno.setNIS(Ftxt_Nis_Aluno.getText());
        aluno.setIdSus(Ftxt_SUS_Cadastro.getText());
        
        Entidades.Aluno alunoN = new Entidades.Aluno();
        alunoN = daoAluno.consultaAlunoPorCpf((String) FtxtCpf.getValue());
        if(alunoN.getCpf() == null){
          aluno.setCpf((String) FtxtCpf.getValue());  
        }else{
            JOptionPane.showMessageDialog(null, "CPF do aluno Já foi cadastrado, verifique se há algum erro ou efetue a busca do aluno no sistema.");
        }
        
        
        
        
        
        //pega data
        String dataNasc = FtxtDataNascimentoAluno.getText();
        SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dataPegada = formatoBrasileiro.parse(dataNasc) ;
            java.sql.Date dataJDBC = new java.sql.Date(dataPegada.getTime());
            aluno.setDataNascimento(dataJDBC);
            
        } catch (ParseException ex) {
           //erro na data
        }
        
        //pegando Campos Cartorio
        aluno.setNaturalidade(txtNaturalidade.getText());
        aluno.setCertidao_nasc(txt_CertNasc.getText());
        aluno.setLivro_Nasc(Ftxt_LivroNasc.getText());
        aluno.setFolhas_Nasc(Ftxt_FolhaNasc.getText());
        aluno.setCartorio_Nasc(Ftxt_Cartorio.getText());
        aluno.setCidade_Cartorio(txtCidadeCartorio.getText());
        aluno.setUFnasc(txtUFnasc.getText());  
        
        //pega data Expedicao
        String dataExpedicao = FtxtDataExpedicao.getText();
        SimpleDateFormat formatoBrasileiroEx = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dataPegada = formatoBrasileiroEx.parse(dataExpedicao) ;
            java.sql.Date dataJDBC = new java.sql.Date(dataPegada.getTime());
            aluno.setDataExpedicao_Certidao(dataJDBC);
            
        } catch (ParseException ex) {
           //erro na data
        }
        
        
             
        
        //VERIFICA SE TEM NECESSIDADE ESPECIAL
        if(RbNecessidadeEspecial == "sim"){
            aluno.setNecessidadesEspeciais(true);
            aluno.setEspecificacaoNecessidadesEspeciais(TextAreaNecessidade.getText());
        }else if(RbNecessidadeEspecial == "nao"){
            aluno.setNecessidadesEspeciais(false);
        }
        
        //VERIFICA SE UTILIZA TRANSPORTE
        if(RbTransporte == "sim"){
            aluno.setUtilizaTransporte(true);
           
        }else if(RbTransporte == "nao"){
            aluno.setUtilizaTransporte(false);
        }
        
        //seta o endereço do aluno
        aluno.setEndereco_idEndereco(endereco);
        //INSERE O ALUNO
        daoAluno.salvar(aluno);
        
        
        //CRIA O OBJETO MAE
        Entidades.Responsavel mae = new Entidades.Responsavel();
        mae.setNome(txtNome_Mae_Filiacao.getText());
        mae.setRg(txtRgMAE.getText());
        mae.setSexo("Feminino");
        mae.setParentesco("mae");
        mae.setProfissao(txt_Profissao_Filiacao_Mae.getText());
        mae.setOrgaoExpeditor(txt_OrgaoExpeditor_Filiacao_Mae.getText());
        mae.setTel(Ftxt_Tel_Filiacao_mae.getText());
        
        //SALVA O OBJETO MAE
        DAO.ResponsavelDAO maeDAO = new DAO.ResponsavelDAO();
        Entidades.Responsavel maeNew = new Entidades.Responsavel();
        try {
            maeDAO.salvar(mae);
        } catch (Exception e) {
            maeNew = maeDAO.PegarIdResponsavelComRG(mae.getRg());
            mae.setIdResponsavel(maeNew.getIdResponsavel());
            maeDAO.salvar(mae);
        }
        
        
        maeNew = maeDAO.PegarIdResponsavelComRG(mae.getRg());
        mae.setIdResponsavel(maeNew.getIdResponsavel());
        
        //CRIA O OBJETO AlunoResponsavel
        Entidades.AlunoResponsavel alRespMae = new Entidades.AlunoResponsavel();
        alRespMae.setAluno(aluno);
        alRespMae.setResponsavel(mae);
        //SALVA O OBJETO AlunoResponsavel
        DAO.AlunoResponsavelDAO alRespMaeDAO = new DAO.AlunoResponsavelDAO();
        alRespMaeDAO.salvar(alRespMae);
        
        
        //CRIA O OBJETO PAI
        if(CheckPai.isSelected() == false){
            Entidades.Responsavel pai = new Entidades.Responsavel();
            pai.setNome(txtNome_Pai_Filiacao.getText());
            pai.setRg(txtRgPAI.getText());
            pai.setSexo("Masculino");
            pai.setParentesco("pai");
            pai.setProfissao(txt_Profissao_Filiacao_Pai.getText());
            pai.setOrgaoExpeditor(txt_OrgaoExpeditor_Filiacao_Pai.getText());
            pai.setTel(Ftxt_Tel_Filiacao_pai.getText());
            
            //SALVA O OBJETO Pai
            DAO.ResponsavelDAO paiDAO = new DAO.ResponsavelDAO();
            Entidades.Responsavel paiNew = new Entidades.Responsavel();
            try {
                paiDAO.salvar(pai);
            } catch (Exception e) {
                paiNew = paiDAO.PegarIdResponsavelComRG(pai.getRg());
                pai.setIdResponsavel(paiNew.getIdResponsavel());
                paiDAO.salvar(pai);
            }

            paiNew = paiDAO.PegarIdResponsavelComRG(pai.getRg());
            pai.setIdResponsavel(paiNew.getIdResponsavel());

            
            
            //CRIA O OBJETO AlunoResponsavel
            Entidades.AlunoResponsavel alRespPai = new Entidades.AlunoResponsavel();
            alRespPai.setAluno(aluno);
            alRespPai.setResponsavel(pai);
            //SALVA O OBJETO AlunoResponsavel
            DAO.AlunoResponsavelDAO alRespPaiDAO = new DAO.AlunoResponsavelDAO();
            alRespPaiDAO.salvar(alRespPai);
        }

    }
    
    public void Atualizar_Aluno_Com_Mae_Pai_e_Endereco(){
        
        //CRIANDO OBJETO ENDEREÇO
        Entidades.Endereco endereco = new Entidades.Endereco();
        DAO.EnderecoDAO daoEndereco = new DAO.EnderecoDAO();
        endereco.setRua(txtRua.getText());
        endereco.setBairro(txtBairro.getText());
        endereco.setNumero(Integer.parseInt(txtNumeroCasa.getText()));
        endereco.setCidade(txtCidade.getText());
        endereco.setEstado(txtEstado.getText());
        daoEndereco.salvar(endereco);
        endereco.setIdEndereco(endereco.getIdEndereco());
        
        //CRIANDO OBJETO ALUNO
        Entidades.Aluno aluno = new Entidades.Aluno();
        DAO.AlunoDAO daoAluno = new DAO.AlunoDAO();
        aluno.setMatricula(MatAlunoSelect);
        aluno.setNome(txtNomeAluno.getText());
        aluno.setCpf((String) FtxtCpf.getValue());
        aluno.setSexo((String) ComboSexoAluno.getSelectedItem());
        aluno.setCor((String) ComboCorAluno.getSelectedItem());
        aluno.setNIS(Ftxt_Nis_Aluno.getText());
        aluno.setIdSus(Ftxt_SUS_Cadastro.getText());
        
        //pega data
        String dataNasc = FtxtDataNascimentoAluno.getText();
        SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dataPegada = formatoBrasileiro.parse(dataNasc) ;
            java.sql.Date dataJDBC = new java.sql.Date(dataPegada.getTime());
            aluno.setDataNascimento(dataJDBC);
            
        } catch (ParseException ex) {
           //erro na data
        }
        
        
        //pegando Campos Cartorio
        aluno.setCertidao_nasc(txt_CertNasc.getText());
        aluno.setLivro_Nasc(Ftxt_LivroNasc.getText());
        aluno.setFolhas_Nasc(Ftxt_FolhaNasc.getText());
        aluno.setCartorio_Nasc(Ftxt_Cartorio.getText());
        aluno.setCidade_Cartorio(txtCidadeCartorio.getText());
        aluno.setUFnasc(txtUFnasc.getText());  
        aluno.setNaturalidade(txtNaturalidade.getText());
        //System.out.println(aluno.getDataExpedicao_Certidao());
        
        //pega data Expedicao
        String dataExpedicao = FtxtDataExpedicao.getText();
        SimpleDateFormat formatoBrasileiroEx = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dataPegadaNasc = formatoBrasileiroEx.parse(dataExpedicao) ;
            java.sql.Date dataJDBC = new java.sql.Date(dataPegadaNasc.getTime());
            aluno.setDataExpedicao_Certidao(dataJDBC);
            
        } catch (ParseException ex) {
           //erro na data
        }
        
        
        //VERIFICA SE TEM NECESSIDADE ESPECIAL
        if(RbNecessidadeEspecial == "sim"){
            aluno.setNecessidadesEspeciais(true);
            aluno.setEspecificacaoNecessidadesEspeciais(TextAreaNecessidade.getText());
        }else if(RbNecessidadeEspecial == "nao"){
            aluno.setNecessidadesEspeciais(false);
        }
        
        //VERIFICA SE UTILIZA TRANSPORTE
        if(RbTransporte == "sim"){
            aluno.setUtilizaTransporte(true);
           
        }else if(RbTransporte == "nao"){
            aluno.setUtilizaTransporte(false);
        }
        
        //seta o endereço do aluno
        aluno.setEndereco_idEndereco(endereco);
        //INSERE O ALUNO
        daoAluno.atualizar(aluno);
        
        //CRIA O OBJETO MAE
        Entidades.Responsavel mae = new Entidades.Responsavel();
        mae.setNome(txtNome_Mae_Filiacao.getText());
        mae.setRg(txtRgMAE.getText());
        mae.setSexo("Feminino");
        mae.setParentesco("mae");
        mae.setProfissao(txt_Profissao_Filiacao_Mae.getText());
        mae.setOrgaoExpeditor(txt_OrgaoExpeditor_Filiacao_Mae.getText());
        mae.setTel((String) Ftxt_Tel_Filiacao_mae.getValue());
        mae.setIdResponsavel(IdMae);
        DAO.ResponsavelDAO maeDAO = new DAO.ResponsavelDAO();
        
        //SALVA O OBJETO MAE
        maeDAO.salvar(mae);
        
        try {
            Entidades.AlunoResponsavel aluResPAI = new Entidades.AlunoResponsavel();
            DAO.AlunoResponsavelDAO aluResPaiDao = new DAO.AlunoResponsavelDAO();
            aluResPAI = aluResPaiDao.consultaPai(MatAlunoSelect);
            
            if(CheckPai.isSelected() == false){
                //CRIA O OBJETO PAI
                Entidades.Responsavel pai = new Entidades.Responsavel();
                pai.setNome(txtNome_Pai_Filiacao.getText());
                pai.setRg(aluResPAI.getResponsavel().getRg());
                pai.setRg(txtRgPAI.getText());
                pai.setSexo("Masculino");
                pai.setParentesco("pai");
                pai.setProfissao(txt_Profissao_Filiacao_Pai.getText());
                pai.setOrgaoExpeditor(txt_OrgaoExpeditor_Filiacao_Pai.getText());
                pai.setTel((String) Ftxt_Tel_Filiacao_pai.getValue());
                
                DAO.ResponsavelDAO paiDAO = new DAO.ResponsavelDAO();
                
                pai.setIdResponsavel(IdPai);
                
                //SALVA OBJETO PAI
                paiDAO.salvar(pai);
            }
            

            
        } catch (Exception e) {
                //CRIA O OBJETO PAI
                Entidades.Responsavel pai = new Entidades.Responsavel();
                pai.setNome(txtNome_Pai_Filiacao.getText());
                pai.setRg(txtRgPAI.getText());
                pai.setSexo("Masculino");
                pai.setParentesco("pai");
                pai.setProfissao(txt_Profissao_Filiacao_Pai.getText());
                pai.setOrgaoExpeditor(txt_OrgaoExpeditor_Filiacao_Pai.getText());
                pai.setTel((String) Ftxt_Tel_Filiacao_pai.getValue());
                
                //SALVA O OBJETO Pai
                DAO.ResponsavelDAO paiDAO = new DAO.ResponsavelDAO();
                Entidades.Responsavel paiNew = new Entidades.Responsavel();
                try {
                    paiDAO.salvar(pai);
                } catch (Exception a) {
                    paiNew = paiDAO.PegarIdResponsavelComRG(pai.getRg());
                    pai.setIdResponsavel(paiNew.getIdResponsavel());
                    paiDAO.salvar(pai);
                }

                paiNew = paiDAO.PegarIdResponsavelComRG(pai.getRg());
                pai.setIdResponsavel(paiNew.getIdResponsavel());
            

                //CRIA O OBJETO AlunoResponsavel
                Entidades.AlunoResponsavel alRespPai = new Entidades.AlunoResponsavel();
                alRespPai.setAluno(aluno);
                alRespPai.setResponsavel(pai);

                //SALVA O OBJETO AlunoResponsavel
                DAO.AlunoResponsavelDAO alRespPaiDAO = new DAO.AlunoResponsavelDAO();
                alRespPaiDAO.salvar(alRespPai);

                ListarTodosAlunosComEndereco();
            
        }
        
        
        
    }
    
    public boolean VerificaCampoVazioDoPainelDadosAluno(){
        boolean retorno = true;
        if("".equals(txtNomeAluno.getText())){
            txtNomeAluno.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txtNomeAluno.setBorder(new LineBorder(Color.green, 1));  
        }
        if("  /  /    ".equals(FtxtDataNascimentoAluno.getText())){
            FtxtDataNascimentoAluno.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
            FtxtDataNascimentoAluno.setBorder(new LineBorder(Color.green, 1));
        }

        if(ComboSexoAluno.getSelectedIndex() == 0){
            ComboSexoAluno.setBorder(new LineBorder(Color.red));
            retorno = false;
        }else{
            ComboSexoAluno.setBorder(new LineBorder(Color.green));
        }        
        if(ComboCorAluno.getSelectedIndex() == 0){
            ComboCorAluno.setBorder(new LineBorder(Color.red));
            retorno = false;
            //System.out.println();
        }else{
             ComboCorAluno.setBorder(new LineBorder(Color.green));
        }
        
        if("".equals(Ftxt_LivroNasc.getText())){
            Ftxt_LivroNasc.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          Ftxt_LivroNasc.setBorder(new LineBorder(Color.green, 1));  
        }        
        if("".equals(txt_CertNasc.getText())){
            txt_CertNasc.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txt_CertNasc.setBorder(new LineBorder(Color.green, 1));  
        }
        if("  /  /    ".equals(FtxtDataExpedicao.getText())){
            FtxtDataExpedicao.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
            FtxtDataExpedicao.setBorder(new LineBorder(Color.green, 1));
        }
        //aaq
        
        if("   .   .   -  ".equals(FtxtCpf.getText())){
            FtxtCpf.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
            FtxtCpf.setBorder(new LineBorder(Color.green, 1));
        }
        
        if("".equals(txtNaturalidade.getText())){
            txtNaturalidade.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txtNaturalidade.setBorder(new LineBorder(Color.green, 1));  
        }
        if("".equals(Ftxt_FolhaNasc.getText())){
            Ftxt_FolhaNasc.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          Ftxt_FolhaNasc.setBorder(new LineBorder(Color.green, 1));  
        }
        if("".equals(Ftxt_Cartorio.getText())){
            Ftxt_Cartorio.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          Ftxt_Cartorio.setBorder(new LineBorder(Color.green, 1));  
        }
        if("".equals(txtCidadeCartorio.getText())){
           txtCidadeCartorio.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txtCidadeCartorio.setBorder(new LineBorder(Color.green, 1));  
        }
        if("".equals(txtUFnasc.getText())){
           txtUFnasc.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txtUFnasc.setBorder(new LineBorder(Color.green, 1));  
        }
        
        if(retorno == true){
           restaurarBordas();          
        }else{
            JOptionPane.showMessageDialog(null, "Por Favor preencha todos os campos obrigatorios da aba Dados Cadastrais!");
        }
        
        return retorno;
    }
    
    public void restaurarBordas(){
        //campos do aluno
        txtNomeAluno.setBorder(borderText);
        FtxtDataNascimentoAluno.setBorder(borderText);
        Ftxt_Nis_Aluno.setBorder(borderText);
        Ftxt_SUS_Cadastro.setBorder(borderText);
        FtxtCpf.setBorder(borderText);
        ComboSexoAluno.setBorder(borderCombo);
        ComboCorAluno.setBorder(borderCombo);
        
        txtNaturalidade.setBorder(borderText);
        Ftxt_LivroNasc.setBorder(borderText);
        txt_CertNasc.setBorder(borderText);
        FtxtDataExpedicao.setBorder(borderText);
        Ftxt_LivroNasc.setBorder(borderText);
        Ftxt_FolhaNasc.setBorder(borderText);
        Ftxt_Cartorio.setBorder(borderText);
        txtCidadeCartorio.setBorder(borderText);
        txtUFnasc.setBorder(borderText);
                
        //campos painel da mae
        txtNome_Mae_Filiacao.setBorder(borderText);
        txtRgMAE.setBorder(borderText);
        txt_OrgaoExpeditor_Filiacao_Mae.setBorder(borderText);
        
        //campos painel do pai
        txtNome_Pai_Filiacao.setBorder(borderText);
        txtRgPAI.setBorder(borderText);
        txt_OrgaoExpeditor_Filiacao_Pai.setBorder(borderText);
        
        //campos de endereco
        txtRua.setBorder(borderText);
        txtBairro.setBorder(borderText);
        txtNumeroCasa.setBorder(borderText);
        txtCidade.setBorder(borderText);
        txtEstado.setBorder(borderText);
    }
    
    public boolean VerificaCampoVazioDoPainelEndereco(){
        boolean retorno = true;
        if("".equals(txtRua.getText())){
            txtRua.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txtRua.setBorder(new LineBorder(Color.green, 1));  
        }
        if("".equals(txtBairro.getText())){
            txtBairro.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txtBairro.setBorder(new LineBorder(Color.green, 1));  
        }
        if("".equals(txtNumeroCasa.getText())){
            txtNumeroCasa.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txtNumeroCasa.setBorder(new LineBorder(Color.green, 1));  
        }
        if("".equals(txtCidade.getText())){
            txtCidade.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txtCidade.setBorder(new LineBorder(Color.green, 1));  
        }
        if("".equals(txtEstado.getText())){
            txtEstado.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txtEstado.setBorder(new LineBorder(Color.green, 1));  
        }
        if(retorno == true){
            restaurarBordas();
        }else{
            JOptionPane.showMessageDialog(null, "Por Favor preencha todos os campos obrigatorios da aba Dados Residenciais!");
        }
        
       return retorno; 
    }
    
    public boolean VerificaCampoVazioDoPainelMae(){
        boolean retorno = true;
        if("".equals(txtNome_Mae_Filiacao.getText())){
            txtNome_Mae_Filiacao.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txtNome_Mae_Filiacao.setBorder(new LineBorder(Color.green, 1));  
        }
        if("".equals(txtRgMAE.getText()) ){
            txtRgMAE.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txtRgMAE.setBorder(new LineBorder(Color.green, 1));  
        }
        if("".equals(txt_OrgaoExpeditor_Filiacao_Mae.getText())){
            txt_OrgaoExpeditor_Filiacao_Mae.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txt_OrgaoExpeditor_Filiacao_Mae.setBorder(new LineBorder(Color.green, 1));  
        }

        if(retorno == true){
            restaurarBordas();
        }else{
            JOptionPane.showMessageDialog(null, "Por Favor preencha todos os Campos obrigatorios da filiação Mãe!");
        }
        
       return retorno; 
    }
    
    public boolean VerificaCampoVazioDoPainelPai(){
        boolean retorno = true;
        if("".equals(txtNome_Pai_Filiacao.getText())){
            txtNome_Pai_Filiacao.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txtNome_Pai_Filiacao.setBorder(new LineBorder(Color.green, 1));  
        }
        if("".equals(txtRgPAI.getText())){
            txtRgPAI.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txtRgPAI.setBorder(new LineBorder(Color.green, 1));  
        }
        if("".equals(txt_OrgaoExpeditor_Filiacao_Pai.getText())){
            txt_OrgaoExpeditor_Filiacao_Pai.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txt_OrgaoExpeditor_Filiacao_Pai.setBorder(new LineBorder(Color.green, 1));  
        }

        if(retorno == true){
            restaurarBordas();
        }else{
            JOptionPane.showMessageDialog(null, "Por Favor preencha todos os campos obrigatorios da filiação Pai!");
        }
        
       return retorno; 
    }
    
    public void LimparCampos(){
        //Painel Aluno
        txtNomeAluno.setText("");
        Ftxt_Nis_Aluno.setText("");
        Ftxt_SUS_Cadastro.setText("");
        FtxtDataNascimentoAluno.setText("");
        FtxtCpf.setText("");
        ComboSexoAluno.setSelectedIndex(0);
        ComboCorAluno.setSelectedIndex(0);
        
        Ftxt_LivroNasc.setText("");
        txt_CertNasc.setText("");
        FtxtDataExpedicao.setText("");
        Ftxt_LivroNasc.setText("");
        Ftxt_FolhaNasc.setText("");
        Ftxt_Cartorio.setText("");
        txtCidadeCartorio.setText("");
        txtUFnasc.setText("");
        txtNaturalidade.setText("");
        
        //painel MAE
        txtNome_Mae_Filiacao.setText("");
        txtRgMAE.setText("");
        txt_OrgaoExpeditor_Filiacao_Mae.setText("");
        txt_Profissao_Filiacao_Mae.setText("");
        Ftxt_Tel_Filiacao_mae.setText("");
        
        //painel PAI
        txtNome_Pai_Filiacao.setText("");
        txtRgPAI.setText("");
        txt_OrgaoExpeditor_Filiacao_Pai.setText("");
        txt_Profissao_Filiacao_Pai.setText("");
        Ftxt_Tel_Filiacao_pai.setText("");
        CheckPai.setEnabled(true);
        CheckPai.setSelected(false);
        boolean check = true;
        txtNome_Pai_Filiacao.setEnabled(check);
        txtRgPAI.setEnabled(check);
        txt_OrgaoExpeditor_Filiacao_Pai.setEnabled(check);
        txt_Profissao_Filiacao_Pai.setEnabled(check);
        Ftxt_Tel_Filiacao_pai.setEnabled(check);
        
        txtNome_Pai_Filiacao.setBorder(borderText);
        txtRgPAI.setBorder(borderText);
        txt_OrgaoExpeditor_Filiacao_Pai.setBorder(borderText);
        
        
        
        //painel Endereco
        txtRua.setText("");
        txtBairro.setText("");
        txtNumeroCasa.setText("");
        txtCidade.setText("");
        txtEstado.setText("");
        
        TextAreaNecessidade.setText("");
        RbNecessidadesNao.setSelected(true);
        RbTransporteNao.setSelected(true);
        GuiasFiliacao.setEnabledAt( 2, false);
    }

    public void ListarTodosAlunosComEndereco(){    
        DAO.AlunoDAO alDAO = new DAO.AlunoDAO();
        List<Entidades.Aluno> listaAluno = alDAO.consultarTodosAlunosComEdereco();
        ModeloTabelaAluno.setNumRows(0);
        SimpleDateFormat Df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat DfEx = new SimpleDateFormat("dd/MM/yyyy");
        for(Entidades.Aluno a : listaAluno){
            java.util.Date dateNasc = a.getDataNascimento();
            java.util.Date dateExp = a.getDataExpedicao_Certidao();
            
             ModeloTabelaAluno.addRow(new Object[]{
                 a.getMatricula(),a.getNome(),Df.format(dateNasc),a.getSexo(),a.getNIS(),a.getIdSus(),
                 a.isNecessidadesEspeciais(),a.getEspecificacaoNecessidadesEspeciais(),
                 a.isUtilizaTransporte(),a.getCor(),
                 a.getEndereco_idEndereco().getRua(),a.getEndereco_idEndereco().getBairro(),
                 a.getEndereco_idEndereco().getNumero(),a.getEndereco_idEndereco().getCidade(),
                 a.getEndereco_idEndereco().getEstado(),
                 a.getNaturalidade(),a.getCertidao_nasc(),
                 a.getLivro_Nasc(),a.getFolhas_Nasc(),DfEx.format(dateExp),
                 a.getCartorio_Nasc(),a.getCidade_Cartorio(),a.getUFnasc(),a.getCpf()
                     
                   
            });
        }
    }  
    
    public void ListarAlunosPorMatComLike(String str){    
        DAO.AlunoDAO alDAO = new DAO.AlunoDAO();
        List<Entidades.Aluno> listaAluno = alDAO.consultaAlunosPorMatComLike(str);
        ModeloTabelaAluno.setNumRows(0);
        SimpleDateFormat Df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat DfEx = new SimpleDateFormat("dd/MM/yyyy");
        for(Entidades.Aluno a : listaAluno){
            java.util.Date dateNasc = a.getDataNascimento();
            java.util.Date dateExp = a.getDataExpedicao_Certidao();
             ModeloTabelaAluno.addRow(new Object[]{
                 
                a.getMatricula(),a.getNome(),Df.format(dateNasc),a.getSexo(),a.getNIS(),a.getIdSus(),
                 a.isNecessidadesEspeciais(),a.getEspecificacaoNecessidadesEspeciais(),
                 a.isUtilizaTransporte(),a.getCor(),
                 a.getEndereco_idEndereco().getRua(),a.getEndereco_idEndereco().getBairro(),
                 a.getEndereco_idEndereco().getNumero(),a.getEndereco_idEndereco().getCidade(),
                 a.getEndereco_idEndereco().getEstado(),
                 a.getNaturalidade(),a.getCertidao_nasc(),
                 a.getLivro_Nasc(),a.getFolhas_Nasc(),DfEx.format(dateExp),
                 a.getCartorio_Nasc(),a.getCidade_Cartorio(),a.getUFnasc(),a.getCpf()
            });
        }
    }
    
    long MatAlunoSelect;
    long IdPai;
    long IdMae;
    public void SetarCampos(){
        IdPai = 0;
        IdMae = 0;
        LimparCampos();
        //campos painel aluno
        MatAlunoSelect = Long.decode(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 0).toString());
        txtNomeAluno.setText(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 1).toString());
        FtxtDataNascimentoAluno.setValue(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 2).toString());
        FtxtCpf.setValue(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 23).toString());
        Ftxt_Nis_Aluno.setText(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 4).toString());
        Ftxt_SUS_Cadastro.setText(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 5).toString());
        
        //combobox Sexo painel aluno
        String sexoAl = TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 3).toString();
        if("Masculino".equals(sexoAl)){
            ComboSexoAluno.setSelectedIndex(1);
        }else if("Feminino".equals(sexoAl)){
            ComboSexoAluno.setSelectedIndex(2);
        }
        //Combobox Cor/Raça painel aluno
        String corRaca = TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 9).toString();
        if("Branca".equals(corRaca)){
            ComboCorAluno.setSelectedIndex(1);
        }else if("Preta".equals(corRaca)){
            ComboCorAluno.setSelectedIndex(2);
        }else if("Parda".equals(corRaca)){
            ComboCorAluno.setSelectedIndex(3);
        }else if("Indígena".equals(corRaca)){
            ComboCorAluno.setSelectedIndex(4);
        }else if("Amarela".equals(corRaca)){
            ComboCorAluno.setSelectedIndex(5);
        }else if("Não desejo Declarar".equals(corRaca)){
            ComboCorAluno.setSelectedIndex(6);
        }
        
        //setar campos cartorio
        txtNaturalidade.setText(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 15).toString());
        txt_CertNasc.setText(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 16).toString());
        Ftxt_LivroNasc.setText(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 17).toString());
        Ftxt_FolhaNasc.setText(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 18).toString());
        FtxtDataExpedicao.setValue(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 19).toString());
        Ftxt_Cartorio.setText(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 20).toString());
        txtCidadeCartorio.setText(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 21).toString());
        txtUFnasc.setText(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 22).toString());
        

        //campos painel endereco
        txtRua.setText(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 10).toString());
        txtBairro.setText(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 12).toString());
        txtNumeroCasa.setText(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 12).toString());
        txtCidade.setText(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 13).toString());
        txtEstado.setText(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 14).toString());
        
        
        //campos painel Informações adicionais
        String necessidade = TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 6).toString();
        if("true".equals(necessidade)){
            TextAreaNecessidade.setEnabled(true);
            TextAreaNecessidade.setText(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 7).toString()); 
            RbNecessidadesSim.setSelected(true);
        }else{
            RbNecessidadesNao.setSelected(true);
            TextAreaNecessidade.setEnabled(true);
            TextAreaNecessidade.setText("");
        }
        
        String transporte = TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 8).toString();
        if("true".equals(transporte)){
           RbTransporteSim.setSelected(true);
        }else{
            RbTransporteNao.setSelected(true);
        }
        
        //CAMPOS PAINEL MAE
        try {
            Entidades.AlunoResponsavel aluResMAE = new Entidades.AlunoResponsavel();
            DAO.AlunoResponsavelDAO aluResMaeDao = new DAO.AlunoResponsavelDAO();
            aluResMAE = aluResMaeDao.consultaMae(MatAlunoSelect);
            txtNome_Mae_Filiacao.setText(aluResMAE.getResponsavel().getNome());
            txtRgMAE.setText(String.valueOf(aluResMAE.getResponsavel().getRg()));
            txt_OrgaoExpeditor_Filiacao_Mae.setText(aluResMAE.getResponsavel().getOrgaoExpeditor());

            String profissaoMae = aluResMAE.getResponsavel().getProfissao();
            System.out.println(profissaoMae);
            if("".equals(profissaoMae)){
                //System.out.println("vazio");
            }else{
                txt_Profissao_Filiacao_Mae.setText(aluResMAE.getResponsavel().getProfissao());
            }
            String telMae = aluResMAE.getResponsavel().getTel();
            if("".equals(telMae)){
                //System.out.println("vazio");
            }else{
                Ftxt_Tel_Filiacao_mae.setValue(aluResMAE.getResponsavel().getTel());
            }

            
            
            
            IdMae = aluResMAE.getResponsavel().getIdResponsavel();
        } catch (Exception e) {
            
        }
        
        //CAMPOS PAINEL PAI
        try {
            Entidades.AlunoResponsavel aluResPAI = new Entidades.AlunoResponsavel();
            DAO.AlunoResponsavelDAO aluResPaiDao = new DAO.AlunoResponsavelDAO();
            aluResPAI = aluResPaiDao.consultaPai(MatAlunoSelect);
            txtNome_Pai_Filiacao.setText(aluResPAI.getResponsavel().getNome());
            txtRgPAI.setText(String.valueOf(aluResPAI.getResponsavel().getRg()));
            txt_OrgaoExpeditor_Filiacao_Pai.setText(aluResPAI.getResponsavel().getOrgaoExpeditor());
            
            String profissaoPai = aluResPAI.getResponsavel().getProfissao();
            if("".equals(profissaoPai)){
                //System.out.println("vazio profissao pai");
            }else{
                txt_Profissao_Filiacao_Pai.setText(aluResPAI.getResponsavel().getProfissao());
            }
            String telPai = aluResPAI.getResponsavel().getTel();
            //System.out.println(telPai);
            if("(  )          ".equals(telPai)){
                //System.out.println("vazio tel pai");
            }else{
                Ftxt_Tel_Filiacao_pai.setValue(aluResPAI.getResponsavel().getTel());
            }
              
            //Ftxt_Tel_Filiacao_pai.setText(aluResPAI.getResponsavel().getTel());
            IdPai = aluResPAI.getResponsavel().getIdResponsavel();
            CheckPai.setSelected(false);
            CheckPai.setEnabled(false);
            txtNome_Pai_Filiacao.setEnabled(true);
            txtRgPAI.setEnabled(true);
            txt_OrgaoExpeditor_Filiacao_Pai.setEnabled(true);
            txt_Profissao_Filiacao_Pai.setEnabled(true);
            Ftxt_Tel_Filiacao_pai.setEnabled(true);
        } catch (Exception e) {
            CheckPai.setSelected(true);
            CheckPai.setEnabled(true);
            txtNome_Pai_Filiacao.setEnabled(false);
            txtRgPAI.setEnabled(false);
            txt_OrgaoExpeditor_Filiacao_Pai.setEnabled(false);
            txt_Profissao_Filiacao_Pai.setEnabled(false);
            Ftxt_Tel_Filiacao_pai.setEnabled(false);
            
        }
        
    }
    
    public void ListarAlunosPeloNomeComLike(String str){    
        DAO.AlunoDAO alDAO = new DAO.AlunoDAO();
        List<Entidades.Aluno> listaAluno = alDAO.consultaAlunosPorNomeComLike(str);
        ModeloTabelaAluno.setNumRows(0);
        SimpleDateFormat Df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat DfEx = new SimpleDateFormat("dd/MM/yyyy");
        for(Entidades.Aluno a : listaAluno){
            java.util.Date dateNasc = a.getDataNascimento();
            java.util.Date dateExp = a.getDataExpedicao_Certidao();
             ModeloTabelaAluno.addRow(new Object[]{
                 
                a.getMatricula(),a.getNome(),Df.format(dateNasc),a.getSexo(),a.getNIS(),a.getIdSus(),
                 a.isNecessidadesEspeciais(),a.getEspecificacaoNecessidadesEspeciais(),
                 a.isUtilizaTransporte(),a.getCor(),
                 a.getEndereco_idEndereco().getRua(),a.getEndereco_idEndereco().getBairro(),
                 a.getEndereco_idEndereco().getNumero(),a.getEndereco_idEndereco().getCidade(),
                 a.getEndereco_idEndereco().getEstado(),
                 a.getNaturalidade(),a.getCertidao_nasc(),
                 a.getLivro_Nasc(),a.getFolhas_Nasc(),DfEx.format(dateExp),
                 a.getCartorio_Nasc(),a.getCidade_Cartorio(),a.getUFnasc(),a.getCpf()
            });
        }
    }
    
    //Modelo da Tabela Aluno
   //Modelo da Tabela Aluno
    DefaultTableModel ModeloTabelaAluno = new DefaultTableModel(  
        new Object[] {"Matricula","Nome","Data Nasc","Sexo","NIS","SUS","Necessidade",
            "EspecificacaoNecessidade","Transporte","CorRaca","Rua","Bairro",
            "Numero","Cidade","Estado",
            
            "Naturalidade","Certidao_nasc","Livro_Nasc"
            ,"Folhas_Nasc","dataExpedicao_Certidao","Cartorio_Nasc","Cidade_Cartorio","Ufnasc","cpf"},0){  
            boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false ,false,false,false
            };    
                @Override  
                public boolean isCellEditable(int rowIndex, int columnIndex){  
                    return canEdit [columnIndex];  
                }  
        };
    
    public void esconderColunas(){
        int i = 4;
        while( i < TabelaPesquisarAluno.getColumnCount()){
            TabelaPesquisarAluno.getColumnModel().getColumn(i).setMinWidth(0);
            TabelaPesquisarAluno.getColumnModel().getColumn(i).setMaxWidth(0);
            i++;
        }
    }

    Border borderText; // Criando VARIAVEL DAS BORDAS GLOBAL
    Border borderCombo;
    public Aluno() {
        
        initComponents();
        TabelaPesquisarAluno.setModel(ModeloTabelaAluno);
        
        borderText = txtNomeAluno.getBorder(); //SETANDO AS BORDAS NA VARIAVEL
        borderCombo = ComboCorAluno.getBorder();
        ListarTodosAlunosComEndereco();
        esconderColunas();
        GuiasFiliacao.setEnabledAt(2, false);
        
    }
    public static String maxlength(String str,int tam) {
            String valor = "";
            if(str.length() > tam){
	        valor = str.substring(0,tam);
                str = valor;
            }
	    return str;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NecessidadesEspeciais = new javax.swing.ButtonGroup();
        Transporte = new javax.swing.ButtonGroup();
        Filtro = new javax.swing.ButtonGroup();
        Painel_Aluno = new javax.swing.JPanel();
        PainelPesquisar = new javax.swing.JPanel();
        Txt_Buscar_ID = new javax.swing.JTextField();
        Txt_Buscar_ID1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaPesquisarAluno = new javax.swing.JTable();
        lblFiltro = new javax.swing.JLabel();
        jRadioButtonNomeProfessor = new javax.swing.JRadioButton();
        jRadioButtonMatriculaProfessor = new javax.swing.JRadioButton();
        lblFiltro1 = new javax.swing.JLabel();
        JtableAluno = new javax.swing.JTabbedPane();
        Painel_Dados_Cadastrais = new javax.swing.JPanel();
        Painel_Dados_Do_Aluno = new javax.swing.JPanel();
        txtNomeAluno = new javax.swing.JTextField();
        lblNISUSCadastro = new javax.swing.JLabel();
        lblNomeCadastro = new javax.swing.JLabel();
        lblNISCadastro = new javax.swing.JLabel();
        lblDataNascimentoCadastro = new javax.swing.JLabel();
        FtxtDataNascimentoAluno = new javax.swing.JFormattedTextField();
        ComboCorAluno = new javax.swing.JComboBox<>();
        ComboSexoAluno = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        Txt_Buscar_ID = new javax.swing.JTextField();
        Ftxt_Nis_Aluno = new javax.swing.JTextField();
        Txt_Buscar_ID = new javax.swing.JTextField();
        Ftxt_SUS_Cadastro = new javax.swing.JTextField();
        lblNomeCadastro2 = new javax.swing.JLabel();
        Txt_Buscar_ID = new javax.swing.JTextField();
        txt_CertNasc = new javax.swing.JTextField();
        lblNomeCadastro10 = new javax.swing.JLabel();
        Txt_Buscar_ID = new javax.swing.JTextField();
        Ftxt_LivroNasc = new javax.swing.JTextField();
        lblDataNascimentoCadastro1 = new javax.swing.JLabel();
        FtxtDataExpedicao = new javax.swing.JFormattedTextField();
        lblNomeCadastro11 = new javax.swing.JLabel();
        Txt_Buscar_ID = new javax.swing.JTextField();
        Ftxt_FolhaNasc = new javax.swing.JTextField();
        lblNomeCadastro12 = new javax.swing.JLabel();
        Txt_Buscar_ID = new javax.swing.JTextField();
        Ftxt_Cartorio = new javax.swing.JTextField();
        lblNomeCadastro13 = new javax.swing.JLabel();
        Txt_Buscar_ID = new javax.swing.JTextField();
        txtCidadeCartorio = new javax.swing.JTextField();
        lblNomeCadastro14 = new javax.swing.JLabel();
        Txt_Buscar_ID = new javax.swing.JTextField();
        txtUFnasc = new javax.swing.JTextField();
        lblNomeCadastro15 = new javax.swing.JLabel();
        Txt_Buscar_ID = new javax.swing.JTextField();
        txtNaturalidade = new javax.swing.JTextField();
        FtxtCpf = new javax.swing.JFormattedTextField();
        lblNISCadastro1 = new javax.swing.JLabel();
        Painel_tab_Filiacao = new javax.swing.JPanel();
        Painel_Filiacao = new javax.swing.JPanel();
        GuiasFiliacao = new javax.swing.JTabbedPane();
        Painel_Mae_Filiacao = new javax.swing.JPanel();
        lblNome_Mae_Filiacao = new javax.swing.JLabel();
        txtNome_Mae_Filiacao = new javax.swing.JTextField();
        lblNomeCadastro3 = new javax.swing.JLabel();
        lbl_OrgaoExpeditor_Filiacao_mae = new javax.swing.JLabel();
        txt_OrgaoExpeditor_Filiacao_Mae = new javax.swing.JTextField();
        lbl_Profissao_Filiacao_mae = new javax.swing.JLabel();
        txt_Profissao_Filiacao_Mae = new javax.swing.JTextField();
        lbl_Tel_Filiacao_mae = new javax.swing.JLabel();
        Ftxt_Tel_Filiacao_mae = new javax.swing.JFormattedTextField();
        txtRgMAE = new javax.swing.JTextField();
        Painel_Pai_Filiacao = new javax.swing.JPanel();
        lblNome_Mae_Filiacao1 = new javax.swing.JLabel();
        txtNome_Pai_Filiacao = new javax.swing.JTextField();
        lblNomeCadastro8 = new javax.swing.JLabel();
        lbl_OrgaoExpeditor_Filiacao_mae1 = new javax.swing.JLabel();
        txt_OrgaoExpeditor_Filiacao_Pai = new javax.swing.JTextField();
        lbl_Profissao_Filiacao_mae1 = new javax.swing.JLabel();
        txt_Profissao_Filiacao_Pai = new javax.swing.JTextField();
        lbl_Tel_Filiacao_mae1 = new javax.swing.JLabel();
        Ftxt_Tel_Filiacao_pai = new javax.swing.JFormattedTextField();
        CheckPai = new javax.swing.JCheckBox();
        txtRgPAI = new javax.swing.JTextField();
        Painel_Pai_Filiacao1 = new javax.swing.JPanel();
        lblNome_Mae_Filiacao2 = new javax.swing.JLabel();
        txtNome_Mae_Filiacao2 = new javax.swing.JTextField();
        lblNomeCadastro9 = new javax.swing.JLabel();
        lbl_OrgaoExpeditor_Filiacao_mae2 = new javax.swing.JLabel();
        txt_OrgaoExpeditor_Filiacao_Mae2 = new javax.swing.JTextField();
        lbl_Profissao_Filiacao_mae2 = new javax.swing.JLabel();
        txt_Profissao_Filiacao_Mae2 = new javax.swing.JTextField();
        lbl_Tel_Filiacao_mae2 = new javax.swing.JLabel();
        Ftxt_nIdentidade_Filiacao_mae2 = new javax.swing.JFormattedTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        txt_OrgaoExpeditor_Filiacao_Mae5 = new javax.swing.JTextField();
        Painel_Endereco = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtRua = new javax.swing.JTextField();
        lblNomeCadastro1 = new javax.swing.JLabel();
        lblNomeCadastro4 = new javax.swing.JLabel();
        txtNumeroCasa = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        lblNomeCadastro5 = new javax.swing.JLabel();
        lblNomeCadastro6 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        lblNomeCadastro7 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        Painel_Responsaveis = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        RbNecessidadesSim = new javax.swing.JRadioButton();
        RbNecessidadesNao = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextAreaNecessidade = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        RbTransporteNao = new javax.swing.JRadioButton();
        RbTransporteSim = new javax.swing.JRadioButton();
        btnCadastrar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        BtnCancelAluno = new javax.swing.JButton();

        Painel_Aluno.setPreferredSize(new java.awt.Dimension(815, 551));

        PainelPesquisar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisar Aluno", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        Txt_Buscar_ID.setBackground(new java.awt.Color(240, 240, 240));
        Txt_Buscar_ID.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        Txt_Buscar_ID.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Txt_Buscar_ID.setText("Buscar o Aluno Por Nome");
        Txt_Buscar_ID.setToolTipText("");
        Txt_Buscar_ID.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), java.awt.Color.black, java.awt.Color.black, java.awt.Color.white));
        Txt_Buscar_ID.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                Txt_Buscar_IDCaretUpdate(evt);
            }
        });
        Txt_Buscar_ID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Txt_Buscar_IDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Txt_Buscar_IDFocusLost(evt);
            }
        });

        Txt_Buscar_ID1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Txt_Buscar_ID1.setText("AA");

        TabelaPesquisarAluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Matricula", "Nome", "Data_Nascimento", "Sexo"
            }
        ));
        TabelaPesquisarAluno.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TabelaPesquisarAluno.setShowHorizontalLines(false);
        TabelaPesquisarAluno.setShowVerticalLines(false);
        TabelaPesquisarAluno.getTableHeader().setResizingAllowed(false);
        TabelaPesquisarAluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TabelaPesquisarAlunoMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(TabelaPesquisarAluno);

        lblFiltro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFiltro.setText("Filtrar Por:");

        Filtro.add(jRadioButtonNomeProfessor);
        jRadioButtonNomeProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButtonNomeProfessor.setSelected(true);
        jRadioButtonNomeProfessor.setText("Nome");
        jRadioButtonNomeProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNomeProfessorActionPerformed(evt);
            }
        });

        Filtro.add(jRadioButtonMatriculaProfessor);
        jRadioButtonMatriculaProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButtonMatriculaProfessor.setText("Matricula");
        jRadioButtonMatriculaProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMatriculaProfessorActionPerformed(evt);
            }
        });

        lblFiltro1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFiltro1.setText("Selecione um aluno caso queira efetuar alguma alteração no mesmo.");

        javax.swing.GroupLayout PainelPesquisarLayout = new javax.swing.GroupLayout(PainelPesquisar);
        PainelPesquisar.setLayout(PainelPesquisarLayout);
        PainelPesquisarLayout.setHorizontalGroup(
            PainelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPesquisarLayout.createSequentialGroup()
                .addGroup(PainelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelPesquisarLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonNomeProfessor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonMatriculaProfessor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Txt_Buscar_ID1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Txt_Buscar_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(PainelPesquisarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFiltro1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelPesquisarLayout.setVerticalGroup(
            PainelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPesquisarLayout.createSequentialGroup()
                .addGroup(PainelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Txt_Buscar_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PainelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButtonNomeProfessor)
                        .addComponent(jRadioButtonMatriculaProfessor)
                        .addComponent(lblFiltro))
                    .addComponent(Txt_Buscar_ID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFiltro1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JtableAluno.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        JtableAluno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        Painel_Dados_Do_Aluno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DADOS DO ALUNO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        lblNISUSCadastro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNISUSCadastro.setText("SUS :");

        lblNomeCadastro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro.setText("* Nome :");

        lblNISCadastro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNISCadastro.setText("NIS :");

        lblDataNascimentoCadastro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDataNascimentoCadastro.setText("* Data Nascimento :");

        try {
            FtxtDataNascimentoAluno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        FtxtDataNascimentoAluno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        ComboCorAluno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ComboCorAluno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "* Cor ou Raça", "Branca", "Preta", "Parda", "Indígena", "Amarela", "Não desejo Declarar" }));

        ComboSexoAluno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ComboSexoAluno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "* Sexo", "Masculino", "Feminino" }));

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));
        jPanel5.setMaximumSize(new java.awt.Dimension(30, 40));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        Ftxt_Nis_Aluno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Ftxt_Nis_Aluno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Ftxt_Nis_AlunoKeyTyped(evt);
            }
        });

        Ftxt_SUS_Cadastro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Ftxt_SUS_Cadastro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Ftxt_SUS_CadastroKeyTyped(evt);
            }
        });

        lblNomeCadastro2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro2.setText("* Livro :");

        txt_CertNasc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_CertNasc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_CertNascKeyTyped(evt);
            }
        });

        lblNomeCadastro10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro10.setText("* Certidão Nascimento :");

        Ftxt_LivroNasc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblDataNascimentoCadastro1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDataNascimentoCadastro1.setText("* Data Expedição :");

        try {
            FtxtDataExpedicao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        FtxtDataExpedicao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblNomeCadastro11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro11.setText("* Folhas :");

        Ftxt_FolhaNasc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblNomeCadastro12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro12.setText("* Cartório :");

        Ftxt_Cartorio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblNomeCadastro13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro13.setText("* Cidade :");

        txtCidadeCartorio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblNomeCadastro14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro14.setText("* UF :");

        txtUFnasc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblNomeCadastro15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro15.setText("* Naturalidade :");

        txtNaturalidade.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        try {
            FtxtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        FtxtCpf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblNISCadastro1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNISCadastro1.setText("CPF:");

        javax.swing.GroupLayout Painel_Dados_Do_AlunoLayout = new javax.swing.GroupLayout(Painel_Dados_Do_Aluno);
        Painel_Dados_Do_Aluno.setLayout(Painel_Dados_Do_AlunoLayout);
        Painel_Dados_Do_AlunoLayout.setHorizontalGroup(
            Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Dados_Do_AlunoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Painel_Dados_Do_AlunoLayout.createSequentialGroup()
                        .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Painel_Dados_Do_AlunoLayout.createSequentialGroup()
                                .addComponent(lblNISCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Ftxt_Nis_Aluno, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Painel_Dados_Do_AlunoLayout.createSequentialGroup()
                                .addComponent(lblNISUSCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Ftxt_SUS_Cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboSexoAluno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ComboCorAluno, 0, 157, Short.MAX_VALUE)))
                    .addGroup(Painel_Dados_Do_AlunoLayout.createSequentialGroup()
                        .addComponent(lblNomeCadastro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeAluno))
                    .addGroup(Painel_Dados_Do_AlunoLayout.createSequentialGroup()
                        .addComponent(lblNomeCadastro10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_CertNasc))
                    .addGroup(Painel_Dados_Do_AlunoLayout.createSequentialGroup()
                        .addComponent(lblNomeCadastro2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Ftxt_LivroNasc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNomeCadastro11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Ftxt_FolhaNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Painel_Dados_Do_AlunoLayout.createSequentialGroup()
                        .addComponent(lblNomeCadastro13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCidadeCartorio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(Painel_Dados_Do_AlunoLayout.createSequentialGroup()
                            .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Painel_Dados_Do_AlunoLayout.createSequentialGroup()
                                    .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblDataNascimentoCadastro)
                                        .addComponent(lblDataNascimentoCadastro1))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(FtxtDataNascimentoAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(FtxtDataExpedicao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(Painel_Dados_Do_AlunoLayout.createSequentialGroup()
                                    .addComponent(lblNomeCadastro15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNaturalidade))
                                .addGroup(Painel_Dados_Do_AlunoLayout.createSequentialGroup()
                                    .addComponent(lblNISCadastro1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(FtxtCpf)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(Painel_Dados_Do_AlunoLayout.createSequentialGroup()
                            .addComponent(lblNomeCadastro12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Ftxt_Cartorio)))
                    .addGroup(Painel_Dados_Do_AlunoLayout.createSequentialGroup()
                        .addComponent(lblNomeCadastro14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUFnasc, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        Painel_Dados_Do_AlunoLayout.setVerticalGroup(
            Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Dados_Do_AlunoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Painel_Dados_Do_AlunoLayout.createSequentialGroup()
                        .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNomeCadastro)
                            .addComponent(txtNomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FtxtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNISCadastro1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(FtxtDataNascimentoAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDataNascimentoCadastro))
                            .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNISCadastro)
                                .addComponent(ComboSexoAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Ftxt_Nis_Aluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ComboCorAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNomeCadastro15)
                                .addComponent(txtNaturalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNISUSCadastro)
                                .addComponent(Ftxt_SUS_Cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_CertNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNomeCadastro10)
                            .addComponent(FtxtDataExpedicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDataNascimentoCadastro1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeCadastro2)
                    .addComponent(Ftxt_LivroNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeCadastro11)
                    .addComponent(Ftxt_FolhaNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeCadastro12)
                    .addComponent(Ftxt_Cartorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Painel_Dados_Do_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeCadastro13)
                    .addComponent(txtCidadeCartorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeCadastro14)
                    .addComponent(txtUFnasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Painel_Dados_CadastraisLayout = new javax.swing.GroupLayout(Painel_Dados_Cadastrais);
        Painel_Dados_Cadastrais.setLayout(Painel_Dados_CadastraisLayout);
        Painel_Dados_CadastraisLayout.setHorizontalGroup(
            Painel_Dados_CadastraisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Painel_Dados_Do_Aluno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Painel_Dados_CadastraisLayout.setVerticalGroup(
            Painel_Dados_CadastraisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Dados_CadastraisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Painel_Dados_Do_Aluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        JtableAluno.addTab("Dados Cadastrais", new javax.swing.ImageIcon(getClass().getResource("/Icones/user.png")), Painel_Dados_Cadastrais); // NOI18N

        Painel_Filiacao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FILIAÇÃO / RESPONSÁVEL", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        Painel_Filiacao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        GuiasFiliacao.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        GuiasFiliacao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        GuiasFiliacao.setDoubleBuffered(true);

        lblNome_Mae_Filiacao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNome_Mae_Filiacao.setText("*Nome Da Mãe :");

        lblNomeCadastro3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro3.setText("* Nº RG :");

        lbl_OrgaoExpeditor_Filiacao_mae.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_OrgaoExpeditor_Filiacao_mae.setText("* Órgão Expeditor :");

        lbl_Profissao_Filiacao_mae.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_Profissao_Filiacao_mae.setText("Profissão :");

        lbl_Tel_Filiacao_mae.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_Tel_Filiacao_mae.setText("Tel. :");

        try {
            Ftxt_Tel_Filiacao_mae.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)# ########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        Ftxt_Tel_Filiacao_mae.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtRgMAE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRgMAEKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout Painel_Mae_FiliacaoLayout = new javax.swing.GroupLayout(Painel_Mae_Filiacao);
        Painel_Mae_Filiacao.setLayout(Painel_Mae_FiliacaoLayout);
        Painel_Mae_FiliacaoLayout.setHorizontalGroup(
            Painel_Mae_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Mae_FiliacaoLayout.createSequentialGroup()
                .addGroup(Painel_Mae_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Painel_Mae_FiliacaoLayout.createSequentialGroup()
                        .addComponent(lblNome_Mae_Filiacao)
                        .addGap(3, 3, 3))
                    .addComponent(lbl_Profissao_Filiacao_mae, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeCadastro3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(Painel_Mae_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Painel_Mae_FiliacaoLayout.createSequentialGroup()
                        .addComponent(txt_Profissao_Filiacao_Mae, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addComponent(lbl_Tel_Filiacao_mae)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Ftxt_Tel_Filiacao_mae, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNome_Mae_Filiacao, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Painel_Mae_FiliacaoLayout.createSequentialGroup()
                        .addComponent(txtRgMAE, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_OrgaoExpeditor_Filiacao_mae)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_OrgaoExpeditor_Filiacao_Mae, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        Painel_Mae_FiliacaoLayout.setVerticalGroup(
            Painel_Mae_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Mae_FiliacaoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(Painel_Mae_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome_Mae_Filiacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome_Mae_Filiacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Painel_Mae_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeCadastro3)
                    .addComponent(txt_OrgaoExpeditor_Filiacao_Mae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_OrgaoExpeditor_Filiacao_mae)
                    .addComponent(txtRgMAE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Painel_Mae_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Profissao_Filiacao_mae)
                    .addGroup(Painel_Mae_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_Tel_Filiacao_mae)
                        .addComponent(Ftxt_Tel_Filiacao_mae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_Profissao_Filiacao_Mae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        GuiasFiliacao.addTab("Mãe", new javax.swing.ImageIcon(getClass().getResource("/Icones/user_female.png")), Painel_Mae_Filiacao); // NOI18N

        lblNome_Mae_Filiacao1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNome_Mae_Filiacao1.setText("*Nome Do Pai :");

        lblNomeCadastro8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro8.setText("* Nº RG :");

        lbl_OrgaoExpeditor_Filiacao_mae1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_OrgaoExpeditor_Filiacao_mae1.setText("* Órgão Expeditor :");

        lbl_Profissao_Filiacao_mae1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_Profissao_Filiacao_mae1.setText("Profissão :");

        lbl_Tel_Filiacao_mae1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_Tel_Filiacao_mae1.setText("Tel. :");

        try {
            Ftxt_Tel_Filiacao_pai.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)# ########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        Ftxt_Tel_Filiacao_pai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        CheckPai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CheckPai.setText("Pai Desconhecido");
        CheckPai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckPaiActionPerformed(evt);
            }
        });

        txtRgPAI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRgPAIKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout Painel_Pai_FiliacaoLayout = new javax.swing.GroupLayout(Painel_Pai_Filiacao);
        Painel_Pai_Filiacao.setLayout(Painel_Pai_FiliacaoLayout);
        Painel_Pai_FiliacaoLayout.setHorizontalGroup(
            Painel_Pai_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Pai_FiliacaoLayout.createSequentialGroup()
                .addGroup(Painel_Pai_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CheckPai)
                    .addGroup(Painel_Pai_FiliacaoLayout.createSequentialGroup()
                        .addGroup(Painel_Pai_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Painel_Pai_FiliacaoLayout.createSequentialGroup()
                                .addComponent(lblNome_Mae_Filiacao1)
                                .addGap(3, 3, 3))
                            .addComponent(lbl_Profissao_Filiacao_mae1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNomeCadastro8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(Painel_Pai_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(Painel_Pai_FiliacaoLayout.createSequentialGroup()
                                .addComponent(txt_Profissao_Filiacao_Pai, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                                .addComponent(lbl_Tel_Filiacao_mae1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Ftxt_Tel_Filiacao_pai, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNome_Pai_Filiacao, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Painel_Pai_FiliacaoLayout.createSequentialGroup()
                                .addComponent(txtRgPAI, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_OrgaoExpeditor_Filiacao_mae1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_OrgaoExpeditor_Filiacao_Pai, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        Painel_Pai_FiliacaoLayout.setVerticalGroup(
            Painel_Pai_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Pai_FiliacaoLayout.createSequentialGroup()
                .addComponent(CheckPai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Painel_Pai_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome_Pai_Filiacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome_Mae_Filiacao1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Painel_Pai_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeCadastro8)
                    .addComponent(txt_OrgaoExpeditor_Filiacao_Pai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_OrgaoExpeditor_Filiacao_mae1)
                    .addComponent(txtRgPAI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Painel_Pai_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Profissao_Filiacao_mae1)
                    .addGroup(Painel_Pai_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_Tel_Filiacao_mae1)
                        .addComponent(Ftxt_Tel_Filiacao_pai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_Profissao_Filiacao_Pai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        GuiasFiliacao.addTab("Pai", new javax.swing.ImageIcon(getClass().getResource("/Icones/user.png")), Painel_Pai_Filiacao); // NOI18N

        lblNome_Mae_Filiacao2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNome_Mae_Filiacao2.setText("*Nome :");

        lblNomeCadastro9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro9.setText("* Nº RG :");

        lbl_OrgaoExpeditor_Filiacao_mae2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_OrgaoExpeditor_Filiacao_mae2.setText("* Órgão Expeditor :");

        lbl_Profissao_Filiacao_mae2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_Profissao_Filiacao_mae2.setText("Profissão :");

        lbl_Tel_Filiacao_mae2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_Tel_Filiacao_mae2.setText("Tel. :");

        try {
            Ftxt_nIdentidade_Filiacao_mae2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)# ########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        Ftxt_nIdentidade_Filiacao_mae2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "* Sexo", "Masculino", "Feminino" }));

        javax.swing.GroupLayout Painel_Pai_Filiacao1Layout = new javax.swing.GroupLayout(Painel_Pai_Filiacao1);
        Painel_Pai_Filiacao1.setLayout(Painel_Pai_Filiacao1Layout);
        Painel_Pai_Filiacao1Layout.setHorizontalGroup(
            Painel_Pai_Filiacao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Pai_Filiacao1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Painel_Pai_Filiacao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Painel_Pai_Filiacao1Layout.createSequentialGroup()
                        .addGroup(Painel_Pai_Filiacao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Profissao_Filiacao_mae2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNomeCadastro9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Painel_Pai_Filiacao1Layout.createSequentialGroup()
                        .addComponent(lblNome_Mae_Filiacao2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(Painel_Pai_Filiacao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Painel_Pai_Filiacao1Layout.createSequentialGroup()
                        .addComponent(txt_Profissao_Filiacao_Mae2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_Tel_Filiacao_mae2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Ftxt_nIdentidade_Filiacao_mae2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNome_Mae_Filiacao2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Painel_Pai_Filiacao1Layout.createSequentialGroup()
                        .addComponent(txt_OrgaoExpeditor_Filiacao_Mae5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_OrgaoExpeditor_Filiacao_mae2)
                        .addGap(5, 5, 5)
                        .addComponent(txt_OrgaoExpeditor_Filiacao_Mae2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        Painel_Pai_Filiacao1Layout.setVerticalGroup(
            Painel_Pai_Filiacao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Pai_Filiacao1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(Painel_Pai_Filiacao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome_Mae_Filiacao2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome_Mae_Filiacao2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Painel_Pai_Filiacao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Painel_Pai_Filiacao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNomeCadastro9)
                        .addComponent(txt_OrgaoExpeditor_Filiacao_Mae2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_OrgaoExpeditor_Filiacao_Mae5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_OrgaoExpeditor_Filiacao_mae2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Painel_Pai_Filiacao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Profissao_Filiacao_mae2)
                    .addGroup(Painel_Pai_Filiacao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_Tel_Filiacao_mae2)
                        .addComponent(Ftxt_nIdentidade_Filiacao_mae2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_Profissao_Filiacao_Mae2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        GuiasFiliacao.addTab("Outros", new javax.swing.ImageIcon(getClass().getResource("/Icones/user_green.png")), Painel_Pai_Filiacao1); // NOI18N

        javax.swing.GroupLayout Painel_FiliacaoLayout = new javax.swing.GroupLayout(Painel_Filiacao);
        Painel_Filiacao.setLayout(Painel_FiliacaoLayout);
        Painel_FiliacaoLayout.setHorizontalGroup(
            Painel_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_FiliacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GuiasFiliacao)
                .addContainerGap())
        );
        Painel_FiliacaoLayout.setVerticalGroup(
            Painel_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_FiliacaoLayout.createSequentialGroup()
                .addComponent(GuiasFiliacao, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 102, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Painel_tab_FiliacaoLayout = new javax.swing.GroupLayout(Painel_tab_Filiacao);
        Painel_tab_Filiacao.setLayout(Painel_tab_FiliacaoLayout);
        Painel_tab_FiliacaoLayout.setHorizontalGroup(
            Painel_tab_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_tab_FiliacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Painel_Filiacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        Painel_tab_FiliacaoLayout.setVerticalGroup(
            Painel_tab_FiliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_tab_FiliacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Painel_Filiacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JtableAluno.addTab("Filiação / Responsável", new javax.swing.ImageIcon(getClass().getResource("/Icones/group.png")), Painel_tab_Filiacao); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ENDEREÇO DO ALUNO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        lblNomeCadastro1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro1.setText("* Rua :");

        lblNomeCadastro4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro4.setText("* Numero :");

        txtNumeroCasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroCasaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroCasaKeyTyped(evt);
            }
        });

        lblNomeCadastro5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro5.setText("* Bairro:");

        lblNomeCadastro6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro6.setText("* Cidade:");

        lblNomeCadastro7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro7.setText("* Estado:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblNomeCadastro7)
                        .addComponent(lblNomeCadastro6)
                        .addComponent(lblNomeCadastro5, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(lblNomeCadastro1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblNomeCadastro4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNumeroCasa))
                        .addComponent(txtRua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 219, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeCadastro1)
                    .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeCadastro5)
                    .addComponent(lblNomeCadastro4)
                    .addComponent(txtNumeroCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeCadastro6)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeCadastro7))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Painel_EnderecoLayout = new javax.swing.GroupLayout(Painel_Endereco);
        Painel_Endereco.setLayout(Painel_EnderecoLayout);
        Painel_EnderecoLayout.setHorizontalGroup(
            Painel_EnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Painel_EnderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        Painel_EnderecoLayout.setVerticalGroup(
            Painel_EnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_EnderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        JtableAluno.addTab("Dados Residenciais", new javax.swing.ImageIcon(getClass().getResource("/Icones/house.png")), Painel_Endereco); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Possui Necessidades Especiais", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        NecessidadesEspeciais.add(RbNecessidadesSim);
        RbNecessidadesSim.setText("Sim");
        RbNecessidadesSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbNecessidadesSimActionPerformed(evt);
            }
        });

        NecessidadesEspeciais.add(RbNecessidadesNao);
        RbNecessidadesNao.setSelected(true);
        RbNecessidadesNao.setText("Não");
        RbNecessidadesNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbNecessidadesNaoActionPerformed(evt);
            }
        });

        TextAreaNecessidade.setColumns(20);
        TextAreaNecessidade.setRows(5);
        TextAreaNecessidade.setEnabled(false);
        jScrollPane2.setViewportView(TextAreaNecessidade);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Se Sim, Informe Qual é");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RbNecessidadesNao)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(RbNecessidadesSim)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(194, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RbNecessidadesSim)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
                .addComponent(RbNecessidadesNao)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Possui Transporte", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        Transporte.add(RbTransporteNao);
        RbTransporteNao.setSelected(true);
        RbTransporteNao.setText("Não");
        RbTransporteNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbTransporteNaoActionPerformed(evt);
            }
        });

        Transporte.add(RbTransporteSim);
        RbTransporteSim.setText("Sim");
        RbTransporteSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbTransporteSimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RbTransporteSim)
                    .addComponent(RbTransporteNao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(RbTransporteSim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RbTransporteNao))
        );

        javax.swing.GroupLayout Painel_ResponsaveisLayout = new javax.swing.GroupLayout(Painel_Responsaveis);
        Painel_Responsaveis.setLayout(Painel_ResponsaveisLayout);
        Painel_ResponsaveisLayout.setHorizontalGroup(
            Painel_ResponsaveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_ResponsaveisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Painel_ResponsaveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        Painel_ResponsaveisLayout.setVerticalGroup(
            Painel_ResponsaveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_ResponsaveisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JtableAluno.addTab("Informações Adicionais", new javax.swing.ImageIcon(getClass().getResource("/Icones/information.png")), Painel_Responsaveis); // NOI18N

        btnCadastrar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/disk.png"))); // NOI18N
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnAtualizar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/pencil_1.png"))); // NOI18N
        btnAtualizar.setText("Atualizar");
        btnAtualizar.setEnabled(false);
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        BtnCancelAluno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnCancelAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/cancel.png"))); // NOI18N
        BtnCancelAluno.setText("Cancelar");
        BtnCancelAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelAlunoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Painel_AlunoLayout = new javax.swing.GroupLayout(Painel_Aluno);
        Painel_Aluno.setLayout(Painel_AlunoLayout);
        Painel_AlunoLayout.setHorizontalGroup(
            Painel_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Painel_AlunoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAtualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCadastrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnCancelAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(JtableAluno)
        );
        Painel_AlunoLayout.setVerticalGroup(
            Painel_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_AlunoLayout.createSequentialGroup()
                .addComponent(PainelPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JtableAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Painel_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCancelAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Painel_Aluno, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Painel_Aluno, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
 int dialogResult = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja salvar?");
        if (dialogResult == JOptionPane.YES_OPTION) {
            //VERIFICA CAMPOS DO ALUNO
            if (VerificaCampoVazioDoPainelDadosAluno()) {

                //VERIFICA CAMPOS DA MAE
                if (VerificaCampoVazioDoPainelMae()) {

                    if (CheckPai.isSelected() == false) {
                        if (VerificaCampoVazioDoPainelPai()) {
                            if (VerificaCampoVazioDoPainelEndereco()) {
                                Inserir_Aluno_Com_Mae_Pai_e_Endereco();
                                ListarTodosAlunosComEndereco();
                                LimparCampos();
                                JOptionPane.showMessageDialog(null, "Aluno Cadastrado com Sucesso !");
                            }
                        }
                    } else {
                        if (VerificaCampoVazioDoPainelEndereco()) {
                            try {
                                Inserir_Aluno_Com_Mae_Pai_e_Endereco();
                                ListarTodosAlunosComEndereco();
                                LimparCampos();
                                JOptionPane.showMessageDialog(null, "Aluno Cadastrado com Sucesso !");
                            } catch (Exception e) {
                            }

                        }
                    }
                }
            }
        }

        
    }//GEN-LAST:event_btnCadastrarActionPerformed
    
    String RbNecessidadeEspecial = "nao";//variavel para controle do radioButtonNecessidadeEspecial
    
    private void RbNecessidadesSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbNecessidadesSimActionPerformed
       RbNecessidadeEspecial = "sim";
       TextAreaNecessidade.setEnabled(true);
    }//GEN-LAST:event_RbNecessidadesSimActionPerformed

    private void RbNecessidadesNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbNecessidadesNaoActionPerformed
        RbNecessidadeEspecial = "nao";
        TextAreaNecessidade.setEnabled(false);
        TextAreaNecessidade.setText("");
    }//GEN-LAST:event_RbNecessidadesNaoActionPerformed
    
    String RbTransporte = "nao";//variavel de controle do radio button Transporte
    private void RbTransporteSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbTransporteSimActionPerformed
        RbTransporte = "sim";
    }//GEN-LAST:event_RbTransporteSimActionPerformed

    private void RbTransporteNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbTransporteNaoActionPerformed
        RbTransporte = "nao";
    }//GEN-LAST:event_RbTransporteNaoActionPerformed

    private void TabelaPesquisarAlunoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaPesquisarAlunoMousePressed
        SetarCampos();
        restaurarBordas();
        btnCadastrar.setEnabled(false);
        btnAtualizar.setEnabled(true);
    }//GEN-LAST:event_TabelaPesquisarAlunoMousePressed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
    int dialogResult = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja salvar as alterações?");
        if (dialogResult == JOptionPane.YES_OPTION) {
            // VerificaCamposVazio Do Aluno
            if (VerificaCampoVazioDoPainelDadosAluno()) {

                //VERIFICA CAMPOS DA MAE
                if (VerificaCampoVazioDoPainelMae()) {

                    if (CheckPai.isSelected() == false) {
                        if (VerificaCampoVazioDoPainelPai()) {
                            //VerificaCamposVazio Do Endereco
                            if (VerificaCampoVazioDoPainelEndereco()) {
                                Atualizar_Aluno_Com_Mae_Pai_e_Endereco();
                                ListarTodosAlunosComEndereco();
                                LimparCampos();
                                restaurarBordas();
                                JOptionPane.showMessageDialog(null, "Alterações Efetuadas Com Sucesso !");
                                btnCadastrar.setEnabled(true);
                                btnAtualizar.setEnabled(false);
                            }
                        }
                    } else {
                        //VerificaCamposVazio Do Endereco
                        if (VerificaCampoVazioDoPainelEndereco()) {
                            Atualizar_Aluno_Com_Mae_Pai_e_Endereco();
                            ListarTodosAlunosComEndereco();
                            LimparCampos();
                            restaurarBordas();
                            JOptionPane.showMessageDialog(null, "Alterações Efetuadas Com Sucesso !");
                            btnCadastrar.setEnabled(true);
                            btnAtualizar.setEnabled(false);
                        }
                    }
                }
            }
        }

        
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void BtnCancelAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelAlunoActionPerformed
        // botao cancelar
        LimparCampos();
        restaurarBordas();
        ListarTodosAlunosComEndereco();
        btnCadastrar.setEnabled(true);
        btnAtualizar.setEnabled(false);
    }//GEN-LAST:event_BtnCancelAlunoActionPerformed

    private void Txt_Buscar_IDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Txt_Buscar_IDFocusGained
        if (RB == "nome") {
            if (Txt_Buscar_ID.getText().equals("Buscar o Aluno Por Nome")) {
                Txt_Buscar_ID.setText("");
            }
        } else if (RB == "matricula") {
            if (Txt_Buscar_ID.getText().equals("Buscar o Aluno Por Matricula")) {
                Txt_Buscar_ID.setText("");
            }
        }
        
    }//GEN-LAST:event_Txt_Buscar_IDFocusGained

    private void Txt_Buscar_IDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Txt_Buscar_IDFocusLost
       if  (Txt_Buscar_ID.getText().equals("")) {
           if(RB == "nome"){
                Txt_Buscar_ID.setText("Buscar o Aluno Por Nome");
                ListarTodosAlunosComEndereco(); 
           }else if(RB == "matricula"){
               Txt_Buscar_ID.setText("Buscar o Aluno Por Matricula");
                ListarTodosAlunosComEndereco();
           }
           
       }
    }//GEN-LAST:event_Txt_Buscar_IDFocusLost

    private void Txt_Buscar_IDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_Txt_Buscar_IDCaretUpdate
        if(RB == "nome"){
            ListarAlunosPeloNomeComLike(Txt_Buscar_ID.getText());
        }else if (RB == "matricula"){
            
            ListarAlunosPorMatComLike(Txt_Buscar_ID.getText());
        }
    }//GEN-LAST:event_Txt_Buscar_IDCaretUpdate

    private void Ftxt_Nis_AlunoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ftxt_Nis_AlunoKeyTyped
        String n = "1234567890";
        if (!n.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_Ftxt_Nis_AlunoKeyTyped

    private void Ftxt_SUS_CadastroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ftxt_SUS_CadastroKeyTyped
       String n = "1234567890";
        if (!n.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_Ftxt_SUS_CadastroKeyTyped

    private void txtNumeroCasaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroCasaKeyTyped
        String n = "1234567890";
        if (!n.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNumeroCasaKeyTyped

    private void txtNumeroCasaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroCasaKeyReleased
        txtNumeroCasa.setText(maxlength(txtNumeroCasa.getText(),8));
    }//GEN-LAST:event_txtNumeroCasaKeyReleased

    private void CheckPaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckPaiActionPerformed
        boolean check = (!CheckPai.isSelected());
        txtNome_Pai_Filiacao.setEnabled(check);
        txtRgPAI.setEnabled(check);
        txt_OrgaoExpeditor_Filiacao_Pai.setEnabled(check);
        txt_Profissao_Filiacao_Pai.setEnabled(check);
        Ftxt_Tel_Filiacao_pai.setEnabled(check);
        
        txtNome_Pai_Filiacao.setBorder(borderText);
        txtRgPAI.setBorder(borderText);
        txt_OrgaoExpeditor_Filiacao_Pai.setBorder(borderText);
        
        
        if(!check){
           txtNome_Pai_Filiacao.setText("");
           txtRgPAI.setText("");
           txt_OrgaoExpeditor_Filiacao_Pai.setText("");
           txt_Profissao_Filiacao_Pai.setText("");
           Ftxt_Tel_Filiacao_pai.setText("");
        }

        
    }//GEN-LAST:event_CheckPaiActionPerformed

    private void txtRgMAEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRgMAEKeyTyped
        String n = "1234567890";
        if (!n.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtRgMAEKeyTyped

    private void txtRgPAIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRgPAIKeyTyped
        String n = "1234567890";
        if (!n.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtRgPAIKeyTyped
    String RB = "nome";
    private void jRadioButtonMatriculaProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMatriculaProfessorActionPerformed
        RB = "matricula";
        Txt_Buscar_ID.setText("Buscar o Aluno Por Matricula");
        ListarTodosAlunosComEndereco();
    }//GEN-LAST:event_jRadioButtonMatriculaProfessorActionPerformed

    private void jRadioButtonNomeProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNomeProfessorActionPerformed
        RB = "nome";
        Txt_Buscar_ID.setText("Buscar o Aluno Por Nome");
        ListarTodosAlunosComEndereco();
    }//GEN-LAST:event_jRadioButtonNomeProfessorActionPerformed

    private void txt_CertNascKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CertNascKeyTyped
        String n = "1234567890";
        if (!n.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_CertNascKeyTyped
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancelAluno;
    private javax.swing.JCheckBox CheckPai;
    private javax.swing.JComboBox<String> ComboCorAluno;
    private javax.swing.JComboBox<String> ComboSexoAluno;
    private javax.swing.ButtonGroup Filtro;
    private javax.swing.JFormattedTextField FtxtCpf;
    private javax.swing.JFormattedTextField FtxtDataExpedicao;
    private javax.swing.JFormattedTextField FtxtDataNascimentoAluno;
    private javax.swing.JTextField Ftxt_Cartorio;
    private javax.swing.JTextField Ftxt_FolhaNasc;
    private javax.swing.JTextField Ftxt_LivroNasc;
    private javax.swing.JTextField Ftxt_Nis_Aluno;
    private javax.swing.JTextField Ftxt_SUS_Cadastro;
    private javax.swing.JFormattedTextField Ftxt_Tel_Filiacao_mae;
    private javax.swing.JFormattedTextField Ftxt_Tel_Filiacao_pai;
    private javax.swing.JFormattedTextField Ftxt_nIdentidade_Filiacao_mae2;
    private javax.swing.JTabbedPane GuiasFiliacao;
    private javax.swing.JTabbedPane JtableAluno;
    private javax.swing.ButtonGroup NecessidadesEspeciais;
    private javax.swing.JPanel PainelPesquisar;
    private javax.swing.JPanel Painel_Aluno;
    private javax.swing.JPanel Painel_Dados_Cadastrais;
    private javax.swing.JPanel Painel_Dados_Do_Aluno;
    private javax.swing.JPanel Painel_Endereco;
    private javax.swing.JPanel Painel_Filiacao;
    private javax.swing.JPanel Painel_Mae_Filiacao;
    private javax.swing.JPanel Painel_Pai_Filiacao;
    private javax.swing.JPanel Painel_Pai_Filiacao1;
    private javax.swing.JPanel Painel_Responsaveis;
    private javax.swing.JPanel Painel_tab_Filiacao;
    private javax.swing.JRadioButton RbNecessidadesNao;
    private javax.swing.JRadioButton RbNecessidadesSim;
    private javax.swing.JRadioButton RbTransporteNao;
    private javax.swing.JRadioButton RbTransporteSim;
    private javax.swing.JTable TabelaPesquisarAluno;
    private javax.swing.JTextArea TextAreaNecessidade;
    private javax.swing.ButtonGroup Transporte;
    private javax.swing.JTextField Txt_Buscar_ID;
    private javax.swing.JTextField Txt_Buscar_ID1;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButtonMatriculaProfessor;
    private javax.swing.JRadioButton jRadioButtonNomeProfessor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDataNascimentoCadastro;
    private javax.swing.JLabel lblDataNascimentoCadastro1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblFiltro1;
    private javax.swing.JLabel lblNISCadastro;
    private javax.swing.JLabel lblNISCadastro1;
    private javax.swing.JLabel lblNISUSCadastro;
    private javax.swing.JLabel lblNomeCadastro;
    private javax.swing.JLabel lblNomeCadastro1;
    private javax.swing.JLabel lblNomeCadastro10;
    private javax.swing.JLabel lblNomeCadastro11;
    private javax.swing.JLabel lblNomeCadastro12;
    private javax.swing.JLabel lblNomeCadastro13;
    private javax.swing.JLabel lblNomeCadastro14;
    private javax.swing.JLabel lblNomeCadastro15;
    private javax.swing.JLabel lblNomeCadastro2;
    private javax.swing.JLabel lblNomeCadastro3;
    private javax.swing.JLabel lblNomeCadastro4;
    private javax.swing.JLabel lblNomeCadastro5;
    private javax.swing.JLabel lblNomeCadastro6;
    private javax.swing.JLabel lblNomeCadastro7;
    private javax.swing.JLabel lblNomeCadastro8;
    private javax.swing.JLabel lblNomeCadastro9;
    private javax.swing.JLabel lblNome_Mae_Filiacao;
    private javax.swing.JLabel lblNome_Mae_Filiacao1;
    private javax.swing.JLabel lblNome_Mae_Filiacao2;
    private javax.swing.JLabel lbl_OrgaoExpeditor_Filiacao_mae;
    private javax.swing.JLabel lbl_OrgaoExpeditor_Filiacao_mae1;
    private javax.swing.JLabel lbl_OrgaoExpeditor_Filiacao_mae2;
    private javax.swing.JLabel lbl_Profissao_Filiacao_mae;
    private javax.swing.JLabel lbl_Profissao_Filiacao_mae1;
    private javax.swing.JLabel lbl_Profissao_Filiacao_mae2;
    private javax.swing.JLabel lbl_Tel_Filiacao_mae;
    private javax.swing.JLabel lbl_Tel_Filiacao_mae1;
    private javax.swing.JLabel lbl_Tel_Filiacao_mae2;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCidadeCartorio;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtNaturalidade;
    private javax.swing.JTextField txtNomeAluno;
    private javax.swing.JTextField txtNome_Mae_Filiacao;
    private javax.swing.JTextField txtNome_Mae_Filiacao2;
    private javax.swing.JTextField txtNome_Pai_Filiacao;
    private javax.swing.JTextField txtNumeroCasa;
    private javax.swing.JTextField txtRgMAE;
    private javax.swing.JTextField txtRgPAI;
    private javax.swing.JTextField txtRua;
    private javax.swing.JTextField txtUFnasc;
    private javax.swing.JTextField txt_CertNasc;
    private javax.swing.JTextField txt_OrgaoExpeditor_Filiacao_Mae;
    private javax.swing.JTextField txt_OrgaoExpeditor_Filiacao_Mae2;
    private javax.swing.JTextField txt_OrgaoExpeditor_Filiacao_Mae5;
    private javax.swing.JTextField txt_OrgaoExpeditor_Filiacao_Pai;
    private javax.swing.JTextField txt_Profissao_Filiacao_Mae;
    private javax.swing.JTextField txt_Profissao_Filiacao_Mae2;
    private javax.swing.JTextField txt_Profissao_Filiacao_Pai;
    // End of variables declaration//GEN-END:variables
}
