public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet A) {
        double dx = this.xxPos - A.xxPos;
        double dy = this.yyPos - A.yyPos;
        double Distance = Math.sqrt(dx * dx + dy * dy);
        return Distance;
    }

    public double calcForceExertedBy(Planet A) {
        final double G = 6.67e-11;
        double Dist = this.calcDistance(A);
        double Force = G * this.mass * A.mass / (Dist * Dist);
        return Force;
    }

    public double calcForceExertedByX(Planet A) {
        double dx = this.xxPos - A.xxPos;
        if (dx < 0) {
            dx = -dx;
        }
        double ForceX = this.calcForceExertedBy(A) * (dx / this.calcDistance(A));
        return ForceX;
    }

    public double calcForceExertedByY(Planet A) {
        double dy = this.yyPos - A.yyPos;
        if (dy < 0) {
            dy = -dy;
        }
        double ForceY = this.calcForceExertedBy(A) * (dy / this.calcDistance(A));
        return ForceY;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double net = 0;
        for (Planet p : allPlanets) {
            if (this.equals(p)) {
                continue;
            } else {
                net += this.calcForceExertedByX(p);
            }
        }
        return net;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double net = 0;
        for (Planet p : allPlanets) {
            if (this.equals(p)) {
                continue;
            } else {
                net += this.calcForceExertedByY(p);
            }
        }
        return net;
    }
}
