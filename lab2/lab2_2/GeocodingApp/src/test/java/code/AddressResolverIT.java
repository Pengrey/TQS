package code;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressResolverIT {

    private AddressResolver resolver;

    @BeforeEach
    public void init(){
        TqsBasicHttpClient httpClient = new TqsBasicHttpClient();
        resolver = new AddressResolver(httpClient);
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException, NoValidAddress {
        Optional<Address> result = resolver.findAddressForLocation(40.640661, -8.656688);
        assertEquals( result, Optional.of(new Address( "Cais do Alboi", "Glória e Vera Cruz", "Centro", "3800-246", null) ) );
    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {
        assertThrows( IndexOutOfBoundsException.class, ()->{resolver.findAddressForLocation(40.6318, -200.0);});
    }
}

