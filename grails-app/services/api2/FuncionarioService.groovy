package api2

import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate

class FuncionarioService {

    PlatformTransactionManager transactionManager

    def listarFuncionarios() {
        Funcionario.list()
    }

    def buscarFuncionarioPorId(Long id) {
        def funcionario = Funcionario.get(id)
        if (!funcionario) {
            throw new IllegalArgumentException("funcionario com ID ${id} não encontrado.")
        }
        funcionario
    }

    def criarFuncionario(Map funcionarioData) {
        def funcionario = new Funcionario(funcionarioData)
        funcionario.validate()

        if (funcionario.hasErrors()) {
            throw new IllegalArgumentException(funcionario.errors.toString())
        }

        realizarTransacao { status ->
            funcionario.save()
        }

        funcionario
    }

    def atualizarFuncionario(Long id, Map funcionarioData) {
        def funcionario = Funcionario.get(id)
        if (!funcionario) {
            throw new IllegalArgumentException("funcionario com ID ${id} não encontrado.")
        }

        funcionario.properties = funcionarioData
        funcionario.validate()

        if (funcionario.hasErrors()) {
            throw new IllegalArgumentException(funcionario.errors.toString())
        }

        realizarTransacao { status ->
            funcionario.save()
        }

        funcionario
    }

    def excluirFuncionario(Long id) {
        def funcionario = Funcionario.get(id)
        if (!funcionario) {
            throw new IllegalArgumentException("funcionario com ID ${id} não encontrado.")
        }

        realizarTransacao { status ->
            funcionario.delete()
        }

        funcionario
    }

    private realizarTransacao(Closure closure) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager)
        transactionTemplate.execute { status ->
            closure.call(status)
        }
    }
}