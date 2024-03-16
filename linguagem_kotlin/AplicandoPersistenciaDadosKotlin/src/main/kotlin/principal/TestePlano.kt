package principal

import dados.Banco
import dados.dao.PlanoDAO
import modelo.planos.PlanoAssinatura

fun main(){
    val plano = PlanoAssinatura(tipo = "bronze", jogosIncluidos = 2, assinatura = 10.99, desconto = 0.25)
    val manager = Banco.getEntityManager()
    val planoDAO = PlanoDAO(manager)
    try{
        //planoDAO.add(plano)
        //planoDAO.addPlanoGamer(34,1)
        //planoDAO.removerGamerPlano(34)
        planoDAO.alterarGamerPlano(34, 1)

        println(planoDAO.getLista())
    }finally {
        manager.close()
    }
}