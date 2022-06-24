package code;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressResolverTest {


    @Test
    void whenResolveAlboiGps_returnCaisAlboiAddress() throws ParseException, IOException, URISyntaxException, NoValidAddress {

        TqsBasicHttpClient mockedhttpClient = mock(TqsBasicHttpClient.class);

        String resultjson="{\"info\":{\"statuscode\":0,\"copyright\": {\"text\":\"\\u00A9 2021 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"\\u00A9 2021 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":1,\"thumbMaps\":true,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"latLng\":{\"lat\":40.6318,\"lng\":-8.658}},\"locations\":[{\"street\":\"Parque Estacionamento da Reitoria - Univerisdade de Aveiro\",\"adminArea6\":\"\",\"adminArea6Type\":\"Neighborhood\",\"adminArea5\":\"Gl\\u00F3ria e Vera Cruz\",\"adminArea5Type\":\"City\",\"adminArea4\":\"\",\"adminArea4Type\":\"County\",\"adminArea3\":\"Centro\",\"adminArea3Type\":\"State\",\"adminArea1\":\"PT\",\"adminArea1Type\":\"Country\",\"postalCode\":\"3810-193\",\"geocodeQualityCode\":\"P1AAA\",\"geocodeQuality\":\"POINT\",\"dragPoint\":false,\"sideOfStreet\":\"N\",\"linkId\":\"0\",\"unknownInput\":\"\",\"type\":\"s\",\"latLng\":{\"lat\":40.631803,\"lng\":-8.657881},\"displayLatLng\":{\"lat\":40.631803,\"lng\":-8.657881},\"mapUrl\":\"http://open.mapquestapi.com/staticmap/v5/map?key=uXSAVwYWbf9tJmsjEGHKKAo0gOjZfBLQ&type=map&size=225,160&locations=40.6318025,-8.657881|marker-sm-50318A-1&scalebar=true&zoom=15&rand=-98728279\",\"roadMetadata\":null}]}]}";

        AddressResolver resolver = new AddressResolver(mockedhttpClient);

        when(mockedhttpClient.doHttpGet(contains("location=40.631800%2C-8.658000"))).thenReturn(resultjson);

        Optional<Address> result = resolver.findAddressForLocation(40.6318, -8.658);

        assertEquals( result, Optional.of(new Address( "Parque Estacionamento da Reitoria - Univerisdade de Aveiro", "Glória e Vera Cruz", "Centro", "3810-193", null) ) );
    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddress() throws IOException, URISyntaxException, ParseException {
        TqsBasicHttpClient mockedhttpClient = mock(TqsBasicHttpClient.class);

        String badresultjson="{\"info\":{\"statuscode\":0,\"copyright\":{\"text\":\"\\u00A9 2021 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"\\u00A9 2021 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":1,\"thumbMaps\":true,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"latLng\":{\"lat\":40.6318,\"lng\":-200.0}},\"locations\":[]}]}";

        AddressResolver resolver = new AddressResolver(mockedhttpClient);

        when(mockedhttpClient.doHttpGet(contains("location=40.631800%2C-200.000"))).thenReturn(badresultjson);

        assertThrows( NoValidAddress.class, ()->{resolver.findAddressForLocation(40.6318, -200.0);});
    }
}

