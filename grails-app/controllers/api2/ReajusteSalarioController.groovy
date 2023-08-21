package api2

import grails.converters.JSON

class ReajusteSalarioController {

    def reajusteSalarioService

    def index() {
        def reajustes = reajusteSalarioService.listarReajustes()
        render reajustes as JSON
    }

    def show(Long id) {
        def reajuste = reajusteSalarioService.buscarReajustePorId(id)
        render reajuste as JSON
    }

    def save() {
        def reajusteData = request.JSON
        def reajuste = reajusteSalarioService.criarReajuste(reajusteData)
        render reajuste as JSON
    }

    def update(Long id) {
        def reajusteData = request.JSON
        def reajuste = reajusteSalarioService.atualizarReajuste(id, reajusteData)
        render reajuste as JSON
    }

    def delete(Long id) {
        def reajuste = reajusteSalarioService.excluirReajuste(id)
        render reajuste as JSON
    }
}
