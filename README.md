# App CRUD API - Gerenciamento de Pessoas

Um aplicativo Android nativo desenvolvido em Kotlin que implementa operações CRUD (Create, Read, Update, Delete) para gerenciamento de pessoas, utilizando uma API REST criada no MockAPI.io.

## 📱 Funcionalidades

- **Listagem de Pessoas**: Visualização de todas as pessoas cadastradas em uma lista interativa
- **Cadastro**: Adição de novas pessoas ao sistema
- **Edição**: Atualização de dados de pessoas existentes
- **Exclusão**: Remoção de pessoas do sistema
- **Carregamento de Imagens**: Exibição de fotos via URL utilizando Glide

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Kotlin
- **Plataforma**: Android (SDK mínimo não especificado)
- **Arquitetura**: Activity-based
- **Interface**: XML Layouts com ConstraintLayout e LinearLayout
- **RecyclerView**: Para listagem de dados
- **Retrofit**: Para comunicação com a API REST
- **Gson**: Para serialização/deserialização JSON
- **Glide**: Para carregamento e cache de imagens
- **MockAPI.io**: API REST para persistência de dados

## 📋 Estrutura do Projeto

```
app/
├── src/main/java/com/example/app_crud_api/
│   ├── Cadastro.kt          # Activity de cadastro/edição
│   ├── MainActivity.kt      # Activity principal com listagem
│   ├── UserAdapter.kt       # Adapter do RecyclerView
│   ├── UserModel.kt         # Modelo de dados
│   ├── EndPoint.kt          # Interface Retrofit
│   └── RetrofitUtils.kt     # Utilitário para configuração do Retrofit
└── src/main/res/layout/
    ├── activity_main.xml        # Layout da tela principal
    ├── activity_cadastro.xml    # Layout da tela de cadastro
    └── lista_pessoa.xml         # Layout do item da lista
```

## 🌐 API Configuration

O projeto utiliza uma API criada no MockAPI.io com os seguintes endpoints:

- **Base URL**: `https://684ba735ed2578be881bffaf.mockapi.io/Eykel/72400307/`
- **GET** `/Pessoas/` - Lista todas as pessoas
- **GET** `/Pessoas/{id}` - Busca pessoa por ID
- **POST** `/Pessoas/` - Cria nova pessoa
- **PUT** `/Pessoas/{id}` - Atualiza pessoa existente
- **DELETE** `/Pessoas/{id}` - Remove pessoa

### Modelo de Dados (UserModel)

```json
{
  "id": 1,
  "name": "Nome da Pessoa",
  "cidade": "Cidade",
  "pais": "País",
  "foto": "URL da foto"
}
```

## 🚀 Como Executar

### Pré-requisitos

- Android Studio (versão mais recente recomendada)
- SDK do Android configurado
- Dispositivo Android ou emulador para testes

### Instalação

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/app-crud-api.git
```

2. Abra o projeto no Android Studio

3. Aguarde a sincronização das dependências do Gradle

4. Execute o projeto em um dispositivo ou emulador Android

## 📱 Telas do Aplicativo

### Tela Principal (MainActivity)
- Lista todas as pessoas cadastradas
- Botão "Incluir Pessoa" para adicionar novos registros
- Cada item da lista pode ser clicado para edição

### Tela de Cadastro/Edição (Cadastro)
- **Modo Cadastro**: Formulário para adicionar nova pessoa
- **Modo Edição**: Formulário preenchido com dados existentes
- Campos: Nome, Cidade, País, Foto (opcional)
- Validação de campos obrigatórios
- Botões contextuais baseados no modo (Salvar/Cancelar ou Alterar/Deletar/Cancelar)

## 🔧 Dependências Principais

Adicione ao arquivo `build.gradle (Module: app)`:

```gradle
dependencies {
    // Retrofit para requisições HTTP
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    
    // Glide para carregamento de imagens
    implementation 'com.github.bumptech.glide:glide:4.14.2'
    
    // RecyclerView
    implementation 'androidx.recyclerview:recyclerview:1.3.0'
}
```

## 📝 Funcionalidades Detalhadas

### Operações CRUD

- **Create**: Cadastro de novas pessoas com validação de campos obrigatórios
- **Read**: Listagem automática com atualização ao retornar para a tela principal
- **Update**: Edição de dados existentes com carregamento automático do formulário
- **Delete**: Exclusão com confirmação via Toast

### Validações

- Campos Nome, Cidade e País são obrigatórios
- Campo Foto é opcional
- Mensagens de erro via Toast para feedback ao usuário
- Verificação de ID válido para operações de edição e exclusão

### Interface do Usuário

- Design responsivo utilizando ConstraintLayout
- RecyclerView com adapter personalizado
- Carregamento assíncrono de imagens
- Feedback visual através de Toast messages
- Interface adaptativa (botões diferentes para cadastro e edição)

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👨‍💻 Desenvolvedor

Desenvolvido como projeto acadêmico para demonstração de operações CRUD em aplicativos Android com integração a APIs REST.

---

**Nota**: Este projeto utiliza MockAPI.io para fins educacionais. Em um ambiente de produção, considere implementar uma API robusta com autenticação e validações adequadas.
