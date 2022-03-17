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

    /**
     * calculate the distance between this planet and another planet A
     * @param A the "another" planet 
     * @return the distance:double
     */
    public double calcDistance(Planet A) {
        double dx = this.xxPos - A.xxPos;
        double dy = this.yyPos - A.yyPos;
        double Distance = Math.sqrt(dx * dx + dy * dy);
        return Distance;
    }

    /**
     * @return the magnitude of the force between two planets
     */
    public double calcForceExertedBy(Planet A) {
        final double G = 6.67e-11;
        double Dist = this.calcDistance(A);
        double Force = G * this.mass * A.mass / (Dist * Dist);
        return Force;
    }

    public double calcForceExertedByX(Planet A) {
        double dx = A.xxPos - this.xxPos;

        double ForceX = this.calcForceExertedBy(A) * (dx / this.calcDistance(A));
        return ForceX;
    }

    public double calcForceExertedByY(Planet A) {
        double dy = A.yyPos - this.yyPos;

        double ForceY = this.calcForceExertedBy(A) * (dy / this.calcDistance(A));
        return ForceY;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double xnet = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                xnet += this.calcForceExertedByX(p); 
            }
        }
        return xnet;
    }


    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double ynet = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                ynet += this.calcForceExertedByY(p);
            }
        }
        return ynet;
    }
    public void update(double dt, double fx, double fy) {
        this.xxVel += dt * (fx / this.mass);
        this.yyVel += dt * (fy / this.mass);
        this.xxPos += dt * (this.xxVel);
        this.yyPos += dt * (this.yyVel);
    }

    public void draw() {
        String planetTODraw = "./images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, planetTODraw);
    }
}
