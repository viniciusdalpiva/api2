package api2

class Cidade {
    long id
    String nome

    static mapping = {
        id generator: "increment"
        version false
    }
    static hasMany = [funcionarios: Funcionario]
    static constraints = {
        nome nullable: false, maxSize: 50
    }
}
