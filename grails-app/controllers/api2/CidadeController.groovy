package api2

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class CidadeController {

    def cidadeService

    def list() {
        if (request.method != "GET") {
            response.setStatus(405)
            render "Método não permitido para esta ação." as JSON
            return
        }

        def cidades = cidadeService.listarCidades()
        render cidades as JSON
    }

    def get(Long id) {
        if (request.method != "GET") {
            response.setStatus(405)
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
            render "Método não permitido para esta ação."
            return
        }
        try {
            def cidade = Cidade.get(id)
            if (!cidade) {
                render(status: 404, text: "Cidade não encontrada.")
                return
            }
            def funcionarios = Funcionario.findAllByCidade(cidade)
            if (funcionarios) {
                throw new DataIntegrityViolationException("Não é possível excluir a cidade, pois existem funcionários associados a ela. Exclua os funcionários primeiro.")
            }
            cidade.delete()
            render(status: 200, text: "Cidade ${id} deletada com sucesso!")
        } catch (DataIntegrityViolationException e) {
            render(status: 409, text: "Não é possível excluir a cidade, pois existem funcionários associados a ela. Exclua os funcionários primeiro.")
        } catch (Exception e) {
            render(status: 500, text: e.message)
        }
    }
}