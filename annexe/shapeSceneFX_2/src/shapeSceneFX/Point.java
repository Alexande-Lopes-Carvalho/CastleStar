package shapeSceneFX;

import java.io.Serializable;

/**
 * Represente un point dans l'espace
 * 
 * @author Alexandre Lopes Carvalho
 *
 */

public class Point implements Cloneable, Serializable {
	/**
	 * 
	 */
	public static final long serialVersionUID = 5882141541229254316L;
	private double x, y, z;

	public Point(double _x, double _y, double _z) {
		set(_x, _y, _z);
	}

	public Point(double _x, double _y) {
		set(_x, _y, 0);
	}

	public Point(Point a) {
		set(a);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * A utilisé pour enchainer les opérations sur la copie, contrairement à clone()
	 * elle ne peut pas lever d'exception
	 * 
	 * @return copie de l'instance
	 */
	public Point copy() {
		return new Point(this);
	}

	@Override
	public String toString() {
		return "[" + x + " " + y + " " + z + "]";
	}
	/*
	public void render(float width) {
		translate();
		PointElement.getApplet().sphere(width);
		translateBack();
	}*/

	/**
	 * Permet la modification des trois composantes x y z en un seul coup
	 * <p>
	 * retourne l'instance pour enchainer les opérations
	 * 
	 * @param _x nouvelle valeur de x
	 * @param _y nouvelle valeur de y
	 * @param _z nouvelle valeur de z
	 * @return this
	 */
	public Point set(double _x, double _y, double _z) {
		x = _x;
		y = _y;
		z = _z;
		return this;
	}

	/**
	 * Permet la modification des deux composantes x y en un seul coup
	 * <p>
	 * retourne l'instance pour enchainer les opérations
	 * 
	 * @param _x nouvelle valeur de x
	 * @param _y nouvelle valeur de y
	 * @return this
	 */
	public Point set(double _x, double _y) {
		x = _x;
		y = _y;
		return this;
	}

	/**
	 * Permet la modification des trois composantes x y z en un seul coup
	 * <p>
	 * retourne l'instance pour enchainer les opérations
	 * 
	 * @param a le point contenant les valeur a copié
	 * @return this
	 */
	public Point set(Point a) {
		x = a.x;
		y = a.y;
		z = a.z;
		return this;
	}

	/**
	 * Fait la somme des points donnés en paramètre et inscrit le resultat dans
	 * l'instance
	 * <p>
	 * retourne l'instance pour enchainer les opérations
	 * 
	 * @param ar ensemble de Point
	 * @return this
	 * 
	 * @see Point#add
	 */
	public Point set(Point... ar) {
		if (ar.length > 0) {
			set(ar[0]);
			for (int i = 1; i < ar.length; i++) {
				add(ar[i]);
			}
		}
		return this;
	}

	/**
	 * Ajoute les valeurs données en paramètre, aux attributs de l'instance
	 * composante par composante
	 * <p>
	 * retourne l'instance pour enchainer les opérations
	 * 
	 * @param _x
	 * @param _y
	 * @param _z
	 * @return this
	 */
	public Point add(double _x, double _y, double _z) {
		x += _x;
		y += _y;
		z += _z;
		return this;
	}

	/**
	 * Ajoute les valeurs données en paramètre, aux attributs de l'instance
	 * composante par composante
	 * <p>
	 * retourne l'instance pour enchainer les opérations
	 * 
	 * @param _x
	 * @param _y
	 * @return this
	 */
	public Point add(double _x, double _y) {
		x += _x;
		y += _y;
		return this;
	}

	/**
	 * Ajoute les composantes du point donné en paramètre, aux attributs de
	 * l'instance composante par composante
	 * <p>
	 * retourne l'instance pour enchainer les opérations
	 * 
	 * @param a
	 * @return this
	 */
	public Point add(Point a) {
		x += a.x;
		y += a.y;
		z += a.z;
		return this;
	}

	/**
	 * Fait la somme de tous les points, et ajoutent le resultat aux composantes du
	 * point
	 * <p>
	 * retourne l'instance pour enchainer les opérations
	 * 
	 * @param ar ensemble de Point
	 * @return this
	 */
	public Point add(Point... ar) {
		for (Point k : ar) {
			add(k);
		}
		return this;
	}

	/**
	 * Soustrait les valeurs données en paramètre, aux attributs de l'instance
	 * composante par composante
	 * <p>
	 * retourne l'instance pour enchainer les opérations
	 * 
	 * @param _x
	 * @param _y
	 * @param _z
	 * @return this
	 */
	public Point sub(double _x, double _y, double _z) {
		x -= _x;
		y -= _y;
		z -= _z;
		return this;
	}

	/**
	 * Soustrait les valeurs données en paramètre, aux attributs de l'instance
	 * composante par composante
	 * <p>
	 * retourne l'instance pour enchainer les opérations
	 * 
	 * @param _x
	 * @param _y
	 * @return this
	 */
	public Point sub(double _x, double _y) {
		x -= _x;
		y -= _y;
		return this;
	}

	/**
	 * Soustrait les composantes du point donné en paramètre, aux attributs de
	 * l'instance composante par composante
	 * <p>
	 * retourne l'instance pour enchainer les opérations
	 * 
	 * @param a
	 * @return this
	 */
	public Point sub(Point a) {
		x -= a.x;
		y -= a.y;
		z -= a.z;
		return this;
	}

	/**
	 * Soustrait les composantes des point donné en paramètre, aux attributs de
	 * l'instance composante par composante
	 * <p>
	 * retourne l'instance pour enchainer les opérations
	 * 
	 * @param ar ensemble de Point
	 * @return this
	 */
	public Point sub(Point... ar) {
		for (Point k : ar) {
			sub(k);
		}
		return this;
	}

	/**
	 * Multiplie les attributs de l'instance par un coefficient
	 * <p>
	 * retourne l'instance pour enchainer les opérations
	 * 
	 * @param k
	 * @return this
	 */
	public Point mult(double k) {
		x *= k;
		y *= k;
		z *= k;
		return this;
	}

	/**
	 * Divise les attributs de l'instance par un coefficient
	 * <p>
	 * retourne l'instance pour enchainer les opérations
	 * 
	 * @param k
	 * @return
	 */
	public Point div(double k) {
		x /= k;
		y /= k;
		z /= k;
		return this;
	}

	/**
	 * Calcule le repère orthonormé après avoir subit des rotations autour de l'axe
	 * x y et z.<br> Les coefficients des rotation provenant de l'instance (Point
	 * "Angle" dont chaque composante represente une rotation autour de l'axe x y
	 * z).
	 * 
	 * @return le repère orthonormé calculé
	 */
	public Point[] getSystemFromAngle() {
		Point u = new Point(1, 0, 0), v = new Point(0, 1, 0), w = new Point(0, 0, 1), a = v.copy();
		// Rotate x
		v.mult( Math.cos(x)).add(w.copy().mult(- Math.sin(x)));
		w.mult( Math.cos(x)).add(a.mult( Math.sin(x)));
		// Rotate y
		a = u.copy();
		u.mult( Math.cos(y)).add(w.copy().mult( Math.sin(y)));
		w.mult( Math.cos(y)).add(a.mult(- Math.sin(y)));
		// Rotate z
		a = u.copy();
		u.mult( Math.cos(z)).add(v.copy().mult( Math.sin(z)));
		v.mult( Math.cos(z)).add(a.mult(- Math.sin(z)));
		return new Point[] { u, v, w };
	}

	/**
	 * Calcule les coordonnées du Point dans le nouveau repère orthonormé fournit en
	 * paramètre. Un repère est un tableau de trois Point.
	 * <p>
	 * Schéma : 
	 * <p>
	 * x*(1, 0, 0) + y*(0, 1, 0) + z*(0, 0, 1) -&gt; x*System[0] + y*System[1]
	 * + z*System[2]
	 * <p>
	 * retourne l'instance pour enchainer les opérations
	 * 
	 * @param system un repère orthonormé
	 * @return this
	 */
	public Point setSystem(Point[] system) {
		if (system.length == 3) {
			double xf = x, yf = y, zf = z;
			x = system[0].x * xf + system[1].x * yf + system[2].x * zf;
			y = system[0].y * xf + system[1].y * yf + system[2].y * zf;
			z = system[0].z * xf + system[1].z * yf + system[2].z * zf;
		}
		return this;
	}

	/**
	 * Calcule les coordonné du Point, après avoir subit des rotations autour de
	 * l'axe x y et z. <br>Les coefficients des rotation provenant du paramètre (Point
	 * "Angle" dont chaque composante represente une rotation autour de l'axe x y
	 * z).
	 * <p>
	 * retourne l'instance pour enchainer les opérations
	 * 
	 * @param angle Point "Angle"
	 * @return this
	 * 
	 * @see Point#getSystemFromAngle
	 * @see Point#setSystem
	 */
	public Point rotate(Point angle) {
		return setSystem(angle.getSystemFromAngle());
	}

	/**
	 * Effectue une translation x y z dans l'applet
	 * 
	 * @see PointElement
	 */
	/*public void translate() {
		translate(x, y);
	}*/

	/**
	 * Effectue une translation x y z "retour" dans l'applet
	 * 
	 * @see PointElement
	 */
	/*public void translateBack() {
		translate(-x, -y);
	}*/

	/**
	 * Effectue une rotation selon l'axe z dans l'applet
	 * <p>
	 * Schéma :
	 * <p>
	 * (R*cos(z)*cos(y), R*sin(z), R*cos(z)*sin(y)) &lt;=&gt; Point(0, y, z).Rotate() +
	 * translate (R, 0, 0)
	 * <p>
	 * (0., R*sin(x), R*cos(x)) &lt;=&gt; Point(x, PI/2., 0).Rotate() + translate(R, 0, 0)<br>
	 * (R*cos(y), 0., R*sin(y)) &lt;=&gt; Point(0., y, 0.).Rotate() + translate(R, 0, 0)<br>
	 * (R*cos(z), R*sin(z), 0.) &lt;=&gt; Point(0., 0., z).Rotate() + translate(R, 0, 0)
	 * 
	 * @see PointElement
	 */
	/*public void rotate() {
		rotate(z);
	}*/

	/**
	 * Effectue une rotation celon l'axe z "retour" dans l'applet
	 * 
	 * @see Point#rotate
	 * @see PointElement
	 */
	/*public void rotateBack() {
		rotate(-z);
	}*/

	/**
	 * Teste si le Point fait parti du segment
	 * 
	 * @param s
	 * @return le resultat du test
	 * 
	 * @see Segment#collideWith
	 */
	public boolean collideWith(Segment s) {
		return s.collideWith(this);
	}

	/**
	 * Teste si le Point fait parti de la droite representé par le segment
	 * 
	 * @param s
	 * @return le resultat du test
	 * 
	 * @see Segment#collideAsLineWith
	 */
	public boolean collideWithLine(Segment s) {
		return s.collideAsLineWith(this);
	}
	
	/**
	 * retourne la distance entre les points a et b
	 * 
	 * @param a
	 * @param b
	 * @return distance
	 */
	public static double getDist(Point a, Point b) {
		return Math.pow(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2) + Math.pow(a.z - b.z, 2), 0.5d);
	}
	
	/**
	 * retourne la distance entre this et le point b
	 * 
	 * @param b
	 * @return distance
	 */
	public double getDist(Point b) {
		return getDist(this, b);
	}

	/**
	 * Calcule la distance entre l'origine (0, 0, 0) et l'instance
	 * 
	 * @return la distance
	 */
	public double getDist() {
		return Math.pow(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2), 0.5d);
	}

	/**
	 * Retourne un Point "Angle", representant les rotations necesssaires par
	 * rapport aux axes pour que un point (1, 0, 0) ai les memes angles par rapport
	 * aux plans que l'instance
	 * <p>
	 * Schéma:
	 * <p>
	 * (R*cos(z)*cos(y), R*sin(z), R*cos(z)*sin(y)) &lt;=&gt; Point(0, y, z).Rotate() +
	 * translate (R, 0, 0)
	 * <p>
	 * (0., R*sin(x), R*cos(x)) &lt;=&gt; Point(x, PI/2., 0).Rotate() + translate(R, 0, 0)<br>
	 * (R*cos(y), 0., R*sin(y)) &lt;=&gt; Point(0., y, 0.).Rotate() + translate(R, 0, 0)<br>
	 * (R*cos(z), R*sin(z), 0.) &lt;=&gt; Point(0., 0., z).Rotate() + translate(R, 0, 0)
	 * 
	 * @return Point "Angle"
	 */
	public Point getAngle() {
		double dist = Math.pow(Math.pow(x, 2) + Math.pow(z, 2), 0.5d);
		if(z == 0){
		    return new Point(0., 0. , ((y == 0)? ((x >= 0)? 0 : -Math.PI) : ((x < 0)? Math.PI : 0)+Math.atan(y/x) ));
		}
		return new Point(0.f,
				 ((x == 0) ? ((z > 0) ? Math.PI / 2. : (z < 0) ? -Math.PI / 2. : 0.)
						: (((x < 0) ? Math.PI : 0) + Math.atan(z / x))),
				 ((dist == 0) ? ((y > 0) ? Math.PI / 2. : ((y < 0) ? -Math.PI / 2. : 0.))
						: Math.atan(y / dist)));
	}
	
	/**
	 * Retourne le vecteur menant du point a au point b
	 * 
	 * @param a
	 * @param b
	 * @return vecteur
	 */
	public static Point getVector(Point a, Point b) {
		return b.copy().sub(a);
	}
	
	/**
	 * Retourne le vecteur menant de this au point a
	 * 
	 * @param a
	 * @return vecteur
	 */
	public Point getVector(Point a) {
		return getVector(this, a);
	}

	/**
	 * Calcule le produit vectoriel a /\ b
	 * <p>
	 * Schéma:
	 * <p>
	 * &nbsp;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;|x = a.y*b.z - a.z*b.y <br>
	 * a /\ b = |y = a.z*b.x - a.x*b.z <br>
	 * &nbsp;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;|z = a.x*b.y - a.y*b.x
	 * <p>
	 * c = a /\ b avec a et b non colinéaire <br>
	 * | c est orthogonale à a et b <br>
	 * | ||c|| = ||a||x||b||*|sin(a, b)| avec (a, b) l'angle entre a et b<br> 
	 * | la base (a, b, c) est de sens direct
	 * 
	 * @param a
	 * @param b
	 * @return a /\ b
	 */
	public static Point produitVectoriel(Point a, Point b) {
		return new Point(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);
	}

	/**
	 * Calcule le produit vectoriel this /\ b
	 * <p>
	 * Schéma:
	 * <p>
	 * &nbsp;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;|x = a.y*b.z - a.z*b.y <br>
	 * a /\ b = |y = a.z*b.x - a.x*b.z <br>
	 * &nbsp;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;|z = a.x*b.y - a.y*b.x
	 * <p>
	 * c = a /\ b avec a et b non colinéaire <br>
	 * | c est orthogonale à a et b <br>
	 * | ||c|| = ||a||x||b||*|sin(a, b)| avec (a, b) l'angle entre a et b <br>
	 * | la base (a, b, c) est de sens direct
	 * 
	 * @param b
	 * @return this /\ b
	 */
	public Point produitVectoriel(Point b) {
		return produitVectoriel(this, b);
	}

	/**
	 * Calcule le produit scalaire &lt; a | b &gt;
	 * <p>
	 * Schéma:
	 * <p>
	 * &lt; a | b &gt; = a.x*b.x + a.y*b.y + a.z*b.z
	 * <p>
	 * Le Produit Scalaire est symetrique, &lt;x|y&gt; = &lt;y|x&gt; <br>Deux Vecteur x et y sont
	 * orthogonaux si &lt;x|y&gt; = 0
	 * <p>
	 * 
	 * &lt;x | y&gt; / (x.Dist()^2) indique de combien le vecteur x s'exprime dans y
	 * (projection de y sur le vecteur x)
	 * <p>
	 * Une base (e1, ... en) est orthogonale si &lt;ei | ej&gt; = 0 pour tout i != j<br>
	 * Elle est dite orthonormale si en plus ||ei|| = 1 pour tout i = 1, ... , n
	 * 
	 * @param a
	 * @param b
	 * @return &lt; a | b &gt;
	 */
	public static double produitScalaire(Point a, Point b) {
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}

	/**
	 * Calcule le produit scalaire &lt; this | b &gt;
	 * <p>
	 * Schéma:
	 * <p>
	 * &lt; a | b &gt; = a.x*b.x + a.y*b.y + a.z*b.z
	 * <p>
	 * Le Produit Scalaire est symetrique, &lt;x|y&gt; = &lt;y|x&gt; <br>Deux Vecteur x et y sont
	 * orthogonaux si &lt;x|y&gt; = 0
	 * <p>
	 * 
	 * &lt;x | y&gt; / (x.Dist()^2) indique de combien le vecteur x s'exprime dans y
	 * (projection de y sur le vecteur x)
	 * <p>
	 * Une base (e1, ... en) est orthogonale si &lt;ei | ej&gt; = 0 pour tout i != j<br>
	 * Elle est dite orthonormale si en plus ||ei|| = 1 pour tout i = 1, ... , n
	 * 
	 * @param b
	 * @return &lt; this | b &gt;
	 */
	public double produitScalaire(Point b) {
		return produitScalaire(this, b);
	}

	/**
	 * Calcule la projection orthogonale de l'instance dans tout les vecteur
	 * <p>
	 * Si les point (e1, ..., en) forme une base orthogonale, on peut alors exprimé
	 * a tq :
	 * <p>
	 * a = k1*e1 + k2*e2 + ... + kn*en
	 * <p>
	 * Au contraire si (e1, ..., en) ne forme pas une base orthogonale la relation
	 * ci-dessus en marchera pas. <br>Les valeur ne seront utilisable que pour les
	 * projection sur chaque vecteur individuellement
	 * 
	 * 
	 * @param ar ensemble de Point "vecteur"
	 * @return tableau des projections
	 * 
	 * @see Point#produitScalaire
	 * @see Point#getDist
	 */
	public double[] projectionOrtho(Point... ar) {
		double[] res = new double[ar.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = produitScalaire(this, ar[i]) / ((float) Math.pow(ar[i].getDist(), 2));
		}
		return res;
	}

	/**
	 * Calcule la projection orthogonale de l'instance dans tout les Segement
	 * <p>
	 * Si les Segment (e1, ..., en) forme une base orthogonale (et on donc tous un
	 * point en commun representant l'origine), on peut alors exprimé a tq :
	 * <p>
	 * a = Origine + k1*e1.Vector() + k2*e2.Vector() + ... + kn*en.Vector()
	 * <p>
	 * Au contraire si (e1, ..., en) ne forme pas une base orthogonale la relation
	 * ci-dessus en marchera pas.<br> Les valeur ne seront utilisable que pour les
	 * projection sur chaque Segment individuellement tq :
	 * <br>
	 * ap = ep.a + kp*ep.Vector()
	 * 
	 * @param ar
	 * @return tableau des projections
	 */
	public double[] projectionOrtho(Segment... ar) {
		double[] res = new double[ar.length];
		for (int i = 0; i < res.length; i++) {
			Point k = new Point(this);
			k.sub(new Point(ar[i].getA()));
			res[i] = produitScalaire(k, ar[i].getVector()) / ((float) Math.pow(ar[i].getDist(), 2));
		}
		return res;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		long result = 1;
		result = prime * result + Double.doubleToLongBits(x);
		result = prime * result + Double.doubleToLongBits(y);
		result = prime * result + Double.doubleToLongBits(z);
		return (int)result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point k = (Point) obj;
			return x == k.x && y == k.y && z == k.z;
		}
		return false;
	}

	/**
	 * Test si le l'instance est plus ou moins egal a b (tolerance de + ou -
	 * epsilon)
	 * 
	 * @param b
	 * @param epsilon
	 * @return resultat du test
	 */
	public boolean equals(Point b, float epsilon) {
		return (x >= b.x - epsilon && x <= b.x + epsilon && y >= b.y - epsilon && y <= b.y + epsilon
				&& z >= b.z - epsilon && z <= b.z + epsilon);
	}
}
