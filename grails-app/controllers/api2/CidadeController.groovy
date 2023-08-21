package api2

import grails.converters.JSON

class CidadeController {

    def cidadeService

    def index() {
        def cidades = cidadeService.listarCidades()
        render cidades as JSON
    }

    def show(Long id) {
        def cidade = cidadeService.buscarCidadePorId(id)
        render cidade as JSON
    }

    def save() {
        def cidadeData = request.JSON
        def cidade = cidadeService.criarCidade(cidadeData)
        render cidade as JSON
    }

    def update(Long id) {
        def cidadeData = request.JSON
        def cidade = cidadeService.atualizarCidade(id, cidadeData)
        render cidade as JSON
    }

    def delete(Long id) {
        def cidade = cidadeService.excluirCidade(id)
        render cidade as JSON
    }
}
