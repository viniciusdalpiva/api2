package api2

import grails.converters.JSON

class CidadeController {

    def cidadeService

    def index() {
        if (request.method != "GET") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação." as JSON
            return
        }

        def cidades = cidadeService.listarCidades()
        render cidades as JSON
    }

    def show(Long id) {
        if (request.method != "GET") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação." as JSON
            return
        }

        def cidade = cidadeService.buscarCidadePorId(id)
        render cidade as JSON
    }

    def save() {
        if (request.method != "POST") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação."
            return
        }
        try {
            def cidadeData = request.JSON
            def cidade = cidadeService.criarCidade(cidadeData)
            render cidade as JSON
        } catch (IllegalArgumentException e) {
            renderError(e.message)
        }
    }

    def update(Long id) {
        if (request.method != "PUT") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação." as JSON
            return
        }

        try {
            def cidadeData = request.JSON
            def cidade = cidadeService.atualizarCidade(id, cidadeData)
            render cidade as JSON
        } catch (IllegalArgumentException e) {
            renderError(e.message)
        }
    }

    def delete(Long id) {
        if (request.method != "DELETE") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação." as JSON
            return
        }

        def cidade = cidadeService.excluirCidade(id)
        render cidade as JSON
    }
    private renderError(String errorMessage) {
        render(contentType: 'application/json') {
            message "Bad Request"
            error 400
            data "Dados inseridos de maneira incorreta!"
        }
    }
}