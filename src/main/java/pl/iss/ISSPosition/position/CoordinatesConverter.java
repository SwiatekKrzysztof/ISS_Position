package pl.iss.ISSPosition.position;

public class CoordinatesConverter {
        public static final double RADIUS = 6378137.0; /* in meters on the equator */
        public static final double EQUATOR = 40075704.0; /* in meters on the equator */
        public static final double MERIDIAN = 20003930.0; /* in meters on the equator */
        /* These functions take their length parameter in meters and return an angle in degrees */

        public static double yToLatitude(double y) {
            return Math.toDegrees(Math.atan(Math.exp(y / RADIUS)) * 2 - Math.PI/2);
        }
        public static double xToLongitude(double x) {
            return Math.toDegrees(x / RADIUS);
        }

        /* These functions take their angle parameter in degrees and return a length in meters */

        public static double latitudeToY(double lat) {
            return Math.log(Math.tan(Math.PI / 4 + Math.toRadians(lat) / 2)) * RADIUS;
        }
        public static double longitudeToX(double lon) {
            return Math.toRadians(lon) * RADIUS;
        }
}
