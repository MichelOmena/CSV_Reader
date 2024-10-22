package ProjetoCSV;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Map;

public class Main {
    private static final String ADMIN_PASSWORD = "11071995";  // Password do Administrador
    private static final String FILE_PATH = "src/ProjetoCSV/files/GameStart_V2.csv";

    public static void main(String[] args) {
        // Primeira parte reconhecer e encontrar o file (lembre-se dos possiveis erros try,catch)
        // Tratamento de exceção para arquivo não encontrado
        try {
            menuApp("src/ProjetoCSV/files/GameStartV2.csv");
        } catch (FileNotFoundException exception) { //erro porque?
            ArquivoNaoEncontrado(exception);
        } catch (InputMismatchException mismatchException) {
            ValorNaoEsperado(mismatchException);
        }
    }

    public static void menuApp(String filePath) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        int opcaoMenu; //Criacao da Variavel para guardar a opcao escolhida
        do {
            MenuInicial();
            opcaoMenu = input.nextInt();         //Input da variavel

            switch (opcaoMenu) {                 // switch cases para o MenuApp
                case 1:
                    loginAdministrador();        //Buscar o metodo LoginAdministrador
                    break;
                case 2:
                    menuCliente(filePath);               //Buscar o metodo menuCliente
                    break;

                case 0:
                    System.out.println("Encerrando o programa");
                    break;
                default:
                    System.out.println("Opção inválida. Insira uma opcao valida!.");
            }
        } while (opcaoMenu != 0);// Se ele optar pelo 0 o programa encerra (!= diferente)
    }

    /**
     * Método para menu inicial
     *
     * @throws FileNotFoundException
     */
    private static void MenuInicial() {
        System.out.println("======================MENU LOGIN========================");
        System.out.println("|\t\t\t\t1-LOGIN ADMINISTRADOR \t\t\t|");
        System.out.println("|\t\t\t\t2-LOGIN CLIENTE \t\t\t\t|");
        System.out.println("|\t\t\t\t0 -Sair do programa \t\t\t|");
        System.out.println("=========================================================");

        System.out.println("Escolha sua opcao: ");
    }


    /**
     * Método para tramamento de erros FileNotFoundException
     *
     * @param exception- Diretorio do arquivo
     * @throws FileNotFoundException
     */

    private static void ArquivoNaoEncontrado(FileNotFoundException exception) {
        System.err.println(exception.getMessage());
        System.out.println("Continuando o programa...");
    }

    /**
     * Método para tramamento de erros InputMismatchException
     *
     * @throws FileNotFoundException
     */
    private static void ValorNaoEsperado(InputMismatchException mismatchException) {
        System.err.println(mismatchException.getMessage());
        System.out.println("Continuando programa...");
    }

    /**
     * Método para Login do administrador
     *
     * @throws FileNotFoundException
     */
    public static void loginAdministrador() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        boolean acessoAutorizado = false;
        int opcaoMenu = -1;                   //Iniciei com um valor negativo pra nao interferir no switch
        //Caminho relativo ao pacote ProjetoCSV
        String filePath = FILE_PATH;
        //Caso a passe esteja errada ele volta a pedir
        while (!acessoAutorizado) {          //chave de abertura do while

            System.out.println("======================MENU ADMINISTRADOR LOGIN========================");
            System.out.println(" |\t\t\t\tBEM VINDO ADMINISTRADOR\t\t\t\t\t|");
            System.out.println("=======================================================================");

            //loopizinho para pedir a senha quando a passe estiver errada
            boolean senhaIncorreta = true;
            while (senhaIncorreta) {
                System.out.print("Insira sua password: ");
                String novaSenha = scanner.nextLine();
                if (novaSenha.equals("0")) {
                    // Se digitar 0 ele volta ao menu login
                    return;
                } else if (novaSenha.equals(ADMIN_PASSWORD)) {
                    //Se estive tudo certinho
                    senhaIncorreta = false;
                    acessoAutorizado = true;
                } else {
                    System.out.println("Password incorreta. Tente novamente. 0 para Menu login");      //Se der algum problema na passe, continua no loop
                }
            }


            while (acessoAutorizado) {
                // Exibir as opções para o usuário
                System.out.println(" |\t\t\t\t\t\t ESCOLHA A OPCAO DESEJADA \t\t\t\t\t|");
                System.out.println(" |\t\t\t\t\t\t1 - Imprimir Conteúdo \t\t\t\t\t\t|");
                System.out.println(" |\t\t\t\t\t\t2 - Lucro liquido dos jogos 20% \t\t\t\t\t\t\t|");
                System.out.println(" |\t\t\t\t\t\t3 - Receita Bruta em Vendas dos Jogos \t\t\t\t\t|");
                System.out.println(" |\t\t\t\t\t\t4 - Consultar Cliente \t\t\t\t\t\t|");
                System.out.println(" |\t\t\t\t\t\t5 - Jogo mais vendido \t\t\t\t\t\t|");
                System.out.println(" |\t\t\t\t\t\t6 - Melhor Cliente \t\t\t\t\t\t\t|");
                System.out.println(" |\t\t\t\t\t\t0 - Voltar ao Menu Login\t\t\t\t\t|");

                //Solicitando a escolha no switch
                System.out.println("Digite a opção desejada: ");
                opcaoMenu = scanner.nextInt();
                scanner.nextLine(); //limpar o buffer de entrada após a leitura

                //Construcao de cada opcao, assim fica mais bonitinho.
                switch (opcaoMenu) {
                    case 1:
                        ImprimirConteudo(filePath);                               //Chamando o metodo ImprimirContato
                        break;
                    case 2:
                        double lucroTotal = LucroGames(filePath);                 //Chamando o metodo LucroGames(precisou de logica diferente)
                        System.out.println(String.format("Lucro total de vendas dos jogos: %.2f", lucroTotal) + "€"); // Deu muitos numero vou editar com o format
                        System.out.println("Deseja Continuar? Digite Sim ou Não ? :");
                        String continuar = scanner.nextLine().toLowerCase();      // Ler a resposta do usuário , toLowerCase tem um tratamento

                        if (continuar.equals("nao")) {
                            System.out.println("Obrigado e volte sempre!");
                            System.exit(0);
                        }
                        break;
                    case 3: //Total de Vendas
                        ValorTotaldeVendas(filePath);                             //Chamando o metodo ValorTotaldeVendas
                        break;
                    case 4:
                        ConsultarCliente(filePath);                              //Chamando o metodo ConsultarCliente
                        break;
                    //Descomentar
                    case 5:
                        JogoXPTO(filePath);                                     //Chamando meu metodo relativo ao produto mais vendido
                        break;
                    case 6:
                        MelhorCliente(filePath);                                //Chamando o metodo MelhorCliente
                        break;
                    case 0:
                        System.out.println("Retornando ao Menu login");
                        return;                                                //um return pra voltar ao menu login
                    default:
                        System.out.println("Opção inválida. Tente novamente.");


                }//chave de fechamento do switch
            }
            while (opcaoMenu != 0) ; // chave de fechamento do while
        }
    }

    /**
     * Método para imprimir no console arquivo "GameStartV2"
     *
     * @param filePath- Diretorio do arquivo
     * @throws FileNotFoundException
     */
    public static void ImprimirConteudo(String filePath) throws FileNotFoundException {

        //Testando a leitura da "rota" dentro do metodo
        System.out.println(filePath);


        //Tratamento da excecao em caso de nao encontrado
        try {
            //Instancia do arquivo GameStartV2.csv
            File menuApp = new File(filePath);

            //Scanner para leitura do arquivo
            Scanner lerArquivo = new Scanner(menuApp);

            //Ciclo para imprimir no conso le cada linha do arquivo
            while (lerArquivo.hasNextLine()) {
                System.out.println(lerArquivo.nextLine());
            }
            //Fechamento do arquivo
            lerArquivo.close();
        } catch (FileNotFoundException exception) {
            exception.getMessage();
            throw new FileNotFoundException("Arquivo nao encontrado");
        } catch (InputMismatchException mismatchException) {
            mismatchException.getMessage();
            throw new InputMismatchException("Erro de entrada");
        }
    }


    /**
     * Método para imprimir valor total de vendas
     *
     * @param filePath- Diretorio do arquivo
     * @throws FileNotFoundException
     */
    public static void ValorTotaldeVendas(String filePath) {
        int NumeroTotaldeVendas = 0;  //Variaveis para o armazenar
        double ValorTotalVendas = 0.0;
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);


            //Ignorar a primeira linha (cabecalho)
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // Loop para percorrer o arquivo
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(";");

                if (partes.length >= 9) {
                    NumeroTotaldeVendas++; //Incrementar o número de vendas
                    ValorTotalVendas += Double.parseDouble(partes[8]);
                }
            }
            scanner.close(); //Fechar

            System.out.println(String.format("Numero total de jogos vendidos: %d Jogos", NumeroTotaldeVendas));
            System.out.println(String.format("Valor total de vendas(bruto): %.2f €", ValorTotalVendas));
        } catch (FileNotFoundException exception) {
            System.err.println(exception.getMessage());
        } catch (InputMismatchException exception) {
            System.out.println(exception.getMessage());
        }

    }

    /**
     * Método para mostrar lucro de 20% em cada jogo da GameStart
     *
     * @param filePath- Diretorio do arquivo
     * @throws FileNotFoundException
     */
    public static double LucroGames(String filePath) {
        double lucroTotal = 0.0;  // Variavel para armazenar o lucro da empresa
        try {
            File menuApp = new File(filePath);
            Scanner scanner = new Scanner(menuApp);
            if (scanner.hasNextLine()) {      //Ignorar o cabecalho
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {  //Loop para percorrer as minhas linhas
                String linha = scanner.nextLine();
                String[] partes = linha.split(";");

                //Verificar se tudo esta ok
                if (partes.length >= 9) {
                    //Calcular o lucro para cada venda e soma na linha
                    double valorVenda = Double.parseDouble(partes[8]);
                    double lucro = valorVenda * 0.2; //Calcular a porcetagem
                    lucroTotal += lucro;
                }
            }

            scanner.close();
        } catch (FileNotFoundException exception) {
            System.err.println("Arquivo nao encontrado" + exception.getMessage());
        }
        return lucroTotal;
    }


    /**
     * Método para consultar o cliente
     *
     * @param filePath- Diretorio do arquivo
     * @throws FileNotFoundException
     */

    public static void ConsultarCliente(String filePath) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o ID do cliente (1 a 58) : ");
        int idCliente = input.nextInt();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            //Como já sabemos ignorar o cabecalho
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            //Indicacao se o cliente foi encontrado
            boolean clienteEncontrado = false;
            //Loopzinho para percorrer as linhas
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(";");

                // Check list
                if (partes.length >= 3) {
                    int idClienteAtual = Integer.parseInt(partes[1]);

                    //Verificar se o ID do cleinte na linha é o mesmo que o ID fornecido
                    if (idClienteAtual == idCliente) {
                        System.out.println("Nome: " + partes[2]);
                        System.out.println("Contacto: " + partes[3]);
                        System.out.println("Email: " + partes[4]);

                        //Confirmar que o cliente foi encontrado
                        clienteEncontrado = true;
                        break; // Esse break para quando encontramos e sai do loop
                    }
                }
            }
            //Verifica se o cliente nao foi encontrado
            if (!clienteEncontrado) {
                System.out.println("Cliente nao esta na BASE DE DADOS");
            }
            scanner.close();
        } catch (FileNotFoundException exception) {
            System.err.println(exception.getMessage());
        } catch (InputMismatchException exception) {
            System.err.println(exception.getMessage());
        }
    }

    /**
     * Método para descobrir o melhor cliente
     *
     * @param filePath- Diretorio do arquivo
     * @throws FileNotFoundException
     */

    public static void JogoXPTO(String filePath) {  //Falta esse
        String jogoMaisVendido = "";
        int quantidadeMaisVendida = 0;
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) { //Como sempre ignorar o cabecalho
                scanner.nextLine();
            }

            Map<String, Integer> vendasPorJogo = new HashMap<>();  // Mapa para armazenar a quantidade de vendas por jogo

            //Loop para percorrer o arquivo
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine(); // Aqui vai ler a linha
                String[] partes = linha.split(";"); //Vai dar um split a cada ; assim separa a informacao

                if (partes.length >= 2) {
                    String nomeJogo = partes[partes.length - 2];

                    // Atualiza o meu mapa de vendas por jogo
                    vendasPorJogo.put(nomeJogo, vendasPorJogo.getOrDefault(nomeJogo, 0) + 1);
                }
            }
            // Encontrar o jogo mais vendido
            for (Map.Entry<String, Integer> entry : vendasPorJogo.entrySet()) {
                String jogo = entry.getKey();
                int quantidadeVendida = entry.getValue();

                if (quantidadeVendida > quantidadeMaisVendida) {
                    quantidadeMaisVendida = quantidadeVendida;
                    jogoMaisVendido = jogo;
                }
            }

            scanner.close(); //Fechar

            if (!jogoMaisVendido.isEmpty()) {
                System.out.println("O jogo mais vendido é " + jogoMaisVendido + " com " + quantidadeMaisVendida + " vendas.");
            } else {
                System.out.println("Nenhum jogo encontrado no arquivo."); // Em caso de algum problema
            }
        } catch (FileNotFoundException exception) {
            System.err.println(exception.getMessage());
        } catch (InputMismatchException exception) {
            System.out.println(exception.getMessage());
        }
    }


    /**
     * Método para descobrir o melhor cliente
     *
     * @param filePath- Diretorio do arquivo
     * @throws FileNotFoundException
     */

    public static void MelhorCliente(String filePath) {
        String melhorCliente = "";
        int quantidadeCompradaPeloMelhorCliente = 0;
        String emailMelhorCliente = "";
        String contatoMelhorCliente = "";

        System.out.println("Identificando o melhor cliente...");
        System.out.println("=========Aguarde==========");

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // Ignorar o cabeçalho
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            Map<String, Integer> jogosPorCliente = new HashMap<>();

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(";");

                if (partes.length >= 9) {
                    String nomeCliente = partes[2];
                    String email = partes[4];
                    String contato = partes[3];
                    String nomeJogo = partes[7];

                    jogosPorCliente.put(nomeCliente, jogosPorCliente.getOrDefault(nomeCliente, 0) + 1);

                    if (jogosPorCliente.get(nomeCliente) > quantidadeCompradaPeloMelhorCliente) {
                        quantidadeCompradaPeloMelhorCliente = jogosPorCliente.get(nomeCliente);
                        melhorCliente = nomeCliente;
                        emailMelhorCliente = email;
                        contatoMelhorCliente = contato;
                    }
                }
            }
            scanner.close();

            if (!melhorCliente.isEmpty()) {
                System.out.println("Melhor Cliente: ");
                System.out.println("Nome: " + melhorCliente);
                System.out.println("Email: " + emailMelhorCliente);
                System.out.println("Contato: " + contatoMelhorCliente);

                // Mostrar todos os jogos comprados pelo melhor cliente
                System.out.println("Jogos comprados: ");
                exibirJogosComprados(filePath, melhorCliente);
            } else {
                System.out.println("Nenhum cliente encontrado no arquivo.");
            }
        } catch (FileNotFoundException exception) {
            System.err.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }


    /**
     * Método para exibir os jogos comprados
     *
     * @param filePath- Diretorio do arquivo
     * @throws FileNotFoundException
     */

    private static void exibirJogosComprados(String filePath, String cliente) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // Ignorar o cabeçalho
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(";");
                String nomeCliente = partes[2];
                String nomeJogo = partes[7];

                if (nomeCliente.equals(cliente)) {
                    System.out.println("- " + nomeJogo);
                }
            }

            scanner.close();
        } catch (FileNotFoundException exception) {
            System.err.println(exception.getMessage());
        }
    }


    /**
     * Método para o menu do cliente
     *
     * @param filePath- Diretorio do arquivo
     * @throws FileNotFoundException
     */

    public static void menuCliente(String filePath) {   //Talvez possa ser necessario o filePath futuramente na evolucao do programa
        Scanner scanner = new Scanner(System.in);

        //Talvez criar uma variavel para definir o tamanho do array?
        int tamanhoArray = 2;
        String[] clienteInformacao = new String[tamanhoArray];
        int[] ArrayContacto = new int[1];

        System.out.println("======================MENU CLIENTE========================");
        System.out.println(" |\t\t\t\t\t 1-REGISTRAR CLIENTE \t\t\t\t|");
        System.out.println(" |\t\t\t\t\t 2-PROCURAR ESTACIONAMENTO \t\t\t\t|");
        System.out.println(" |\t\t\t\t\t 3-PESQUISAR POR EDITORA \t\t\t\t|");
        System.out.println(" |\t\t\t\t\t 0-VOLTAR AO MENU \t\t\t\t|");
        System.out.println("==========================================================");

        System.out.println("Escolha sua opcao");
        int escolha = scanner.nextInt();
        // scanner.nextList(); // Limpar o tal buffer


        if (escolha == 1) {
            //Loop para registrar clientes
            for (int i = 0; i < tamanhoArray; i++) {
                System.out.println(" |\t\t\t\t\t  DIGITE O SEU NOME:\t\t\t\t|");
                System.out.println(" =========================================================");
                clienteInformacao[i] = scanner.nextLine(); //Eu vou guardar o nome

                 // Gambiarra para o loop entender e pular para o proximo Output porque o nosso array tem o tamanho 3

                System.out.println("======================MENU CLIENTE========================");
                System.out.println(" |\t\t\t\t\t\t2 - DIGITE O SEU EMAIL:  \t\t\t\t|");
                System.out.println(" =========================================================");
                clienteInformacao[i] = scanner.nextLine();

                i = 2; // Aqui ponho mais um truque, no contacto nao e preciso porque ja e um numero por si.
            }
            System.out.println("======================MENU CLIENTE========================");
            System.out.println(" |\t\t\t\t\t\t2 - DIGITE O SEU CONTACTO:  \t\t\t\t|");
            System.out.println(" =========================================================");

            ArrayContacto[0] = Integer.parseInt(scanner.nextLine()); // a posicao é indiferente porque eu criei uma array diferente para por o contacto int.
            {

                System.out.println("==================================MENU CLIENTE=================================");
                System.out.println(" |\t\t\t\t\t\t***CLIENTE REGISTRADO COM SUCESSO***\t\t\t\t");
                System.out.println(" |\t\t\t-OLÁ --->\t\t\t\t\t" + clienteInformacao[0] + "\t\t\t\t\t");
                System.out.println(" |\t\t\t-SEU EMAIL --->\t\t\t" + clienteInformacao[1] + "\t\t\t\t\t");
                System.out.println(" |\t\t\t-SEU CONTACTO --->\t\t\t" + ArrayContacto[0] + "\t\t\t\t\t");
                System.out.println(" ==============================================================================");
            }
        } else if (escolha == 2) {
            procurarEstacionamento(scanner);
        } else if (escolha == 3) {
            //  pesquisarPorEditora(filePath, scanner);
        } else if (escolha == 0) {
            System.out.println("Retornando ao Menu Cliente");
            System.exit(0);

        } else {
            System.out.println("Opcao invalida. Tente novamente mais tarde");

        }
    }


    public static void procurarEstacionamento(Scanner scanner) {
        System.out.println("======================PROCURAR ESTACIONAMENTO========================");

        for (int i = 1; i <= 121; i++) {
            // Verificar se i é um número triangular múltiplo de 5
            if (NumeroTriangular(i) && i % 5 == 0) {
                System.out.println("Lugar disponível: " + i);
            }
        }

        System.out.print("Escolha um lugar de estacionamento disponível: ");
        int escolha = scanner.nextInt();

        // Verificar se o lugar escolhido está disponível
        if (NumeroTriangular(escolha) && escolha % 5 == 0) {
            System.out.println("Estacionamento reservado para o lugar " + escolha);
        } else {
            System.out.println("Lugar de estacionamento não disponível ou inválido.");
        }
    }

    public static boolean NumeroTriangular(int n) {

        //Logica
        int soma = 0;
        int i = 1;
        while (soma < n) {
            soma += i;
            i++;
        }
        return soma == n;
    }
}

