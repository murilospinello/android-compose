# 📱 Projeto Android com Jetpack Compose – Arquitetura MVVM

Este é um projeto Android nativo desenvolvido com **Jetpack Compose**, utilizando a arquitetura **MVVM (Model-View-ViewModel)**, com o objetivo de demonstrar boas práticas de desenvolvimento mobile moderno. O projeto serve como um case técnico para meu portfólio.

## 🚀 Tecnologias e Bibliotecas Utilizadas

- **Jetpack Compose** – UI declarativa e moderna do Android
- **Kotlin** – Linguagem principal
- **Koin** – Injeção de dependência simples e eficaz
- **Retrofit** – Comunicação com APIs RESTful
- **Room Database** – Persistência local com SQLite
- **Coroutines + Flow** – Programação assíncrona e reativa
- **Navigation Compose** – Navegação entre telas com segurança e reatividade
- **Material 3** – Componentes visuais seguindo as diretrizes do Google

## 💡 Objetivo do Projeto

Demonstrar:

- Implementação da arquitetura MVVM
- Uso de Compose com navegação e gerenciamento de estado
- Integração com API externa (via Retrofit)
- Persistência local com Room
- Separação de camadas e responsabilidades
- Reutilização de componentes
- Práticas modernas de injeção de dependência com Koin

## 🗂 Estrutura do Projeto (Padrão MVVM)

```plaintext
├── data/
│   ├── local/                 # Fontes de dados mockadas
│   └── repository/            # Implementações dos repositórios
├── domain/
│   ├── model/                 # Modelos de domínio (CallItem, ChatItem, etc.)
│   ├── repository/            # Interfaces dos repositórios
│   └── usecase/               # Casos de uso (GetXUseCase)
├── ui/
│   └── home/
│       ├── calls/
│       ├── chats/
│       └── status/
│   └── theme/                 # Tema e dimensões do Material 3
├── MainActivity.kt            # Ponto de entrada da aplicação
└── di/                        # Módulos do Koin
```

## 📷 Screenshots

> ... Em andamento

## 🧾 Classes Criadas e Atualizadas

### 🔹 `MainActivity.kt`
- Tela principal com `HorizontalPager` e `Scaffold`, alternando entre as abas: **Conversas**, **Status**, e **Chamadas**.

### 🔹 `WhatsAppTab.kt`
- `sealed class` que representa cada aba com título e ícone.

### 🔹 `StringUtils.kt`
- Função utilitária `emptyString()` usada na ViewModel.

---

### 🔹 📁 Chats
- `ChatItem.kt`: Modelo de dados para as conversas.
- `ChatsScreen.kt`: Tela da aba **Conversas** com `LazyColumn`.
- `ChatsViewModel.kt`: ViewModel que expõe o fluxo de dados de conversas.
- `ChatsDataSourceMock.kt`: Fonte de dados mockada com `sampleChats`.
- `ChatsRepository.kt`: Interface de repositório de conversas.
- `ChatsRepositoryImpl.kt`: Implementação concreta do repositório.
- `GetChatsUseCase.kt`: Caso de uso que retorna as conversas.

---

### 🔹 📁 Status
- `StatusItem.kt`: `sealed class` com `MyStatus` e `ContactStatus`.
- `StatusScreen.kt`: Tela com status do usuário e dos contatos.
- `StatusViewModel.kt`: ViewModel para controle da tela de status.
- `StatusDataSourceMock.kt`: Fonte de dados simulada.
- `StatusRepository.kt`: Interface de repositório de status.
- `StatusRepositoryImpl.kt`: Implementação concreta.
- `GetStatusUseCase.kt`: Caso de uso que fornece os dados de status.

---

### 🔹 📁 Calls
- `CallItem.kt`: `sealed class` com tipos `Favorite` e `Recent`.
- `CallsScreen.kt`: Tela organizada por seções (Favoritos e Recentes).
- `CallsViewModel.kt`: ViewModel usando `StateFlow` e `catch` para tratar erros.
- `CallsDataSourceMock.kt`: Dados mockados simulando chamadas.
- `CallsRepository.kt`: Interface para chamadas.
- `CallsRepositoryImpl.kt`: Implementação usando o mock.
- `GetCallsUseCase.kt`: Caso de uso para expor a lista de chamadas.

---

### 🔹 `Dimens.kt`
- Objeto que centraliza os valores de `dp` utilizados nas telas.

---

## 📌 Observações

* Arquitetura baseada em MVVM com camadas separadas
* Camadas `data`, `domain` e `ui` seguem o padrão de Clean Architecture
* `ViewModels` individuais por feature
* `UseCases` promovem isolamento da lógica de negócio
* Mock data permite exibição funcional mesmo sem backend

## ✨ Sobre

Este repositório foi criado como parte do meu **portfólio como Desenvolvedor Android Sênior**, com foco em tecnologias modernas e arquitetura robusta. Sugestões e feedbacks são bem-vindos!
