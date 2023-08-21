package api2

class Cidade {
    String nome
    static hasMany = [funcionarios: Funcionario]

    static constraints = {
        nome nullable: false, maxSize: 50
    }
}
