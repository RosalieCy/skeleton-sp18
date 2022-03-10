public class NBody {
    public static double readRadius(String filename) {
        In in = new In("./data/planets.txt");
        in.readInt();
        double readRadius = in.readDouble();
        return readRadius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In("./data/planets.txt");
        int numbersofplanets = in.readInt();
        in.readDouble(); // radius
        Planet[] planets = new Planet[numbersofplanets];
        for (int i = 0; i < numbersofplanets; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
        }
        return planets;
    }

    public static void main(String[] args) {
        // args is a array of String passed through command line
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        // start reading file
        double universeradius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);
        int numbersofplanets = planets.length;

        // enable Double Buffering which prevents flickering in the animation
        StdDraw.enableDoubleBuffering();

        StdDraw.setScale(-universeradius, universeradius);
        StdDraw.clear();
        String starfield = "./images/starfield.jpg";

        StdDraw.picture(0, 0, starfield);
        StdDraw.show();

        // Loop til the total time T
        for (int t = 0; t < T; t++) {
            double[] xForces = new double[numbersofplanets];
            double[] yForces = new double[numbersofplanets];
            for (int i = 0; i < numbersofplanets; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                // planets[i].update();
            }
        }
        // TODO
        // for(Planet p : planets){
        // System.out.println(p.imgFileName);
        // p.draw();
        // }
    }
}
