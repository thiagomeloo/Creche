package Telas;

import Entidades.Professor;
import java.awt.Color;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.ParseConversionEvent;


public class Gerenciar_Turma extends javax.swing.JInternalFrame {

    public void ListarTurmas() {
        DAO.TurmaDAO Tdao = new DAO.TurmaDAO();
        List<Entidades.Turma> listaTurma = Tdao.consultarTodsAsTurmas();
        ModeloTabelaTurma.setNumRows(0);
        
        for (Entidades.Turma a : listaTurma) {
            String nomeProf = "";
            try {
               if (a.getIdProfessor().getNome() != null) {
                nomeProf = a.getIdProfessor().getNome();
                } 
            } catch (Exception e) {
                nomeProf = "";
            }
            
            ModeloTabelaTurma.addRow(new Object[]{
            a.getNome(),a.getQuantidadeMaxima(),a.getAnoLetivo(),a.getTurno(),
                a.getFaixaEtaria(),nomeProf,a.getIdTurma()
               
            });
        }
        TabelaTurma.getColumnModel().getColumn(6).setMinWidth(0);
        TabelaTurma.getColumnModel().getColumn(6).setMaxWidth(0);
    }
    
    public void GerarComboProfessor(){
        Entidades.Professor pro = new Entidades.Professor();
        ComboProf.removeAllItems();
        pro.setNome("Professor(a)");
        ComboProf.addItem(pro);
        DAO.ProfessorDAO Pdao = new DAO.ProfessorDAO();
        for(Entidades.Professor p : Pdao.consultarTodosAtivo()){
            
           ComboProf.addItem(p);
        }
    }
    
    public void GerarComboBoxAnoLetivo(){

        Calendar c = Calendar.getInstance();

        c.add(Calendar.YEAR, 0);

        int a = 1;
        while (a <= 11) {
            String b = String.valueOf(c.getWeekYear());
            ComboAnoLetivo.addItem(b);
            c.add(Calendar.YEAR, 1);
            a++;
        }
    }
    
    public void AtualizarComboBoxAnoLetivo(String entrada){

        for (int i = 0; i < ComboAnoLetivo.getItemCount(); i++) {
                ComboAnoLetivo.removeAllItems();
                ComboAnoLetivo.addItem("* Ano Letivo");
                if(entrada != null){
                  ComboAnoLetivo.addItem(entrada);  
                }
                GerarComboBoxAnoLetivo();
        }
        
        
    }
    
    public void SetarCampos() {
        
        IdTurma = Long.decode(TabelaTurma.getValueAt(TabelaTurma.getSelectedRow(), 6).toString());
        txtNomeTurma.setText(TabelaTurma.getValueAt(TabelaTurma.getSelectedRow(), 0).toString());
        txtFaixaEtariaTurma.setText(TabelaTurma.getValueAt(TabelaTurma.getSelectedRow(), 4).toString());
        txtQtdMax.setText(TabelaTurma.getValueAt(TabelaTurma.getSelectedRow(), 1).toString());
        ComboTurno.setSelectedItem(TabelaTurma.getValueAt(TabelaTurma.getSelectedRow(), 3).toString());
        String anoVindoTabela = TabelaTurma.getValueAt(TabelaTurma.getSelectedRow(), 2).toString();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR,0);
        String atual = String.valueOf(c.getWeekYear());
        if(anoVindoTabela.equals(atual)){
             ComboAnoLetivo.setSelectedItem(anoVindoTabela);
        }else{
            AtualizarComboBoxAnoLetivo(TabelaTurma.getValueAt(TabelaTurma.getSelectedRow(), 2).toString());
            ComboAnoLetivo.setSelectedItem(TabelaTurma.getValueAt(TabelaTurma.getSelectedRow(), 2).toString());
        }

        
       
    }
    
    public void limparCampos(){
        
        txtNomeTurma.setText("");
        txtFaixaEtariaTurma.setText("");
        txtQtdMax.setText("");
        ComboAnoLetivo.setSelectedIndex(0);
        ComboTurno.setSelectedIndex(0);
        ComboProf.setSelectedIndex(0);
        ComboAnoLetivo.removeAllItems();
        ComboAnoLetivo.addItem("* Ano Letivo");
        GerarComboBoxAnoLetivo();
        
    }
    
    public boolean VerificaCampoVazio(){
        boolean retorno = true;
        if("".equals(txtNomeTurma.getText())){
            txtNomeTurma.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txtNomeTurma.setBorder(new LineBorder(Color.green, 1));  
        }
        if("".equals(txtFaixaEtariaTurma.getText())){
            txtFaixaEtariaTurma.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txtFaixaEtariaTurma.setBorder(new LineBorder(Color.green, 1));  
        }
        if("".equals(txtQtdMax.getText())){
            txtQtdMax.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txtQtdMax.setBorder(new LineBorder(Color.green, 1));  
        }
        
        if(ComboAnoLetivo.getSelectedIndex() == 0){
            ComboAnoLetivo.setBorder(new LineBorder(Color.red));
            retorno = false;
        }else{
            ComboAnoLetivo.setBorder(new LineBorder(Color.green));
        }
        if(ComboTurno.getSelectedIndex() == 0){
            ComboTurno.setBorder(new LineBorder(Color.red));
            retorno = false;
        }else{
            ComboTurno.setBorder(new LineBorder(Color.green));
        }
        if(retorno == true){
           restaurarBorda();
        }else{
            JOptionPane.showMessageDialog(null, "Por Favor Preencha Todos Os Campos Obrigatorios!");
        }
        
       return retorno; 
    }
    
    public void restaurarBorda(){
        txtNomeTurma.setBorder(borderText);
        txtFaixaEtariaTurma.setBorder(borderText);
        txtQtdMax.setBorder(borderText);
        ComboAnoLetivo.setBorder(borderCombo);
        ComboTurno.setBorder(borderCombo);
        ComboProf.setBorder(borderCombo);
    }
    
    public void InserirTurma(){
        
        //Instancia Os Objetos Turma e Turma DAO
        Entidades.Turma t = new Entidades.Turma();
        DAO.TurmaDAO tDao = new DAO.TurmaDAO();
        if(ComboProf.getSelectedIndex() == 0){
            t.setIdProfessor(null);
        }else{
            t.setIdProfessor((Professor) ComboProf.getSelectedItem());
        }
        t.setNome(txtNomeTurma.getText());
        t.setTurno(ComboTurno.getSelectedItem().toString());
        t.setFaixaEtaria(txtFaixaEtariaTurma.getText());
        t.setQuantidadeMaxima(Integer.parseInt(txtQtdMax.getText()));
        t.setAnoLetivo((String) ComboAnoLetivo.getSelectedItem());
        tDao.salvar(t); 
    }
    
    private long IdTurma;
    public void AtualizarTurma(){
        DAO.AlunoDAO aldao = new DAO.AlunoDAO();
        long qtd = aldao.contarAlunosDeUmaTurma(IdTurma);
        long valorNovo = Integer.parseInt(txtQtdMax.getText());
        if (qtd > valorNovo) {
            JOptionPane.showMessageDialog(null, "Insira um valor menor do que a quantidade de alunos ja existentes na turma.");
        } else {
            //Instancia Os Objetos Turma e Turma DAO
            Entidades.Turma t = new Entidades.Turma();
            DAO.TurmaDAO tDao = new DAO.TurmaDAO();
            t.setIdTurma(IdTurma);
            t.setNome(txtNomeTurma.getText());
            t.setTurno(ComboTurno.getSelectedItem().toString());
            t.setFaixaEtaria(txtFaixaEtariaTurma.getText());
            t.setQuantidadeMaxima(Integer.parseInt(txtQtdMax.getText()));
            t.setAnoLetivo((String) ComboAnoLetivo.getSelectedItem());
            tDao.salvar(t);
            JOptionPane.showMessageDialog(null, "Alterações Efetuadas Com Sucesso !");
            ListarTurmas();
            limparCampos();
        }

    }
    
    public void RemoverTurma(long idTurma){
        DAO.AlunoDAO alDAO = new DAO.AlunoDAO();
        List<Entidades.Aluno> listaAluno = alDAO.consultaAlunosDeUmaTurma(idTurma);
        for(Entidades.Aluno a : listaAluno){
            Entidades.Aluno aluno  = alDAO.consultaAlunoPorMatricula(a.getMatricula());
            aluno.setTurma_idTurma(null);
            alDAO.atualizar(aluno);
        }
        
        
        
        //Instancia Os Objetos Turma e Turma DAO
        Entidades.Turma t = new Entidades.Turma();
        DAO.TurmaDAO tDao = new DAO.TurmaDAO();
        t.setIdTurma(IdTurma);
        t.setNome(txtNomeTurma.getText());
        t.setTurno(ComboTurno.getSelectedItem().toString());
        t.setFaixaEtaria(txtFaixaEtariaTurma.getText());
        t.setQuantidadeMaxima(Integer.parseInt(txtQtdMax.getText()));
        t.setAnoLetivo((String) ComboAnoLetivo.getSelectedItem());
        tDao.Deletar(t); 
    }
    
    //Modelo da Tabela Aluno
    DefaultTableModel ModeloTabelaTurma = new DefaultTableModel(
            new Object[]{"Nome", "Quantidade_Max", "AnoLetivo", "Turno", "Faixa_Etaria", "Professor","ID"
            }, 0) {
        boolean[] canEdit = new boolean[]{false, false, false, false, false, false,false};

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    };

    
    Border borderText;
    Border borderCombo;
    
    public Gerenciar_Turma() {
        initComponents();
        borderText = txtNomeTurma.getBorder();
        borderCombo = ComboAnoLetivo.getBorder();
        GerarComboProfessor();
        GerarComboBoxAnoLetivo();
        TabelaTurma.setModel(ModeloTabelaTurma);
        ListarTurmas();
        
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Painel_Professor = new javax.swing.JPanel();
        PainelPesquisar1 = new javax.swing.JPanel();
        txtFaixaEtariaTurma = new javax.swing.JTextField();
        lblNomeCadastro = new javax.swing.JLabel();
        lblNomeCadastro1 = new javax.swing.JLabel();
        txtNomeTurma = new javax.swing.JTextField();
        txtQtdMax = new javax.swing.JTextField();
        lblNomeCadastro2 = new javax.swing.JLabel();
        ComboTurno = new javax.swing.JComboBox<>();
        ComboProf = new javax.swing.JComboBox<>();
        ComboAnoLetivo = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabelaTurma = new javax.swing.JTable();
        btnAtualizar = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();
        BtnCancelAluno = new javax.swing.JButton();
        BtnRemover = new javax.swing.JButton();

        Painel_Professor.setPreferredSize(new java.awt.Dimension(815, 551));
        Painel_Professor.setRequestFocusEnabled(false);

        PainelPesquisar1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CADASTRAR / ATUALIZAR TURMA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        lblNomeCadastro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro.setText("* Faixa Etária:");

        lblNomeCadastro1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro1.setText("* Nome:");

        txtQtdMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQtdMaxKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQtdMaxKeyTyped(evt);
            }
        });

        lblNomeCadastro2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro2.setText("* Qtd Max:");

        ComboTurno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ComboTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "* Turno", "Matutino", "Vespertino", "" }));

        ComboProf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ComboProf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Professor" }));

        ComboAnoLetivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ComboAnoLetivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "* Ano Letivo" }));

        javax.swing.GroupLayout PainelPesquisar1Layout = new javax.swing.GroupLayout(PainelPesquisar1);
        PainelPesquisar1.setLayout(PainelPesquisar1Layout);
        PainelPesquisar1Layout.setHorizontalGroup(
            PainelPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelPesquisar1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PainelPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblNomeCadastro2)
                    .addComponent(lblNomeCadastro)
                    .addComponent(lblNomeCadastro1))
                .addGap(4, 4, 4)
                .addGroup(PainelPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomeTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFaixaEtariaTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQtdMax, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ComboAnoLetivo, javax.swing.GroupLayout.Alignment.LEADING, 0, 258, Short.MAX_VALUE)
                    .addComponent(ComboProf, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboTurno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );
        PainelPesquisar1Layout.setVerticalGroup(
            PainelPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelPesquisar1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeCadastro1)
                    .addComponent(txtNomeTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeCadastro)
                    .addComponent(txtFaixaEtariaTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboAnoLetivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(PainelPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQtdMax, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeCadastro2)
                    .addComponent(ComboProf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LISTA DE TURMAS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        TabelaTurma.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabelaTurma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TabelaTurmaMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(TabelaTurma);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnAtualizar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/pencil_1.png"))); // NOI18N
        btnAtualizar.setText("Atualizar");
        btnAtualizar.setEnabled(false);
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        btnCadastrar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/disk.png"))); // NOI18N
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
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

        BtnRemover.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/delete.png"))); // NOI18N
        BtnRemover.setText("Remover");
        BtnRemover.setEnabled(false);
        BtnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Painel_ProfessorLayout = new javax.swing.GroupLayout(Painel_Professor);
        Painel_Professor.setLayout(Painel_ProfessorLayout);
        Painel_ProfessorLayout.setHorizontalGroup(
            Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_ProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PainelPesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(Painel_ProfessorLayout.createSequentialGroup()
                        .addComponent(btnAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnCancelAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        Painel_ProfessorLayout.setVerticalGroup(
            Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_ProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PainelPesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCancelAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Painel_Professor, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Painel_Professor, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtQtdMaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtdMaxKeyTyped
        String N = "1234567890";
        if (!N.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtQtdMaxKeyTyped

    private void txtQtdMaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtdMaxKeyReleased
        txtQtdMax.setText(Telas.Aluno.maxlength(txtQtdMax.getText(),4));
    }//GEN-LAST:event_txtQtdMaxKeyReleased

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        try {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja salvar?");
            if (dialogResult == JOptionPane.YES_OPTION) {
                if (VerificaCampoVazio()) {
                    InserirTurma();
                    limparCampos();
                    ListarTurmas();
                    JOptionPane.showMessageDialog(null, "Turma cadastrada com sucesso!");
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Ocorreu Algum Erro Ao Inserir a Turma !");
        }
        
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void TabelaTurmaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaTurmaMousePressed
        AtualizarComboBoxAnoLetivo(null);
        SetarCampos();
        btnAtualizar.setEnabled(true);
        BtnRemover.setEnabled(true);
        btnCadastrar.setEnabled(false);        
        ComboProf.setVisible(false);
    }//GEN-LAST:event_TabelaTurmaMousePressed

    private void BtnCancelAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelAlunoActionPerformed
        AtualizarComboBoxAnoLetivo(null);
        btnAtualizar.setEnabled(false);
        BtnRemover.setEnabled(false);
        btnCadastrar.setEnabled(true);
        ComboProf.setVisible(true);     
        limparCampos();
    }//GEN-LAST:event_BtnCancelAlunoActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja salvar as alterações?");
        if (dialogResult == JOptionPane.YES_OPTION) {
            AtualizarTurma();
            AtualizarComboBoxAnoLetivo(null);
            btnAtualizar.setEnabled(false);
            BtnRemover.setEnabled(false);
            btnCadastrar.setEnabled(true);
            ComboProf.setVisible(true);     
            limparCampos();
        }
        
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void BtnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRemoverActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover a turma selecionada, isso irá excluir todas as informações da turma, e irá desvincular todos os alunos que a ela pertencem. ");
        if (dialogResult == JOptionPane.YES_OPTION) {
            btnAtualizar.setEnabled(false);
            BtnRemover.setEnabled(false);
            btnCadastrar.setEnabled(true);
            RemoverTurma(IdTurma);
            ListarTurmas();
            limparCampos();
        }


    }//GEN-LAST:event_BtnRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancelAluno;
    private javax.swing.JButton BtnRemover;
    private javax.swing.JComboBox<String> ComboAnoLetivo;
    private javax.swing.JComboBox<Object> ComboProf;
    private javax.swing.JComboBox<String> ComboTurno;
    private javax.swing.JPanel PainelPesquisar1;
    private javax.swing.JPanel Painel_Professor;
    private javax.swing.JTable TabelaTurma;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblNomeCadastro;
    private javax.swing.JLabel lblNomeCadastro1;
    private javax.swing.JLabel lblNomeCadastro2;
    private javax.swing.JTextField txtFaixaEtariaTurma;
    private javax.swing.JTextField txtNomeTurma;
    private javax.swing.JTextField txtQtdMax;
    // End of variables declaration//GEN-END:variables
}
