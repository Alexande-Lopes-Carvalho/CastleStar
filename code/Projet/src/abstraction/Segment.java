package abstraction;

import java.io.Serializable;



/**
 * Represente un segment dans l'espace
 * 
 * @author Administrator
 *
 */

public class Segment implements Cloneable, Serializable {
	/**
	 * 
	 */
	public static final long serialVersionUID = 1121009923839487389L;
	private static Point zAngle = new Point(0, 0, -1).getAngle();
	private Point a, b;
	public Segment(Point _a, Point _b) {
		a = _a;
		b = _b;
	}
	
	public Object clone() throws CloneNotSupportedException {
		Segment k = (Segment) super.clone();
		k.a = new Point(a);
		k.b = new Point(b);
		return k;
	}
	
	public Point getA() {
		return a;
	}
	public void setA(Point a) {
		this.a = a;
	}
	public Point getB() {
		return b;
	}
	public void setB(Point b) {
		this.b = b;
	}
	
	/**
	 * retourne le vecteur menant du point a au point b
	 * 
	 * @return vecteur
	 */
	public Point getVector() {
		return Point.getVector(a, b);
	}
	
	/**
	 * Regarde si p appartient � la droite representer par this
	 * <br>renvoie un coefficient k tel que this.getVector().Mult(k) == p
	 * <br>retourne null si p n'appartient pas a la droite
	 * 
	 * @param p
	 * @return coefficient
	 */
	public Double collide(Point p) {
		Point Uvec = this.getVector(), StartVec = a.getVector(p);
		double[][] Matrix = new double[][] { new double[] { Uvec.getX(), Uvec.getY(), Uvec.getZ() },
				new double[] { StartVec.getX(), StartVec.getY(), StartVec.getZ() } };
		/*
		 * Matrix | Ux : [0][0] | | Px-Ax : [1][0] | | Uy : [0][1] |x k = | Py-Ay :
		 * [1][1] | | Uz : [0][2] | | Pz-Az : [1][2] |
		 */
		Double k = null;
		for (int i = 0; i < Matrix[0].length; i++) {
			if (k == null) {
				if (Matrix[0][i] == 0) {
					if (Matrix[1][i] != 0) {
						return null;
					}
				} else {
					k = Matrix[1][i] / Matrix[0][i];
				}
			} else {
				if (Matrix[0][i] * k != Matrix[1][i]) {
					return null;
				}
			}
		}
		return (k == null) ? 0 : k;
	}
	
	/**
	 * Teste si le point p appartient au Segment this
	 * 
	 * @param p
	 * @return r�sultat du test
	 */
	public boolean collideWith(Point p) {
		Double res = collide(p);
		return (res != null && res >= 0 && res <= 1);
	}
	
	/**
	 * Teste si le point p appartient a la droite representer par this
	 * 
	 * @param p
	 * @return r�sultat du test
	 */
	public boolean collideAsLineWith(Point p) {
		Double res = collide(p);
		return (res != null);
	}
	
	/**
	 * regarde si le segemnt s et this sont secant, <br>renvoie un tableau de coefficient tel que:<br>
	 * this.getVector().mult(tab[0]) et s.getVector().mult(tab[1]) soit l'intersection, <br>retourne null si aucune intersection
	 * 
	 * @param s
	 * @return tableau de coefficient
	 */
	public Double[] collide(Segment s) {
		Point Uvec = this.getVector(), Vvec = s.getVector(),
				Startvec = new Point(a.getX() - s.a.getX(), a.getY() - s.a.getY(), a.getZ() - s.a.getZ());
		Vvec.mult(-1.f);
		// println(" Matrix ____");
		// println(" |" + Uvec.x + " " + Vvec.x + " " + Startvec.x + "|");
		// println(" |" + Uvec.y + " " + Vvec.y + " " + Startvec.y + "|");
		// println(" |" + Uvec.z + " " + Vvec.z + " " + Startvec.z + "|");
		// println(" Det : " +
		// (Uvec.x*(Vvec.y*Startvec.z-Vvec.z*Startvec.y)-Uvec.y*(Vvec.x*Startvec.z-Vvec.z*Startvec.x)+Uvec.z*(Vvec.x*Startvec.y-Vvec.y*Startvec.x)));
		if (Uvec.getX() * (Vvec.getY() * Startvec.getZ() - Vvec.getZ() * Startvec.getY()) 
				- Uvec.getY() * (Vvec.getX() * Startvec.getZ() - Vvec.getZ() * Startvec.getX())
				+ Uvec.getZ() * (Vvec.getX() * Startvec.getY() - Vvec.getY() * Startvec.getX()) == 0) {
			double[][] Matrix = new double[][] { new double[] { Uvec.getX(), Uvec.getY(), Uvec.getZ() },
					new double[] { Vvec.getX(), Vvec.getY(), Vvec.getZ() }, new double[] { Startvec.getX(), Startvec.getY(), Startvec.getZ() }, };
			/*
			 * Matrix | Ux : [0][0] -Vx : [1][0] Ax-Bx : [2][0] | | Uy : [0][1] -Vy : [1][1]
			 * Ay-By : [2][1] | | Uz : [0][2] -Vz : [1][2] Az-Bz : [2][2] |
			 */
			int kLigne = -1;
			for (int i = 0; i < Matrix[0].length; i++) {
				if (Matrix[0][i] != 0) {
					kLigne = i;
					break;
				}
			}
			if (kLigne != -1) {
				for (int i = 0; i < Matrix[0].length; i++) {
					if (kLigne != i) {
						double coef = -Matrix[0][i] / Matrix[0][kLigne];
						for (int j = 1; j < Matrix.length; j++) { // Inutile d'effectuer l'operation pour annuler Ui, on
																	// sait qu'elle sera nulle, on ignore la case (moin
																	// d'operation)
							Matrix[j][i] += coef * Matrix[j][kLigne];
						}
					}
				}
			}
			/*
			 * a partir d'ici toute les U a l'exeption de Uk sont nulle, Uk nulle si kLigne
			 * == -1
			 * 
			 * Matrix | Uk : [0][kLigne] -Vk : [1][kLigne] Ak-Bk : [2][kLigne] | | 0 :
			 * [0][/] -V/ : [1][/] A/-B/ : [2][/] | | 0 : [0][.] -V. : [1][.] A.-B. : [2][.]
			 * |
			 */
			// println("a partir d'ici toute les U a l'exeption de Uk sont nulle, Uk nulle
			// si kLigne == -1");
			// println("kLigne : " + kLigne);
			// PrintMatrix(Matrix);
			int lLigne = -1;
			for (int i = 0; i < Matrix[1].length; i++) {
				if (i != kLigne && Matrix[1][i] != 0) {
					lLigne = i;
					break;
				}
			}
			if (lLigne != -1) {
				for (int i = 0; i < Matrix[1].length; i++) {
					if (lLigne != i && kLigne != i) {
						double coef = -Matrix[1][i] / Matrix[1][lLigne]; // Inutile d'effectuer l'operation pour annuler
																			// Vi, on sait qu'elle sera nulle, on ignore
																			// la case V et U (moin d'operation)
						Matrix[2][i] += coef * Matrix[2][lLigne];
					}
				}
			}
			/*
			 * a partir d'ici toute les V a l'exeption de Vl et Vk sont nulle, si kLigne ==
			 * -1 et | lLigne == -1 => tous V nulle | lLigne != -1 => Vl est l'unique non
			 * nulle
			 * 
			 * si kLigne != -1 et | lLigne == -1 => tous V nulle sauf Vk qui peut etre non
			 * nulle | lLigne != -1 => Vl non nulle
			 * 
			 * Matrix | Uk : [0][kLigne] -Vk : [1][kLigne] Ak-Bk : [2][kLigne] | | 0 :
			 * [0][/] -Vl : [1][lLigne] Al-Bl : [2][lLigne] | | 0 : [0][.] 0 : [1][.] A.-B.
			 * : [2][.] |
			 */
			// println("a partir d'ici toute les V a l'exeption de Vl et Vk sont nulle");
			// println("lLigne : " + lLigne);
			// PrintMatrix(Matrix);
			for (int i = 0; i < Matrix[2].length; i++) {
				if (i != kLigne && i != lLigne && Matrix[2][i] != 0) {
					/*
					 * il existe un cas : | 0 : [0][i] 0 : [1][i] x != 0 : [2][i] |
					 * 
					 * ce qui rend impossible le 1 dnas la solution : | k | | l | | 1 |
					 */
					return null;
				}
			}
			// a partir d'ici on a forecement une solution
			double l = (lLigne != -1) ? -Matrix[2][lLigne] / Matrix[1][lLigne] : 0d,
					k = (kLigne != -1) ? -(Matrix[2][kLigne] + Matrix[1][kLigne] * l) / Matrix[0][kLigne] : 0d;
			return new Double[] { k, l };
		} else {
			return null;
		}
	}
	
	/**
	 * Teste si les segments this et k sont s�cants
	 * 
	 * @param k
	 * @return r�sultat du test
	 */
	public boolean collideWith(Segment k) { // this : Segment | k : Segment
		Double[] res = collide(k);
		return (res != null && res[0] >= 0 && res[0] <= 1 && res[1] >= 0 && res[1] <= 1);
	}
	
	/**
	 * Teste si la droite this et le segment k sont s�cants
	 * 
	 * @param k
	 * @return r�sultat du test
	 */
	public boolean collideAsLineWith(Segment k) { // this : Droite | k : Segment
		Double[] res = collide(k);
		return (res != null && res[1] >= 0 && res[1] <= 1);
	}
	
	/**
	 * Teste si le segment this et la droite k sont s�cants
	 * 
	 * @param k
	 * @return r�sultat du test
	 */
	public boolean collideWithLine(Segment k) { // this : Segment | k : Droite
		Double[] res = collide(k);
		return (res != null && res[0] >= 0 && res[0] <= 1);
	}
	
	/**
	 * Teste si les droites this et k sont s�cantes
	 * 
	 * @param k
	 * @return r�sultat du test
	 */
	public boolean collideAsLineWithLine(Segment k) { // this : Droite | k : Droite
		Double[] res = collide(k);
		return (res != null);
	}
	
	/**
	 * retourne l'aire par rapport au segment et a sa projection orthogonale sur le
	 * plan (xoz)
	 * 
	 * @return Aire
	 */
	public double getArea() {
		if (a.getY() >= 0 && b.getY() >= 0) {
			double A =  Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getZ() - b.getZ(), 2)), B = Math.min(a.getY(), b.getY());
			return  (A * B + 0.5 * A * (Math.max(a.getY(), b.getY()) - B));
		} else if (a.getY() <= 0 && b.getY() <= 0) {
			double A =  Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getZ() - b.getZ(), 2)), B = Math.max(a.getY(), b.getY());
			return (A * B + 0.5 * A * (Math.min(a.getY(), b.getY()) - B));
		} else { // collision avec le plan (xoz)
			Double[] res = collide(new Segment(new Point(a.getX(), 0.f, a.getZ()), new Point(b.getX(), 0.f, b.getZ())));
			double cx = a.getX() + res[0].floatValue() * (b.getX() - a.getX()), cz = a.getZ() + res[0].floatValue() * (b.getZ() - a.getZ());
			return (0.5 * (Math.sqrt(Math.pow(a.getX() - cx, 2) + Math.pow(a.getZ() - cz, 2))) * a.getY()
					+ 0.5 * (Math.sqrt(Math.pow(b.getX() - cx, 2) + Math.pow(b.getZ() - cz, 2))) * b.getY());
		}
	}
	
	/**
	 * Retourne la distance entre a et b
	 * 
	 * @return distance
	 */
	public double getDist() {
		return a.getDist(b);
	}
	
	/**
	 * Retourne un Point "Angle", representant les rotations necesssaires par
	 * rapport aux axes pour que un segment (0, 0, 0) (1, 0, 0) ai les memes angles par rapport
	 * aux plans que l'instance
	 * <p>
	 * Sch�ma:
	 * <p>
	 * (R*cos(z)*cos(y), R*sin(z), R*cos(z)*sin(y)) &lt;=&gt; Point(0, y, z).Rotate() +
	 * translate (R, 0, 0)
	 * <p>
	 * (0., R*sin(x), R*cos(x)) &lt;=&gt; Point(x, PI/2., 0).Rotate() + translate(R, 0, 0)<br>
	 * (R*cos(y), 0., R*sin(y)) &lt;=&gt; Point(0., y, 0.).Rotate() + translate(R, 0, 0)<br>
	 * (R*cos(z), R*sin(z), 0.) &lt;=&gt; Point(0., 0., z).Rotate() + translate(R, 0, 0)
	 * 
	 * @return Point "Angle"
	 * 
	 * @see Point#getAngle
	 */
	public Point getAngle() {
		return getVector().getAngle();
	}
	
	/**
	 * Indique de quel cot� se trouve le point c par rapport au Segment this
	 * en fonction de la position de l'observateur
	 * 
	 * @param c Point
	 * @param viewRot Point "Angle" doit etre perpandiculaire au plan (abc)
	 * @return coefficient
	 */
	public double getSide(Point c, Point viewRot) {
		Point res = Point.produitVectoriel(this.getVector(), Point.getVector(a, c)), Angle = res.getAngle();
		// PrintPoint(ViewRot);
		// PrintPoint(Angle);
		return ((Angle.equals(viewRot, 0.0001f)) ? -1 : 1) * res.getDist();
	}
	
	/**
	 * Teste si le Point c se trouve a "gauche" ou sur segment
	 * 
	 * @param c
	 * @param viewRot Point "Angle" doit etre perpandiculaire au plan (abc)
	 * @return r�sultat du test
	 * 
	 * @see Segment#getSide
	 */
	public boolean isPointOnLeft(Point c, Point viewRot) {
		return getSide(c, viewRot) <= 0;
	}
	
	/**
	 * Teste si le Point c se trouve a "gauche" ou sur segment en 2D
	 * 
	 * @param c
	 * @return r�sultat du test
	 * 
	 * @see Segment#getSide
	 */
	public boolean isPointOnLeft(Point c) {
		return getSide(c, zAngle) <= 0;
	}
	
	/**
	 * Teste si le Point c se trouve a "gauche" du segment
	 * 
	 * @param c
	 * @param viewRot Point "Angle" doit etre perpandiculaire au plan (abc)
	 * @return r�sultat du test
	 * 
	 * @see Segment#getSide
	 */
	public boolean isPointOnLeftStrict(Point c, Point viewRot) {
		return getSide(c, viewRot) < 0;
	}
	
	/**
	 * Teste si le Point c se trouve a "gauche" du segment en 2D
	 * 
	 * @param c
	 * @return r�sultat du test
	 * 
	 * @see Segment#getSide
	 */
	public boolean isPointOnLeftStrict(Point c) {
		return getSide(c, zAngle) < 0;
	}
	
	/**
	 * Teste si le Point c se trouve a "droite" ou sur segment
	 * 
	 * @param c
	 * @param viewRot Point "Angle" doit etre perpandiculaire au plan (abc)
	 * @return r�sultat du test
	 * 
	 * @see Segment#getSide
	 */
	public boolean isPointOnRight(Point c, Point viewRot) {
		return getSide(c, viewRot) >= 0;
	}
	
	/**
	 * Teste si le Point c se trouve a "droite" ou sur segment en 2D
	 * 
	 * @param c
	 * @return r�sultat du test
	 * 
	 * @see Segment#getSide
	 */
	public boolean isPointOnRight(Point c) {
		return getSide(c, zAngle) >= 0;
	}
	
	/**
	 * Teste si le Point c se trouve a "droite" du segment 
	 * @param c
	 * @param viewRot Point "Angle" doit etre perpandiculaire au plan (abc)
	 * @return r�sultat du test
	 * 
	 * @see Segment#getSide
	 */
	public boolean isPointOnRightStrict(Point c, Point viewRot) {
		return getSide(c, viewRot) > 0;
	}
	
	/**
	 * Teste si le Point c se trouve a "droite" du segment en 2D 
	 * @param c
	 * @return r�sultat du test
	 * 
	 * @see Segment#getSide
	 */
	public boolean isPointOnRightStrict(Point c) {
		return getSide(c, zAngle) > 0;
	}
	
	/**
	 * Teste si le Point c se trouve sur segment
	 * @param c
	 * @return r�sultat du test
	 * 
	 * @see Segment#getSide
	 */
	public boolean isPointOnCenter(Point c) {
		return getSide(c, zAngle) == 0;
	}
	
	@Override
	public String toString() {
		return "[a : " + a + " | b : " + b + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Segment) {
			Segment k = (Segment) obj;
			return a.equals(k.a) && b.equals(k.b);
		}
		return false;
	}
}
