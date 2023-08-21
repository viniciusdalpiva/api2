package api2

import grails.converters.JSON

class FuncionarioController {

    def funcionarioService

    def index() {
        if (request.method != "GET") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação."
            return
        }

        def funcionarios = funcionarioService.listarFuncionarios()
        render funcionarios as JSON
    }

    def show(Long id) {
        if (request.method != "GET") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação."
            return
        }

        def funcionario = funcionarioService.buscarFuncionarioPorId(id)
        render funcionario as JSON
    }

    def save() {
        if (request.method != "POST") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação."
            return
        }

        def funcionarioData = request.JSON
        def funcionario = funcionarioService.criarFuncionario(funcionarioData)
        render funcionario as JSON
    }

    def update(Long id) {
        if (request.method != "PUT") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação."
            return
        }

        def funcionarioData = request.JSON
        def funcionario = funcionarioService.atualizarFuncionario(id, funcionarioData)
        render funcionario as JSON
    }

    def delete(Long id) {
        if (request.method != "DELETE") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação."
            return
        }

        def funcionario = funcionarioService.excluirFuncionario(id)
        render funcionario as JSON
    }
}
