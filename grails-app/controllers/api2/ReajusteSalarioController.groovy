package api2

import grails.converters.JSON

class ReajusteSalarioController {

    def reajusteSalarioService

    def index() {
        if (request.method != "GET") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação."
            return
        }

        def reajustes = reajusteSalarioService.listarReajustes()
        render reajustes as JSON
    }

    def show(Long id) {
        if (request.method != "GET") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação."
            return
        }

        def reajuste = reajusteSalarioService.buscarReajustePorId(id)
        render reajuste as JSON
    }

    def save() {
        if (request.method != "POST") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação."
            return
        }
        try {
            def reajusteData = request.JSON
            def reajuste = reajusteSalarioService.criarReajuste(reajusteData)
            render reajuste as JSON
        } catch (IllegalArgumentException e) {
            renderError(e.message)
        }
    }

    def update(Long id) {
        if (request.method != "PUT") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação."
            return
        }
        try {
            def reajusteData = request.JSON
            def reajuste = reajusteSalarioService.atualizarReajuste(id, reajusteData)
            render reajuste as JSON
        } catch (IllegalArgumentException e) {
            renderError(e.message)
        }
    }

    def delete(Long id) {
        if (request.method != "DELETE") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação."
            return
        }

        def reajuste = reajusteSalarioService.excluirReajuste(id)
        render reajuste as JSON
    }
    private renderError(String errorMessage) {
        render(contentType: 'application/json') {
            message "Bad Request"
            error 400
            data "Dados inseridos de maneira incorreta!"
        }
    }
}