package code;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    private List<Stock> stocks = new ArrayList<Stock>();
    private IStockmarketService stockmarket;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
    }

    public void addStock (Stock stock){
        this.stocks.add(stock);
    }

    public double getTotalValue(){
        return stocks.stream().map(x->x.getQuantity()*stockmarket.lookUpPrice(x.getLabel())).reduce(0d, Double::sum);
    }
}