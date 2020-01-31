package Telas;

import DAO.ProfessorDAO;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Professor extends javax.swing.JInternalFrame {
    
    public void ListarProfessorAtivo(){    
        ProfessorDAO pfDAO = new ProfessorDAO();
        List<Entidades.Professor> listaProfessor = pfDAO.consultarTodosAtivo();
        ModeloTabelaProfessor.setNumRows(0);
        for(Entidades.Professor p : listaProfessor){
             ModeloTabelaProfessor.addRow(new Object[]{
                 p.getNome(),p.getRg(),p.getSexo(),p.getTel1(),
                 p.getTel2(),p.getIdProfessor(),p.isEstadoAtual()
            });
        }
    }
    
    public void ListarProfessorInativo(){    
        ProfessorDAO pfDAO = new ProfessorDAO();
        List<Entidades.Professor> listaProfessor = pfDAO.consultarTodosInativo();
        ModeloTabelaProfessorInativo.setNumRows(0);
        for(Entidades.Professor p : listaProfessor){
             ModeloTabelaProfessorInativo.addRow(new Object[]{
                 p.getNome(),p.getRg(),p.getSexo(),p.getTel1(),
                 p.getTel2(),p.getIdProfessor(),p.isEstadoAtual()
            });
        }
    }
    
    public void FiltrarProfessorAtivoPeloNome(String str){
        
        ProfessorDAO pfDAO = new ProfessorDAO();
        List<Entidades.Professor> listaProfessor = pfDAO.FiltrarTodosAtivosPeloNome(str);
        ModeloTabelaProfessor.setNumRows(0);
        for(Entidades.Professor p : listaProfessor){
             ModeloTabelaProfessor.addRow(new Object[]{
                 p.getNome(),p.getRg(),p.getSexo(),p.getTel1(),
                 p.getTel2(),p.getIdProfessor(),p.isEstadoAtual()
            });
        }  
    }
    
    public void FiltrarProfessorInativoPeloNome(String str){
        
        ProfessorDAO pfDAO = new ProfessorDAO();
        List<Entidades.Professor> listaProfessor = pfDAO.FiltrarTodosInativosPeloNome(str);
        ModeloTabelaProfessorInativo.setNumRows(0);
        for(Entidades.Professor p : listaProfessor){
             ModeloTabelaProfessorInativo.addRow(new Object[]{
                 p.getNome(),p.getRg(),p.getSexo(),p.getTel1(),
                 p.getTel2(),p.getIdProfessor(),p.isEstadoAtual()
            });
        }  
    }
    
    public void FiltrarProfessorAtivoPeloRg(String str){
        
        ProfessorDAO pfDAO = new ProfessorDAO();
        List<Entidades.Professor> listaProfessor = pfDAO.FiltrarTodosAtivosPeloRg(str);
        ModeloTabelaProfessor.setNumRows(0);
        for(Entidades.Professor p : listaProfessor){
             ModeloTabelaProfessor.addRow(new Object[]{
                 p.getNome(),p.getRg(),p.getSexo(),p.getTel1(),
                 p.getTel2(),p.getIdProfessor(),p.isEstadoAtual()
            });
        }  
    }

    public void FiltrarProfessorInativoPeloRg(String str){
        
        ProfessorDAO pfDAO = new ProfessorDAO();
        List<Entidades.Professor> listaProfessor = pfDAO.FiltrarTodosInativosPeloRg(str);
        ModeloTabelaProfessorInativo.setNumRows(0);
        for(Entidades.Professor p : listaProfessor){
             ModeloTabelaProfessorInativo.addRow(new Object[]{
                 p.getNome(),p.getRg(),p.getSexo(),p.getTel1(),
                 p.getTel2(),p.getIdProfessor(),p.isEstadoAtual()
            });
        }  
    }
    
    public void SetarCamposDaTabela(){
        
        BtnAtualizar.setEnabled(true);
        BtnInativar.setEnabled(true);
        
        //Pega Os valores da tupla da tabela que foi selecionada 
        txtNome.setText(TabelaProfessor.getValueAt(TabelaProfessor.getSelectedRow(), 0).toString());
        NumRg.setText(TabelaProfessor.getValueAt(TabelaProfessor.getSelectedRow(), 1).toString());
        String s = TabelaProfessor.getValueAt(TabelaProfessor.getSelectedRow(), 2).toString();
        String tel1 = TabelaProfessor.getValueAt(TabelaProfessor.getSelectedRow(), 3).toString();
        String tel2 = TabelaProfessor.getValueAt(TabelaProfessor.getSelectedRow(), 4).toString();
        IdProfSelect = Long.decode(TabelaProfessor.getValueAt(TabelaProfessor.getSelectedRow(), 5).toString()); 
        //Retorna Os valores dos Telefones
        if(tel1 != null){
            TelN1.setText(tel1);
        }
        if(tel2 != null){
            TelN2.setText(tel2);
        }
        if("Feminino".equals(s)){
            SexoSelect.setSelectedIndex(2);
        }else if("Masculino".equals(s)){
            SexoSelect.setSelectedIndex(1);
        }

    }
    
    public void limparCampos(){
        
        //LIMPA OS CAMPOS APÓS O CADASTRO
        txtNome.setText("");
        NumRg.setText("");
        SexoSelect.setSelectedIndex(0);
        TelN1.setText("");
        TelN2.setText("");
        
        txtNome.setBorder(borderText);
        NumRg.setBorder(borderText);
        SexoSelect.setBorder(borderCombo);
        TelN1.setBorder(borderText);
        TelN2.setBorder(borderText);
    }
    
    public void esconderColunas(){
        int i = 3;
        while( i < TabelaProfessor.getColumnCount()){
            TabelaProfessor.getColumnModel().getColumn(i).setMinWidth(0);
            TabelaProfessor.getColumnModel().getColumn(i).setMaxWidth(0);
            i++;
        }
        int j = 5;
        while( j < TabelaProfessorDesativado.getColumnCount()){
            TabelaProfessorDesativado.getColumnModel().getColumn(j).setMinWidth(0);
            TabelaProfessorDesativado.getColumnModel().getColumn(j).setMaxWidth(0);
            j++;
        }
    }
   
    public void esconderColunasTabelaDesativada(){
        
    }
    
    public boolean VerificaCampoVazio(){
        boolean retorno = true;
        if("".equals(txtNome.getText())){
            txtNome.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          txtNome.setBorder(new LineBorder(Color.green, 1));  
        }
        if("".equals(NumRg.getText())){
            NumRg.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          NumRg.setBorder(new LineBorder(Color.green, 1));  
        }
        if("".equals(NumRg.getText())){
            NumRg.setBorder(new LineBorder(Color.red, 1));
            retorno = false;
        }else{
          NumRg.setBorder(new LineBorder(Color.green, 1));  
        }
        if(SexoSelect.getSelectedIndex() == 0){
            SexoSelect.setBorder(new LineBorder(Color.red));
            retorno = false;
        }else{
            SexoSelect.setBorder(new LineBorder(Color.green));
        }

        if(retorno == true){
           txtNome.setBorder(borderText);
           NumRg.setBorder(borderText);
           SexoSelect.setBorder(borderCombo);
        }else{
            JOptionPane.showMessageDialog(null, "Por Favor Preencha Todos Os Campos Obrigatorios!");
        }
        
       return retorno; 
    }
    
    public void InserirProfessor(){
        Entidades.Professor prof = new Entidades.Professor();
        prof.setNome(txtNome.getText());
        prof.setRg(NumRg.getText());
        prof.setSexo(SexoSelect.getSelectedItem().toString());
        prof.setTel1(TelN1.getText());
        prof.setTel2(TelN2.getText());
        prof.setEstadoAtual(true);
        
        DAO.ProfessorDAO DaoProf = new DAO.ProfessorDAO();
        DaoProf.salvar(prof);
        
    }
    
    public void AtualizarProfessor(){
        Entidades.Professor prof = new Entidades.Professor();
        prof.setIdProfessor(IdProfSelect);
        prof.setNome(txtNome.getText());
        prof.setRg(NumRg.getText());
        prof.setSexo(SexoSelect.getSelectedItem().toString());
        prof.setTel1(TelN1.getText());
        prof.setTel2(TelN2.getText());
        prof.setEstadoAtual(true);
        DAO.ProfessorDAO DaoProf = new DAO.ProfessorDAO();
        DaoProf.atualizar(prof);
        
    }
    
    public void InativarProfessor(){
        
        Entidades.Professor prof = new Entidades.Professor();
        prof.setIdProfessor(IdProfSelect);
        prof.setNome(txtNome.getText());
        prof.setRg(NumRg.getText());
        prof.setSexo(SexoSelect.getSelectedItem().toString());
        prof.setTel1(TelN1.getText());
        prof.setTel2(TelN2.getText());
        prof.setEstadoAtual(false);
        
        DAO.ProfessorDAO DaoProf = new DAO.ProfessorDAO();
        DaoProf.atualizar(prof);
    }
    
    public void ReativarProfessor(){
        
        Entidades.Professor prof = new Entidades.Professor();
        prof.setIdProfessor(Long.decode(TabelaProfessorDesativado.getValueAt(TabelaProfessorDesativado.getSelectedRow(), 5).toString()));
        prof.setNome(TabelaProfessorDesativado.getValueAt(TabelaProfessorDesativado.getSelectedRow(), 0).toString());
        prof.setRg(TabelaProfessorDesativado.getValueAt(TabelaProfessorDesativado.getSelectedRow(), 1).toString());
        prof.setSexo(TabelaProfessorDesativado.getValueAt(TabelaProfessorDesativado.getSelectedRow(), 2).toString());
        prof.setTel1(TabelaProfessorDesativado.getValueAt(TabelaProfessorDesativado.getSelectedRow(), 3).toString());
        prof.setTel2(TabelaProfessorDesativado.getValueAt(TabelaProfessorDesativado.getSelectedRow(), 4).toString());
        prof.setEstadoAtual(true);

        DAO.ProfessorDAO DaoProf = new DAO.ProfessorDAO();
        DaoProf.atualizar(prof);
    }
   
    //Modelo da Tabela Professor
    DefaultTableModel ModeloTabelaProfessor = new DefaultTableModel(  
        new Object[] {"Nome","RG","Sexo","Tel1","Tel2","id","estado"},0){  
            boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false};    
                @Override  
                public boolean isCellEditable(int rowIndex, int columnIndex){  
                    return canEdit [columnIndex];  
                }  
        };
    //Modelo da Tabela Professor Inativo
        DefaultTableModel ModeloTabelaProfessorInativo = new DefaultTableModel(  
        new Object[] {"Nome","RG","Sexo","Tel1","Tel2","id","estado"},0){  
            boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false};    
                @Override  
                public boolean isCellEditable(int rowIndex, int columnIndex){  
                    return canEdit [columnIndex];  
                }  
        };
    
    Border borderText;
    Border borderCombo;
    long IdProfSelect;
    
    public Professor() {
        initComponents();
        TabelaProfessor.setModel(ModeloTabelaProfessor);
        TabelaProfessorDesativado.setModel(ModeloTabelaProfessorInativo);
        borderText = txtNome.getBorder();
        borderCombo = SexoSelect.getBorder();
        ListarProfessorAtivo();
        ListarProfessorInativo();
        esconderColunas();
       
    }
    
    
    private String RB = "nome";
    private String RBInativo = "nome";
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GrupoBtnPesquisaProfAtivo = new javax.swing.ButtonGroup();
        GrupoBtnPesquisaProfInativo = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Painel_Professor = new javax.swing.JPanel();
        PainelCadastrarProfessor = new javax.swing.JPanel();
        txtNome = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        lblNomeCadastro2 = new javax.swing.JLabel();
        TelN1 = new javax.swing.JFormattedTextField();
        lbl_Tel_Filiacao_pai = new javax.swing.JLabel();
        TelN2 = new javax.swing.JFormattedTextField();
        lbl_Tel_Filiacao_pai1 = new javax.swing.JLabel();
        SexoSelect = new javax.swing.JComboBox<>();
        NumRg = new javax.swing.JTextField();
        lbl_Tel_Filiacao_pai2 = new javax.swing.JLabel();
        PainelPesquisarProfessor = new javax.swing.JPanel();
        jRadioButtonNomeProfessor = new javax.swing.JRadioButton();
        jRadioButtonMatriculaProfessor = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaProfessor = new javax.swing.JTable();
        lblFiltro = new javax.swing.JLabel();
        Txt_Busca1 = new javax.swing.JTextField();
        BtnCadastrar = new javax.swing.JButton();
        BtnAtualizar = new javax.swing.JButton();
        BtnInativar = new javax.swing.JButton();
        BtnCancel = new javax.swing.JButton();
        Painel_Prof_Inativo = new javax.swing.JPanel();
        PnProfInativo = new javax.swing.JPanel();
        PainelListaPfInativo = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelaProfessorDesativado = new javax.swing.JTable();
        BtnReativar = new javax.swing.JButton();
        lblFiltro1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        Txt_Buscar_Inativos = new javax.swing.JTextField();

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        Painel_Professor.setPreferredSize(new java.awt.Dimension(815, 551));
        Painel_Professor.setRequestFocusEnabled(false);

        PainelCadastrarProfessor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastrar / Atualizar ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNome.setText("* Nome:");

        lblNomeCadastro2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro2.setText("* Nº Identidade :");

        try {
            TelN1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)# ########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TelN1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbl_Tel_Filiacao_pai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_Tel_Filiacao_pai.setText("Tel. :");

        try {
            TelN2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)# ########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TelN2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbl_Tel_Filiacao_pai1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_Tel_Filiacao_pai1.setText("Tel. :");

        SexoSelect.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        SexoSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sexo", "Masculino", "Feminino" }));

        NumRg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NumRgKeyTyped(evt);
            }
        });

        lbl_Tel_Filiacao_pai2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_Tel_Filiacao_pai2.setText("*");

        javax.swing.GroupLayout PainelCadastrarProfessorLayout = new javax.swing.GroupLayout(PainelCadastrarProfessor);
        PainelCadastrarProfessor.setLayout(PainelCadastrarProfessorLayout);
        PainelCadastrarProfessorLayout.setHorizontalGroup(
            PainelCadastrarProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelCadastrarProfessorLayout.createSequentialGroup()
                .addGroup(PainelCadastrarProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNome)
                    .addComponent(lbl_Tel_Filiacao_pai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelCadastrarProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelCadastrarProfessorLayout.createSequentialGroup()
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNomeCadastro2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NumRg, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelCadastrarProfessorLayout.createSequentialGroup()
                        .addComponent(TelN1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_Tel_Filiacao_pai1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TelN2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(lbl_Tel_Filiacao_pai2)
                        .addGap(3, 3, 3)
                        .addComponent(SexoSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelCadastrarProfessorLayout.setVerticalGroup(
            PainelCadastrarProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelCadastrarProfessorLayout.createSequentialGroup()
                .addGroup(PainelCadastrarProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelCadastrarProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNomeCadastro2)
                        .addComponent(NumRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelCadastrarProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNome)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelCadastrarProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Tel_Filiacao_pai)
                    .addComponent(TelN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Tel_Filiacao_pai1)
                    .addComponent(TelN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SexoSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Tel_Filiacao_pai2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        PainelPesquisarProfessor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisar Professor", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        GrupoBtnPesquisaProfAtivo.add(jRadioButtonNomeProfessor);
        jRadioButtonNomeProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButtonNomeProfessor.setSelected(true);
        jRadioButtonNomeProfessor.setText("Nome");
        jRadioButtonNomeProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNomeProfessorActionPerformed(evt);
            }
        });

        GrupoBtnPesquisaProfAtivo.add(jRadioButtonMatriculaProfessor);
        jRadioButtonMatriculaProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButtonMatriculaProfessor.setText("RG");
        jRadioButtonMatriculaProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMatriculaProfessorActionPerformed(evt);
            }
        });

        TabelaProfessor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "RG", "Sexo"
            }
        ));
        TabelaProfessor.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TabelaProfessor.setShowHorizontalLines(false);
        TabelaProfessor.setShowVerticalLines(false);
        TabelaProfessor.getTableHeader().setResizingAllowed(false);
        TabelaProfessor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TabelaProfessorMousePressed(evt);
            }
        });
        TabelaProfessor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TabelaProfessorKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TabelaProfessor);

        lblFiltro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFiltro.setText("Filtrar Por:");

        Txt_Busca1.setBackground(new java.awt.Color(240, 240, 240));
        Txt_Busca1.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        Txt_Busca1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Txt_Busca1.setText("Buscar o Professor Por Nome");
        Txt_Busca1.setToolTipText("");
        Txt_Busca1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), java.awt.Color.black, java.awt.Color.black, java.awt.Color.white));
        Txt_Busca1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                Txt_Busca1CaretUpdate(evt);
            }
        });
        Txt_Busca1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Txt_Busca1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Txt_Busca1FocusLost(evt);
            }
        });

        javax.swing.GroupLayout PainelPesquisarProfessorLayout = new javax.swing.GroupLayout(PainelPesquisarProfessor);
        PainelPesquisarProfessor.setLayout(PainelPesquisarProfessorLayout);
        PainelPesquisarProfessorLayout.setHorizontalGroup(
            PainelPesquisarProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelPesquisarProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelPesquisarProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelPesquisarProfessorLayout.createSequentialGroup()
                        .addComponent(lblFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonNomeProfessor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonMatriculaProfessor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Txt_Busca1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        PainelPesquisarProfessorLayout.setVerticalGroup(
            PainelPesquisarProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPesquisarProfessorLayout.createSequentialGroup()
                .addGroup(PainelPesquisarProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonNomeProfessor)
                    .addComponent(jRadioButtonMatriculaProfessor)
                    .addComponent(lblFiltro)
                    .addComponent(Txt_Busca1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        BtnCadastrar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/disk.png"))); // NOI18N
        BtnCadastrar.setText("Cadastrar");
        BtnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCadastrarActionPerformed(evt);
            }
        });

        BtnAtualizar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/pencil_1.png"))); // NOI18N
        BtnAtualizar.setText("Atualizar");
        BtnAtualizar.setEnabled(false);
        BtnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAtualizarActionPerformed(evt);
            }
        });

        BtnInativar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnInativar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/delete.png"))); // NOI18N
        BtnInativar.setText("Inativar");
        BtnInativar.setEnabled(false);
        BtnInativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInativarActionPerformed(evt);
            }
        });

        BtnCancel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/cancel.png"))); // NOI18N
        BtnCancel.setText("Cancelar");
        BtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Painel_ProfessorLayout = new javax.swing.GroupLayout(Painel_Professor);
        Painel_Professor.setLayout(Painel_ProfessorLayout);
        Painel_ProfessorLayout.setHorizontalGroup(
            Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_ProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Painel_ProfessorLayout.createSequentialGroup()
                        .addComponent(BtnAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnInativar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnCadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(PainelCadastrarProfessor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PainelPesquisarProfessor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        Painel_ProfessorLayout.setVerticalGroup(
            Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_ProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PainelPesquisarProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PainelCadastrarProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnInativar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        jTabbedPane1.addTab("Gerenciar Professores", new javax.swing.ImageIcon(getClass().getResource("/Icones/group_gear.png")), Painel_Professor); // NOI18N

        PnProfInativo.setPreferredSize(new java.awt.Dimension(815, 551));
        PnProfInativo.setRequestFocusEnabled(false);

        PainelListaPfInativo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Professores Desativados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        TabelaProfessorDesativado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nome", "RG", "Sexo", "Tel1", "Tel2"
            }
        ));
        TabelaProfessorDesativado.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TabelaProfessorDesativado.setShowHorizontalLines(false);
        TabelaProfessorDesativado.setShowVerticalLines(false);
        TabelaProfessorDesativado.getTableHeader().setResizingAllowed(false);
        TabelaProfessorDesativado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TabelaProfessorDesativadoMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(TabelaProfessorDesativado);

        BtnReativar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnReativar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/add.png"))); // NOI18N
        BtnReativar.setText("Reativar");
        BtnReativar.setEnabled(false);
        BtnReativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReativarActionPerformed(evt);
            }
        });

        lblFiltro1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFiltro1.setText("Filtrar Por:");

        GrupoBtnPesquisaProfInativo.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Nome");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        GrupoBtnPesquisaProfInativo.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton2.setText("RG");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        Txt_Buscar_Inativos.setBackground(new java.awt.Color(240, 240, 240));
        Txt_Buscar_Inativos.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        Txt_Buscar_Inativos.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Txt_Buscar_Inativos.setText("Buscar o Professor Por Nome");
        Txt_Buscar_Inativos.setToolTipText("");
        Txt_Buscar_Inativos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), java.awt.Color.black, java.awt.Color.black, java.awt.Color.white));
        Txt_Buscar_Inativos.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                Txt_Buscar_InativosCaretUpdate(evt);
            }
        });
        Txt_Buscar_Inativos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Txt_Buscar_InativosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Txt_Buscar_InativosFocusLost(evt);
            }
        });

        javax.swing.GroupLayout PainelListaPfInativoLayout = new javax.swing.GroupLayout(PainelListaPfInativo);
        PainelListaPfInativo.setLayout(PainelListaPfInativoLayout);
        PainelListaPfInativoLayout.setHorizontalGroup(
            PainelListaPfInativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelListaPfInativoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelListaPfInativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PainelListaPfInativoLayout.createSequentialGroup()
                        .addComponent(lblFiltro1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Txt_Buscar_Inativos, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                    .addGroup(PainelListaPfInativoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BtnReativar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PainelListaPfInativoLayout.setVerticalGroup(
            PainelListaPfInativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelListaPfInativoLayout.createSequentialGroup()
                .addGroup(PainelListaPfInativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelListaPfInativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFiltro1)
                        .addComponent(jRadioButton1)
                        .addComponent(jRadioButton2))
                    .addComponent(Txt_Buscar_Inativos, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnReativar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PnProfInativoLayout = new javax.swing.GroupLayout(PnProfInativo);
        PnProfInativo.setLayout(PnProfInativoLayout);
        PnProfInativoLayout.setHorizontalGroup(
            PnProfInativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelListaPfInativo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PnProfInativoLayout.setVerticalGroup(
            PnProfInativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnProfInativoLayout.createSequentialGroup()
                .addComponent(PainelListaPfInativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 366, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Painel_Prof_InativoLayout = new javax.swing.GroupLayout(Painel_Prof_Inativo);
        Painel_Prof_Inativo.setLayout(Painel_Prof_InativoLayout);
        Painel_Prof_InativoLayout.setHorizontalGroup(
            Painel_Prof_InativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Prof_InativoLayout.createSequentialGroup()
                .addComponent(PnProfInativo, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                .addContainerGap())
        );
        Painel_Prof_InativoLayout.setVerticalGroup(
            Painel_Prof_InativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PnProfInativo, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTabbedPane1.addTab(" Professores Inativos", new javax.swing.ImageIcon(getClass().getResource("/Icones/group_delete.png")), Painel_Prof_Inativo); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCadastrarActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja salvar?");
        if (dialogResult == JOptionPane.YES_OPTION) {
            if (VerificaCampoVazio()) {
                InserirProfessor();
                ListarProfessorAtivo();
                ListarProfessorInativo();
                JOptionPane.showMessageDialog(null, "Professor(a) Cadastrado com Sucesso !");
                limparCampos();
            }
        }
        
    }//GEN-LAST:event_BtnCadastrarActionPerformed

    private void TabelaProfessorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaProfessorMousePressed
        SetarCamposDaTabela();
        BtnCadastrar.setEnabled(false);
    }//GEN-LAST:event_TabelaProfessorMousePressed

    private void TabelaProfessorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TabelaProfessorKeyReleased
        SetarCamposDaTabela();
        BtnCadastrar.setEnabled(false);
    }//GEN-LAST:event_TabelaProfessorKeyReleased

    private void BtnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAtualizarActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja salvar as alterações?");
        if (dialogResult == JOptionPane.YES_OPTION) {
            if (VerificaCampoVazio()) {
                AtualizarProfessor();
                ListarProfessorAtivo();
                ListarProfessorInativo();
                JOptionPane.showMessageDialog(null, "Alterações Efetuadas Com Sucesso !");
                BtnAtualizar.setEnabled(false);
                BtnInativar.setEnabled(false);
                BtnCadastrar.setEnabled(true);
                limparCampos();
            }
        }
        

        
        
    }//GEN-LAST:event_BtnAtualizarActionPerformed

    private void BtnInativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInativarActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja Inativar o professor selecionado as alterações?");
        if (dialogResult == JOptionPane.YES_OPTION) {
            if (VerificaCampoVazio()) {
                InativarProfessor();
                BtnInativar.setEnabled(false);
                BtnAtualizar.setEnabled(false);
                ListarProfessorAtivo();
                ListarProfessorInativo();
                limparCampos();
                JOptionPane.showMessageDialog(null, "Professor(a) Inativado com Sucesso, Para Reativar Acesse a Aba Professores Inativos.");
                BtnCadastrar.setEnabled(true);
            }
        }

        
        
    }//GEN-LAST:event_BtnInativarActionPerformed

    private void BtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelActionPerformed
        ListarProfessorAtivo();
        ListarProfessorInativo();
        limparCampos();
        BtnCadastrar.setEnabled(true);
        BtnInativar.setEnabled(false);
        BtnAtualizar.setEnabled(false);
    }//GEN-LAST:event_BtnCancelActionPerformed

    private void BtnReativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReativarActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja Reativar o(a) Professor(a) selecionado(a)");
        if (dialogResult == JOptionPane.YES_OPTION) {
            ReativarProfessor();
            ListarProfessorAtivo();
            ListarProfessorInativo();
            BtnReativar.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Professor(a) Reativado(a) com sucesso!");
        }
        
    }//GEN-LAST:event_BtnReativarActionPerformed

    private void TabelaProfessorDesativadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaProfessorDesativadoMousePressed
        BtnReativar.setEnabled(true);
    }//GEN-LAST:event_TabelaProfessorDesativadoMousePressed

    private void jRadioButtonNomeProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNomeProfessorActionPerformed
        RB = "nome";
        Txt_Busca1.setText("Buscar o Professor Por Nome");
        ListarProfessorAtivo();
    }//GEN-LAST:event_jRadioButtonNomeProfessorActionPerformed

    private void jRadioButtonMatriculaProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMatriculaProfessorActionPerformed
        RB = "rg";
        Txt_Busca1.setText("Buscar o Professor Por RG");
        ListarProfessorAtivo();
    }//GEN-LAST:event_jRadioButtonMatriculaProfessorActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        RBInativo = "nome";
        Txt_Buscar_Inativos.setText("Buscar o Professor Por Nome");
        ListarProfessorInativo();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        RBInativo = "rg";
        Txt_Buscar_Inativos.setText("Buscar o Professor Por RG");
        ListarProfessorInativo();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void NumRgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumRgKeyTyped
        String N = "1234567890";
        if (!N.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_NumRgKeyTyped

    private void Txt_Busca1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Txt_Busca1FocusGained
        if (Txt_Busca1.getText().equals("Buscar o Professor Por Nome") || Txt_Busca1.getText().equals("Buscar o Professor Por RG") ) {    
            Txt_Busca1.setText("");        
        }
    }//GEN-LAST:event_Txt_Busca1FocusGained

    private void Txt_Busca1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Txt_Busca1FocusLost
        if  (Txt_Busca1.getText().equals("")) {
            if(RB == "nome"){
                Txt_Busca1.setText("Buscar o Professor Por Nome");
            }else if(RB == "rg"){
                Txt_Busca1.setText("Buscar o Professor Por RG");
            }
            ListarProfessorAtivo();
       }
    }//GEN-LAST:event_Txt_Busca1FocusLost

    private void Txt_Busca1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_Txt_Busca1CaretUpdate
        if(RB == "nome"){
                FiltrarProfessorAtivoPeloNome(Txt_Busca1.getText());
            }else if(RB == "rg"){
                FiltrarProfessorAtivoPeloRg(Txt_Busca1.getText());
            }
    }//GEN-LAST:event_Txt_Busca1CaretUpdate

    private void Txt_Buscar_InativosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Txt_Buscar_InativosFocusGained
        if (Txt_Buscar_Inativos.getText().equals("Buscar o Professor Por Nome") || Txt_Buscar_Inativos.getText().equals("Buscar o Professor Por RG") ) {    
            Txt_Buscar_Inativos.setText("");        
        }
    }//GEN-LAST:event_Txt_Buscar_InativosFocusGained

    private void Txt_Buscar_InativosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Txt_Buscar_InativosFocusLost
         if  (Txt_Buscar_Inativos.getText().equals("")) {
            if(RBInativo == "nome"){
                Txt_Buscar_Inativos.setText("Buscar o Professor Por Nome");
            }else if(RBInativo == "rg"){
                Txt_Buscar_Inativos.setText("Buscar o Professor Por RG");
            }
             ListarProfessorInativo();
       }
    }//GEN-LAST:event_Txt_Buscar_InativosFocusLost

    private void Txt_Buscar_InativosCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_Txt_Buscar_InativosCaretUpdate
         if(RBInativo == "nome"){
                FiltrarProfessorInativoPeloNome(Txt_Buscar_Inativos.getText());
            }else if(RBInativo == "rg"){
                FiltrarProfessorInativoPeloRg(Txt_Buscar_Inativos.getText());
            }
    }//GEN-LAST:event_Txt_Buscar_InativosCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAtualizar;
    private javax.swing.JButton BtnCadastrar;
    private javax.swing.JButton BtnCancel;
    private javax.swing.JButton BtnInativar;
    private javax.swing.JButton BtnReativar;
    private javax.swing.ButtonGroup GrupoBtnPesquisaProfAtivo;
    private javax.swing.ButtonGroup GrupoBtnPesquisaProfInativo;
    private javax.swing.JTextField NumRg;
    private javax.swing.JPanel PainelCadastrarProfessor;
    private javax.swing.JPanel PainelListaPfInativo;
    private javax.swing.JPanel PainelPesquisarProfessor;
    private javax.swing.JPanel Painel_Prof_Inativo;
    private javax.swing.JPanel Painel_Professor;
    private javax.swing.JPanel PnProfInativo;
    private javax.swing.JComboBox<String> SexoSelect;
    private javax.swing.JTable TabelaProfessor;
    private javax.swing.JTable TabelaProfessorDesativado;
    private javax.swing.JFormattedTextField TelN1;
    private javax.swing.JFormattedTextField TelN2;
    private javax.swing.JTextField Txt_Busca1;
    private javax.swing.JTextField Txt_Buscar_Inativos;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButtonMatriculaProfessor;
    private javax.swing.JRadioButton jRadioButtonNomeProfessor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblFiltro1;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNomeCadastro2;
    private javax.swing.JLabel lbl_Tel_Filiacao_pai;
    private javax.swing.JLabel lbl_Tel_Filiacao_pai1;
    private javax.swing.JLabel lbl_Tel_Filiacao_pai2;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
