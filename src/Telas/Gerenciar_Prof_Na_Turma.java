package Telas;

import DAO.ProfessorDAO;
import Entidades.Professor;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thiago
 */
public class Gerenciar_Prof_Na_Turma extends javax.swing.JInternalFrame {

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
          Tabela_Prof_DaTurma.getColumnModel().getColumn(1).setPreferredWidth(5);
          
        while( i < Tabela_Prof_DaTurma.getColumnCount()){
            Tabela_Prof_DaTurma.getColumnModel().getColumn(i).setMinWidth(0);
            Tabela_Prof_DaTurma.getColumnModel().getColumn(i).setMaxWidth(0);
            TabelaTodosProf.getColumnModel().getColumn(i).setMinWidth(0);
            TabelaTodosProf.getColumnModel().getColumn(i).setMaxWidth(0);
            i++;
        }

    }
    
    public void ListarProfessorAtivo(){    
        ProfessorDAO pfDAO = new ProfessorDAO();
        List<Entidades.Professor> listaProfessor = pfDAO.consultarTodosAtivo();
        ModeloTabelaTodosProfessores.setNumRows(0);
        for(Entidades.Professor p : listaProfessor){
             ModeloTabelaTodosProfessores.addRow(new Object[]{
                 p.getNome(),p.getRg(),p.getSexo(),p.getTel1(),
                 p.getTel2(),p.isEstadoAtual(),p.getIdProfessor()
            });
        }
    }
    
    //Modelo Tabela Todos Professores
    DefaultTableModel ModeloTabelaTodosProfessores = new DefaultTableModel(  
        new Object[] {"Nome","rg","sexo","tel1","tel2","estadoAtual","idProfessor",},0){  
            boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false};    
                @Override  
                public boolean isCellEditable(int rowIndex, int columnIndex){  
                    return canEdit [columnIndex];  
                }  
        };
        
        
    //Modelo Tabela Professor Da Turma
    DefaultTableModel ModeloTabelaProfessorDaTurma = new DefaultTableModel(  
        new Object[] {"Nome","rg","sexo","tel1","tel2","estadoAtual","idProfessor",},0){  
            boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false};    
                @Override  
                public boolean isCellEditable(int rowIndex, int columnIndex){  
                    return canEdit [columnIndex];  
                }  
        };
    
    public Gerenciar_Prof_Na_Turma() {
        initComponents();
        GerarComboTurma();
        Tabela_Prof_DaTurma.setModel(ModeloTabelaProfessorDaTurma);
        TabelaTodosProf.setModel(ModeloTabelaTodosProfessores);
        esconderColunas();
        ListarProfessorAtivo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Painel_Professor = new javax.swing.JPanel();
        AddProf = new javax.swing.JButton();
        PainelPesquisar5 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        TabelaTodosProf = new javax.swing.JTable();
        DelProf = new javax.swing.JButton();
        PainelPesquisar8 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        Tabela_Prof_DaTurma = new javax.swing.JTable();
        ComboTurma = new javax.swing.JComboBox<>();
        lblNomeCadastro = new javax.swing.JLabel();

        Painel_Professor.setPreferredSize(new java.awt.Dimension(815, 551));
        Painel_Professor.setRequestFocusEnabled(false);

        AddProf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        AddProf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/arrow_right.png"))); // NOI18N
        AddProf.setEnabled(false);
        AddProf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddProfActionPerformed(evt);
            }
        });

        PainelPesquisar5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Professores", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        TabelaTodosProf.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nome", "RG"
            }
        ));
        TabelaTodosProf.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TabelaTodosProf.setShowVerticalLines(false);
        TabelaTodosProf.getTableHeader().setResizingAllowed(false);
        TabelaTodosProf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TabelaTodosProfMousePressed(evt);
            }
        });
        jScrollPane9.setViewportView(TabelaTodosProf);

        javax.swing.GroupLayout PainelPesquisar5Layout = new javax.swing.GroupLayout(PainelPesquisar5);
        PainelPesquisar5.setLayout(PainelPesquisar5Layout);
        PainelPesquisar5Layout.setHorizontalGroup(
            PainelPesquisar5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPesquisar5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelPesquisar5Layout.setVerticalGroup(
            PainelPesquisar5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        DelProf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        DelProf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/arrow_left.png"))); // NOI18N
        DelProf.setEnabled(false);
        DelProf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DelProfActionPerformed(evt);
            }
        });

        PainelPesquisar8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Professores da Turma", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        Tabela_Prof_DaTurma.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nome", "RG"
            }
        ));
        Tabela_Prof_DaTurma.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Tabela_Prof_DaTurma.setShowVerticalLines(false);
        Tabela_Prof_DaTurma.getTableHeader().setResizingAllowed(false);
        Tabela_Prof_DaTurma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Tabela_Prof_DaTurmaMousePressed(evt);
            }
        });
        jScrollPane11.setViewportView(Tabela_Prof_DaTurma);

        javax.swing.GroupLayout PainelPesquisar8Layout = new javax.swing.GroupLayout(PainelPesquisar8);
        PainelPesquisar8.setLayout(PainelPesquisar8Layout);
        PainelPesquisar8Layout.setHorizontalGroup(
            PainelPesquisar8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPesquisar8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelPesquisar8Layout.setVerticalGroup(
            PainelPesquisar8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPesquisar8Layout.createSequentialGroup()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ComboTurma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        ComboTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboTurmaActionPerformed(evt);
            }
        });

        lblNomeCadastro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNomeCadastro.setText("Turma:");

        javax.swing.GroupLayout Painel_ProfessorLayout = new javax.swing.GroupLayout(Painel_Professor);
        Painel_Professor.setLayout(Painel_ProfessorLayout);
        Painel_ProfessorLayout.setHorizontalGroup(
            Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_ProfessorLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Painel_ProfessorLayout.createSequentialGroup()
                        .addComponent(lblNomeCadastro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboTurma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(PainelPesquisar5, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddProf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DelProf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(PainelPesquisar8, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Painel_ProfessorLayout.setVerticalGroup(
            Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_ProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeCadastro))
                .addGap(12, 12, 12)
                .addGroup(Painel_ProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PainelPesquisar8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Painel_ProfessorLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(AddProf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DelProf))
                    .addComponent(PainelPesquisar5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Painel_Professor, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Painel_Professor, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboTurmaActionPerformed
        try {
            ListarProfessorAtivo();
            Entidades.Turma turma = (Entidades.Turma) ComboTurma.getSelectedItem();
            if(ComboTurma.getSelectedIndex() == 0){
                AddProf.setEnabled(false);
                DelProf.setEnabled(false);
            }
                
            
            ModeloTabelaProfessorDaTurma.setNumRows(0);
            ModeloTabelaProfessorDaTurma.addRow(new Object[]{
                turma.getIdProfessor().getNome(),turma.getIdProfessor().getRg(),turma.getIdProfessor().getSexo(),
                turma.getIdProfessor().getTel1(),turma.getIdProfessor().getTel2(),turma.getIdProfessor().getIdProfessor()
            });
        } catch (Exception e) {
        }
    }//GEN-LAST:event_ComboTurmaActionPerformed

    private void Tabela_Prof_DaTurmaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabela_Prof_DaTurmaMousePressed
        DelProf.setEnabled(true);
        TabelaTodosProf.getSelectionModel().clearSelection();
    }//GEN-LAST:event_Tabela_Prof_DaTurmaMousePressed

    private void DelProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DelProfActionPerformed
       Entidades.Turma turma = (Entidades.Turma) ComboTurma.getSelectedItem();
       turma.setIdProfessor(null);
       DAO.TurmaDAO daoTURMA = new DAO.TurmaDAO();
       daoTURMA.salvar(turma);
       ModeloTabelaProfessorDaTurma.setNumRows(0);
       DelProf.setEnabled(false);
       ComboTurma.setSelectedIndex(0);
       ListarProfessorAtivo();
    }//GEN-LAST:event_DelProfActionPerformed

    private void TabelaTodosProfMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaTodosProfMousePressed
        Entidades.Turma turma = (Entidades.Turma) ComboTurma.getSelectedItem();
        if(ComboTurma.getSelectedIndex() != 0){
            if(turma.getIdProfessor() == null){
                AddProf.setEnabled(true);
            }else{
                AddProf.setEnabled(false);
            }
        }
        Tabela_Prof_DaTurma.getSelectionModel().clearSelection();
        
        
    }//GEN-LAST:event_TabelaTodosProfMousePressed

    private void AddProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddProfActionPerformed
        try {
            Entidades.Turma turma = (Entidades.Turma) ComboTurma.getSelectedItem();
            Entidades.Professor profs = new Entidades.Professor();
            DAO.ProfessorDAO Dpf = new DAO.ProfessorDAO();
            long idSelected = Long.decode(TabelaTodosProf.getValueAt(TabelaTodosProf.getSelectedRow(), 6).toString());

            profs = Dpf.RetornarProfessorPeloID(idSelected);
            turma.setIdProfessor(profs);
            DAO.TurmaDAO daoTURMA = new DAO.TurmaDAO();
            daoTURMA.salvar(turma);
            ListarProfessorAtivo();
            ComboTurma.setSelectedIndex(0);
            
            
            
        } catch (Exception e) {
            System.out.println("erro");
        }
       
    }//GEN-LAST:event_AddProfActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddProf;
    private javax.swing.JComboBox<Object> ComboTurma;
    private javax.swing.JButton DelProf;
    private javax.swing.JPanel PainelPesquisar5;
    private javax.swing.JPanel PainelPesquisar8;
    private javax.swing.JPanel Painel_Professor;
    private javax.swing.JTable TabelaTodosProf;
    private javax.swing.JTable Tabela_Prof_DaTurma;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblNomeCadastro;
    // End of variables declaration//GEN-END:variables
}
