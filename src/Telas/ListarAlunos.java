package Telas;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class ListarAlunos extends javax.swing.JInternalFrame {

    long MatAlunoSelect;
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
                 a.getCartorio_Nasc(),a.getCidade_Cartorio(),a.getUFnasc()
                     
                   
            });
        }
    }  
    
    
    //Modelo da Tabela Aluno
    DefaultTableModel ModeloTabelaAluno = new DefaultTableModel(  
        new Object[] {"Matricula","Nome","Data Nasc","Sexo","NIS","SUS","Necessidade",
            "EspecificacaoNecessidade","Transporte","CorRaca","Rua","Bairro",
            "Numero","Cidade","Estado",
            
            "Naturalidade","Certidao_nasc","Livro_Nasc"
            ,"Folhas_Nasc","dataExpedicao_Certidao","Cartorio_Nasc","Cidade_Cartorio","Ufnasc"},0){  
            boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false ,false,false
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
    
    Properties config = new Properties();
    String arquivo = "conf.ini";//local do arquivo
    
    public boolean getConf() throws IOException{
        
        try {
            
            config.load(new FileInputStream(arquivo));
            
            return true;
            
            
        } catch (FileNotFoundException ex) {
            return false;
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
                 a.getCartorio_Nasc(),a.getCidade_Cartorio(),a.getUFnasc()
                     
                   
            });
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
                 a.getCartorio_Nasc(),a.getCidade_Cartorio(),a.getUFnasc()
                     
                   
            });
        }
    }
    
    public void gerarDeclaracao() {
        
        String nomeAluno = TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 1).toString();

        //pegar nome da mae
        Entidades.AlunoResponsavel aluResMAE = new Entidades.AlunoResponsavel();
        DAO.AlunoResponsavelDAO aluResMaeDao = new DAO.AlunoResponsavelDAO();
        aluResMAE = aluResMaeDao.consultaMae(Long.decode(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 0).toString()));
        String nomeMae = aluResMAE.getResponsavel().getNome();
        
        //pegar nome do pai
        String nomePai;
        try {
            Entidades.AlunoResponsavel aluResPAI = new Entidades.AlunoResponsavel();
            DAO.AlunoResponsavelDAO aluResPaiDao = new DAO.AlunoResponsavelDAO();
            aluResPAI = aluResPaiDao.consultaPai(Long.decode(TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 0).toString()));
            nomePai = aluResPAI.getResponsavel().getNome();
        } catch (Exception e) {
            nomePai = "PAI DESCONHECIDO";
        }
        
        String dataNasc = TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 2).toString();
        String naturalidade = TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 15).toString();
        String numCertidao = TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 16).toString();
        String livroCertidao = TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 17).toString();
        String folhaCertidao = TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 18).toString();
        String dataExpedicao_Certidao = TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 19).toString();
        String nomeCartorio = TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 20).toString();
        String cidadeCartorio = TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 21).toString();
        String ufNasc = TabelaPesquisarAluno.getValueAt(TabelaPesquisarAluno.getSelectedRow(), 22).toString();
        
        Document document = new Document();
        try {
            FileOutputStream out = new FileOutputStream("declaracao.pdf");
            PdfWriter.getInstance(document, out);
            document.open();

            // ADICIONANDO IMAGEM 

            BaseFont bf = BaseFont.createFont(FontFactory.TIMES_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);
            if(!getConf()){
                Image image = Image.getInstance(jLabel1.getIcon().toString());
                image.setAlignment(Element.ALIGN_CENTER);
                document.add(image);
                Font fonteNormal = new Font(bf, 11);
                Paragraph l1 = new Paragraph("ESTADO DO RIO GRANDE DO NORTE", fonteNormal);
                l1.setAlignment(1);
                document.add(l1);
                Paragraph l2 = new Paragraph("PREFEITURA MUNICIPAL DE SERRINHA", fonteNormal);
                l2.setAlignment(1);
                document.add(l2);
                Paragraph l3 = new Paragraph("SECRETARIA MUNICIPAL DE EDUCAÇÃO", fonteNormal);
                l3.setAlignment(1);
                document.add(l3);
                Paragraph l4 = new Paragraph("CENTRO MUNICIPAL DE EDUCAÇÃO INFANTIL CORAÇÂO DE ESTUDANTE ", fonteNormal);
                l4.setAlignment(1);
                document.add(l4);
                Paragraph l5 = new Paragraph("Rua José Correia de Andrade,S/N. Centro. Serrinha/RN. CEP:59258-000 ", fonteNormal);
                l5.setAlignment(1);
                l5.setSpacingAfter(10);
                document.add(l5);
                LineSeparator separador = new LineSeparator();
                separador.setLineWidth(2);
                document.add(separador);
            }else{
                Image image = Image.getInstance(config.getProperty("imagem"));
                image.setAlignment(Element.ALIGN_CENTER);
                document.add(image);
                Font fonteNormal = new Font(bf, 11);
                Paragraph l1 = new Paragraph(config.getProperty("estado"), fonteNormal);
                l1.setAlignment(1);
                document.add(l1);
                Paragraph l2 = new Paragraph(config.getProperty("prefeitura"), fonteNormal);
                l2.setAlignment(1);
                document.add(l2);
                Paragraph l3 = new Paragraph(config.getProperty("secretaria"), fonteNormal);
                l3.setAlignment(1);
                document.add(l3);
                Paragraph l4 = new Paragraph(config.getProperty("nomeEscola"), fonteNormal);
                l4.setAlignment(1);
                document.add(l4);
                Paragraph l5 = new Paragraph(config.getProperty("rua"), fonteNormal);
                l5.setAlignment(1);
                l5.setSpacingAfter(10);
                document.add(l5);
                LineSeparator separador = new LineSeparator();
                separador.setLineWidth(2);
                document.add(separador);
            }
            


            Font fonteDeclaracao = new Font(bf, 18);
            Paragraph declaracao = new Paragraph("DECLARAÇÃO", fonteDeclaracao);
            declaracao.setAlignment(1);
            declaracao.setSpacingBefore(50);
            declaracao.setSpacingAfter(30);
            document.add(new Paragraph(declaracao));

            Font BOLD;
            Font NORMAL;
            BaseFont timesbd = null;
            BaseFont times = null;
            try {
                try {
                    timesbd = BaseFont.createFont("c:/windows/fonts/ARLRDBD.ttf",
                        BaseFont.WINANSI, BaseFont.EMBEDDED);
                } catch (Exception e) {
                    
                timesbd = BaseFont.createFont("C:\\Windows\\Fonts\\arialbd.ttf",
                        BaseFont.WINANSI, BaseFont.EMBEDDED);
                }
                times = BaseFont.createFont("c:/windows/fonts/Arial.ttf",
                        BaseFont.WINANSI, BaseFont.EMBEDDED);


            } catch (DocumentException e) {
                System.out.println("erro ao carregar fonte");
            } catch (IOException e) {
                System.out.println("erro ao carregar fonte");
            }
            BOLD = new Font(timesbd, 12);
            NORMAL = new Font(times, 12);

            Paragraph text = (new Paragraph("Declaramos para os devidos fins que o (a) aluno (a). ", NORMAL));
            text.add(new Phrase(nomeAluno.toUpperCase(), BOLD));
            text.add(new Phrase(" filho(a) de ", NORMAL));
            text.add(new Phrase(nomeMae.toUpperCase(), BOLD));
            if("PAI DESCONHECIDO".equals(nomePai)){     
            }else{
               text.add(new Phrase(", ", NORMAL));
                text.add(new Phrase(nomePai.toUpperCase(), BOLD)); 
            } 
            text.add(new Phrase(", nascido (a) no dia ", NORMAL));
            text.add(new Phrase(dataNasc.toUpperCase(), BOLD));
            text.add(new Phrase(", natural de ", NORMAL));
            text.add(new Phrase(naturalidade.toUpperCase(), BOLD));
            text.add(new Phrase(", Certidão de nascimento N°: ", NORMAL));
            text.add(new Phrase(numCertidao.toUpperCase(), BOLD));
            text.add(new Phrase(" Livro: ", NORMAL));
            text.add(new Phrase(livroCertidao.toUpperCase(), BOLD));
            text.add(new Phrase(" Folhas: ", NORMAL));
            text.add(new Phrase(folhaCertidao.toUpperCase(), BOLD));
            text.add(new Phrase(" data de Expedição: ", NORMAL));
            text.add(new Phrase(dataExpedicao_Certidao.toUpperCase(), BOLD));
            text.add(new Phrase(", Cartório: ", NORMAL));
            text.add(new Phrase(nomeCartorio.toUpperCase(), BOLD));
            text.add(new Phrase(" :na cidade de: ", NORMAL));
            text.add(new Phrase(cidadeCartorio.toUpperCase(), BOLD));
            text.add(new Phrase(" UF: ", NORMAL));
            text.add(new Phrase(ufNasc.toUpperCase(), BOLD));
            text.add(new Phrase(".", NORMAL));
            text.setFirstLineIndent(30);
            
            //text.add(new Paragraph(".\nCursou ( ) está cursando ( ) o __________________ ( ) ", NORMAL));
            //text.add(new Phrase("do Ensino Infantil estando apto a matricular-se no __________________ do ", NORMAL));
            //text.add(new Phrase("Ensino Fundamental. ", NORMAL));
            
            text.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(text);
            
            Paragraph text2 = (new Paragraph("Cursou ( ) está cursando ( ) o __________________________ ( ) "
                    + " do Ensino Infantil estando apto a matricular-se no __________________________ do Ensino Fundamental.", NORMAL));
            text2.setFirstLineIndent(30);
            //text2.setSpacingBefore(25);
            text2.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(text2);
            
            

            Paragraph text1 = (new Paragraph("Outrossim, declaramos que a sua transferência"
                    + " será expedida no prazo de 30 (trinta) dias a contar desta data.", NORMAL));
            text1.setFirstLineIndent(30);
            text1.setSpacingBefore(25);
            text1.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(text1);

            Font bold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Paragraph as = new Paragraph("Assinatura do Responsável", bold);
            as.setAlignment(1);

            as.setSpacingBefore(80);
            as.setSpacingAfter(25);
            document.add(as);
            Paragraph linha = new Paragraph("___________________________________________");
            linha.setAlignment(1);
            document.add(linha);
            
            Date dataAtual = new Date();
            SimpleDateFormat formatad = new SimpleDateFormat("dd/MM/yyyy");
            
            Paragraph data = (new Paragraph("Serrinha, ",NORMAL));
            data.add(new Phrase(formatad.format( dataAtual ),BOLD));
            data.setAlignment(Element.ALIGN_RIGHT);
            
            data.setSpacingBefore(160);
            document.add(data);
            
            
            document.close();
            Desktop.getDesktop().open(new File("declaracao.pdf"));
            
        } catch (FileNotFoundException | DocumentException ex) {
            System.out.println("erro");
            document.close();
        } catch (IOException ex) {
            System.out.println("erro");
            document.close();
        }finally{
            document.close();
        }
    }
    

    Border borderText; // Criando VARIAVEL DAS BORDAS GLOBAL
    Border borderCombo;
    public ListarAlunos() {
        
        initComponents();
        TabelaPesquisarAluno.setModel(ModeloTabelaAluno);
        
       
        ListarTodosAlunosComEndereco();
        esconderColunas();
        
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

        filtro = new javax.swing.ButtonGroup();
        Painel_Aluno = new javax.swing.JPanel();
        PainelPesquisar = new javax.swing.JPanel();
        Txt_Buscar_ID = new javax.swing.JTextField();
        Txt_Buscar_ID1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaPesquisarAluno = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jRadioButtonNomeProfessor = new javax.swing.JRadioButton();
        jRadioButtonMatriculaProfessor = new javax.swing.JRadioButton();
        lblFiltro = new javax.swing.JLabel();
        btnGerarDeclaracao = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        Painel_Aluno.setPreferredSize(new java.awt.Dimension(815, 551));

        PainelPesquisar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisar Aluno", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        Txt_Buscar_ID.setBackground(new java.awt.Color(240, 240, 240));
        Txt_Buscar_ID.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        Txt_Buscar_ID.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Txt_Buscar_ID.setText("Buscar o Aluno Por Nome");
        Txt_Buscar_ID.setToolTipText("Buscar o Aluno Por Nome");
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
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Data_Nascimento", "Sexo"
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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Selecione um aluno para gerar a sua declaração ");

        filtro.add(jRadioButtonNomeProfessor);
        jRadioButtonNomeProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButtonNomeProfessor.setSelected(true);
        jRadioButtonNomeProfessor.setText("Nome");
        jRadioButtonNomeProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNomeProfessorActionPerformed(evt);
            }
        });

        filtro.add(jRadioButtonMatriculaProfessor);
        jRadioButtonMatriculaProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButtonMatriculaProfessor.setText("Matricula");
        jRadioButtonMatriculaProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMatriculaProfessorActionPerformed(evt);
            }
        });

        lblFiltro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFiltro.setText("Filtrar Por:");

        javax.swing.GroupLayout PainelPesquisarLayout = new javax.swing.GroupLayout(PainelPesquisar);
        PainelPesquisar.setLayout(PainelPesquisarLayout);
        PainelPesquisarLayout.setHorizontalGroup(
            PainelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPesquisarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelPesquisarLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelPesquisarLayout.createSequentialGroup()
                        .addComponent(Txt_Buscar_ID1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonNomeProfessor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonMatriculaProfessor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Txt_Buscar_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PainelPesquisarLayout.setVerticalGroup(
            PainelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPesquisarLayout.createSequentialGroup()
                .addGroup(PainelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButtonNomeProfessor)
                        .addComponent(jRadioButtonMatriculaProfessor)
                        .addComponent(lblFiltro))
                    .addComponent(Txt_Buscar_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Txt_Buscar_ID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGerarDeclaracao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnGerarDeclaracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/disk.png"))); // NOI18N
        btnGerarDeclaracao.setText("Gerar Declaração");
        btnGerarDeclaracao.setEnabled(false);
        btnGerarDeclaracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarDeclaracaoActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Util/serrinha_mini.jpg"))); // NOI18N

        javax.swing.GroupLayout Painel_AlunoLayout = new javax.swing.GroupLayout(Painel_Aluno);
        Painel_Aluno.setLayout(Painel_AlunoLayout);
        Painel_AlunoLayout.setHorizontalGroup(
            Painel_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Painel_AlunoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGerarDeclaracao))
        );
        Painel_AlunoLayout.setVerticalGroup(
            Painel_AlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_AlunoLayout.createSequentialGroup()
                .addComponent(PainelPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btnGerarDeclaracao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(173, Short.MAX_VALUE))
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
    
    String RbNecessidadeEspecial = "nao";//variavel para controle do radioButtonNecessidadeEspecial
        
    String RbTransporte = "nao";//variavel de controle do radio button Transporte
    private void TabelaPesquisarAlunoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaPesquisarAlunoMousePressed
        btnGerarDeclaracao.setEnabled(true);
    }//GEN-LAST:event_TabelaPesquisarAlunoMousePressed

    private void btnGerarDeclaracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarDeclaracaoActionPerformed

        try {
            gerarDeclaracao();
            btnGerarDeclaracao.setEnabled(false);
            TabelaPesquisarAluno.getSelectionModel().clearSelection();
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_btnGerarDeclaracaoActionPerformed

    private void Txt_Buscar_IDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_Txt_Buscar_IDCaretUpdate
        if(RB == "nome"){
            ListarAlunosPeloNomeComLike(Txt_Buscar_ID.getText());
        }else if (RB == "matricula"){
            
            ListarAlunosPorMatComLike(Txt_Buscar_ID.getText());
        }
    }//GEN-LAST:event_Txt_Buscar_IDCaretUpdate

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
    String RB = "nome";
    private void jRadioButtonNomeProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNomeProfessorActionPerformed
        RB = "nome";
        Txt_Buscar_ID.setText("Buscar o Aluno Por Nome");
        ListarTodosAlunosComEndereco();
    }//GEN-LAST:event_jRadioButtonNomeProfessorActionPerformed

    private void jRadioButtonMatriculaProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMatriculaProfessorActionPerformed
        RB = "matricula";
        Txt_Buscar_ID.setText("Buscar o Aluno Por Matricula");
        ListarTodosAlunosComEndereco();
    }//GEN-LAST:event_jRadioButtonMatriculaProfessorActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PainelPesquisar;
    private javax.swing.JPanel Painel_Aluno;
    private javax.swing.JTable TabelaPesquisarAluno;
    private javax.swing.JTextField Txt_Buscar_ID;
    private javax.swing.JTextField Txt_Buscar_ID1;
    private javax.swing.JButton btnGerarDeclaracao;
    private javax.swing.ButtonGroup filtro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButtonMatriculaProfessor;
    private javax.swing.JRadioButton jRadioButtonNomeProfessor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    // End of variables declaration//GEN-END:variables
}
