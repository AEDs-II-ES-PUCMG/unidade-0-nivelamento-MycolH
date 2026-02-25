import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Date;

public class ProdutoPerecivel extends Produto{

	private static final double DESCONTO = 0.25;
    // Prazo, em dias, para coonceder o desconto por pro
	private static final int PRAZO_DESCONTO = 7;
    private LocalDate dataValidade;

    public ProdutoPerecivel (String desc, double precoCusto, double margemLucro, LocalDate validade){
        super(desc, precoCusto,margemLucro);
        if (validade.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("O produto está vencido!");
        }
        dataValidade = validade;
    }

    /** Retorna o valor de venda do produto, considerando seu preço de custo, margem de lucro e...*/
    @Override
    public double valorVenda(){
        double desconto = 0d;
        int diasValidade = LocalDate.now().until(dataValidade).getDays(); 
        if (diasValidade <= 7) {
            desconto = DESCONTO;
        }
        return (precoCusto * (1 - margemLucro)) * (1 - desconto);
    }

    // Descrição em string do produto, contendo sua descrição, o valor de venda e data de validade...*/
    @Override
    public String toString(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        String dados = super.toString();
        dados += "\nVálido até " + formato.format(dataValidade);
        return dados;
    }
}
