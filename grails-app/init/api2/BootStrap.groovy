package api2

import api2.Cidade
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BootStrap {
    PlatformTransactionManager transactionManager

    def init = { servletContext ->
        realizarTransacao { status ->
            def cid1 = new Cidade(nome: "Sapiranha").save()
            def cid2 = new Cidade(nome: "Good Field").save()
            def cid3 = new Cidade(nome: "New Hamburguer").save()

            def func1 = new Funcionario(nome: "João", cidade: cid1).save()
            def func2 = new Funcionario(nome: "Maria", cidade: cid2).save()
            def func3 = new Funcionario(nome: "Pedro", cidade: cid3).save()

            // Definir o formato esperado da data
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            LocalDate data1 = LocalDate.parse('10/10/2005', formatter)
            def reajuste1 = new ReajusteSalario(dataReajuste: data1, valorSalario: 2500.00, funcionario: func1).save()
        }
    }
    private realizarTransacao(Closure closure) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager)
        transactionTemplate.execute { status ->
            closure.call(status)
        }
    }

    def destroy = {
    }
}

