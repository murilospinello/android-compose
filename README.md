
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
│   ├── remote/            # Retrofit e modelos da API
│   ├── local/             # Room: entidades, DAOs e database
│   └── repository/        # Repositórios que integram dados remotos e locais
├── di/                    # Módulos de injeção do Koin
├── domain/                # Modelos de negócio (caso use uma camada de domínio)
├── ui/
│   ├── screens/           # Telas em Compose organizadas por feature
│   ├── components/        # Componentes visuais reutilizáveis
│   └── theme/             # Tema e estilos do Material 3
├── viewmodel/             # ViewModels com lógica de estado e fluxo de dados
└── MainActivity.kt        # Ponto de entrada da aplicação
````

## 📷 Screenshots

> ... Em andamento

## 📌 Observações

* Arquitetura baseada em MVVM
* Pronto para escalabilidade e testabilidade
* Separação clara de responsabilidades
* Design limpo e responsivo com Material 3

## ✨ Sobre

Este repositório foi criado como parte do meu **portfólio como Desenvolvedor Android Sênior**, com foco em tecnologias modernas e arquitetura robusta. Sugestões e feedbacks são bem-vindos!
