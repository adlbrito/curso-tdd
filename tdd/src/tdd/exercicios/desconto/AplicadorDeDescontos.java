package tdd.exercicios.desconto;

/**
 * Como os m�todos {@link #descontoPorProduto(Compra)} e {@link #descontoPorValor(Compra)}
 * s�o privados, a solu��o encontrada para facilitar os testes foi implementar o princ�pio
 * de SoC (Separation of Concerns - separa��o de responsabilidades/preocupa��es) 
 * 
 * @author mauricio
 * @author douglas.japiassu
 * @since 07.11.2015
 *
 */
public class AplicadorDeDescontos {
	DescontoPorProduto descProduto;
	DescontoPorValor descValor;
	
	public AplicadorDeDescontos() {
		this.descProduto = new DescontoPorProduto();
		this.descValor = new DescontoPorValor();
	}
	
	public void aplicaDesconto(Compra compra) {
		boolean retorno = descProduto.executa(compra);
		if (!retorno) descValor.executa(compra);
	}

	@Deprecated
	public void aplica(Compra compra) {
		boolean retorno = descontoPorProduto(compra);
		if(!retorno) descontoPorValor(compra);
	}

	@Deprecated
	private boolean descontoPorProduto(Compra compra) {
		if(compra.tem("MACBOOK") && compra.tem("IPHONE")) {
			compra.reduzValor(compra.getValorLiquido() * 0.15);
			return true;
		}

		if(compra.tem("NOTEBOOK") && compra.tem("WINDOWS PHONE")) {
			compra.reduzValor(compra.getValorLiquido() * 0.12);
			return true;
		}
		
		if(compra.tem("XBOX")) {
			compra.reduzValor(compra.getValorLiquido() * 0.7);
			return true;
		}
		
		return false;
	}

	@Deprecated
	private void descontoPorValor(Compra compra) {
		
		if(compra.getValorLiquido()<=1000 && compra.qtdItens() <= 2) {
			compra.reduzValor(compra.getValorLiquido() * 0.02);
		}
		
		else if(compra.getValorLiquido() > 3000 && compra.qtdItens() < 5 && compra.qtdItens() > 2) {
			compra.reduzValor(compra.getValorLiquido() * 0.05);
		}

		else if(compra.getValorLiquido() > 3000 && compra.qtdItens() >= 5) {
			compra.reduzValor(compra.getValorLiquido() * 0.06);
		}
	}
}
