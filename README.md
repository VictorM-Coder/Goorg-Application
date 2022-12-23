<h1 align="center">
  <img width="250px" title="Logo goorg" alt="Logo goorg" src="/logo-goorg.svg">
</h1>

## 💻 Sobre o Projeto
O sistema a ser criado trata-se de uma aplicação de organização pessoal tal qual softwares como Notion, Trello e Evernote. Nessa aplicação, será possível que os usuários consigam administrar todas as diversas atividades que são realizadas no cotidiano, sejam elas no contexto pessoal, nas tarefas do dia a dia, ou no cenário profissional, com funcionalidades que visem propiciar um maior controle das atividades profissionais, proporcionando uma maior organização sobre o fluxo de trabalho.

Com ferramentas que facilitem a organização pessoal, o objetivo é apresentar funcionalidades essenciais, ao mesmo tempo que apresenta um layout limpo e agradável. Além disso, objetiva-se que a aplicação seja simples de se utilizar, ou seja, autoexplicativa.

## 🚀 Funcionalidades
✅ **Criar Workspaces** - Você pode definir workpaces para categorizar suas atividades
</br>

✅ **Gerenciar Atividades** - Você pode criar e manipular atividades, definindo descrições, datas de início e término, prioridade, dentre outros
</br>

✅ **Gerenciar Tarefas em atividades** - Você pode criar tarefas para subdividir suas atividades e ganhar melhor controle sobre a execução delas
</br>

✅ **Completar taferas com uso de pomodoro** - Você pode completar tarefas enquanto faz uso de um pomodoro para organizar melhor seu tempo

## 🎨 Layout

## 🛠 Tecnologias
#### FrontEnd
- [**React**](https://pt-br.reactjs.org)
- [**Node JS**](https://nodejs.org/en/)
- [**Tailwind**](https://tailwindcss.com)

#### BackEnd
- [**Java**](https://www.java.com/pt-BR/)
- [**SpringBoot**](https://spring.io/projects/spring-boot)
- [**Java Persistence API**](https://www.oracle.com/java/technologies/persistence-jsp.html)
- [**JUnit 5**](https://junit.org/junit5/docs/current/user-guide/)
- [**PostgreSQL**](https://www.postgresql.org)

#### Integração para desktop
- [**Electron**](https://www.electronjs.org)

#### Utilitários
- Protótipo: [**Figma**](https://www.figma.com)
- Editor de código: [**Intellij**](https://www.jetbrains.com/pt-br/idea/) & [**VS Code**](https://code.visualstudio.com)
- Teste de API: [**Insomnia**](https://insomnia.rest/download)
- Gerenciamento de recursos: [**Notion**](https://www.notion.so)


## ✔️ Requisitos
 - JDK 17
 - Springboot 2.7.4
 - Maven 3.8.6
 - Node 16.17 LTS
 - React 18.2.0
 - postgresql configurado

## 📋 Passo a Passo

### 01 - Configurar o arquivo application.properties
Em: *Goorg-Application/goorg-java/src/main/resources/application.properties*

```
spring.datasource.url=<url-database>
spring.datasource.username=<username>
spring.datasource.password=<password>
```

### 02 - Com o maven instalado em sua máquina execute o seguinte comando:
```
mvn package -P src  
```

### 03 - Copie a pasta Targert gerada e cole no seguinte diretório.
*Goorg-Application\goorg-electron\electron*

### 04 - No diretorio Goorg-Application\goorg-electron execute o seguinte comando.
```
npm install
```

### 05 - No diretorio *Goorg-Application\goorg-electron* execute o seguinte comando para gerar o executável.
```
npm run electron:build
```

### 06 - Para instalar o executável entre no seguinte diretório 
*Goorg-Application\goorg-electron\release\1.0.0* e abra o arquivo *goorg_1.0.0*.

## 📸 Autores
<table>
  <tr>
    <td align="center"><a href="https://github.com/danielft2"><img src="https://avatars.githubusercontent.com/u/73781042?v=4" width="100px;" alt="Daniel Almeida"/><br /><sub><b>Daniel Almeida</b></sub></a><br /><a href="https://github.com/danielft2" title="FrontEnd">
            <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/react/react-original.svg" width="20px" />
    </a></td>
    <td align="center"><a href="https://github.com/VictorM-Coder"><img src="https://avatars.githubusercontent.com/u/84944695?v=4" width="100px;" alt="Victor Martins"/><br /><sub><b>Victor Martins</b></sub></a><br /><a href="https://github.com/VictorM-Coder" title="BackEnd">
            <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="20px"/>
    </a></td>
     <td align="center"><a href="https://github.com/JoseVitorNobre"><img src="https://avatars.githubusercontent.com/u/62249331?v=4" width="100px;" alt="José Vitor"/><br /><sub><b>José Vitor</b></sub></a><br /><a href="https://github.com/JoseVitorNobre" title="BackEnd">
     <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="20px"/>
     </a></td>
     <td align="center"><a href="https://github.com/HigorSantiago"><img src="https://avatars.githubusercontent.com/u/93281590?v=4" width="100px;" alt="Higor Santiago"/><br /><sub><b>Higor Santiago</b></sub></a><br /><a href="https://github.com/HigorSantiago" title="Design">
            <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/figma/figma-original.svg" width="20px"/>
     </a></td>
     <td align="center"><a href="https://github.com/alexsonalmeida"><img src="https://avatars.githubusercontent.com/u/101877352?v=4" width="100px;" alt="Higor Santiago"/><br /><sub><b>Alexson Almeida</b></sub></a><br /><a href="https://github.com/alexsonalmeida" title="FrontEnd">
     <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/react/react-original.svg" width="20px" />
     </a></td>
  </tr>
</table>


