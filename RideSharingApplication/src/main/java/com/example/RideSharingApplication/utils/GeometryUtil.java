package com.example.RideSharingApplication.utils;

import com.example.RideSharingApplication.dto.PointDto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

/**
 * Utility class for handling geometric operations.
 * Specifically, this class provides methods to work with geographic points.
 */
public class GeometryUtil {

    /**
     * Converts a PointDto object to a JTS (Java Topology Suite) Point object.
     *
     * param pointDto The PointDto object containing coordinates.
     * return A JTS Point object created from the coordinates in pointDto.
     */
    public static Point createPoint(PointDto pointDto) {
        // Create a GeometryFactory instance with a precision model and a spatial reference system (SRID) of 4326.
        // SRID 4326 corresponds to the WGS 84 coordinate system used by GPS.
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

        // Create a Coordinate object using the coordinates provided in pointDto.
        // Coordinates are expected to be in [longitude, latitude] format.
        Coordinate coordinate = new Coordinate(pointDto.getCoordinates()[0],
                pointDto.getCoordinates()[1]);

        // Create a Point object using the GeometryFactory and the created Coordinate.
        // The Point object represents a specific location in a 2D or 3D space.
        return geometryFactory.createPoint(coordinate);
    }
}
