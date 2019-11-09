package com.example.santos_labexer4;

public class CompanyDetails {
    private String name, country, industry, ceo, des;
    private int logo;

    public CompanyDetails(String name, String country, String industry, String ceo, int logo, String des) {
        this.name = name;
        this.country = country;
        this.industry = industry;
        this.ceo = ceo;
        this.logo = logo;
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getIndustry() {
        return industry;
    }

    public String getCeo() {
        return ceo;
    }

    public int getLogo() {
        return logo;
    }

    public String getDes() {
        return des;
    }
}
