package api2

import grails.converters.JSON

class FuncionarioController {

    def funcionarioService

    def index() {
        def funcionarios = funcionarioService.listarFuncionarios()
        render funcionarios as JSON
    }

    def show(Long id) {
        def funcionario = funcionarioService.buscarFuncionarioPorId(id)
        render funcionario as JSON
    }

    def save() {
        def funcionarioData = request.JSON
        def funcionario = funcionarioService.criarFuncionario(funcionarioData)
        render funcionario as JSON
    }

    def update(Long id) {
        def funcionarioData = request.JSON
        def funcionario = funcionarioService.atualizarFuncionario(id, funcionarioData)
        render funcionario as JSON
    }

    def delete(Long id) {
        def funcionario = funcionarioService.excluirFuncionario(id)
        render funcionario as JSON
    }
}