package api2

class Funcionario {
    String nome
    Cidade cidade

    static constraints = {
        nome nullable: false, maxSize: 50
        cidade nullable: false
    }
    static belongsTo = [cidade: Cidade]

//    static mapping = {
//        id column: "funcionario_id"
//        version false
//    }
}
