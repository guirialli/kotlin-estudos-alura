package utilitarios

import dados.entity.PlanoEntity
import modelo.planos.PlanoAssinatura

fun PlanoAssinatura.toEntity(): PlanoEntity{
    return  PlanoEntity(
        tipo = this.tipo,
        mensalidade = this.assinatura.toBigDecimal(),
        jogosIncluidos = this.jogosIncluidos,
        percentualDesconto = this.desconto.toBigDecimal()
    )
}

fun PlanoEntity.toModel(): PlanoAssinatura{
    return PlanoAssinatura(
        id = this.id,
        tipo = this.tipo,
        jogosIncluidos = this.jogosIncluidos,
        assinatura = this.mensalidade.toDouble(),
        desconto = this.percentualDesconto.toDouble()
    )
}