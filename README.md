# BedWars

Olá! Este é um projeto pessoal que consiste em um plugin open-source do minigame Bed Wars para servidores de Minecraft. Este plugin é compatível com a versão 1.8 do jogo. Sinta-se livre para fazer um fork com as suas próprias mudanças, abrir issues ou enviar pull-requests.

## Como utilizar?

Você pode baixar um dos releases já compilados (disponíveis em breve), ou baixar o código fonte e compilar por conta própria.

Caso você opte por compilar o código fonte na sua máquina, será necessário rodar o [BuildTools](https://www.spigotmc.org/wiki/buildtools/) (para a versão 1.8.8) ao menos uma vez.

#### Qual o motivo do BuildTools ser necessário?

O BuildTools é uma ferramenta que cria, na sua máquina, uma jar do Spigot que contém os códigos do Minecraft Server. Criar a jar utilizando o BuildTools faz com que o spigot.org não distribua diretamente o código do Minecraft Server, evitando problemas com direitos autorais.

Quando você rodar o BuildTools, uma jar do Spigot que contém os códigos do Minecraft Server será gerada na sua máquina e adicionada ao diretório ~/.m2/. Esta jar será utilizada como dependência para o projeto.