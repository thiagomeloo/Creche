package Telas;

import java.awt.Color;
import java.awt.Component;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class Gerenciar_Aluno_Turma extends javax.swing.JInternalFrame {

    public void GerarComboTurma(){
        
        Entidades.Turma tur = new Entidades.Turma();
        ComboTurma.removeAllItems();
        tur.setNome("");
        tur.setTurno("");
        tur.setAnoLetivo("");
        ComboTurma.addItem(tur);
        
        DAO.TurmaDAO Tdao = new DAO.TurmaDAO();
        for(Entidades.Turma t : Tdao.consultarTodsAsTurmas()){
           ComboTurma.addItem(t);
        }
    }
    
    public void esconderColunas(){
        int i = 2;
          TabelaTodosAlunos.getColumnModel().getColumn(0).setPreferredWidth(10);
          
        while( i < TabelaTodosAlunos.getColumnCount()){
            TabelaTodosAlunos.getColumnModel().getColumn(i).setMinWidth(0);
            TabelaTodosAlunos.getColumnModel().getColumn(i).setMaxWidth(0);
            i++;
        }
        int j = 1;
        while( j < TabelaAlunosDaTurma.getColumnCount()){
            TabelaAlunosDaTurma.getColumnModel().getColumn(j).setMinWidth(0);
            TabelaAlunosDaTurma.getColumnModel().getColumn(j).setMaxWidth(0);
            j++;
        }

    }
    
    public void ListarTodosAlunosSemTurma(){    
        DAO.AlunoDAO alDAO = new DAO.AlunoDAO();
        List<Entidades.Aluno> listaAluno = alDAO.consultarTodosAlunosComEderecoSemTurma();
        ModeloTabelaAluno.setNumRows(0);
        SimpleDateFormat Df = new SimpleDateFormat("dd/MM/yyyy");
        for(Entidades.Aluno a : listaAluno){
            java.util.Date date = a.getDataNascimento();
             ModeloTabelaAluno.addRow(new Object[]{
                 a.getMatricula(),a.getNome(),Df.format(date),a.getSexo(),a.getNIS(),a.getIdSus(),
                 a.isNecessidadesEspeciais(),a.getEspecificacaoNecessidadesEspeciais(),a.isUtilizaTransporte(),a.getCor(),
                 a.getStatusMatricula(),a.getEndereco_idEndereco().getRua(),a.getEndereco_idEndereco().getBairro(),
                 a.getEndereco_idEndereco().getNumero(),a.getEndereco_idEndereco().getCidade(),
                 a.getEndereco_idEndereco().getEstado(),
                   
            });
        }
    }  
    
    public void ListarAlunosDaTurma(long id){
        ModeloTabelaAlunoDaTurma.setNumRows(0);
        Entidades.Turma turma = (Entidades.Turma) ComboTurma.getSelectedItem();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR,0);
        int anoTurma = Integer.parseInt(turma.getAnoLetivo());
        int anoAtual = c.getWeekYear();
        if(anoAtual > anoTurma){
            SetarAlunosComoPendente(turma.getIdTurma());
        }
        DAO.AlunoDAO alDAO = new DAO.AlunoDAO();
        List<Entidades.Aluno> listaAluno = alDAO.consultaAlunosDeUmaTurma(id);
        ModeloTabelaAlunoDaTurma.setNumRows(0);
        SimpleDateFormat Df = new SimpleDateFormat("dd/MM/yyyy");
        for(Entidades.Aluno a : listaAluno){
            java.util.Date date = a.getDataNascimento();
             ModeloTabelaAlunoDaTurma.addRow(new Object[]{
                 a.getNome(),a.getMatricula(),Df.format(date),a.getSexo(),a.getNIS(),a.getIdSus(),
                 a.isNecessidadesEspeciais(),a.getEspecificacaoNecessidadesEspeciais(),a.isUtilizaTransporte(),a.getCor(),
                 a.getStatusMatricula(),a.getEndereco_idEndereco().getRua(),a.getEndereco_idEndereco().getBairro(),
                 a.getEndereco_idEndereco().getNumero(),a.getEndereco_idEndereco().getCidade(),
                 a.getEndereco_idEndereco().getEstado(),
                   
            });  
        }
        corNaLinha();
        
    }
    
    public void corNaLinha(){
  
        TabelaAlunosDaTurma.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);
                
                Object texto = table.getValueAt(row, 10);
                Object nome = table.getValueAt(row, 0);
                
                if(texto.equals("matriculado")){
                    setBackground(Color.green);
                    setForeground(Color.black);
                    
                }else{
                    setBackground(Color.RED);
                    setForeground(Color.WHITE);
                }
                
                
                
                return this;
            }
        });
    }

    public void InserirAlunoNaTurma(Entidades.Turma turma, long IdAluno){

        DAO.AlunoDAO alDAO = new DAO.AlunoDAO();
        Entidades.Aluno aluno  = alDAO.consultaAlunoPorMatricula(IdAluno);
        aluno.setTurma_idTurma(turma);
        aluno.setStatusMatricula("matriculado");
        alDAO.atualizar(aluno);
        
        
        Entidades.Matricula mat = new Entidades.Matricula();
        mat.setAluno_Matricula(aluno);
        Date data = new Date(new java.util.Date().getTime());  
        mat.setData_Matricula(data);
        DAO.MatriculaDAO matDAO = new DAO.MatriculaDAO();
        matDAO.salvar(mat);
        
    }
    
    public void SetarAlunosComoPendente(long idTurma){

        DAO.AlunoDAO alDAO = new DAO.AlunoDAO();
        List<Entidades.Aluno> listaAluno = alDAO.consultaAlunosDeUmaTurma(idTurma);
        for(Entidades.Aluno a : listaAluno){
            Entidades.Aluno aluno  = alDAO.consultaAlunoPorMatricula(a.getMatricula());
            aluno.setStatusMatricula("pendente");
            alDAO.atualizar(aluno);
        }
        corNaLinha();
    }
    
    public void RemoveAlunodaTurma(Entidades.Turma turma, long IdAluno){

        DAO.AlunoDAO alDAO = new DAO.AlunoDAO();
        Entidades.Aluno aluno  = alDAO.consultaAlunoPorMatricula(IdAluno); 
        aluno.setTurma_idTurma(null);
        aluno.setStatusMatricula(null);
        alDAO.atualizar(aluno);
   
    }
    

    //Modelo da Tabela Alunos da turma
    DefaultTableModel ModeloTabelaAlunoDaTurma = new DefaultTableModel(  
        new Object[] {"Nome","Matricula","Data Nasc","Sexo","NIS","SUS","Necessidade",
            "EspecificacaoNecessidade","Transporte","CorRaca","StatusMat","Rua","Bairro",
            "Numero","Cidade","Estado",},0){  
            boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false, false, false, false, false, false};    
                @Override  
                public boolean isCellEditable(int rowIndex, int columnIndex){  
                    return canEdit [columnIndex];  
                }  
        };
    
    
    
    //Modelo da Tabela todos Alunos
    DefaultTableModel ModeloTabelaAluno = new DefaultTableModel(  
        new Object[] {"Matricula","Nome","Data Nasc","Sexo","NIS","SUS","Necessidade",
            "EspecificacaoNecessidade","Transporte","CorRaca","StatusMat","Rua","Bairro",
            "Numero","Cidade","Estado",},0){  
            boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false, false, false, false, false, false};    
                @Override  
                public boolean isCellEditable(int rowIndex, int columnIndex){  
                    return canEdit [columnIndex];  
                }  
        };
    
      
    public Gerenciar_Aluno_Turma() {
        initComponents();
        GerarComboTurma();
        ListarTodosAlunosSemTurma();
        TabelaTodosAlunos.setModel(ModeloTabelaAluno);
        TabelaAlunosDaTurma.setModel(ModeloTabelaAlunoDaTurma);
        esconderColunas();
        
        
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Painel_Professor = new javax.swing.JPanel();
        PainelPesquisar2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelaTodosAlunos = new javax.swing.JTable();
        PainelPesquisar6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabelaAlunosDaTurma = new javax.swing.JTable();
        qtdAlunos = new javax.swing.JLabel();
        qtdMaxAlTurma = new javax.swing.JLabel();
        Add_Na_Turma = new javax.swing.JButton();
        Remove_Aluno_Da_Turma = new javax.swing.JButton();
        lblNomeCadastro = new javax.swing.JLabel();
        ComboTurma = new javax.swing.JComboBox<>();
        Renovar_Mat = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        Painel_Professor.setPreferredSize(new java.awt.Dimension(815, 551));
        Painel_Professor.setRequestFocusEnabled(false);

        PainelPesquisar2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Todos os Alunos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        TabelaTodosAlunos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Matricula", "Nome"
            }
        ));
        TabelaTodosAlunos.setShowVerticalLines(false);
        TabelaTodosAlunos.getTableHeader().setResizingAllowed(false);
        TabelaTodosAlunos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TabelaTodosAlunosMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(TabelaTodosAlunos);
        if (TabelaTodosAlunos.getColumnModel().getColumnCount() > 0) {
            TabelaTodosAlunos.getColumnModel().getColumn(0).setHeaderValue("Matricula");
        }

        javax.swing.GroupLayout PainelPesquisar2Layout = new javax.swing.GroupLayout(PainelPesquisar2);
        PainelPesquisar2.setLayout(PainelPesquisar2Layout);
        PainelPesquisar2Layout.setHorizontalGroup(
            PainelPesquisar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPesquisar2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelPesquisar2Layout.setVerticalGroup(
            PainelPesquisar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelPesquisar2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        PainelPesquisar6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alunos Matriculados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        TabelaAlunosDaTurma.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabelaAlunosDaTurma.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Nome"
            }
        ));
        TabelaAlunosDaTurma.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TabelaAlunosDaTurma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TabelaAlunosDaTurmaMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(TabelaAlunosDaTurma);

        qtdAlunos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qtdAlunos.setText("Qtd de Alunos na Turma: ");

        qtdMaxAlTurma.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qtdMaxAlTurma.setText("Qtd Max de Alunos na Turma:");

        javax.swing.GroupLayout PainelPesquisar6Layout = new javax.swing.GroupLayout(PainelPesquisar6);
        PainelPesquisar6.setLayout(PainelPesquisar6Layout);
        PainelPesquisar6Layout.setHorizontalGroup(
            PainelPesquisar6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPesquisar6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelPesquisar6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qtdAlunos)
                    .addComponent(qtdMaxAlTurma))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelPesquisar6Layout.setVerticalGroup(
            PainelPesquisar6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelPesquisar6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(qtdAlunos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qtdMaxAlTurma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Add_Na_Turma.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Add_Na_Turma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/arrow_right.png"))); // NOI18N
        Add_Na_Turma.setEnabled(false);
        Add_Na_Turma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_Na_TurmaActionPerformed(evt);
            }
        });

        Remove_Aluno_Da_Turma.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Remove_Aluno_Da_Turma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/arrow_left.png"))); // NOI18N
        Remove_Aluno_Da_Turma.setEnabled(false);
        Remove_Aluno_Da_Turma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Remove_Aluno_Da_TurmaActionPerformed(evt);
            }
        });

        lblNomeCadastro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro.setText("Turma:");

        ComboTurma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        ComboTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboTurmaActionPerformed(evt);
            }
        });

        Renovar_Mat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Renovar_Mat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/arrow_refresh.png"))); // NOI18N
        Renovar_Mat.setEnabled(false);
        Renovar_Mat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Renovar_MatActionPerformed(evt);
            }
        });

        jLabel1.setText("Alunos Matriculados:");

        jLabel2.setText("Alunos Pendentes:");

        jPanel1.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(51, 255, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Painel_ProfessorLayout = new javax.swing.GroupLayout(Painel_Professor);
        Painel_Professor.setLayout(Painel_ProfessorLayout);
        Painel_ProfessorLayout.setHorizontalGroup(
            Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_ProfessorLayout.createSequentialGroup()
                .addGroup(Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(PainelPesquisar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Painel_ProfessorLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblNomeCadastro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboTurma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Add_Na_Turma, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Remove_Aluno_Da_Turma, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Renovar_Mat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PainelPesquisar6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Painel_ProfessorLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Painel_ProfessorLayout.setVerticalGroup(
            Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_ProfessorLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ComboTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNomeCadastro)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Painel_ProfessorLayout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(Add_Na_Turma)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Remove_Aluno_Da_Turma)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Renovar_Mat))
                    .addComponent(PainelPesquisar6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PainelPesquisar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Painel_Professor, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Painel_Professor, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboTurmaActionPerformed
        //pega a turma
        try {
            if(ComboTurma.getSelectedIndex() == 0){
                qtdAlunos.setText("Qtd de Alunos na Turma: ");
                qtdMaxAlTurma.setText("Qtd Max de Alunos na Turma: ");
            }
         
            Entidades.Turma turma = (Entidades.Turma) ComboTurma.getSelectedItem();
            ListarAlunosDaTurma(turma.getIdTurma());
            //pega qntd de alunos que ja pertencem a turma
            DAO.AlunoDAO AlDAO = new DAO.AlunoDAO();
            Long qtd = AlDAO.contarAlunosDeUmaTurma(turma.getIdTurma());
            qtdAlunos.setText("Qtd de Alunos na Turma: "+qtd);
            qtdMaxAlTurma.setText("Qtd Max de Alunos na Turma: "+ turma.getQuantidadeMaxima());
            Add_Na_Turma.setEnabled(false);
            ListarTodosAlunosSemTurma();
            
        } catch (Exception e) {
            
        }
          
       
        
    }//GEN-LAST:event_ComboTurmaActionPerformed

    private void Add_Na_TurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_Na_TurmaActionPerformed
       try {
            if(ComboTurma.getSelectedIndex() == 0){
                
            }else{
                Entidades.Turma turma = (Entidades.Turma) ComboTurma.getSelectedItem();
                //pega qntd de alunos que ja pertencem a turma
                DAO.AlunoDAO AlDAO = new DAO.AlunoDAO();
                Long qtd = AlDAO.contarAlunosDeUmaTurma(turma.getIdTurma());
                qtdAlunos.setText("Qtd de Alunos na Turma: "+(qtd + 1));
                qtdMaxAlTurma.setText("Qtd Max de Alunos na Turma: "+ turma.getQuantidadeMaxima());
                //verifica se a qtd de alunos e menor que a qntd maxima
                if(qtd < turma.getQuantidadeMaxima()){
                   long idAluno = Long.decode(TabelaTodosAlunos.getValueAt(TabelaTodosAlunos.getSelectedRow(), 0).toString());
                   InserirAlunoNaTurma(turma,idAluno);
                   ListarTodosAlunosSemTurma();
                   ListarAlunosDaTurma(turma.getIdTurma());
                   Add_Na_Turma.setEnabled(false);
                   Renovar_Mat.setEnabled(false);
                }  
            }
                    
            
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_Add_Na_TurmaActionPerformed

    private void TabelaTodosAlunosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaTodosAlunosMousePressed
            
        try {
            Entidades.Turma turma = (Entidades.Turma) ComboTurma.getSelectedItem();
            TabelaAlunosDaTurma.getSelectionModel().clearSelection();
            
            //pega qntd de alunos que ja pertencem a turma
            DAO.AlunoDAO AlDAO = new DAO.AlunoDAO();
            Long qtd = AlDAO.contarAlunosDeUmaTurma(turma.getIdTurma());
            
            //verifica se a qtd de alunos e menor que a qntd maxima
            if(qtd < turma.getQuantidadeMaxima()){
                Add_Na_Turma.setEnabled(true);
                //permite add mais aluno na turma
            }else{
                Add_Na_Turma.setEnabled(false);
                //diz que a turma ta lotada
            }          
            Renovar_Mat.setEnabled(false);
            Remove_Aluno_Da_Turma.setEnabled(false);
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_TabelaTodosAlunosMousePressed

    private void Remove_Aluno_Da_TurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Remove_Aluno_Da_TurmaActionPerformed
         try {
            
            Entidades.Turma turma = (Entidades.Turma) ComboTurma.getSelectedItem();
            long idAluno = Long.decode(TabelaAlunosDaTurma.getValueAt(TabelaAlunosDaTurma.getSelectedRow(), 1).toString());
            RemoveAlunodaTurma(turma,idAluno);
            ListarTodosAlunosSemTurma();
            ListarAlunosDaTurma(turma.getIdTurma());
            DAO.AlunoDAO AlDAO = new DAO.AlunoDAO();
            Long qtd = AlDAO.contarAlunosDeUmaTurma(turma.getIdTurma());
            qtdAlunos.setText("Qtd de Alunos na Turma: "+(qtd));
            qtdMaxAlTurma.setText("Qtd Max de Alunos na Turma: "+ turma.getQuantidadeMaxima());
            Remove_Aluno_Da_Turma.setEnabled(false);
            Renovar_Mat.setEnabled(false);
            
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_Remove_Aluno_Da_TurmaActionPerformed

    private void TabelaAlunosDaTurmaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaAlunosDaTurmaMousePressed
        TabelaTodosAlunos.getSelectionModel().clearSelection();
        Remove_Aluno_Da_Turma.setEnabled(true);
        Add_Na_Turma.setEnabled(false);
        String estadoMat = TabelaAlunosDaTurma.getValueAt(TabelaAlunosDaTurma.getSelectedRow(), 10).toString();
        if("pendente".equals(estadoMat)){
            Renovar_Mat.setEnabled(true);
        }else{
            Renovar_Mat.setEnabled(false);
        }
    }//GEN-LAST:event_TabelaAlunosDaTurmaMousePressed

    private void Renovar_MatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Renovar_MatActionPerformed
        try {

            if (ComboTurma.getSelectedIndex() == 0) {

            } else {
                Entidades.Turma turma = (Entidades.Turma) ComboTurma.getSelectedItem();
                Calendar c = Calendar.getInstance();
                c.add(Calendar.YEAR, 0);
                int anoTurma = Integer.parseInt(turma.getAnoLetivo());
                if (anoTurma >= c.getWeekYear()) {

                    //pega qntd de alunos que ja pertencem a turma
                    DAO.AlunoDAO AlDAO = new DAO.AlunoDAO();
                    Long qtd = AlDAO.contarAlunosDeUmaTurma(turma.getIdTurma());

                    long idAluno = Long.decode(TabelaAlunosDaTurma.getValueAt(TabelaAlunosDaTurma.getSelectedRow(), 1).toString());
                    InserirAlunoNaTurma(turma, idAluno);
                    ListarTodosAlunosSemTurma();
                    ListarAlunosDaTurma(turma.getIdTurma());
                    Add_Na_Turma.setEnabled(false);
                    Renovar_Mat.setEnabled(false);

                }else{
                    JOptionPane.showMessageDialog(null, "Atualize o Ano Letivo da turma antes de Renovar Matricula dos alunos.");
                }
            }

        } catch (Exception e) {

        }
    }//GEN-LAST:event_Renovar_MatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add_Na_Turma;
    private javax.swing.JComboBox<Object> ComboTurma;
    private javax.swing.JPanel PainelPesquisar2;
    private javax.swing.JPanel PainelPesquisar6;
    private javax.swing.JPanel Painel_Professor;
    private javax.swing.JButton Remove_Aluno_Da_Turma;
    private javax.swing.JButton Renovar_Mat;
    private javax.swing.JTable TabelaAlunosDaTurma;
    private javax.swing.JTable TabelaTodosAlunos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblNomeCadastro;
    private javax.swing.JLabel qtdAlunos;
    private javax.swing.JLabel qtdMaxAlTurma;
    // End of variables declaration//GEN-END:variables
}
