public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);// 这里这么写的话要这个参数干嘛，肯定不对啊
        in.readInt();
        double readRadius = in.readDouble();
        return readRadius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
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

        StdDraw.enableDoubleBuffering();

        // Loop til the total time T
        double t = 0;
        while (t <= T) {
            double[] xForces = new double[numbersofplanets];
            double[] yForces = new double[numbersofplanets];
            for (int i = 0; i < numbersofplanets; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                // planets[i].update();
            }

            for (int i = 0; i < numbersofplanets; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, starfield);

            for (int i = 0; i < numbersofplanets; i++) {
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", universeradius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
