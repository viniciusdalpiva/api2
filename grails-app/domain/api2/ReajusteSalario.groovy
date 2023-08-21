package api2

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonManagedReference
import grails.converters.JSON

import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReajusteSalario {

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataReajuste
    BigDecimal valorSalario
    Funcionario funcionario

    static {
        JSON.registerObjectMarshaller(LocalDate) { LocalDate date ->
            date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }
    }

    static constraints = {
        dataReajuste nullable: false
        valorSalario nullable: false, scale: 2
        funcionario nullable: false
    }

//    static mapping = {
//        id column: "reajuste_id"
//        version false
//    }
}
