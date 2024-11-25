// package com.example.app_cardapio.viewModel

// Importando as bibliotecas necessárias para a funcionalidade do ViewModel
// import androidx.lifecycle.LiveData
// import androidx.lifecycle.MutableLiveData
// import androidx.lifecycle.ViewModel
// import com.example.app_cardapio.Models.User
// import com.example.app_cardapio.Models.Repository.UserRepository

// Definindo o ViewModel para a tela de criação de conta
// class CriarContaVM : ViewModel() {

//     // Inicializando o repositório de usuários
//     private val userRepository = UserRepository()

//     // Variáveis observáveis para controlar o estado da UI
//     private val _isLoading = MutableLiveData<Boolean>()
//     val isLoading: LiveData<Boolean> get() = _isLoading // Exibe o estado de carregamento

//     private val _success = MutableLiveData<Boolean>()
//     val success: LiveData<Boolean> get() = _success // Exibe se a criação da conta foi bem-sucedida

//     private val _errorMessage = MutableLiveData<String?>()
//     val errorMessage: LiveData<String?> get() = _errorMessage // Exibe a mensagem de erro, se houver

//     // Função para validar se as senhas coincidem
//     fun validarSenhas(senha: String, confirmaSenha: String): Boolean {
//         return senha == confirmaSenha // Retorna true se as senhas coincidirem
//     }

//     // Função para criar a conta e registrar o usuário
//     fun criarConta(user: User) {
//         _isLoading.value = true // Inicia o carregamento
//         _errorMessage.value = null // Limpa a mensagem de erro anterior

//         // Verifica se as senhas coincidem
//         if (validarSenhas(user.senha, user.confirmaSenha)) {
//             // Chama o método de registro do repository
//             userRepository.registerUser(user) { isSuccessful, message ->
//                 _isLoading.value = false // Finaliza o carregamento
//                 if (isSuccessful) {
//                     _success.value = true // Se o registro for bem-sucedido, indica sucesso
//                 } else {
//                     _errorMessage.value = message // Caso contrário, exibe a mensagem de erro
//                     _success.value = false // Indica que não foi bem-sucedido
//                 }
//             }
//         } else {
//             _isLoading.value = false // Finaliza o carregamento
//             _errorMessage.value = "As senhas não coincidem!" // Se as senhas não coincidirem, exibe erro
//         }
//     }
// }

