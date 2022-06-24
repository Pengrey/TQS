package com.tqs.WebApp.API;

public class CountryStatResponse extends ApiResponse{
    private String recordDate, isoCode, continent, location, date, totalCases, newCases, newCasesSmoothed, totalDeaths, newDeaths, newDeathsSmoothed, totalCasesPerMillion, newCasesPerMillion, newCasesSmoothedPerMillion, totalDeathsPerMillion, newDeathsPerMillion, newDeathsSmoothedPerMillion, reproductionRate, icuPatients, icuPatientsPerMillion, hospPatients, hospPatientsPerMillion, weeklyIcuAdmissions, weeklyIcuAdmissionsPerMillion, weeklyHospAdmissions, weeklyHospAdmissionsPerMillion, newTests, totalTests, totalTestsPerThousand, newTestsPerThousand, newTestsSmoothed, newTestsSmoothedPerThousand, positiveRate, testsPerCase, testsUnits, totalVaccinations, peopleVaccinated, peopleFullyVaccinated, totalBoosters, newVaccinations, newVaccinationsSmoothed, totalVaccinationsPerHundred, peopleVaccinatedPerHundred, peopleFullyVaccinatedPerHundred, newVaccinationsSmoothedPerMillion, stringencyIndex, population, populationDensity, medianAge, aged65Older, aged70Older, extremePoverty, cardiovascDeathRate, diabetesPrevalence, femaleSmokers, maleSmokers, handwashingFacilities, hospitalBedsPerThousand, lifeExpectancy, humanDevelopmentIndex, excessMortality;

    @Override
    public String getTime() {
        return this.date;
    }

    @Override
    public String getNewCases() {
        if (!this.newCases.isBlank())
            return this.newCases.split("\\.")[0];
        return this.newCases;
    }

    @Override
    public String getNewDeaths() {
        if (!this.newDeaths.isBlank())
            return this.newDeaths.split("\\.")[0];
        return this.newDeaths;
    }

    @Override
    public String getTotalCases() {
        if (!this.totalCases.isBlank())
            return this.totalCases.split("\\.")[0];
        return this.totalCases;
    }

    @Override
    public String getTotalDeaths() {
        if (!this.totalDeaths.isBlank())
            return this.totalDeaths.split("\\.")[0];
        return this.totalDeaths;
    }

    @Override
    public String toString() {
        return "SlotixResponse{" +
                "Time='" + this.getTime() + '\'' +
                ", NewCases='" + this.getNewCases() + '\'' +
                ", NewDeaths='" + this.getNewDeaths() + '\'' +
                ", TotalCases='" + this.getTotalCases() + '\'' +
                ", TotalDeaths='" + this.getTotalDeaths() + '\'' +
                '}';
    }
}
