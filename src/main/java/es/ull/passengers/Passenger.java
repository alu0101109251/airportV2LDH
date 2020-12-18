/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package es.ull.passengers;

import es.ull.flights.Flight;

import java.util.Arrays;
import java.util.Locale;

/**
 * @class Passenger
 * @brief This class represents a Passenger
 */
public class Passenger {

    private final String identifier;    /**< Passenger unique identifier. */
    private final String name;          /**< Passenger name. */
    private final String countryCode;   /**< Passenger country code (f.e.: ES). */
    private Flight flight;              /**< Current flight attached to the passenger. */

    /**
     * @brief Class constructor
     * @param identifier -> passenger unique identifier
     * @param name -> passenger name
     * @param countryCode -> passenger country code
     */
    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    /**
     * @brief Getter for passenger identifier
     * @return String -> identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @brief Getter for passenger name
     * @return String -> name
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Getter for passenger country code
     * @return String -> countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @brief Getter for passenger current flight
     * @return Flight -> flight
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @brief Adds current passenger to a given flight
     * @param flight -> flight to be joint
     */
    public void joinFlight(Flight flight) {
        Flight previousFlight = this.flight;
        if (null != previousFlight) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(flight);
        if (null != flight) {
            if (!flight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /**
     * @brief Sets current flight
     * @param flight -> new flight
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * @brief Gets a string representation of the object
     * @return String -> formatted string
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}
