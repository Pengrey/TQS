package com.tqs.WebApp.Controller;

import com.tqs.WebApp.API.*;
import com.tqs.WebApp.Data.Cache;
import com.tqs.WebApp.Exceptions.NoApisException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@RequestMapping("")
@Controller
@Slf4j
public class MainController {
    // Create whitelist
    String[]  words = new String[] {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Anguilla", "Antigua-and-Barbuda", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia-and-Herzegovina", "Botswana", "Brazil", "British-Virgin-Islands", "Brunei", "Bulgaria", "Burkina-Faso", "Burundi", "Cabo-Verde", "Cambodia", "Cameroon", "Canada", "CAR", "Caribbean-Netherlands", "Cayman-Islands", "Chad", "Channel-Islands", "Chile", "China", "Colombia", "Comoros", "Congo", "Cook-Islands", "Costa-Rica", "Croatia", "Cuba", "Cyprus", "Czechia", "Denmark", "Diamond-Princess", "Diamond-Princess-", "Djibouti", "Dominica", "Dominican-Republic", "DRC", "Ecuador", "Egypt", "El-Salvador", "Equatorial-Guinea", "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Faeroe-Islands", "Falkland-Islands", "Fiji", "Finland", "France", "French-Guiana", "French-Polynesia", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hong-Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle-of-Man", "Israel", "Italy", "Ivory-Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macao", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall-Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique", "MS-Zaandam", "MS-Zaandam-", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "New-Caledonia", "New-Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "North-Macedonia", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua-New-Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Puerto-Rico", "Qatar", "Romania", "Russia", "Rwanda", "S-Korea", "Saint-Helena", "Saint-Kitts-and-Nevis", "Saint-Lucia", "Saint-Martin", "Saint-Pierre-Miquelon", "Samoa", "San-Marino", "Sao-Tome-and-Principe", "Saudi-Arabia", "Senegal", "Serbia", "Seychelles", "Sierra-Leone", "Singapore", "Sint-Maarten", "Slovakia", "Slovenia", "Solomon-Islands", "Somalia", "South-Africa", "South-Sudan", "Spain", "Sri-Lanka", "St-Barth", "St-Vincent-Grenadines", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad-and-Tobago", "Tunisia", "Turkey", "Turks-and-Caicos", "UAE", "Uganda", "UK", "Ukraine", "Uruguay", "US-Virgin-Islands", "USA", "Uzbekistan", "Vanuatu", "Vatican-City", "Venezuela", "Vietnam", "Wallis-and-Futuna", "Western-Sahara", "Yemen", "Zambia", "Zimbabwe"};
    List<String> whitelist = Arrays.asList(words);

    // Create Client
    Client client = new Client();

    // Get APIs
    Api slotixapi = new SlotixApi();
    Api countrystatapi = new CountryStatApi();

    // Create Cache
    Cache cache = new Cache();

    @GetMapping("/")
    public String entryPoint() {
        log.info("Requested MainPage");
        return "mainPage";
    }

    @PostMapping("/")
    public String getResult(@RequestParam(required = false, name = "country") String country, Model model) throws IOException, NoApisException, ParseException, InterruptedException {
        model.addAttribute("result", client.getStats(cache,country,slotixapi,countrystatapi));
        log.info("Requested Stats for country: " + country);
        return "results";
    }

    @GetMapping("/api/v1/cache")
    public @ResponseBody Cache getCache()
    {
        log.info("Requested Cache Stats");
        return cache;
    }

    @GetMapping("/api/v1/stats/{country}")
    public @ResponseBody
    LinkedHashMap<String, String> getStats(@PathVariable String country, @RequestParam(defaultValue = "1", required = false, name = "newCases") String newCases, @RequestParam(defaultValue = "1", required = false, name = "newDeaths") String newDeaths, @RequestParam(defaultValue = "1", required = false, name = "totalCases") String totalCases, @RequestParam(defaultValue = "1", required = false, name = "totalDeaths") String totalDeaths) throws IOException, NoApisException, ParseException, InterruptedException {
        LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
        log.info("Requested Stats for country: " + country);

        if(whitelist.contains(country)){
            ApiResponse request = client.getStats(cache,country,slotixapi,countrystatapi);

            // Add timestamp
            result.put("time", request.getTime());

            // Apply filters
            if(newCases.equals("1"))
                result.put("newCases", request.getNewCases());

            if(newDeaths.equals("1"))
                result.put("newDeaths", request.getNewDeaths());

            if(totalCases.equals("1"))
                result.put("totalCases", request.getTotalCases());

            if(totalDeaths.equals("1"))
                result.put("totalDeaths", request.getTotalDeaths());
        }else{
            log.warn("Requested country: " + country + " not present in the whitelist!");
        }
        return result;
    }

    @GetMapping("/error")
    public String error() {
        log.error("An error occurred");
        return "error";
    }
}
