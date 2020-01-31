package Telas;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Thiago
 */
public class Principal extends javax.swing.JFrame {
    
   Aluno Aluno = new Aluno();
   
   Professor Professor = new Professor();
   
   Gerenciar_Turma Gerenciar_Turma = new Gerenciar_Turma();
   
   Gerenciar_Aluno_Turma Gerenciar_Aluno_Turma = new Gerenciar_Aluno_Turma();
   
   Gerenciar_Prof_Na_Turma Gerenciar_Prof_Na_Turma = new Gerenciar_Prof_Na_Turma();
   
   ListarAlunos Listar_Alunos = new ListarAlunos();
   
   boolean abaAluno = false;
   
   boolean abaProfessor = false;
   
   boolean abaCriar_Turma = false;
   
   boolean abaGerenciar_Aluno_Turma = false;
   
   boolean abaGerenciar_Prof_Turma = false;
   
   boolean abaListarAlunos = false;
    
    public Principal() {
        initComponents();
        
        
        //parte da aba aluno
        AreaDeTrabalho.add(Aluno);
        ((BasicInternalFrameUI)Aluno.getUI()).setNorthPane(null);
        Aluno.setBorder(null);
        
        //parte aba professor
        AreaDeTrabalho.add(Professor);
        ((BasicInternalFrameUI)Professor.getUI()).setNorthPane(null);
        Professor.setBorder(null);
        
        //parte aba Gerenciar Turma
        AreaDeTrabalho.add(Gerenciar_Turma);
        ((BasicInternalFrameUI)Gerenciar_Turma.getUI()).setNorthPane(null);
        Gerenciar_Turma.setBorder(null);
        
        
        //parte aba Gerenciar Aluno na Turma
        AreaDeTrabalho.add(Gerenciar_Aluno_Turma);
        ((BasicInternalFrameUI)Gerenciar_Aluno_Turma.getUI()).setNorthPane(null);
        Gerenciar_Aluno_Turma.setBorder(null);
        
        //parte aba Gerenciar Prof Na turma
        AreaDeTrabalho.add(Gerenciar_Prof_Na_Turma);
        ((BasicInternalFrameUI)Gerenciar_Prof_Na_Turma.getUI()).setNorthPane(null);
        Gerenciar_Prof_Na_Turma.setBorder(null);
        
        //parte aba Listar Alunos
        AreaDeTrabalho.add(Listar_Alunos);
        ((BasicInternalFrameUI)Listar_Alunos.getUI()).setNorthPane(null);
        Listar_Alunos.setBorder(null);
        
        
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GpFitrarAluno = new javax.swing.ButtonGroup();
        NecessidadesEspeciais = new javax.swing.ButtonGroup();
        AreaDeTrabalho = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        Menu_Principal = new javax.swing.JMenuBar();
        MenuAluno = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        MenuProfessor = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        MenuTurma = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Creche");
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        AreaDeTrabalho.setBackground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 715, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 609, Short.MAX_VALUE)
        );

        AreaDeTrabalho.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout AreaDeTrabalhoLayout = new javax.swing.GroupLayout(AreaDeTrabalho);
        AreaDeTrabalho.setLayout(AreaDeTrabalhoLayout);
        AreaDeTrabalhoLayout.setHorizontalGroup(
            AreaDeTrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AreaDeTrabalhoLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        AreaDeTrabalhoLayout.setVerticalGroup(
            AreaDeTrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AreaDeTrabalhoLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        Menu_Principal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        MenuAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/user.png"))); // NOI18N
        MenuAluno.setText("Aluno");
        MenuAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/pencil_add.png"))); // NOI18N
        jMenuItem2.setText("Cadastrar / Atualizar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        MenuAluno.add(jMenuItem2);

        Menu_Principal.add(MenuAluno);

        MenuProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/user_suit.png"))); // NOI18N
        MenuProfessor.setText("Professor");
        MenuProfessor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenuItem3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/group_gear.png"))); // NOI18N
        jMenuItem3.setText("Gerenciar Professores");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        MenuProfessor.add(jMenuItem3);

        Menu_Principal.add(MenuProfessor);

        MenuTurma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/book.png"))); // NOI18N
        MenuTurma.setText("Turma");
        MenuTurma.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/book_add.png"))); // NOI18N
        jMenuItem1.setText("Gerenciar Turma");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        MenuTurma.add(jMenuItem1);

        jMenuItem4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/book_edit.png"))); // NOI18N
        jMenuItem4.setText("Gerenciar Alunos da Turma");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        MenuTurma.add(jMenuItem4);

        jMenuItem6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/book_edit.png"))); // NOI18N
        jMenuItem6.setText("Gerenciar Professores da Turma");
        jMenuItem6.setContentAreaFilled(false);
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        MenuTurma.add(jMenuItem6);

        Menu_Principal.add(MenuTurma);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/paste_plain.png"))); // NOI18N
        jMenu1.setText("Relatório");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenuItem5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/page_white_acrobat.png"))); // NOI18N
        jMenuItem5.setText("Gerar Declaração do Aluno");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        Menu_Principal.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/help.png"))); // NOI18N
        jMenu2.setText("Ajuda");
        jMenu2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenuItem7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/book_open.png"))); // NOI18N
        jMenuItem7.setText("Manual Do Usuário");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);
        jMenu2.add(jSeparator1);

        jMenuItem8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/information.png"))); // NOI18N
        jMenuItem8.setText("Sobre");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        Menu_Principal.add(jMenu2);

        setJMenuBar(Menu_Principal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AreaDeTrabalho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AreaDeTrabalho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (abaCriar_Turma == false) {
            Gerenciar_Turma.show();
            abaCriar_Turma = true;
            Gerenciar_Turma.GerarComboProfessor();
            Gerenciar_Turma.ListarTurmas();
            
            Aluno.hide();
            abaAluno = false;
            
            Professor.hide();
            abaProfessor = false;
            
            Gerenciar_Aluno_Turma.hide();
            abaGerenciar_Aluno_Turma = false;
            
            Gerenciar_Prof_Na_Turma.hide();
            abaGerenciar_Prof_Turma = false;
            
            Listar_Alunos.hide();
            abaListarAlunos = false;
            
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (abaAluno == false) {
            Aluno.show();
            abaAluno = true;
            
            Professor.hide();
            abaProfessor = false;
            
            Gerenciar_Turma.hide();
            abaCriar_Turma = false;
            
            Gerenciar_Aluno_Turma.hide();
            abaGerenciar_Aluno_Turma = false;
            
            Gerenciar_Prof_Na_Turma.hide();
            abaGerenciar_Prof_Turma = false;
            
            Listar_Alunos.hide();
            abaListarAlunos = false;
            
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if (abaProfessor == false) {
            Professor.show();
            abaProfessor = true;
            
            Aluno.hide();
            abaAluno = false;
            
            Gerenciar_Turma.hide();
            abaCriar_Turma = false;
            
            Gerenciar_Aluno_Turma.hide();
            abaGerenciar_Aluno_Turma = false;
            
            Gerenciar_Prof_Na_Turma.hide();
            abaGerenciar_Prof_Turma = false;
            
            Listar_Alunos.hide();
            abaListarAlunos = false;
            
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if (abaGerenciar_Aluno_Turma == false) {
            Gerenciar_Aluno_Turma.show();
            abaGerenciar_Aluno_Turma = true;
            Gerenciar_Aluno_Turma.ListarTodosAlunosSemTurma();
            Gerenciar_Aluno_Turma.GerarComboTurma();
            
            Aluno.hide();
            abaAluno = false;
            
            Gerenciar_Turma.hide();
            abaCriar_Turma = false;
            
            Professor.hide();
            abaProfessor = false;
            
            Gerenciar_Prof_Na_Turma.hide();
            abaGerenciar_Prof_Turma = false;
            
            Listar_Alunos.hide();
            abaListarAlunos = false;
            
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        if (abaGerenciar_Prof_Turma == false) {
            Gerenciar_Prof_Na_Turma.show();
            abaGerenciar_Prof_Turma= true;
            Gerenciar_Prof_Na_Turma.ListarProfessorAtivo();
            Gerenciar_Prof_Na_Turma.GerarComboTurma();

            Aluno.hide();
            abaAluno = false;
            
            Gerenciar_Turma.hide();
            abaCriar_Turma = false;
            
            Professor.hide();
            abaProfessor = false;
            
            Gerenciar_Aluno_Turma.hide();
            abaGerenciar_Aluno_Turma= false;
            
            Listar_Alunos.hide();
            abaListarAlunos = false;

        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if (abaListarAlunos == false) {
            Listar_Alunos.show();
            abaListarAlunos = true;
            
            Aluno.hide();
            abaAluno = false;

            Gerenciar_Turma.hide();
            abaCriar_Turma = false;

            Professor.hide();
            abaProfessor = false;

            Gerenciar_Aluno_Turma.hide();
            abaGerenciar_Aluno_Turma = false;

            Gerenciar_Prof_Na_Turma.hide();
            abaGerenciar_Prof_Turma = false;

        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
       try {
           Desktop.getDesktop().open(new File("manual_usuario.pdf"));
       } catch (IOException ex) {
           System.out.println("erro");
       }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        JOptionPane.showMessageDialog(Aluno,"Versão: 1.1", "Creche", WIDTH);
        
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    public static void main(String args[]) {
 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane AreaDeTrabalho;
    private javax.swing.ButtonGroup GpFitrarAluno;
    private javax.swing.JMenu MenuAluno;
    private javax.swing.JMenu MenuProfessor;
    private javax.swing.JMenu MenuTurma;
    private javax.swing.JMenuBar Menu_Principal;
    private javax.swing.ButtonGroup NecessidadesEspeciais;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
