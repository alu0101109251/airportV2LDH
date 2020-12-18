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
package es.ull.flights;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.passengers.Passenger;

/**
 * @class Flight
 * @brief Class which represents a Flight
 */
public class Flight {

    private final String flightNumber;                              /**< String for storing flight number. */
    private final int seats;                                        /**< Number of seats. */
    private final Set<Passenger> passengers = new HashSet<>();      /**< Flight passengers. */

    private static final String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$";   /**< Valid flight number regular expression. */
    private static final Pattern pattern = Pattern.compile(flightNumberRegex);  /**< Regular expression pattern. */

    /**
     * @brief Class constructor
     * @param flightNumber -> flight number, which will be checked
     * @param seats -> number of seats
     */
    public Flight(String flightNumber, int seats) {
        Matcher matcher = pattern.matcher(flightNumber);
        if (!matcher.matches()) {
            throw new RuntimeException("Invalid flight number");
        }
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    /**
     * @brief Getter for flight number
     * @return String -> flightNumber
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @brief Getter for number of passengers
     * @return int -> number of passengers
     */
    public int getNumberOfPassengers() {
        return passengers.size();
    }

    /**
     * @brief Adds a passenger to the current flight
     * @param passenger -> passenger to be added
     * @return boolean -> true if the passenger was added, false otherwise
     */
    public boolean addPassenger(Passenger passenger) {
        if (getNumberOfPassengers() >= seats) {
            throw new RuntimeException("Not enough seats for flight " + getFlightNumber());
        }
        passenger.setFlight(this);
        return passengers.add(passenger);
    }

    /**
     * @brief Removes a passenger of the current flight
     * @param passenger -> passenger to be removed
     * @return boolean -> true if the passenger was removed, false otherwise
     */
    public boolean removePassenger(Passenger passenger) {
        passenger.setFlight(null);
        return passengers.remove(passenger);
    }
}
