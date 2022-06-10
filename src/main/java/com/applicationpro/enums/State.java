package com.applicationpro.enums;

import java.util.*;


//This file to be modified after UI requirements or sample website is provided. Eyyup.
public enum State {

    /*
       ALABAMA("Alabama", "AL"), ALASKA("Alaska", "AK"), AMERICAN_SAMOA("American Samoa", "AS"), ARIZONA("Arizona", "AZ"), ARKANSAS(
            "Arkansas", "AR"), CALIFORNIA("California", "CA"), COLORADO("Colorado", "CO"), CONNECTICUT("Connecticut", "CT"), DELAWARE(
            "Delaware", "DE"), DISTRICT_OF_COLUMBIA("District of Columbia", "DC"), FEDERATED_STATES_OF_MICRONESIA(
            "Federated States of Micronesia", "FM"), FLORIDA("Florida", "FL"), GEORGIA("Georgia", "GA"), GUAM("Guam", "GU"), HAWAII(
            "Hawaii", "HI"), IDAHO("Idaho", "ID"), ILLINOIS("Illinois", "IL"), INDIANA("Indiana", "IN"), IOWA("Iowa", "IA"), KANSAS(
            "Kansas", "KS"), KENTUCKY("Kentucky", "KY"), LOUISIANA("Louisiana", "LA"), MAINE("Maine", "ME"), MARYLAND("Maryland", "MD"), MARSHALL_ISLANDS(
            "Marshall Islands", "MH"), MASSACHUSETTS("Massachusetts", "MA"), MICHIGAN("Michigan", "MI"), MINNESOTA("Minnesota", "MN"), MISSISSIPPI(
            "Mississippi", "MS"), MISSOURI("Missouri", "MO"), MONTANA("Montana", "MT"), NEBRASKA("Nebraska", "NE"), NEVADA("Nevada",
            "NV"), NEW_HAMPSHIRE("New Hampshire", "NH"), NEW_JERSEY("New Jersey", "NJ"), NEW_MEXICO("New Mexico", "NM"), NEW_YORK(
            "New York", "NY"), NORTH_CAROLINA("North Carolina", "NC"), NORTH_DAKOTA("North Dakota", "ND"), NORTHERN_MARIANA_ISLANDS(
            "Northern Mariana Islands", "MP"), OHIO("Ohio", "OH"), OKLAHOMA("Oklahoma", "OK"), OREGON("Oregon", "OR"), PALAU("Palau",
            "PW"), PENNSYLVANIA("Pennsylvania", "PA"), PUERTO_RICO("Puerto Rico", "PR"), RHODE_ISLAND("Rhode Island", "RI"), SOUTH_CAROLINA(
            "South Carolina", "SC"), SOUTH_DAKOTA("South Dakota", "SD"), TENNESSEE("Tennessee", "TN"), TEXAS("Texas", "TX"), UTAH(
            "Utah", "UT"), VERMONT("Vermont", "VT"), VIRGIN_ISLANDS("Virgin Islands", "VI"), VIRGINIA("Virginia", "VA"), WASHINGTON(
            "Washington", "WA"), WEST_VIRGINIA("West Virginia", "WV"), WISCONSIN("Wisconsin", "WI"), WYOMING("Wyoming", "WY"), UNKNOWN(
            "Unknown", "");
     */
    AL("Alabama", "AL"), AK("Alaska", "AK"), AS("American Samoa", "AS"), AZ("Arizona", "AZ"), AR(
            "Arkansas", "AR"), CA("California", "CA"), CO("Colorado", "CO"), CT("Connecticut", "CT"), DE(
            "Delaware", "DE"), DC("District of Columbia", "DC"), FM(
            "Federated States of Micronesia", "FM"), FL("Florida", "FL"), GE("Georgia", "GA"), GU("Guam", "GU"), HI(
            "Hawaii", "HI"), ID("Idaho", "ID"), IL("Illinois", "IL"), IN("Indiana", "IN"), IA("Iowa", "IA"), KS(
            "Kansas", "KS"), KY("Kentucky", "KY"), LA("Louisiana", "LA"), ME("Maine", "ME"), MD("Maryland", "MD"), MH(
            "Marshall Islands", "MH"), MA("Massachusetts", "MA"), MI("Michigan", "MI"), MN("Minnesota", "MN"), MS(
            "Mississippi", "MS"), MO("Missouri", "MO"), MT("Montana", "MT"), NE("Nebraska", "NE"), NV("Nevada",
            "NV"), NH("New Hampshire", "NH"), NJ("New Jersey", "NJ"), NM("New Mexico", "NM"), NY(
            "New York", "NY"), NC("North Carolina", "NC"), ND("North Dakota", "ND"), MP(
            "Northern Mariana Islands", "MP"), OH("Ohio", "OH"), OK("Oklahoma", "OK"), OR("Oregon", "OR"), PW("Palau",
            "PW"), PA("Pennsylvania", "PA"), PR("Puerto Rico", "PR"), RI("Rhode Island", "RI"), SC(
            "South Carolina", "SC"), SD("South Dakota", "SD"), TN("Tennessee", "TN"), TX("Texas", "TX"), UT(
            "Utah", "UT"), VT("Vermont", "VT"), VI("Virgin Islands", "VI"), VA("Virginia", "VA"), WA(
            "Washington", "WA"), WV("West Virginia", "WV"), WI("Wisconsin", "WI"), WY("Wyoming", "WY"), UN(
            "Unknown", "UN");

    /**
     * The state's name.
     */
    private String name;

    /**
     * The state's abbreviation.
     */
    private String abbreviation;

    /**
     * The set of states addressed by abbreviations.
     */
    private static final Map<String, State> STATES_BY_ABBR = new HashMap<String, State>();

    /* static initializer */
    static {
        for (State state : values()) {
            STATES_BY_ABBR.put(state.getAbbreviation(), state);
        }
    }

    /**
     * Constructs a new state.
     *
     * @param name the state's name.
     * @param abbreviation the state's abbreviation.
     */
    State(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    /**
     * Returns the state's abbreviation.
     *
     * @return the state's abbreviation.
     */
    public String getAbbreviation() {
        return abbreviation;
    }
    /**
     * Gets the enum constant with the specified abbreviation.
     *
     * @param abbr the state's abbreviation.
     * @return the enum constant with the specified abbreviation.
     * @throws //SunlightException if the abbreviation is invalid.
     */
    public static State valueOfAbbreviation(final String abbr) {
        final State state = STATES_BY_ABBR.get(abbr);
        if (state != null) {
            return state;
        } else {
            return UN;
        }
    }

    public static State valueOfName(final String name) {
        final String enumName = name.toUpperCase().replaceAll(" ", "_");
        try {
            return valueOf(enumName);
        } catch (final IllegalArgumentException e) {
            return State.UN;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}