//package com.example.app_cardapio.Views

// Importando as bibliotecas necessárias para a funcionalidade do aplicativo
// import android.os.Bundle
// import android.widget.Toast
// import androidx.activity.enableEdgeToEdge
// import androidx.appcompat.app.AppCompatActivity
// import androidx.core.view.ViewCompat
// import androidx.core.view.WindowInsetsCompat
// import androidx.lifecycle.ViewModelProvider
// import com.example.app_cardapio.Models.User
// import com.example.app_cardapio.R
// import com.example.app_cardapio.databinding.ActivityCriarContaViewBinding
// import com.example.app_cardapio.viewModel.CriarContaVM

// Definindo a classe da Activity que representa a tela de criação de conta
// class CriarContaView : AppCompatActivity() {

//     // Declaração das variáveis para o binding e o ViewModel
//     private lateinit var binding: ActivityCriarContaViewBinding
//     private lateinit var viewModel: CriarContaVM

//     // Método chamado quando a Activity é criada
//     override fun onCreate(savedInstanceState: Bundle?) {
//         super.onCreate(savedInstanceState)

//         // Inicializa o binding, que conecta a Activity com o layout XML
//         binding = ActivityCriarContaViewBinding.inflate(layoutInflater)
//         setContentView(binding.root)

//         // Inicializando o ViewModel que irá controlar a lógica da criação de conta
//         viewModel = ViewModelProvider(this).get(CriarContaVM::class.java)

//         // Habilitando o comportamento Edge-to-Edge, permitindo que o conteúdo ocupe toda a tela
//         enableEdgeToEdge()

//         // Definindo o comportamento dos insets da janela (barras do sistema)
//         ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//             val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()) // Obtém os insets da barra de sistema
//             v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom) // Aplica o padding ao conteúdo
//             insets // Retorna os insets para serem aplicados
//         }

//         // Observando a variável isLoading do ViewModel, que indica se o processo de criação da conta está em andamento
//         viewModel.isLoading.observe(this) { isLoading ->
//             // Se estiver carregando, exibe o indicador de progresso
//             if (isLoading) {
//                 // Exemplo de exibição de um progress bar
//                 binding.progressBar.visibility = android.view.View.VISIBLE
//             } else {
//                 // Se não estiver carregando, oculta o progress bar
//                 binding.progressBar.visibility = android.view.View.GONE
//             }
//         }

//         // Observando a variável success do ViewModel, que indica se a criação da conta foi bem-sucedida
//         viewModel.success.observe(this) { success ->
//             // Se a criação da conta foi bem-sucedida, exibe uma mensagem de sucesso
//             if (success) {
//                 Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
//                 // Exemplo de navegação: pode ser para outra Activity ou finalizar a atual
//                 // finish() // Finaliza a Activity atual ou startActivity(intent) para navegar para outra tela
//             }
//         }

//         // Observando a variável errorMessage do ViewModel, que contém a mensagem de erro, caso exista
//         viewModel.errorMessage.observe(this) { errorMessage ->
//             // Se houver uma mensagem de erro, exibe um Toast com o erro
//             errorMessage?.let {
//                 Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//             }
//         }

//         // Ação ao clicar no botão de cadastrar
//         binding.CadastrarUsuario.setOnClickListener {
//             val nomeUsuario = binding.nomeUsuario.text.toString()
//             val nomeCompleto = binding.nomeCompleto.text.toString()
//             val email = binding.seuEmail.text.toString()
//             val senha = binding.senha.text.toString()
//             val confirmaSenha = binding.confirmacaoSenha.text.toString()

//             if (nomeUsuario.isNotBlank() && nomeCompleto.isNotBlank() && email.isNotBlank() && senha.isNotBlank() && confirmaSenha.isNotBlank()) {
//                 // Se todos os campos estiverem preenchidos, cria um objeto User
//                 val user = User(
//                     nomeUsuario = nomeUsuario,
//                     nomeCompleto = nomeCompleto,
//                     email = email,
//                     senha = senha,
//                     confirmaSenha = confirmaSenha
//                 )
//                 // Chama o método para criar a conta no ViewModel
//                 viewModel.criarConta(user)
//             } else {
//                 // Se algum campo estiver vazio, exibe uma mensagem de erro
//                 Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
//             }
//         }
//     }
// }