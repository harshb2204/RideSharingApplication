package com.example.RideSharingApplication.services.implementations;

import com.example.RideSharingApplication.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Implementation of the DistanceService interface using OSRM (Open Source Routing Machine) API.
 * This service calculates the driving distance between two geographic points.
 */
@Service
public class DistServiceOSRMImpl implements DistanceService {

    // Base URL for the OSRM API to calculate routes
    private static final String OSRM_API_BASE_URL = "https://router.project-osrm.org/route/v1/driving/";

    // RestTemplate instance used to make HTTP requests
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Calculates the driving distance between two geographic points using OSRM API.
     *
     * @param src The starting point of the route.
     * @param dest The destination point of the route.
     * @return The driving distance in kilometers between the source and destination points.
     * @throws RuntimeException If there is an error getting the response from OSRM or no route is found.
     */
    @Override
    public double calculateDistance(Point src, Point dest) {
        try {
            // Build the OSRM API URL with source and destination coordinates
            // The URL format is: https://router.project-osrm.org/route/v1/driving/{src_lng},{src_lat};{dest_lng},{dest_lat}?overview=false
            String uri = OSRM_API_BASE_URL + src.getX() + "," + src.getY() + ";" + dest.getX() + "," + dest.getY() + "?overview=false";

            // Send a GET request to the OSRM API and get the response as OSRMResponseDto
            OSRMResponseDto osrmResponseDto = restTemplate.getForObject(uri, OSRMResponseDto.class);

            // Check if the response is not null and contains at least one route
            if (osrmResponseDto != null && !osrmResponseDto.getRoutes().isEmpty()) {
                // Get the distance of the first route in the response and convert it from meters to kilometers
                return osrmResponseDto.getRoutes().get(0).getDistance() / 1000.0;
            } else {
                // Throw an exception if no routes are found in the response
                throw new RuntimeException("No route found in the OSRM response.");
            }
        } catch (RestClientException e) {
            // Catch and wrap any RestClientException thrown during the API request
            throw new RuntimeException("Error getting message from OSRM: " + e.getMessage(), e);
        }
    }
}

/**
 * Data Transfer Object (DTO) for the OSRM API response.
 */
@Data
class OSRMResponseDto {
    private List<OSRMRoute> routes; // List of routes in the OSRM response
}

/**
 * Data Transfer Object (DTO) for a single route in the OSRM API response.
 */
@Data
class OSRMRoute {
    private Double distance; // Distance of the route in meters
}
