package api2

import com.fasterxml.jackson.annotation.JsonFormat
import grails.converters.JSON
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReajusteSalario {
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataReajuste
    BigDecimal valorSalario
    Funcionario funcionario

    static belongsTo = [funcionario: Funcionario]

    static {
        JSON.registerObjectMarshaller(LocalDate) { LocalDate date ->
            date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }
    }

    static constraints = {
        dataReajuste nullable: false
        valorSalario nullable: false, precision: 6, scale: 2
        funcionario nullable: false
    }
    static mapping = {
        id generator: "increment"
        version false
    }
}