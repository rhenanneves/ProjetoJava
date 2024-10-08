*Sistema de Gerenciamento de Restaurante*
===============================
1. Introdução
O sistema de gerenciamento de restaurante é uma aplicação desktop desenvolvida com o objetivo de otimizar o controle das operações do restaurante, incluindo o cadastro e gerenciamento de funcionários, mesas, pratos e bebidas, além da geração de relatórios detalhados para o gerente. O sistema é desenvolvido em Java com integração ao banco de dados via JDBC.
========================================
*2. Objetivos*

*Objetivo Geral:

Criar um sistema que permita ao gerente do restaurante realizar o gerenciamento completo dos recursos operacionais (funcionários, mesas, pratos e bebidas) de maneira eficiente e organizada, além de possibilitar a geração de relatórios.

*Objetivos Específicos:

Permitir o cadastro, edição, exclusão e listagem de funcionários. <br>
Permitir o cadastro, edição, exclusão e listagem de mesas. <br>
Gerenciar o cardápio, permitindo o cadastro, edição, exclusão e listagem de pratos e bebidas.<br>
Fornecer uma interface amigável para geração de relatórios de funcionários e cardápio.<br>
Controlar acesso e operações por diferentes tipos de usuários, com foco no gerente.<br>
=====================================
*3. Funcionalidades do Sistema*
Funcionalidades de Gerenciamento de Recursos:
Cadastro de Funcionários:
Permite o registro de novos funcionários com informações como nome, cargo e salário.
CRUD completo (Create, Read, Update, Delete) para funcionários.

Cadastro de Mesas:
Permite a criação de mesas com a capacidade de assentos.
CRUD completo para mesas.

Cadastro de Pratos:
Gerenciamento do cardápio com opções para adicionar, editar, visualizar e remover pratos.
Campos principais: nome do prato, ingredientes, preço, disponibilidade.

Cadastro de Bebidas:
Gerenciamento de bebidas do cardápio, similar ao cadastro de pratos.
Campos principais: nome da bebida, tamanho (ml), preço, disponibilidade.

Funcionalidades de Relatórios:

Relatório de Funcionários:
Geração de relatórios com informações detalhadas de todos os funcionários registrados.
Relatórios exportados em formato .txt.

Relatório de Pratos e Bebidas:
Geração de relatórios detalhados sobre os pratos e bebidas cadastrados no sistema.
Relatórios exportados em formato .txt.
Funcionalidades de Acesso e Segurança:

Controle de Acesso:
Diferenciação de acessos conforme o perfil de usuário (Gerente).
Apenas o gerente pode acessar e gerenciar cadastros e relatórios.

Interface Gráfica Amigável:
Utilização de Java Swing para a construção de uma interface gráfica intuitiva e responsiva.
=================================
*5. Análise de Riscos*

5.1 Riscos Técnicos
Falta de Conhecimento em Tecnologias: Caso a equipe de desenvolvimento não tenha familiaridade com Java, JDBC ou o framework de interface gráfica utilizado (Swing), isso pode gerar atrasos na implementação e necessidade de treinamentos.

Mitigação: Garantir que todos os membros da equipe sejam treinados previamente nas tecnologias necessárias antes do início da implementação.
Integração com Banco de Dados: Problemas durante a integração do sistema com o banco de dados podem afetar a performance e a confiabilidade das operações de CRUD.

Mitigação: Realizar testes de integração frequentes durante o desenvolvimento e implementar rotinas de backup para evitar perda de dados.
Falta de Testes Adequados: A ausência de testes unitários e de integração pode ocasionar bugs na versão final do sistema.

Mitigação: Implementar um processo contínuo de testes, especialmente em funcionalidades críticas como geração de relatórios e operações de CRUD.

5.2 Riscos de Negócio
Alterações nos Requisitos: Mudanças nos requisitos do sistema ao longo do projeto podem gerar retrabalho e comprometer prazos.

Mitigação: Definir um escopo claro e fechado com o cliente/gerente antes de iniciar a implementação e aplicar um controle de mudanças rigoroso.
Expectativas dos Usuários: Caso as expectativas dos usuários não sejam atendidas, o sistema pode ser rejeitado ou mal utilizado.

Mitigação: Realizar reuniões frequentes com o cliente/gerente para validar funcionalidades durante o desenvolvimento e ajustar de acordo com o feedback.

5.3 Riscos de Cronograma
Atrasos nas Entregas: Problemas técnicos ou indisponibilidade da equipe podem comprometer a entrega do projeto no prazo estipulado.
Mitigação: Manter uma comunicação clara e aberta entre os membros da equipe, com revisões semanais do cronograma e replanejamento caso necessário.

5.4 Riscos Operacionais
Segurança de Dados: Falhas na proteção de dados podem comprometer informações sensíveis dos funcionários e a integridade dos relatórios.

Mitigação: Implementar autenticação adequada (senhas seguras para gerentes), garantir o uso de conexões seguras com o banco de dados e monitoramento de logs de acessos.
Inconsistência nos Relatórios: Se o sistema não gerar relatórios precisos, isso pode prejudicar a tomada de decisões do gerente.

Mitigação: Realizar validação dos dados e testes exaustivos nas funcionalidades de geração de relatórios.
=========================================
*6. Equipe e Recursos*

*Gerente de Projeto	Coordenação geral do projeto, planejamento, alocação de recursos e cronograma.	1

*Analista de Sistemas	Definir os requisitos do sistema e realizar a modelagem de dados e funcionalidades.	1

*Desenvolvedor Back-End	Desenvolvimento das funcionalidades de gerenciamento (cadastros, relatórios, controle de acesso).	2

*Desenvolvedor Front-End	Implementação da interface gráfica do usuário (Java Swing), criação da UI/UX.	1

*DBA (Administrador de Banco de Dados)	Modelagem e gerenciamento do banco de dados, integrações e consultas otimizadas.	1

*Tester/QA	Realização de testes manuais e automatizados, garantia de qualidade e validação das funcionalidades.	1

*Designer	Criação dos mockups da interface gráfica, definição da experiência do usuário.	1

*Diagramas*

Diagrama de classe 

<p>
  <img src="/img/diagramadeclasse.png" alt="logo" width="1000px">
</p>

<br>

Diagrama de Uso

<p>
  <img src="/img/diagramadeuso.png" alt="logo" width="1000px">
</p>

Fluxograma

<p>
  <img src="/img/diagramadefluxo.png" alt="logo" width="1000px">
</p>