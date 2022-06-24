package code;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StocksPortfolioTest {

    @DisplayName("calc value of portfolio for EBAY and MSFT")
    @Test
    void whenGetTotal_thenSumWithMockMarket(){

        // 1 - instantiate the mock substitute
        IStockmarketService market = Mockito.mock( IStockmarketService.class );

        // 2 - instantiate the SuT and inject the mock
        StocksPortfolio portfolio = new StocksPortfolio(market);

        // 3 - "teach" the required expectations (prepare the mock)
        Mockito.when( market.lookUpPrice( "EBAY" )).thenReturn( 4.0 );
        Mockito.when( market.lookUpPrice( "MSFT" )).thenReturn( 1.5 );

        // 4 - execute the test in the SuT
        portfolio.addStock(new Stock("EBAY", 2));
        portfolio.addStock(new Stock("MSFT", 4));
        double result = portfolio.getTotalValue();

        // 5 - verify the result and/or the use  of the mock object
        assertEquals(14.0, result);

        verify(market, times(1)).lookUpPrice( "EBAY" );
        verify(market, times(1)).lookUpPrice( "MSFT" );

        // 1b - verification of the 5 but with the hamcrest library
        assertThat(result, is(14.0));
    }
}
