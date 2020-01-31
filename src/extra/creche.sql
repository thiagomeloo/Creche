-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 31-Jan-2020 às 16:41
-- Versão do servidor: 10.4.11-MariaDB
-- versão do PHP: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `creche`
--

create database creche;

use creche;

-- --------------------------------------------------------

--
-- Estrutura da tabela `aluno`
--

CREATE TABLE `aluno` (
  `MATRICULA` bigint(20) NOT NULL,
  `CARTORIO_NASC` varchar(255) DEFAULT NULL,
  `CERTIDAO_NASC` varchar(255) DEFAULT NULL,
  `CIDADE_CARTORIO` varchar(255) DEFAULT NULL,
  `FOLHAS_NASC` varchar(255) DEFAULT NULL,
  `LIVRO_NASC` varchar(255) DEFAULT NULL,
  `NIS` varchar(255) DEFAULT NULL,
  `NATURALIDADE` varchar(255) DEFAULT NULL,
  `UFNASC` varchar(255) DEFAULT NULL,
  `COR` varchar(255) DEFAULT NULL,
  `CPF` varchar(255) NOT NULL,
  `DATAEXPEDICAO_CERTIDAO` date DEFAULT NULL,
  `DATANASCIMENTO` date DEFAULT NULL,
  `ESPECIFICACAONECESSIDADESESPECIAIS` varchar(255) DEFAULT NULL,
  `IDSUS` varchar(255) DEFAULT NULL,
  `NECESSIDADESESPECIAIS` tinyint(1) DEFAULT 0,
  `NOME` varchar(255) DEFAULT NULL,
  `SEXO` varchar(255) DEFAULT NULL,
  `STATUSMATRICULA` varchar(255) DEFAULT NULL,
  `UTILIZATRANSPORTE` tinyint(1) DEFAULT 0,
  `ENDERECO_IDENDERECO_IDENDERECO` bigint(20) DEFAULT NULL,
  `TURMA_IDTURMA_IDTURMA` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `alunoresponsavel`
--

CREATE TABLE `alunoresponsavel` (
  `ID` bigint(20) NOT NULL,
  `ALUNO_MATRICULA` bigint(20) DEFAULT NULL,
  `RESPONSAVEL_IDRESPONSAVEL` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
--

CREATE TABLE `endereco` (
  `IDENDERECO` bigint(20) NOT NULL,
  `BAIRRO` varchar(255) DEFAULT NULL,
  `CIDADE` varchar(255) DEFAULT NULL,
  `ESTADO` varchar(255) DEFAULT NULL,
  `NUMERO` int(11) DEFAULT NULL,
  `RUA` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `matricula`
--

CREATE TABLE `matricula` (
  `IDMATRICULA` bigint(20) NOT NULL,
  `DATA_MATRICULA` date DEFAULT NULL,
  `ALUNO_MATRICULA_MATRICULA` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `professor`
--

CREATE TABLE `professor` (
  `IDPROFESSOR` bigint(20) NOT NULL,
  `ESTADOATUAL` tinyint(1) DEFAULT 0,
  `NOME` varchar(255) DEFAULT NULL,
  `RG` varchar(255) NOT NULL,
  `SEXO` varchar(255) DEFAULT NULL,
  `TEL1` varchar(255) DEFAULT NULL,
  `TEL2` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `responsavel`
--

CREATE TABLE `responsavel` (
  `IDRESPONSAVEL` bigint(20) NOT NULL,
  `NOME` varchar(255) DEFAULT NULL,
  `ORGAOEXPEDITOR` varchar(255) DEFAULT NULL,
  `PARENTESCO` varchar(255) DEFAULT NULL,
  `PROFISSAO` varchar(255) DEFAULT NULL,
  `RG` varchar(255) NOT NULL,
  `SEXO` varchar(255) DEFAULT NULL,
  `TEL` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `turma`
--

CREATE TABLE `turma` (
  `IDTURMA` bigint(20) NOT NULL,
  `ANOLETIVO` varchar(255) DEFAULT NULL,
  `FAIXAETARIA` varchar(255) DEFAULT NULL,
  `NOME` varchar(255) DEFAULT NULL,
  `QUANTIDADEMAXIMA` int(11) DEFAULT NULL,
  `TURNO` varchar(255) DEFAULT NULL,
  `IDPROFESSOR_IDPROFESSOR` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `aluno`
--
ALTER TABLE `aluno`
  ADD PRIMARY KEY (`MATRICULA`),
  ADD UNIQUE KEY `CPF` (`CPF`),
  ADD KEY `FK_ALUNO_ENDERECO_IDENDERECO_IDENDERECO` (`ENDERECO_IDENDERECO_IDENDERECO`),
  ADD KEY `FK_ALUNO_TURMA_IDTURMA_IDTURMA` (`TURMA_IDTURMA_IDTURMA`);

--
-- Índices para tabela `alunoresponsavel`
--
ALTER TABLE `alunoresponsavel`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_ALUNORESPONSAVEL_ALUNO_MATRICULA` (`ALUNO_MATRICULA`),
  ADD KEY `FK_ALUNORESPONSAVEL_RESPONSAVEL_IDRESPONSAVEL` (`RESPONSAVEL_IDRESPONSAVEL`);

--
-- Índices para tabela `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`IDENDERECO`);

--
-- Índices para tabela `matricula`
--
ALTER TABLE `matricula`
  ADD PRIMARY KEY (`IDMATRICULA`),
  ADD KEY `FK_MATRICULA_ALUNO_MATRICULA_MATRICULA` (`ALUNO_MATRICULA_MATRICULA`);

--
-- Índices para tabela `professor`
--
ALTER TABLE `professor`
  ADD PRIMARY KEY (`IDPROFESSOR`),
  ADD UNIQUE KEY `RG` (`RG`);

--
-- Índices para tabela `responsavel`
--
ALTER TABLE `responsavel`
  ADD PRIMARY KEY (`IDRESPONSAVEL`),
  ADD UNIQUE KEY `RG` (`RG`);

--
-- Índices para tabela `turma`
--
ALTER TABLE `turma`
  ADD PRIMARY KEY (`IDTURMA`),
  ADD KEY `FK_TURMA_IDPROFESSOR_IDPROFESSOR` (`IDPROFESSOR_IDPROFESSOR`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `aluno`
--
ALTER TABLE `aluno`
  MODIFY `MATRICULA` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `alunoresponsavel`
--
ALTER TABLE `alunoresponsavel`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `endereco`
--
ALTER TABLE `endereco`
  MODIFY `IDENDERECO` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `matricula`
--
ALTER TABLE `matricula`
  MODIFY `IDMATRICULA` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `professor`
--
ALTER TABLE `professor`
  MODIFY `IDPROFESSOR` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `responsavel`
--
ALTER TABLE `responsavel`
  MODIFY `IDRESPONSAVEL` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `turma`
--
ALTER TABLE `turma`
  MODIFY `IDTURMA` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `aluno`
--
ALTER TABLE `aluno`
  ADD CONSTRAINT `FK_ALUNO_ENDERECO_IDENDERECO_IDENDERECO` FOREIGN KEY (`ENDERECO_IDENDERECO_IDENDERECO`) REFERENCES `endereco` (`IDENDERECO`),
  ADD CONSTRAINT `FK_ALUNO_TURMA_IDTURMA_IDTURMA` FOREIGN KEY (`TURMA_IDTURMA_IDTURMA`) REFERENCES `turma` (`IDTURMA`);

--
-- Limitadores para a tabela `alunoresponsavel`
--
ALTER TABLE `alunoresponsavel`
  ADD CONSTRAINT `FK_ALUNORESPONSAVEL_ALUNO_MATRICULA` FOREIGN KEY (`ALUNO_MATRICULA`) REFERENCES `aluno` (`MATRICULA`),
  ADD CONSTRAINT `FK_ALUNORESPONSAVEL_RESPONSAVEL_IDRESPONSAVEL` FOREIGN KEY (`RESPONSAVEL_IDRESPONSAVEL`) REFERENCES `responsavel` (`IDRESPONSAVEL`);

--
-- Limitadores para a tabela `matricula`
--
ALTER TABLE `matricula`
  ADD CONSTRAINT `FK_MATRICULA_ALUNO_MATRICULA_MATRICULA` FOREIGN KEY (`ALUNO_MATRICULA_MATRICULA`) REFERENCES `aluno` (`MATRICULA`);

--
-- Limitadores para a tabela `turma`
--
ALTER TABLE `turma`
  ADD CONSTRAINT `FK_TURMA_IDPROFESSOR_IDPROFESSOR` FOREIGN KEY (`IDPROFESSOR_IDPROFESSOR`) REFERENCES `professor` (`IDPROFESSOR`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
