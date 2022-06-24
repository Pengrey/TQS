package com.tqs.WebApp.API;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CountryStatApiTest {
    public CountryStatApi api;

    @BeforeEach
    public void setUp() {
        api = new CountryStatApi();
    }

    @AfterEach
    public void tearDown() {
        api = null;
    }

    @Test
    @DisplayName("Get right URI for country")
    void apiGetURIForCountry() {
        String result = api.getURI("Portugal");
        assertThat(result, is("https://countrystat.p.rapidapi.com/coronavirus/who_latest_stat_by_country.php?country=Portugal"));
    }

    @Test
    @DisplayName("Get right Host for API")
    void apiGetApiHost() {
        assertThat(api.getHost(), is("countrystat.p.rapidapi.com"));
    }

    @Test
    @DisplayName("Parse result from API to object")
    void apiParseResult() {
        CountryStatResponse parsedResponse = api.parseResponse("{\"recordDate\":\"2022-04-16\",\"isoCode\":\"PRT\",\"continent\":\"Europe\",\"location\":\"Portugal\",\"date\":\"2022-04-14\",\"totalCases\":\"3719485.0\",\"newCases\":\"10459.0\",\"newCasesSmoothed\":\"10121.571\",\"totalDeaths\":\"21993.0\",\"newDeaths\":\"23.0\",\"newDeathsSmoothed\":\"23.714\",\"totalCasesPerMillion\":\"365805.78\",\"newCasesPerMillion\":\"1028.627\",\"newCasesSmoothedPerMillion\":\"995.441\",\"totalDeathsPerMillion\":\"2162.979\",\"newDeathsPerMillion\":\"2.262\",\"newDeathsSmoothedPerMillion\":\"2.332\",\"reproductionRate\":\"\",\"icuPatients\":\"\",\"icuPatientsPerMillion\":\"\",\"hospPatients\":\"\",\"hospPatientsPerMillion\":\"\",\"weeklyIcuAdmissions\":\"\",\"weeklyIcuAdmissionsPerMillion\":\"\",\"weeklyHospAdmissions\":\"\",\"weeklyHospAdmissionsPerMillion\":\"\",\"newTests\":\"\",\"totalTests\":\"\",\"totalTestsPerThousand\":\"\",\"newTestsPerThousand\":\"\",\"newTestsSmoothed\":\"\",\"newTestsSmoothedPerThousand\":\"\",\"positiveRate\":\"\",\"testsPerCase\":\"\",\"testsUnits\":\"\",\"totalVaccinations\":\"\",\"peopleVaccinated\":\"\",\"peopleFullyVaccinated\":\"\",\"totalBoosters\":\"\",\"newVaccinations\":\"\",\"newVaccinationsSmoothed\":\"\",\"totalVaccinationsPerHundred\":\"\",\"peopleVaccinatedPerHundred\":\"\",\"peopleFullyVaccinatedPerHundred\":\"\",\"newVaccinationsSmoothedPerMillion\":\"\",\"stringencyIndex\":\"\",\"population\":\"10167923.0\",\"populationDensity\":\"112.371\",\"medianAge\":\"46.2\",\"aged65Older\":\"21.502\",\"aged70Older\":\"14.924\",\"extremePoverty\":\"0.5\",\"cardiovascDeathRate\":\"127.842\",\"diabetesPrevalence\":\"9.85\",\"femaleSmokers\":\"16.3\",\"maleSmokers\":\"30.0\",\"handwashingFacilities\":\"\",\"hospitalBedsPerThousand\":\"3.39\",\"lifeExpectancy\":\"82.05\",\"humanDevelopmentIndex\":\"0.864\",\"excessMortality\":\"\"}");
        assertThat(parsedResponse.toString(), is("SlotixResponse{Time='2022-04-14', NewCases='10459', NewDeaths='23', TotalCases='3719485', TotalDeaths='21993'}"));
    }
}
