package com.sparta.alena.ProjectTest;

public class Data {
    public class Codes{
        public String admin_district;
        public String admin_county;
        public String admin_ward;
        public String parish;
        public String parliamentary_constituency;
        public String ccg;
        public String ccg_id;
        public String ced;
        public String nuts;
        public String lsoa;
        public String msoa;
        public String lau2;
    }

    public class Result{
        public String postcode;
        public Integer quality;
        public Integer eastings;
        public Integer northings;
        public String country;
        public String nhs_ha;
        public Double longitude;
        public Double latitude;
        public String european_electoral_region;
        public String primary_care_trust;
        public String region;
        public String lsoa;
        public String msoa;
        public String incode;
        public String outcode;
        public String parliamentary_constituency;
        public String admin_district;
        public String parish;
        public Object admin_county;
        public String admin_ward;
        public Object ced;
        public String ccg;
        public String nuts;
        public Codes codes;
    }

    public class Root{
        public Integer status;
        public Result result;
    }
}
