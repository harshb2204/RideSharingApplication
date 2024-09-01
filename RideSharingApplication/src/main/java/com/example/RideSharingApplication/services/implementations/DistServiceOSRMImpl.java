package com.example.RideSharingApplication.services.implementations;

import com.example.RideSharingApplication.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DistServiceOSRMImpl implements DistanceService {

    private static final String OSRM_API_BASE_URL = "https://router.project-osrm.org/route/v1/driving/";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public double calculateDistance(Point src, Point dest) {
        try {
            // Build the URL with the correct query parameters
            String uri = OSRM_API_BASE_URL + src.getX() + "," + src.getY() + ";" + dest.getX() + "," + dest.getY() + "?overview=false";

            OSRMResponseDto osrmResponseDto = restTemplate.getForObject(uri, OSRMResponseDto.class);

            if (osrmResponseDto != null && !osrmResponseDto.getRoutes().isEmpty()) {
                return osrmResponseDto.getRoutes().get(0).getDistance() / 1000.0;
            } else {
                throw new RuntimeException("No route found in the OSRM response.");
            }
        } catch (RestClientException e) {
            throw new RuntimeException("Error getting message from OSRM: " + e.getMessage(), e);
        }
    }
}

@Data
class OSRMResponseDto {
    private List<OSRMRoute> routes;
}

@Data
class OSRMRoute {
    private Double distance;
}
